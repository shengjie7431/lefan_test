package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseMediationClaimLegalApi;
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
public class BackendCaseMediationClaimLegalApiImpl implements BackendCaseMediationClaimLegalApi{
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private CaseMediationClaimReportLegalMapper caseMediationClaimReportLegalMapper;
    @Autowired
    private CaseMediationClaimLegalMapper caseMediationClaimLegalMapper;
    @Autowired
    private PaymentEstimateApplyMapper paymentEstimateApplyMapper;
    @Autowired
    private CaseAssessmentObjReportMapper caseAssessmentObjReportMapper;
    @Autowired
    private CaseMediationClaimReportMapper caseMediationClaimReportMapper;
    @Autowired
    private CaseMediationClaimMapper caseMediationClaimMapper;
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
    @ApiMethod(descript = "查看索赔方案", value = "backend-case-mediation-claim-legal")
    @Override
    public ApiResponse getCaseMediationClaim(ApiRequest apiReq) {
        Long caseId = apiReq.getLong("caseId");
        HashMap<String,Object> map = new HashMap<>();
        CaseMediationClaimLegal caseMediationClaimLegal = caseMediationClaimLegalMapper.queryByCaseId(caseId);
        if (caseMediationClaimLegal == null){
            caseMediationClaimLegal = new CaseMediationClaimLegal();
            caseMediationClaimLegal.setId(null);
            //从案件中心带出可用数据(初始化数据)
            CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);
            caseMediationClaimLegal.setCaseId(caseCenterInfo.getId());
            caseMediationClaimLegal = initData(caseMediationClaimLegal,caseCenterInfo);
            caseMediationClaimLegalMapper.insertSelective(caseMediationClaimLegal);
        }
        caseMediationClaimLegal.setCaseId(caseId);
        map.put("caseMediationClaim",caseMediationClaimLegal);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }

    @ApiMethod(descript = "新增索赔方案",value = "backend-add-case-mediation-claim-legal")
    @Override
    public ApiResponse addCaseMediationClaim(ApiRequest apiReq) {
        Long currentUserId = apiReq.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        CaseMediationClaimLegal caseMediationClaimLegal = ConvertToBeanUtil.toBeanFromApiRequest(apiReq, CaseMediationClaimLegal.class);
        int ret = -1;
        if (caseMediationClaimLegal != null){
            ret = caseMediationClaimLegalMapper.insert(caseMediationClaimLegal);
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
            caseCenterInfo.setListStateName("诉讼预案审核中");
            caseCenterInfo.setOperReason(null);
            caseCenterInfoMapper.updateByPrimaryKeySelective(caseCenterInfo);
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "legal_shenhe", 2, 7);
        }
        caseMediationClaimLegal = caseMediationClaimLegalMapper.selectByPrimaryKey(caseMediationClaimLegal.getId());
        caseMediationClaimLegal.setStepCode(apiReq.getInt("stepCode"));//用于回调时，代表需要展示的页码
        caseMediationClaimLegal.setNoNext(apiReq.getInt("noNext"));//用于回调时，判断页面是关闭、还是下一页
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,caseMediationClaimLegal);
    }

    @ApiMethod(descript = "修改索赔方案",value = "backend-upd-case-mediation-claim-legal")
    @Override
    public ApiResponse updCaseMediationClaim(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        Long currentUserId = apiReq.getLong("operatorId");//当前登录人id  apiReq.getCurrentUserId()
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(currentUserId);
        CaseMediationClaimLegal caseMediationClaimLegal = caseMediationClaimLegalMapper.selectByPrimaryKey(id);
        if (caseMediationClaimLegal != null){
            caseMediationClaimLegal = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,caseMediationClaimLegal);
        }
        int ret = caseMediationClaimLegalMapper.updateByPrimaryKey(caseMediationClaimLegal);
        Double rate = caseMediationClaimLegal.getLiabilityRatio() == null ? 0D : caseMediationClaimLegal.getLiabilityRatio();

        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseMediationClaimLegal.getCaseId());
        Boolean b = false;
//        if (caseCenterInfo.getType() == 2){
//            AgentApply agentApply = agentApplyMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
//            if (agentApply.getAgentType() == 2){
//                b = true;
//            }
//        }
        if (!b){
            insuranceCalculation(caseMediationClaimLegal.getCaseId(),rate);
        }

        //insuranceCalculation(caseMediationClaimLegal.getCaseId(),rate);
//        if (ret > 0){
//            return new ApiResponse(ApiMsgEnum.SUCCESS);
//        }else{
//            return new ApiResponse(ApiMsgEnum.FAIL);
//        }
        if(apiReq.getInt("noNext")==3){
            //保存并提交审核
            caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(apiReq.getLong("caseId"));
            caseCenterInfo.setClaimState(7);
            caseCenterInfo.setOperReason(null);
            caseCenterInfo.setListStateName("诉讼预案审核中");
            caseCenterInfoMapper.updateByPrimaryKeySelective(caseCenterInfo);
            backendCaseStepsApi.update(caseCenterInfo, userInfo, true, "legal_shenhe", 2, 7);
        }
        caseMediationClaimLegal = caseMediationClaimLegalMapper.selectByPrimaryKey(id);
        caseMediationClaimLegal.setStepCode(apiReq.getInt("stepCode"));//用于回调时，代表需要展示的页码
        caseMediationClaimLegal.setNoNext(apiReq.getInt("noNext"));//用于回调时，判断页面是关闭、还是下一页
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,caseMediationClaimLegal);
    }

    @ApiMethod(descript = "查询索赔方案赔偿及保险理赔方案",value = "backend-get-case-mediation-claim-report-legal")
    @Override
    public ApiResponse getCaseMediationClaimReport(ApiRequest apiReq) {
        Long caseId = apiReq.getLong("caseId");
        String op = apiReq.getString("op");
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);
        List<CaseMediationClaimReportLegal> caseMediationClaimLegalReports = caseMediationClaimReportLegalMapper.selectByCaseId(caseId);
        CaseMediationClaimLegal caseMediationClaimLegal = caseMediationClaimLegalMapper.queryByCaseId(caseId);

        if (caseMediationClaimLegalReports == null || caseMediationClaimLegalReports.size() == 0){
            //初始化数据
            Map<String,Object> mapNames = null;
            /*if (caseCenterInfo.getType() == 2){
                mapNames = ReportProjectEnum.MEDIATION_TYPE_PAY.getMap();//mapNames  key 诉讼方案的项目    对应  value   测算项目
            }else{
                mapNames = ReportProjectEnum.MEDIATION_TYPE.getMap();//mapNames  key 诉讼方案的项目    对应  value   公估报告的项目
            }*/
            mapNames = ReportProjectEnum.MEDIATION_TYPE_PAY.getMap();//mapNames  key 诉讼方案的项目    对应  value   测算项目
            //如果索赔方案项目不为NULL
            List<CaseMediationClaimReport> claimReports = caseMediationClaimReportMapper.selectByCaseId(caseCenterInfo.getId());
            if (claimReports != null){
                mapNames = ReportProjectEnum.MEDIATION_LEGAL_TYPE.getMap();//诉讼方案项目   对应  索赔方案项目
            }
            if ("view".equals(op)){
                for (String name : mapNames.keySet()){
                    CaseMediationClaimReportLegal caseMediationClaimReport = initDataDetail(caseCenterInfo,mapNames,name,claimReports);
                    caseMediationClaimLegalReports.add(caseMediationClaimReport);
                }
            }else{
                for (String name : mapNames.keySet()){
                    CaseMediationClaimReportLegal caseMediationClaimReport = initDataDetail(caseCenterInfo,mapNames,name,claimReports);
                    caseMediationClaimReportLegalMapper.insert(caseMediationClaimReport);
                }
                Double rate = 0D;
                if (caseMediationClaimLegal != null){
                    rate = caseMediationClaimLegal.getLiabilityRatio() == null ? 0D : caseMediationClaimLegal.getLiabilityRatio();
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
                caseMediationClaimLegalReports = caseMediationClaimReportLegalMapper.selectByCaseId(caseId);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,caseMediationClaimLegalReports.size(),caseMediationClaimLegalReports);
    }

    @ApiMethod(descript = "修改案件赔偿及保险理赔方案",value = "backend-upd-case-mediation-claim-report-legal")
    @Override
    public ApiResponse updCaseMediationClaimReport(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        String type = apiReq.getString("type");
        String value = apiReq.getString("value");
        CaseMediationClaimReportLegal recode = caseMediationClaimReportLegalMapper.selectByPrimaryKey(id);
        CaseMediationClaimLegal caseMediationClaimLegal = caseMediationClaimLegalMapper.queryByCaseId(recode.getCaseId());
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
        caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(recode);
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(recode.getCaseId());
        //计算合计损失
        Double amount = 0D;
        if ("1".equals(type)){
            if (!"合计损失".equals(recode.getProjectName())){
                CaseMediationClaimReportLegal claimReportAll = caseMediationClaimReportLegalMapper.selectByCaseIdOrProjectName(returnMap(recode.getCaseId(),"合计损失"));
                List<CaseMediationClaimReportLegal> reports = caseMediationClaimReportLegalMapper.selectByCaseId(recode.getCaseId());
                for (CaseMediationClaimReportLegal report : reports){
                    amount += report.getOpinionMoney();
                }
                claimReportAll.setOpinionMoney(amount - claimReportAll.getOpinionMoney());//计算的是所有项目的总和 减去 多加的合计损失
                claimReportAll.setAuditingMoney(claimReportAll.getOpinionMoney());
                caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(claimReportAll);
                Double rate = caseMediationClaimLegal.getLiabilityRatio() == null ? 0D : caseMediationClaimLegal.getLiabilityRatio();

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
                CaseMediationClaimReportLegal claimReportAll = caseMediationClaimReportLegalMapper.selectByCaseIdOrProjectName(returnMap(recode.getCaseId(),"合计损失"));
                List<CaseMediationClaimReportLegal> reports = caseMediationClaimReportLegalMapper.selectByCaseId(recode.getCaseId());
                for (CaseMediationClaimReportLegal report : reports){
                    amount += report.getAuditingMoney();
                }
                claimReportAll.setAuditingMoney(amount - claimReportAll.getAuditingMoney());//计算的是所有项目的总和 减去 多加的合计损失
                caseMediationClaimReportLegalMapper.updateByPrimaryKey(claimReportAll);
                Double rate = caseMediationClaimLegal.getLiabilityRatio() == null ? 0D : caseMediationClaimLegal.getLiabilityRatio();
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
    /**
     * 初始化主数据
     * @param caseMediationClaim  索赔报告
     * @param caseCenterInfo    案件中心信息
     * @return
     */
    private CaseMediationClaimLegal initData(CaseMediationClaimLegal caseMediationClaim,CaseCenterInfo caseCenterInfo){
        try {
            Map<String,Object> caseCenterInfoMap = new HashMap<>();
            caseCenterInfoMap.put("caseNo",caseCenterInfo.getCaseNo());
            PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectPEAByCaseCenterInfoNew(caseCenterInfoMap);
            PaymentEstimateInquiryDto paymentEstimateInquiryDto = caseCenterInfoMapper.selectNewestPayInquiryNew(caseCenterInfo.getId());//最新测算报价信息

            CaseMediationClaim caseMediationClaim1 = caseMediationClaimMapper.queryByCaseId(caseCenterInfo.getId());
            if (caseMediationClaim1 != null){
                caseMediationClaim.setLiabilityRatio(caseMediationClaim1.getLiabilityRatio());
                //支付伤者 驾驶员 保险公司金额
                caseMediationClaim.setPayWoundedMoney(caseMediationClaim1.getPayWoundedMoney());
                caseMediationClaim.setPayDriverMoney(caseMediationClaim1.getPayDriverMoney());
                caseMediationClaim.setPaySafeMoney(caseMediationClaim1.getPaySafeMoney());

                caseMediationClaim.setLoanMoney(caseMediationClaim1.getLoanMoney());//贷款金额
                caseMediationClaim.setPartyName(caseMediationClaim1.getPartyName());//肇事人
                caseMediationClaim.setPartyTel(caseMediationClaim1.getPartyTel());//肇事方电话
                caseMediationClaim.setCardNumber(caseMediationClaim1.getCardNumber());//车牌号
                caseMediationClaim.setInsuranceCompany(caseMediationClaim1.getInsuranceCompany());//保险公司
                caseMediationClaim.setDetermineType(caseMediationClaim1.getDetermineType());//是否已鉴定
                caseMediationClaim.setInvalidismGrade(caseMediationClaim1.getInvalidismGrade());//伤残等级
                caseMediationClaim.setPayInsuranceType(caseMediationClaim1.getPayInsuranceType());//是否投保交强险
                caseMediationClaim.setTradeAmount(caseMediationClaim1.getTradeAmount());//商业三者险限额
                caseMediationClaim.setSomeCarType(caseMediationClaim1.getSomeCarType());//是否多车事故
                caseMediationClaim.setDisclaimerType(caseMediationClaim1.getDisclaimerType());//肇事方有无免责情形
                caseMediationClaim.setInsOfficerName(caseMediationClaim1.getInsOfficerName());//保险公司调解员
                caseMediationClaim.setInsOfficerTel(caseMediationClaim1.getInsOfficerTel());//保险公司调解员电话
            }

            if (paymentEstimateInquiryDto != null){
                caseMediationClaim.setLoanMoney(paymentEstimateInquiryDto.getTotalLoanFee());
            }
            if (paymentEstimateApply != null){
                caseMediationClaim.setAppStandType(paymentEstimateApply.getIrrType() == null ? "" : paymentEstimateApply.getIrrType().toString());
                caseMediationClaim.setAccidentDutyType(paymentEstimateApply.getMyAccidentLiability() == null ? "" : paymentEstimateApply.getMyAccidentLiability().toString());
                caseMediationClaim.setHurtTrafficType(paymentEstimateApply.getMyTarfficStatus() == null ? "" : paymentEstimateApply.getMyTarfficStatus().toString());
                caseMediationClaim.setPartyTrafficeType(paymentEstimateApply.getOtherTarfficStatus() == null ? "" : paymentEstimateApply.getOtherTarfficStatus().toString());
            }
            caseMediationClaim.setUserName(caseCenterInfo.getCaseName());
            caseMediationClaim.setClaimerId(caseCenterInfo.getClaimantId());
            caseMediationClaim.setClaimerName(caseCenterInfo.getClaimantName());
            caseMediationClaim.setClaimTime(new Date());
//            caseMediationClaim.setCardNumber(caseCenterInfo.getCarNo());
            caseMediationClaim.setOutInsuranceTime(caseCenterInfo.getDangerTime());
            caseMediationClaim.setCaseCommitTime(new Date());

            //初始化索赔预案中的基础数据

        }catch (Exception e){
            e.printStackTrace();//打印出报错信息，但初始化数据还需返回已初始化的数据
        }
        return caseMediationClaim;
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
        List<CaseMediationClaimReportLegal> caseClosedObjReportList = this.caseMediationClaimReportLegalMapper.selectByCaseId(caseId);
        for(CaseMediationClaimReportLegal caseMediationClaimReport:caseClosedObjReportList){
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
              /*  t_totalFee=caseMediationClaimReport.getAuditingMoney();*/
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
        CaseMediationClaimLegal caseMediationClaim1=this.caseMediationClaimLegalMapper.queryByCaseId(caseId);
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
            CaseMediationClaimReportLegal caseMediationClaimReportLegal=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_medicalFeeId);
            Double jqMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/estimateOneTotal) *compulsoryInsuranceOneFee);//医疗费交强险
            Double  syMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/estimateOneTotal)*commercialInsuranceOneFee);//医疗费商业险
            if(jqMedicalFee>0){
                caseMediationClaimReportLegal.setCommerMoney(jqMedicalFee);
                t_totalYlJq=t_totalYlJq+jqMedicalFee;
                caseMediationClaimReportLegal.setCompulMoney(syMedicalFee);
                t_totalYlSy=t_totalYlSy+syMedicalFee;
                this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLegal);
            }


            //伙食补助费
            CaseMediationClaimReportLegal caseMediationClaimReportLega2=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_hospitalFoodFeeId);
            Double jqhospitalFoodFee=DecimalUtil.twoDecimalTOFourFromFive((t_hospitalFoodFee/estimateOneTotal)*compulsoryInsuranceOneFee);//伙食补助费交强险
            Double syhospitalFoodFee=DecimalUtil.twoDecimalTOFourFromFive((t_hospitalFoodFee/estimateOneTotal)*commercialInsuranceOneFee);//伙食补助商业险
            if(jqhospitalFoodFee>0){
                caseMediationClaimReportLega2.setCommerMoney(jqhospitalFoodFee);
                t_totalYlJq=t_totalYlJq+jqhospitalFoodFee;
                caseMediationClaimReportLega2.setCompulMoney(syhospitalFoodFee);
                t_totalYlSy=t_totalYlSy+syhospitalFoodFee;
                this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega2);
            }

            //营养费
            CaseMediationClaimReportLegal caseMediationClaimReportLega3=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_nutritionFeeId);
            Double  jqnutritionFee=DecimalUtil.twoDecimalTOFourFromFive((t_nutritionFee/estimateOneTotal)*compulsoryInsuranceOneFee);//营养费交强险
            Double synutritionFee=DecimalUtil.twoDecimalTOFourFromFive((t_nutritionFee/estimateOneTotal)*commercialInsuranceOneFee);//营养费商业险
            if(jqnutritionFee>0){
                caseMediationClaimReportLega3.setCommerMoney(jqnutritionFee);
                t_totalYlJq=t_totalYlJq+jqnutritionFee;
                caseMediationClaimReportLega3.setCompulMoney(synutritionFee);
                t_totalYlSy=t_totalYlSy+synutritionFee;
                this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega3);
            }




            //后续治疗费
            CaseMediationClaimReportLegal caseMediationClaimReportLega4=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_againCureFeeId);
            Double jqagainCureFee=DecimalUtil.twoDecimalTOFourFromFive((t_againCureFee/estimateOneTotal)*compulsoryInsuranceOneFee);//后续治疗费交强险
            Double syagainCureFee=DecimalUtil.twoDecimalTOFourFromFive((t_againCureFee/estimateOneTotal)*commercialInsuranceOneFee);//后续治疗费商业险
            if(jqagainCureFee>0){
                caseMediationClaimReportLega4.setCommerMoney(jqagainCureFee);
                t_totalYlJq=t_totalYlJq+jqagainCureFee;
                caseMediationClaimReportLega4.setCompulMoney(syagainCureFee);
                t_totalYlSy=t_totalYlSy+syagainCureFee;
                this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega4);
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
                CaseMediationClaimReportLegal caseMediationClaimReportLega5=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_nursingFeeId);
                Double jqnursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//护理费交强险
                Double  synursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal)*commercialInsuranceTwoFee);//护理费商业险
                if(jqnursingFee>0){
                    caseMediationClaimReportLega5.setCommerMoney(jqnursingFee);
                    t_totalPcJq=t_totalPcJq+jqnursingFee;
                    caseMediationClaimReportLega5.setCompulMoney(synursingFee);
                    t_totalPcSy=t_totalPcSy+synursingFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega5);
                }



                //误工费
                CaseMediationClaimReportLegal caseMediationClaimReportLega6=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_lossWordFeeId);
                Double jqlossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//误工费交强险
                Double  sylossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal)*commercialInsuranceTwoFee);//误工费商业险
                if(jqlossWordFee>0){
                    caseMediationClaimReportLega6.setCommerMoney(jqlossWordFee);
                    t_totalPcJq=t_totalPcJq+jqlossWordFee;
                    caseMediationClaimReportLega6.setCompulMoney(sylossWordFee);
                    t_totalPcSy=t_totalPcSy+sylossWordFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega6);
                }



                //残疾赔偿金
                CaseMediationClaimReportLegal caseMediationClaimReportLega7=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_invalidismIndemnifyFeeId);
                Double jqinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//残疾赔偿金交强险
                Double  syinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal)*commercialInsuranceTwoFee);//残疾赔偿金商业险
                if(jqinvalidismIndemnifyFee>0){
                    caseMediationClaimReportLega7.setCommerMoney(jqinvalidismIndemnifyFee);
                    t_totalPcJq=t_totalPcJq+jqinvalidismIndemnifyFee;
                    caseMediationClaimReportLega7.setCompulMoney(syinvalidismIndemnifyFee);
                    t_totalPcSy=t_totalPcSy+syinvalidismIndemnifyFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega7);
                }



                //精神抚慰金
                CaseMediationClaimReportLegal caseMediationClaimReportLega8=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_spiritComfortFeeId);
                Double jqspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive(t_spiritComfortFee);//精神抚慰金交强险

                if(jqspiritComfortFee>0){
                    caseMediationClaimReportLega8.setCommerMoney(jqspiritComfortFee);
                    t_totalPcJq=t_totalPcJq+jqspiritComfortFee;
                    caseMediationClaimReportLega8.setCompulMoney(0D);
                    t_totalPcSy=t_totalPcSy+0D;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega8);
                }


                //交通费
                CaseMediationClaimReportLegal caseMediationClaimReportLega9=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_trafficFeeId);
                Double jqtrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sytrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqtrafficFee>0){
                    caseMediationClaimReportLega9.setCommerMoney(jqtrafficFee);
                    t_totalPcJq=t_totalPcJq+jqtrafficFee;
                    caseMediationClaimReportLega9.setCompulMoney(sytrafficFee);
                    t_totalPcSy=t_totalPcSy+sytrafficFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega9);
                }



                //诉讼费
                CaseMediationClaimReportLegal caseMediationClaimReportLega10=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_litigationFeeId);
                Double jqlitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sylitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqlitigationFee>0){
                    caseMediationClaimReportLega10.setCommerMoney(jqlitigationFee);
                    t_totalPcJq=t_totalPcJq+jqlitigationFee;
                    caseMediationClaimReportLega10.setCompulMoney(sylitigationFee);
                    t_totalPcSy=t_totalPcSy+sylitigationFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega10);
                }



                //残疾器具费
                CaseMediationClaimReportLegal caseMediationClaimReportLega11=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_disabilityEquipmentFeeId);
                Double jqdisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sydisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqdisabilityEquipmentFee>0){
                    caseMediationClaimReportLega11.setCommerMoney(jqdisabilityEquipmentFee);
                    t_totalPcJq=t_totalPcJq+jqdisabilityEquipmentFee;
                    caseMediationClaimReportLega11.setCompulMoney(sydisabilityEquipmentFee);
                    t_totalPcSy=t_totalPcSy+sydisabilityEquipmentFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega11);
                }



                //鉴定费
                CaseMediationClaimReportLegal caseMediationClaimReportLega12=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_appraisalFeeId);
                Double jqappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  syappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqappraisalFee>0){
                    caseMediationClaimReportLega12.setCommerMoney(jqappraisalFee);
                    t_totalPcJq=t_totalPcJq+jqappraisalFee;
                    caseMediationClaimReportLega12.setCompulMoney(syappraisalFee);
                    t_totalPcSy=t_totalPcSy+syappraisalFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega12);
                }
            }else{
                //护理费
                CaseMediationClaimReportLegal caseMediationClaimReportLega5=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_nursingFeeId);
                Double jqnursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//护理费交强险
                Double  synursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal)*commercialInsuranceTwoFee);//护理费商业险
                if(jqnursingFee>0){
                    caseMediationClaimReportLega5.setCommerMoney(jqnursingFee);
                    t_totalPcJq=t_totalPcJq+jqnursingFee;
                    caseMediationClaimReportLega5.setCompulMoney(synursingFee);
                    t_totalPcSy=t_totalPcSy+jqnursingFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega5);
                }



                //误工费
                CaseMediationClaimReportLegal caseMediationClaimReportLega6=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_lossWordFeeId);
                Double jqlossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//误工费交强险
                Double  sylossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal)*commercialInsuranceTwoFee);//误工费商业险
                if(jqlossWordFee>0){
                    caseMediationClaimReportLega6.setCommerMoney(jqlossWordFee);
                    t_totalPcJq=t_totalPcJq+jqlossWordFee;
                    caseMediationClaimReportLega6.setCompulMoney(sylossWordFee);
                    t_totalPcSy=t_totalPcSy+sylossWordFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega6);
                }



                //残疾赔偿金
                CaseMediationClaimReportLegal caseMediationClaimReportLega7=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_invalidismIndemnifyFeeId);
                Double jqinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//残疾赔偿金交强险
                Double  syinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal)*commercialInsuranceTwoFee);//残疾赔偿金商业险
                if(jqinvalidismIndemnifyFee>0){
                    caseMediationClaimReportLega7.setCommerMoney(jqinvalidismIndemnifyFee);
                    t_totalPcJq=t_totalPcJq+jqinvalidismIndemnifyFee;
                    caseMediationClaimReportLega7.setCompulMoney(syinvalidismIndemnifyFee);
                    t_totalPcSy=t_totalPcSy+syinvalidismIndemnifyFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega7);
                }



                //精神抚慰金
                CaseMediationClaimReportLegal caseMediationClaimReportLega8=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_spiritComfortFeeId);
                Double jqspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive(110000);//精神抚慰金交强险
                Double  syspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/estimateTwoTotal)*commercialInsuranceTwoFee);//精神抚慰金商业险
                if(jqspiritComfortFee>0){
                    caseMediationClaimReportLega8.setCommerMoney(jqspiritComfortFee);
                    t_totalPcJq=t_totalPcJq+jqspiritComfortFee;
                    caseMediationClaimReportLega8.setCompulMoney(syspiritComfortFee);
                    t_totalPcSy=t_totalPcSy+syspiritComfortFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega8);
                }


                //交通费
                CaseMediationClaimReportLegal caseMediationClaimReportLega9=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_trafficFeeId);
                Double jqtrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sytrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqtrafficFee>0){
                    caseMediationClaimReportLega9.setCommerMoney(jqtrafficFee);
                    t_totalPcJq=t_totalPcJq+jqtrafficFee;
                    caseMediationClaimReportLega9.setCompulMoney(sytrafficFee);
                    t_totalPcSy=t_totalPcSy+sytrafficFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega9);
                }



                //诉讼费
                CaseMediationClaimReportLegal caseMediationClaimReportLega10=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_litigationFeeId);
                Double jqlitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sylitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqlitigationFee>0){
                    caseMediationClaimReportLega10.setCommerMoney(jqlitigationFee);
                    t_totalPcJq=t_totalPcJq+jqlitigationFee;
                    caseMediationClaimReportLega10.setCompulMoney(sylitigationFee);
                    t_totalPcSy=t_totalPcSy+sylitigationFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega10);
                }



                //残疾器具费
                CaseMediationClaimReportLegal caseMediationClaimReportLega11=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_disabilityEquipmentFeeId);
                Double jqdisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sydisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqdisabilityEquipmentFee>0){
                    caseMediationClaimReportLega11.setCommerMoney(jqdisabilityEquipmentFee);
                    t_totalPcJq=t_totalPcJq+jqdisabilityEquipmentFee;
                    caseMediationClaimReportLega11.setCompulMoney(sydisabilityEquipmentFee);
                    t_totalPcSy=t_totalPcSy+sydisabilityEquipmentFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega11);
                }



                //鉴定费
                CaseMediationClaimReportLegal caseMediationClaimReportLega12=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_appraisalFeeId);
                Double jqappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  syappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqappraisalFee>0){
                    caseMediationClaimReportLega12.setCommerMoney(jqappraisalFee);
                    t_totalPcJq=t_totalPcJq+jqappraisalFee;
                    caseMediationClaimReportLega12.setCompulMoney(syappraisalFee);
                    t_totalPcSy=t_totalPcSy+syappraisalFee;
                    this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega12);
                }
            }






            //*****第三部分
            Double estimateThreeTotal =Double.parseDouble(t_estimateThree.get("estimateThreeTotal").toString());
            Double compulsoryInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("compulsoryInsuranceThreeFee").toString());
            t_totalCwJq=t_totalCwJq+compulsoryInsuranceThreeFee;
            Double commercialInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*blameQuotiety;
            t_totalCwSy=t_totalCwSy+commercialInsuranceThreeFee;
            Double ct_commercialInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*(1-blameQuotiety);
            CaseMediationClaimReportLegal caseMediationClaimReportLega13=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_financialLossId);
            if(compulsoryInsuranceThreeFee>0){
                caseMediationClaimReportLega13.setCommerMoney(compulsoryInsuranceThreeFee);
                caseMediationClaimReportLega13.setCompulMoney(commercialInsuranceThreeFee);
                this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega13);
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
            CaseMediationClaimReportLegal caseMediationClaimReportLega1=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_medicalFeeId);
            Double jqMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/destimateOneTotal) *dcompulsoryInsuranceOneFee);//医疗费交强险
            Double  syMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/destimateOneTotal)*dcommercialInsuranceOneFee);//医疗费商业险
            if(jqMedicalFee>0){
                caseMediationClaimReportLega1.setCommerMoney(jqMedicalFee);
                t_totalYlJq=t_totalYlJq+jqMedicalFee;
                caseMediationClaimReportLega1.setCompulMoney(syMedicalFee);
                t_totalYlSy=t_totalYlSy+syMedicalFee;
                this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega1);
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
            CaseMediationClaimReportLegal caseMediationClaimReportLega2=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_deathIndemnifyFeeId);
            Double jqdeathIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_deathIndemnifyFee/destimateTwoTotal)*dcompulsoryInsuranceTwoFee);//死亡赔偿金交强险
            Double sydeathIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_deathIndemnifyFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);//死亡赔偿金商业险
            if(jqdeathIndemnifyFee>0){
                caseMediationClaimReportLega2.setCommerMoney(jqdeathIndemnifyFee);
                t_totalPcJq=t_totalPcJq+jqdeathIndemnifyFee;
                caseMediationClaimReportLega2.setCompulMoney(sydeathIndemnifyFee);
                t_totalPcSy=t_totalPcSy+sydeathIndemnifyFee;
                this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega2);
            }



            //精神抚慰金
            CaseMediationClaimReportLegal caseMediationClaimReportLega3=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_spiritComfortFeeId);
            Double jqspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/destimateTwoTotal)*dcompulsoryInsuranceTwoFee);//精神抚慰金
            Double syspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);//精神抚慰金
            if(jqspiritComfortFee>0){
                caseMediationClaimReportLega3.setCommerMoney(jqspiritComfortFee);
                t_totalPcJq=t_totalPcJq+jqspiritComfortFee;
                caseMediationClaimReportLega3.setCompulMoney(syspiritComfortFee);
                t_totalPcSy=t_totalPcSy+syspiritComfortFee;
                this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega3);
            }



            //丧葬费
            CaseMediationClaimReportLegal caseMediationClaimReportLega4=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_funeralFeeId);
            Double jqfuneralFee=DecimalUtil.twoDecimalTOFourFromFive((t_funeralFee/destimateTwoTotal)*dcompulsoryInsuranceTwoFee);//丧葬费交强险
            Double syfuneralFee=DecimalUtil.twoDecimalTOFourFromFive((t_funeralFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);//丧葬费商业险
            if(jqfuneralFee>0){
                caseMediationClaimReportLega4.setCommerMoney(jqfuneralFee);
                t_totalPcJq=t_totalPcJq+jqfuneralFee;
                caseMediationClaimReportLega4.setCompulMoney(syfuneralFee);
                t_totalPcSy=t_totalPcSy+syfuneralFee;
                this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLega4);
            }


            //*****第三部分
            Double estimateThreeTotal =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("estimateThreeTotal").toString()));
            Double compulsoryInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("compulsoryInsuranceThreeFee").toString()));
            t_totalCwJq=t_totalCwJq+compulsoryInsuranceThreeFee;
            Double commercialInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*blameQuotiety);
            t_totalCwSy=t_totalCwSy+compulsoryInsuranceThreeFee;
            Double ct_commercialInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*(1-blameQuotiety));
            CaseMediationClaimReportLegal caseMediationClaimReportLegaCw=this.caseMediationClaimReportLegalMapper.selectByPrimaryKey(t_financialLossId);
            caseMediationClaimReportLegaCw.setCommerMoney(compulsoryInsuranceThreeFee);
            caseMediationClaimReportLegaCw.setCompulMoney(commercialInsuranceThreeFee);
            this.caseMediationClaimReportLegalMapper.updateByPrimaryKeySelective(caseMediationClaimReportLegaCw);
        }
        //把交强险，商业险，赔偿总额等数据写入结案报告
        CaseMediationClaimLegal caseMediationClaim=this.caseMediationClaimLegalMapper.queryByCaseId(caseId);
        caseMediationClaim.setCpsMoney(DecimalUtil.twoDecimalTOFourFromFive(t_totalYlJq+t_totalPcJq+t_totalCwJq));//交强险
        caseMediationClaim.setCocMoney(DecimalUtil.twoDecimalTOFourFromFive(t_totalYlSy+t_totalPcSy+t_totalCwSy));//商业险
        caseMediationClaim.setCptMoney(DecimalUtil.twoDecimalTOFourFromFive(t_totalYlJq+t_totalPcJq+t_totalCwJq+t_totalYlSy+t_totalPcSy+t_totalCwSy+causeTroubleFee));//保险公司赔偿金额
        caseMediationClaim.setFinalLoanMoney(causeTroubleFee);
        this.caseMediationClaimLegalMapper.updateByPrimaryKeySelective(caseMediationClaim);
    }

    /**
     * 初始化项目详情数据
     * @param
     * @param
     * @return
     */
    private CaseMediationClaimReportLegal initDataDetail(CaseCenterInfo caseCenterInfo,Map mapNames,String projectName,List<CaseMediationClaimReport> claimReports){
        CaseMediationClaimReportLegal caseMediationClaimReport= new CaseMediationClaimReportLegal();
        caseMediationClaimReport.setProjectName(projectName);
        caseMediationClaimReport.setCaseId(caseCenterInfo.getId());
        caseMediationClaimReport.setAuditingMoney(0D);
        caseMediationClaimReport.setIcAuditingMoney(0D);
        caseMediationClaimReport.setOpinionMoney(0D);
        Double amount  = 0D;
        if (claimReports != null){
            for (CaseMediationClaimReport report : claimReports){
                //与索赔报告对应
                if (report.getProjectName().equals(mapNames.get(projectName))){
                    amount = (report.getAuditingMoney() == null ? 0 : report.getAuditingMoney());
                    break;
                }
            }
        }else{
            Map<String,Object> caseCenterInfoMap = new HashMap<>();
            caseCenterInfoMap.put("caseNo",caseCenterInfo.getCaseNo());
            PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectPEAByCaseCenterInfoNew(caseCenterInfoMap);
            if (paymentEstimateApply != null){
                Map<String,Object> map = new HashMap<>();
                map.put("paymentEstimateId",paymentEstimateApply.getId());
                List<PaymentEstimateReport> reports = paymentEstimateReportMapper.selectPaymentEstimateReportByPeId(map);
                for (PaymentEstimateReport report : reports){
//                    if (!"合计".equals(report.getPaymentProject()) && "合计损失".equals(projectName)){
//                        amount += report.getCheckMedicalFee();
//                        continue;
//                    }
                    //如果存在对应关系,则初始化金额数据  跳出循环
                    if (report.getPaymentProject().equals(mapNames.get(projectName))){
                        amount = (report.getCheckMedicalFee() == null ? (report.getMedicalFee() == null ? 0 : report.getMedicalFee()) : report.getCheckMedicalFee());
                        break;
                    }
                }
            }
//            if (caseCenterInfo.getType() == 2){
//                Map<String,Object> caseCenterInfoMap = new HashMap<>();
//                caseCenterInfoMap.put("caseNo",caseCenterInfo.getCaseNo());
//                PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectPEAByCaseCenterInfoNew(caseCenterInfoMap);
//                if (paymentEstimateApply != null){
//                    Map<String,Object> map = new HashMap<>();
//                    map.put("paymentEstimateId",paymentEstimateApply.getId());
//                    List<PaymentEstimateReport> reports = paymentEstimateReportMapper.selectPaymentEstimateReportByPeId(map);
//                    for (PaymentEstimateReport report : reports){
////                    if (!"合计".equals(report.getPaymentProject()) && "合计损失".equals(projectName)){
////                        amount += report.getCheckMedicalFee();
////                        continue;
////                    }
//                        //如果存在对应关系,则初始化金额数据  跳出循环
//                        if (report.getPaymentProject().equals(mapNames.get(projectName))){
//                            amount = (report.getCheckMedicalFee() == null ? (report.getMedicalFee() == null ? 0 : report.getMedicalFee()) : report.getCheckMedicalFee());
//                            break;
//                        }
//                    }
//                }
//            }else{
//                List<CaseAssessmentObjReport> reports = caseAssessmentObjReportMapper.selectByCaseId(caseCenterInfo.getId());
//                for (CaseAssessmentObjReport report : reports){
//                    //合计损失项目 并且 公估报告项目与索赔方案存在对应关系
////                if ("合计损失".equals(projectName) && mapNames.values().contains(report.getProjectName())){
////                    amount += report.getCheckAmount();
////                    continue;
////                }
//                    //与公估报告存在对应关系,则计算金额
//                    if (report.getProjectName().equals(mapNames.get(projectName))){
//                        amount = (report.getCheckAmount() == null ? 0 : report.getCheckAmount());
//                        break;
//                    }
//                }
//            }
        }
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
}
