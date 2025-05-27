package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BillingReceiveInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.BillingApplyDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.SerialNumberUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author wangwei
 * @date 2018/10/9
 */
@ApiService(descript = "认领明细表")
@Service
public class BillingReceiveInfoApiImpl extends BaseServiceImpl implements BillingReceiveInfoApi {

    @Autowired
    private BillingReceiveInfoMapper billingReceiveInfoMapper;

    @Autowired
    private BillingApplyMapper billingApplyMapper;

    @Autowired
    private BillingApplyAccountsMapper billingApplyAccountsMapper;

    @Autowired
    private BillingApplyImgsMapper billingApplyImgsMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private BillingApplyUnmatchMapper billingApplyUnmatchMapper;

    @Autowired
    private BillingRefundInfoMapper billingRefundInfoMapper;
    @Autowired
    private StaffPersonnelInfoMapper staffPersonnelInfoMapper;
    @Autowired
    private SurveyPayInfoMapper surveyPayInfoMapper;

    /**
     * 认领明细表List
     * @param request
     * @return
     */
    @ApiMethod(descript = "认领明细表List" ,value = "billing-receive-info-selectByMap")
    @Override
    public ApiResponse selectByMap(ApiRequest request) {
        request.put("unmatchId",request.getLong("id"));
        List<BillingApplyDto> list=billingApplyMapper.selectByList(request);
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, list);
    }

    @ApiMethod(descript = "认领明细表 退票列表" ,value = "billing-receive-info-selectByMap-refund")
    @Override
    public ApiResponse selectByMapRefund(ApiRequest request) {
        Long id = request.getLong("id");
        List<BillingRefundInfo> list=billingRefundInfoMapper.selectByMatchId(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, list);
    }

    /**
     * 开票认领
     * @param request
     * @return
     */
    @ApiMethod(descript = "开票认领" ,value = "billing-receive-info-change")
    public ApiResponse change(ApiRequest request) {
        Long currentUserId = getCurrentUserId(request);
        UserInfo userInfo=userInfoMapper.selectByPrimaryKey(currentUserId);
        boolean apply = false;
        if ( StringUtils.isNotBlank(request.getString("apply")) && "true".equals(request.getString("apply"))){
            apply = true;
        }
        List<BillingReceiveInfo> entityList = JSONArray.parseArray(request.getString("billingReceiveInfoList"),BillingReceiveInfo.class);
        BillingApplyUnmatch billingApplyUnmatch=billingApplyUnmatchMapper.selectByPrimaryKey(request.getLong("billingMatchId"));
        if(billingApplyUnmatch != null){
            //计算查询出来的数据总和
            Double ageSum = entityList.stream().mapToDouble(BillingReceiveInfo::getReceiveMoney).sum();
            Double matchMoney=(billingApplyUnmatch.getMatchMoney()==null?0:billingApplyUnmatch.getMatchMoney())+ageSum;
            Double unmatchMoney=billingApplyUnmatch.getMoney();
            if(matchMoney>unmatchMoney){
                return new ApiResponse(ApiMsgEnum.FAIL, 0, "认领金额总和大于匹配收款金额!");
            }else if(matchMoney<unmatchMoney){
                billingApplyUnmatch.setState(3);
            }else if(matchMoney.equals(unmatchMoney)){
                billingApplyUnmatch.setState(2);
            }
            if (!apply){
                billingApplyUnmatch.setMatchMoney(matchMoney);
                billingApplyUnmatch.setUnmatchMoney(unmatchMoney-matchMoney);
            }else{
                billingApplyUnmatch.setUnmatchMoney(billingApplyUnmatch.getUnmatchMoney()-ageSum);
            }

            billingApplyUnmatch.setClaimById(userInfo.getUserId());
            billingApplyUnmatch.setClaimBy(userInfo.getUserName());
            billingApplyUnmatch.setClaimTime(new Date());
            billingApplyUnmatchMapper.updateByPrimaryKey(billingApplyUnmatch);
        }
        for (BillingReceiveInfo billingReceiveInfo:entityList) {
            if(billingReceiveInfo.getBillingImgsId() != null){
                //根据开票申请表附表Id查询公估确认到账记录表中是否存在
                BillingApplyImgs billingApplyImgs=billingApplyImgsMapper.selectByPrimaryKey(Long.parseLong(billingReceiveInfo.getBillingImgsId().toString()));
                if(billingApplyImgs != null){
                    ////根据开票申请表附表Id查询公估确认到账记录数据
                    List<BillingApplyAccounts> BillingApplyAccountsList=  billingApplyAccountsMapper.selectListByImgsId(billingApplyImgs.getId());
                    //计算查询出来的数据总和
                    Double ageSumTwo = BillingApplyAccountsList.stream().mapToDouble(BillingApplyAccounts::getMoney).sum();
                    //计算到账总额加本次认领金额
                    Double countMoney=ageSumTwo + (billingReceiveInfo.getReceiveMoney()==null?0:billingReceiveInfo.getReceiveMoney());
                    Double billingMoney=billingApplyImgs.getBillingMoney()==null?0:billingApplyImgs.getBillingMoney();
                    //判断认领总金额状态
                    if(billingMoney<countMoney){
                        return new ApiResponse(ApiMsgEnum.FAIL, 0, "认领金额总和大于发票金额!");
                    }else if(billingMoney>countMoney){
                        billingApplyImgs.setPayState(1);
                    }else if(billingMoney.equals(countMoney)){
                        billingApplyImgs.setPayState(2);
                    }
                    //修改开票申请表附表到账状态
                    billingApplyImgsMapper.updateByPrimaryKey(billingApplyImgs);
                }
                Date startTime = new Date();
                if (StringUtils.isNotBlank(request.getString("startTime"))){
                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        startTime = simpleDateFormat.parse(request.getString("startTime"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                //创建认领明细表
                billingReceiveInfo.setBillingMatchId(request.getInt("billingMatchId"));
                billingReceiveInfo.setReceiveUserId(Integer.parseInt(userInfo.getUserId().toString()));
                billingReceiveInfo.setReceiveUserName(userInfo.getUserName());
                billingReceiveInfo.setReceiveTime(startTime);
                if (apply){//申请认领
                    billingReceiveInfo.setReceiveType(2);
                    billingReceiveInfo.setReceiveStatus(1);
                }else{
                    billingReceiveInfo.setReceiveType(1);
                    //创建公估确认到账记录记录
                    BillingApplyAccounts billingApplyAccounts=new BillingApplyAccounts();
                    billingApplyAccounts.setBillId(Long.parseLong(billingReceiveInfo.getBillingId().toString()));
                    billingApplyAccounts.setBillImgsId(Long.parseLong(billingReceiveInfo.getBillingImgsId().toString()));
                    billingApplyAccounts.setMoney(billingReceiveInfo.getReceiveMoney());
                    billingApplyAccounts.setState(1);
                    billingApplyAccounts.setAccountTime(billingApplyUnmatch.getPayTime());
                    billingApplyAccountsMapper.insertSelective(billingApplyAccounts);
                }
                billingReceiveInfoMapper.insertOne(billingReceiveInfo);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, null);
    }


    @ApiMethod(descript = "开票认领修改" ,value = "billing-receive-info-change-upd")
    public ApiResponse receiveBillUpd(ApiRequest request) {

        Long receiveId = request.getLong("receiveId");
        String type = request.getString("type");
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("id",receiveId);
        BillingReceiveInfo billingReceiveInfo = billingReceiveInfoMapper.selectByOne(paramMap);
        if (billingReceiveInfo != null){
            if (StringUtils.isNotBlank(type)){
                BillingApplyUnmatch billingApplyUnmatch = billingApplyUnmatchMapper.selectByPrimaryKey(Long.valueOf(billingReceiveInfo.getBillingMatchId().toString()));
                Double receiveMoney = Optional.ofNullable(billingReceiveInfo.getReceiveMoney()).orElse(0d);
                if ("pass".equals(type)){
                    billingReceiveInfo.setReceiveStatus(2);
                    billingReceiveInfoMapper.updateOne(billingReceiveInfo);
                    billingApplyUnmatch.setMatchMoney(billingApplyUnmatch.getMatchMoney()+receiveMoney);
                    billingApplyUnmatchMapper.updateByPrimaryKeySelective(billingApplyUnmatch);

                    //创建公估确认到账记录记录
                    BillingApplyAccounts billingApplyAccounts=new BillingApplyAccounts();
                    billingApplyAccounts.setBillId(Long.parseLong(billingReceiveInfo.getBillingId().toString()));
                    billingApplyAccounts.setBillImgsId(Long.parseLong(billingReceiveInfo.getBillingImgsId().toString()));
                    billingApplyAccounts.setMoney(billingReceiveInfo.getReceiveMoney());
                    billingApplyAccounts.setState(1);
                    billingApplyAccounts.setAccountTime(billingApplyUnmatch.getPayTime());
                    billingApplyAccountsMapper.insertSelective(billingApplyAccounts);

                }else if ("re".equals(type)){
                    billingReceiveInfo.setReceiveStatus(1);
                    billingReceiveInfoMapper.updateOne(billingReceiveInfo);
                    if (billingApplyUnmatch!=null){
                        billingApplyUnmatch.setUnmatchMoney(billingApplyUnmatch.getUnmatchMoney()-receiveMoney);
                        billingApplyUnmatchMapper.updateByPrimaryKeySelective(billingApplyUnmatch);
                    }
                }else if ("reject".equals(type)){
                    billingReceiveInfo.setReceiveStatus(3);
                    billingReceiveInfo.setRejectReason(request.getString("rejectReason"));
                    billingReceiveInfoMapper.updateOne(billingReceiveInfo);
                    if (billingApplyUnmatch!=null){
                        billingApplyUnmatch.setUnmatchMoney(billingApplyUnmatch.getUnmatchMoney()+receiveMoney);
                        billingApplyUnmatchMapper.updateByPrimaryKeySelective(billingApplyUnmatch);
                    }
                }else if ("del".equals(type)){
                    billingReceiveInfoMapper.deleteOne(Integer.parseInt(receiveId.toString()));
                } else if ("chexiao".equals(type)){
                    billingReceiveInfoMapper.deleteOne(Integer.parseInt(receiveId.toString()));
                    if (billingReceiveInfo.getBillingImgsId() != null){
                        BillingApplyImgs billingApplyImgs = billingApplyImgsMapper.selectByPrimaryKey(billingReceiveInfo.getBillingImgsId().longValue());
                        billingApplyImgs.setPayState(0);
                        billingApplyImgsMapper.updateByPrimaryKey(billingApplyImgs);

                        List<BillingApplyAccounts> billingApplyAccounts = billingApplyAccountsMapper.selectListByImgsId(billingReceiveInfo.getBillingImgsId().longValue());
                        for (BillingApplyAccounts billingApplyAccount : billingApplyAccounts) {
                            billingApplyAccount.setState(0);
                            billingApplyAccountsMapper.updateByPrimaryKey(billingApplyAccount);
                        }
                    }
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS, 1, null);
            }
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(descript = "退费修改" ,value = "billing-receive-info-change-refund-upd")
    public ApiResponse receiveBillRefundUpd(ApiRequest request) {

        Long refundId = request.getLong("id");
        String type = request.getString("type");
        BillingRefundInfo billingRefundInfo = billingRefundInfoMapper.selectByPrimaryKey(refundId);
        if (billingRefundInfo != null){
            if (StringUtils.isNotBlank(type)){
                if ("pass".equals(type)){
                    billingRefundInfo.setRefundStatus(2);
                    billingRefundInfoMapper.updateByPrimaryKeySelective(billingRefundInfo);
                    BillingApplyUnmatch billingApplyUnmatch = billingApplyUnmatchMapper.selectByPrimaryKey(billingRefundInfo.getBillingMatchId());
                    StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(billingRefundInfo.getRefundUserId());
                    UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(request));
                    //提交至付款管理
                    SurveyPayInfo surveyPayInfo = new SurveyPayInfo();
                    surveyPayInfo.setPayNo(SerialNumberUtil.toBuilNo("TK"));
                    surveyPayInfo.setAppStartDate(new Date());
                    surveyPayInfo.setAppEndDate(new Date());
                    surveyPayInfo.setCreateUserId(userInfo.getUserId());
                    surveyPayInfo.setCreateBy(userInfo.getUserName());
                    surveyPayInfo.setPayState(1);
                    surveyPayInfo.setCreateTime(new Date());
                    surveyPayInfo.setDeleteFlag(0);
                    surveyPayInfo.setUpdateBy(userInfo.getUserName());
                    surveyPayInfo.setUpdateTime(new Date());
                    surveyPayInfo.setPayType(7);//退款
                    surveyPayInfo.setPayKeyId(billingRefundInfo.getId());
                    if (billingApplyUnmatch!=null){
                        surveyPayInfo.setRemark("收款编号："+billingApplyUnmatch.getUnmatchNo()+"\n付款方："+billingRefundInfo.getPayee()+"\n开票产品："+billingApplyUnmatch.getBillingItemsName());
                        surveyPayInfo.setSocialSecurityCompany(billingApplyUnmatch.getReceivingCompanyName());
                        surveyPayInfo.setRealName(billingRefundInfo.getRefundUserName());
                        surveyPayInfo.setUserId(billingRefundInfo.getRefundUserId());
                        surveyPayInfo.setAppPayMoney(billingRefundInfo.getRefundMoney());
                    }
                    if (staffPersonnelInfo!=null){
                        surveyPayInfo.setOrganId(staffPersonnelInfo.getOrganId());
                        surveyPayInfo.setOrgan(staffPersonnelInfo.getOrgan());
                        surveyPayInfo.setDepartmentId(staffPersonnelInfo.getDepartmentId());
                        surveyPayInfo.setDepartment(staffPersonnelInfo.getDepartment());
                        surveyPayInfo.setTeamId(staffPersonnelInfo.getTeamId());
                        surveyPayInfo.setTeam(staffPersonnelInfo.getTeam());
                        surveyPayInfo.setJobPost(staffPersonnelInfo.getJobPost());
                        surveyPayInfo.setJobPostId(staffPersonnelInfo.getJobPostId());
                    }
                    surveyPayInfoMapper.insert(surveyPayInfo);
                }else if ("re".equals(type)){
                    billingRefundInfo.setRefundStatus(1);
                    billingRefundInfoMapper.updateByPrimaryKeySelective(billingRefundInfo);
                    Double refundMoney = billingRefundInfo.getRefundMoney();
                    BillingApplyUnmatch billingApplyUnmatch = billingApplyUnmatchMapper.selectByPrimaryKey(billingRefundInfo.getBillingMatchId());
                    if (billingApplyUnmatch!=null){
                        billingApplyUnmatch.setUnmatchMoney(billingApplyUnmatch.getUnmatchMoney()-refundMoney);
                        billingApplyUnmatchMapper.updateByPrimaryKeySelective(billingApplyUnmatch);
                    }
                }else if ("reject".equals(type)){
                    billingRefundInfo.setRefundStatus(3);
                    billingRefundInfo.setRefundReason(request.getString("rejectReason"));
                    billingRefundInfoMapper.updateByPrimaryKeySelective(billingRefundInfo);
                    Double refundMoney = billingRefundInfo.getRefundMoney();
                    BillingApplyUnmatch billingApplyUnmatch = billingApplyUnmatchMapper.selectByPrimaryKey(billingRefundInfo.getBillingMatchId());
                    if (billingApplyUnmatch!=null){
                        billingApplyUnmatch.setUnmatchMoney(billingApplyUnmatch.getUnmatchMoney()+refundMoney);
                        billingApplyUnmatchMapper.updateByPrimaryKeySelective(billingApplyUnmatch);
                    }
                }else if ("del".equals(type)){
                    billingRefundInfoMapper.deleteByPrimaryKey(refundId);
                }
                return new ApiResponse(ApiMsgEnum.SUCCESS, 1, null);
            }
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    @ApiMethod(descript = "退费添加" ,value = "billing-receive-info-change-refund-add")
    public ApiResponse receiveBillRefundAdd(ApiRequest request) {
       try{
           UserInfo userInfo = userInfoMapper.selectByPrimaryKey(getCurrentUserId(request));
           Long matchId = request.getLong("matchId");
           Double money = Optional.ofNullable(request.getDouble("money")).orElse(0d);
           BillingApplyUnmatch billingApplyUnmatch = billingApplyUnmatchMapper.selectByPrimaryKey(matchId);
           if (billingApplyUnmatch != null){
               if (billingApplyUnmatch.getUnmatchMoney() < money){
                   return new ApiResponse(ApiMsgEnum.FAIL,0,"退费金额大于未认领金额");
               }
               billingApplyUnmatch.setUnmatchMoney(billingApplyUnmatch.getUnmatchMoney()-money);
               billingApplyUnmatchMapper.updateByPrimaryKeySelective(billingApplyUnmatch);
           }
           BillingRefundInfo billingRefundInfo = new BillingRefundInfo();
           billingRefundInfo.setBillingMatchId(matchId);
           billingRefundInfo.setRefundMoney(money);
           billingRefundInfo.setRefundUserId(userInfo.getUserId());
           billingRefundInfo.setRefundUserName(userInfo.getUserName());
           String apply = request.getString("apply");
           if (StringUtils.isNotBlank(apply) && "true".equals(apply)){
               billingRefundInfo.setRefundType(2);
               billingRefundInfo.setRefundStatus(1);
           }else {
               billingRefundInfo.setRefundType(1);
           }
           billingRefundInfo.setCreateTime(new Date());
           billingRefundInfo.setPayee(request.getString("payee"));
           billingRefundInfo.setBankName(request.getString("bankName"));
           billingRefundInfo.setBankBranch(request.getString("bankBranch"));
           billingRefundInfo.setBankCarNo(request.getString("bankCarNo"));
           billingRefundInfoMapper.insert(billingRefundInfo);

           if (StringUtils.isNotBlank(apply) && "false".equals(apply)){
               //提交至付款管理
               SurveyPayInfo surveyPayInfo = new SurveyPayInfo();
               surveyPayInfo.setPayNo(SerialNumberUtil.toBuilNo("TK"));
               surveyPayInfo.setAppStartDate(new Date());
               surveyPayInfo.setAppEndDate(new Date());
               surveyPayInfo.setCreateUserId(userInfo.getUserId());
               surveyPayInfo.setCreateBy(userInfo.getUserName());
               surveyPayInfo.setPayState(1);
               surveyPayInfo.setCreateTime(new Date());
               surveyPayInfo.setDeleteFlag(0);
               surveyPayInfo.setUpdateBy(userInfo.getUserName());
               surveyPayInfo.setUpdateTime(new Date());
               surveyPayInfo.setPayType(7);//退款
               surveyPayInfo.setPayKeyId(billingRefundInfo.getId());
               if (billingApplyUnmatch!=null){
                   surveyPayInfo.setRemark("收款编号："+billingApplyUnmatch.getUnmatchNo()+"\n付款方："+billingRefundInfo.getPayee()+"\n开票产品："+billingApplyUnmatch.getBillingItemsName());
                   surveyPayInfo.setSocialSecurityCompany(billingApplyUnmatch.getReceivingCompanyName());
                   surveyPayInfo.setRealName(billingRefundInfo.getRefundUserName());
                   surveyPayInfo.setUserId(billingRefundInfo.getRefundUserId());
                   surveyPayInfo.setAppPayMoney(billingRefundInfo.getRefundMoney());
               }
               StaffPersonnelInfo staffPersonnelInfo = staffPersonnelInfoMapper.selectStaffPersonelInfoByUserId(billingRefundInfo.getRefundUserId());
               if (staffPersonnelInfo!=null){
                   surveyPayInfo.setOrganId(staffPersonnelInfo.getOrganId());
                   surveyPayInfo.setOrgan(staffPersonnelInfo.getOrgan());
                   surveyPayInfo.setDepartmentId(staffPersonnelInfo.getDepartmentId());
                   surveyPayInfo.setDepartment(staffPersonnelInfo.getDepartment());
                   surveyPayInfo.setTeamId(staffPersonnelInfo.getTeamId());
                   surveyPayInfo.setTeam(staffPersonnelInfo.getTeam());
                   surveyPayInfo.setJobPost(staffPersonnelInfo.getJobPost());
                   surveyPayInfo.setJobPostId(staffPersonnelInfo.getJobPostId());
               }
               surveyPayInfoMapper.insert(surveyPayInfo);
           }

           return new ApiResponse(ApiMsgEnum.SUCCESS, 1, null);
       }catch (Exception e){
           e.printStackTrace();
       }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
}
