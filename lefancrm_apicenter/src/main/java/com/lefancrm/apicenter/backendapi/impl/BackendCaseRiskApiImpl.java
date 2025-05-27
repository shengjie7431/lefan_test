package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendCaseRiskApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.PaymentEstimateInquiryDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.RedisService;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.apicenter.util.PDFUtil;
import com.lefancrm.apicenter.util.SendMessageUntil;
import com.lefancrm.apicenter.util.tsign.RiskReportSign;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiParam;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.apache.ibatis.annotations.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;

/**
 * 案件中心
 * Created by wanjun on 2017-04-26.
 */
@Service
@ApiService(descript = "后台案件中心API")
public class BackendCaseRiskApiImpl extends BaseServiceImpl implements BackendCaseRiskApi {

    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;
    @Autowired
    private CaseCenterExtendMapper caseCenterExtendMapper;
    @Autowired
    private LoanApplicationMapper loanApplicationMapper;
    @Autowired
    private AgentApplyMapper agentApplyMapper;
    @Autowired
    private CaseMediationClaimMapper caseMediationClaimMapper;
    @Autowired
    private CaseMediationClaimReportMapper caseMediationClaimReportMapper;
    @Autowired
    private PaymentEstimateReportMapper paymentEstimateReportMapper;
    @Autowired
    private CaseRiskControlMapper caseRiskControlMapper;
    @Autowired
    private PaymentEstimateApplyMapper paymentEstimateApplyMapper;
    @Autowired
    private PaymentEstimateInquiryMapper paymentEstimateInquiryMapper;
    @Autowired
    private BlameQuotietyMapper blameQuotietyMapper;
    @Autowired
    private RedisService redisService;

    @Autowired
    private CaseFileMidMapper caseFileMidMapper;
    @Autowired
    private CommonFileMapper commonFileMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private MessageInfoMapper messageInfoMapper;
    @Autowired
    private CaseAssessmentReportMapper caseAssessmentReportMapper;
    @Autowired
    private CaseAssessmentObjReportMapper caseAssessmentObjReportMapper;
    @Autowired
    private CardInfoDtoMapper cardInfoDtoMapper;//卡信息mapper
    @Autowired
    private SurveyRiskCaseMapper surveyRiskCaseMapper;

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
//
//    @Value("${organize_img_file_path_risk}")
//    private String organizeImgFilePath;
//    @Value("${company_name}")
//    private String companyName;
//    @Value("${organ_code}")
//    private String organCode;
//    @Value("${company_address}")
//    private String companyAddress;
//    @Value("${agent_name}")
//    private String agentName;
//    @Value("${agent_id_no}")
//    private String agentIdNo;
    /**
     * 分页查询案件中心数据
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "案件风控列表中心", value = "backend-case-risk-list")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse caseRiskList(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        Integer type = apiReq.getInt("riskType");
        apiReq.remove("riskType");
        if(type == 1){//贷款评估
            apiReq.put("gradationState", 2);
        }else if(type == 2){//索赔
            apiReq.put("gradationState", 3);
        }else if(type == 3){//结案
            apiReq.put("closedState", 1);
        }
        int count = caseCenterInfoMapper.selectCountCaseCenterInfoByParam(apiReq);
        List<CaseCenterInfo> list = caseCenterInfoMapper.selectCaseCenterInfoByParam(apiReq);
        return new ApiResponse<List<CaseCenterInfo>>(ApiMsgEnum.SUCCESS, count, list);
    }

    @ApiMethod(descript = "风控案件审核", value = "backend-case-edit-risk-state")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse editRiskState(ApiRequest apiReq) {
        Integer type = apiReq.getInt("riskType");
        Long id  = apiReq.getLong("id");
        Integer state = apiReq.getInt("state");
        String stateReason = apiReq.getString("stateReason");
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(id);
        if(caseCenterInfo == null){
            return new ApiResponse(ApiMsgEnum.CASE_RISK_STATE_ERROR);
        }
        if(type == 1){//贷款评估
            if(caseCenterInfo.getIssuanceState() != 0){
                return new ApiResponse(ApiMsgEnum.CASE_RISK_STATE_ERROR);
            }
            caseCenterInfo.setIssuanceState(state);
            caseCenterInfo.setIssuanceReason(stateReason);
        }else if(type == 2){//索赔
            if(caseCenterInfo.getClaimState() != 2){
                return new ApiResponse(ApiMsgEnum.CASE_RISK_STATE_ERROR);
            }
            if(caseCenterInfo.getIsFeedback() == null || caseCenterInfo.getIsFeedback() != 1){
                return new ApiResponse(ApiMsgEnum.CASE_RISK_IS_NOT_FEED_BACK);
            }
            caseCenterInfo.setClaimState(state);
            caseCenterInfo.setClaimReason(stateReason);
        }else if(type == 3){//结案
            if(caseCenterInfo.getClosedState() != 1){
                return new ApiResponse(ApiMsgEnum.CASE_RISK_STATE_ERROR);
            }
            caseCenterInfo.setClosedState(state);
            caseCenterInfo.setClosedReason(stateReason);
            caseCenterInfo.setCaseState(9);
            caseCenterInfo.setCaseStateStr("已结案");
            caseCenterInfo.setGradationState(4);
        }
        int ret = caseCenterInfoMapper.updateByPrimaryKeySelective(caseCenterInfo);
        if(ret > 0){
            if(caseCenterInfo.getClosedState() != null && caseCenterInfo.getClosedState() == 2){
                 if(caseCenterInfo.getType() == 1){
                    LoanApplication loanApplication = loanApplicationMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
                     if(loanApplication != null){
                         loanApplication.setState(22);
                         loanApplicationMapper.updateByPrimaryKeySelective(loanApplication);
                     }
                 }else if(caseCenterInfo.getType() == 2){
                     AgentApply agentApplyInfo = agentApplyMapper.selectByPrimaryKey(caseCenterInfo.getCaseId());
                     if(agentApplyInfo != null){
                         agentApplyInfo.setState(22);
                         agentApplyMapper.updateByPrimaryKeySelective(agentApplyInfo);
                     }
                 }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }


    @ApiMethod(descript = "查询赔偿方案", value = "backend-case-claim")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse queryCaseClaim(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        CaseMediationClaim caseMediationClaim = caseMediationClaimMapper.queryByCaseId(id);
        if(caseMediationClaim != null){
            caseMediationClaim.setCaseMediationClaimReports(caseMediationClaimReportMapper.selectByCaseId(id));
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,caseMediationClaim);
    }


    @ApiMethod(descript = "查询赔偿方案报告", value = "backend-case-claim-report")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse queryCaseClaimReport(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        List<CaseMediationClaimReport> caseMediationClaimReports = caseMediationClaimReportMapper.selectByCaseId(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,caseMediationClaimReports);
    }

    @ApiMethod(descript = "理赔测算报告修改", value = "backend_payment_estimate_report_edit")
    @Override
    public ApiResponse paymentEstimateReportEdit(ApiRequest apiReq) {
        Integer accidentLiability = apiReq.getInt("accidentLiability");//机动车方责任
        Integer s_accidentLiability=accidentLiability;
        //转换为伤者方责任进行计算
        if(accidentLiability==1){
            //机动车放责任为全部责任，那么伤者为无责
            s_accidentLiability=5;
        }else if(accidentLiability==2){
            s_accidentLiability=4;
        }else if(accidentLiability==3){
            s_accidentLiability=3;
        }else if(accidentLiability==4){
            s_accidentLiability=2;
        }else if(accidentLiability==5){
            s_accidentLiability=1;
        }else{
            s_accidentLiability=accidentLiability;
        }

        Long  paymentId=apiReq.getLong("paymentId");
        apiReq.put("list", JSONArray.parseArray(apiReq.getString("list")));

        //查出原来提交的测算数据
        PaymentEstimateApply paymentEstimateApply =  this.paymentEstimateApplyMapper.selectByPrimaryKey(paymentId);
        paymentEstimateApply.setMyAccidentLiability(accidentLiability);
        if (paymentEstimateApply.getCaseType() != null){
            if (paymentEstimateApply.getCaseType() == 1){
                Integer loanFund = apiReq.getInt("loanFund");
                paymentEstimateApply.setLoanFund(loanFund);
            }
        }
        this.paymentEstimateApplyMapper.updateByPrimaryKeySelective(paymentEstimateApply);
        Integer isDeanth=0;//未死亡
        if(paymentEstimateApply.getInjuryStatus().equals("12")){
            isDeanth=1;
        }
        //责任系数
       /* redisService.estimateRemove("blameQuotieties");*/
        List<BlameQuotiety> blameQuotieties = redisService.estimateGet("blameQuotieties",new BlameQuotiety());
        if(blameQuotieties==null){
            blameQuotieties =    this.blameQuotietyMapper.selectBlameQuotiety();
            redisService.estimateAdd("blameQuotieties",blameQuotieties,30 * 24 * 60 * 60);
        }
        //根据新的责任计算责任比例
        double blameQuotiety = getBlameQuotiety(paymentEstimateApply.getMyStatus(),blameQuotieties,accidentLiability,paymentEstimateApply.getOtherTarfficStatus(),paymentEstimateApply.getMyTarfficStatus());
        //判断有无责
        boolean bl = getResponsibility(blameQuotieties,accidentLiability,paymentEstimateApply.getOtherTarfficStatus(),paymentEstimateApply.getMyTarfficStatus());
        int result = paymentEstimateReportMapper.updatePaymentEstimateReport(apiReq);
        if(result > 0){
            //开始计算交强险和商业险，以及肇事方赔偿金额
            JSONArray list= JSONArray.parseArray( apiReq.getString("list"));
            //非死亡案件的各项费用：
            if(list.size()>6){
                isDeanth=0;
            }else{
                isDeanth=1;
            }

            //医疗费
            Double t_medicalFee= JSON.parseObject(list.get(0).toString()).getDoubleValue("value");
            Long t_medicalFeeId=JSON.parseObject(list.get(0).toString()).getLong("id");
            //医院伙食补助费
            Double t_hospitalFoodFee=JSON.parseObject(list.get(1).toString()).getDoubleValue("value");
            Long t_hospitalFoodFeeId=JSON.parseObject(list.get(1).toString()).getLong("id");
            //营养费
            Double  t_nutritionFee =JSON.parseObject(list.get(2).toString()).getDoubleValue("value");
            Long  t_nutritionFeeId =JSON.parseObject(list.get(2).toString()).getLong("id");
            //后续治疗费
            Double  t_againCureFee =JSON.parseObject(list.get(3).toString()).getDoubleValue("value");
            Long  t_againCureFeeId =JSON.parseObject(list.get(3).toString()).getLong("id");


            //误工费
            Double t_lossWordFee=JSON.parseObject(list.get(4).toString()).getDoubleValue("value");
            Long t_lossWordFeeId=JSON.parseObject(list.get(4).toString()).getLong("id");
            //护理费
            Double t_nursingFee=JSON.parseObject(list.get(5).toString()).getDoubleValue("value");
            Long t_nursingFeeId=JSON.parseObject(list.get(5).toString()).getLong("id");
            //伤残赔偿金
            Double  t_invalidismIndemnifyFee=JSON.parseObject(list.get(6).toString()).getDoubleValue("value");
            Long  t_invalidismIndemnifyFeeId=JSON.parseObject(list.get(6).toString()).getLong("id");
            //精神抚慰金
            Double t_spiritComfortFee=JSON.parseObject(list.get(7).toString()).getDoubleValue("value");
            Long t_spiritComfortFeeId=JSON.parseObject(list.get(7).toString()).getLong("id");
            //交通费
            Double t_trafficFee=JSON.parseObject(list.get(8).toString()).getDoubleValue("value");
            Long t_trafficFeeId=JSON.parseObject(list.get(8).toString()).getLong("id");
            //财物损失费
            Double t_financialLoss=JSON.parseObject(list.get(9).toString()).getDoubleValue("value");
            Long t_financialLossId=JSON.parseObject(list.get(9).toString()).getLong("id");
            //合计
            Long t_totalId=JSON.parseObject(list.get(10).toString()).getLong("id");
            //死亡案件的各项费用记录
            //医疗费
            Double t_dmedicalFee=JSON.parseObject(list.get(0).toString()).getDoubleValue("value");
            Long t_dmedicalFeeId=JSON.parseObject(list.get(0).toString()).getLong("id");
            //死亡赔偿金
            Double t_deathIndemnifyFee=JSON.parseObject(list.get(1).toString()).getDoubleValue("value");
            Long t_deathIndemnifyFeeId=JSON.parseObject(list.get(1).toString()).getLong("id");
            //精神抚慰金
            Double t_dspiritComfortFee=JSON.parseObject(list.get(2).toString()).getDoubleValue("value");
            Long t_dspiritComfortFeeId=JSON.parseObject(list.get(2).toString()).getLong("id");
            //丧葬费
            Double t_funeralFee=JSON.parseObject(list.get(3).toString()).getDoubleValue("value");
            Long t_funeralFeeId=JSON.parseObject(list.get(3).toString()).getLong("id");
            //财物损失费
            Double t_dfinancialLoss=JSON.parseObject(list.get(4).toString()).getDoubleValue("value");
            Long t_dfinancialLossId=JSON.parseObject(list.get(4).toString()).getLong("id");
            //合计
            Long t_dtotalId=JSON.parseObject(list.get(5).toString()).getLong("id");
            //扣除精神抚慰金后金额
            Double  subtractSpiritComfortFee=subtractSpiritComfortFee(t_spiritComfortFee);

            Map<String,Object> t_estimateOne=null;
            Map<String,Object> t_estimateTwo=null;
            Map<String,Object> t_estimateThree=null;
            Map<String,Object> t_destimateOne=null;
            Map<String,Object> t_destimateTwo=null;
            //第一部分总和金额
            if(bl){
                t_estimateOne =estimateOneIrresponsibility(t_medicalFee,t_hospitalFoodFee,t_nutritionFee,t_againCureFee);
                //第二部分总和金额
                t_estimateTwo=estimateTwoIrresponsibility(t_lossWordFee,t_nursingFee,t_invalidismIndemnifyFee,t_spiritComfortFee,t_trafficFee,subtractSpiritComfortFee);
                //第三部分总和金额
                t_estimateThree=estimateThreeIrresponsibility(t_financialLoss);

                //第一部分总和金额
                t_destimateOne=destimateOneIrresponsibility(t_medicalFee);
                //第二部分总和金额
                t_destimateTwo=destimateTwoIrresponsibility(t_deathIndemnifyFee, t_spiritComfortFee, t_funeralFee,subtractSpiritComfortFee);
            }else{
                t_estimateOne=estimateOne(t_medicalFee,t_hospitalFoodFee,t_nutritionFee,t_againCureFee);
                //第二部分总和金额
                t_estimateTwo=estimateTwo(t_lossWordFee,t_nursingFee,t_invalidismIndemnifyFee,t_spiritComfortFee,t_trafficFee,subtractSpiritComfortFee);
                //第三部分总和金额
                t_estimateThree=estimateThree(t_financialLoss);

                //第一部分总和金额
                t_destimateOne=destimateOne(t_medicalFee);
                //第二部分总和金额
                t_destimateTwo=destimateTwo(t_deathIndemnifyFee, t_spiritComfortFee, t_funeralFee,subtractSpiritComfortFee);
            }
            //肇事方金额
            Double causeTroubleFee=t_medicalFee*0.1*blameQuotiety;//肇事方金额*责任比例


            //伤者可获得赔款
            Double  hurtMoney=0D;
            //保险公司赔款
            Double  companyMoney=0D;
            //司机承担
            Double  causeTroubleMoney=0D;
            //医疗费总额
            Double medicalMoney=0D;
            //本案件可赔付金额---询价使用
            Double payableFee=0D;
            Double paymedicalFee=0D;
            List<PaymentEstimateReport> paymentEstimateReportList=new ArrayList<PaymentEstimateReport>();
            if(isDeanth==0){
                //总计
                Double totalZhuz=0D;
                Double totalJiaoq=0D;
                Double totalShangy=0D;

                //***********第一部分
                Double estimateOneTotal = DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateOne.get("estimateOneTotal").toString()));
                Double compulsoryInsuranceOneFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateOne.get("compulsoryInsuranceOneFee").toString()));
                Double commercialInsuranceOneFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateOne.get("commercialInsuranceOneFee").toString())*blameQuotiety);
                //肇事方金额
                Double ct_commercialInsuranceOneFee= DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateOne.get("commercialInsuranceOneFee").toString()) * (1 - blameQuotiety));
                Double oneJqTotal=0D;
                Double  oneSyTotal=0D;

                PaymentEstimateReport paymentEstimateReport1=this.paymentEstimateReportMapper.selectByPrimaryKey(t_medicalFeeId);
                paymentEstimateReport1.setCheckMedicalFee(t_medicalFee);
                totalZhuz=totalZhuz+t_medicalFee;

                Double jqMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/estimateOneTotal) *compulsoryInsuranceOneFee);//避免2次计算，性能低下
                paymentEstimateReport1.setCompulsoryInsuranceFee(jqMedicalFee);//交强险可赔偿金额
                oneJqTotal=oneJqTotal+jqMedicalFee;
                totalJiaoq=totalJiaoq+jqMedicalFee;
                medicalMoney=medicalMoney+jqMedicalFee;//医疗费总额计算

                Double  syMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/estimateOneTotal)*commercialInsuranceOneFee);
                paymentEstimateReport1.setCommercialInsuranceFee(syMedicalFee);//商业险可赔偿金额
                oneSyTotal=oneSyTotal+syMedicalFee;
                totalShangy=totalShangy+syMedicalFee;
                medicalMoney=medicalMoney+syMedicalFee;//医疗费总额计算
                //paymentEstimateReport1.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/estimateOneTotal)*ct_commercialInsuranceOneFee));//肇事方可赔偿金额
                paymentEstimateReport1.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive(causeTroubleFee));
                paymedicalFee=paymedicalFee+jqMedicalFee+syMedicalFee;
                this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport1);

                PaymentEstimateReport paymentEstimateReport2=this.paymentEstimateReportMapper.selectByPrimaryKey(t_hospitalFoodFeeId);
                paymentEstimateReport2.setCheckMedicalFee(t_hospitalFoodFee);
                totalZhuz=totalZhuz+t_hospitalFoodFee;
                Double jqhospitalFoodFee=DecimalUtil.twoDecimalTOFourFromFive((t_hospitalFoodFee/estimateOneTotal)*compulsoryInsuranceOneFee);
                paymentEstimateReport2.setCompulsoryInsuranceFee(jqhospitalFoodFee);//交强险可赔偿金额
                totalJiaoq=totalJiaoq+jqhospitalFoodFee;
                oneJqTotal=oneJqTotal+jqhospitalFoodFee;
                medicalMoney=medicalMoney+jqhospitalFoodFee;//医疗费总额计算
                Double syhospitalFoodFee=DecimalUtil.twoDecimalTOFourFromFive((t_hospitalFoodFee/estimateOneTotal)*commercialInsuranceOneFee);
                paymentEstimateReport2.setCommercialInsuranceFee(syhospitalFoodFee);//商业险可赔偿金额
                totalShangy=totalShangy+syhospitalFoodFee;
                oneSyTotal=oneSyTotal+syhospitalFoodFee;
                medicalMoney=medicalMoney+syhospitalFoodFee;//医疗费总额计算
                //paymentEstimateReport2.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_hospitalFoodFee/estimateOneTotal)*ct_commercialInsuranceOneFee));//肇事方可赔偿金额
                paymentEstimateReport2.setCauseTroubleFee(0D);
                this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport2);

                PaymentEstimateReport paymentEstimateReport3=this.paymentEstimateReportMapper.selectByPrimaryKey(t_nutritionFeeId);
                paymentEstimateReport3.setCheckMedicalFee(t_nutritionFee);
                totalZhuz=totalZhuz+t_nutritionFee;
                Double  jqnutritionFee=DecimalUtil.twoDecimalTOFourFromFive((t_nutritionFee/estimateOneTotal)*compulsoryInsuranceOneFee);
                paymentEstimateReport3.setCompulsoryInsuranceFee(jqnutritionFee);//交强险可赔偿金额
                totalJiaoq=totalJiaoq+jqnutritionFee;
                oneJqTotal=oneJqTotal+jqnutritionFee;
                medicalMoney=medicalMoney+jqnutritionFee;//医疗费总额计算
                Double synutritionFee=DecimalUtil.twoDecimalTOFourFromFive((t_nutritionFee/estimateOneTotal)*commercialInsuranceOneFee);
                paymentEstimateReport3.setCommercialInsuranceFee(synutritionFee);//商业险可赔偿金额
                totalShangy=totalShangy+synutritionFee;
                oneSyTotal=oneSyTotal+synutritionFee;
                medicalMoney=medicalMoney+synutritionFee;//医疗费总额计算
                //paymentEstimateReport3.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_nutritionFee/estimateOneTotal)*ct_commercialInsuranceOneFee));
                paymentEstimateReport3.setCauseTroubleFee(0D);
                this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport3);


                PaymentEstimateReport paymentEstimateReport4=this.paymentEstimateReportMapper.selectByPrimaryKey(t_againCureFeeId);
                paymentEstimateReport4.setCheckMedicalFee(t_againCureFee);
                totalZhuz=totalZhuz+t_againCureFee;
                Double jqagainCureFee=compulsoryInsuranceOneFee-oneJqTotal;
                paymentEstimateReport4.setCompulsoryInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive(jqagainCureFee));//交强险可赔偿金额
                totalJiaoq=totalJiaoq+jqagainCureFee;
                medicalMoney=medicalMoney+jqagainCureFee;//医疗费总额计算
                Double syagainCureFee=commercialInsuranceOneFee-oneSyTotal;
                paymentEstimateReport4.setCommercialInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive(syagainCureFee));//商业险可赔偿金额
                totalShangy=totalShangy+syagainCureFee;
                medicalMoney=medicalMoney+syagainCureFee;//医疗费总额计算
                //paymentEstimateReport4.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_againCureFee/estimateOneTotal)*ct_commercialInsuranceOneFee));
                paymentEstimateReport4.setCauseTroubleFee(0D);
                paymedicalFee=paymedicalFee+jqagainCureFee+syagainCureFee;
                this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport4);
                //**********第二部分
                Double estimateTwoTotal =Double.parseDouble(t_estimateTwo.get("estimateTwoTotal").toString());
                Double compulsoryInsuranceTwoFee =Double.parseDouble(t_estimateTwo.get("compulsoryInsuranceTwoFee").toString());
                Double commercialInsuranceTwoFee =Double.parseDouble(t_estimateTwo.get("commercialInsuranceTwoFee").toString())*blameQuotiety;
                Double ct_commercialInsuranceTwoFee =Double.parseDouble(t_estimateTwo.get("commercialInsuranceTwoFee").toString())*(1-blameQuotiety);
                //判断精神抚慰金
                if(subtractSpiritComfortFee>=0){

                    Double  twoJqTotal=0D;
                    Double  twoSyTotal=0D;
                    PaymentEstimateReport paymentEstimateReport5=this.paymentEstimateReportMapper.selectByPrimaryKey(t_lossWordFeeId);
                    paymentEstimateReport5.setCheckMedicalFee(t_lossWordFee);
                    totalZhuz=totalZhuz+t_lossWordFee;
                    Double  jqlossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal)*compulsoryInsuranceTwoFee);
                    paymentEstimateReport5.setCompulsoryInsuranceFee(jqlossWordFee);//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+jqlossWordFee;
                    twoJqTotal=twoJqTotal+jqlossWordFee;
                    Double sylossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal)*commercialInsuranceTwoFee);
                    paymentEstimateReport5.setCommercialInsuranceFee(sylossWordFee);//商业险可赔偿金额
                    totalShangy=totalShangy+sylossWordFee;
                    twoSyTotal=twoSyTotal+sylossWordFee;
                    //paymentEstimateReport5.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal)*ct_commercialInsuranceTwoFee));
                    paymentEstimateReport5.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport5);

                    PaymentEstimateReport paymentEstimateReport6=this.paymentEstimateReportMapper.selectByPrimaryKey(t_nursingFeeId);
                    paymentEstimateReport6.setCheckMedicalFee(t_nursingFee);
                    totalZhuz=totalZhuz+t_nursingFee;
                    Double  jqnursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal)*compulsoryInsuranceTwoFee);
                    paymentEstimateReport6.setCompulsoryInsuranceFee(jqnursingFee);//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+jqnursingFee;
                    twoJqTotal=twoJqTotal+jqnursingFee;
                    Double synursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal)*commercialInsuranceTwoFee);
                    paymentEstimateReport6.setCommercialInsuranceFee(synursingFee);//商业险可赔偿金额
                    totalShangy=totalShangy+synursingFee;
                    twoSyTotal=twoSyTotal+synursingFee;
                    //paymentEstimateReport6.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal)*ct_commercialInsuranceTwoFee));
                    paymentEstimateReport6.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport6);

                    PaymentEstimateReport paymentEstimateReport7=this.paymentEstimateReportMapper.selectByPrimaryKey(t_invalidismIndemnifyFeeId);
                    paymentEstimateReport7.setCheckMedicalFee(t_invalidismIndemnifyFee);
                    totalZhuz=totalZhuz+t_invalidismIndemnifyFee;
                    Double jqinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal)*compulsoryInsuranceTwoFee);
                    paymentEstimateReport7.setCompulsoryInsuranceFee(jqinvalidismIndemnifyFee);//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+jqinvalidismIndemnifyFee;
                    twoJqTotal=twoJqTotal+jqinvalidismIndemnifyFee;
                    Double syinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal)*commercialInsuranceTwoFee);
                    paymentEstimateReport7.setCommercialInsuranceFee(syinvalidismIndemnifyFee);//商业险可赔偿金额
                    totalShangy=totalShangy+syinvalidismIndemnifyFee;
                    twoSyTotal=twoSyTotal+syinvalidismIndemnifyFee;
                    //paymentEstimateReport7.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal)*ct_commercialInsuranceTwoFee));
                    paymentEstimateReport7.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport7);


                    PaymentEstimateReport paymentEstimateReport9=this.paymentEstimateReportMapper.selectByPrimaryKey(t_spiritComfortFeeId);
                    paymentEstimateReport9.setCheckMedicalFee(t_spiritComfortFee);
                    totalZhuz=totalZhuz+t_spiritComfortFee;
                    Double jqspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive(t_spiritComfortFee);
                    paymentEstimateReport9.setCompulsoryInsuranceFee(jqspiritComfortFee);//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+jqspiritComfortFee;
               /* twoJqTotal=twoJqTotal+jqspiritComfortFee;*/
                    paymentEstimateReport9.setCommercialInsuranceFee(0D);//商业险可赔偿金额
                    totalShangy=totalShangy+0D;
                    twoSyTotal=twoSyTotal+0D;
                    //paymentEstimateReport9.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/estimateTwoTotal)*ct_commercialInsuranceTwoFee));
                    paymentEstimateReport9.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport9);

                    PaymentEstimateReport paymentEstimateReport10=this.paymentEstimateReportMapper.selectByPrimaryKey(t_trafficFeeId);
                    paymentEstimateReport10.setCheckMedicalFee(t_trafficFee);
                    totalZhuz=totalZhuz+t_trafficFee;
                    Double jqtrafficFee=compulsoryInsuranceTwoFee-twoJqTotal;

                    paymentEstimateReport10.setCompulsoryInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive(jqtrafficFee));//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+jqtrafficFee;

                    Double sytrafficFee=commercialInsuranceTwoFee-twoSyTotal;
                    //DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal)*commercialInsuranceTwoFee);
                    paymentEstimateReport10.setCommercialInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive(sytrafficFee));//商业险可赔偿金额
                    totalShangy=totalShangy+sytrafficFee;

                    //paymentEstimateReport10.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal)*ct_commercialInsuranceTwoFee));
                    paymentEstimateReport10.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport10);

                }else{
                    Double  twoJqTotal=0D;
                    Double  twoSyTotal=0D;


                    PaymentEstimateReport paymentEstimateReport5=this.paymentEstimateReportMapper.selectByPrimaryKey(t_lossWordFeeId);
                    paymentEstimateReport5.setCheckMedicalFee(t_lossWordFee);
                    totalZhuz=totalZhuz+t_lossWordFee;
                    paymentEstimateReport5.setCompulsoryInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive(0D));//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+0D;
                    twoJqTotal=twoJqTotal+0D;
                    Double sylossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal)*commercialInsuranceTwoFee);
                    paymentEstimateReport5.setCommercialInsuranceFee(sylossWordFee);//商业险可赔偿金额
                    totalShangy=totalShangy+sylossWordFee;
                    twoSyTotal=twoSyTotal+sylossWordFee;
                    //paymentEstimateReport5.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal)*ct_commercialInsuranceTwoFee));
                    paymentEstimateReport5.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport5);

                    PaymentEstimateReport paymentEstimateReport6=this.paymentEstimateReportMapper.selectByPrimaryKey(t_nursingFeeId);
                    paymentEstimateReport6.setCheckMedicalFee(t_nursingFee);
                    totalZhuz=totalZhuz+t_nursingFee;
                    paymentEstimateReport6.setCompulsoryInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive(0D));//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+0D;
                    twoJqTotal=twoJqTotal+0D;
                    Double synursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal)*commercialInsuranceTwoFee);
                    paymentEstimateReport6.setCommercialInsuranceFee(synursingFee);//商业险可赔偿金额
                    totalShangy=totalShangy+synursingFee;
                    twoSyTotal=twoSyTotal+synursingFee;
                    //paymentEstimateReport6.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal)*ct_commercialInsuranceTwoFee));
                    paymentEstimateReport6.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport6);

                    PaymentEstimateReport paymentEstimateReport7=this.paymentEstimateReportMapper.selectByPrimaryKey(t_invalidismIndemnifyFeeId);
                    paymentEstimateReport7.setCheckMedicalFee(t_invalidismIndemnifyFee);
                    totalZhuz=totalZhuz+t_invalidismIndemnifyFee;
                    paymentEstimateReport7.setCompulsoryInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive(0D));//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+0D;
                    twoJqTotal=twoJqTotal+0D;
                    Double syinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal)*commercialInsuranceTwoFee);
                    paymentEstimateReport7.setCommercialInsuranceFee(syinvalidismIndemnifyFee);//商业险可赔偿金额
                    totalShangy=totalShangy+syinvalidismIndemnifyFee;
                    twoSyTotal=twoSyTotal+syinvalidismIndemnifyFee;
                    //paymentEstimateReport7.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal)*ct_commercialInsuranceTwoFee));
                    paymentEstimateReport7.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport7);

                    PaymentEstimateReport paymentEstimateReport9=this.paymentEstimateReportMapper.selectByPrimaryKey(t_spiritComfortFeeId);
                    paymentEstimateReport9.setCheckMedicalFee(t_spiritComfortFee);
                    totalZhuz=totalZhuz+t_spiritComfortFee;
                    paymentEstimateReport9.setCompulsoryInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive(110000));//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+110000;
                    twoJqTotal=twoJqTotal+110000;
                    Double syspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/estimateTwoTotal)*commercialInsuranceTwoFee);
                    paymentEstimateReport9.setCommercialInsuranceFee(syspiritComfortFee);//商业险可赔偿金额
                    totalShangy=totalShangy+syspiritComfortFee;
                    twoSyTotal=twoSyTotal+syspiritComfortFee;
                    //paymentEstimateReport9.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/estimateTwoTotal)*ct_commercialInsuranceTwoFee));
                    paymentEstimateReport9.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport9);


                    PaymentEstimateReport paymentEstimateReport10=this.paymentEstimateReportMapper.selectByPrimaryKey(t_trafficFeeId);
                    paymentEstimateReport10.setCheckMedicalFee(t_trafficFee);
                    totalZhuz=totalZhuz+t_trafficFee;
                    paymentEstimateReport10.setCompulsoryInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive(0D));//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+0D;
                    twoJqTotal=twoJqTotal+0D;
                    Double sytrafficFee=DecimalUtil.twoDecimalTOFourFromFive(commercialInsuranceTwoFee-twoSyTotal);
                    paymentEstimateReport10.setCommercialInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive(sytrafficFee));//商业险可赔偿金额
                    totalShangy=totalShangy+sytrafficFee;
                    //paymentEstimateReport10.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal)*ct_commercialInsuranceTwoFee));
                    paymentEstimateReport10.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport10);

                }


                //*****第三部分
                Double estimateThreeTotal =Double.parseDouble(t_estimateThree.get("estimateThreeTotal").toString());
                Double compulsoryInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("compulsoryInsuranceThreeFee").toString());
                Double commercialInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*blameQuotiety;
                Double ct_commercialInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*(1-blameQuotiety);
                PaymentEstimateReport paymentEstimateReport12=this.paymentEstimateReportMapper.selectByPrimaryKey(t_financialLossId);
                paymentEstimateReport12.setCheckMedicalFee(estimateThreeTotal);
                totalZhuz=totalZhuz+estimateThreeTotal;
                paymentEstimateReport12.setCompulsoryInsuranceFee((compulsoryInsuranceThreeFee));//交强险可赔偿金额
                totalJiaoq=totalJiaoq+compulsoryInsuranceThreeFee;
                paymentEstimateReport12.setCommercialInsuranceFee((commercialInsuranceThreeFee));//商业险可赔偿金额
                totalShangy=totalShangy+commercialInsuranceThreeFee;
                //paymentEstimateReport12.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((estimateThreeTotal/estimateThreeTotal)*ct_commercialInsuranceThreeFee));
                paymentEstimateReport12.setCauseTroubleFee(0D);
                this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport12);
                //******总计数据
                PaymentEstimateReport paymentEstimateReport13=this.paymentEstimateReportMapper.selectByPrimaryKey(t_totalId);
                paymentEstimateReport13.setCheckMedicalFee(DecimalUtil.twoDecimalTOFourFromFive(totalZhuz));
                paymentEstimateReport13.setCompulsoryInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive((totalJiaoq)));//交强险可赔偿金额
                paymentEstimateReport13.setCommercialInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive((totalShangy)));//商业险可赔偿金额
                //paymentEstimateReport13.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive(ct_commercialInsuranceOneFee+ct_commercialInsuranceTwoFee+ct_commercialInsuranceThreeFee));
                paymentEstimateReport13.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive(causeTroubleFee));
                this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport13);
                //伤者可获得赔款
                hurtMoney=paymentEstimateReport13.getCommercialInsuranceFee()+paymentEstimateReport13.getCompulsoryInsuranceFee()+paymentEstimateReport13.getCauseTroubleFee();
                //保险公司赔款
                companyMoney=paymentEstimateReport13.getCommercialInsuranceFee()+paymentEstimateReport13.getCompulsoryInsuranceFee();
                //司机承担
                causeTroubleMoney=paymentEstimateReport13.getCauseTroubleFee();

                payableFee=totalJiaoq+totalShangy;
            }else{
                //t_spiritComfortFee=50000D;
                //总计
                Double totalZhuz=0D;
                Double totalJiaoq=0D;
                Double totalShangy=0D;

                //***********第一部分
                Double destimateOneTotal =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateOne.get("destimateOneTotal").toString()));
                Double dcompulsoryInsuranceOneFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateOne.get("dcompulsoryInsuranceOneFee").toString()));
                Double dcommercialInsuranceOneFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateOne.get("dcommercialInsuranceOneFee").toString())*blameQuotiety);
                Double ct_dcommercialInsuranceOneFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateOne.get("dcommercialInsuranceOneFee").toString())*(1-blameQuotiety));
                PaymentEstimateReport paymentEstimateReport1=this.paymentEstimateReportMapper.selectByPrimaryKey(t_dmedicalFeeId);
                paymentEstimateReport1.setCheckMedicalFee(t_medicalFee);
                totalZhuz=totalZhuz+t_medicalFee;

                paymentEstimateReport1.setCompulsoryInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee / destimateOneTotal) * dcompulsoryInsuranceOneFee));//交强险可赔偿金额
                totalJiaoq=totalJiaoq+DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee / destimateOneTotal) * dcompulsoryInsuranceOneFee);
                paymentEstimateReport1.setCommercialInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/destimateOneTotal)*dcommercialInsuranceOneFee));//商业险可赔偿金额
                totalShangy=totalShangy+DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/destimateOneTotal)*dcommercialInsuranceOneFee);

                //paymentEstimateReport1.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/destimateOneTotal)*ct_dcommercialInsuranceOneFee));
                paymentEstimateReport1.setCauseTroubleFee(causeTroubleFee);
                medicalMoney=medicalMoney+paymentEstimateReport1.getCompulsoryInsuranceFee()+paymentEstimateReport1.getCommercialInsuranceFee();//医疗费总额计算
                paymedicalFee=paymedicalFee+paymentEstimateReport1.getCompulsoryInsuranceFee()+paymentEstimateReport1.getCommercialInsuranceFee();
                this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport1);


                //**********第二部分

                Double destimateTwoTotal =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateTwo.get("destimateTwoTotal").toString()));
                Double dcompulsoryInsuranceTwoFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateTwo.get("dcompulsoryInsuranceTwoFee").toString()));
                Double dcommercialInsuranceTwoFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateTwo.get("dcommercialInsuranceTwoFee").toString())*blameQuotiety);
                Double ct_dcommercialInsuranceTwoFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_destimateTwo.get("dcommercialInsuranceTwoFee").toString())*(1-blameQuotiety));

                //判断精神抚慰金
                if(subtractSpiritComfortFee>=0){

                    Double  twoJqTotal=0D;
                    Double  twoSyTotal=0D;
                    PaymentEstimateReport paymentEstimateReport8=this.paymentEstimateReportMapper.selectByPrimaryKey(t_deathIndemnifyFeeId);
                    paymentEstimateReport8.setCheckMedicalFee(t_deathIndemnifyFee);
                    totalZhuz=totalZhuz+t_deathIndemnifyFee;
                    Double jqdeathIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_deathIndemnifyFee/destimateTwoTotal)*dcompulsoryInsuranceTwoFee);
                    paymentEstimateReport8.setCompulsoryInsuranceFee(jqdeathIndemnifyFee);//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+jqdeathIndemnifyFee;
                    twoJqTotal=twoJqTotal+jqdeathIndemnifyFee;
                    Double sydeathIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_deathIndemnifyFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);
                    paymentEstimateReport8.setCommercialInsuranceFee(sydeathIndemnifyFee);//商业险可赔偿金额
                    totalShangy=totalShangy+sydeathIndemnifyFee;
                    twoSyTotal=twoSyTotal+sydeathIndemnifyFee;
                    //paymentEstimateReport8.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_deathIndemnifyFee/destimateTwoTotal)*ct_dcommercialInsuranceTwoFee));
                    paymentEstimateReport8.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport8);

                    PaymentEstimateReport paymentEstimateReport9=this.paymentEstimateReportMapper.selectByPrimaryKey(t_spiritComfortFeeId);
                    paymentEstimateReport9.setCheckMedicalFee(t_spiritComfortFee);
                    totalZhuz=totalZhuz+t_spiritComfortFee;
                    Double jqspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive(t_spiritComfortFee);
                    paymentEstimateReport9.setCompulsoryInsuranceFee(jqspiritComfortFee);//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+jqspiritComfortFee;
               /* twoJqTotal=twoJqTotal+jqspiritComfortFee;*/
                    paymentEstimateReport9.setCommercialInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive(0D));//商业险可赔偿金额
                    totalShangy=totalShangy+0D;
                    twoSyTotal=twoSyTotal+0D;
                    //paymentEstimateReport9.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/destimateTwoTotal)*ct_dcommercialInsuranceTwoFee));
                    paymentEstimateReport9.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport9);


                    PaymentEstimateReport paymentEstimateReport11=this.paymentEstimateReportMapper.selectByPrimaryKey(t_funeralFeeId);
                    paymentEstimateReport11.setCheckMedicalFee(t_funeralFee);
                    totalZhuz=totalZhuz+t_funeralFee;
                    Double jqfuneralFee=dcompulsoryInsuranceTwoFee-twoJqTotal;
                    paymentEstimateReport11.setCompulsoryInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive(jqfuneralFee));//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+jqfuneralFee;
                    Double syfuneralFee=dcommercialInsuranceTwoFee-twoSyTotal;
                    paymentEstimateReport11.setCommercialInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive(syfuneralFee));//商业险可赔偿金额
                    totalShangy=totalShangy+syfuneralFee;
                    //paymentEstimateReport11.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_funeralFee/destimateTwoTotal)*ct_dcommercialInsuranceTwoFee));
                    paymentEstimateReport11.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport11);
                }else{
                    Double  twoJqTotal=0D;
                    Double  twoSyTotal=0D;
                    PaymentEstimateReport paymentEstimateReport8=this.paymentEstimateReportMapper.selectByPrimaryKey(t_funeralFeeId);
                    paymentEstimateReport8.setCheckMedicalFee(t_deathIndemnifyFee);
                    totalZhuz=totalZhuz+t_deathIndemnifyFee;
                    paymentEstimateReport8.setCompulsoryInsuranceFee(0D);//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+0D;
                    twoJqTotal=twoJqTotal+0D;
                    Double sydeathIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_deathIndemnifyFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);
                    paymentEstimateReport8.setCommercialInsuranceFee(sydeathIndemnifyFee);//商业险可赔偿金额
                    totalShangy=totalShangy+sydeathIndemnifyFee;
                    twoSyTotal=twoSyTotal+sydeathIndemnifyFee;
                    //paymentEstimateReport8.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_deathIndemnifyFee/destimateTwoTotal)*ct_dcommercialInsuranceTwoFee));
                    paymentEstimateReport8.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport8);

                    PaymentEstimateReport paymentEstimateReport9=this.paymentEstimateReportMapper.selectByPrimaryKey(t_spiritComfortFeeId);
                    paymentEstimateReport9.setCheckMedicalFee(t_spiritComfortFee);
                    totalZhuz=totalZhuz+t_spiritComfortFee;
                    paymentEstimateReport9.setCompulsoryInsuranceFee(110000D);//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+110000D;
                    twoJqTotal=twoJqTotal+110000D;
                    Double syspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);
                    paymentEstimateReport9.setCommercialInsuranceFee(syspiritComfortFee);//商业险可赔偿金额
                    totalShangy=totalShangy+syspiritComfortFee;
                    twoSyTotal=twoSyTotal+syspiritComfortFee;
                    //paymentEstimateReport9.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/destimateTwoTotal)*ct_dcommercialInsuranceTwoFee));
                    paymentEstimateReport9.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport9);

                    PaymentEstimateReport paymentEstimateReport11=this.paymentEstimateReportMapper.selectByPrimaryKey(t_funeralFeeId);
                    paymentEstimateReport11.setCheckMedicalFee(t_funeralFee);
                    totalZhuz=totalZhuz+t_funeralFee;
                    paymentEstimateReport11.setCompulsoryInsuranceFee(0D);//交强险可赔偿金额
                    totalJiaoq=totalJiaoq+0D;
                    Double syfuneralFee=DecimalUtil.twoDecimalTOFourFromFive(dcommercialInsuranceTwoFee-twoSyTotal);
                    paymentEstimateReport11.setCommercialInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive(syfuneralFee));//商业险可赔偿金额
                    totalShangy=totalShangy+syfuneralFee;
                    //paymentEstimateReport11.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((t_funeralFee/destimateTwoTotal)*ct_dcommercialInsuranceTwoFee));
                    paymentEstimateReport11.setCauseTroubleFee(0D);
                    this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport11);
                }
                //*****第三部分
                Double estimateThreeTotal =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("estimateThreeTotal").toString()));
                Double compulsoryInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("compulsoryInsuranceThreeFee").toString()));
                Double commercialInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*blameQuotiety);
                Double ct_commercialInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*(1-blameQuotiety));
                PaymentEstimateReport paymentEstimateReport12=this.paymentEstimateReportMapper.selectByPrimaryKey(t_funeralFeeId);
                paymentEstimateReport12.setCheckMedicalFee(estimateThreeTotal);
                totalZhuz=totalZhuz+estimateThreeTotal;
                paymentEstimateReport12.setCompulsoryInsuranceFee((compulsoryInsuranceThreeFee));//交强险可赔偿金额
                totalJiaoq=totalJiaoq+compulsoryInsuranceThreeFee;
                paymentEstimateReport12.setCommercialInsuranceFee((commercialInsuranceThreeFee));//商业险可赔偿金额
                totalShangy=totalShangy+commercialInsuranceThreeFee;
                //paymentEstimateReport12.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive((estimateThreeTotal/estimateThreeTotal)*ct_commercialInsuranceThreeFee));
                paymentEstimateReport12.setCauseTroubleFee(0D);
                this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport12);
                //******总计数据
                PaymentEstimateReport paymentEstimateReport13=this.paymentEstimateReportMapper.selectByPrimaryKey(t_dtotalId);
                paymentEstimateReport13.setCheckMedicalFee(DecimalUtil.twoDecimalTOFourFromFive(totalZhuz));
                paymentEstimateReport13.setCompulsoryInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive((totalJiaoq)));//交强险可赔偿金额
                paymentEstimateReport13.setCommercialInsuranceFee(DecimalUtil.twoDecimalTOFourFromFive((totalShangy)));//商业险可赔偿金额
                //paymentEstimateReport13.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive(ct_dcommercialInsuranceOneFee+ct_dcommercialInsuranceTwoFee+ct_commercialInsuranceThreeFee));
                paymentEstimateReport13.setCauseTroubleFee(DecimalUtil.twoDecimalTOFourFromFive(causeTroubleFee));
                this.paymentEstimateReportMapper.updateByPrimaryKeySelective(paymentEstimateReport13);

                //伤者可获得赔款
                hurtMoney=paymentEstimateReport13.getCommercialInsuranceFee()+paymentEstimateReport13.getCompulsoryInsuranceFee()+paymentEstimateReport13.getCauseTroubleFee();
                //保险公司赔款
                companyMoney=paymentEstimateReport13.getCommercialInsuranceFee()+paymentEstimateReport13.getCompulsoryInsuranceFee();
                //司机承担
                causeTroubleMoney=paymentEstimateReport13.getCauseTroubleFee();

                payableFee=totalJiaoq+totalShangy;

                //精准报价数据重新计算

            }
            //计算报价-- paymentId
            HashMap<String,Object> map = new HashMap<>();
            map.put("paymentEstimateId",paymentId);
            PaymentEstimateInquiry paymentEstimateInquiry = paymentEstimateInquiryMapper.selectPaymentEstimateInquiryByPeId(map);
            paymentEstimateInquiry.setPayableFee(DecimalUtil.twoDecimalTOFourFromFive(payableFee-paymedicalFee));//理赔款
            paymentEstimateInquiry.setMedicalFee(paymedicalFee);//医疗费
            //重新计算各项费用
            Double  free_pre_pay_fee = DecimalUtil.twoDecimalTOFourFromFive(payableFee*0.7);
            paymentEstimateInquiry.setFreePrePayFee(free_pre_pay_fee);
            int ret = paymentEstimateInquiryMapper.updateByPrimaryKeySelective(paymentEstimateInquiry);
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    @ApiMethod(descript = "查询洽谈中的案件", value = "backend_case_negotiate")
    @Override
    public ApiResponse queryCaseNegotiateState(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
//        apiReq.put("orgIds","orgIds");//查看当前登录人 所 管理的机构案件
        apiReq.put("userId",apiReq.get("operatorId"));

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

        int count = caseCenterInfoMapper.queryCaseNegotiateStateCount(apiReq);
        List<CaseCenterInfo> list = caseCenterInfoMapper.queryCaseNegotiateState(apiReq);
        return new ApiResponse<List<CaseCenterInfo>>(ApiMsgEnum.SUCCESS, count, list);
    }

    @ApiMethod(descript = "添加评估报告",value = "backend-add-case-risk-control")
    @Override
    public ApiResponse addCaseRiskControl(ApiRequest apiReq) {
        CaseRiskControl caseRiskControl = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,CaseRiskControl.class);
        int ret = -1;
        if (caseRiskControl != null){
            ret = caseRiskControlMapper.insert(caseRiskControl);
        }
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    @ApiMethod(descript = "修改评估报告",value = "backend-upd-case-risk-control")
    @Override
    public ApiResponse updCaseRiskControl(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        CaseRiskControl caseRiskControl = caseRiskControlMapper.selectByPrimaryKey(id);
        if (caseRiskControl != null){
            caseRiskControl = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,caseRiskControl);
        }
        int ret = caseRiskControlMapper.updateByPrimaryKey(caseRiskControl);
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    @ApiMethod(descript = "获取评估报告",value = "backend-get-case-risk-control")
    @Override
    public ApiResponse getCaseRiskControl(ApiRequest apiReq) {
        Long caseId = apiReq.getLong("caseId");//案件中心ID  key
        String caseNo = apiReq.getString("caseNo");
        HashMap<String,Object> map = new HashMap<>();
        CaseRiskControl caseRiskControl = caseRiskControlMapper.selectByCaseId(caseId);
        if (caseRiskControl == null){
            caseRiskControl = new CaseRiskControl();
            caseRiskControl.setId(null);
            //从案件中心带出可用数据(初始化数据)
            CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);
            caseRiskControl = initData(caseRiskControl,caseCenterInfo);
        }
        caseRiskControl.setCaseId(caseId);
        caseRiskControl.setCaseNo(caseNo);
        map.put("caseRiskControl",caseRiskControl);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }

    /**
     * 初始化数据
     * @param caseRiskControl 评估报告
     * @param caseCenterInfo  案件中心信息
     * @return
     */
    private CaseRiskControl initData(CaseRiskControl caseRiskControl,CaseCenterInfo caseCenterInfo){
        try{
            Map<String,Object> caseCenterInfoMap = new HashMap<>();
            caseCenterInfoMap.put("caseNo",caseCenterInfo.getCaseNo());
            PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectPEAByCaseCenterInfoNew(caseCenterInfoMap);
            PaymentEstimateInquiryDto paymentEstimateInquiryDto = caseCenterInfoMapper.selectNewestPayInquiryNew(caseCenterInfo.getId());
//            CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByCaseId(caseCenterInfo.getId());
            CardInfoDto search = new CardInfoDto();
            search.setCaseId(caseCenterInfo.getId());
            CardInfoDto cardInfoDto = cardInfoDtoMapper.selectByInfo(search);
            caseRiskControl.setUserName(caseCenterInfo.getCaseName());
//        caseRiskControl.setSex();//性别
            caseRiskControl.setUserTel(caseCenterInfo.getCaseTel());
            if (paymentEstimateApply != null){
                caseRiskControl.setLoanPurpose(paymentEstimateApply.getCaseType());
                caseRiskControl.setSettlementMoney(paymentEstimateInquiryDto.getLoanFee());
                caseRiskControl.setLefanProposalMoney(paymentEstimateInquiryDto.getLoanFee());
            }
            if (paymentEstimateInquiryDto != null){
//                caseRiskControl.setLoanMoney(paymentEstimateInquiryDto.getPayableFee());
                caseRiskControl.setLoanMoney(paymentEstimateInquiryDto.getTotalLoanFee());
            }

            Map map = new HashMap();
            map.put("caseId",caseCenterInfo.getId());
            map.put("projectName","合计");
            CaseAssessmentObjReport objReport = caseAssessmentObjReportMapper.selectByCaseIdOrProjectName(map);
            if (objReport != null){
                caseRiskControl.setAssessmentAmount(objReport.getCommerAmount() + objReport.getCompulAmount());
            }
//        caseRiskControl.setLendingCycle();//周期
//        caseRiskControl.setLefanProposalCycle();//周期
            if (cardInfoDto != null){
                caseRiskControl.setAdvanceName(cardInfoDto.getUserName());
                caseRiskControl.setAdvanceBank(cardInfoDto.getBankName());
                caseRiskControl.setAdvanceAccount(cardInfoDto.getCardNo());
            }
        }catch (Exception e){
            e.printStackTrace();//打印出报错信息，但初始化数据还需返回已初始化的数据
        }
        return caseRiskControl;
    }
    /**
     * 根据case_id查看案件理赔测算历史记录
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据案件ID查看案件理赔测算历史记录", value = "backend-select-paymentestimate")
    @SuppressWarnings("rawtypes")
    @Override
    public ApiResponse selectPaymentEstimateApplysHisory(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        Long caseId = apiReq.getLong("caseId");
        Integer type = apiReq.getInt("type");
        if(StringUtils.isEmpty(caseId)){
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        if(StringUtils.isEmpty(type)){
            return new ApiResponse(ApiMsgEnum.MISS_PARAMETER);
        }
        Map map = new HashMap();
        map.put("caseId",caseId);
        map.put("type",type);
        List<CaseCenterInfo> caseCenterInfos = caseCenterInfoMapper.selectCaseCenterInfoByParam(map);
        if (caseCenterInfos == null || caseCenterInfos.size() == 0){
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
        Map param = new HashMap();
        param.put("caseNo",caseCenterInfos.get(0).getCaseNo());
        int count = paymentEstimateApplyMapper.selectCountPaymentEstimateApplyByCaseIdNew(param);
        List<PaymentEstimateApply> list=paymentEstimateApplyMapper.selectPaymentEstimateApplyByCaseIdNew(param);
        return new ApiResponse< List<PaymentEstimateApply>>(ApiMsgEnum.SUCCESS,count,list);
    }

    @ApiMethod(descript = "根据ID查看案件查询测算申请信息", value = "backend-select-paymentestimate-new")
    @Override
    public ApiResponse selectPaymentEstimateApplyNew(ApiRequest apiReq){
        Long id = apiReq.getLong("id");
        PaymentEstimateApply apply = paymentEstimateApplyMapper.selectByPrimaryKey(id);
//        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(id);
//        Map map = new HashMap();
//        map.put("caseId",caseCenterInfo.getCaseId());
//        map.put("caseType",caseCenterInfo.getType());
//        PaymentEstimateApply apply = paymentEstimateApplyMapper.selectPEAByCaseCenterInfo(map);
        //转换成受伤部位 字符串
        String injuredPartStr = "";
        if (apply != null) {
            String injuredPart = apply.getInjuredPart();
            if (!"".equals(injuredPart) && injuredPart != null) {
                injuredPartStr = convertInjuredPartStr(injuredPart);
            }else {
                injuredPartStr = injuredPartStr.concat("无");
            }
            apply.setInjuredPartStr(injuredPartStr);
        }

        return new ApiResponse<PaymentEstimateApply>(ApiMsgEnum.SUCCESS,1,apply);
    }

    @Override
    @ApiMethod(descript = "重复案件", value = "backend-survey-case-repetition")
    public ApiResponse surveyRiskRepetition(ApiRequest apiRequest) {
        Long riskId = apiRequest.getLong("riskId");
        SurveyRiskCase riskCase = surveyRiskCaseMapper.selectByPrimaryKey(riskId);
        if (riskCase == null){
            return new ApiResponse(ApiMsgEnum.SUCCESS,0,Collections.emptyList());
        }
        String idNumber = riskCase.getIdNumber();
        List<SurveyRiskCase> surveyRiskCases = surveyRiskCaseMapper.selectByIdNumber(idNumber);
        if (surveyRiskCases != null && surveyRiskCases.size()>0){
            surveyRiskCases.removeIf(e->riskId.equals(e.getId()));
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,surveyRiskCases.size(),surveyRiskCases);
    }

    /**
     * 理赔测算报告查询
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "理赔测算报告查询", value = "backend-payment_estimate_report-list", apiParams = {
            @ApiParam(descript = "测算申请ID", name = "id")})
    @Override
    public ApiResponse<List<PaymentEstimateReport>> getPaymentEstimateReportList(ApiRequest apiReq) {
        String id = apiReq.getString("id");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if(id != null) {
            paramMap.put("paymentEstimateId", id);
        }else{
            //info页面查询测算报告
            PaymentEstimateInquiryDto inquiryDto = caseCenterInfoMapper.selectNewestPayInquiryNew(apiReq.getLong("caseId"));
            if(inquiryDto !=null){
                //通过精准报价，获取测算申请的id
                paramMap.put("paymentEstimateId",inquiryDto.getPaymentEstimateId());
            }else{
                //如果未做精准报价，直接获取最新的测算申请id
                CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(apiReq.getLong("caseId"));
                if(caseCenterInfo!=null){
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("caseNo",caseCenterInfo.getCaseNo());
                    PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectPEAByCaseCenterInfoNew(map);
                    if(paymentEstimateApply!=null){
                        paramMap.put("paymentEstimateId",paymentEstimateApply.getId());
                    }
                }
            }
        }
        List<PaymentEstimateReport> paymentEstimateReports=this.paymentEstimateReportMapper.selectPaymentEstimateReportByPeId(paramMap);
        return new ApiResponse(ApiMsgEnum.SUCCESS,paymentEstimateReports==null?0:paymentEstimateReports.size(),paymentEstimateReports);
    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "洽谈案件审核", value = "backend_case_negotiate_state", apiParams = {})
    @Override
    public ApiResponse caseNegotiateState(ApiRequest apiReq) {
        Long caseId = apiReq.getLong("id");
        Integer negotiateState = apiReq.getInt("state");
        String negotiateReason = apiReq.getString("reason");
        CaseCenterInfo caseCenterInfo = new CaseCenterInfo();
        caseCenterInfo.setId(caseId);
        caseCenterInfo.setNegotiateState(negotiateState);
        caseCenterInfo.setNegotiateReason(negotiateReason);
        int ret = caseCenterInfoMapper.updateByPrimaryKeySelective(caseCenterInfo);
        CaseCenterExtend caseCenterExtend = caseCenterExtendMapper.selectByPrimaryKey(caseId);
        if (caseCenterExtend != null) {
            caseCenterExtend.setNegotiateDate(new Date());
            caseCenterExtendMapper.updateByPrimaryKeySelective(caseCenterExtend);
        }
        if (ret > 0){
            //初始化赔偿方案
            if(negotiateState == 1){
                caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(caseCenterInfo.getOrgUserId());
                String Conent = caseCenterInfo.getCaseTitle() + "已经通过平台复审,请您基于平台复审的各项费用进行报价提交。";
                //发送短信给CC业务员
                sendMessage(caseCenterInfo.getCaseTitle(),Conent,caseCenterInfo.getOrgUserId(),caseCenterInfo.getOrgUserName(),userInfo == null ? "" : userInfo.getUserTel());

                //初始化数据放到打开公估报告界面
//                List<CaseAssessmentObjReport> list =  caseAssessmentObjReportMapper.queryByCaseId(caseId);
//                if(list == null || list.isEmpty()){
//                    String [] names = {"医疗费","住院伙食补助","营养费","后续治疗费","医疗小计","误工费","护理费","交通费","残疾赔偿金","伤残辅助用具","丧葬费","死亡赔偿金","被抚养人生活费","精神抚慰金","赔款小计","财物项(车、衣物等)","合计"};
//                    for (String name : names){
//                        createCaseAssessmentObjReport(caseCenterInfo.getId(),caseCenterInfo.getCaseNo(),name);
//                    }
//                }
            }
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    public void sendMessage(String title,String Conent,Long userId,String ccName,String telPhone) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setTitle(title);
        messageInfo.setConent(Conent);
        messageInfo.setSenderId(0);
        messageInfo.setSenderName("系统消息");
        messageInfo.setSendTime(new Date());
        messageInfo.setReceiverName(ccName);

        messageInfo.setReceiverId(userId);
        messageInfo.setMessageType(1);
        messageInfo.setDeleteFlag(0);
        messageInfo.setIsRead(0);
        messageInfoMapper.insertSelective(messageInfo);
        //短信消息提醒
        try {
            SendMessageUntil.paymentEstimateMsg(telPhone, title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void createCaseAssessmentObjReport(Long caseId, String caseNo,String projectName){
//        CaseAssessmentObjReport caseAssessmentObjReport = new CaseAssessmentObjReport();
//        caseAssessmentObjReport.setCaseId(caseId);
//        caseAssessmentObjReport.setCaseNo(caseNo);
//        caseAssessmentObjReport.setProjectName(projectName);
//        caseAssessmentObjReport.setCheckAmount(0D);
//        caseAssessmentObjReport.setCommerAmount(0D);
//        caseAssessmentObjReport.setCompulAmount(0D);
//        caseAssessmentObjReportMapper.insertSelective(caseAssessmentObjReport);
//    }

    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "贷款二次审核", value = "backend_case_issuance_state", apiParams = {})
    @Override
    public ApiResponse issuanceState(ApiRequest apiReq) {
        Long id  = apiReq.getLong("id");
        Integer state = apiReq.getInt("state");
        String stateReason = apiReq.getString("stateReason");
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(id);
        if(caseCenterInfo.getIssuanceState() != 1){
            return new ApiResponse(ApiMsgEnum.CASE_RISK_STATE_ERROR);
        }
        caseCenterInfo.setIssuanceState(state);
        caseCenterInfo.setIssuanceReason(stateReason);
        int ret = caseCenterInfoMapper.updateByPrimaryKeySelective(caseCenterInfo);
        if(ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }
        return new ApiResponse(ApiMsgEnum.FAIL);
    }
    /**
     * 扣除精神抚慰金
     * @return
     */
    public Double subtractSpiritComfortFee (Double spiritComfortFee)
    {
        Double subtractSpiritComfortFee=Double.parseDouble(quotaTwo)-spiritComfortFee;
        return subtractSpiritComfortFee;
    }
    /**
     * 获取责任系数
     * @param blameQuotieties 责任系数基本数据列表
     * @param myAccidentLiability 我的责任ID
     * @return
     */
    public static double getBlameQuotiety(Integer mystatus,List<BlameQuotiety> blameQuotieties, Integer myAccidentLiability, Integer otherTarfficStatus, Integer myTarfficStatus){
        long myAccidentLiabilityL = (long)myAccidentLiability;
        for(BlameQuotiety blameQuotiety : blameQuotieties){
            if( blameQuotiety.getId() == myAccidentLiabilityL){
                if(otherTarfficStatus !=1  && myTarfficStatus == 1){
                    return blameQuotiety.getVehicleToUnvehicle();
                }else if(otherTarfficStatus ==1  && myTarfficStatus == 1){
                    if(mystatus==1){
                        return blameQuotiety.getHvehicleToUnvehicle();
                    }else if(mystatus==2){
                        return blameQuotiety.getHvehicleToVehicle();
                    }
                    return blameQuotiety.getVehicleToVehicle();
                }else if(otherTarfficStatus ==1  && myTarfficStatus != 1){
                    return blameQuotiety.getHvehicleToVehicle();
                }else if(otherTarfficStatus !=1  && myTarfficStatus != 1){
                    if(mystatus==1){
                        return blameQuotiety.getHvehicleToUnvehicle();
                    }else if(mystatus==2){
                        return blameQuotiety.getHvehicleToVehicle();
                    }
                }
            }
        }
        return 0;
    }
    /**
     * 判断有无责
     * @return
     */
    public static boolean getResponsibility(List<BlameQuotiety> blameQuotieties, Integer myAccidentLiability, Integer otherTarfficStatus, Integer myTarfficStatus){
        boolean bl=false;
        long myAccidentLiabilityL = (long)myAccidentLiability;
        if(myAccidentLiabilityL==5){
            //无责任，我方是机动车无责
            for(BlameQuotiety blameQuotiety : blameQuotieties){
                if(blameQuotiety.getId()==5)
                {
                    if(otherTarfficStatus !=1  && myTarfficStatus == 1){
                        bl=true;
                    }
                }
            }
        }else if(myAccidentLiabilityL==1){
            //全责，我方全责，机动车方无责
            for(BlameQuotiety blameQuotiety : blameQuotieties){
                if(blameQuotiety.getId()==1)
                {
                    if(otherTarfficStatus ==1&&myTarfficStatus != 1){
                        bl=true;
                    }
                }
            }
        }
        return bl;
    }
    //限额5万第一部分计算费用总和 残 无责任
    public Map<String,Object> estimateOneIrresponsibility (Double  medicalFee,Double  hospitalFoodFee,Double nutritionFee,Double againCureFee)
    {
        //第一部分费用总和
        Double estimateOneTotal=medicalFee+hospitalFoodFee+nutritionFee+againCureFee;
        //第一部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第一部分商业险总和
        Double commercialInsuranceFee=0D;
        Double irrespQuotaOneFee=Double.parseDouble(irrespQuotaOne);
        if(estimateOneTotal>irrespQuotaOneFee){
            compulsoryInsuranceFee = irrespQuotaOneFee;//限额只赔交强险
            commercialInsuranceFee=0D;//商业险为0D
        }else{
            compulsoryInsuranceFee=estimateOneTotal;//只赔付交强险
            commercialInsuranceFee=0D;//商业险为0D
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("estimateOneTotal",estimateOneTotal);//费用总和
        map.put("compulsoryInsuranceOneFee",compulsoryInsuranceFee);//交强险
        map.put("commercialInsuranceOneFee",commercialInsuranceFee);//商业险

        return map;
    }
    //第二部分费用，无责任的情况，计算
    public Map<String,Object> estimateTwoIrresponsibility(Double lossWordFee,Double nursingFee,Double invalidismIndemnifyFee,Double spiritComfortFee,Double trafficFee,Double subtractSpiritComfortFee)
    {
        Map<String,Object> map=new HashMap<String,Object>();
        //第2部分费用总和
        Double estimateTwoTotal=lossWordFee+nursingFee+invalidismIndemnifyFee+trafficFee+spiritComfortFee;
        //第2部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第2部分商业险总和
        Double commercialInsuranceFee=0D;
        Double irrespQuotaTwoFee=Double.valueOf(irrespQuotaTwo);
        if(estimateTwoTotal>=irrespQuotaTwoFee){
            compulsoryInsuranceFee = irrespQuotaTwoFee;
            commercialInsuranceFee=estimateTwoTotal-irrespQuotaTwoFee;
        }else{
            compulsoryInsuranceFee = estimateTwoTotal;
        }
        map.put("estimateTwoTotal",estimateTwoTotal-spiritComfortFee);//费用总和
        map.put("compulsoryInsuranceTwoFee",compulsoryInsuranceFee-spiritComfortFee);//交强险
        map.put("commercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        /*if(subtractSpiritComfortFee>=0){
            if(estimateTwoTotal>subtractSpiritComfortFee){
                if(subtractSpiritComfortFee>irrespQuotaTwoFee){
                    compulsoryInsuranceFee = irrespQuotaTwoFee;
                }else{
                    compulsoryInsuranceFee = subtractSpiritComfortFee;
                }
                commercialInsuranceFee=0D;
            }
            else{
                if(estimateTwoTotal>irrespQuotaTwoFee){
                    compulsoryInsuranceFee = irrespQuotaTwoFee;
                }else{
                    compulsoryInsuranceFee = estimateTwoTotal;
                }
                commercialInsuranceFee=0D;
            }
            map.put("estimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("compulsoryInsuranceTwoFee",compulsoryInsuranceFee);//交强险
            map.put("commercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        }else{
            map.put("estimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("compulsoryInsuranceTwoFee",0D);//交强险
            map.put("commercialInsuranceTwoFee",estimateTwoTotal);//商业险
        }*/
        return map;
    }
    //限额2000，第三部分计算费用总和(财务损失) 无责任
    public Map<String,Object> estimateThreeIrresponsibility (Double financialLossFee)
    {
        //第3部分费用总和
        Double estimateThreeTotal=financialLossFee;
        //第3部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第3部分商业险总和
        Double commercialInsuranceFee=0D;
        Double irrespQuotaThreeFee=Double.parseDouble(irrespQuotaThree);
        if(estimateThreeTotal>irrespQuotaThreeFee){
            compulsoryInsuranceFee = irrespQuotaThreeFee;
            commercialInsuranceFee=0D;
        }
        else{
            compulsoryInsuranceFee=estimateThreeTotal;
            commercialInsuranceFee=0D;
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("estimateThreeTotal",estimateThreeTotal);//费用总和
        map.put("compulsoryInsuranceThreeFee",compulsoryInsuranceFee);//交强险
        map.put("commercialInsuranceThreeFee",commercialInsuranceFee);//商业险
        return map;
    }
    //第一部分无责死亡的计算
    public Map<String,Object> destimateOneIrresponsibility (Double  medicalFee)
    {
        //第一部分费用总和
        Double estimateOneTotal=medicalFee;
        //第一部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第一部分商业险总和
        Double commercialInsuranceFee=0D;
        Double irrespQuotaOneFee=Double.parseDouble(irrespQuotaOne);
        if(estimateOneTotal>irrespQuotaOneFee){
            compulsoryInsuranceFee = irrespQuotaOneFee;
            commercialInsuranceFee=0D;
        }
        else{
            compulsoryInsuranceFee=estimateOneTotal;
            commercialInsuranceFee=0D;
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("destimateOneTotal",estimateOneTotal);//费用总和
        map.put("dcompulsoryInsuranceOneFee",compulsoryInsuranceFee);//交强险
        map.put("dcommercialInsuranceOneFee",commercialInsuranceFee);//商业险
        return map;
    }
    //限额11万，第二部分计算费用总和 死亡 无责任计算
    public Map<String,Object> destimateTwoIrresponsibility(Double deathIndemnifyFee,Double spiritComfortFee,Double funeralFee,Double subtractSpiritComfortFee)
    {
        //第2部分费用总和
        Double estimateTwoTotal=deathIndemnifyFee+funeralFee+spiritComfortFee;
        //第2部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第2部分商业险总和
        Double commercialInsuranceFee=0D;
        /**
         * 无责限额，只赔交强险
         */
        Double irrespQuotaTwoFee=Double.valueOf(irrespQuotaTwo);
        Map<String,Object> map=new HashMap<String,Object>();

        if(estimateTwoTotal>=irrespQuotaTwoFee){
            compulsoryInsuranceFee = irrespQuotaTwoFee;
            commercialInsuranceFee=estimateTwoTotal-irrespQuotaTwoFee;
        }else{
            compulsoryInsuranceFee=estimateTwoTotal;
        }
        map.put("destimateTwoTotal",estimateTwoTotal-spiritComfortFee);//费用总和
        map.put("dcompulsoryInsuranceTwoFee",compulsoryInsuranceFee-spiritComfortFee);//交强险
        map.put("dcommercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        /*if(subtractSpiritComfortFee>=0){
            if(estimateTwoTotal>subtractSpiritComfortFee){
                compulsoryInsuranceFee = subtractSpiritComfortFee;
                if(subtractSpiritComfortFee>irrespQuotaTwoFee){
                    compulsoryInsuranceFee = irrespQuotaTwoFee;
                }else{
                    compulsoryInsuranceFee = subtractSpiritComfortFee;
                }
                commercialInsuranceFee=0D;
            }
            else{
                if(estimateTwoTotal>irrespQuotaTwoFee){
                    compulsoryInsuranceFee = irrespQuotaTwoFee;
                }else{
                    compulsoryInsuranceFee = estimateTwoTotal;
                }
                commercialInsuranceFee=0D;
            }
            map.put("destimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("dcompulsoryInsuranceTwoFee",compulsoryInsuranceFee);//交强险
            map.put("dcommercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        }else{
            map.put("destimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("dcompulsoryInsuranceTwoFee",0D);//交强险
            map.put("dcommercialInsuranceTwoFee",estimateTwoTotal);//商业险
        }*/
        return map;
    }
    //限额5万第一部分计算费用总和 残 除开无责任
    public Map<String,Object> estimateOne (Double  medicalFee,Double  hospitalFoodFee,Double nutritionFee,Double againCureFee)
    {
        //第一部分费用总和
        Double estimateOneTotal=medicalFee+hospitalFoodFee+nutritionFee+againCureFee;
        //第一部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第一部分商业险总和
        Double commercialInsuranceFee=0D;
        Double quotaOneFee=Double.parseDouble(quotaOne);
        if(estimateOneTotal>quotaOneFee){
            compulsoryInsuranceFee = quotaOneFee;
            commercialInsuranceFee=estimateOneTotal-quotaOneFee;
        }else{
            compulsoryInsuranceFee=estimateOneTotal;
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("estimateOneTotal",estimateOneTotal);//费用总和
        map.put("compulsoryInsuranceOneFee",compulsoryInsuranceFee);//交强险
        map.put("commercialInsuranceOneFee",commercialInsuranceFee);//商业险

        return map;
    }
    //限额11万，第二部分计算费用总和加上优先扣除精神抚慰金算法  残
    public Map<String,Object> estimateTwo(Double lossWordFee,Double nursingFee,Double invalidismIndemnifyFee,Double spiritComfortFee,Double trafficFee,Double subtractSpiritComfortFee)
    {
        Map<String,Object> map=new HashMap<String,Object>();
        //第2部分费用总和
        Double estimateTwoTotal=lossWordFee+nursingFee+invalidismIndemnifyFee+trafficFee+spiritComfortFee;
        //第2部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第2部分商业险总和
        Double commercialInsuranceFee=0D;
        Double quotaTwoFee=Double.valueOf(quotaTwo);
        if(estimateTwoTotal>=quotaTwoFee){
            compulsoryInsuranceFee = quotaTwoFee;
            commercialInsuranceFee=estimateTwoTotal-quotaTwoFee;
        }else{
            compulsoryInsuranceFee=estimateTwoTotal;
        }
        map.put("estimateTwoTotal",estimateTwoTotal-spiritComfortFee);//费用总和
        map.put("compulsoryInsuranceTwoFee",compulsoryInsuranceFee-spiritComfortFee);//交强险
        map.put("commercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        /*if(subtractSpiritComfortFee>=0){
            if(estimateTwoTotal>subtractSpiritComfortFee){
                compulsoryInsuranceFee = subtractSpiritComfortFee;
                commercialInsuranceFee=estimateTwoTotal-subtractSpiritComfortFee;
            }
            else{
                compulsoryInsuranceFee=estimateTwoTotal;
            }
            map.put("estimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("compulsoryInsuranceTwoFee",compulsoryInsuranceFee);//交强险
            map.put("commercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        }else{
            map.put("estimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("compulsoryInsuranceTwoFee",0D);//交强险
            map.put("commercialInsuranceTwoFee",estimateTwoTotal);//商业险
        }*/
        return map;
    }
    //限额2000，第三部分计算费用总和(财务损失)
    public Map<String,Object> estimateThree (Double financialLossFee)
    {
        //第3部分费用总和
        Double estimateThreeTotal=financialLossFee;
        //第3部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第3部分商业险总和
        Double commercialInsuranceFee=0D;
        Double quotaThreeFee=Double.parseDouble(quotaThree);
        if(estimateThreeTotal>quotaThreeFee){
            compulsoryInsuranceFee = quotaThreeFee;
            commercialInsuranceFee=estimateThreeTotal-quotaThreeFee;
        }
        else{
            compulsoryInsuranceFee=estimateThreeTotal;
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("estimateThreeTotal",estimateThreeTotal);//费用总和
        map.put("compulsoryInsuranceThreeFee",compulsoryInsuranceFee);//交强险
        map.put("commercialInsuranceThreeFee",commercialInsuranceFee);//商业险
        return map;
    }
    //限额第一部分计算费用总和  死亡
    public Map<String,Object> destimateOne (Double  medicalFee)
    {
        //第一部分费用总和
        Double estimateOneTotal=medicalFee;
        //第一部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第一部分商业险总和
        Double commercialInsuranceFee=0D;
        Double quotaOneFee=Double.parseDouble(quotaOne);
        if(estimateOneTotal>quotaOneFee){
            compulsoryInsuranceFee = quotaOneFee;
            commercialInsuranceFee=estimateOneTotal-quotaOneFee;
        }
        else{
            compulsoryInsuranceFee=estimateOneTotal;
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("destimateOneTotal",estimateOneTotal);//费用总和
        map.put("dcompulsoryInsuranceOneFee",compulsoryInsuranceFee);//交强险
        map.put("dcommercialInsuranceOneFee",commercialInsuranceFee);//商业险
        return map;
    }
    //限额11万，第二部分计算费用总和 死亡
    public Map<String,Object> destimateTwo(Double deathIndemnifyFee,Double spiritComfortFee,Double funeralFee,Double subtractSpiritComfortFee)
    {
        //第2部分费用总和
        Double estimateTwoTotal=deathIndemnifyFee+funeralFee+spiritComfortFee;
        //第2部分费用交强险总和
        Double compulsoryInsuranceFee=0D;
        //第2部分商业险总和
        Double commercialInsuranceFee=0D;
        Double quotaTwoFee=Double.valueOf(quotaTwo);
        Map<String,Object> map=new HashMap<String,Object>();

        if(estimateTwoTotal>=quotaTwoFee){
            compulsoryInsuranceFee = quotaTwoFee;
            commercialInsuranceFee=estimateTwoTotal-quotaTwoFee;
        }else{
            compulsoryInsuranceFee=estimateTwoTotal;
        }
        map.put("destimateTwoTotal",estimateTwoTotal-spiritComfortFee);//费用总和
        map.put("dcompulsoryInsuranceTwoFee",compulsoryInsuranceFee-spiritComfortFee);//交强险
        map.put("dcommercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        /*if(subtractSpiritComfortFee>=0){
            if(estimateTwoTotal>subtractSpiritComfortFee){
                compulsoryInsuranceFee = subtractSpiritComfortFee;
                commercialInsuranceFee=estimateTwoTotal-subtractSpiritComfortFee;
            }
            else{
                compulsoryInsuranceFee=estimateTwoTotal;
            }
            map.put("destimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("dcompulsoryInsuranceTwoFee",compulsoryInsuranceFee);//交强险
            map.put("dcommercialInsuranceTwoFee",commercialInsuranceFee);//商业险
        }else{
            map.put("destimateTwoTotal",estimateTwoTotal);//费用总和
            map.put("dcompulsoryInsuranceTwoFee",0D);//交强险
            map.put("dcommercialInsuranceTwoFee",estimateTwoTotal);//商业险
        }*/
        return map;
    }

    @ApiMethod(descript = "生成评估报告PDF",value = "create-case-risk-control-pdf")
    @Override
    public ApiResponse createCaseRiskPDF(ApiRequest apiReq) {
        String pdfName = apiReq.getString("pdfName");

        Long caseId = apiReq.getLong("caseId");
        String caseNo = apiReq.getString("caseNo");
        HashMap<String,Object> map = new HashMap<>();
        CaseRiskControl caseRiskControl = caseRiskControlMapper.selectByCaseId(caseId);
        if (caseRiskControl == null){
            caseRiskControl = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,CaseRiskControl.class);
        }
        File file = PDFUtil.createCaseRiskControlPDF(reportPathUrl,pdfName,caseRiskControl);

        String organizeImgFilePath = reportPathUrl + "20180313100923.png";
        String companyName = "上海乐凡金融信息服务有限公司";
        String organCode = "913101080677536805";
        String companyAddress = "上海市闸北区共和新路340号209、210室";
        String agentName = "高长青";
        String agentIdNo = "321102197312221917";
        RiskReportSign.doSignWithImageSealByStream(file.getPath(),file.getParent(),file.getName(),organizeImgFilePath,companyName,organCode,companyAddress,agentName,agentIdNo);


        String filePath = file.getPath();//   /data/webapps/pic/images/saas/
        filePath = filePath.replaceAll("/data/webapps/","http://openapi.shlefan.com/");
        String fileName = file.getName();
        CommonFile commonFile = new CommonFile();
        commonFile.setFileName(fileName.substring(0,fileName.lastIndexOf(".")));
        commonFile.setFilePath(filePath);
        List<CommonFile> commonFiles = commonFileMapper.selectByFilePath(commonFile);
        for (CommonFile item : commonFiles){
            commonFileMapper.deleteByPrimaryKey(item.getId());
            caseFileMidMapper.deleteByFileId(item.getId());
        }
        //保存附件表
        commonFile.setCreateTime(new Date());
        commonFileMapper.insert(commonFile);
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);
        CaseFileMid caseFileMid = new CaseFileMid();
        caseFileMid.setCaseId(caseCenterInfo.getId());
        caseFileMid.setFileId(commonFile.getId());
        caseFileMid.setCommonFile(commonFile);
        caseFileMid.setCatalogId(2L);
        caseFileMid.setCatalogName("其他材料");
        caseFileMid.setCaseNo(caseCenterInfo.getCaseNo());
        caseFileMidMapper.insert(caseFileMid);
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }

    /**
     * 转换成受伤部位 字符串  (2,3,4 to 背部,腰部,臀部)
     * @param
     * @return
     */
    private String convertInjuredPartStr(String injuredPart) {
        String injuredPartStr = "";
        if (!"".equals(injuredPart) && injuredPart != null) {
            String [] grades = injuredPart.split(",");
            for (String s : grades) {
                if ("无".equals(s) || "".equals(s)){
                    injuredPartStr = injuredPartStr.concat("无，");
                    continue;
                }
                try {
                    switch (Integer.valueOf(s)){
                        case 2 : injuredPartStr = injuredPartStr.concat("背部,"); break;
                        case 3 : injuredPartStr = injuredPartStr.concat("腰部,"); break;
                        case 4 : injuredPartStr = injuredPartStr.concat("臀部,"); break;
                        case 5 : injuredPartStr = injuredPartStr.concat("头颅,"); break;
                        case 6 : injuredPartStr = injuredPartStr.concat("面部,"); break;
                        case 7 : injuredPartStr = injuredPartStr.concat("颈部,"); break;
                        case 8 : injuredPartStr = injuredPartStr.concat("胸部,"); break;
                        case 9 : injuredPartStr = injuredPartStr.concat("上肢,"); break;
                        case 10 : injuredPartStr = injuredPartStr.concat("腹部,"); break;
                        case 11 : injuredPartStr = injuredPartStr.concat("会阴部,"); break;
                        case 12 : injuredPartStr = injuredPartStr.concat("手,"); break;
                        case 13 : injuredPartStr = injuredPartStr.concat("下肢,"); break;
                        case 14 : injuredPartStr = injuredPartStr.concat("足,"); break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else {
            injuredPartStr = injuredPartStr.concat("无，");
        }
        return injuredPartStr.substring(0,injuredPartStr.length() - 1);
    }
}
