package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSON;
import com.lefancrm.apicenter.backendapi.BackendCaseCloseReportApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.PaymentEstimateInquiryDto;
import com.lefancrm.apicenter.enums.ReportProjectEnum;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.RedisService;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
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

import java.util.*;

@Service
@ApiService(descript = "结案报告API")
public class BackendCaseCloseReportImpl extends BaseServiceImpl implements BackendCaseCloseReportApi {
    @Autowired
    private CaseClosedReportMapper caseCloseReportMapper;
    @Autowired
    private CasePayInfoMapper casePayInfoMapper;
    @Autowired
    private CaseClosedObjReportMapper caseCloseObjReportMapper;
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private PaymentEstimateApplyMapper paymentEstimateApplyMapper;
    @Autowired
    private CaseMediationClaimReportMapper caseMediationClaimReportMapper;
    @Autowired
    private CaseMediationClaimReportLegalMapper caseMediationClaimReportLegalMapper;
    @Autowired
    private CaseMediationClaimMapper caseMediationClaimMapper;
    @Autowired
    private CaseMediationClaimLegalMapper caseMediationClaimLegalMapper;
    @Autowired
    private BlameQuotietyMapper blameQuotietyMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AgentApplyMapper agentApplyMapper;

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

    @Value("${report_path_url}")
    private String reportPathUrl;
    @ApiMethod(descript = "新增结案报告",value = "backend-add-case-close-report")
    @Override
    public ApiResponse addCaseCloseReport(ApiRequest apiReq) {
        CaseClosedReport caseCloseReport = ConvertToBeanUtil.toBeanFromApiRequest(apiReq, CaseClosedReport.class);
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseCloseReport.getCaseId());
        saveCasePayInfo(caseCloseReport, caseCenterInfo);
        int ret = -1;
        if (caseCloseReport != null){
            ret = caseCloseReportMapper.insert(caseCloseReport);
        }
//        if (ret > 0){
//            return new ApiResponse(ApiMsgEnum.SUCCESS);
//        }else{
//            return new ApiResponse(ApiMsgEnum.FAIL);
//        }
        if(apiReq.getInt("noNext")==3){
            //保存并提交审核
            caseCenterInfo.setClaimState(10);
            caseCenterInfo.setOperReason(null);
            caseCenterInfo.setCloseReportState(2);
            caseCenterInfoMapper.updateByPrimaryKeySelective(caseCenterInfo);
        }
        caseCloseReport = caseCloseReportMapper.selectByPrimaryKey(caseCloseReport.getId());
        caseCloseReport.setNoNext(apiReq.getInt("noNext"));
        caseCloseReport.setStepCode(apiReq.getInt("stepCode"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,caseCloseReport);
    }

    @ApiMethod(descript = "修改结案报告",value = "backend-upd-case-close-report")
    @Override
    public ApiResponse updCaseCloseReport(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        CaseClosedReport caseCloseReport = caseCloseReportMapper.selectByPrimaryKey(id);
        if (caseCloseReport != null){
            caseCloseReport = ConvertToBeanUtil.toBeanFromApiRequest(apiReq, caseCloseReport);
        }
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseCloseReport.getCaseId());
        saveCasePayInfo(caseCloseReport,caseCenterInfo);
        int ret = caseCloseReportMapper.updateByPrimaryKey(caseCloseReport);
        Double rate = caseCloseReport.getCompensateRate() == null ? 0D : caseCloseReport.getCompensateRate();

        Boolean b = false;
        insuranceCalculation(caseCloseReport.getCaseId(),rate);
        //insuranceCalculation(caseCloseReport.getCaseId(),rate);
//        if (ret > 0){
//            return new ApiResponse(ApiMsgEnum.SUCCESS);
//        }else{
//            return new ApiResponse(ApiMsgEnum.FAIL);
//        }
        if(apiReq.getInt("noNext")==3){
            if (caseCenterInfo.getGradationState() == 3){
                caseCenterInfo.setClaimState(10);
                caseCenterInfo.setListStateName("结案报告审核中");
                caseCenterInfo.setOperReason(null);
            }else if (caseCenterInfo.getGradationState() == 6){
                caseCenterInfo.setClaimState(11);
                caseCenterInfo.setListStateName("结案报告审核中");
                caseCenterInfo.setOperReason(null);
            }
            //保存并提交审核
            caseCenterInfo.setCloseReportState(2);
            caseCenterInfoMapper.updateByPrimaryKey(caseCenterInfo);
        }
        caseCloseReport = caseCloseReportMapper.selectByPrimaryKey(id);
        caseCloseReport.setNoNext(apiReq.getInt("noNext"));
        caseCloseReport.setStepCode(apiReq.getInt("stepCode"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,caseCloseReport);
    }

    /**
     * 保存待支付信息 账户名等
     * @param caseCloseReport
     * @param caseCenterInfo
     */
    private void saveCasePayInfo(CaseClosedReport caseCloseReport,CaseCenterInfo caseCenterInfo){
        if (caseCenterInfo != null){
            List<CasePayInfo> infos = casePayInfoMapper.selectByCaseId(caseCenterInfo.getId());
            if (infos != null && infos.size() > 0){
                for (CasePayInfo casePayInfo : infos){
                    if ("律师费".equals(casePayInfo.getPayName())){
                        casePayInfo.setPayMoney(caseCloseReport.getLawyerMoney());
                        casePayInfo.setAccountName(caseCloseReport.getLawyerMoneyAccName());
                        casePayInfo.setBankName(caseCloseReport.getLawyerMoneyBankName());
                        casePayInfo.setCardNo(caseCloseReport.getLawyerMoneyCardNo());
                        casePayInfo.setRemarks(caseCloseReport.getLawyerMoneyRemarks());
                    }else if ("鉴定费".equals(casePayInfo.getPayName())){
                        casePayInfo.setPayMoney(caseCloseReport.getAppraisalMoney());
                        casePayInfo.setAccountName(caseCloseReport.getAppraisalMoneyAccName());
                        casePayInfo.setBankName(caseCloseReport.getAppraisalMoneyBankName());
                        casePayInfo.setCardNo(caseCloseReport.getAppraisalMoneyCardNo());
                        casePayInfo.setRemarks(caseCloseReport.getAppraisalMoneyRemarks());
                    }else if ("诉讼费".equals(casePayInfo.getPayName())){
                        casePayInfo.setPayMoney(caseCloseReport.getLitigateMoney());
                        casePayInfo.setAccountName(caseCloseReport.getLitigateMoneyAccName());
                        casePayInfo.setBankName(caseCloseReport.getLitigateMoneyBankName());
                        casePayInfo.setCardNo(caseCloseReport.getLitigateMoneyCardNo());
                        casePayInfo.setRemarks(caseCloseReport.getLitigateMoneyRemarks());
                    }else if ("索赔费用".equals(casePayInfo.getPayName())){
                        casePayInfo.setPayMoney(caseCloseReport.getClaimMoney());
                        casePayInfo.setAccountName(caseCloseReport.getClaimMoneyAccName());
                        casePayInfo.setBankName(caseCloseReport.getClaimMoneyBankName());
                        casePayInfo.setCardNo(caseCloseReport.getClaimMoneyCardNo());
                        casePayInfo.setRemarks(caseCloseReport.getClaimMoneyRemarks());
                    }else if ("其他费用".equals(casePayInfo.getPayName())){
                        casePayInfo.setPayMoney(caseCloseReport.getOtherMoney());
                        casePayInfo.setAccountName(caseCloseReport.getOtherMoneyAccName());
                        casePayInfo.setBankName(caseCloseReport.getOtherMoneyBankName());
                        casePayInfo.setCardNo(caseCloseReport.getOtherMoneyCardNo());
                        casePayInfo.setRemarks(caseCloseReport.getOtherMoneyRemarks());
                    }
                    casePayInfoMapper.updateByPrimaryKey(casePayInfo);
                }
            }else {
                //判断是否已存在待支付信息 ,已存在则修改 否则新增
                String [] payNames = {"律师费","鉴定费","诉讼费","索赔费用","其他费用"};
                for (String name : payNames){
                    CasePayInfo casePayInfo = new CasePayInfo();
                    casePayInfo.setPayName(name);
                    casePayInfo.setCaseId(caseCenterInfo.getId());
                    casePayInfo.setCaseTitle(caseCenterInfo.getCaseTitle());
                    casePayInfo.setUserName(caseCenterInfo.getCaseName());
                    if ("律师费".equals(name)){
                        casePayInfo.setPayMoney(caseCloseReport.getLawyerMoney());
                        casePayInfo.setAccountName(caseCloseReport.getLawyerMoneyAccName());
                        casePayInfo.setBankName(caseCloseReport.getLawyerMoneyBankName());
                        casePayInfo.setCardNo(caseCloseReport.getLawyerMoneyCardNo());
                        casePayInfo.setRemarks(caseCloseReport.getLawyerMoneyRemarks());
                    }else if ("鉴定费".equals(name)){
                        casePayInfo.setPayMoney(caseCloseReport.getAppraisalMoney());
                        casePayInfo.setAccountName(caseCloseReport.getAppraisalMoneyAccName());
                        casePayInfo.setBankName(caseCloseReport.getAppraisalMoneyBankName());
                        casePayInfo.setCardNo(caseCloseReport.getAppraisalMoneyCardNo());
                        casePayInfo.setRemarks(caseCloseReport.getAppraisalMoneyRemarks());
                    }else if ("诉讼费".equals(name)){
                        casePayInfo.setPayMoney(caseCloseReport.getLitigateMoney());
                        casePayInfo.setAccountName(caseCloseReport.getLitigateMoneyAccName());
                        casePayInfo.setBankName(caseCloseReport.getLitigateMoneyBankName());
                        casePayInfo.setCardNo(caseCloseReport.getLitigateMoneyCardNo());
                        casePayInfo.setRemarks(caseCloseReport.getLitigateMoneyRemarks());
                    }else if ("索赔费用".equals(name)){
                        casePayInfo.setPayMoney(caseCloseReport.getClaimMoney());
                        casePayInfo.setAccountName(caseCloseReport.getClaimMoneyAccName());
                        casePayInfo.setBankName(caseCloseReport.getClaimMoneyBankName());
                        casePayInfo.setCardNo(caseCloseReport.getClaimMoneyCardNo());
                        casePayInfo.setRemarks(caseCloseReport.getClaimMoneyRemarks());
                    }else if ("其他费用".equals(name)){
                        casePayInfo.setPayMoney(caseCloseReport.getOtherMoney());
                        casePayInfo.setAccountName(caseCloseReport.getOtherMoneyAccName());
                        casePayInfo.setBankName(caseCloseReport.getOtherMoneyBankName());
                        casePayInfo.setCardNo(caseCloseReport.getOtherMoneyCardNo());
                        casePayInfo.setRemarks(caseCloseReport.getOtherMoneyRemarks());
                    }
                    casePayInfo.setPayState(0);
                    casePayInfo.setAuditState(1);
                    casePayInfo.setCreateTime(new Date());
                    if (casePayInfo.getPayMoney() == null || casePayInfo.getPayMoney() == 0D){
                        continue;
                    }
                    casePayInfoMapper.insert(casePayInfo);
                }
            }
        }
    }

    @ApiMethod(descript = "获取结案报告",value = "backend-get-case-close-report")
    @Override
    public ApiResponse getCaseCloseReport(ApiRequest apiReq) {
        Long caseId = apiReq.getLong("caseId");
        String caseNo = apiReq.getString("caseNo");
        String op = apiReq.getString("op");
        HashMap<String,Object> map = new HashMap<>();
        CaseClosedReport caseCloseReport = caseCloseReportMapper.selectByCaseId(caseId);
        if (caseCloseReport == null){
            caseCloseReport = new CaseClosedReport();
            caseCloseReport.setId(null);
            //从案件中心带出可用数据(初始化数据)
            CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);
            caseCloseReport.setCaseId(caseCenterInfo.getId());
            caseCloseReport.setCaseNo(caseCenterInfo.getCaseNo());
            caseCloseReport = initData(caseCloseReport,caseCenterInfo);
            if ("view".equals(op)){

            }else{
                caseCloseReportMapper.insert(caseCloseReport);
            }
        }
        caseCloseReport.setCaseId(caseId);
        caseCloseReport.setCaseNo(caseNo);
        List<CasePayInfo> infos = casePayInfoMapper.selectByCaseId(caseId);
        for (CasePayInfo casePayInfo : infos){
            if ("律师费".equals(casePayInfo.getPayName())){
                caseCloseReport.setLawyerMoneyAccName(casePayInfo.getAccountName());
                caseCloseReport.setLawyerMoneyBankName(casePayInfo.getBankName());
                caseCloseReport.setLawyerMoneyCardNo(casePayInfo.getCardNo());
                caseCloseReport.setLawyerMoneyRemarks(casePayInfo.getRemarks());
            }else if ("鉴定费".equals(casePayInfo.getPayName())){
                caseCloseReport.setAppraisalMoneyAccName(casePayInfo.getAccountName());
                caseCloseReport.setAppraisalMoneyBankName(casePayInfo.getBankName());
                caseCloseReport.setAppraisalMoneyCardNo(casePayInfo.getCardNo());
                caseCloseReport.setAppraisalMoneyRemarks(casePayInfo.getRemarks());
            }else if ("诉讼费".equals(casePayInfo.getPayName())){
                caseCloseReport.setLitigateMoneyAccName(casePayInfo.getAccountName());
                caseCloseReport.setLitigateMoneyBankName(casePayInfo.getBankName());
                caseCloseReport.setLitigateMoneyCardNo(casePayInfo.getCardNo());
                caseCloseReport.setLitigateMoneyRemarks(casePayInfo.getRemarks());
            }else if ("索赔费用".equals(casePayInfo.getPayName())){
                caseCloseReport.setClaimMoneyAccName(casePayInfo.getAccountName());
                caseCloseReport.setClaimMoneyBankName(casePayInfo.getBankName());
                caseCloseReport.setClaimMoneyCardNo(casePayInfo.getCardNo());
                caseCloseReport.setClaimMoneyRemarks(casePayInfo.getRemarks());
            }else if ("其他费用".equals(casePayInfo.getPayName())){
                caseCloseReport.setOtherMoneyAccName(casePayInfo.getAccountName());
                caseCloseReport.setOtherMoneyBankName(casePayInfo.getBankName());
                caseCloseReport.setOtherMoneyCardNo(casePayInfo.getCardNo());
                caseCloseReport.setOtherMoneyRemarks(casePayInfo.getRemarks());
            }
        }
        map.put("caseCloseReport",caseCloseReport);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }

    @ApiMethod(descript = "修改案件赔偿及保险理赔方案",value = "backend-update-case-close-obj-report")
    @Override
    public ApiResponse updCaseCloseObjReport(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        String type = apiReq.getString("type");
        String value = apiReq.getString("value");
        CaseClosedObjReport recode = this.caseCloseObjReportMapper.selectByPrimaryKey(id);
        CaseClosedReport caseClosedReport = caseCloseReportMapper.selectByCaseId(recode.getCaseId());
        // 1 主张赔偿金额 ,2 保险公司赔偿金额 ,3 驾驶员赔偿金额
        if ("1".equals(type)){
            recode.setOpinionMoney(Double.parseDouble(value == null || "".equals(value) ? "0" : value));
            recode.setInsOmpanyMoney(recode.getOpinionMoney());
        }else if("2".equals(type)){
            recode.setInsOmpanyMoney(Double.parseDouble(value == null || "".equals(value) ? "0" : value));
        }else if("3".equals(type)){
            recode.setDriverMoney(Double.parseDouble(value == null || "".equals(value) ? "0" : value));
        }
        int result = caseCloseObjReportMapper.updateByPrimaryKeySelective(recode);
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(recode.getCaseId());
        //只有更改主张赔偿金额项目的时候计算合计
        Double amount = 0D;
        if ("1".equals(type)){
            CaseClosedObjReport objReportAll = caseCloseObjReportMapper.selectByCaseIdOrProjectName(returnMap(recode.getCaseId(),"合计损失"));
            if (!"合计损失".equals(recode.getObjectName())){
                List<CaseClosedObjReport> reports = caseCloseObjReportMapper.queryByCaseId(recode.getCaseId());
                for (CaseClosedObjReport report : reports){
                    amount += report.getOpinionMoney();
                }
                objReportAll.setOpinionMoney(amount - objReportAll.getOpinionMoney());//计算的是所有项目的总和 减去 多加的合计损失
                objReportAll.setInsOmpanyMoney(objReportAll.getOpinionMoney());
                caseCloseObjReportMapper.updateByPrimaryKey(objReportAll);
            }
        }else if ("2".equals(type)){
            CaseClosedObjReport objReportAll = caseCloseObjReportMapper.selectByCaseIdOrProjectName(returnMap(recode.getCaseId(),"合计损失"));
            if (!"合计损失".equals(recode.getObjectName())){
                List<CaseClosedObjReport> reports = caseCloseObjReportMapper.queryByCaseId(recode.getCaseId());
                for (CaseClosedObjReport report : reports){
                    amount += report.getInsOmpanyMoney();
                }
                objReportAll.setInsOmpanyMoney(amount - objReportAll.getInsOmpanyMoney());//计算的是所有项目的总和 减去 多加的合计损失
                caseCloseObjReportMapper.updateByPrimaryKey(objReportAll);
            }
        }
        if(result > 0){
            Double rate = 0D;
            if (caseClosedReport != null){
                rate = caseClosedReport.getCompensateRate() == null ? 0D : caseClosedReport.getCompensateRate();
            }
            //insuranceCalculation(recode.getCaseId(),rate);
            Boolean b = false;
            if (caseCenterInfo.getType() == 2){
                AgentApply agentApply = agentApplyMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
                if (agentApply.getAgentType() == 2){
                    b = true;
                }
            }
            if (!b){
                insuranceCalculation(recode.getCaseId(),rate);
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }

    //结案报告中修改项目后算法计算交强险，商业险
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
        List<CaseClosedObjReport> caseClosedObjReportList = this.caseCloseObjReportMapper.queryByCaseId(caseId);
        for(CaseClosedObjReport caseClosedObjReport:caseClosedObjReportList){
            if(caseClosedObjReport.getObjectName().equals("医疗费")){
                t_medicalFee= caseClosedObjReport.getInsOmpanyMoney();
                t_medicalFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("住院伙食补助")){
                //医院伙食补助费
                t_hospitalFoodFee=caseClosedObjReport.getInsOmpanyMoney();
                t_hospitalFoodFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("营养费")){
                //营养费
                t_nutritionFee=caseClosedObjReport.getInsOmpanyMoney();
                t_nutritionFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("后续治疗费")){
                //后续治疗费
                t_againCureFee=caseClosedObjReport.getInsOmpanyMoney();
                t_againCureFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("护理费")){
                //护理费
                t_nursingFee=caseClosedObjReport.getInsOmpanyMoney();
                t_nursingFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("误工费")){
                //误工费
                t_lossWordFee=caseClosedObjReport.getInsOmpanyMoney();
                t_lossWordFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("残疾赔偿金")){
                //残疾赔偿金
                t_invalidismIndemnifyFee=caseClosedObjReport.getInsOmpanyMoney();
                t_invalidismIndemnifyFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("精神抚慰金")){
                //精神抚慰金
                t_spiritComfortFee=caseClosedObjReport.getInsOmpanyMoney();
                t_spiritComfortFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("被抚养人生活费")){
                //被抚养人生活费
                Double t_liveFee=caseClosedObjReport.getInsOmpanyMoney();
                Long t_liveFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("死亡赔偿金")){
                //死亡赔偿金
                t_deathIndemnifyFee=caseClosedObjReport.getInsOmpanyMoney();
                t_deathIndemnifyFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("丧葬费")){
                //丧葬费
                t_funeralFee=caseClosedObjReport.getInsOmpanyMoney();
                t_funeralFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("交通费")){
                //交通费
                t_trafficFee=caseClosedObjReport.getInsOmpanyMoney();
                t_trafficFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("诉讼费")){
                //诉讼费
                t_litigationFee=caseClosedObjReport.getInsOmpanyMoney();
                t_litigationFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("残疾器具费")){
                //残疾器具费
                t_disabilityEquipmentFee=caseClosedObjReport.getInsOmpanyMoney();
                t_disabilityEquipmentFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("鉴定费")){
                //鉴定费
                t_appraisalFee=caseClosedObjReport.getInsOmpanyMoney();
                t_appraisalFeeId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("财产损失")){
                //财产损失
                t_financialLoss=caseClosedObjReport.getInsOmpanyMoney();
                t_financialLossId=caseClosedObjReport.getId();
            }else if(caseClosedObjReport.getObjectName().equals("合计损失")){
                //合计损失
                // t_totalFee=caseClosedObjReport.getOpinionMoney();
                t_totalFeeId=caseClosedObjReport.getId();
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
        CaseClosedReport caseClosedReport1=this.caseCloseReportMapper.selectByCaseId(caseId);
        //根据新的责任计算责任比例
        double blameQuotiety=0D;
        if(rate!=null&&rate!=0){
            blameQuotiety=DecimalUtil.twoDecimalTOFourFromFive(rate/100);
        }else if(caseClosedReport1.getCompensateRate()!=null&&caseClosedReport1.getCompensateRate()>0){
            blameQuotiety= DecimalUtil.twoDecimalTOFourFromFive(caseClosedReport1.getCompensateRate()/100);
        }else if(paymentEstimateApply!=null){
            blameQuotiety = PaymentAlgorithmUtil.getBlameQuotiety(paymentEstimateApply.getMyStatus(), blameQuotieties, paymentEstimateApply.getMyAccidentLiability(), paymentEstimateApply.getOtherTarfficStatus(), paymentEstimateApply.getMyTarfficStatus());
        }else{
            blameQuotiety=0D;
        }

        // double blameQuotiety = PaymentAlgorithmUtil.getBlameQuotiety(paymentEstimateApply.getMyStatus(), blameQuotieties, paymentEstimateApply.getMyAccidentLiability(), paymentEstimateApply.getOtherTarfficStatus(), paymentEstimateApply.getMyTarfficStatus());
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
        if(isDeanth==0){
            //***********第一部分
            Double estimateOneTotal = DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateOne.get("estimateOneTotal").toString()));
            if(estimateOneTotal==0){
                estimateOneTotal=1D;
            }
            Double compulsoryInsuranceOneFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateOne.get("compulsoryInsuranceOneFee").toString()));
            Double commercialInsuranceOneFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateOne.get("commercialInsuranceOneFee").toString())*blameQuotiety);
            //医疗费
            CaseClosedObjReport caseClosedObjReport1=this.caseCloseObjReportMapper.selectByPrimaryKey(t_medicalFeeId);
            if (estimateOneTotal == 0){
                estimateOneTotal = 1D;
            }
            Double jqMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/estimateOneTotal) *compulsoryInsuranceOneFee);//医疗费交强险
            Double  syMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/estimateOneTotal)*commercialInsuranceOneFee);//医疗费商业险
            if(jqMedicalFee>0){
                caseClosedObjReport1.setCommerMoney(jqMedicalFee);
                t_totalYlJq=t_totalYlJq+jqMedicalFee;
                caseClosedObjReport1.setCompulMoney(syMedicalFee);
                t_totalYlSy=t_totalYlSy+syMedicalFee;
                caseClosedObjReport1.setDriverMoney(causeTroubleFee);
                this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport1);
            }


            //伙食补助费
            CaseClosedObjReport caseClosedObjReport2=this.caseCloseObjReportMapper.selectByPrimaryKey(t_hospitalFoodFeeId);
            Double jqhospitalFoodFee=DecimalUtil.twoDecimalTOFourFromFive((t_hospitalFoodFee/estimateOneTotal)*compulsoryInsuranceOneFee);//伙食补助费交强险
            Double syhospitalFoodFee=DecimalUtil.twoDecimalTOFourFromFive((t_hospitalFoodFee/estimateOneTotal)*commercialInsuranceOneFee);//伙食补助商业险
            if(jqhospitalFoodFee>0){
                caseClosedObjReport2.setCommerMoney(jqhospitalFoodFee);
                t_totalYlJq=t_totalYlJq+jqhospitalFoodFee;
                caseClosedObjReport2.setCompulMoney(syhospitalFoodFee);
                t_totalYlSy=t_totalYlSy+syhospitalFoodFee;
                this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport2);
            }

            //营养费
            CaseClosedObjReport caseClosedObjReport3=this.caseCloseObjReportMapper.selectByPrimaryKey(t_nutritionFeeId);
            Double  jqnutritionFee=DecimalUtil.twoDecimalTOFourFromFive((t_nutritionFee/estimateOneTotal)*compulsoryInsuranceOneFee);//营养费交强险
            Double synutritionFee=DecimalUtil.twoDecimalTOFourFromFive((t_nutritionFee/estimateOneTotal)*commercialInsuranceOneFee);//营养费商业险
            if(jqnutritionFee>0){
                caseClosedObjReport3.setCommerMoney(jqnutritionFee);
                t_totalYlJq=t_totalYlJq+jqnutritionFee;
                caseClosedObjReport3.setCompulMoney(synutritionFee);
                t_totalYlSy=t_totalYlSy+synutritionFee;
                this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport3);
            }




            //后续治疗费
            CaseClosedObjReport caseClosedObjReport4=this.caseCloseObjReportMapper.selectByPrimaryKey(t_againCureFeeId);
            Double jqagainCureFee=DecimalUtil.twoDecimalTOFourFromFive((t_againCureFee/estimateOneTotal)*compulsoryInsuranceOneFee);//后续治疗费交强险
            Double syagainCureFee=DecimalUtil.twoDecimalTOFourFromFive((t_againCureFee/estimateOneTotal)*commercialInsuranceOneFee);//后续治疗费商业险
            if(jqagainCureFee>0){
                caseClosedObjReport4.setCommerMoney(jqagainCureFee);
                t_totalYlJq=t_totalYlJq+jqagainCureFee;
                caseClosedObjReport4.setCompulMoney(syagainCureFee);
                t_totalYlSy=t_totalYlSy+syagainCureFee;
                this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport4);
            }



            //肇事方金额
            Double ct_commercialInsuranceOneFee= DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateOne.get("commercialInsuranceOneFee").toString()) * (1 - blameQuotiety));
            //**********第二部分
            Double estimateTwoTotal =Double.parseDouble(t_estimateTwo.get("estimateTwoTotal").toString());
            if(estimateTwoTotal==0){
                estimateTwoTotal=1D;
            }
            Double compulsoryInsuranceTwoFee =Double.parseDouble(t_estimateTwo.get("compulsoryInsuranceTwoFee").toString());
            Double commercialInsuranceTwoFee =Double.parseDouble(t_estimateTwo.get("commercialInsuranceTwoFee").toString())*blameQuotiety;
            Double ct_commercialInsuranceTwoFee =Double.parseDouble(t_estimateTwo.get("commercialInsuranceTwoFee").toString())*(1-blameQuotiety);
            if (estimateTwoTotal == 0){
                estimateTwoTotal = 1D;
            }
            //判断精神抚慰金
            if(subtractSpiritComfortFee>=0){
                //护理费
                CaseClosedObjReport caseClosedObjReport5=this.caseCloseObjReportMapper.selectByPrimaryKey(t_nursingFeeId);
                Double jqnursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//护理费交强险
                Double  synursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal)*commercialInsuranceTwoFee);//护理费商业险
                if(jqnursingFee>0){
                    caseClosedObjReport5.setCommerMoney(jqnursingFee);
                    t_totalPcJq=t_totalPcJq+jqnursingFee;
                    caseClosedObjReport5.setCompulMoney(synursingFee);
                    t_totalPcSy=t_totalPcSy+synursingFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport5);
                }



                //误工费
                CaseClosedObjReport caseClosedObjReport6=this.caseCloseObjReportMapper.selectByPrimaryKey(t_lossWordFeeId);
                Double jqlossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//误工费交强险
                Double  sylossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal)*commercialInsuranceTwoFee);//误工费商业险
                if(jqlossWordFee>0){
                    caseClosedObjReport6.setCommerMoney(jqlossWordFee);
                    t_totalPcJq=t_totalPcJq+jqlossWordFee;
                    caseClosedObjReport6.setCompulMoney(sylossWordFee);
                    t_totalPcSy=t_totalPcSy+sylossWordFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport6);
                }



                //残疾赔偿金
                CaseClosedObjReport caseClosedObjReport7=this.caseCloseObjReportMapper.selectByPrimaryKey(t_invalidismIndemnifyFeeId);
                Double jqinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//残疾赔偿金交强险
                Double  syinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal)*commercialInsuranceTwoFee);//残疾赔偿金商业险
                if(jqinvalidismIndemnifyFee>0){
                    caseClosedObjReport7.setCommerMoney(jqinvalidismIndemnifyFee);
                    t_totalPcJq=t_totalPcJq+jqinvalidismIndemnifyFee;
                    caseClosedObjReport7.setCompulMoney(syinvalidismIndemnifyFee);
                    t_totalPcSy=t_totalPcSy+syinvalidismIndemnifyFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport7);
                }



                //精神抚慰金
                CaseClosedObjReport caseClosedObjReport8=this.caseCloseObjReportMapper.selectByPrimaryKey(t_spiritComfortFeeId);
                Double jqspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive(t_spiritComfortFee);//精神抚慰金交强险

                if(jqspiritComfortFee>0){
                    caseClosedObjReport8.setCommerMoney(jqspiritComfortFee);
                    t_totalPcJq=t_totalPcJq+jqspiritComfortFee;
                    caseClosedObjReport8.setCompulMoney(0D);
                    t_totalPcSy=t_totalPcSy+0D;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport8);
                }


                //交通费
                CaseClosedObjReport caseClosedObjReport9=this.caseCloseObjReportMapper.selectByPrimaryKey(t_trafficFeeId);
                Double jqtrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sytrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqtrafficFee>0){
                    caseClosedObjReport9.setCommerMoney(jqtrafficFee);
                    t_totalPcJq=t_totalPcJq+jqtrafficFee;
                    caseClosedObjReport9.setCompulMoney(sytrafficFee);
                    t_totalPcSy=t_totalPcSy+sytrafficFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport9);
                }



                //诉讼费
                CaseClosedObjReport caseClosedObjReport10=this.caseCloseObjReportMapper.selectByPrimaryKey(t_litigationFeeId);
                Double jqlitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sylitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqlitigationFee>0){
                    caseClosedObjReport10.setCommerMoney(jqlitigationFee);
                    t_totalPcJq=t_totalPcJq+jqlitigationFee;
                    caseClosedObjReport10.setCompulMoney(sylitigationFee);
                    t_totalPcSy=t_totalPcSy+sylitigationFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport10);
                }



                //残疾器具费
                CaseClosedObjReport caseClosedObjReport11=this.caseCloseObjReportMapper.selectByPrimaryKey(t_disabilityEquipmentFeeId);
                Double jqdisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sydisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqdisabilityEquipmentFee>0){
                    caseClosedObjReport11.setCommerMoney(jqdisabilityEquipmentFee);
                    t_totalPcJq=t_totalPcJq+jqdisabilityEquipmentFee;
                    caseClosedObjReport11.setCompulMoney(sydisabilityEquipmentFee);
                    t_totalPcSy=t_totalPcSy+sydisabilityEquipmentFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport11);
                }



                //鉴定费
                CaseClosedObjReport caseClosedObjReport12=this.caseCloseObjReportMapper.selectByPrimaryKey(t_appraisalFeeId);
                Double jqappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  syappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqappraisalFee>0){
                    caseClosedObjReport12.setCommerMoney(jqappraisalFee);
                    t_totalPcJq=t_totalPcJq+jqappraisalFee;
                    caseClosedObjReport12.setCompulMoney(syappraisalFee);
                    t_totalPcSy=t_totalPcSy+syappraisalFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport12);
                }
            }else{
                //护理费
                CaseClosedObjReport caseClosedObjReport5=this.caseCloseObjReportMapper.selectByPrimaryKey(t_nursingFeeId);
                Double jqnursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//护理费交强险
                Double  synursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal)*commercialInsuranceTwoFee);//护理费商业险
                if(jqnursingFee>0){
                    caseClosedObjReport5.setCommerMoney(jqnursingFee);
                    t_totalPcJq=t_totalPcJq+jqnursingFee;
                    caseClosedObjReport5.setCompulMoney(synursingFee);
                    t_totalPcSy=t_totalPcSy+jqnursingFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport5);
                }



                //误工费
                CaseClosedObjReport caseClosedObjReport6=this.caseCloseObjReportMapper.selectByPrimaryKey(t_lossWordFeeId);
                Double jqlossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//误工费交强险
                Double  sylossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal)*commercialInsuranceTwoFee);//误工费商业险
                if(jqlossWordFee>0){
                    caseClosedObjReport6.setCommerMoney(jqlossWordFee);
                    t_totalPcJq=t_totalPcJq+jqlossWordFee;
                    caseClosedObjReport6.setCompulMoney(sylossWordFee);
                    t_totalPcSy=t_totalPcSy+sylossWordFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport6);
                }



                //残疾赔偿金
                CaseClosedObjReport caseClosedObjReport7=this.caseCloseObjReportMapper.selectByPrimaryKey(t_invalidismIndemnifyFeeId);
                Double jqinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//残疾赔偿金交强险
                Double  syinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal)*commercialInsuranceTwoFee);//残疾赔偿金商业险
                if(jqinvalidismIndemnifyFee>0){
                    caseClosedObjReport7.setCommerMoney(jqinvalidismIndemnifyFee);
                    t_totalPcJq=t_totalPcJq+jqinvalidismIndemnifyFee;
                    caseClosedObjReport7.setCompulMoney(syinvalidismIndemnifyFee);
                    t_totalPcSy=t_totalPcSy+syinvalidismIndemnifyFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport7);
                }



                //精神抚慰金
                CaseClosedObjReport caseClosedObjReport8=this.caseCloseObjReportMapper.selectByPrimaryKey(t_spiritComfortFeeId);
                Double jqspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive(110000);//精神抚慰金交强险
                Double  syspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/estimateTwoTotal)*commercialInsuranceTwoFee);//精神抚慰金商业险
                if(jqspiritComfortFee>0){
                    caseClosedObjReport8.setCommerMoney(jqspiritComfortFee);
                    t_totalPcJq=t_totalPcJq+jqspiritComfortFee;
                    caseClosedObjReport8.setCompulMoney(syspiritComfortFee);
                    t_totalPcSy=t_totalPcSy+syspiritComfortFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport8);
                }


                //交通费
                CaseClosedObjReport caseClosedObjReport9=this.caseCloseObjReportMapper.selectByPrimaryKey(t_trafficFeeId);
                Double jqtrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sytrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqtrafficFee>0){
                    caseClosedObjReport9.setCommerMoney(jqtrafficFee);
                    t_totalPcJq=t_totalPcJq+jqtrafficFee;
                    caseClosedObjReport9.setCompulMoney(sytrafficFee);
                    t_totalPcSy=t_totalPcSy+sytrafficFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport9);
                }



                //诉讼费
                CaseClosedObjReport caseClosedObjReport10=this.caseCloseObjReportMapper.selectByPrimaryKey(t_litigationFeeId);
                Double jqlitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sylitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqlitigationFee>0){
                    caseClosedObjReport10.setCommerMoney(jqlitigationFee);
                    t_totalPcJq=t_totalPcJq+jqlitigationFee;
                    caseClosedObjReport10.setCompulMoney(sylitigationFee);
                    t_totalPcSy=t_totalPcSy+sylitigationFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport10);
                }



                //残疾器具费
                CaseClosedObjReport caseClosedObjReport11=this.caseCloseObjReportMapper.selectByPrimaryKey(t_disabilityEquipmentFeeId);
                Double jqdisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sydisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqdisabilityEquipmentFee>0){
                    caseClosedObjReport11.setCommerMoney(jqdisabilityEquipmentFee);
                    t_totalPcJq=t_totalPcJq+jqdisabilityEquipmentFee;
                    caseClosedObjReport11.setCompulMoney(sydisabilityEquipmentFee);
                    t_totalPcSy=t_totalPcSy+sydisabilityEquipmentFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport11);
                }



                //鉴定费
                CaseClosedObjReport caseClosedObjReport12=this.caseCloseObjReportMapper.selectByPrimaryKey(t_appraisalFeeId);
                Double jqappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  syappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqappraisalFee>0){
                    caseClosedObjReport12.setCommerMoney(jqappraisalFee);
                    t_totalPcJq=t_totalPcJq+jqappraisalFee;
                    caseClosedObjReport12.setCompulMoney(syappraisalFee);
                    t_totalPcSy=t_totalPcSy+syappraisalFee;
                    this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport12);
                }
            }






            //*****第三部分
            Double estimateThreeTotal =Double.parseDouble(t_estimateThree.get("estimateThreeTotal").toString());
            Double compulsoryInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("compulsoryInsuranceThreeFee").toString());
            t_totalCwJq=t_totalCwJq+compulsoryInsuranceThreeFee;
            Double commercialInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*blameQuotiety;
            t_totalCwSy=t_totalCwSy+commercialInsuranceThreeFee;
            Double ct_commercialInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*(1-blameQuotiety);
            CaseClosedObjReport caseClosedObjReport13=this.caseCloseObjReportMapper.selectByPrimaryKey(t_financialLossId);
            if(compulsoryInsuranceThreeFee>0){
                caseClosedObjReport13.setCommerMoney(compulsoryInsuranceThreeFee);
                caseClosedObjReport13.setCompulMoney(commercialInsuranceThreeFee);
                this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport13);
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
            if (destimateOneTotal == 0){
                destimateOneTotal = 1D;
            }
            //医疗费
            CaseClosedObjReport caseClosedObjReport1=this.caseCloseObjReportMapper.selectByPrimaryKey(t_medicalFeeId);
            Double jqMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/destimateOneTotal) *dcompulsoryInsuranceOneFee);//医疗费交强险
            Double  syMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/destimateOneTotal)*dcommercialInsuranceOneFee);//医疗费商业险
            if(jqMedicalFee>0){
                caseClosedObjReport1.setCommerMoney(jqMedicalFee);
                t_totalYlJq=t_totalYlJq+jqMedicalFee;
                caseClosedObjReport1.setCompulMoney(syMedicalFee);
                t_totalYlSy=t_totalYlSy+syMedicalFee;
                this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport1);
            }



            //**********第二部分

            Double destimateTwoTotal =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateTwo.get("destimateTwoTotal").toString()));
            if(destimateTwoTotal==0){
                destimateTwoTotal=1D;
            }
            Double dcompulsoryInsuranceTwoFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateTwo.get("dcompulsoryInsuranceTwoFee").toString()));
            Double dcommercialInsuranceTwoFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateTwo.get("dcommercialInsuranceTwoFee").toString())*blameQuotiety);
            Double ct_dcommercialInsuranceTwoFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateTwo.get("dcommercialInsuranceTwoFee").toString())*(1-blameQuotiety));
            if (destimateTwoTotal == 0){
                destimateTwoTotal = 1D;
            }
            //死亡赔偿金
            CaseClosedObjReport caseClosedObjReport2=this.caseCloseObjReportMapper.selectByPrimaryKey(t_deathIndemnifyFeeId);
            Double jqdeathIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_deathIndemnifyFee/destimateTwoTotal)*dcompulsoryInsuranceTwoFee);//死亡赔偿金交强险
            Double sydeathIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_deathIndemnifyFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);//死亡赔偿金商业险
            if(jqdeathIndemnifyFee>0){
                caseClosedObjReport2.setCommerMoney(jqdeathIndemnifyFee);
                t_totalPcJq=t_totalPcJq+jqdeathIndemnifyFee;
                caseClosedObjReport2.setCompulMoney(sydeathIndemnifyFee);
                t_totalPcSy=t_totalPcSy+sydeathIndemnifyFee;
                this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport2);
            }



            //精神抚慰金
            CaseClosedObjReport caseClosedObjReport3=this.caseCloseObjReportMapper.selectByPrimaryKey(t_spiritComfortFeeId);
            Double jqspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/destimateTwoTotal)*dcompulsoryInsuranceTwoFee);//精神抚慰金
            Double syspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);//精神抚慰金
            if(jqspiritComfortFee>0){
                caseClosedObjReport3.setCommerMoney(jqspiritComfortFee);
                t_totalPcJq=t_totalPcJq+jqspiritComfortFee;
                caseClosedObjReport3.setCompulMoney(syspiritComfortFee);
                t_totalPcJq=t_totalPcJq+syspiritComfortFee;
                this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport3);
            }



            //丧葬费
            CaseClosedObjReport caseClosedObjReport4=this.caseCloseObjReportMapper.selectByPrimaryKey(t_funeralFeeId);
            Double jqfuneralFee=DecimalUtil.twoDecimalTOFourFromFive((t_funeralFee/destimateTwoTotal)*dcompulsoryInsuranceTwoFee);//丧葬费交强险
            Double syfuneralFee=DecimalUtil.twoDecimalTOFourFromFive((t_funeralFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);//丧葬费商业险
            if(jqfuneralFee>0){
                caseClosedObjReport4.setCommerMoney(jqfuneralFee);
                t_totalPcJq=t_totalPcJq+jqfuneralFee;
                caseClosedObjReport4.setCompulMoney(syfuneralFee);
                t_totalPcJq=t_totalPcJq+syfuneralFee;
                this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReport4);
            }


            //*****第三部分
            Double estimateThreeTotal =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("estimateThreeTotal").toString()));
            Double compulsoryInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("compulsoryInsuranceThreeFee").toString()));
            t_totalCwJq=t_totalCwJq+compulsoryInsuranceThreeFee;
            Double commercialInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*blameQuotiety);
            t_totalCwSy=t_totalCwSy+compulsoryInsuranceThreeFee;
            Double ct_commercialInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*(1-blameQuotiety));
            CaseClosedObjReport caseClosedObjReportCw=this.caseCloseObjReportMapper.selectByPrimaryKey(t_financialLossId);
            caseClosedObjReportCw.setCommerMoney(compulsoryInsuranceThreeFee);
            caseClosedObjReportCw.setCompulMoney(commercialInsuranceThreeFee);
            this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReportCw);

        }
        CaseClosedObjReport caseClosedObjReportCw=this.caseCloseObjReportMapper.selectByPrimaryKey(t_totalFeeId);
        caseClosedObjReportCw.setCommerMoney(DecimalUtil.twoDecimalTOFourFromFive(t_totalYlJq+t_totalPcJq+t_totalCwJq));
        caseClosedObjReportCw.setCompulMoney(DecimalUtil.twoDecimalTOFourFromFive(t_totalYlSy+t_totalPcSy+t_totalCwSy));
        this.caseCloseObjReportMapper.updateByPrimaryKeySelective(caseClosedObjReportCw);

        //把交强险，商业险，赔偿总额等数据写入结案报告
        CaseClosedReport caseClosedReport=this.caseCloseReportMapper.selectByCaseId(caseId);
        caseClosedReport.setCompulsoryMoney(DecimalUtil.twoDecimalTOFourFromFive(t_totalYlJq+t_totalPcJq+t_totalCwJq));
        caseClosedReport.setCommercialMoney(DecimalUtil.twoDecimalTOFourFromFive(t_totalYlSy+t_totalPcSy+t_totalCwSy));
        caseClosedReport.setCompensateMoney(DecimalUtil.twoDecimalTOFourFromFive(t_totalYlJq+t_totalPcJq+t_totalCwJq+t_totalYlSy+t_totalPcSy+t_totalCwSy));
        caseClosedReport.setDriverMoney(DecimalUtil.twoDecimalTOFourFromFive(causeTroubleFee));
        caseClosedReport.setInjuredMoney(t_totalYlJq+t_totalPcJq+t_totalCwJq+t_totalYlSy+t_totalPcSy+t_totalCwSy+causeTroubleFee);
        this.caseCloseReportMapper.updateByPrimaryKeySelective(caseClosedReport);
    }

    @ApiMethod(descript = "查询案件赔偿及保险理赔方案",value = "backend-get-case-close-obj-report")
    @Override
    public ApiResponse getCaseCloseObjReport(ApiRequest apiReq) {
        Long caseId = apiReq.getLong("caseId");
        String caseNo = apiReq.getString("caseNo");
        String op = apiReq.getString("op");
        List<CaseClosedObjReport> caseCloseObjReports = caseCloseObjReportMapper.queryByCaseId(caseId);
        CaseClosedReport caseClosedReport = caseCloseReportMapper.selectByCaseId(caseId);
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);
        if (caseCloseObjReports == null || caseCloseObjReports.size() == 0){
            Map<String,Object> mapNames = ReportProjectEnum.CLOSE_TYPE.getMap();//初始化一个Map 与 索赔方案的名称对应
            if ("view".equals(op)){
                for (String name : mapNames.keySet()){
                    CaseClosedObjReport caseClosedObjReport = initDataDetail(name,caseId,caseNo,mapNames);
                    caseCloseObjReports.add(caseClosedObjReport);
                }
            }else{
                for (String name : mapNames.keySet()){
                    CaseClosedObjReport caseClosedObjReport = initDataDetail(name,caseId,caseNo,mapNames);
                    caseCloseObjReportMapper.insertSelective(caseClosedObjReport);
                }
                Double rate  = 0D;
                if (caseClosedReport != null){
                    rate = caseClosedReport.getCompensateRate() == null ? 0D : caseClosedReport.getCompensateRate();
                }
                Boolean b = false;
                if (caseCenterInfo.getType() == 2){
                    AgentApply agentApply = agentApplyMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
                    if (agentApply.getAgentType() == 2){
                        b = true;
                    }
                }
                if (!b){
                    insuranceCalculation(caseId,rate);
                }
                //insuranceCalculation(caseId,rate);
                //重新获取数据
                caseCloseObjReports = caseCloseObjReportMapper.queryByCaseId(caseId);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,caseCloseObjReports.size(),caseCloseObjReports);
    }

    /**
     * 初始化主数据
     * @param caseClosedReport  结案报告
     * @param caseCenterInfo    案件中心信息
     * @return
     */
    private CaseClosedReport initData(CaseClosedReport caseClosedReport,CaseCenterInfo caseCenterInfo){
        try {
            Map<String,Object> caseCenterInfoMap = new HashMap<>();
            caseCenterInfoMap.put("caseNo",caseCenterInfo.getCaseNo());
            //代理案件
//            if (caseCenterInfo.getType() == 2){
//                caseClosedReport.setIsDeductedLoan("0");
//            }
            //索赔员
            caseClosedReport.setClaimantId(caseCenterInfo.getClaimantId());
            caseClosedReport.setClaimantName(caseCenterInfo.getClaimantName());
            //诉讼员
            caseClosedReport.setLegalUser(caseCenterInfo.getLegalUserName());
            caseClosedReport.setLegalUserId(caseCenterInfo.getLegalUserId());

            //承接机构
            caseClosedReport.setOrgId(caseCenterInfo.getOrgId());
            caseClosedReport.setOrgName(caseCenterInfo.getOrgName());
            //委托开始时间
            caseClosedReport.setEntrustTime(caseCenterInfo.getAgreeSignTime());

            PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectPEAByCaseCenterInfoNew(caseCenterInfoMap);
            if(paymentEstimateApply != null){
                //委托人
                caseClosedReport.setClienteleName(paymentEstimateApply.getUserName());
                caseClosedReport.setClienteleId(paymentEstimateApply.getUserId());
            }

            PaymentEstimateInquiryDto paymentEstimateInquiryDto = caseCenterInfoMapper.selectNewestPayInquiryNew(caseCenterInfo.getId());
            if(paymentEstimateInquiryDto != null){
                if(paymentEstimateInquiryDto.getCalculationType()!=null){
                    if(paymentEstimateInquiryDto.getCalculationType()==1){ //固定收费
                        caseClosedReport.setChargeType(1);
                        caseClosedReport.setFixedChargeMoney(paymentEstimateInquiryDto.getShouldDeFee());
                    }else if(paymentEstimateInquiryDto.getCalculationType()==2){ //按比例收费()
                        caseClosedReport.setChargeType(2);
                        //比例基数比例收费基数（1.赔偿金额，2，赔偿款-医疗费，3.伤残赔偿金+精神损失费）
                        if(paymentEstimateInquiryDto.getRateBaseType()!=null){
                            if(paymentEstimateInquiryDto.getRateBaseType()==1){
                                caseClosedReport.setProportionChargeMoney(paymentEstimateInquiryDto.getPayableFee());
                            }else if(paymentEstimateInquiryDto.getRateBaseType()==2){
                                caseClosedReport.setProportionChargeMoney(DecimalUtil.twoDecimalTOFourFromFive(paymentEstimateInquiryDto.getPayableFee() - paymentEstimateInquiryDto.getMedicalFee()));
                            }else if(paymentEstimateInquiryDto.getRateBaseType()==3){
                                caseClosedReport.setProportionChargeMoney(DecimalUtil.twoDecimalTOFourFromFive(paymentEstimateInquiryDto.getInvalidismIndemnifyFee() + paymentEstimateInquiryDto.getSpiritComfortFee()));
                            }
                        }
                        //收费比例
                        caseClosedReport.setProportionChargeRate(paymentEstimateInquiryDto.getServiceRate());
                    }
                }else{
                    caseClosedReport.setChargeType(1);//默认选中固定收费
                    caseClosedReport.setFixedChargeMoney(paymentEstimateInquiryDto.getShouldDeFee());
                }
                //应收取服务费总金额   预收服务费总金额   剩余应收服务费总金额
                caseClosedReport.setShouldTotalMoney(paymentEstimateInquiryDto.getShouldDeFee());
                caseClosedReport.setPreMoney(paymentEstimateInquiryDto.getRealDeFee());
                caseClosedReport.setSurplusTotalMoney((caseClosedReport.getShouldTotalMoney() == null ? 0 : caseClosedReport.getShouldTotalMoney()) - (caseClosedReport.getPreMoney() == null ? 0 : caseClosedReport.getPreMoney()));
                //贷款金额
                caseClosedReport.setLoanMoney(paymentEstimateInquiryDto.getTotalLoanFee());
                //保证保险保费
                caseClosedReport.setInsuranceMoney(paymentEstimateInquiryDto.getInsuranceFee());
                //贷款通道费：费用和比例
                caseClosedReport.setLoanChannelMoney(paymentEstimateInquiryDto.getLoanFee());
                caseClosedReport.setLoanChannelRate(paymentEstimateInquiryDto.getLoanRate()== null ? 0:paymentEstimateInquiryDto.getLoanRate() * 100);
            }

            CaseMediationClaim caseMediationClaim = caseMediationClaimMapper.queryByCaseId(caseCenterInfo.getId());
            CaseMediationClaimLegal caseMediationClaimLegal = caseMediationClaimLegalMapper.queryByCaseId(caseCenterInfo.getId());
            //索赔报告不为NULL 取索赔报告数据 , 等于NULL取诉讼报告数据
            if(caseMediationClaim != null){
                //事故责任比例
                caseClosedReport.setCompensateRate(caseMediationClaim.getLiabilityRatio());
                //索赔开始时间
                caseClosedReport.setClaimBeginTime(caseMediationClaim.getClaimTime());
                //支付伤者 驾驶员 保险公司金额
                caseClosedReport.setPayWoundedMoney(caseMediationClaim.getPayWoundedMoney());
                caseClosedReport.setPayDriverMoney(caseMediationClaim.getPayDriverMoney());
                caseClosedReport.setPaySafeMoney(caseMediationClaim.getPaySafeMoney());
            }else{
                if (caseMediationClaimLegal != null){
                    //事故责任比例
                    caseClosedReport.setCompensateRate(caseMediationClaimLegal.getLiabilityRatio());
                    //索赔开始时间
                    caseClosedReport.setClaimBeginTime(caseMediationClaimLegal.getClaimTime());
                    //支付伤者 驾驶员 保险公司金额
                    caseClosedReport.setPayWoundedMoney(caseMediationClaimLegal.getPayWoundedMoney());
                    caseClosedReport.setPayDriverMoney(caseMediationClaimLegal.getPayDriverMoney());
                    caseClosedReport.setPaySafeMoney(caseMediationClaimLegal.getPaySafeMoney());
                }
            }
            if(caseMediationClaimLegal != null){
                //诉讼开始时间
                caseClosedReport.setLegalBeginTime(caseMediationClaimLegal.getClaimTime());
            }

        }catch (Exception e){
            e.printStackTrace();//打印出报错信息，但初始化数据还需返回已初始化的数据
        }
        return caseClosedReport;
    }

    /**
     * 初始化项目详情数据
     * @param name
     * @param caseId
     * @param caseNo
     * @return
     */
    private CaseClosedObjReport initDataDetail(String name,Long caseId,String caseNo,Map mapNames){
        CaseClosedObjReport caseClosedObjReport = new CaseClosedObjReport();
        caseClosedObjReport.setObjectName(name);
        caseClosedObjReport.setCaseId(caseId);
        caseClosedObjReport.setCaseNo(caseNo);
        caseClosedObjReport.setInsOmpanyMoney(0D);
        caseClosedObjReport.setDriverMoney(0D);
        caseClosedObjReport.setOpinionMoney(0D);
        List<CaseMediationClaimReport> caseMediationClaimReportList = caseMediationClaimReportMapper.selectByCaseId(caseId);
        Double amount = 0D;//结案报告的金额
        if (caseMediationClaimReportList != null && caseMediationClaimReportList.size() > 0){
            for(CaseMediationClaimReport caseMediationClaimReport : caseMediationClaimReportList){
//            if ("合计损失".equals(name) && mapNames.values().contains(caseMediationClaimReport.getProjectName())){
//                amount += caseMediationClaimReport.getAuditingMoney();
//                continue;
//            }
                //如果存在对应关系,则初始化金额数据  跳出循环
                if (caseMediationClaimReport.getProjectName().equals(mapNames.get(name))){
                    amount = caseMediationClaimReport.getAuditingMoney();
                    break;
                }
            }
        }else{
            List<CaseMediationClaimReportLegal> caseMediationClaimReportLegalList = caseMediationClaimReportLegalMapper.selectByCaseId(caseId);
            for(CaseMediationClaimReportLegal caseMediationClaimReportLegal : caseMediationClaimReportLegalList){
                //如果存在对应关系,则初始化金额数据  跳出循环
                if (caseMediationClaimReportLegal.getProjectName().equals(mapNames.get(name))){
                    amount = caseMediationClaimReportLegal.getAuditingMoney();
                    break;
                }
            }
        }
        caseClosedObjReport.setOpinionMoney(DecimalUtil.twoDecimalTOFourFromFive(amount));
        caseClosedObjReport.setInsOmpanyMoney(DecimalUtil.twoDecimalTOFourFromFive(amount));
        return caseClosedObjReport;
    }

    /**
     * 计算合计 小计, 便于代码整洁
     * @param caseId
     * @param objectName
     * @return
     */
    private Map returnMap(Long caseId,String objectName){
        Map map = new HashMap();
        map.put("caseId",caseId);
        map.put("objectName",objectName);
        return map;
    }
}
