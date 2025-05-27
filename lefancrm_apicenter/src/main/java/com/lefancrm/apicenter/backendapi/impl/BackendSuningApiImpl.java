package com.lefancrm.apicenter.backendapi.impl;

import cn.jpush.api.report.UsersResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lefancrm.apicenter.backendapi.BackendSuningApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.apicenter.util.pinganfu.StringUtil;
import com.lefancrm.apicenter.util.suning.util.CryptoUtil;
import com.lefancrm.apicenter.util.suning.util.Digest;
import com.lefancrm.apicenter.util.suning.util.HttpClientUtil;
import com.lefancrm.apicenter.util.suning.util.JSONUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import com.lefancrm.base.utils.RandomIDUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by lixianfeng on 2018/3/22.
 */

@ApiService(descript = "苏宁代扣API")
@Service
public class BackendSuningApiImpl extends BaseServiceImpl implements BackendSuningApi {

    @Value("${suning_pay_url}")
    private String url;
    @Value("${suning_pay_yifubao_file}")
    private String filePath;

    @Value("${suning_merchant_no}")
    private String merchantNo;
    @Value("${suning_public_key_index}")
    private String publicKeyIndex;
    @Value("${suning_version}")
    private String version;
    @Value("${suning_sign_algorithm}")
    private String signAlgorithm;
    @Value("${suning_input_charset}")
    private String inputCharset;
    @Value("${suning_order_type}")
    private String orderType;
    @Value("${suning_currency}")
    private String currency;
    @Value("${suning_saler_merchant_no}")
    private String salerMerchantNo;
    @Value("${suning_goods_type}")
    private String goodsType;
    @Value("${suning_pay_timeout}")
    private String payTimeout;
    @Value("${suning_business_type}")
    private String businessType;
    @Value("${suning_sub_merchant_no}")
    private String subMerchantNo;
    @Value("${suning_sub_merchant_name}")
    private String subMerchantName;

    @Autowired
    private SuningWithholdApplyMapper suningWithholdApplyMapper;
    @Autowired
    private BackendCommissionApiImpl backendCommissionApi;
    /**
     * 跨行单笔收支付订单信息
     */
    @Autowired
    private PaymentOrderDtoMapper paymentOrderDtoMapper;

    @Autowired
    private CardInfoDtoMapper cardInfoDtoMapper;
    /**
     * 开票申请表信息
     */
    @Autowired
    private BillingApplyMapper billingApplyMapper;
    @Autowired
    private BusUserRoleMapper busUserRoleMapper;
    /**
     * 到账详情列表
     */
    @Autowired
    private ArrivalInfoMapper arrivalInfoMapper;
    @Autowired
    private BackendMessageApiImpl messageApiImpl;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BankInfoMapper bankInfoMapper;
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private BillingApplyImgsMapper billingApplyImgsMapper;
    @Autowired
    private BackendPinganfuApiImpl backendPinganfuApi;
    @Autowired
    private LawCaseInfoMapper lawCaseInfoMapper;
    @Autowired
    private SurveyRiskCaseInfoMapper surveyRiskCaseInfoMapper;
    @Autowired
    private BillingApplyCompanyMapper billingApplyCompanyMapper;
    @Autowired
    private BillingApplyRecipientMapper billingApplyRecipientMapper;
    @Autowired
    private SurveyBillingApplyMapper surveyBillingApplyMapper;
    @Autowired
    private SurveyConsignorMapper surveyConsignorMapper;

    @ApiMethod(descript = "苏宁代扣申请列表" ,value = "suning-select-wha-list")
    @Override
    public ApiResponse findSuningWithholdApplyList(ApiRequest request) {
        Long type = request.getLong("type");
        if (type == 1L){//评估代扣申请 放款代扣和紧急代扣的
            request.put("applyType",-1);//applyType in (0,2)
        }else if (type == 2L){//索赔代扣申请 还款代扣和紧急代扣的
            request.put("applyType",-2);//applyType in (1,4,2)
        }else if (type == 3L){//财务代扣申请 代扣成功的
            request.put("withholdState",2);
//            request.put("mType",3);

            //工作台：苏宁代扣，未确认到账
            Long numberType = request.getLong("numberType");
            request.put("numberType",numberType);
        }
        this.setBackendPageSize(request);

        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(request.getLong("operatorId"));
        if(userInfo != null){
            if(userInfo.getIsTester() != 1){
                //不是测试人员：默认查询非测试案件
                request.put("isTest",0);
            }else{
                //测试人员:默认查询测试案件
                request.put("isTest",1);
            }
        }
        int count = suningWithholdApplyMapper.selectListSize(request);
        List<SuningWithholdApply> suningWithholdApplies = suningWithholdApplyMapper.selectList(request);
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,suningWithholdApplies);
    }

    @ApiMethod(descript = "发起苏宁代扣",value = "suning-ok-start-wha-info")
    @Override
    public ApiResponse okStartSuningWithholdApplyInfo(ApiRequest request) {
        Long id = request.getLong("id");
        Long currentUserId = request.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        Integer withholdType = request.getInt("withholdType");
//        CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByCaseId(id);//根据案件中心id查看卡信息
        CardInfoDto dto = new CardInfoDto();
        dto.setCaseId(id);
        if (withholdType == 2){
            dto.setCardSource(1);
        }else if (withholdType == 4){
            dto.setCardSource(2);
        }
        CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByInfo(dto);
        CaseCenterInfo caseCenterInfo =  caseCenterInfoMapper.selectByPrimaryKey(id);
        Double amount = request.getDouble("amount");
        Double hidAmount = request.getDouble("hidAmount");
        //苏宁代扣发起          需要审核true
        String path = request.getRes().getSession().getServletContext().getRealPath("/");
        Boolean isSuccess = proxy(cardInfoDto, amount, caseCenterInfo, true, withholdType, currentUserId, null, path, 1,hidAmount);
        if (isSuccess){
            Date handInTime = DateUtils.parseDate(request.getString("handInTime"), "yyyy-MM-dd");
            caseCenterInfo.setHandInFlag("1");// 已发起代扣              // 已发起代扣之后,由财务确认代扣之后 将申请结案状态改为0(未申请结案),代扣状态改为2 代扣已确认
            caseCenterInfo.setHandInTime(handInTime);
            caseCenterInfoMapper.updateByPrimaryKey(caseCenterInfo);
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
            Boolean b = messageApiImpl.addMessageOrFollowByUpdCaseState(userInfo.getUserId(),userInfo.getUserName(),caseCenterInfo,"代扣已发起","成功");
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    @ApiMethod(descript = "财务确认",value = "suning-ok-wha-info")
    @Override
    public ApiResponse okSuningWithholdApplyInfo(ApiRequest request){
        Long id = request.getLong("id");
        Long type = request.getLong("type");// 0 代扣确认   1确认到账  3转结案
        Long currentUserId = request.getLong("operatorId");
        Integer applyType = request.getInt("applyType");
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        SuningWithholdApply suningWithholdApply = suningWithholdApplyMapper.selectByPrimaryKey(id);
        Map<String,Object> map = new HashMap<String, Object>();
        map = new HashMap<String,Object>();
        map.put("caseNo",suningWithholdApply.getCaseNo());
        CaseCenterInfo caseCenterInfo = null;
        if (applyType ==5 || applyType == 6){
            caseCenterInfo = null;
        }else{
            caseCenterInfo = caseCenterInfoMapper.findListByInfo(map).get(0);
        }
        if (0 == type){
            Boolean isSuccess = false;
            //现金 或者  转账  不对接苏宁
            if (suningWithholdApply.getWithholdType() == 1 || suningWithholdApply.getWithholdType() == 3){
                isSuccess = true;
            }else if (suningWithholdApply.getWithholdType() == 2 || suningWithholdApply.getWithholdType() == 4){
//                CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByUserId(suningWithholdApply.getAppUserId());
//                CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByCaseId(caseCenterInfo.getId());
                CardInfoDto dto = new CardInfoDto();
                dto.setCaseId(caseCenterInfo.getId());
                if (suningWithholdApply.getWithholdType() == 2){
                    dto.setCardSource(1);
                }else if (suningWithholdApply.getWithholdType() == 4){
                    dto.setCardSource(2);
                }
                CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByInfo(dto);
                if (cardInfoDto == null){
                    return new ApiResponse(ApiMsgEnum.CASE_SUNING_HANDOUT_CARD);
                }
                suningWithholdApply.setWithholdState(1);//代扣中
                suningWithholdApplyMapper.updateByPrimaryKey(suningWithholdApply);
                String path = request.getRes().getSession().getServletContext().getRealPath("/");
                isSuccess = proxy(cardInfoDto, suningWithholdApply.getWithholdMoney(), caseCenterInfo, false, suningWithholdApply.getWithholdType(), currentUserId, suningWithholdApply.getId(), path, 1,suningWithholdApply.getWithholdMoney());
            }
            //成功之后改  案件状态
            if (isSuccess){
                suningWithholdApply.setWithholdState(2);
                suningWithholdApply.setRemark(null);
                suningWithholdApplyMapper.updateByPrimaryKey(suningWithholdApply);
                caseCenterInfo.setHandInFlag("2");//已确认代扣
                caseCenterInfo.setHandInTime(suningWithholdApply.getWithholdTime());
                caseCenterInfo.setClosedState(0);//未申请结案
                Boolean b = messageApiImpl.addMessageOrFollowByUpdCaseState(userInfo.getUserId(),userInfo.getUserName(),caseCenterInfo,"代扣已确认","成功");
            }else{
                suningWithholdApply.setWithholdState(3);
                suningWithholdApplyMapper.updateByPrimaryKey(suningWithholdApply);
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }else if (1 == type){
            //调用到账已确认的接口
            int ret = backendCommissionApi.commissionCalculation(request,caseCenterInfo,applyType,suningWithholdApply,userInfo);
            if (ret == 0){
                suningWithholdApply.setAccountState(1);//已确认到账
                suningWithholdApplyMapper.updateByPrimaryKey(suningWithholdApply);
                //修改开票记录表的到账状态
                updateBillingApply(suningWithholdApply,request);
                return new ApiResponse(ApiMsgEnum.SUCCESS);//直接返回, 不需要保存关于案件的信息
            }else if (ret == 1){
                return new ApiResponse(ApiMsgEnum.CASE_COMMISSION_MONEY);
            }else if (ret == 2){
                return new ApiResponse(ApiMsgEnum.CASE_COMMISSION_USER_LEVEL);
            }else{
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }else if (3 == type){
            caseCenterInfo.setGradationState(3);//索赔阶段
            caseCenterInfo.setClosedState(0);//未发起结案
            Boolean b = messageApiImpl.addMessageOrFollowByUpdCaseState(userInfo.getUserId(),userInfo.getUserName(),caseCenterInfo,"已转结案","成功");
        }
        int ret = 0;
        if (caseCenterInfo != null){
            ret = caseCenterInfoMapper.updateByPrimaryKey(caseCenterInfo);
        }
        if (ret < 0){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    private void updateBillingApply(SuningWithholdApply suningWithholdApply,ApiRequest request) {

        Map<String, Object> bmap = new HashMap<>();
        bmap.put("caseId",suningWithholdApply.getCaseId());
        //查询是否有开票记录
        BillingApply billingApply = billingApplyMapper.selectByInfo(bmap);
        if(billingApply == null){
            return;
        }else{
            Map<String, Object> smap = new HashMap<>();
            smap.put("caseId",suningWithholdApply.getCaseId());
            smap.put("caseNo",suningWithholdApply.getCaseNo());
            int count = suningWithholdApplyMapper.selectCountForAccountState(smap);
            if(count > 0){
                return;
            }else{
                bmap = new HashMap<>();
                bmap.put("caseId",suningWithholdApply.getCaseId());
                bmap.put("confirmAccountState",2);
                Date confirmAccountTime = DateUtils.parseDate(request.getString("confirmAccountTime"), "yyyy-MM-dd");
                bmap.put("confirmAccountTime",confirmAccountTime);
                billingApplyMapper.updateAccountState(bmap);
            }

        }
    }

    @ApiMethod(descript = "开票申请列表",value = "suning-bill-apply-list")
    @Override
    public ApiResponse findBillApply(ApiRequest request){
        Long menuType = request.getLong("menuType");
        if (menuType != null && menuType == 5){
            Long currentUserId = request.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
            List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
            Boolean orgManager = isRoleUser(userRoles,31L);//机构经理
            Boolean boss = isRoleUser(userRoles,32L);//总经理
            if (orgManager){
                request.put("menuType",51);//billingState in (3,5,6,7,8);
            }
            if (boss){
                request.put("menuType",52);// billingState in (5,6);
            }
            if (boss && orgManager){
                request.put("menuType",51);//如果两个角色都有, 则同机构经理 billingState in (3,5,6,7,8);
            }
            if (!orgManager && !boss){
                request.put("billingState",-1);//如果两个角色都没有,查询不到数据
            }
        }
        if (menuType != null && menuType == 3){
            request.put("mType",3);
        }
        this.setBackendPageSize(request);
        int count = billingApplyMapper.selectListSize(request);
        List<BillingApply> billingApplies = billingApplyMapper.selectList(request);
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,billingApplies);
    }

    private Boolean isRoleUser(List<BusUserRole> busUserRoles,Long roleId){
        for (BusUserRole busUserRole : busUserRoles){
            if (busUserRole.getRoleId() == roleId){
                return true;
            }
        }
        return false;
    }

    @ApiMethod(descript = "到账详情列表",value = "suning-arrival-info-list")
    @Override
    public ApiResponse findArrivalList(ApiRequest request) {
        this.setBackendPageSize(request);
        int count = arrivalInfoMapper.selectListSize(request);
        List<ArrivalInfoDto> arrivalInfos = arrivalInfoMapper.selectList(request);
        return new ApiResponse(ApiMsgEnum.SUCCESS,count,arrivalInfos);
    }


    @ApiMethod(descript = "开票确认",value = "suning-bill-apply-upd")
    @Override
    public ApiResponse updBillApply(ApiRequest request) {
        String operatorType = request.getString("operatorType");
        Long id = request.getLong("id");
        Long userId = getCurrentUserId(request);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if (StringUtils.isNotBlank(operatorType) ){
            BillingApplyImgs billingApplyImgs = billingApplyImgsMapper.selectByPrimaryKey(id);
            int repeatCount = billingApplyImgsMapper.repeatBillingCodeCount(request.getString("billingCode"));

            if("upd".equals(operatorType)){
                if (repeatCount>0 && !request.getString("billingCode").equals(billingApplyImgs.getBillingCode())){
                    return new ApiResponse(ApiMsgEnum.BILL_REPEAT);
                }
                String createTime = request.getString("createTime");
                if (StringUtils.isNotBlank(createTime)){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        billingApplyImgs.setCreateTime(simpleDateFormat.parse(createTime));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                billingApplyImgs.setBillingImgs(request.getString("img"));
                billingApplyImgs.setBillingCode(request.getString("billingCode"));
                billingApplyImgs.setBillingMoney(request.getDouble("billingMoney"));
                billingApplyImgsMapper.updateByPrimaryKey(billingApplyImgs);
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
            if("hc".equals(operatorType)){ //红冲  需要添加一条新的  并且把之前到账记录删了
                if (repeatCount>0){
                    return new ApiResponse(ApiMsgEnum.BILL_REPEAT);
                }
                billingApplyImgs.setState(3);
                billingApplyImgs.setPayState(0);
                billingApplyImgsMapper.updateByPrimaryKey(billingApplyImgs);
                billingApplyImgsMapper.deleteByBillImgId(billingApplyImgs.getId());//删除到账记录
                BillingApplyImgs imgs = new BillingApplyImgs();
                imgs.setBillId(billingApplyImgs.getBillId());
                imgs.setBillingImgs(request.getString("img"));
                imgs.setBillingCode(request.getString("billingCode"));
                imgs.setBillingMoney(-billingApplyImgs.getBillingMoney());
                imgs.setCreateById(billingApplyImgs.getCreateById());
                imgs.setCreateBy(billingApplyImgs.getCreateBy());
                String createTime = request.getString("createTime");
                if (!StringUtils.isEmpty(createTime)){
                    try {
                        imgs.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").parse(createTime));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else{
                    imgs.setCreateTime(new Date());
                }
                imgs.setState(3);
                imgs.setStateUpdateTime(new Date());
                imgs.setStateUpdateBy(userInfo.getUserName());
                imgs.setStateUpdateById(userInfo.getUserId());
                imgs.setPayState(0);
                billingApplyImgsMapper.insert(imgs);
                return new ApiResponse(ApiMsgEnum.SUCCESS);
            }
            if ("del".equals(operatorType)){
                int i = billingApplyImgsMapper.deleteByPrimaryKey(id);
                return new ApiResponse(i>0?ApiMsgEnum.SUCCESS:ApiMsgEnum.FAIL);
            }

        }

        BillingApply apply = billingApplyMapper.selectByPrimaryKey(id);

        //并将上传的“票据证明”保存
        BillingApplyImgs billingApplyImgs = new BillingApplyImgs();
        billingApplyImgs.setBillId(id);
        billingApplyImgs.setCreateTime(new Date());
        String img = request.getString("img");
        billingApplyImgs.setBillingImgs(img);
        String billingCode = request.getString("billingCode");
        billingApplyImgs.setBillingCode(billingCode);
        Double billingMoney = request.getDouble("billingMoney");
        billingApplyImgs.setBillingMoney(billingMoney);
        billingApplyImgs.setCreateById(userId);
        billingApplyImgs.setCreateBy(userInfo.getUserName());
//        if ("yhc".equals(request.getString("yhc"))){
//            //待红冲功能，已有数据,直接红冲
//            if(request.getString("type")!=null && ("4".equals(request.getString("type")) || "5".equals(request.getString("type")))){
//                BillingApplyImgs billingApplyImgOld = billingApplyImgsMapper.selectByPrimaryKey(id);
//                billingApplyImgOld.setBillingImgs(img);
//                billingApplyImgOld.setBillingCode(billingCode);
//                if("4".equals(request.getString("type"))){
//                    billingMoney = billingMoney>0 ? -billingMoney : billingMoney;
//                    billingApplyImgOld.setBillingMoney(billingMoney);
//                    billingApplyImgOld.setState(3);
//                }else if("5".equals(request.getString("type"))){
//                    billingApplyImgOld.setBillingMoney(billingMoney);
//                    billingApplyImgOld.setState(1);
//                }
//                billingApplyImgOld.setStateUpdateTime(new Date());
//                billingApplyImgOld.setStateUpdateBy(userInfo.getUserName());
//                billingApplyImgOld.setStateUpdateById(userInfo.getUserId());
//                billingApplyImgsMapper.updateByPrimaryKeySelective(billingApplyImgOld);
//            }
//            else {
//                billingMoney = billingMoney>0 ? -billingMoney : billingMoney;
//                billingApplyImgs.setBillingMoney(billingMoney);
//                billingApplyImgs.setState(3);
//                billingApplyImgs.setStateUpdateTime(new Date());
//                billingApplyImgs.setStateUpdateBy(userInfo.getUserName());
//                billingApplyImgs.setStateUpdateById(userInfo.getUserId());
//                billingApplyImgsMapper.insert(billingApplyImgs);
//            }
//        }else{
//
//            List<BillingApplyImgs> billList =  JSON.parseArray(request.getString("aaa"),BillingApplyImgs.class);
//            for (int i = 0; i < billList.size(); i++) {
//                BillingApplyImgs imgs = billList.get(i);
//                imgs.setState(1);
//                imgs.setBillId(id);
//                imgs.setCreateTime(new Date());
//                imgs.setCreateById(userId);
//                imgs.setCreateBy(userInfo.getUserName());
//                imgs.setPayState(0);
//                billingApplyImgsMapper.insert(imgs);
//            }
////            billingApplyImgsMapper.insert(billingApplyImgs);
//        }

        List<BillingApplyImgs> billList =  JSON.parseArray(request.getString("billings"),BillingApplyImgs.class);
        if (billList != null && billList.size()>0){
            int repeatCount = billingApplyImgsMapper.repeatBillingCodeCount(billList.stream().map(BillingApplyImgs::getBillingCode).collect(Collectors.joining(",")));
            if (repeatCount>0){
                return new ApiResponse(ApiMsgEnum.BILL_REPEAT);
            }
            for (int i = 0; i < billList.size(); i++) {
                BillingApplyImgs imgs = billList.get(i);
                imgs.setState(1);
                imgs.setBillId(id);
                Date createTime = imgs.getCreateTime();
                if (createTime == null){
                    createTime = new Date();
                }
                imgs.setCreateTime(createTime);
                imgs.setCreateById(userId);
                imgs.setCreateBy(userInfo.getUserName());
                imgs.setPayState(0);
                billingApplyImgsMapper.insert(imgs);
            }
        }


        //确认开票时,查询开票对象是否存在，如不存在则insert
        Long entrustOrgId = request.getLong("entrustOrgId");
        if(!StringUtils.isEmpty(apply.getCompanyName())){
            Map<String, Object> map = new HashMap<>();
            map.put("companyName",apply.getCompanyName());
            map.put("entrustOrgId",entrustOrgId);
            BillingApplyCompany company = billingApplyCompanyMapper.selectByInfo(map);
            if(company == null){
                company = new BillingApplyCompany();
                company.setCompanyName(apply.getCompanyName());
                company.setEntrustOrgId(entrustOrgId);
                if (company.getEntrustOrgId() != null){
                    SurveyConsignor surveyConsignor = surveyConsignorMapper.selectByPrimaryKey(company.getEntrustOrgId());
                    if (surveyConsignor != null){
                        company.setEntrustOrgName(surveyConsignor.getName());
                    }
                }
                company.setCreateTime(new Date());
                company.setCreateBy(userInfo.getUserId());
                company.setCreateByName(userInfo.getUserName());
                company.setState(0);
                company.setDeleteFlag(0);
                billingApplyCompanyMapper.insert(company);
            }
            if (apply != null){
                apply.setCompanyId(company.getId());//更新开票对象
            }
        }
        //确认开票时,查询开票-收件人信息是否存在，如不存在则insert
        if(apply.getRecipientsName()!=null && !("").equals(apply.getRecipientsName()) && apply.getRecipientsPhone()!=null && !("").equals(apply.getRecipientsPhone())&& apply.getAddress()!=null&& !("").equals(apply.getAddress())){
            Map<String, Object> map2 = new HashMap<>();
            map2.put("recipientsName",apply.getRecipientsName());
            map2.put("recipientsPhone",apply.getRecipientsPhone());
            map2.put("address",apply.getAddress());
            BillingApplyRecipient recipient = billingApplyRecipientMapper.selectByInfo(map2);
            if(recipient == null){
                recipient = new BillingApplyRecipient();
                recipient.setRecipientsName(apply.getRecipientsName());
                recipient.setRecipientsPhone(apply.getRecipientsPhone());
                recipient.setProvince(apply.getProvince());
                recipient.setProvinceId(apply.getProvinceId());
                recipient.setCity(apply.getCity());
                recipient.setCityId(apply.getCityId());
                recipient.setDistrict(apply.getDistrict());
                recipient.setDistrictId(apply.getDistrictId());
                recipient.setAddress(apply.getAddress());
                recipient.setCreateBy(apply.getCreateBy());
                recipient.setCreateById(apply.getCreateById());
                recipient.setStaffOrgId(apply.getStaffOrgId());
                recipient.setStaffOrgName(apply.getStaffOrgName());
                billingApplyRecipientMapper.insertSelective(recipient);
            }
        }

        if (apply != null){
            apply.setBillingState(3);//改为"已开票"
            apply.setBillingTime(new Date());//开票时间
            apply.setBillingBy(userInfo.getUserName());//开票人姓名
            apply.setBillingById(userId);//开票人
            int ret = billingApplyMapper.updateByPrimaryKey(apply);
            if (ret <0){
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }

        /** 司法评估票据 更改案件的开票状态 */
        if (apply.getBillingSource() == 4 && apply.getBillingSource() != null){
            LawCaseInfo lawCaseInfo = lawCaseInfoMapper.selectByCaseNo(apply.getCaseNo());
            if (lawCaseInfo != null){
                lawCaseInfo.setIsBill(2);
                lawCaseInfoMapper.updateByPrimaryKey(lawCaseInfo);
            }
        }

        /** 狄大人平台调查案件票据  更改案件状态 */
        if (apply.getBillingSource() == 3 && apply.getBillingSource() != null){
            SurveyRiskCaseInfo surveyRiskCaseInfo = surveyRiskCaseInfoMapper.getSurveyRiskCaseInfoBySurveyCno(apply.getCaseNo());
            //单个开票的数据
            if(surveyRiskCaseInfo != null){
                surveyRiskCaseInfo.setIsPayEntrustFee(2);
                surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
            }else{//批量开票的数据
                Map<String,Object> map = new HashMap<>();
                map.put("billId",apply.getId());
                List<SurveyBillingApply> applyList = surveyBillingApplyMapper.selectByInfo(map);
                for (int i = 0; i < applyList.size(); i++) {
                    surveyRiskCaseInfo = surveyRiskCaseInfoMapper.selectByPrimaryKey(applyList.get(i).getRiskCaseInfoId());
                    if(surveyRiskCaseInfo!=null){
                        surveyRiskCaseInfo.setIsPayEntrustFee(2);
                        surveyRiskCaseInfoMapper.updateByPrimaryKey(surveyRiskCaseInfo);
                    }
                }
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @Override
    public int saveBillingApply(BillingApply billingApply) {
        return  billingApplyMapper.insert(billingApply);
    }


    /**
     * 代扣
     * @param cardInfoDto  卡信息
     * @param amount        扣款金额
     * @param caseCenterInfo 案件信息  用于保存苏宁代扣申请表信息
     * @param isAudit  是否审核  true 审核  false 不审核  （不审核表示直接扣款 ，审核需要财务确认扣款）
     * @param type   代扣类型  1:现金，2：苏宁代扣，3：转账  4平安代扣
     * @param currentUserId  当前登录用ID
     * @param applyId 代扣申请ID;
     * @param applyType 0放款代扣  1还款代扣  2紧急代扣
     * @param hidAmount  还款代扣的本息金额或本金
     * @return
     */
    @Override
    public boolean proxy(CardInfoDto cardInfoDto,Double amount,CaseCenterInfo caseCenterInfo,Boolean isAudit,Integer type,Long currentUserId,Long applyId,String resoucePath,Integer applyType,Double hidAmount){
        try {
            SuningWithholdApply applyBx = null;
            //代扣状态 state  0：申请代扣，1：代扣中，2：代扣成功，3：代扣失败
            //保存代扣申请表
            SuningWithholdApply apply = suningWithholdApplyMapper.selectByPrimaryKey(applyId);
            if (apply != null){
                apply.setCaseId(caseCenterInfo.getId());
                apply.setCaseNo(caseCenterInfo.getCaseNo());
                apply.setCaseTitle(caseCenterInfo.getCaseTitle());
                apply.setWithholdMoney(amount);
                apply.setWithholdState(isAudit ? 0 : 2);//需要审核保存代扣中 否则代扣成功
                if ((2 == type || 4 == type) && !isAudit){
                    apply.setWithholdState(1);//如果为代扣 且不审核 则保存为代扣中
                }
                apply.setWithholdType(type);
                apply.setAppUserId(currentUserId);
                apply.setApplyType(applyType);
                suningWithholdApplyMapper.updateByPrimaryKey(apply);
            }else{
                //还款代扣 且是 贷款案件
//                if (applyType == 1 && caseCenterInfo.getType() == 1){
//                    applyBx = new SuningWithholdApply();//本息代扣记录
//                    applyBx.setCaseId(caseCenterInfo.getId());
//                    applyBx.setCaseNo(caseCenterInfo.getCaseNo());
//                    applyBx.setCaseTitle(caseCenterInfo.getCaseTitle());
//                    applyBx.setWithholdMoney(DecimalUtil.twoDecimalTOFourFromFive(amount - hidAmount));
//                    applyBx.setWithholdTime(new Date());
//                    applyBx.setWithholdState(isAudit ? 0 : 2);
//                    if ((2 == type || 4 == type) && !isAudit){
//                        applyBx.setWithholdState(1);//如果为代扣 且不审核 则保存为代扣中
//                    }
//                    applyBx.setWithholdType(type);
//                    applyBx.setAppUserId(currentUserId);
//                    applyBx.setApplyType(4);
//                    suningWithholdApplyMapper.insert(applyBx);
//                }
                apply = new SuningWithholdApply();
                apply.setCaseId(caseCenterInfo.getId());
                apply.setCaseNo(caseCenterInfo.getCaseNo());
                apply.setCaseTitle(caseCenterInfo.getCaseTitle());
//                if (applyType == 1 && caseCenterInfo.getType() == 1){
//                    apply.setWithholdMoney(DecimalUtil.twoDecimalTOFourFromFive(hidAmount == null ? 0D : hidAmount));
//                }else {
//                    apply.setWithholdMoney(amount);
//                }
                apply.setWithholdMoney(amount);
                apply.setWithholdTime(new Date());
                apply.setWithholdState(isAudit ? 0 : 2);
                if ((2 == type || 4 == type) && !isAudit){
                    apply.setWithholdState(1);//如果为代扣 且不审核 则保存为代扣中
                }
                apply.setWithholdType(type);
                apply.setAppUserId(currentUserId);
                apply.setApplyType(applyType);
                suningWithholdApplyMapper.insert(apply);
            }
            if (!isAudit && 2 == type){
                //1.构造支付订单 以及 卡信息 参数
                SuningPaymentOrderDto suningPaymentOrderDto = buildPayOrder(amount,cardInfoDto.getBankCode(),cardInfoDto.getCardType().toString());
                SuningCardInfoDto suningCardInfoDto = ConvertToBeanUtil.buildInfo(SuningCardInfoDto.class, cardInfoDto);
                //2.加密卡信息
                String suningCardInfoJson = JSONUtil.toJSONString(suningCardInfoDto);
                String cryptoSuningCardInfo = CryptoUtil.encryptJson(suningCardInfoJson,  resoucePath + filePath);
                suningPaymentOrderDto.setCardInfo(cryptoSuningCardInfo);
                //3.签名
                Map<String,String> map = objectToMap(suningPaymentOrderDto);
                String digest = Digest.digest(map, "signature", "signAlgorithm");
                String signature = CryptoUtil.sign(digest,CryptoUtil.getPrivateKey(getKey()));
                suningPaymentOrderDto.setSignature(signature);
                map = objectToMap(suningPaymentOrderDto);
                String response = HttpClientUtil.post(url, map, false);

                Boolean b = false;
                JSONObject jsonObject2 = JSON.parseObject(response);
                if ("0000".equals(jsonObject2.get("responseCode"))){
                    apply.setWithholdState(2);
                    apply.setRemark(null);
                    apply.setOrderCode(jsonObject2.get("outOrderNo").toString());
                    suningWithholdApplyMapper.updateByPrimaryKey(apply);
                    if (applyBx != null){
                        applyBx.setWithholdState(2);
                        applyBx.setRemark(null);
                        applyBx.setOrderCode(jsonObject2.get("outOrderNo").toString());
                        suningWithholdApplyMapper.updateByPrimaryKey(applyBx);
                    }
                    b = true;
                }else{
                    apply.setWithholdState(3);
                    apply.setCode(jsonObject2.get("responseCode").toString());
                    apply.setRemark(jsonObject2.get("responseMsg").toString());
                    apply.setOrderCode(jsonObject2.get("outOrderNo").toString());
                    suningWithholdApplyMapper.updateByPrimaryKey(apply);
                    if (applyBx != null){
                        applyBx.setWithholdState(3);
                        applyBx.setCode(jsonObject2.get("responseCode").toString());
                        applyBx.setRemark(jsonObject2.get("responseMsg").toString());
                        applyBx.setOrderCode(jsonObject2.get("outOrderNo").toString());
                        suningWithholdApplyMapper.updateByPrimaryKey(applyBx);
                    }
                    b = false;
                }
                //保存单笔收支付订单表
                PaymentOrderDto paymentOrderDto = ConvertToBeanUtil.buildInfo(PaymentOrderDto.class,suningPaymentOrderDto);
                paymentOrderDto.setCardInfo(suningCardInfoJson);
                paymentOrderDto.setSignAlgorithm(digest);
                paymentOrderDto.setRemark(jsonObject2.get("responseMsg").toString());
                paymentOrderDto.setCaseId(caseCenterInfo.getId());
                paymentOrderDto.setCreateTime(new Date());
                paymentOrderDtoMapper.insert(paymentOrderDto);
                return b;
            }

            //平安代扣
            if (4 == type){
                //发送平安付代扣请求
                RepayRespDTO respDTO = backendPinganfuApi.withHold(caseCenterInfo,cardInfoDto,"01",amount,currentUserId);
                if (respDTO == null){
                    apply.setWithholdState(3);//代扣失败
                    apply.setCode("7777");
                    apply.setRemark("未授权");
                    suningWithholdApplyMapper.updateByPrimaryKey(apply);
                    if (applyBx != null){
                        applyBx.setWithholdState(3);//代扣失败
                        applyBx.setCode("7777");
                        applyBx.setRemark("未授权");
                        suningWithholdApplyMapper.updateByPrimaryKey(applyBx);
                    }
                    return  false;
                }else{
                    //拿到响应数据更改
                    if ("000000".equals(respDTO.getRespCode())){
                        apply.setWithholdState(2);//代扣成功
                        apply.setRemark(null);
                        apply.setOrderCode(respDTO.getReqNo());
                        suningWithholdApplyMapper.updateByPrimaryKey(apply);
                        if (applyBx != null){
                            applyBx.setWithholdState(2);//代扣成功
                            applyBx.setRemark(null);
                            applyBx.setOrderCode(respDTO.getReqNo());
                            suningWithholdApplyMapper.updateByPrimaryKey(applyBx);
                        }
                        return true;
                    }else{
                        apply.setWithholdState(3);//代扣失败
                        apply.setCode(respDTO.getRespCode());
                        apply.setRemark(respDTO.getRespMsg());
                        apply.setOrderCode(respDTO.getReqNo());
                        suningWithholdApplyMapper.updateByPrimaryKey(apply);
                        if (applyBx != null){
                            applyBx.setWithholdState(3);//代扣失败
                            applyBx.setCode(respDTO.getRespCode());
                            applyBx.setRemark(respDTO.getRespMsg());
                            applyBx.setOrderCode(respDTO.getReqNo());
                            suningWithholdApplyMapper.updateByPrimaryKey(applyBx);
                        }
                        return false;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 构建支付订单信息
     * @param amount  支付金额
     * @param bankCode 银行卡编码
     * @param cardType 卡类型    1储蓄卡   2信用卡
     * @return   苏宁支付订单对象
     */
    private SuningPaymentOrderDto buildPayOrder(Double amount,String bankCode,String cardType){
        try {
            SuningPaymentOrderDto dto = new SuningPaymentOrderDto();
            dto.setMerchantNo(merchantNo);//交易发起方商户号，易付宝提供
            dto.setPublicKeyIndex(publicKeyIndex);
            dto.setVersion(version);
            dto.setSignature("");//签名--  主函数构建签名
            dto.setSignAlgorithm(signAlgorithm);
            dto.setInputCharset(inputCharset);
            dto.setSubmitTime(getNum());
            dto.setBankCode(bankCode);
            dto.setCardType(cardType);
            dto.setCardInfo("");//卡信息  --卡信息构建后 加密    主函数构建
            dto.setOutOrderNo(getNum().concat(RandomIDUtil.getNumber(4)));
            dto.setOrderType(orderType);
            dto.setOrderAmount(Double.valueOf(amount * 100).longValue()+"");
            dto.setOrderTime(getNum());
            dto.setCurrency(currency);
            dto.setSalerMerchantNo(salerMerchantNo);//卖家商户易付宝商户号
            dto.setGoodsType(goodsType);//商品类型 易付宝分配
            dto.setGoodsName(Base64.encodeBase64String("商品名称".getBytes("UTF-8")));//商品名称 Base64 字符集 UTF-8 转码
            dto.setPayTimeout(payTimeout);
            dto.setBusinessType(businessType);//业务类型 有易付宝分配
            dto.setSubMerchantNo(subMerchantNo);//二级商户号
            dto.setSubMerchantName(subMerchantName);//二级商户名称
            dto.setTunnelData("");//扩展信息
            dto.setRemark("");//备注
            return dto;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String  getNum(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    private Map<String, String> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object fieldObj =field.get(obj);
            String fieldStr="";
            if(null!=fieldObj)
            {
                fieldStr =fieldObj.toString();
            }
            map.put(field.getName(), fieldStr);
        }

        return map;
    }

    private String getKey(){
        String key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMBCrSpa/41WFB2o" +
                "YlRpQvfcCSDt4hb+8MnU0uqIeQ2WxCTdimwOHfH2wNpee13KTtUT3tdb3rC2IFE+" +
                "/zYblg9NO6onrUmm7siSKdzq9D3Ek3UmO1rJ8gNYnRDtkoGkXRB81R3HOqwAsCDo" +
                "Pih5U2OY0CTsF6uwoEN/Bu1F4B89AgMBAAECgYAsrdIMK8WDlONr2Puw3h1f+FRu" +
                "wJlv+qL+ZGT3d+uZytWeM5W9crJmzo8WsCo/K4qSXeHFbmYb7tcnyloIuvRuBow6" +
                "fdNBf6ptFxsC1N7WbP7ZljvLJCg1gb7rjViPPaz9cDEr3b+eYJPtnHNBbMIq/Vrt" +
                "0Q3fx8y6r/VnPuTAiQJBAO2FbO+1YwbiOmi7UA6dsQefeXXmgcE0Obx/3jLaqqIC" +
                "iN951w1LzX7png0rncxG9D3ifBNlwPztfUYEwYN1HNMCQQDPN9GKKxQg1x+zkOAI" +
                "hHvMKRbvRkSlT4bP2EJ93jAgTaKoPQ2G4E9+RietgXL5owwW9FRuq2GDUEymIWpR" +
                "OgmvAkBIJrPEzVDbknUzw1K6XeSc8DCxQ+g+jGLNg/o3cH1M4YA6goR1IYW2+7hr" +
                "P8ibeSJQejA+pDZPnsTjNCakjDrJAkEAip8qvCWpZ3bCLFvko44NSzzJrPPzBCu5" +
                "Yd2oTY+P3mxRPf7px6rTQwQnkvigM8QRWGHHAeoAZ9oWDKUZ/JC43QJBAIYg5tHG" +
                "Ujs44DoAzFvdiIjp0vDL82GFBRk7xOl8gZx0v6t49aYHIjwHtTjcf68/0zEyET19" +
                "1OmUWGpp/pdEn3E=";
        return key;
    }

    @ApiMethod(descript = "银行卡信息",value = "backend-card-info")
    @Override
    public ApiResponse editBankCardInfo(ApiRequest request) {
        this.setBackendPageSize(request);
        Long id = request.getLong("caseId");
        CardInfoDto dto = new CardInfoDto();
        dto.setCaseId(id);
        dto.setCardSource(1);
        CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByInfo(dto);
//        CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByCaseId();
        return new ApiResponse(ApiMsgEnum.SUCCESS,null,cardInfoDto);
    }

    /*
     * 联合案件信息查看开票详情页面
     */
    @ApiMethod(descript = "联合案件信息查看开票详情页面",value = "suning-bill-apply-view-by-case-id")
    @Override
    public ApiResponse findBillApplyView(ApiRequest request) {
        Long currentUserId = request.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        List<BusUserRole> userRoles = busUserRoleMapper.orgUserRoleList(currentUserId);
        Boolean orgManager = isRoleUser(userRoles,31L);//机构经理
        Boolean boss = isRoleUser(userRoles,32L);//总经理

        Map<String,Object> map = new HashMap();
        Long id = request.getLong("id");
        map.put("id",id);
        BillingApply billingApply = billingApplyMapper.selectByPrimaryKey(request.getLong("id"));
        BillingApplyDto dto = ConvertToBeanUtil.buildInfo(BillingApplyDto.class,billingApply);
        dto.setOrgManager(orgManager);
        dto.setBoss(boss);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,dto);
    }

    /*
     * 查看确认到账页面
     */
    @ApiMethod(descript = "查看详情",value = "suning-with-hold-apply-view")
    @Override
    public ApiResponse findWithHoldApplyView(ApiRequest request) {
        Long id = request.getLong("id");
        SuningWithholdApply suningWithholdApply = suningWithholdApplyMapper.selectByPrimaryKey(id);
        SuningWithholdApplyDto dto = ConvertToBeanUtil.buildInfo(SuningWithholdApplyDto.class,suningWithholdApply);
        if (dto.getApplyType() == 5 || dto.getApplyType() == 6){
            dto.setShowConvertCloseState(false);
        }else{
            CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(suningWithholdApply.getCaseId());
            //是否显示转结案   结案状态为NULL 表示可以转结案
            if (caseCenterInfo.getClosedState() == null){
                dto.setShowConvertCloseState(true);
            }else{
                dto.setShowConvertCloseState(false);
            }
        }
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,dto);
    }

    /**
     * 保存或修改银行卡信息
     * @param apiRequest
     * @param apiRequest
     * @return
     */
    @ApiMethod(descript = "银行卡信息",value = "backend-card-info-save")
    @Override
    public ApiResponse saveBankCardInfo(ApiRequest apiRequest) {
        Long userId = apiRequest.getLong("operatorId");
        Long caseId = apiRequest.getLong("caseId");
//        CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByCaseId(caseId);
        CardInfoDto dto = new CardInfoDto();
        dto.setCaseId(caseId);
        dto.setCardSource(1);
        CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByInfo(dto);
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);

        if (cardInfoDto == null){
            cardInfoDto = ConvertToBeanUtil.toBeanFromApiRequest(apiRequest, CardInfoDto.class);
        }else{
            cardInfoDto = ConvertToBeanUtil.toBeanFromApiRequest(apiRequest,cardInfoDto);
        }

        //年度  月度做特殊处理
        if (cardInfoDto.getExpYear().length() == 1){
            cardInfoDto.setExpYear("0".concat(cardInfoDto.getExpYear()));//补0
        }else if(cardInfoDto.getExpYear().length() > 2){
            cardInfoDto.setExpYear(cardInfoDto.getExpYear().substring(cardInfoDto.getExpYear().length() - 2));//年度取最后两位
        }
        if (cardInfoDto.getExpMonth().length() == 1){
            cardInfoDto.setExpMonth("0".concat(cardInfoDto.getExpMonth()));
        }else if(cardInfoDto.getExpMonth().length() > 2){
            cardInfoDto.setExpMonth(cardInfoDto.getExpMonth().substring(cardInfoDto.getExpMonth().length() - 2));//月度取最后两位
        }

        cardInfoDto.setUserId(userId);
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        cardInfoDto.setUserName(userInfo.getUserName());
        cardInfoDto.setCaseId(caseId);
        cardInfoDto.setCaseNo(caseCenterInfo.getCaseNo());

        //根据银行name，查询银行信息
        Long bankId = apiRequest.getLong("bankId");
        BankInfo bankInfo = bankInfoMapper.selectByPrimaryKey(bankId);
        cardInfoDto.setBankName(bankInfo.getBankName());
        cardInfoDto.setBankCode(bankInfo.getBankCode());

        if (cardInfoDto.getId() == null || "".equals(cardInfoDto.getId())) {
            cardInfoDtoMapper.insertSelective(cardInfoDto);
        }else{
            cardInfoDtoMapper.updateByPrimaryKeySelective(cardInfoDto);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

}
