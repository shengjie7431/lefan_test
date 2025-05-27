package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseMediationClaimApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.PaymentEstimateInquiryDto;
import com.lefancrm.apicenter.enums.ReportProjectEnum;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.RedisService;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.apicenter.util.PaymentAlgorithmUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 索赔方案
 * Created by lixianfeng on 2018/5/2.
 */
@ApiService(descript = "索赔方案API")
@Service
public class BackendCaseMediationClaimApiImpl implements BackendCaseMediationClaimApi{
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private CaseMediationClaimReportMapper caseMediationClaimReportMapper;
    @Autowired
    private CaseMediationClaimMapper caseMediationClaimMapper;
    @Autowired
    private PaymentEstimateApplyMapper paymentEstimateApplyMapper;
    @Autowired
    private CaseAssessmentObjReportMapper caseAssessmentObjReportMapper;
    @Autowired
    private BlameQuotietyMapper blameQuotietyMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private PaymentEstimateReportMapper paymentEstimateReportMapper;
    @Autowired
    private AgentApplyMapper agentApplyMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private BackendCaseStepsApiImpl backendCaseStepsApi;
    @Autowired
    private CaseCenterInfoExtend2Mapper caseCenterInfoExtend2Mapper;
    @Autowired
    private InvalidismEstimateMapper invalidismEstimateMapper;
    @Autowired
    private InvalidismEstimateReportMapper invalidismEstimateReportMapper;

    @Value("${quota_one}")
    private String quotaOne;//第一部分限额
    @Value("${quota_two}")
    private String quotaTwo;//第二部分限额
    @Value("${quota_three}")
    private String quotaThree;//第三部分限额

    @Value("${irresp_quota_one}")
    private String irrespQuotaOne;//无责任第一部分限额
    @Value("${irresp_quota_two}")
    private String irrespQuotaTwo;//无责任第一部分限额
    @Value("${irresp_quota_three}")
    private String irrespQuotaThree;//无责任第一部分限额
    @Value("${loss_word_fee}")
    private Double lossWordFee;//无责任第一部分限额
    @ApiMethod(descript = "查看索赔方案", value = "backend-case-mediation-claim")
    @Override
    public ApiResponse getCaseMediationClaim(ApiRequest apiReq) {
        Long caseId = apiReq.getLong("caseId");
        String op = apiReq.getString("op");
        HashMap<String,Object> map = new HashMap<>();
        CaseMediationClaim caseMediationClaim = caseMediationClaimMapper.queryByCaseId(caseId);
        if (caseMediationClaim == null){
            caseMediationClaim = new CaseMediationClaim();
            caseMediationClaim.setId(null);
            //从案件中心带出可用数据(初始化数据)
            CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);
            caseMediationClaim.setCaseId(caseCenterInfo.getId());
            caseMediationClaim = initData(caseMediationClaim,caseCenterInfo);
            if ("view".equals(op)){

            }else{
                caseMediationClaimMapper.insertSelective(caseMediationClaim);
            }
        }
        caseMediationClaim.setCaseId(caseId);
        map.put("caseMediationClaim",caseMediationClaim);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }

    @ApiMethod(descript = "新增索赔方案",value = "backend-add-case-mediation-claim")
    @Override
    public ApiResponse addCaseMediationClaim(ApiRequest apiReq) {
        Long currentUserId = apiReq.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        CaseMediationClaim caseMediationClaim = ConvertToBeanUtil.toBeanFromApiRequest(apiReq, CaseMediationClaim.class);
        int ret = -1;
        if (caseMediationClaim != null){
            ret = caseMediationClaimMapper.insert(caseMediationClaim);
        }
//        if (ret > 0){
//            return new ApiResponse(ApiMsgEnum.SUCCESS);
//        }else{
//            return new ApiResponse(ApiMsgEnum.FAIL);
//        }
        if(apiReq.getInt("noNext")==3){
            //保存并提交审核
            CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(apiReq.getLong("caseId"));
            caseCenterInfo.setClaimState(7);
            caseCenterInfo.setListStateName("索赔预案审核中");
            caseCenterInfoMapper.updateByPrimaryKeySelective(caseCenterInfo);
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "claim_shenhe", 1, 7);
        }
        caseMediationClaim = caseMediationClaimMapper.selectByPrimaryKey(caseMediationClaim.getId());
        caseMediationClaim.setNoNext(apiReq.getInt("noNext"));
        caseMediationClaim.setStepCode(apiReq.getInt("stepCode"));
        //根据“事故责任比例”计算总计
        Double rate = caseMediationClaim.getLiabilityRatio() == null ? 0D : caseMediationClaim.getLiabilityRatio();
        insuranceCalculation(caseMediationClaim.getCaseId(),rate);

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,caseMediationClaim);
    }

    @ApiMethod(descript = "修改索赔方案",value = "backend-upd-case-mediation-claim")
    @Override
    public ApiResponse updCaseMediationClaim(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        Long currentUserId = apiReq.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        CaseMediationClaim caseMediationClaim = caseMediationClaimMapper.selectByPrimaryKey(id);
        if (caseMediationClaim != null){
            caseMediationClaim = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,caseMediationClaim);
        }
        int ret = caseMediationClaimMapper.updateByPrimaryKey(caseMediationClaim);
//        if (ret > 0){
//            return new ApiResponse(ApiMsgEnum.SUCCESS);
//        }else{
//            return new ApiResponse(ApiMsgEnum.FAIL);
//        }
        if(apiReq.getInt("noNext")==3){
            //保存并提交审核
            CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(apiReq.getLong("caseId"));
            caseCenterInfo.setClaimState(7);
            caseCenterInfo.setOperReason(null);
            caseCenterInfo.setListStateName("索赔预案审核中");
            caseCenterInfoMapper.updateByPrimaryKeySelective(caseCenterInfo);
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "claim_shenhe", 1, 7);
        }
        caseMediationClaim = caseMediationClaimMapper.selectByPrimaryKey(id);
        caseMediationClaim.setNoNext(apiReq.getInt("noNext"));
        caseMediationClaim.setStepCode(apiReq.getInt("stepCode"));
        //根据“事故责任比例”计算总计
        Double rate = caseMediationClaim.getLiabilityRatio() == null ? 0D : caseMediationClaim.getLiabilityRatio();
        insuranceCalculation(caseMediationClaim.getCaseId(),rate);

        return new ApiResponse(ApiMsgEnum.SUCCESS,1,caseMediationClaim);
    }

    @ApiMethod(descript = "查询索赔方案赔偿及保险理赔方案",value = "backend-get-case-mediation-claim-report")
    @Override
    public ApiResponse getCaseMediationClaimReport(ApiRequest apiReq) {
        Long caseId = apiReq.getLong("caseId");
        String op = apiReq.getString("op");
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);
        List<CaseMediationClaimReport> caseMediationClaimReports = caseMediationClaimReportMapper.selectByCaseId(caseId);
        CaseMediationClaim caseMediationClaim = caseMediationClaimMapper.queryByCaseId(caseId);
        if (caseMediationClaimReports == null || caseMediationClaimReports.size() == 0){
            //初始化数据
            Map<String,Object> mapNames = null;
//            if (caseCenterInfo.getType() == 2){
//                mapNames = ReportProjectEnum.MEDIATION_TYPE_PAY.getMap();//mapNames  key 索赔方案的项目    对应  value   测算项目
//            }else{
//                mapNames = ReportProjectEnum.MEDIATION_TYPE.getMap();//mapNames  key 索赔方案的项目    对应  value   公估报告的项目
//            }
            mapNames = ReportProjectEnum.MEDIATION_TYPE_PAY.getMap();//mapNames  key 索赔方案的项目    对应  value   测算项目
            if ("view".equals(op)){
                for (String name : mapNames.keySet()){
                    CaseMediationClaimReport caseMediationClaimReport = initDataDetail(caseCenterInfo,mapNames,name);
                    caseMediationClaimReports.add(caseMediationClaimReport);
                }
            }else{
                for (String name : mapNames.keySet()){
                    CaseMediationClaimReport caseMediationClaimReport = initDataDetail(caseCenterInfo,mapNames,name);
                    caseMediationClaimReportMapper.insert(caseMediationClaimReport);
                }
                Double rate = 0D;
                if (caseMediationClaim != null){
                    rate = caseMediationClaim.getLiabilityRatio() == null ? 0D : caseMediationClaim.getLiabilityRatio();
                }
                Boolean b = false;
//                if (caseCenterInfo.getType() == 2){
//                    AgentApply agentApply = agentApplyMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
//                    if (agentApply.getAgentType() == 2){
//                        b = true;
//                    }
//                }
//                if (!b){
//                    insuranceCalculation(caseId,rate);
//                }
                insuranceCalculation(caseId,rate);
                //重新获取数据
                caseMediationClaimReports = caseMediationClaimReportMapper.selectByCaseId(caseId);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,caseMediationClaimReports.size(),caseMediationClaimReports);
    }

    @ApiMethod(descript = "修改案件赔偿及保险理赔方案",value = "backend-upd-case-mediation-claim-report")
    @Override
    public ApiResponse updCaseMediationClaimReport(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        String type = apiReq.getString("type");
        String value = apiReq.getString("value");
        CaseMediationClaimReport recode = caseMediationClaimReportMapper.selectByPrimaryKey(id);
        CaseMediationClaim caseMediationClaim = caseMediationClaimMapper.queryByCaseId(recode.getCaseId());

        // 1 索赔预案金额 ,2 索赔预案审核金额 ,3 保险公司审核金额
        if ("1".equals(type)){
            recode.setOpinionMoney(Double.parseDouble(value == null || "".equals(value) ? "0" : value));
            recode.setAuditingMoney(recode.getOpinionMoney());
        }else if("2".equals(type)){
            recode.setAuditingMoney(Double.parseDouble(value == null || "".equals(value) ? "0" : value));
        }else if("3".equals(type)){
            recode.setIcAuditingMoney(Double.parseDouble(value == null || "".equals(value) ? "0" : value));
        }else if ("4".equals(type)){
            recode.setCheckBasis(value == null ? "" : value);
        }
        caseMediationClaimReportMapper.updateByPrimaryKeySelective(recode);
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(recode.getCaseId());
        //计算合计损失
        Double amount = 0D;
        if ("1".equals(type)){
            if (!"合计损失".equals(recode.getProjectName())){
                CaseMediationClaimReport claimReportAll = caseMediationClaimReportMapper.selectByCaseIdOrProjectName(returnMap(recode.getCaseId(),"合计损失"));
                List<CaseMediationClaimReport> reports = caseMediationClaimReportMapper.selectByCaseId(recode.getCaseId());
                for (CaseMediationClaimReport report : reports){
                    amount += report.getOpinionMoney();
                }
                claimReportAll.setOpinionMoney(amount - claimReportAll.getOpinionMoney());//计算的是所有项目的总和 减去 多加的合计损失
                claimReportAll.setAuditingMoney(claimReportAll.getOpinionMoney());
                caseMediationClaimReportMapper.updateByPrimaryKey(claimReportAll);
                Double rate = caseMediationClaim.getLiabilityRatio() == null ? 0D : caseMediationClaim.getLiabilityRatio();
                //insuranceCalculation(recode.getCaseId(),rate);
                Boolean b = false;
//                if (caseCenterInfo.getType() == 2){
//                    AgentApply agentApply = agentApplyMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
//                    if (agentApply.getAgentType() == 2){
//                        b = true;
//                    }
//                }
//                if (!b){
//                    insuranceCalculation(recode.getCaseId(),rate);
//                }
                insuranceCalculation(recode.getCaseId(),rate);
            }
        }else if("2".equals(type)){
            if (!"合计损失".equals(recode.getProjectName())){
                CaseMediationClaimReport claimReportAll = caseMediationClaimReportMapper.selectByCaseIdOrProjectName(returnMap(recode.getCaseId(),"合计损失"));
                List<CaseMediationClaimReport> reports = caseMediationClaimReportMapper.selectByCaseId(recode.getCaseId());
                for (CaseMediationClaimReport report : reports){
                    amount += report.getAuditingMoney();
                }
                claimReportAll.setAuditingMoney(amount - claimReportAll.getAuditingMoney());//计算的是所有项目的总和 减去 多加的合计损失
                caseMediationClaimReportMapper.updateByPrimaryKey(claimReportAll);
                Double rate = caseMediationClaim.getLiabilityRatio() == null ? 0D : caseMediationClaim.getLiabilityRatio();
                Boolean b = false;
//                if (caseCenterInfo.getType() == 2){
//                    AgentApply agentApply = agentApplyMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
//                    if (agentApply.getAgentType() == 2){
//                        b = true;
//                    }
//                }
//                if (!b){
//                    insuranceCalculation(recode.getCaseId(),rate);
//                }
                insuranceCalculation(recode.getCaseId(),rate);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }
    //预案报告中修改项目后算法计算交强险，商业险
    public void insuranceCalculation(Long caseId,Double rate){
        //查询案件信息
        //查询预案项目各项费用
        //医疗费
        Double t_medicalFee=0D;
        Long t_medicalFeeId=0L;
        //医院伙食补助费
        Double t_hospitalFoodFee=0D;
        Long t_hospitalFoodFeeId=0L;
        //营养费
        Double t_nutritionFee=0D;
        Long t_nutritionFeeId=0L;
        //后续治疗费
        Double t_againCureFee=0D;
        Long t_againCureFeeId=0L;
        //护理费
        Double t_nursingFee=0D;
        Long t_nursingFeeId=0L;
        //误工费
        Double t_lossWordFee=0D;
        Long t_lossWordFeeId=0L;
        //残疾赔偿金
        Double t_invalidismIndemnifyFee=0D;
        Long t_invalidismIndemnifyFeeId=0L;
        //精神抚慰金
        Double t_spiritComfortFee=0D;
        Long t_spiritComfortFeeId=0L;
        //死亡赔偿金
        Double t_deathIndemnifyFee=0D;
        Long t_deathIndemnifyFeeId=0L;
        ////丧葬费
        Double t_funeralFee=0D;
        Long t_funeralFeeId=0L;
        //交通费
        Double t_trafficFee=0D;
        Long t_trafficFeeId=0L;
        //诉讼费
        Double t_litigationFee=0D;
        Long t_litigationFeeId=0L;
        //残疾器具费
        Double t_disabilityEquipmentFee=0D;
        Long t_disabilityEquipmentFeeId=0L;
        //鉴定费
        Double t_appraisalFee=0D;
        Long t_appraisalFeeId=0L;
        //财产损失
        Double t_financialLoss=0D;
        Long t_financialLossId=0L;
        //合计损失
        Double t_totalFeeJq=0D;
        Double t_totalFeeSy=0D;
        Long t_totalFeeId=0L;

        //医疗小计
        Double t_totalYlJq=0D;
        Double t_totalYlSy=0D;
        Long t_totalYlId=0L;
        //赔偿小计
        Double t_totalPcJq=0D;
        Double t_totalPcSy=0D;
        Long t_totalPcId=0L;

        //第三部分的合计
        Double t_totalCwJq=0D;
        Double t_totalCwSy=0D;
        List<CaseMediationClaimReport> caseMediationClaimReports = this.caseMediationClaimReportMapper.selectByCaseId(caseId);
        for(CaseMediationClaimReport caseMediationClaimReport:caseMediationClaimReports){
            if(caseMediationClaimReport.getProjectName().equals("医疗费")){
                t_medicalFee= caseMediationClaimReport.getAuditingMoney();
                t_medicalFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("住院伙食补助费")){
                //医院伙食补助费
                t_hospitalFoodFee=caseMediationClaimReport.getAuditingMoney();
                t_hospitalFoodFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("营养费")){
                //营养费
                t_nutritionFee=caseMediationClaimReport.getAuditingMoney();
                t_nutritionFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("后续治疗费")){
                //后续治疗费
                t_againCureFee=caseMediationClaimReport.getAuditingMoney();
                t_againCureFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("护理费")){
                //护理费
                t_nursingFee=caseMediationClaimReport.getAuditingMoney();
                t_nursingFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("误工费")){
                //误工费
                t_lossWordFee=caseMediationClaimReport.getAuditingMoney();
                t_lossWordFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("残疾赔偿金")){
                //残疾赔偿金
                t_invalidismIndemnifyFee=caseMediationClaimReport.getAuditingMoney();
                t_invalidismIndemnifyFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("精神抚慰金")){
                //精神抚慰金
                t_spiritComfortFee=caseMediationClaimReport.getAuditingMoney();
                t_spiritComfortFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("被抚养人生活费")){
                //被抚养人生活费
                Double t_liveFee=caseMediationClaimReport.getAuditingMoney();
                Long t_liveFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("死亡赔偿金")){
                //死亡赔偿金
                t_deathIndemnifyFee=caseMediationClaimReport.getAuditingMoney();
                t_deathIndemnifyFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("丧葬费")){
                //丧葬费
                t_funeralFee=caseMediationClaimReport.getAuditingMoney();
                t_funeralFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("交通费")){
                //交通费
                t_trafficFee=caseMediationClaimReport.getAuditingMoney();
                t_trafficFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("诉讼费")){
                //诉讼费
                t_litigationFee=caseMediationClaimReport.getAuditingMoney();
                t_litigationFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("残疾器具费")){
                //残疾器具费
                t_disabilityEquipmentFee=caseMediationClaimReport.getAuditingMoney();
                t_disabilityEquipmentFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("鉴定费")){
                //鉴定费
                t_appraisalFee=caseMediationClaimReport.getAuditingMoney();
                t_appraisalFeeId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("财产损失")){
                //财产损失
                t_financialLoss=caseMediationClaimReport.getAuditingMoney();
                t_financialLossId=caseMediationClaimReport.getId();
            }else if(caseMediationClaimReport.getProjectName().equals("合计损失")){
                //合计损失
                // t_totalFee=caseClosedObjReport.getAuditingMoney();
                t_totalFeeId=caseMediationClaimReport.getId();
            }
        }
        CaseCenterInfo caseCenterInfo = this.caseCenterInfoMapper.selectByPrimaryKey(caseId);
        //查出原来提交的测算数据
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("caseNo",caseCenterInfo.getCaseNo());
        PaymentEstimateApply paymentEstimateApply =  this.paymentEstimateApplyMapper.selectPEAByCaseCenterInfoNew(map);
        List<BlameQuotiety> blameQuotieties = redisService.estimateGet("blameQuotieties",new BlameQuotiety());
        if(blameQuotieties==null){
            blameQuotieties =    this.blameQuotietyMapper.selectBlameQuotiety();
            redisService.estimateAdd("blameQuotieties",blameQuotieties,30 * 24 * 60 * 60);
        }
        CaseMediationClaim caseMediationClaim1=this.caseMediationClaimMapper.queryByCaseId(caseId);
        //根据新的责任计算责任比例
        double blameQuotiety=0D;
        if(rate!=null&&rate!=0){
            blameQuotiety=DecimalUtil.twoDecimalTOFourFromFive(rate/100);
        }else if(caseMediationClaim1.getLiabilityRatio()!=null&&caseMediationClaim1.getLiabilityRatio()>0){
             blameQuotiety=DecimalUtil.twoDecimalTOFourFromFive(caseMediationClaim1.getLiabilityRatio()/100);
        }else if(paymentEstimateApply!=null){
             blameQuotiety = PaymentAlgorithmUtil.getBlameQuotiety(paymentEstimateApply.getMyStatus(), blameQuotieties, paymentEstimateApply.getMyAccidentLiability(), paymentEstimateApply.getOtherTarfficStatus(), paymentEstimateApply.getMyTarfficStatus());
        }else{
            blameQuotiety=0D;
        }

        //double blameQuotiety = PaymentAlgorithmUtil.getBlameQuotiety(paymentEstimateApply.getMyStatus(), blameQuotieties, paymentEstimateApply.getMyAccidentLiability(), paymentEstimateApply.getOtherTarfficStatus(), paymentEstimateApply.getMyTarfficStatus());
        //判断有无责
        boolean bl = PaymentAlgorithmUtil.getResponsibility(blameQuotieties, paymentEstimateApply.getMyAccidentLiability(), paymentEstimateApply.getOtherTarfficStatus(), paymentEstimateApply.getMyTarfficStatus());
        //扣除精神抚慰金后金额
        Double  subtractSpiritComfortFee=PaymentAlgorithmUtil.subtractSpiritComfortFee(quotaTwo,t_spiritComfortFee);
        //计算各项费用的交强险，商业险
        Map<String,Object> t_estimateOne=null;
        Map<String,Object> t_estimateTwo=null;
        Map<String,Object> t_estimateThree=null;
        Map<String,Object> t_destimateOne=null;
        Map<String,Object> t_destimateTwo=null;
        //第一部分总和金额
        if(bl){
            t_estimateOne =PaymentAlgorithmUtil.estimateOneIrresponsibility(irrespQuotaOne, t_medicalFee, t_hospitalFoodFee, t_nutritionFee, t_againCureFee);
            //第二部分总和金额
            t_estimateTwo=PaymentAlgorithmUtil.estimateTwoIrresponsibility(irrespQuotaTwo, t_lossWordFee, t_nursingFee, t_invalidismIndemnifyFee, t_spiritComfortFee, t_trafficFee,t_litigationFee,t_disabilityEquipmentFee,t_appraisalFee);
            //第三部分总和金额
            t_estimateThree=PaymentAlgorithmUtil.estimateThreeIrresponsibility(irrespQuotaThree, t_financialLoss);

            //第一部分总和金额
            t_destimateOne=PaymentAlgorithmUtil.destimateOneIrresponsibility(irrespQuotaOne, t_medicalFee);
            //第二部分总和金额
            t_destimateTwo=PaymentAlgorithmUtil.destimateTwoIrresponsibility(irrespQuotaTwo, t_deathIndemnifyFee, t_spiritComfortFee, t_funeralFee);
        }else{
            t_estimateOne=PaymentAlgorithmUtil.estimateOne(quotaOne, t_medicalFee, t_hospitalFoodFee, t_nutritionFee, t_againCureFee);
            //第二部分总和金额
            t_estimateTwo=PaymentAlgorithmUtil.estimateTwo(quotaTwo, t_lossWordFee, t_nursingFee, t_invalidismIndemnifyFee, t_spiritComfortFee, t_trafficFee,t_litigationFee,t_disabilityEquipmentFee,t_appraisalFee);
            //第三部分总和金额
            t_estimateThree=PaymentAlgorithmUtil.estimateThree(quotaThree, t_financialLoss);

            //第一部分总和金额
            t_destimateOne=PaymentAlgorithmUtil.destimateOne(quotaOne, t_medicalFee);
            //第二部分总和金额
            t_destimateTwo=PaymentAlgorithmUtil.destimateTwo(quotaTwo, t_deathIndemnifyFee, t_spiritComfortFee, t_funeralFee);
        }
        //肇事方金额
        Double causeTroubleFee=(t_medicalFee/0.9)*0.1*blameQuotiety;//肇事方金额*责任比例
        Integer isDeanth=0;//未死亡
        if(paymentEstimateApply.getInjuryStatus().equals("12")){
            isDeanth=1;
        }
        Double compulsoryInsuranceFee=0D;//计算交强险
        Double commercialInsuranceFee=0D;//计算商业险
        if(isDeanth==0){
            //***********第一部分
            Double estimateOneTotal = DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateOne.get("estimateOneTotal").toString()));
            if(estimateOneTotal==0){
                estimateOneTotal=1D;
            }
            Double compulsoryInsuranceOneFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateOne.get("compulsoryInsuranceOneFee").toString()));
            Double commercialInsuranceOneFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateOne.get("commercialInsuranceOneFee").toString())*blameQuotiety);
            //医疗费
            CaseMediationClaimReport caseMediationClaimReport1=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_medicalFeeId);
            Double jqMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/estimateOneTotal) *compulsoryInsuranceOneFee);//医疗费交强险
            Double  syMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/estimateOneTotal)*commercialInsuranceOneFee);//医疗费商业险
            if(jqMedicalFee>0){
                caseMediationClaimReport1.setCommerMoney(jqMedicalFee);
                t_totalYlJq=t_totalYlJq+jqMedicalFee;
                caseMediationClaimReport1.setCompulMoney(syMedicalFee);
                t_totalYlSy=t_totalYlSy+syMedicalFee;
                this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport1);
            }


            //伙食补助费
            CaseMediationClaimReport caseMediationClaimReport2=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_hospitalFoodFeeId);
            Double jqhospitalFoodFee=DecimalUtil.twoDecimalTOFourFromFive((t_hospitalFoodFee/estimateOneTotal)*compulsoryInsuranceOneFee);//伙食补助费交强险
            Double syhospitalFoodFee=DecimalUtil.twoDecimalTOFourFromFive((t_hospitalFoodFee/estimateOneTotal)*commercialInsuranceOneFee);//伙食补助商业险
            if(jqhospitalFoodFee>0){
                caseMediationClaimReport2.setCommerMoney(jqhospitalFoodFee);
                t_totalYlJq=t_totalYlJq+jqhospitalFoodFee;
                caseMediationClaimReport2.setCompulMoney(syhospitalFoodFee);
                t_totalYlSy=t_totalYlSy+syhospitalFoodFee;
                this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport2);
            }

            //营养费
            CaseMediationClaimReport caseMediationClaimReport3=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_nutritionFeeId);
            Double  jqnutritionFee=DecimalUtil.twoDecimalTOFourFromFive((t_nutritionFee/estimateOneTotal)*compulsoryInsuranceOneFee);//营养费交强险
            Double synutritionFee=DecimalUtil.twoDecimalTOFourFromFive((t_nutritionFee/estimateOneTotal)*commercialInsuranceOneFee);//营养费商业险
            if(jqnutritionFee>0){
                caseMediationClaimReport3.setCommerMoney(jqnutritionFee);
                t_totalYlJq=t_totalYlJq+jqnutritionFee;
                caseMediationClaimReport3.setCompulMoney(synutritionFee);
                t_totalYlSy=t_totalYlSy+synutritionFee;
                this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport3);
            }




            //后续治疗费
            CaseMediationClaimReport caseMediationClaimReport4=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_againCureFeeId);
            Double jqagainCureFee=DecimalUtil.twoDecimalTOFourFromFive((t_againCureFee/estimateOneTotal)*compulsoryInsuranceOneFee);//后续治疗费交强险
            Double syagainCureFee=DecimalUtil.twoDecimalTOFourFromFive((t_againCureFee/estimateOneTotal)*commercialInsuranceOneFee);//后续治疗费商业险
            if(jqagainCureFee>0){
                caseMediationClaimReport4.setCommerMoney(jqagainCureFee);
                t_totalYlJq=t_totalYlJq+jqagainCureFee;
                caseMediationClaimReport4.setCompulMoney(syagainCureFee);
                t_totalYlSy=t_totalYlSy+syagainCureFee;
                this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport4);
            }



            //肇事方金额
            Double ct_commercialInsuranceOneFee= DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateOne.get("commercialInsuranceOneFee").toString()) * (1 - blameQuotiety));
            //**********第二部分
            Double estimateTwoTotal =Double.parseDouble(t_estimateTwo.get("estimateTwoTotal").toString());
            if(estimateTwoTotal==0){
                estimateTwoTotal=1.0;
            }
            Double compulsoryInsuranceTwoFee =Double.parseDouble(t_estimateTwo.get("compulsoryInsuranceTwoFee").toString());
            Double commercialInsuranceTwoFee =Double.parseDouble(t_estimateTwo.get("commercialInsuranceTwoFee").toString())*blameQuotiety;
            Double ct_commercialInsuranceTwoFee =Double.parseDouble(t_estimateTwo.get("commercialInsuranceTwoFee").toString())*(1-blameQuotiety);

            //判断精神抚慰金
            if(subtractSpiritComfortFee>=0){
                //护理费
                CaseMediationClaimReport caseMediationClaimReport5=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_nursingFeeId);
                Double jqnursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//护理费交强险
                Double  synursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal)*commercialInsuranceTwoFee);//护理费商业险
                if(jqnursingFee>0){
                    caseMediationClaimReport5.setCommerMoney(jqnursingFee);
                    t_totalPcJq=t_totalPcJq+jqnursingFee;
                    caseMediationClaimReport5.setCompulMoney(synursingFee);
                    t_totalPcSy=t_totalPcSy+synursingFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport5);
                }



                //误工费
                CaseMediationClaimReport caseMediationClaimReport6=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_lossWordFeeId);
                Double jqlossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//误工费交强险
                Double  sylossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal)*commercialInsuranceTwoFee);//误工费商业险
                if(jqlossWordFee>0){
                    caseMediationClaimReport6.setCommerMoney(jqlossWordFee);
                    t_totalPcJq=t_totalPcJq+jqlossWordFee;
                    caseMediationClaimReport6.setCompulMoney(sylossWordFee);
                    t_totalPcSy=t_totalPcSy+sylossWordFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport6);
                }



                //残疾赔偿金
                CaseMediationClaimReport caseMediationClaimReport7=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_invalidismIndemnifyFeeId);
                Double jqinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//残疾赔偿金交强险
                Double  syinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal)*commercialInsuranceTwoFee);//残疾赔偿金商业险
                if(jqinvalidismIndemnifyFee>0){
                    caseMediationClaimReport7.setCommerMoney(jqinvalidismIndemnifyFee);
                    t_totalPcJq=t_totalPcJq+jqinvalidismIndemnifyFee;
                    caseMediationClaimReport7.setCompulMoney(syinvalidismIndemnifyFee);
                    t_totalPcSy=t_totalPcSy+syinvalidismIndemnifyFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport7);
                }



                //精神抚慰金
                CaseMediationClaimReport caseMediationClaimReport8=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_spiritComfortFeeId);
                Double jqspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive(t_spiritComfortFee);//精神抚慰金交强险

                if(jqspiritComfortFee>0){
                    caseMediationClaimReport8.setCommerMoney(jqspiritComfortFee);
                    t_totalPcJq=t_totalPcJq+jqspiritComfortFee;
                    caseMediationClaimReport8.setCompulMoney(0D);
                    t_totalPcSy=t_totalPcSy+0D;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport8);
                }


                //交通费
                CaseMediationClaimReport caseMediationClaimReport9=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_trafficFeeId);
                Double jqtrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sytrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqtrafficFee>0){
                    caseMediationClaimReport9.setCommerMoney(jqtrafficFee);
                    t_totalPcJq=t_totalPcJq+jqtrafficFee;
                    caseMediationClaimReport9.setCompulMoney(sytrafficFee);
                    t_totalPcSy=t_totalPcSy+sytrafficFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport9);
                }



                //诉讼费
                CaseMediationClaimReport caseMediationClaimReport10=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_litigationFeeId);
                Double jqlitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sylitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqlitigationFee>0){
                    caseMediationClaimReport10.setCommerMoney(jqlitigationFee);
                    t_totalPcJq=t_totalPcJq+jqlitigationFee;
                    caseMediationClaimReport10.setCompulMoney(sylitigationFee);
                    t_totalPcSy=t_totalPcSy+sylitigationFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport10);
                }



                //残疾器具费
                CaseMediationClaimReport caseMediationClaimReport11=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_disabilityEquipmentFeeId);
                Double jqdisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sydisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqdisabilityEquipmentFee>0){
                    caseMediationClaimReport11.setCommerMoney(jqdisabilityEquipmentFee);
                    t_totalPcJq=t_totalPcJq+jqdisabilityEquipmentFee;
                    caseMediationClaimReport11.setCompulMoney(sydisabilityEquipmentFee);
                    t_totalPcSy=t_totalPcSy+sydisabilityEquipmentFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport11);
                }



                //鉴定费
                CaseMediationClaimReport caseMediationClaimReport12=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_appraisalFeeId);
                Double jqappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  syappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqappraisalFee>0){
                    caseMediationClaimReport12.setCommerMoney(jqappraisalFee);
                    t_totalPcJq=t_totalPcJq+jqappraisalFee;
                    caseMediationClaimReport12.setCompulMoney(syappraisalFee);
                    t_totalPcSy=t_totalPcSy+syappraisalFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport12);
                }
            }else{
                //护理费
                CaseMediationClaimReport caseMediationClaimReport5=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_nursingFeeId);
                Double jqnursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//护理费交强险
                Double  synursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal)*commercialInsuranceTwoFee);//护理费商业险
                if(jqnursingFee>0){
                    caseMediationClaimReport5.setCommerMoney(jqnursingFee);
                    t_totalPcJq=t_totalPcJq+jqnursingFee;
                    caseMediationClaimReport5.setCompulMoney(synursingFee);
                    t_totalPcSy=t_totalPcSy+jqnursingFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport5);
                }



                //误工费
                CaseMediationClaimReport caseMediationClaimReport6=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_lossWordFeeId);
                Double jqlossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//误工费交强险
                Double  sylossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal)*commercialInsuranceTwoFee);//误工费商业险
                if(jqlossWordFee>0){
                    caseMediationClaimReport6.setCommerMoney(jqlossWordFee);
                    t_totalPcJq=t_totalPcJq+jqlossWordFee;
                    caseMediationClaimReport6.setCompulMoney(sylossWordFee);
                    t_totalPcSy=t_totalPcSy+sylossWordFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport6);
                }



                //残疾赔偿金
                CaseMediationClaimReport caseMediationClaimReport7=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_invalidismIndemnifyFeeId);
                Double jqinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//残疾赔偿金交强险
                Double  syinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal)*commercialInsuranceTwoFee);//残疾赔偿金商业险
                if(jqinvalidismIndemnifyFee>0){
                    caseMediationClaimReport7.setCommerMoney(jqinvalidismIndemnifyFee);
                    t_totalPcJq=t_totalPcJq+jqinvalidismIndemnifyFee;
                    caseMediationClaimReport7.setCompulMoney(syinvalidismIndemnifyFee);
                    t_totalPcSy=t_totalPcSy+syinvalidismIndemnifyFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport7);
                }



                //精神抚慰金
                CaseMediationClaimReport caseMediationClaimReport8=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_spiritComfortFeeId);
                Double jqspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive(110000);//精神抚慰金交强险
                Double  syspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/estimateTwoTotal)*commercialInsuranceTwoFee);//精神抚慰金商业险
                if(jqspiritComfortFee>0){
                    caseMediationClaimReport8.setCommerMoney(jqspiritComfortFee);
                    t_totalPcJq=t_totalPcJq+jqspiritComfortFee;
                    caseMediationClaimReport8.setCompulMoney(syspiritComfortFee);
                    t_totalPcSy=t_totalPcSy+syspiritComfortFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport8);
                }


                //交通费
                CaseMediationClaimReport caseMediationClaimReport9=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_trafficFeeId);
                Double jqtrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sytrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqtrafficFee>0){
                    caseMediationClaimReport9.setCommerMoney(jqtrafficFee);
                    t_totalPcJq=t_totalPcJq+jqtrafficFee;
                    caseMediationClaimReport9.setCompulMoney(sytrafficFee);
                    t_totalPcSy=t_totalPcSy+sytrafficFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport9);
                }



                //诉讼费
                CaseMediationClaimReport caseMediationClaimReport10=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_litigationFeeId);
                Double jqlitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sylitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqlitigationFee>0){
                    caseMediationClaimReport10.setCommerMoney(jqlitigationFee);
                    t_totalPcJq=t_totalPcJq+jqlitigationFee;
                    caseMediationClaimReport10.setCompulMoney(sylitigationFee);
                    t_totalPcSy=t_totalPcSy+sylitigationFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport10);
                }



                //残疾器具费
                CaseMediationClaimReport caseMediationClaimReport11=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_disabilityEquipmentFeeId);
                Double jqdisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sydisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqdisabilityEquipmentFee>0){
                    caseMediationClaimReport11.setCommerMoney(jqdisabilityEquipmentFee);
                    t_totalPcJq=t_totalPcJq+jqdisabilityEquipmentFee;
                    caseMediationClaimReport11.setCompulMoney(sydisabilityEquipmentFee);
                    t_totalPcSy=t_totalPcSy+sydisabilityEquipmentFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport11);
                }



                //鉴定费
                CaseMediationClaimReport caseMediationClaimReport12=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_appraisalFeeId);
                Double jqappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  syappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqappraisalFee>0){
                    caseMediationClaimReport12.setCommerMoney(jqappraisalFee);
                    t_totalPcJq=t_totalPcJq+jqappraisalFee;
                    caseMediationClaimReport12.setCompulMoney(syappraisalFee);
                    t_totalPcSy=t_totalPcSy+syappraisalFee;
                    this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport12);
                }
            }






            //*****第三部分
            Double estimateThreeTotal =Double.parseDouble(t_estimateThree.get("estimateThreeTotal").toString());
            Double compulsoryInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("compulsoryInsuranceThreeFee").toString());
            t_totalCwJq=t_totalCwJq+compulsoryInsuranceThreeFee;
            Double commercialInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*blameQuotiety;
            t_totalCwSy=t_totalCwSy+commercialInsuranceThreeFee;
            Double ct_commercialInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*(1-blameQuotiety);
            CaseMediationClaimReport caseMediationClaimReport13=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_financialLossId);
            if(compulsoryInsuranceThreeFee>0){
                caseMediationClaimReport13.setCommerMoney(compulsoryInsuranceThreeFee);
                caseMediationClaimReport13.setCompulMoney(commercialInsuranceThreeFee);
                this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport13);
            }

        }else{
            //***********第一部分
            Double destimateOneTotal =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateOne.get("destimateOneTotal").toString()));
            if(destimateOneTotal==0){
                destimateOneTotal=1D;
            }
            Double dcompulsoryInsuranceOneFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateOne.get("dcompulsoryInsuranceOneFee").toString()));
            Double dcommercialInsuranceOneFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateOne.get("dcommercialInsuranceOneFee").toString())*blameQuotiety);
            Double ct_dcommercialInsuranceOneFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateOne.get("dcommercialInsuranceOneFee").toString())*(1-blameQuotiety));
            //医疗费
            CaseMediationClaimReport caseMediationClaimReport1=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_medicalFeeId);
            Double jqMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/destimateOneTotal) *dcompulsoryInsuranceOneFee);//医疗费交强险
            Double  syMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/destimateOneTotal)*dcommercialInsuranceOneFee);//医疗费商业险
            if(jqMedicalFee>0){
                caseMediationClaimReport1.setCommerMoney(jqMedicalFee);
                t_totalYlJq=t_totalYlJq+jqMedicalFee;
                caseMediationClaimReport1.setCompulMoney(syMedicalFee);
                t_totalYlSy=t_totalYlSy+syMedicalFee;
                this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport1);
            }



            //**********第二部分

            Double destimateTwoTotal =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateTwo.get("destimateTwoTotal").toString()));
            if(destimateTwoTotal==0){
                destimateTwoTotal=1D;
            }
            Double dcompulsoryInsuranceTwoFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateTwo.get("dcompulsoryInsuranceTwoFee").toString()));
            Double dcommercialInsuranceTwoFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateTwo.get("dcommercialInsuranceTwoFee").toString())*blameQuotiety);
            Double ct_dcommercialInsuranceTwoFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateTwo.get("dcommercialInsuranceTwoFee").toString())*(1-blameQuotiety));
            //死亡赔偿金
            CaseMediationClaimReport caseMediationClaimReport2=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_deathIndemnifyFeeId);
            Double jqdeathIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_deathIndemnifyFee/destimateTwoTotal)*dcompulsoryInsuranceTwoFee);//死亡赔偿金交强险
            Double sydeathIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_deathIndemnifyFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);//死亡赔偿金商业险
            if(jqdeathIndemnifyFee>0){
                caseMediationClaimReport2.setCommerMoney(jqdeathIndemnifyFee);
                t_totalPcJq=t_totalPcJq+jqdeathIndemnifyFee;
                caseMediationClaimReport2.setCompulMoney(sydeathIndemnifyFee);
                t_totalPcSy=t_totalPcSy+sydeathIndemnifyFee;
                this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport2);
            }



            //精神抚慰金
            CaseMediationClaimReport caseMediationClaimReport3=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_spiritComfortFeeId);
            Double jqspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/destimateTwoTotal)*dcompulsoryInsuranceTwoFee);//精神抚慰金
            Double syspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);//精神抚慰金
            if(jqspiritComfortFee>0){
                caseMediationClaimReport3.setCommerMoney(jqspiritComfortFee);
                t_totalPcJq=t_totalPcJq+jqspiritComfortFee;
                caseMediationClaimReport3.setCompulMoney(syspiritComfortFee);
                t_totalPcSy=t_totalPcSy+syspiritComfortFee;
                this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport3);
            }



            //丧葬费
            CaseMediationClaimReport caseMediationClaimReport4=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_funeralFeeId);
            Double jqfuneralFee=DecimalUtil.twoDecimalTOFourFromFive((t_funeralFee/destimateTwoTotal)*dcompulsoryInsuranceTwoFee);//丧葬费交强险
            Double syfuneralFee=DecimalUtil.twoDecimalTOFourFromFive((t_funeralFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);//丧葬费商业险
            if(jqfuneralFee>0){
                caseMediationClaimReport4.setCommerMoney(jqfuneralFee);
                t_totalPcJq=t_totalPcJq+jqfuneralFee;
                caseMediationClaimReport4.setCompulMoney(syfuneralFee);
                t_totalPcSy=t_totalPcSy+syfuneralFee;
                this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReport4);
            }


            //*****第三部分
            Double estimateThreeTotal =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("estimateThreeTotal").toString()));
            Double compulsoryInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("compulsoryInsuranceThreeFee").toString()));
            t_totalCwJq=t_totalCwJq+compulsoryInsuranceThreeFee;
            Double commercialInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*blameQuotiety);
            t_totalCwSy=t_totalCwSy+compulsoryInsuranceThreeFee;
            Double ct_commercialInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*(1-blameQuotiety));
            CaseMediationClaimReport caseMediationClaimReportCw=this.caseMediationClaimReportMapper.selectByPrimaryKey(t_financialLossId);
            caseMediationClaimReportCw.setCommerMoney(compulsoryInsuranceThreeFee);
            caseMediationClaimReportCw.setCompulMoney(commercialInsuranceThreeFee);
            this.caseMediationClaimReportMapper.updateByPrimaryKeySelective(caseMediationClaimReportCw);
        }
            //把交强险，商业险，赔偿总额等数据写入结案报告
        CaseMediationClaim caseMediationClaim=this.caseMediationClaimMapper.queryByCaseId(caseId);
        caseMediationClaim.setCpsMoney(DecimalUtil.twoDecimalTOFourFromFive(t_totalYlJq+t_totalPcJq+t_totalCwJq));//交强险
        caseMediationClaim.setCocMoney(DecimalUtil.twoDecimalTOFourFromFive(t_totalYlSy+t_totalPcSy+t_totalCwSy));//商业险
        caseMediationClaim.setCptMoney(DecimalUtil.twoDecimalTOFourFromFive(t_totalYlJq+t_totalPcJq+t_totalCwJq+t_totalYlSy+t_totalPcSy+t_totalCwSy+causeTroubleFee));//保险公司赔偿金额
        caseMediationClaim.setFinalLoanMoney(causeTroubleFee);
        this.caseMediationClaimMapper.updateByPrimaryKeySelective(caseMediationClaim);
    }
    /**
     * 初始化主数据
     * @param caseMediationClaim  索赔报告
     * @param caseCenterInfo    案件中心信息
     * @return
     */
    private CaseMediationClaim initData(CaseMediationClaim caseMediationClaim,CaseCenterInfo caseCenterInfo){
        try {
            Map<String,Object> caseCenterInfoMap = new HashMap<>();
            caseCenterInfoMap.put("caseNo",caseCenterInfo.getCaseNo());
            PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectPEAByCaseCenterInfoNew(caseCenterInfoMap);
            PaymentEstimateInquiryDto paymentEstimateInquiryDto = caseCenterInfoMapper.selectNewestPayInquiryNew(caseCenterInfo.getId());//最新测算报价信息
            if (paymentEstimateInquiryDto != null){
                caseMediationClaim.setLoanMoney(paymentEstimateInquiryDto.getTotalLoanFee());
            }
            if (paymentEstimateApply != null){
                caseMediationClaim.setAppStandType(paymentEstimateApply.getIrrType() == null ? "" : paymentEstimateApply.getIrrType().toString());
                caseMediationClaim.setAccidentDutyType(paymentEstimateApply.getMyAccidentLiability() == null ? "" : paymentEstimateApply.getMyAccidentLiability().toString());
                caseMediationClaim.setHurtTrafficType(paymentEstimateApply.getMyTarfficStatus() == null ? "" : paymentEstimateApply.getMyTarfficStatus().toString());
                caseMediationClaim.setPartyTrafficeType(paymentEstimateApply.getOtherTarfficStatus() == null ? "" : paymentEstimateApply.getOtherTarfficStatus().toString());
                caseMediationClaim.setInvalidismGrade(paymentEstimateApply.getInvalidismGrade());
            }
            caseMediationClaim.setUserName(caseCenterInfo.getCaseName());
            caseMediationClaim.setClaimerId(caseCenterInfo.getClaimantId());
            caseMediationClaim.setClaimerName(caseCenterInfo.getClaimantName());
            caseMediationClaim.setClaimTime(new Date());
            caseMediationClaim.setCardNumber(caseCenterInfo.getCarNo());
            caseMediationClaim.setOutInsuranceTime(caseCenterInfo.getDangerTime());
            caseMediationClaim.setCaseCommitTime(new Date());

            CaseCenterInfoExtend2 caseCenterInfoExtend2 = caseCenterInfoExtend2Mapper.selectByPrimaryKey(caseCenterInfo.getId());
            if(caseCenterInfoExtend2 != null){
                //是否已鉴定
                if(caseCenterInfoExtend2.getIsAgreeAppraisal()!=null){
                    if(caseCenterInfoExtend2.getIsAgreeAppraisal() ==1){
                        caseMediationClaim.setDetermineType("1");
                    }else if(caseCenterInfoExtend2.getIsAgreeAppraisal() ==2){
                        caseMediationClaim.setDetermineType("0");
                    }
                }
                //是否多车事故
                if(caseCenterInfoExtend2.getAccidentType()!=null){
                    if(caseCenterInfoExtend2.getAccidentType()==1 || caseCenterInfoExtend2.getAccidentType()==2){
                        caseMediationClaim.setSomeCarType("0");
                    }else if(caseCenterInfoExtend2.getAccidentType() ==3){
                        caseMediationClaim.setSomeCarType("1");
                    }
                }
            }
            //伤残等级
            Map<String,Object> map = new HashMap<>();
            map.put("caseId",caseCenterInfo.getId());
            map.put("caseNo",caseCenterInfo.getCaseNo());
            InvalidismEstimate invalidismEstimate= invalidismEstimateMapper.selectInvalidismEstimateByInfo(map);
            if(invalidismEstimate!=null){
                InvalidismEstimateReport report = invalidismEstimateReportMapper.queryApplyByEstimateId(invalidismEstimate.getId());
                if(report!=null){
                    //转换成伤残等级 字符串
                    String gradeStr = "";
                    String grade = report.getInvalidismGrade();
                    if (!"".equals(grade) && grade != null) {
                        gradeStr = convertGradeStr(grade);
                    }else {
                        gradeStr = gradeStr.concat("无级");
                    }
                    caseMediationClaim.setInvalidismGrade(gradeStr);
                }
            }

        }catch (Exception e){
            e.printStackTrace();//打印出报错信息，但初始化数据还需返回已初始化的数据
        }
        return caseMediationClaim;
    }

    /**
     *初始化详情数据(案件赔偿及保险理赔方案)
     * @param caseCenterInfo 案件中心信息
     * @Param map              测算项目名称与公估报告项目名称对应
     * @param projectName     项目名称
     * @return
     */
    private CaseMediationClaimReport initDataDetail(CaseCenterInfo caseCenterInfo,Map mapNames,String projectName){
        CaseMediationClaimReport caseMediationClaimReport= new CaseMediationClaimReport();
        caseMediationClaimReport.setProjectName(projectName);
        caseMediationClaimReport.setCaseId(caseCenterInfo.getId());
        caseMediationClaimReport.setAuditingMoney(0D);
        caseMediationClaimReport.setIcAuditingMoney(0D);
        caseMediationClaimReport.setOpinionMoney(0D);
        Double amount  = 0D;
        Map<String,Object> caseCenterInfoMap = new HashMap<>();
        caseCenterInfoMap.put("caseNo", caseCenterInfo.getCaseNo());
        PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectPEAByCaseCenterInfoNew(caseCenterInfoMap);
        if (paymentEstimateApply != null){
            Map<String,Object> map = new HashMap<>();
            map.put("paymentEstimateId",paymentEstimateApply.getId());
            List<PaymentEstimateReport> reports = paymentEstimateReportMapper.selectPaymentEstimateReportByPeId(map);
            for (PaymentEstimateReport report : reports){
                //如果存在对应关系,则初始化金额数据  跳出循环
                if (report.getPaymentProject().equals(mapNames.get(projectName))){
                    amount = (report.getCheckMedicalFee() == null ? (report.getMedicalFee() == null ? 0 : report.getMedicalFee()) : report.getCheckMedicalFee());
                    break;
                }
            }
        }

//        if (caseCenterInfo.getType() == 2){
//            Map<String,Object> caseCenterInfoMap = new HashMap<>();
//            caseCenterInfoMap.put("caseNo",caseCenterInfo.getCaseNo());
//            PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectPEAByCaseCenterInfoNew(caseCenterInfoMap);
//            if (paymentEstimateApply != null){
//                Map<String,Object> map = new HashMap<>();
//                map.put("paymentEstimateId",paymentEstimateApply.getId());
//                List<PaymentEstimateReport> reports = paymentEstimateReportMapper.selectPaymentEstimateReportByPeId(map);
//                for (PaymentEstimateReport report : reports){
//                    //如果存在对应关系,则初始化金额数据  跳出循环
//                    if (report.getPaymentProject().equals(mapNames.get(projectName))){
//                        amount = (report.getCheckMedicalFee() == null ? (report.getMedicalFee() == null ? 0 : report.getMedicalFee()) : report.getCheckMedicalFee());
//                        break;
//                    }
//                }
//            }
//        }else{
//            List<CaseAssessmentObjReport> reports = caseAssessmentObjReportMapper.selectByCaseId(caseCenterInfo.getId());
//            for (CaseAssessmentObjReport report : reports){
//                //合计损失项目 并且 公估报告项目与索赔方案存在对应关系
////                if ("合计损失".equals(projectName) && mapNames.values().contains(report.getProjectName())){
////                    amount += report.getCheckAmount();
////                    continue;
////                }
//                //与公估报告存在对应关系,则计算金额
//                if (report.getProjectName().equals(mapNames.get(projectName))){
//                    amount = (report.getCheckAmount() == null ? 0 : report.getCheckAmount());
//                    break;
//                }
//            }
//        }
        caseMediationClaimReport.setOpinionMoney(DecimalUtil.twoDecimalTOFourFromFive(amount));
        caseMediationClaimReport.setAuditingMoney(DecimalUtil.twoDecimalTOFourFromFive(amount));
        return caseMediationClaimReport;
    }

    private Map returnMap(Long caseId,String projectName){
        Map map = new HashMap();
        map.put("caseId",caseId);
        map.put("projectName",projectName);
        return map;
    }

    /**
     * 转换成伤残等级 字符串  (1,2,3,1 to 一级,二级,三级,一级)
     * @param grade
     * @return
     */
    private String convertGradeStr(String grade){
        String gradeStr = "";
        if (!"".equals(grade) && grade != null) {
            String [] grades = grade.split(",");
            for (String s : grades) {
                if ("无".equals(s) || "".equals(s)){
                    gradeStr = gradeStr.concat("无级,");
                    continue;
                }
                try {
                    switch (Integer.valueOf(s)){
                        case 1 : gradeStr = gradeStr.concat("一级,"); break;
                        case 2 : gradeStr = gradeStr.concat("二级,"); break;
                        case 3 : gradeStr = gradeStr.concat("三级,"); break;
                        case 4 : gradeStr = gradeStr.concat("四级,"); break;
                        case 5 : gradeStr = gradeStr.concat("五级,"); break;
                        case 6 : gradeStr = gradeStr.concat("六级,"); break;
                        case 7 : gradeStr = gradeStr.concat("七级,"); break;
                        case 8 : gradeStr = gradeStr.concat("八级,"); break;
                        case 9 : gradeStr = gradeStr.concat("九级,"); break;
                        case 10 : gradeStr = gradeStr.concat("十级,"); break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else {
            gradeStr = gradeStr.concat("无级,");
        }
        return gradeStr.substring(0,gradeStr.length() - 1);
    }
}
