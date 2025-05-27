package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseCenterInfoApi;
import com.lefancrm.apicenter.backendapi.BackendSuningApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.model.CommonFile;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.apicenter.util.DateUtils;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.apicenter.util.SendMessageUntil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by lixianfeng on 2018/3/23.
 */
@ApiService(descript = "案件中心信息API")
@Service
public class BackendCaseCenterInfoApiImpl extends BaseServiceImpl implements BackendCaseCenterInfoApi{
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private BackendSuningApi suningApi;//苏宁代扣Api
    @Autowired
    private CardInfoDtoMapper cardInfoDtoMapper;//卡信息mapper
    @Autowired
    private CaseClosedReportMapper caseClosedReportMapper;//结案报告mapper
    @Autowired
    private SuningWithholdApplyMapper suningWithholdApplyMapper;//苏宁代扣申请mapper

    @Autowired
    private CommonEnumMapper commonEnumMapper;
    @Autowired
    private CaseFileMidMapper caseFileMidMapper;
    @Autowired
    private CommonFileMapper commonFileMapper;
    @Autowired
    private LoanApplicationMapper loanApplicationMapper;
    @Autowired
    private AgentApplyMapper agentApplyMapper;
    @Autowired
    private CommonAreaMapper commonAreaMapper;
    @Autowired
    private CaseFollowInfoMapper caseFollowInfoMapper;
    @Autowired
    private CaseFollowInfoFileMapper caseFollowInfoFileMapper;
    @Autowired
    private OperatorFollowInfoMapper operatorFollowInfoMapper;
    @Autowired
    private CaseMediationClaimReportMapper caseMediationClaimReportMapper;
    @Autowired
    private CaseMediationClaimMapper caseMediationClaimMapper;

    @Autowired
    private PaymentEstimateApplyMapper paymentEstimateApplyMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BankInfoMapper bankInfoMapper;

    @Autowired
    private PromotionOutlayMapper promotionOutlayMapper;

    @Autowired
    private DistributionBasicMapper distributionBasicMapper;

    @Autowired
    private OrgInfoMapper orgInfoMapper;

    @Autowired
    private OrgAccountMapper orgAccountMapper;
    @Autowired
    private OrgAccountDetailMapper orgAccountDetailMapper;

    @Autowired
    private BackendMessageApiImpl messageApiImpl;
    @Autowired
    private CaseCenterExtendMapper caseCenterExtendMapper;
    @Autowired
    private UserPromotedMapper userPromotedMapper;
    @Autowired
    private CaseRiskControlMapper caseRiskControlMapper;
    /**
     * 案件列表
     * @param apiReq
     * @return
     */
    @Override
    public ApiResponse list(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        //listType 列表类型
        //评估案件列表  0评估列表、1待评估列表
        //案件风控     2一审待审核列表、3二审待审核列表
        //案件风控     4待提交苏宁贷款、5已提交苏宁贷款
        //索赔案件列表 6待索赔列表、
        //案件风控    7索赔预案审核列表
        //索赔案件列表 77预案通过列表
        //案件风控     777结案报告审核
        //索赔案件列表  8需代扣列表、9案件列表、10待结案列表、11结案列表
        //财务管理     12已结案案件
        //案件管理     13紧急代扣
        //评估案件列表 14评估通过列表

        //案件管理     20用户待接收案件

        Long listType = apiReq.getLong("listType");
        Long operatorId = apiReq.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()

        //阶段状态判断
        if (0 == listType || 1 == listType || 2 == listType || 3 == listType || 4 == listType || 5 == listType || 14 == listType){
            apiReq.put("gradationState",2);//评估阶段
        }else if (6 == listType || 7 == listType || listType == 77 || listType == 777 || 8 == listType || 9 == listType || 10 == listType){
            apiReq.put("gradationState",3);//索赔阶段
        }else if(11 == listType || 12 == listType){
            apiReq.put("gradationState",4);//结案
        }else if (13 == listType){
            //紧急代扣  所有案件
        }else if (20 == listType){
            //待接收案件
        }else{
            //其他列表  无数据
            apiReq.put("gradationState",-1);
        }


        //流程状态判断
        if (listType == 1){
            apiReq.put("assessId",operatorId);//评估员
            apiReq.put("caseState",2);//已接收的状态
            apiReq.put("listType",1);//列表类型  评估员列表 ,查询条件为 未填写报告的, 退回的,未提交审核的    sql 根据listType 组织查询条件
        }
        else if (listType == 2){
            apiReq.put("issuanceState",5);//一次审核中,待提交一审
        }
        else if (listType == 3){
            apiReq.put("issuanceState",1);//一审通过的,待提交二审
        }
        else if(listType == 4 || listType == 14){
            apiReq.put("handOutFlag","0");// 4未提交苏宁贷款      14查的也是相同的数据，评估通过列表
        }else if(listType == 5){
            apiReq.put("handOutFlag","1");//已提交苏宁贷款的
        }else if(listType == 6){
            apiReq.put("listType",6);//   索赔状态未通过   和  未发起的            in(0,3)
            apiReq.put("caseState",2);//已接收
        }else if(listType == 7){
            apiReq.put("claimState",2);//索赔方案审核中
        }else if (listType == 77){
            apiReq.put("listType",77);//未发起结案报告  和 结案报告审核不通过   0,3
        }else if(listType == 777){
            apiReq.put("closeReportState",2);// 结案报告审核中的
        }else if(listType == 8){
            apiReq.put("handInFlag","0");//未发起代扣的
            apiReq.put("closeReportState",1);//结案报告审核通过的
        }else if(listType == 9){
            apiReq.put("listType",9);//    0未申请结案的    3结案不通过的  sql  closeState in (0,3)
        }else if(listType == 10){
            apiReq.put("closedState",1);//结案审核中
        }else if(listType == 11){
            apiReq.put("closedState",2);//结案通过的
        }else if(listType == 12){
            apiReq.put("closedState",2);//结案通过的
        }else if (listType == 13){
            //所有案件都可以紧急代扣
        }else if (listType == 20){
            apiReq.put("orgUserId",operatorId);//当前用户
            apiReq.put("caseState",1);//用户待接收案件
        }
        int count = caseCenterInfoMapper.findListSize(apiReq);
        List<CaseCenterInfoDto> list = caseCenterInfoMapper.findList(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }
//    /**
//     * 案件列表操作
//     * @param apiRequest
//     * @return
//     */
//    @Override
//    public ApiResponse operate(ApiRequest apiRequest){
//        //listType 列表类型
//        //评估案件列表  0评估列表、1待评估列表
//        //案件风控     2一审待审核列表、3二审待审核列表
//        //案件风控     4待提交苏宁贷款、5已提交苏宁贷款
//        //索赔案件列表 6待索赔列表、
//        //案件风控    7索赔预案审核列表
//        //索赔案件列表 77预案通过列表
//        //案件风控     777结案报告审核
//        //索赔案件列表  8需代扣列表、9案件列表、10待结案列表、11结案列表
//        //财务管理     12已结案案件
//        //案件管理     13紧急代扣
//        //案件管理     20用户待接收案件
//        Long id = apiRequest.getLong("id");//案件中心ID
//        Long operatorId = apiRequest.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
//        Long listType = apiRequest.getLong("listType");//列表类型
//        String operateType = apiRequest.getString("operateType");//操作类型   pass 通过   back 退回
//        String reason = apiRequest.getString("reason");//退回原因
//        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(id);
//        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(operatorId);// 登录人
//
//        if (1 == listType){
//            caseCenterInfo.setIssuanceState(5);//评估员提交
//        }else if(2 == listType){
//            if ("pass".equals(operateType)){
//                caseCenterInfo.setIssuanceState(1);//一审通过
//            }else if("back".equals(operateType)){
//                caseCenterInfo.setIssuanceState(2);//一审不通过
//                caseCenterInfo.setIssuanceReason(reason);
//            }
//        }else if (3 == listType){
//            if ("pass".equals(operateType)){
//                caseCenterInfo.setIssuanceState(3);//二审通过
//                caseCenterInfo.setHandOutFlag("0");//未提交苏宁贷款
//            }else if("back".equals(operateType)){
//                caseCenterInfo.setIssuanceState(4);//二审不通过
//                caseCenterInfo.setIssuanceReason(reason);
//            }
//        }else if (4 == listType){
//            caseCenterInfo.setHandOutFlag("1");//已提交苏宁贷款
//        }else if (5 == listType || listType == 13){
//            Map map = new HashMap();
//            map.put("caseId",caseCenterInfo.getCaseId());
//            map.put("caseType",caseCenterInfo.getType());
//            PaymentEstimateInquiryDto dto = caseCenterInfoMapper.selectNewestPayInquiry(map);//最新测算信息
//            Boolean isSuccess = false;//默认都是不成功的
//            String withholdType = apiRequest.getString("withholdType");//代扣方式   1现金  2代扣  3转账
//            Double amount = apiRequest.getDouble("amount");//金额
//            Date handOutTime = DateUtils.parseDate(apiRequest.getString("handOutTime"), "yyyy-MM-dd");//放款时间
//            if ("1".equals(withholdType) || "3".equals(withholdType)){
//                //不对接苏宁  生成代扣成功的纪录
//                SuningWithholdApply apply = new SuningWithholdApply();
//                apply.setCaseId(caseCenterInfo.getId());
//                apply.setCaseNo(caseCenterInfo.getCaseNo());
//                apply.setCaseTitle(caseCenterInfo.getCaseTitle());
//                apply.setWithholdMoney(amount);
//                apply.setWithholdTime(handOutTime);
//                apply.setWithholdState(2);//代扣成功
//                apply.setWithholdType(Integer.parseInt(withholdType));
//                apply.setAppUserId(operatorId);
//                apply.setApplyType(listType == 5 ? 0 : 2);
//                suningWithholdApplyMapper.insert(apply);
//                isSuccess = true;
//            }else if ("2".equals(withholdType)){
//                //苏宁代扣
////                CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByUserId(operatorId);
//                CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByCaseId(caseCenterInfo.getId());
//                if (cardInfoDto == null){
//                    return new ApiResponse(ApiMsgEnum.CASE_SUNING_HANDOUT_CARD);
//                }
//                String path = apiRequest.getRes().getSession().getServletContext().getRealPath("/");
//                isSuccess = suningApi.suningProxy(cardInfoDto,amount,caseCenterInfo,false,2,operatorId,null,path,listType == 5 ? 0 : 2);
//            }
//
//            //成功之后保存开票表  以及修改案件状态表
//            if (isSuccess){
//                //保存开票申请
//                BillingApply billingApply = new BillingApply();
//                billingApply.setCaseId(caseCenterInfo.getId());
//                billingApply.setCaseNo(caseCenterInfo.getCaseNo());
//                billingApply.setCaseTitle(caseCenterInfo.getCaseTitle());
//                if (dto != null){
//                    billingApply.setServcieMoney(dto.getAgentServiceFee());
//                    billingApply.setChannelMoney(dto.getLoanFee());
//                    billingApply.setInsuranceMoney(dto.getInsuranceFee());
//                    billingApply.setDeductionMoney(amount);
//                    billingApply.setBillingMoney((dto.getAgentServiceFee() == null ? 0D : dto.getAgentServiceFee()) + (dto.getLoanFee() == null ? 0D : dto.getLoanFee()));
//                }
//                billingApply.setBillingState(0);
//                billingApply.setBillingTime(new Date());
//                suningApi.saveBillingApply(billingApply);
//                caseCenterInfo.setHandOutFlag("2");//苏宁已确认(代扣成功)
//                caseCenterInfo.setHandOutTime(handOutTime);
//                //紧急代扣不改变案件状态
//                if (listType != 13){
//                    caseCenterInfo.setGradationState(3);//改为索赔阶段
//                    caseCenterInfo.setClaimState(3);//索赔方案状态审核  3未发起
//                    caseCenterInfo.setCaseState(1);//待接收
//                    caseCenterInfo.setCaseStateStr("待接收");
//                }
//            }else{
//                return new ApiResponse(ApiMsgEnum.FAIL);
//            }
//        }else if (6 == listType){
//            caseCenterInfo.setClaimState(2);//索赔方案  审核中
//        }else if(7 == listType){
//            //更新索赔方案预案审核信息  审核意见 审核人 审核时间
//            CaseMediationClaim caseMediationClaim = caseMediationClaimMapper.queryByCaseId(caseCenterInfo.getId());
//            if (caseMediationClaim != null){
//                caseMediationClaim.setPlanReviewDesc(reason);
//                caseMediationClaim.setPlanReviewPerson(userInfo.getUserName());
//                caseMediationClaim.setPlanReviewTime(new Date());
//                caseMediationClaimMapper.updateByPrimaryKeySelective(caseMediationClaim);
//            }
//            if ("pass".equals(operateType)){
//                caseCenterInfo.setClaimState(1);//索赔方案  审核通过
//                caseCenterInfo.setCloseReportState(3);//结案报告  [未发起结案报告]
//                //索赔预案审核通过                发送短信
//                try {
//                    SendMessageUntil.caseToMessage(caseCenterInfo.getCaseTel(),caseCenterInfo.getCaseName(),"1368");
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }else if("back".equals(operateType)) {
//                caseCenterInfo.setClaimState(0);//索赔方案  未通过审核
//                caseCenterInfo.setClaimReason(reason);
//            }
//        }
//        //增加结案报告审核的流程(77)
//        else if(77 == listType){
//            caseCenterInfo.setCloseReportState(2);//结案报告审核中
//        }else if(777 == listType){
//            if ("pass".equals(operateType)){
//                caseCenterInfo.setCloseReportState(1);//结案报告审核通过
//                caseCenterInfo.setHandInFlag("0");//未发起代扣
//            }else if ("back".equals(operateType)){
//                caseCenterInfo.setCloseReportState(0);//结案报告审核未通过
//            }
//        }
//        else if (8 == listType){
//            //需代扣清单的  发起代扣  提交走suning api 提交方法
//            // 已发起代扣之后,由财务确认代扣之后 将申请结案状态改为0(未申请结案),代扣状态改为2 代扣已确认
//        }else if (9 == listType){
//            caseCenterInfo.setClosedState(1);//结案审核中
//        }else if (10 == listType){
//            if ("pass".equals(operateType)){
//                caseCenterInfo.setClosedState(2);// 结案通过
//                caseCenterInfo.setGradationState(4);
//                //结案审核通过   发送短信
//                try {
//                    SendMessageUntil.caseToMessage(caseCenterInfo.getCaseTel(),caseCenterInfo.getCaseName(),"1370");
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }else if("back".equals(operateType)) {
//                caseCenterInfo.setClosedState(3);//结案不通过
//                caseCenterInfo.setClosedReason(reason);
//            }
//        }else if(12 == listType){
//            Map map = new HashMap();
//            map.put("caseId",caseCenterInfo.getCaseId());
//            map.put("caseType",caseCenterInfo.getType());
//            Double amount = apiRequest.getDouble("amount");
//            PaymentEstimateInquiryDto dto = caseCenterInfoMapper.selectNewestPayInquiry(map);
//            //开票 OR  补票
//            //保存开票申请
//            BillingApply billingApply = new BillingApply();
//            billingApply.setCaseId(caseCenterInfo.getId());
//            billingApply.setCaseNo(caseCenterInfo.getCaseNo());
//            billingApply.setCaseTitle(caseCenterInfo.getCaseTitle());
//            billingApply.setServcieMoney(dto.getAgentServiceFee());
//            billingApply.setChannelMoney(dto.getLoanFee());
//            billingApply.setInsuranceMoney(dto.getInsuranceFee());
//            billingApply.setDeductionMoney(amount);
//            billingApply.setBillingMoney((dto.getAgentServiceFee() == null ? 0D : dto.getAgentServiceFee()) + (dto.getLoanFee() == null ? 0D : dto.getLoanFee()));
//            billingApply.setBillingState(1);
//            billingApply.setBillingTime(new Date());
//            suningApi.saveBillingApply(billingApply);
//        }else if (14 == listType){
//            caseCenterInfo.setIssuanceState(5);//评估员再次审核
//            caseCenterInfo.setHandOutFlag("-1"); // 无实际意义 ，表示改案件在待提交苏宁代扣查询不到
//        }else if (20 == listType){
//            if (caseCenterInfo.getGradationState() == 1){//洽谈阶段的接收
//                if ("pass".equals(operateType)){
//                    Double money = promotionOutlayMapper.selectSumMoneyByCaseNo(caseCenterInfo.getCaseNo());   //首先查询案件的推广总费用
//                    if (money == null){
//                        DistributionBasic dis = distributionBasicMapper.selectDistributionBasic();       //如果没有推广总费用，查询固定扣款费用
//                        money = dis.getCaseMoney();
//                    }
//                    HashMap<String,Object> paramMap = new HashMap<>();
//                    paramMap.put("userId",operatorId);
//                    Long orgId = userInfo.getOrgId();
//                    if (userInfo == null || orgId == null){
//                        //未找到用户机构信息
//                        return new ApiResponse(ApiMsgEnum.CASE_ACCECP_ORG);
//                    }
//                    while (true){
//                        OrgInfo orgInfo = orgInfoMapper.selectByPrimaryKey(orgId);
//                        if(orgInfo.getOrgParentid() == 1){
//                            break;
//                        }
//                        orgId = orgInfo.getOrgParentid();
//                    }
//                    OrgAccount orgAccount = orgAccountMapper.selectByPrimaryKey(orgId);   //根据机构ID查询机构信息
//                    if (orgAccount.getUserForegift() != null && orgAccount.getUserForegift() > 0){
//                        if (orgAccount.getUserUsable() < money){
//                            //余额不足
//                            return new ApiResponse(ApiMsgEnum.CASE_ACCECP_AMONT);
//                        }else {
//                            if (caseCenterInfo.getGradationState() == 1){
//                                Boolean b = updateOrgAccount(userInfo.getOrgId(),operatorId,money,2,"案源消费支出("+caseCenterInfo.getCaseName()+")",2);  //开始修改机构账户余额
//                                if (!b){
//                                    //机构账户金额更新失败
//                                    return new ApiResponse(ApiMsgEnum.CASE_ACCECP_UPD_AMOUNT);
//                                }
//                            }
//                        }
//                    }
//                    caseCenterInfo.setCaseState(2);
//                    caseCenterInfo.setCaseStateStr("已接收");
//                    caseCenterInfo.setUpdateTime(new Date());
//                }else if ("back".equals(operateType)){
//                    caseCenterInfo.setCaseState(30);
//                    caseCenterInfo.setCaseStateStr("已拒绝");
//                    caseCenterInfo.setUpdateTime(new Date());
//                }
//            }else if (caseCenterInfo.getGradationState() == 2 || caseCenterInfo.getGradationState() == 3){//评估阶段或索赔阶段的接收
//                if ("pass".equals(operateType)){
//                    //索赔员接收案件(索赔阶段接收案件) 发送短信
//                    if (caseCenterInfo.getGradationState() == 3){
//                        try {
//                            SendMessageUntil.caseToMessage(caseCenterInfo.getCaseTel(),caseCenterInfo.getCaseName(),userInfo.getUserName(),userInfo.getUserTel(),"1366");
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                    caseCenterInfo.setCaseState(2);
//                    caseCenterInfo.setCaseStateStr("已接收");
//                    caseCenterInfo.setUpdateTime(new Date());
//                }else if ("back".equals(operateType)){
//                    caseCenterInfo.setCaseState(30);
//                    caseCenterInfo.setCaseStateStr("已拒绝");
//                    caseCenterInfo.setUpdateTime(new Date());
//                }
//            }
//        }
//
//        //评估案件列表  0评估列表、1待评估列表
//        //案件风控     2一审待审核列表、3二审待审核列表
//        //案件风控     4待提交苏宁贷款、5已提交苏宁贷款
//        //索赔案件列表 6待索赔列表、
//        //案件风控    7索赔预案审核列表
//        //索赔案件列表 77预案通过列表
//        //案件风控     777结案报告审核
//        //索赔案件列表  8需代扣列表、9案件列表、10待结案列表、11结案列表
//        //财务管理     12已结案案件
//        //案件管理     13紧急代扣
//        //评估案件列表 14评估通过列表
//        String caseStateName = "";
//        switch (Integer.parseInt(listType.toString())){
//            case 1: caseStateName = "一审待审核"; break;
//            case 2:
//                if ("pass".equals(operateType)){
//                    caseStateName = "二审待审核";
//                }else if ("back".equals(operateType)){
//                    caseStateName = "待评估";
//                }
//                break;
//            case 3:
//                if ("pass".equals(operateType)){
//                    caseStateName = "待提交苏宁贷款";
//                }else if ("back".equals(operateType)){
//                    caseStateName = "待评估";
//                }
//                break;
//            case 4: caseStateName = "已提交苏宁贷款"; break;
//            case 5: caseStateName = "索赔员待接收";break;
//            case 6: caseStateName = "索赔方案审核中";break;
//            case 7:
//                if ("pass".equals(operateType)){
//                    caseStateName = "结案报告待发起";
//                }else if ("back".equals(operateType)){
//                    caseStateName = "重新提交索赔方案";
//                }
//                break;
//            case 77: caseStateName = "结案报告审核中";break;
//            case 777:
//                if ("pass".equals(operateType)){
//                    caseStateName = "待发起还款";
//                }else if ("back".equals(operateType)){
//                    caseStateName = "重新提交结案报告";
//                }
//                break;
//            case 9: caseStateName = "结案报告审核中";break;
//            case 10:
//                if ("pass".equals(operateType)){
//                    caseStateName = "结案通过";
//                }else if ("back".equals(operateType)){
//                    caseStateName = "结案不通过";
//                }
//                break;
//            case 14: caseStateName = "评估员再次审核";break;
//            case 20:
//                if ("pass".equals(operateType)){
//                    caseStateName = "已接收";
//                }else if ("back".equals(operateType)){
//                    caseStateName = "已拒绝";
//                }
//                break;
//        }
//        Boolean b = messageApiImpl.addMessageOrFollowByUpdCaseState(userInfo.getUserId(),userInfo.getUserName(),caseCenterInfo,caseStateName,reason);
//        //保存退回原因
//        if ("back".equals(operateType)){
//            caseCenterInfo.setOperReason(reason);
//        }else{
//            caseCenterInfo.setOperReason(null);
//        }
//        int ret = caseCenterInfoMapper.updateByPrimaryKey(caseCenterInfo);
//        if (ret < 0){
//            return new ApiResponse(ApiMsgEnum.FAIL);
//        }
//        return new ApiResponse(ApiMsgEnum.SUCCESS);
//    }

    //机构账号金额扣除和增加
    private  boolean  updateOrgAccount(Long orgId,Long userId,Double money,Integer type,String title,Integer genre){
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("orgId",orgId);
        paramMap.put("money",money);
        boolean flag=false;
        try{
            //type =1收入，type =2支出
            if(type==1){
                orgAccountMapper.addMoneyOrgAccountByOrgId(paramMap);
                //添加收支明细
            }else if(type==2){
                orgAccountMapper.lessMoneyOrgAccountByOrgId(paramMap);
                //添加收支明细
            }
            OrgInfo orgInfo=orgInfoMapper.selectByPrimaryKey(orgId);
            Map<String, Object> paramMap1=new HashMap<String, Object>();
            paramMap1.put("userId",userId);
            UserInfo userInfo=userInfoMapper.selectUserInfoByUserId(paramMap1);
            OrgAccountDetail orgAccountDetail=new OrgAccountDetail();
            orgAccountDetail.setOrgName(orgInfo.getOrgName());
            orgAccountDetail.setUserName(userInfo.getUserName());
            orgAccountDetail.setOrgId(orgId);
            orgAccountDetail.setUserId(userId);
            orgAccountDetail.setType(type);
            orgAccountDetail.setTitle(title);
            orgAccountDetail.setMoney(money);
            orgAccountDetail.setCreateTime(new Date());
            orgAccountDetail.setGenre(genre);
            orgAccountDetailMapper.insertSelective(orgAccountDetail);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
            return flag;
        }
        return flag;
    }



    @ApiMethod(descript = "获取最新报价信息",value = "backend-get-pay-inquiry-info")
    @Override
    public ApiResponse selectNewestPayInquiry(ApiRequest apiRequest) {
        Long id = apiRequest.getLong("id");
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(id);
        Map<String,Object> map = new HashMap();
        map.put("caseId",caseCenterInfo.getCaseId());
        map.put("caseType",caseCenterInfo.getType());
        Long applyId = apiRequest.getLong("applyId");
        if (applyId != null){
            SuningWithholdApply apply = suningWithholdApplyMapper.selectByPrimaryKey(applyId);
            if (apply != null && 1 == apply.getApplyType()){
                apiRequest.put("withholdApply","withholdApply");
            }
        }
        PaymentEstimateInquiryDto dto = caseCenterInfoMapper.selectNewestPayInquiryNew(caseCenterInfo.getId());
        //工商事故案件
//        if (caseCenterInfo.getType() == 2){
//            AgentApply agentApplyInfo = agentApplyMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
//            if (agentApplyInfo.getAgentType() == 2){
//                dto = caseCenterInfoMapper.selectNewestPayInquiryNew(caseCenterInfo.getId());
//                dto.setIsWorkInjury(true);
//            }
//        }
        if (dto != null){
            dto.setCaseType(caseCenterInfo.getType());
            Object withholdApply = apiRequest.get("withholdApply");
            CaseRiskControl caseRisk = caseRiskControlMapper.selectByCaseId(id);
            if(caseRisk!=null){
                dto.setOkAssessAmount(caseRisk.getLoanMoney()==null?0D:caseRisk.getLoanMoney());//
            }

            dto.setAmount(dto.getTotalDeFee());//默认显示的扣款费用
            if ("withholdApply".equals(withholdApply)){//表示 还款查看的最新报价信息
                //计算扣款费用
                CaseClosedReport caseClosedReport = caseClosedReportMapper.selectByCaseId(caseCenterInfo.getId());
//                Integer days = caseClosedReport.getLoanCycle() == null ? 0 : caseClosedReport.getLoanCycle();//实际贷款周期
                Double a = caseClosedReport.getFixedMoney() == null ? 0D : caseClosedReport.getFixedMoney();//固定收费
                Double b = caseClosedReport.getProMoney() == null ? 0D : caseClosedReport.getProMoney();//比列收费 ,  收费比列
                Double c = dto.getPayableFee() == null ? 0D : dto.getPayableFee();//理赔款
                Double d = dto.getRealDeFee() == null ? 0D : dto.getRealDeFee();//实际扣费
                Double e = caseClosedReport.getLoanMoney() == null ? 0D : caseClosedReport.getLoanMoney();//本金   (贷款金额)
                //页面显示数据
                dto.setLaveServiceAmount(DecimalUtil.twoDecimalTOFourFromFive(caseClosedReport.getSurplusTotalMoney() == null ? 0D : caseClosedReport.getSurplusTotalMoney()));//去结案报告的剩余应收服务费总额
//                dto.setLaveServiceAmount(DecimalUtil.twoDecimalTOFourFromFive(a + b/100 * c - d));
                Date outTime = caseCenterInfo.getHandOutTime() == null ? new Date() : caseCenterInfo.getHandOutTime();
                Date currentDate = new Date();
                int days = (int)(currentDate.getTime() - outTime.getTime()) / (1000 * 3600 * 24) + 3;
                dto.setCycleDays(days);
                dto.setAssetsAmount(DecimalUtil.twoDecimalTOFourFromFive(e));
                dto.setInterstAmount(DecimalUtil.twoDecimalTOFourFromFive(e * 0.0002778 * days));
                Double amount = DecimalUtil.twoDecimalTOFourFromFive(dto.getLaveServiceAmount() + e + e * 0.0002778 * days);
                dto.setAmount(amount);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,dto);
    }



    @ApiMethod(descript = "后台案件中心列表" ,value = "backend-case-list")
    @Override
    public ApiResponse oldList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        Integer operatorId = apiReq.getInt("operatorId");
        apiReq.put("insOfficerId",operatorId);
        int count = caseCenterInfoMapper.selectCountCaseCenterInfoByParam(apiReq);
        List<CaseCenterInfo> list = caseCenterInfoMapper.selectCaseCenterInfoByParam(apiReq);
        return  new ApiResponse<List<CaseCenterInfo>>(ApiMsgEnum.SUCCESS,count,list);
    }

    @ApiMethod(descript = "查看案件中心资料枚举",value = "backend-case-file-enum")
    @Override
    public ApiResponse selectCaseFileEnum(ApiRequest apiReq) {
        List<CommonEnum> enums = commonEnumMapper.selectFileCatelogState();
        for (int i = 0; i < enums.size(); i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("caseNo",apiReq.getString("caseNo"));
            map.put("catalogId",enums.get(i).getEnumCode());
            List<CaseFileMid> mids = caseFileMidMapper.selectByCaseIdAndCatalogId(map);
            enums.get(i).setNum(mids==null?0:mids.size());
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,enums == null ? 0 : enums.size() , enums);
    }

    @ApiMethod(descript = "查看案件中心资料",value = "backend-case-file")
    @Override
    public ApiResponse selectCaseFile(ApiRequest apiReq) {
        String caseNo = apiReq.getString("caseNo");
        String catalogId = apiReq.getString("catalogId");
        Long enumId = apiReq.getLong("enumId");
        HashMap<String,Object> paramMap = new HashMap<>();
        paramMap.put("caseNo",caseNo);
        paramMap.put("catalogId",catalogId);
        List<CaseFileMid> caseFileMids = caseFileMidMapper.selectByCaseIdAndCatalogId(paramMap);
        for (CaseFileMid caseFileMid : caseFileMids){
            CommonFile commonFile = commonFileMapper.selectByPrimaryKey(caseFileMid.getFileId());
            caseFileMid.setCommonFile(commonFile);

            //如果材料是pdf文件，返回1
            int lastNamePdf = commonFile.getFilePath().lastIndexOf(".") + 1;
            String urlName =commonFile.getFilePath().substring(lastNamePdf);
            if("pdf".equals(urlName)){
                caseFileMid.setType(1);
            }else{
                caseFileMid.setType(2);
            }
            //是否可下载（原因：部分图片是在32服务器上，现在项目更改部署，放在了47上，通过流的方式无法下载。2019年6月13日11:36:05）
            if(commonFile.getFilePath().indexOf("openapi.shlefan.com") > 1){
                caseFileMid.setIsDownLoad(false);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,caseFileMids == null ?0 :caseFileMids.size(),caseFileMids);
    }

    @ApiMethod(descript = "根据枚举查看单证",value = "backend-case-file-address")
    @Override
    public ApiResponse selectFilesAddress(ApiRequest apiReq) {
        List<HashMap<String,Object>> list = caseFileMidMapper.selectFilesAddress(apiReq);
        for (int i = 0; i < list.size(); i++) {
            //如果材料是pdf文件，返回1
            String filePath = list.get(i).get("filePath").toString();
            //如果材料是pdf文件，返回1
            int lastNamePdf = filePath.lastIndexOf(".") + 1;
            String urlName = filePath.substring(lastNamePdf);
            if("pdf".equals(urlName)){
                list.get(i).put("type",1);
            }else{
                list.get(i).put("type",2);
            }
            //是否可下载（原因：部分图片是在32服务器上，现在项目更改部署，放在了47上，通过流的方式无法下载。2019年6月13日11:36:05）
            if(filePath.indexOf("openapi.shlefan.com") > 1){
                list.get(i).put("isDownLoad",false);
            }else{
                list.get(i).put("isDownLoad",true);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,list==null?0:list.size(),list);
    }

    @ApiMethod(descript = "案件详情与跟踪记录", value = "backend-select-caseDetails")
    @Override
    public ApiResponse selectCaseDetails(ApiRequest apiReq) {
        Integer type = apiReq.getInt("type");
        Integer caseType =apiReq.getInt("caseType");
        Long caseId = apiReq.getLong("caseId");
        BackendCaseDetailsDto bcdd = new BackendCaseDetailsDto();
//        //根据案件类型（type）查询不同的申请表 1.贷款申请2.代理申请
//        if(1==type){
//            //查询贷款申请表案件信息
//            LoanApplication loanApplication = loanApplicationMapper.selectByPrimaryKey(caseId);
//            String provinceId = loanApplication.getAccidentProvince();
//            String city = loanApplication.getAccidentCity();
//            String district = loanApplication.getAccidentDistrict();
//            Map<String, Object> paramMap = new HashMap<String, Object>();
//            String[] aredIdArray =new String[]{provinceId,city,district};
//            paramMap.put("areaList",aredIdArray);
//            List<CommonArea>  commonAreas = commonAreaMapper.selectAreaByAreaIdList(paramMap);
//            for(CommonArea commonArea:commonAreas){
//                if(commonArea.getAreaType()==1)
//                    loanApplication.setAccidentProvince(commonArea.getAreaName());
//                else if(commonArea.getAreaType()==2)
//                    loanApplication.setAccidentCity(commonArea.getAreaName());
//                else  if(commonArea.getAreaType()==3)
//                    loanApplication.setAccidentDistrict(commonArea.getAreaName());
//            }
//            bcdd.setLoanApplication(loanApplication);
//        }else{
//            //查询代理申请表案件信息
//            bcdd.setAgentApply(agentApplyMapper.selectByPrimaryKey(caseId));
//        }
        //根据跟踪类型（caseType）的不同查询不同的跟踪信息表1.案件跟踪信息表2.客服跟踪记录
        if(1==caseType){
            //查询案件跟踪信息表
            List<CaseFollowInfo> caseFollowInfoList = caseFollowInfoMapper.queryCaseFollowByCaseNo(apiReq);
            for(int i=0;i<caseFollowInfoList.size();i++){
                List<CaseFollowInfoFile> caseFollowInfoFiles = caseFollowInfoFileMapper.selectByFollowInfoId(caseFollowInfoList.get(i).getId());
                caseFollowInfoList.get(i).setList(caseFollowInfoFiles);
            }
            bcdd.setDetails(caseFollowInfoList);
        }else{
            //查询客服跟踪记录表
            bcdd.setDetails(operatorFollowInfoMapper.selectOperatorFollowInfo(apiReq));
        }
        return new ApiResponse<BackendCaseDetailsDto>(ApiMsgEnum.SUCCESS, (bcdd == null ? 0 : 1), bcdd);
    }



    /**
     * 超时案件列表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "超时案件列表", value = "backend-case-overtimeList")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse overtimeList(ApiRequest apiReq) {

        this.setBackendPageSize(apiReq);
        String overTimeType = apiReq.getString("overTimeType");

        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(apiReq.getLong("operatorId"));
        String isTest =null;
        if(userInfo != null){
            if(userInfo.getIsTester() != 1){
                //不是测试人员：默认查询非测试案件
                isTest ="0";
            }else{
                //测试人员:默认查询测试案件
                isTest ="1";
            }
        }

        int count = 0;
        List<CaseCenterInfoDto> allList = new ArrayList<>();

        if("1".equals(overTimeType) ){
            //overTimeType ==1 超时未接收案件
            apiReq.put("overTimeType",overTimeType);
            apiReq.put("isTest",isTest);
            count += caseCenterInfoMapper.selectCountFour(apiReq) ;
            List<CaseCenterInfoDto> list = caseCenterInfoMapper.selectFour(apiReq);
            allList.addAll(list);
        }
        if("2".equals(overTimeType) ){
            //overTimeType ==2  未跟踪案件
            apiReq.put("overTimeType",overTimeType);
            apiReq.put("isTest",isTest);
            count += caseCenterInfoMapper.selectCountFour(apiReq);
            List<CaseCenterInfoDto> list = caseCenterInfoMapper.selectFour(apiReq);
            allList.addAll(list);
        }
        if("3".equals(overTimeType) ){
            //overTimeType ==3  状态未变更（update_time超过30天）
            apiReq.put("overTimeType",overTimeType);
            apiReq.put("isTest",isTest);
            count += caseCenterInfoMapper.selectCountFour(apiReq) ;
            List<CaseCenterInfoDto> list = caseCenterInfoMapper.selectFour(apiReq);
            allList.addAll(list);
        }
        if("4".equals(overTimeType) ){
            //overTimeType ==4  超时未签约（create_time 超过7天）
            apiReq.put("overTimeType",overTimeType);
            apiReq.put("isTest",isTest);
            count += caseCenterInfoMapper.selectCountFour(apiReq) ;
            List<CaseCenterInfoDto> list = caseCenterInfoMapper.selectFour(apiReq);
            allList.addAll(list);
        }
        if(overTimeType == null){
            //overTimeType 为空时，查询以上四个情况的所有案件
            apiReq.put("overTimeType",overTimeType);
            apiReq.put("isTest",isTest);
            count += caseCenterInfoMapper.selectCountFour(apiReq) ;
            List<CaseCenterInfoDto> list = caseCenterInfoMapper.selectFour(apiReq);
            allList.addAll(list);
        }

        return new ApiResponse<List<CaseCenterInfoDto>>(ApiMsgEnum.SUCCESS, count, allList);
    }

    /**
     * 还款清单列表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "还款清单列表" ,value = "backend-case-center-repay-list")
    @Override
    public ApiResponse<List<CaseCenterInfo>> repayCaseCenterInfoList(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        //根据当前登录人是否是测试人员
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(apiReq.getLong("operatorId"));
        if(userInfo != null){
            if(userInfo.getIsTester() != 1){
                //不是测试人员：默认查询非测试案件
                apiReq.put("isTest",0);
            }else{
                //测试人员:默认查询测试案件
                apiReq.put("isTest",1);
            }
        }
        int count = caseCenterInfoMapper.selectCaseCenterInfoForRepaySize(apiReq);
        List<CaseCenterInfo> list = caseCenterInfoMapper.selectCaseCenterInfoForRepay(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    /**
     * 还款清单的详情页面
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "还款清单的详情页面" ,value = "backend-case-center-for-repay")
    @Override
    public ApiResponse<CaseCenterInfo> searchCaseCenterInfoForRepay(ApiRequest apiReq){
        Long id = apiReq.getLong("id");
        this.setBackendPageSize(apiReq);
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(id);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,null,caseCenterInfo);
    }


    @ApiMethod(descript = "操作还款确认",value = "backend-haldle-repay-case-center-info-upd")
    @Override
    public ApiResponse updHaldleRepayCaseCenterInfo(ApiRequest request) {
        Long id = request.getLong("id");
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(id);

        if (caseCenterInfo != null){
            //已操作还款
            caseCenterInfo.setHandOutFlag("5");
            int ret = caseCenterInfoMapper.updateByPrimaryKey(caseCenterInfo);
            if (ret <0){
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }

        CaseCenterExtend caseCenterExtend = caseCenterExtendMapper.selectByPrimaryKey(id);
        if(caseCenterExtend != null){
            //并将上传的“支付凭证”保存
            String img = request.getString("img");
            caseCenterExtend.setRepayImg(img);
            caseCenterExtend.setRepay(1);
            int ret =caseCenterExtendMapper.updateByPrimaryKey(caseCenterExtend);
            if (ret <0){
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }


    /**
     * 还款确认列表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "还款确认列表" ,value = "backend-case-center-confirm-repay-list")
    @Override
    public ApiResponse<List<CaseCenterInfo>> confirmRepayCaseCenterInfoList(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        //根据当前登录人是否是测试人员
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(apiReq.getLong("operatorId"));
        if(userInfo != null){
            if(userInfo.getIsTester() != 1){
                //不是测试人员：默认查询非测试案件
                apiReq.put("isTest",0);
            }else{
                //测试人员:默认查询测试案件
                apiReq.put("isTest",1);
            }
        }
        int count = caseCenterInfoMapper.selectConfirmRepayCaseCenterInfoSize(apiReq);
        List<CaseCenterInfo> list = caseCenterInfoMapper.selectConfirmRepayCaseCenterInfo(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    /**
     * 还款确认的详情页面
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "还款清单的详情页面" ,value = "backend-case-center-for-repay")
    @Override
    public ApiResponse<CaseCenterInfo> searchCaseCenterInfoForConfirmRepay(ApiRequest apiReq){
        Long id = apiReq.getLong("id");
        this.setBackendPageSize(apiReq);
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(id);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,null,caseCenterInfo);
    }

    @ApiMethod(descript = "确认通过还款",value = "backend-case-center-confirm-repay-upd")
    @Override
    public ApiResponse updCaseCenterInfoConfirmRepay(ApiRequest request) {
        Long id = request.getLong("id");
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(id);

        if (caseCenterInfo != null){
            //确认通过还款
            caseCenterInfo.setHandOutFlag("6");
            int ret = caseCenterInfoMapper.updateByPrimaryKey(caseCenterInfo);
            if (ret <0){
                return new ApiResponse(ApiMsgEnum.FAIL);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 查询机构信息，供案件分配
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "查询机构信息，供案件分配", value = "backend-case-select-orgInfo")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse selectOrgInfo(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        int count = orgInfoMapper.queryCountOrgListBackend(apiReq);
        List<OrgInfo> list = orgInfoMapper.queryOrgListBackend(apiReq);
        return new ApiResponse<List<OrgInfo>>(ApiMsgEnum.SUCCESS, count, list);
    }

    @ApiMethod(descript = "确认服务费",value = "backend-case-ok-service-fee")
    @Override
    public ApiResponse okServiceFee(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(id);
        Map<String,Object> map = new HashMap();
        map.put("caseId",caseCenterInfo.getCaseId());
        map.put("caseType",caseCenterInfo.getType());
        PaymentEstimateInquiryDto dto = caseCenterInfoMapper.selectNewestPayInquiryNew(caseCenterInfo.getId());
        if (dto != null){
            Double stillNeedFee = dto.getStillNeedFee() == null ? 0 : dto.getStillNeedFee();
            Double appDiscountLoanFee = dto.getApplyDiscountLoanFee() == null ? 0 : dto.getApplyDiscountLoanFee();
            Double amount = DecimalUtil.twoDecimalTOFourFromFive(((appDiscountLoanFee + stillNeedFee)*0.0002778*365 + (appDiscountLoanFee + stillNeedFee))*0.03);
            dto.setAmount(amount);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,dto);
    }


    @ApiMethod(descript = "保存上传单证图片",value = "backend-upload-common-file-img")
    @Override
    public ApiResponse uploadCommonFileImg(ApiRequest apiReq) {
        String caseNo = apiReq.getString("caseNo");
        Long caseId = apiReq.getLong("caseId");
        Long catalogId = apiReq.getLong("catalogId");
        String catalogName = apiReq.getString("catalogName");

        //保存“文件资料表”(单个文件上传)
//        CommonFile commonFile = new CommonFile();
//        commonFile.setFilePath(imgUrl);
//        int firstName = imgUrl.lastIndexOf("\\") + 1 ;
//        int lastName = imgUrl.lastIndexOf(".");
//        String name = imgUrl.substring(firstName,lastName);
//        commonFile.setFileName(name);
//        commonFile.setCreateTime(new Date());
//        commonFileMapper.insertSelective(commonFile);

        //案件文件表
        CaseFileMid caseFileMid = new CaseFileMid();
        caseFileMid.setCaseId(caseId);
        caseFileMid.setCaseNo(caseNo);
        caseFileMid.setCatalogId(catalogId);
        caseFileMid.setCatalogName(catalogName);

        String strUrl = apiReq.getString("img");
        if (strUrl != null) {
            String[] urls = strUrl.split(",");
            for (String url : urls) {
                CommonFile commonFile = new CommonFile();
                commonFile.setFilePath(url);
                int firstName = url.lastIndexOf("/") + 1 ;
                int lastName = url.lastIndexOf(".");
                String name = url.substring(firstName,lastName);
                commonFile.setFileName(name);
                commonFile.setCreateTime(new Date());
                commonFileMapper.insertSelective(commonFile);

                //保存“案件文件表”
                caseFileMid.setFileId(commonFile.getId());
                caseFileMidMapper.insertSelective(caseFileMid);
            }
        }

        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

}
