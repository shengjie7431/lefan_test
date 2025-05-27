package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendLawCaseInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.LawCaseInfoDto;
import com.lefancrm.apicenter.dto.LawFileDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.SerialNumberUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by lixianfeng on 2018/10/17.
 */
@ApiService(descript = "司法评估API")
@Service
public class BackendLawCaseInfoApiImpl extends BaseServiceImpl implements BackendLawCaseInfoApi{
    @Autowired
    private LawCaseInfoMapper lawCaseInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private LawFileMapper lawFileMapper;
    @Autowired
    private LawReqLatterMapper lawReqLatterMapper;
    @Autowired
    private LawFeeDetailMapper lawFeeDetailMapper;
    @Autowired
    private CommonFileMapper commonFileMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    @Autowired
    private SuningWithholdApplyMapper suningWithholdApplyMapper;
    @Autowired
    private BillingApplyMapper billingApplyMapper;
    @Autowired
    private LawCaseInfoFollowMapper lawCaseInfoFollowMapper;
    @Autowired
    private OrgInfoMapper orgInfoMapper;
    @Autowired
    private CaseFollowInfoMapper caseFollowInfoMapper;

    @Value("${law_end_day}")
    private Integer lawEndDay;

    @ApiMethod(descript = "获取案件列表", value = "backend-law-case-info-list")
    @Override
    public ApiResponse<List<LawCaseInfoDto>> list(ApiRequest apiRequest) {
        this.setBackendPageSize(apiRequest);
        String menuType = apiRequest.getString("menuType");
        Long currentUserId = apiRequest.getLong("operatorId");//当前登录人id
        if ("1".equals(menuType)) {
            //客服中心 司法评估列表

        }else if("5".equals(menuType)){//委托案件列表
            apiRequest.put("stageState",1);//委托阶段
        }else if("10".equals(menuType)){
            apiRequest.put("flowState",3);//待分派评估师
        }else if("15".equals(menuType)){//
            apiRequest.put("flowState",5);//评估待接收
            apiRequest.put("assessId",currentUserId);
        }else if("18".equals(menuType)){
            apiRequest.put("stageState",2);//评估计划中
            apiRequest.put("flowState",6);
            apiRequest.put("assessId",currentUserId);
        }else if("20".equals(menuType)){//
            apiRequest.put("stageState",2);//评估处理
            apiRequest.put("menuType",20);//   flowState  in (8,12)   计划完成报告制作的  ，待结案
            apiRequest.put("assessId",currentUserId);
        }else if("25".equals(menuType)){//评估审核  -- 审核中
            //apiRequest.put("stageState",2); 退案审核中的可能不是 评估阶段 ，去掉此条件
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean isPgzg = isRoleUser(userRoles, 36L); //9 13的
            Boolean isPgjl = isRoleUser(userRoles, 37L); //10的
            apiRequest.put("menuType",25);//审核中  评估报告审核中 结案审核中  flowState in (9,10,13);
            if (isPgzg){
                apiRequest.put("role",1);
            }
            if (isPgjl){
                apiRequest.put("role",2);
            }
            if(isPgzg && isPgjl){
                apiRequest.put("role",3);
            }
        }else if("30".equals(menuType)){
            //查询所有 -- 综合查询
        }else if("35".equals(menuType)){
            apiRequest.put("createBy",currentUserId);//委托清单-登录人所提交
        }else{

        }

        if (currentUserId == 2189L){//便于检查问题。  2189是李贤丰的账号
            apiRequest.remove("assessId");
            apiRequest.remove("createBy");
        }
        int count = lawCaseInfoMapper.findListSize(apiRequest);
        List<LawCaseInfoDto> list = lawCaseInfoMapper.findList(apiRequest);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    @ApiMethod(descript = "获取案件详情", value = "backend-law-case-info-number")
    @Override
    public ApiResponse<LawNumberDto> number(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);
        int count = 0;
        LawNumberDto numberDto = new LawNumberDto();
        apiRequest.clear();
        //apiRequest.put("createBy",currentUserId);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setNumber1(count);
        apiRequest.clear();
        apiRequest.put("assessId",currentUserId);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setNumber2(count);
        apiRequest.clear();
        apiRequest.put("stageState",4);
        //apiRequest.put("assessId",currentUserId);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setNumber3(count);
        apiRequest.clear();
        apiRequest.put("stageState",1);//委托阶段
        apiRequest.put("flowState",2);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setType1(count);
        apiRequest.clear();
        apiRequest.put("flowState",5);
        apiRequest.put("assessId",currentUserId);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setType2(count);
        apiRequest.clear();
        apiRequest.put("stageState",2);//超时案件
        apiRequest.put("assessId",currentUserId);
        apiRequest.put("isUploadReqmoneyLetter",3);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setType3(count);
        apiRequest.clear();
        apiRequest.put("stageState",2);//催收案件
        apiRequest.put("assessId",currentUserId);
        apiRequest.put("flowState",6);
        apiRequest.put("isUploadReqmoneyLetter",2);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setType4(count);
        apiRequest.clear();
        apiRequest.put("stageState",2);//计划未完成
        apiRequest.put("assessId",currentUserId);
        apiRequest.put("condition",1);// isUploadAssessPlan = 0 or isUploadReqmoneyLetter = 0
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setType5(count);
        apiRequest.clear();
        apiRequest.put("stageState",2);//计划未寄送
        apiRequest.put("isUploadAssessPlan",1);
        apiRequest.put("isUploadReqmoneyLetter",1);
        apiRequest.put("isExpress",0);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setType6(count);
        apiRequest.clear();
        apiRequest.put("stageState",2);//报告未寄送
        apiRequest.put("flowState",12);
        apiRequest.put("isSendReport",0);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setType7(count);
        apiRequest.clear();
        //apiRequest.put("stageState",2);//评估阶段
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean isPgzg = isRoleUser(userRoles, 36L); //9 13的
        Boolean isPgjl = isRoleUser(userRoles, 37L); //10的
        apiRequest.put("menuType",25);//审核中  评估报告审核中 结案审核中  flowState in (9,10,13);  退案审核中 retreatState = 1
        if (isPgzg){
            apiRequest.put("role",1);
        }
        if (isPgjl){
            apiRequest.put("role",2);
        }
        if(isPgzg && isPgjl){
            apiRequest.put("role",3);
        }
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setType8(count);
        apiRequest.clear();
        apiRequest.put("flowState",3);//待分派评估师
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setType9(count);

        apiRequest.clear();
        apiRequest.put("flowState",6);//信息未确认
        apiRequest.put("isOkInfo",0);
        apiRequest.put("stageState",2);
        apiRequest.put("assessId",currentUserId);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setTitle1(count);

        apiRequest.clear();
        apiRequest.put("flowState",6);//计划完成
        apiRequest.put("isExpress",1);
        apiRequest.put("stageState",2);
        apiRequest.put("assessId",currentUserId);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setTitle2(count);

        apiRequest.clear();
        apiRequest.put("flowState",8);//待制作报告
        apiRequest.put("stageState",2);
        apiRequest.put("assessId",currentUserId);
        apiRequest.put("isReport",0);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setTitle3(count);

        apiRequest.clear();
        apiRequest.put("flowState",8);//报告信息未确认
        apiRequest.put("stageState",2);
        apiRequest.put("assessId",currentUserId);
        apiRequest.put("isOkInfo2",0);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setTitle4(count);

        apiRequest.clear();
        apiRequest.put("flowState",8);//报告待提交审核
        apiRequest.put("stageState",2);
        apiRequest.put("assessId",currentUserId);
        apiRequest.put("isReport",1);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setTitle10(count);

        apiRequest.clear();
        apiRequest.put("flowState",12);//待结案
        apiRequest.put("stageState",2);
        apiRequest.put("assessId",currentUserId);
        count = lawCaseInfoMapper.findListSize(apiRequest);
        numberDto.setTitle5(count);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,numberDto);
    }

    @ApiMethod(descript = "获取案件详情", value = "backend-law-case-info-info")
    @Override
    public ApiResponse<LawCaseInfoDto> info(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        Long currentUserId = getCurrentUserId(apiRequest);;//当前登录人id
        LawCaseInfoDto dto = lawCaseInfoMapper.selectByPrimaryKey(id);
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        dto.setIsAssess(isRoleUser(userRoles,34L));
        dto.setIsComplex(isRoleUser(userRoles, 35L));
        dto.setIsAssessSuper(isRoleUser(userRoles, 36L));
        dto.setIsAssessManager(isRoleUser(userRoles, 37L));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,dto);
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == roleId){
                return true;
            }
        }
        return false;
    }

    @ApiMethod(descript = "添加案件信息", value = "backend-law-case-info-add")
    @Override
    public ApiResponse<LawCaseInfo> add(ApiRequest apiRequest) {
        Long currentUserId = getCurrentUserId(apiRequest);;//当前登录人id
        UserInfo currentUserInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        LawCaseInfo lawCaseInfo = ConvertToBeanUtil.toBeanFromApiRequest(apiRequest,LawCaseInfo.class);
        lawCaseInfo.setCaseNo(SerialNumberUtil.nextCaseCode("SFPG"));
        lawCaseInfo.setPubCaseNo(SerialNumberUtil.nextCaseCode("LFCX"));
        lawCaseInfo.setOrgId(null);
        lawCaseInfo.setOrgName(null);
        lawCaseInfo.setStageState(1);//委托阶段
        lawCaseInfo.setFlowState(2);//
        lawCaseInfo.setFlowStateName("委托审核中");
        lawCaseInfo.setIsCursupplement(0);
        lawCaseInfo.setIsUploadAssessPlan(0);
        lawCaseInfo.setIsUploadReqmoneyLetter(0);
        lawCaseInfo.setIsSendReport(0);
        lawCaseInfo.setIsArrAssFee(0);
        lawCaseInfo.setIsExpress(0);
        lawCaseInfo.setIsArrRefund(0);
        lawCaseInfo.setIsSue(0);
        lawCaseInfo.setIsBill(0);
        lawCaseInfo.setRetreatState(0);
        lawCaseInfo.setIsOkInfo(0);
        lawCaseInfo.setIsOkInfo2(0);
        lawCaseInfo.setIsReport(0);
        lawCaseInfo.setCreateBy(currentUserId);
        lawCaseInfo.setCreateTime(new Date());
        lawCaseInfo.setUpdateBy(currentUserId);
        lawCaseInfo.setUpdateTime(new Date());
        lawCaseInfo.setCreateByName(currentUserInfo.getUserName());
        //委托时间
        Date nextFollowTime = DateUtils.parseDate(apiRequest.getString("entrustTime"), "yyyy-MM-dd HH:mm:ss");
        lawCaseInfo.setEntrustTime(nextFollowTime);
        //根据标的市匹配机构
        if (lawCaseInfo.getTargetCityId() != null){
            OrgInfo orgInfo = orgInfoMapper.selectOrgInfoByCityId(new Long(lawCaseInfo.getTargetCityId()));
            if (orgInfo != null){
                if ("1".equals(orgInfo.getId().toString())){
                    lawCaseInfo.setOrgId(26L);
                    lawCaseInfo.setOrgName("上海机构");
                }else{
                    lawCaseInfo.setOrgId(orgInfo.getId());
                    lawCaseInfo.setOrgName(orgInfo.getOrgName());
                }
            }
        }
        int ret = lawCaseInfoMapper.insert(lawCaseInfo);
        //保存委托材料
        String paths = apiRequest.getString("uploadPaths");
        saveFile(paths,lawCaseInfo,1);
        if (ret < 0){
            return new ApiResponse<>(ApiMsgEnum.FAIL);
        }

        CaseFollowInfo caseFollowInfo = new CaseFollowInfo();
        caseFollowInfo.setType(5);
        caseFollowInfo.setCaseId(lawCaseInfo.getId());
        caseFollowInfo.setCaseState(null);
        caseFollowInfo.setCaseStateStr("委托信息发起,委托审核中");
        caseFollowInfo.setFollowDesc(null);
        caseFollowInfo.setFollowBy(currentUserInfo.getUserName());
        caseFollowInfo.setFollowById(currentUserId);
        caseFollowInfo.setFollowTime(new Date());
        caseFollowInfo.setCaseNo(lawCaseInfo.getCaseNo());
        caseFollowInfoMapper.insertSelective(caseFollowInfo);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,lawCaseInfo);
    }

    @ApiMethod(descript = "处理案件更新案件状态等", value = "backend-law-case-info-opr")
    @Override
    public ApiResponse<LawCaseInfo> operate(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        Long currentUserId = getCurrentUserId(apiRequest);;//当前登录人id
        UserInfo currentUserInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        String btnCode = apiRequest.getString("btnCode");
        String reason = apiRequest.getString("reason");
        boolean isRemarkNull = true;//是否将审核意见置空 默认是
        LawCaseInfoDto lawCaseInfo = lawCaseInfoMapper.selectByPrimaryKey(id);
        String stateStr = null,desc = null;
        //客服补充信息
        if("1100".equals(btnCode) || "1000".equals(btnCode)){
            lawCaseInfo = ConvertToBeanUtil.toBeanFromApiRequestSuper(apiRequest, lawCaseInfo);
            //委托时间
            Date nextFollowTime = DateUtils.parseDate(apiRequest.getString("entrustTime"), "yyyy-MM-dd HH:mm:ss");
            lawCaseInfo.setEntrustTime(nextFollowTime);
            if (lawCaseInfo.getTargetCityId() != null){
                OrgInfo orgInfo = orgInfoMapper.selectOrgInfoByCityId(new Long(lawCaseInfo.getTargetCityId()));
                if (orgInfo != null){
                    if ("1".equals(orgInfo.getId().toString())){
                        lawCaseInfo.setOrgId(26L);
                        lawCaseInfo.setOrgName("上海机构");
                    }else{
                        lawCaseInfo.setOrgId(orgInfo.getId());
                        lawCaseInfo.setOrgName(orgInfo.getOrgName());
                    }
                }
            }
            if ("1100".equals(btnCode)){
                lawCaseInfo.setIsCursupplement(2);//补充完成
                stateStr = "信息补充完成";desc="";
            }else if ("1000".equals(btnCode)){
                lawCaseInfo.setFlowState(2);
                lawCaseInfo.setFlowStateName("委托审核中");
                stateStr = "委托申请已提交，委托审核中";desc="同意";
            }
        }
        //案件审核通过 1200 不通过 1201
        else if ("1200".equals(btnCode)){
            lawCaseInfo.setFlowState(3);//待分派
            lawCaseInfo.setFlowStateName("案件审核通过，待分派评估师");
            lawCaseInfo.setIsCursupplement(2);//默认，补充完成
            lawCaseInfo.setOneCheckOpinion(1);
            stateStr = lawCaseInfo.getFlowStateName();desc="同意";
        }else if ("1201".equals(btnCode)){
            lawCaseInfo.setFlowState(1);
            lawCaseInfo.setFlowStateName("撤回委托");
            lawCaseInfo.setOneCheckOpinion(2);
            isRemarkNull = false;
            stateStr = "撤回委托，重新提交";desc = reason;
        }
        //发送客服补充信息
        else if("1203".equals(btnCode)){
            lawCaseInfo.setIsCursupplement(1);
            stateStr = "发送客服补充信息";desc = null;
        }
        //分派评估师
        else if ("1300".equals(btnCode)){
            Long assessId = apiRequest.getLong("assessId");
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(assessId);
            lawCaseInfo.setAssessId(assessId);
            lawCaseInfo.setAssessName(userInfo.getUserName());
            lawCaseInfo.setAssessTel(userInfo.getUserTel());
            lawCaseInfo.setFlowState(5);//评估师待接收
            lawCaseInfo.setFlowStateName("评估师待接收");
            stateStr = "评估师已分派，待接收";desc = null;
        }
        //评估师接收 1400  评估师拒绝 1401
        else if ("1400".equals(btnCode)){
            lawCaseInfo.setAcceptTime(new Date());//评估员接收时间
            lawCaseInfo.setFlowState(6);
            lawCaseInfo.setFlowStateName("评估计划中");
            lawCaseInfo.setStageState(2);//评估阶段
            stateStr = "评估师已接收，评估计划中"; desc = null;
        }else if ("1401".equals(btnCode)){
            lawCaseInfo.setFlowState(3);//评估员已拒绝，待分派评估师
            lawCaseInfo.setFlowStateName("评估师已拒绝，重新分派评估师");
            isRemarkNull = false;
            stateStr = lawCaseInfo.getFlowStateName(); desc = reason;
        }
        else if ("1500".equals(btnCode)){
            String paths = apiRequest.getString("uploadPaths");
            saveFile(paths, lawCaseInfo, 2);
            lawCaseInfo.setIsUploadAssessPlan(1);
            stateStr = "评估方案已上传"; desc = null;
        }
        else if ("1501".equals(btnCode)){
            String paths = apiRequest.getString("uploadPaths");
            saveFile(paths, lawCaseInfo, 3);
            lawCaseInfo.setIsUploadReqmoneyLetter(1);
            stateStr = "请款函已上传"; desc = null;
        }
        else if ("1502".equals(btnCode)){
            //验证计划信息是否确认
            if (lawCaseInfo.getIsOkInfo() == 0){
                return new ApiResponse<>(ApiMsgEnum.LAW_CASE_INFO_ISOK);
            }

            lawCaseInfo.setIsArrAssFee(0);//未到账
            lawCaseInfo.setIsExpress(1);
            lawCaseInfo.setExpressTime(new Date());
            //保存请款函表
            LawReqLatter lawReqLatter =  new LawReqLatter();
            lawReqLatter.setCaseId(lawCaseInfo.getId());
            lawReqLatter.setCaseNo(lawCaseInfo.getCaseNo());
            lawReqLatter.setMoney(lawCaseInfo.getFinalAssessFee());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE,lawEndDay);
            lawReqLatter.setEndPayTime(cal.getTime());
            lawReqLatter.setPayState(0);
            lawReqLatter.setCreateTime(new Date());
            lawReqLatterMapper.insert(lawReqLatter);
            String orgName = lawCaseInfo.getOrgName() == null ? "" : lawCaseInfo.getOrgName();
            String caseTitle = orgName.concat(lawCaseInfo.getEntrustUserName()).concat("司法评估");
            //预付费生成到账不生成开票
            if (lawCaseInfo.getPayType() == 1){
                lawCaseInfo.setIsArrAssFee(0);//未到账
                SuningWithholdApply apply = new SuningWithholdApply();
                apply.setCaseId(null);
                apply.setCaseNo(lawCaseInfo.getCaseNo());
                apply.setCaseTitle(caseTitle);
                apply.setWithholdMoney(lawCaseInfo.getFinalAssessFee());
                apply.setWithholdTime(new Date());
                apply.setWithholdType(3);
                apply.setWithholdState(2);
                apply.setAppUserId(currentUserId);
                apply.setAccountState(0);
                apply.setApplyType(5);
                suningWithholdApplyMapper.insert(apply);
            }
            //后付费生成开票 不生成到账
            if (lawCaseInfo.getPayType() == 2){
                lawCaseInfo.setIsBill(1);//申请中
                BillingApply billingApply = new BillingApply();
                billingApply.setCaseId(null);
                billingApply.setCaseNo(lawCaseInfo.getCaseNo());
                billingApply.setCaseTitle(caseTitle);
                billingApply.setBillingMoney(lawCaseInfo.getFinalAssessFee());
                billingApply.setBillingState(2);
                billingApply.setBillingEnum(11);//公估公司-司法评估-公估费
                billingApply.setBillingItem(11);
                billingApply.setBillingType(1);
                billingApply.setBusinessType(2);
                billingApply.setBillingSource(4);//开票来源（4、司法评估开票）

                OrgInfo orgInfo = isTopOrg(lawCaseInfo.getOrgId());
                billingApply.setOrgId(orgInfo.getId());
                billingApply.setOrgName(orgInfo.getOrgName());

                billingApply.setConfirmAccountMoney(0D);
                billingApply.setConfirmAccountTime(null);
                billingApply.setConfirmAccountState(1);
                billingApply.setCreateTime(new Date());
                billingApply.setCreateById(lawCaseInfo.getAssessId());
                billingApply.setCreateBy(lawCaseInfo.getAssessName());
                billingApply.setReportNo(lawCaseInfo.getPubCaseNo());
                billingApply.setWoundedName(lawCaseInfo.getEntrustUserName());
                billingApply.setMeritName(lawCaseInfo.getAssessName());
                billingApply.setRecipientsName(lawCaseInfo.getToName());
                billingApply.setRecipientsPhone(lawCaseInfo.getToTel());
                billingApply.setAddress(lawCaseInfo.getToAddress());
                billingApply.setCompanyName(lawCaseInfo.getToOrg());
                billingApplyMapper.insert(billingApply);
            }

            stateStr = "评估方案/请款函已快递"; desc = null;
        }else if ("1503".equals(btnCode)){
            String collectionRemarks = apiRequest.getString("collectionRemarks");
            lawCaseInfo.setCollectionRemarks(collectionRemarks);
            lawCaseInfo.setIsUploadReqmoneyLetter(2);
            isRemarkNull = false;
            stateStr = "请款函催收中"; desc = collectionRemarks;
        }else if("1515".equals(btnCode)){
            Double checkAssessFee = apiRequest.getDouble("checkAssessFee");
            Double travelFee = apiRequest.getDouble("travelFee");
            Double finalAssessFee = apiRequest.getDouble("finalAssessFee");
            Integer payType = apiRequest.getInt("payType");
            if (payType == 2){
                String toName = apiRequest.getString("toName");
                String toTel = apiRequest.getString("toTel");
                String toOrg = apiRequest.getString("toOrg");
                String toAddress = apiRequest.getString("toAddress");
                String toProvince = apiRequest.getString("toProvince");
                String toCity = apiRequest.getString("toCity");
                String toDistrict = apiRequest.getString("toDistrict");
                Integer toProvinceId = apiRequest.getInt("toProvinceId");
                Integer toCityId = apiRequest.getInt("toCityId");
                Integer toDistrictId = apiRequest.getInt("toDistrictId");

                lawCaseInfo.setToName(toName);
                lawCaseInfo.setToTel(toTel);
                lawCaseInfo.setToOrg(toOrg);
                lawCaseInfo.setToAddress(toAddress);
                lawCaseInfo.setToProvince(toProvince);
                lawCaseInfo.setToProvinceId(toProvinceId);
                lawCaseInfo.setToCity(toCity);
                lawCaseInfo.setToCityId(toCityId);
                lawCaseInfo.setToDistrict(toDistrict);
                lawCaseInfo.setToDistrictId(toDistrictId);
            }else{
                lawCaseInfo.setToName(null);
                lawCaseInfo.setToTel(null);
                lawCaseInfo.setToOrg(null);
                lawCaseInfo.setToAddress(null);
            }
            lawCaseInfo.setPayType(payType);
            lawCaseInfo.setCheckAssessFee(checkAssessFee);
            lawCaseInfo.setTravelFee(travelFee);
            lawCaseInfo.setFinalAssessFee(finalAssessFee);
            lawCaseInfo.setIsOkInfo(1);
            isRemarkNull = false;
            stateStr = "评估费信息已确认"; desc = null;
        }else if ("1505".equals(btnCode)){//评估完成
            //评估信息是否确认
            if (lawCaseInfo.getIsOkInfo() == 0){
                return new ApiResponse<>(ApiMsgEnum.LAW_CASE_INFO_ISOK);
            }
            //如果是预付费验证到账状态
            if (lawCaseInfo.getIsExpress() == 0){
                return new ApiResponse<>(ApiMsgEnum.LAW_CASE_EXPRESS_ISOK);
            }
            if (lawCaseInfo.getPayType() ==1 && lawCaseInfo.getIsArrAssFee() == 0){
                return new ApiResponse<>(ApiMsgEnum.LAW_CASE_ARR_ISOK);
            }
            //如果是后付费验证开票状态（暂时先不验证）

            lawCaseInfo.setFlowState(8);
            lawCaseInfo.setFlowStateName("计划完成，评估报告制作中");
            stateStr = lawCaseInfo.getFlowStateName(); desc = null;
        }else if ("1504".equals(btnCode)){
            String paths = apiRequest.getString("uploadPaths");
            saveFile(paths,lawCaseInfo,4);
            lawCaseInfo.setIsReport(1);
            isRemarkNull = false;
            stateStr = "评估报告已上传"; desc = null;
        }else if("1555".equals(btnCode)){
            if (lawCaseInfo.getIsOkInfo2() == 0){
                return new ApiResponse<>(ApiMsgEnum.LAW_CASE_INFO_ISOK);
            }
            lawCaseInfo.setFlowState(9);
            lawCaseInfo.setFlowStateName("报告已提交，一审审核中");
            stateStr = lawCaseInfo.getFlowStateName(); desc = null;
        }else if("1556".equals(btnCode)){
            Double finalAssessAmount = apiRequest.getDouble("finalAssessAmount");
            String assessRemark = apiRequest.getString("assessRemark");
            Integer appraiseType = apiRequest.getInt("appraiseType");
            String refoundRemark = apiRequest.getString("refoundRemark");
            Double amount = apiRequest.getDouble("amount");

            lawCaseInfo.setFinalAssessAmount(finalAssessAmount);
            lawCaseInfo.setAssessRemark(assessRemark);
            lawCaseInfo.setRefoundRemark(refoundRemark);
            lawCaseInfo.setRefundType(appraiseType);
            lawCaseInfo.setRefundFee(amount);
            lawCaseInfo.setIsOkInfo2(1);
            if (lawCaseInfo.getPayType() == 1){
                String toName = apiRequest.getString("toName");
                String toTel = apiRequest.getString("toTel");
                String toOrg = apiRequest.getString("toOrg");
                String toAddress = apiRequest.getString("toAddress");
                String toProvince = apiRequest.getString("toProvince");
                String toCity = apiRequest.getString("toCity");
                String toDistrict = apiRequest.getString("toDistrict");
                Integer toProvinceId = apiRequest.getInt("toProvinceId");
                Integer toCityId = apiRequest.getInt("toCityId");
                Integer toDistrictId = apiRequest.getInt("toDistrictId");

                lawCaseInfo.setToName(toName);
                lawCaseInfo.setToTel(toTel);
                lawCaseInfo.setToOrg(toOrg);
                lawCaseInfo.setToAddress(toAddress);
                lawCaseInfo.setToProvince(toProvince);
                lawCaseInfo.setToProvinceId(toProvinceId);
                lawCaseInfo.setToCity(toCity);
                lawCaseInfo.setToCityId(toCityId);
                lawCaseInfo.setToDistrict(toDistrict);
                lawCaseInfo.setToDistrictId(toDistrictId);
            }
            stateStr = "信息已确认"; desc = null;

        }else if ("1506".equals(btnCode) || "1508".equals(btnCode) || "1800".equals(btnCode)){
            Double amount = apiRequest.getDouble("amount");
            Integer appraiseType = apiRequest.getInt("appraiseType") == null ? -1 : apiRequest.getInt("appraiseType");
            String refoundRemark = apiRequest.getString("refoundRemark");
            if (appraiseType == 2){
                SuningWithholdApply apply = new SuningWithholdApply();
                apply.setCaseId(null);
                apply.setCaseNo(lawCaseInfo.getCaseNo());
                String orgName = lawCaseInfo.getOrgName() == null ? "" : lawCaseInfo.getOrgName();
                apply.setCaseTitle(orgName.concat(lawCaseInfo.getEntrustUserName()).concat("司法评估"));
                apply.setWithholdMoney(amount);
                apply.setWithholdTime(new Date());
                apply.setWithholdType(3);
                apply.setWithholdState(2);
                apply.setAppUserId(currentUserId);
                apply.setAccountState(0);
                apply.setApplyType(6);
                suningWithholdApplyMapper.insert(apply);
            }else if (appraiseType == 3){
                LawFeeDetail lawFeeDetail = new LawFeeDetail();
                lawFeeDetail.setCaseId(lawCaseInfo.getId());
                lawFeeDetail.setCaseNo(lawCaseInfo.getCaseNo());
                lawFeeDetail.setMoney(amount);
                lawFeeDetail.setRetreatTime(new Date());
                lawFeeDetail.setArrTime(null);
                lawFeeDetail.setType("1506".equals(btnCode) ? 1 : 3);
                lawFeeDetail.setCreateTime(new Date());
                lawFeeDetail.setState(1);//退费为确认
                lawFeeDetailMapper.insert(lawFeeDetail);
            }
            if("1800".equals(btnCode)){
                lawCaseInfo.setRetreatState(1);
                stateStr = "退案已发起"; desc = reason;
            }
            lawCaseInfo.setRefundType(appraiseType);
            lawCaseInfo.setRefoundRemark(refoundRemark);
            if (!"1800".equals(btnCode)){
                lawCaseInfo.setFlowState(12);
                lawCaseInfo.setFlowStateName("报告审核通过，待结案");
                stateStr = lawCaseInfo.getFlowStateName(); desc = null;
            }

        }else if ("1507".equals(btnCode)){
            //正常案件
            if (lawCaseInfo.getIsSendReport() == 0){
                return new ApiResponse<>(ApiMsgEnum.LAW_CASE_REPORT_ISOK);
            }
            if (lawCaseInfo.getIsBill() != 2) {
                if (lawCaseInfo.getRefundType() == 1){
                    return new ApiResponse<>(ApiMsgEnum.LAW_CASE_BILL_ISOK);
                }
                if (lawCaseInfo.getRefundType() == 2){
                    return new ApiResponse<>(ApiMsgEnum.LAW_CASE_BILL2_ISOK);
                }
            }
            lawCaseInfo.setFlowState(13);
            lawCaseInfo.setFlowStateName("结案审核中");
            stateStr = "结案已发起，结案审核中"; desc = null;
        }else if("1520".equals(btnCode)){
            Map<String,Object> map = new HashMap<>();
            map.put("caseNo",lawCaseInfo.getCaseNo());
            BillingApply apply = billingApplyMapper.selectByInfo(map);
            if(apply!=null){
                apply.setBillingState(2);
                billingApplyMapper.updateByPrimaryKeySelective(apply);
            }else {
                BillingApply billingApply = new BillingApply();
                billingApply.setCaseId(null);
                billingApply.setCaseNo(lawCaseInfo.getCaseNo());
                String orgName = lawCaseInfo.getOrgName() == null ? "" : lawCaseInfo.getOrgName();
                billingApply.setCaseTitle(orgName.concat(lawCaseInfo.getEntrustUserName()).concat("司法评估"));
                Double billMoney = 0D;
                if (lawCaseInfo.getRefundType() == 2) {
                    billMoney = lawCaseInfo.getFinalAssessFee() + lawCaseInfo.getRefundFee();
                } else if (lawCaseInfo.getRefundType() == 3) {
                    billMoney = lawCaseInfo.getFinalAssessFee() - lawCaseInfo.getRefundFee();
                } else {
                    billMoney = lawCaseInfo.getFinalAssessFee();
                }
                billingApply.setBillingMoney(billMoney);
                billingApply.setBillingState(2);
                billingApply.setBillingEnum(11);//公估公司-司法评估-公估费
                billingApply.setBillingItem(11);
                billingApply.setBillingType(1);
                billingApply.setBusinessType(2);
                billingApply.setBillingSource(4);//开票来源（4、司法评估开票）

                OrgInfo orgInfo = isTopOrg(lawCaseInfo.getOrgId());
                billingApply.setOrgId(orgInfo.getId());
                billingApply.setOrgName(orgInfo.getOrgName());

                billingApply.setConfirmAccountMoney(0D);
                billingApply.setConfirmAccountTime(null);
                billingApply.setConfirmAccountState(1);
                billingApply.setCreateTime(new Date());
                billingApply.setCreateById(lawCaseInfo.getAssessId());
                billingApply.setCreateBy(lawCaseInfo.getAssessName());
                billingApply.setReportNo(lawCaseInfo.getPubCaseNo());
                billingApply.setWoundedName(lawCaseInfo.getEntrustUserName());
                billingApply.setMeritName(lawCaseInfo.getAssessName());
                billingApply.setRecipientsName(lawCaseInfo.getToName());
                billingApply.setRecipientsPhone(lawCaseInfo.getToTel());
                billingApply.setAddress(lawCaseInfo.getToAddress());
                billingApply.setCompanyName(lawCaseInfo.getToOrg());
                billingApplyMapper.insert(billingApply);
            }
            lawCaseInfo.setIsBill(1);//申请中
            isRemarkNull = false;

            stateStr = "已发起开票申请"; desc = null;
        }
        else if ("1600".equals(btnCode)){
            lawCaseInfo.setFlowState(10);
            lawCaseInfo.setFlowStateName("一审审核通过，二审待审核");
            stateStr = lawCaseInfo.getFlowStateName(); desc = null;
        }else if ("1601".equals(btnCode)){
            lawCaseInfo.setFlowState(8);
            lawCaseInfo.setFlowStateName("一审审核不通过，重新制作报告");
            isRemarkNull = false;
            stateStr = lawCaseInfo.getFlowStateName(); desc = reason;
        }else if ("1602".equals(btnCode)){
            lawCaseInfo.setFlowState(12);
            lawCaseInfo.setFlowStateName("报告审核通过,待结案");
            //生成补费退费到账信息
            if (lawCaseInfo.getRefundType() == 2){
                SuningWithholdApply apply = new SuningWithholdApply();
                apply.setCaseId(null);
                apply.setCaseNo(lawCaseInfo.getCaseNo());
                String orgName = lawCaseInfo.getOrgName() == null ? "" : lawCaseInfo.getOrgName();
                apply.setCaseTitle(orgName.concat(lawCaseInfo.getEntrustUserName()).concat("司法评估"));
                apply.setWithholdMoney(lawCaseInfo.getRefundFee());
                apply.setWithholdTime(new Date());
                apply.setWithholdType(3);
                apply.setWithholdState(2);
                apply.setAppUserId(currentUserId);
                apply.setAccountState(0);
                apply.setApplyType(6);
                suningWithholdApplyMapper.insert(apply);
            }else if (lawCaseInfo.getRefundType() == 3){
                LawFeeDetail lawFeeDetail = new LawFeeDetail();
                lawFeeDetail.setCaseId(lawCaseInfo.getId());
                lawFeeDetail.setCaseNo(lawCaseInfo.getCaseNo());
                lawFeeDetail.setMoney(lawCaseInfo.getRefundFee());
                lawFeeDetail.setRetreatTime(new Date());
                lawFeeDetail.setArrTime(null);
                lawFeeDetail.setType(1);
                lawFeeDetail.setCreateTime(new Date());
                lawFeeDetail.setState(1);//退费未确认
                lawFeeDetailMapper.insert(lawFeeDetail);
            }
            stateStr = lawCaseInfo.getFlowStateName(); desc = null;
        }else if ("1603".equals(btnCode)){
            lawCaseInfo.setFlowState(8);
            lawCaseInfo.setFlowStateName("二审审核不通过，重新制作报告");
            isRemarkNull = false;
            stateStr = lawCaseInfo.getFlowStateName(); desc = reason;
        }else if ("1604".equals(btnCode)){
            lawCaseInfo.setStageState(3);
            lawCaseInfo.setFlowState(14);
            lawCaseInfo.setFlowStateName("已结案");
            if (lawCaseInfo.getPayType() == 2){
                lawCaseInfo.setIsArrAssFee(0);
                SuningWithholdApply apply = new SuningWithholdApply();
                apply.setCaseId(null);
                apply.setCaseNo(lawCaseInfo.getCaseNo());
                String orgName = lawCaseInfo.getOrgName() == null ? "" : lawCaseInfo.getOrgName();
                apply.setCaseTitle(orgName.concat(lawCaseInfo.getEntrustUserName()).concat("司法评估"));
                apply.setWithholdMoney(lawCaseInfo.getFinalAssessFee());
                apply.setWithholdTime(new Date());
                apply.setWithholdType(3);
                apply.setWithholdState(2);
                apply.setAppUserId(currentUserId);
                apply.setAccountState(0);
                apply.setApplyType(5);
                suningWithholdApplyMapper.insert(apply);
            }
            stateStr = lawCaseInfo.getFlowStateName(); desc = null;
        }else if ("1605".equals(btnCode)){
            lawCaseInfo.setFlowState(12);
            lawCaseInfo.setFlowStateName("结案审核不通过，重新结案");
            isRemarkNull = false;
            stateStr = lawCaseInfo.getFlowStateName(); desc = reason;
        }else if ("1700".equals(btnCode)){
            lawCaseInfo.setIsSue(1);
            lawCaseInfo.setIsSendReport(1);
            lawCaseInfo.setSendReportTime(new Date());
            isRemarkNull = false;
            stateStr = "报告已签发"; desc = null;
        }
//        else if ("1800".equals(btnCode)){
//            lawCaseInfo.setRetreatState(1);
//        }
        else if ("1801".equals(btnCode)){
            lawCaseInfo.setRetreatTime(new Date());
            lawCaseInfo.setRetreatState(2);
            lawCaseInfo.setStageState(4);
            stateStr = "退案审核通过，已退案"; desc = null;
        }else if ("1802".equals(btnCode)){
            lawCaseInfo.setRetreatState(3);
            isRemarkNull = false;
            stateStr = "退案审核不通过"; desc = reason;
        }else if ("1900".equals(btnCode)){
            Long orgId = apiRequest.getLong("orgId");
            OrgInfo orgInfo = orgInfoMapper.selectByPrimaryKey(orgId);
            lawCaseInfo.setOrgId(orgId);
            lawCaseInfo.setOrgName(orgInfo.getOrgName());
            isRemarkNull = false;
            stateStr = "已分配机构"; desc = null;
        }else if ("1499".equals(btnCode)){//委托材料上传
            String paths = apiRequest.getString("uploadPaths");
            saveFile(paths,lawCaseInfo,1);
            isRemarkNull = false;
            stateStr = "委托材料已上传"; desc = null;
        }
        else{
            return new ApiResponse<>(ApiMsgEnum.FAIL);
        }

        //同意的情况把审核意见置空 不同意的时候添加审核意见
        if (isRemarkNull){
            lawCaseInfo.setOneCheckOpinionRemark(null);
        }else{
            lawCaseInfo.setOneCheckOpinionRemark(reason);
        }
        lawCaseInfo.setUpdateBy(currentUserInfo.getUserId());
        lawCaseInfo.setUpdateTime(new Date());

        CaseFollowInfo caseFollowInfo = new CaseFollowInfo();
        caseFollowInfo.setType(5);
        caseFollowInfo.setCaseId(lawCaseInfo.getId());
        caseFollowInfo.setCaseState(Integer.parseInt(btnCode));
        caseFollowInfo.setCaseStateStr(stateStr);
        caseFollowInfo.setFollowDesc(desc);
        caseFollowInfo.setFollowBy(currentUserInfo.getUserName());
        caseFollowInfo.setFollowById(currentUserId);
        caseFollowInfo.setFollowTime(new Date());
        caseFollowInfo.setCaseNo(lawCaseInfo.getCaseNo());
        caseFollowInfoMapper.insertSelective(caseFollowInfo);

        int ret = lawCaseInfoMapper.updateByPrimaryKey(lawCaseInfo);
        if (ret < 0){
            return new ApiResponse<>(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,lawCaseInfo);
    }

    /**
     * 获取顶级机构
     */
    private OrgInfo isTopOrg(Long orgId) {
        OrgInfo orgInfo =  orgInfoMapper.selectByPrimaryKey(orgId);
        if(orgInfo.getOrgParentid() == -1){
            //默认为总部
            orgInfo = orgInfoMapper.selectByPrimaryKey(33L);
            return orgInfo;
        }
        if(orgInfo.getOrgParentid() == 1){
            return orgInfo;
        }else{
            return isTopOrg(orgInfo.getOrgParentid());
        }
    }


    /**
     * 根据案件状态 获取 状态名称
     * @param state
     * @return
     */
    private String getFlowStateNameByState(Integer state){
        Map<Integer,String> map = new HashMap<>();

        map.put(2,"撤回委托");
        map.put(7,"委托审核通过，待分派评估师");
        map.put(8,"评估师待接收");
        map.put(9,"评估中");
        return map.get(state);
    }

    /**
     * 保存附件
     * @param paths
     * @param lawCaseInfo
     * @param type 1委托材料  2评估计划  3请款函  4评估报告
     */
    private boolean saveFile(String paths,LawCaseInfo lawCaseInfo,int type){
        if (!StringUtils.isEmpty(paths)){
            String [] urls =paths.split(",");
            for (String url : urls) {
                //保存“文件资料表”
                CommonFile commonFile = new CommonFile();
                commonFile.setFilePath(url);
                int firstName = url.lastIndexOf("/") + 1 ;
                int lastName = url.lastIndexOf(".");
                String name = url.substring(firstName,lastName);
                commonFile.setFileName(name);
                commonFile.setCreateTime(new Date());
                commonFileMapper.insertSelective(commonFile);

                LawFile lawFile = new LawFile();
                lawFile.setCaseId(lawCaseInfo.getId());
                lawFile.setCaseNo(lawCaseInfo.getCaseNo());
                lawFile.setFileId(commonFile.getId());
                lawFile.setFileType(type);
                lawFileMapper.insert(lawFile);
            }
        }
        return true;
    }


    /**
     * 保存案件跟踪信息
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "保存案件跟踪信息", value = "backend-law-case-info-follow-save")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse followSave(ApiRequest apiReq) {

        String followDesc = apiReq.getString("followDesc");
        Date nextFollowTime = DateUtils.parseDate(apiReq.getString("nextFollowTime"), "yyyy-MM-dd HH:mm:ss");
        //当前登录人
        Long operatorId = apiReq.getLong("operatorId");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(operatorId);
        LawCaseInfoDto lawCaseInfo = lawCaseInfoMapper.selectByPrimaryKey(apiReq.getLong("caseId"));

        LawCaseInfoFollow follow = new LawCaseInfoFollow();
        //跟踪说明
        follow.setFollowDesc(followDesc);
        //下次跟踪时间
        follow.setNextFollowTime(nextFollowTime);

        follow.setCaseId(apiReq.getLong("caseId"));
        follow.setCaseNo(lawCaseInfo.getCaseNo());
        follow.setFollowBy(userInfo.getUserName());
        follow.setFollowById(userInfo.getUserId());
        follow.setFollowTime(new Date());

        int result = lawCaseInfoFollowMapper.insertSelective(follow);
        if(result > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }

        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    /**
     * 根据‘案件编号’查询案件跟踪信息
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据‘案件编号’查询案件跟踪信息" ,value = "backend-law-case-info-follow-by-caseid")
    @Override
    public ApiResponse<List<LawCaseInfoFollow>> searchFollowByCaseId(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        List<LawCaseInfoFollow> list = lawCaseInfoFollowMapper.searchFollowByCaseId(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,null,list);
    }


    /**
     * 查看资料
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "查看资料",value = "backend-law-file")
    @Override
    public ApiResponse selectLawFile(ApiRequest apiReq) {
        String id = apiReq.getString("id");
        String fileType = apiReq.getString("fileType");
        LawCaseInfo caseInfo= lawCaseInfoMapper.selectByPrimaryKey(Long.valueOf(id));

        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("caseId",caseInfo.getId());
        paramMap.put("fileType",fileType);
        List<LawFileDto> lawFiles = lawFileMapper.selectByCaseId(paramMap);
        for (LawFileDto lawFile : lawFiles){
            CommonFile commonFile = commonFileMapper.selectByPrimaryKey(lawFile.getFileId());
            lawFile.setCommonFile(commonFile);

            //如果材料是pdf文件，返回1
            int lastNamePdf = commonFile.getFilePath().lastIndexOf(".") + 1;
            String urlName =commonFile.getFilePath().substring(lastNamePdf);
            if("pdf".equals(urlName)){
                lawFile.setDocumentType(1);
            }else if("docx".equals(urlName) || "doc".equals(urlName)){
                lawFile.setDocumentType(2);
            }else{
                lawFile.setDocumentType(3);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,lawFiles == null ?0 :lawFiles.size(),lawFiles);
    }

    @ApiMethod(descript = "查看进度",value = "backend-law-progress")
    @Override
    public ApiResponse selectLawProgress(ApiRequest apiRequest) {
        List<CaseFollowInfo> list = caseFollowInfoMapper.queryCaseFollowByCaseId(apiRequest);
        return new ApiResponse(ApiMsgEnum.SUCCESS,list == null ? 0 : list.size() , list);
    }

    @ApiMethod(descript = "根据类型查看资料",value = "backend-law-file-address")
    @Override
    public ApiResponse selectFilesAddress(ApiRequest apiReq) {
        List<HashMap<String,Object>> list = lawFileMapper.selectFilesAddress(apiReq);
        for (int i = 0; i < list.size(); i++) {
            //如果材料是pdf文件，返回1
            String filePath = list.get(i).get("filePath").toString();
            //如果材料是pdf文件，返回1
            int lastNamePdf = filePath.lastIndexOf(".") + 1;
            String urlName = filePath.substring(lastNamePdf);
            if("pdf".equals(urlName)){
                list.get(i).put("documentType",1);
            }else if("docx".equals(urlName) || "doc".equals(urlName)){
                list.get(i).put("documentType",2);
            }else{
                list.get(i).put("documentType",3);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,list==null?0:list.size(),list);
    }
}
