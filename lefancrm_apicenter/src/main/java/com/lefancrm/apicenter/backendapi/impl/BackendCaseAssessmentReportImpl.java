package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendCaseAssessmentReportApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.PaymentEstimateInquiryDto;
import com.lefancrm.apicenter.enums.ReportProjectEnum;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.RedisService;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.apicenter.util.DecimalUtil;
import com.lefancrm.apicenter.util.PDFUtil;
import com.lefancrm.apicenter.util.PaymentAlgorithmUtil;
import com.lefancrm.apicenter.util.tsign.RiskReportSign;
import com.lefancrm.base.annotations.ApiMethod;
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

@Service
@ApiService(descript = "公估报告API")
public class BackendCaseAssessmentReportImpl extends BaseServiceImpl implements BackendCaseAssessmentReportApi {
    @Autowired
    private CaseAssessmentReportMapper caseAssessmentReportMapper;

    @Autowired
    private CaseAssessmentObjReportMapper caseAssessmentObjReportMapper;
    @Autowired
    private CaseFileMidMapper caseFileMidMapper;
    @Autowired
    private CommonFileMapper commonFileMapper;
    @Autowired
    private CaseCenterInfoMapper caseCenterInfoMapper;

    @Autowired
    private PaymentEstimateApplyMapper paymentEstimateApplyMapper;
    @Autowired
    private PaymentEstimateReportMapper paymentEstimateReportMapper;

    @Autowired
    private BlameQuotietyMapper blameQuotietyMapper;
    @Autowired
    private RedisService redisService;

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
    @ApiMethod(descript = "新增公估报告",value = "backend-add-case-assessment-report")
    @Override
    public ApiResponse addCaseAssessmentReport(ApiRequest apiReq) {
        CaseAssessmentReport caseAssessmentReport = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,CaseAssessmentReport.class);
        int ret = -1;
        if (caseAssessmentReport != null){
            ret = caseAssessmentReportMapper.insert(caseAssessmentReport);
        }
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    @ApiMethod(descript = "修改公估报告",value = "backend-upd-case-assessment-report")
    @Override
    public ApiResponse updCaseAssessmentReport(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        CaseAssessmentReport caseAssessmentReport = caseAssessmentReportMapper.selectByPrimaryKey(id);
        if (caseAssessmentReport != null){
            caseAssessmentReport = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,caseAssessmentReport);
        }
        toPDF(caseAssessmentReport);
        int ret = caseAssessmentReportMapper.updateByPrimaryKey(caseAssessmentReport);
        if (ret > 0){
            return new ApiResponse(ApiMsgEnum.SUCCESS);
        }else{
            return new ApiResponse(ApiMsgEnum.FAIL);
        }
    }

    @ApiMethod(descript = "获取公估报告",value = "backend-get-case-assessment-report")
    @Override
    public ApiResponse getCaseAssessmentReport(ApiRequest apiReq) {
        Long caseId = apiReq.getLong("caseId");
        String caseNo = apiReq.getString("caseNo");
        String op = apiReq.getString("op");
        HashMap<String,Object> map = new HashMap<>();
        CaseAssessmentReport caseAssessmentReport = caseAssessmentReportMapper.selectByCaseId(caseId);
        if (caseAssessmentReport == null){
            caseAssessmentReport = new CaseAssessmentReport();
            caseAssessmentReport.setId(null);
            caseAssessmentReport.setCaseId(caseId);
            caseAssessmentReport.setCaseNo(caseNo);
            //从案件中心带出可用数据(初始化数据)
            CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);
            caseAssessmentReport = initData(caseAssessmentReport,caseCenterInfo);
            if ("view".equals(op)){

            }else{
                caseAssessmentReportMapper.insert(caseAssessmentReport);
            }
        }
        caseAssessmentReport.setCaseId(caseId);
        caseAssessmentReport.setCaseNo(caseNo);
        map.put("caseAssessmentReport",caseAssessmentReport);
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,map);
    }

    @ApiMethod(descript = "修改案件赔偿及保险理赔方案",value = "backend-update-case-assessment-obj-report")
    @Override
    public ApiResponse updCaseAssessmentObjReport(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        String type = apiReq.getString("type");
        String value = apiReq.getString("value");
        CaseAssessmentObjReport recode = caseAssessmentObjReportMapper.selectByPrimaryKey(id);
        // 1 核损金额 ,2 核损依据 ,3 交强险 ,4 商业险
        if ("1".equals(type)){
            recode.setCheckAmount(Double.parseDouble(value == null || "".equals(value) ? "0" : value));
        }else if("2".equals(type)){
            recode.setCheckBasis(value == null ? "" : value);
        }else if("3".equals(type)){
            recode.setCommerAmount(Double.parseDouble(value == null || "".equals(value) ? "0" : value));
        }else if("4".equals(type)){
            recode.setCompulAmount(Double.parseDouble(value == null || "".equals(value) ? "0" : value));
        }
        caseAssessmentObjReportMapper.updateByPrimaryKeySelective(recode);
        //只有更改核损金额项目的时候计算小计合计
        if ("1".equals(type)){
            //计算{医疗小计}
            List itemsDoctor = Arrays.asList("医疗费","住院伙食补助","后续治疗费");
            CaseAssessmentObjReport objReportDoctor = caseAssessmentObjReportMapper.selectByCaseIdOrProjectName(returnMap(recode.getCaseId(),"医疗小计"));
            if (!"医疗小计".equals(recode.getProjectName())){
                if (itemsDoctor.contains(recode.getProjectName())){
                    Double amount = 0D;
                    for (Object name : itemsDoctor){
                        CaseAssessmentObjReport item = caseAssessmentObjReportMapper.selectByCaseIdOrProjectName(returnMap(recode.getCaseId(),name.toString()));
                        amount += (item.getCheckAmount() == null ? 0 : item.getCheckAmount());
                    }
                    objReportDoctor.setCheckAmount(amount);
                    caseAssessmentObjReportMapper.updateByPrimaryKey(objReportDoctor);
                }
            }

            //计算{赔款小计}
            List itemsPay = Arrays.asList("误工费","护理费","交通费","残疾赔偿金","伤残辅助用具","丧葬费","死亡赔偿金","被抚养人生活费","精神抚慰金");
            CaseAssessmentObjReport objReportPay = caseAssessmentObjReportMapper.selectByCaseIdOrProjectName(returnMap(recode.getCaseId(),"赔款小计"));
            if (!"赔款小计".equals(recode.getProjectName())){
                if (itemsPay.contains(recode.getProjectName())){
                    Double amount = 0D;
                    for (Object name : itemsPay){
                        CaseAssessmentObjReport item = caseAssessmentObjReportMapper.selectByCaseIdOrProjectName(returnMap(recode.getCaseId(),name.toString()));
                        amount += (item.getCheckAmount() == null ? 0 : item.getCheckAmount());
                    }
                    objReportPay.setCheckAmount(amount);
                    caseAssessmentObjReportMapper.updateByPrimaryKey(objReportPay);
                }
            }

            //计算合计   医疗小计+赔款小计+财务项
            if (!"合计".equals(recode.getProjectName())){
                CaseAssessmentObjReport objReportAll = caseAssessmentObjReportMapper.selectByCaseIdOrProjectName(returnMap(recode.getCaseId(),"合计"));
                CaseAssessmentObjReport objReportOther = caseAssessmentObjReportMapper.selectByCaseIdOrProjectName(returnMap(recode.getCaseId(),"财物项(车、衣物等)"));
                objReportAll.setCheckAmount(objReportDoctor.getCheckAmount() + objReportPay.getCheckAmount() + objReportOther.getCheckAmount());
                caseAssessmentObjReportMapper.updateByPrimaryKey(objReportAll);
                insuranceCalculation(recode.getCaseId());
            }
        }
        CaseAssessmentReport caseAssessmentReport = caseAssessmentReportMapper.selectByCaseId(recode.getCaseId());
        toPDF(caseAssessmentReport);
        return new ApiResponse(ApiMsgEnum.SUCCESS);
    }


    //公估报告修改项目后算法计算交强险，商业险
    public void insuranceCalculation(Long caseId){
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
        List<CaseAssessmentObjReport> caseClosedObjReportList = this.caseAssessmentObjReportMapper.selectByCaseId(caseId);
        for(CaseAssessmentObjReport caseAssessmentObjReport:caseClosedObjReportList){
            if(caseAssessmentObjReport.getProjectName().equals("医疗费")){
                t_medicalFee= caseAssessmentObjReport.getCheckAmount();
                t_medicalFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("住院伙食补助")){
                //医院伙食补助费
                t_hospitalFoodFee=caseAssessmentObjReport.getCheckAmount();
                t_hospitalFoodFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("营养费")){
                //营养费
                t_nutritionFee=caseAssessmentObjReport.getCheckAmount();
                t_nutritionFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("后续治疗费")){
                //后续治疗费
                t_againCureFee=caseAssessmentObjReport.getCheckAmount();
                t_againCureFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("医疗小计")){
                //医疗小计
                t_totalYlId=caseAssessmentObjReport.getId();
            }
            else if(caseAssessmentObjReport.getProjectName().equals("护理费")){
                //护理费
                t_nursingFee=caseAssessmentObjReport.getCheckAmount();
                t_nursingFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("误工费")){
                //误工费
                t_lossWordFee=caseAssessmentObjReport.getCheckAmount();
                t_lossWordFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("残疾赔偿金")){
                //残疾赔偿金
                t_invalidismIndemnifyFee=caseAssessmentObjReport.getCheckAmount();
                t_invalidismIndemnifyFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("精神抚慰金")){
                //精神抚慰金
                t_spiritComfortFee=caseAssessmentObjReport.getCheckAmount();
                t_spiritComfortFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("被抚养人生活费")){
                //被抚养人生活费
                Double t_liveFee=caseAssessmentObjReport.getCheckAmount();
                Long t_liveFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("死亡赔偿金")){
                //死亡赔偿金
                t_deathIndemnifyFee=caseAssessmentObjReport.getCheckAmount();
                t_deathIndemnifyFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("丧葬费")){
                //丧葬费
                t_funeralFee=caseAssessmentObjReport.getCheckAmount();
                t_funeralFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("交通费")){
                //交通费
                t_trafficFee=caseAssessmentObjReport.getCheckAmount();
                t_trafficFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("诉讼费")){
                //诉讼费
                t_litigationFee=caseAssessmentObjReport.getCheckAmount();
                t_litigationFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("残疾器具费")){
                //残疾器具费
                t_disabilityEquipmentFee=caseAssessmentObjReport.getCheckAmount();
                t_disabilityEquipmentFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("鉴定费")){
                //鉴定费
                t_appraisalFee=caseAssessmentObjReport.getCheckAmount();
                t_appraisalFeeId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("赔款小计")){
                //赔偿小计
                t_totalPcId=caseAssessmentObjReport.getId();
            }
            else if(caseAssessmentObjReport.getProjectName().equals("财物项(车、衣物等)")){
                //财产损失
                t_financialLoss=caseAssessmentObjReport.getCheckAmount();
                t_financialLossId=caseAssessmentObjReport.getId();
            }else if(caseAssessmentObjReport.getProjectName().equals("合计")){
                //合计损失
                t_totalFeeId=caseAssessmentObjReport.getId();
            }
        }
        CaseCenterInfo caseCenterInfo = this.caseCenterInfoMapper.selectByPrimaryKey(caseId);
        //查出原来提交的测算数据
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("caseNo",caseCenterInfo.getCaseNo());
        PaymentEstimateApply paymentEstimateApply =  this.paymentEstimateApplyMapper.selectPEAByCaseCenterInfoNew(map);
        if (paymentEstimateApply == null){
            return;
        }
        List<BlameQuotiety> blameQuotieties = redisService.estimateGet("blameQuotieties",new BlameQuotiety());
        if(blameQuotieties==null){
            blameQuotieties =    this.blameQuotietyMapper.selectBlameQuotiety();
            redisService.estimateAdd("blameQuotieties",blameQuotieties,30 * 24 * 60 * 60);
        }
        //根据新的责任计算责任比例
        double blameQuotiety = PaymentAlgorithmUtil.getBlameQuotiety(paymentEstimateApply.getMyStatus(), blameQuotieties, paymentEstimateApply.getMyAccidentLiability(), paymentEstimateApply.getOtherTarfficStatus(), paymentEstimateApply.getMyTarfficStatus());
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
        Double causeTroubleFee=t_medicalFee*0.1*blameQuotiety;//肇事方金额*责任比例
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
            CaseAssessmentObjReport caseAssessmentObjReport1=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_medicalFeeId);
            Double jqMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/estimateOneTotal) *compulsoryInsuranceOneFee);//医疗费交强险
            Double  syMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/estimateOneTotal)*commercialInsuranceOneFee);//医疗费商业险
            if(jqMedicalFee>0){
                caseAssessmentObjReport1.setCommerAmount(jqMedicalFee);
                t_totalYlJq=t_totalYlJq+jqMedicalFee;
                caseAssessmentObjReport1.setCompulAmount(syMedicalFee);
                t_totalYlSy=t_totalYlSy+syMedicalFee;
                this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport1);
            }


            //伙食补助费
            CaseAssessmentObjReport caseAssessmentObjReport2=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_hospitalFoodFeeId);
            Double jqhospitalFoodFee=DecimalUtil.twoDecimalTOFourFromFive((t_hospitalFoodFee/estimateOneTotal)*compulsoryInsuranceOneFee);//伙食补助费交强险
            Double syhospitalFoodFee=DecimalUtil.twoDecimalTOFourFromFive((t_hospitalFoodFee/estimateOneTotal)*commercialInsuranceOneFee);//伙食补助商业险
            if(jqhospitalFoodFee>0){
                caseAssessmentObjReport2.setCommerAmount(jqhospitalFoodFee);
                t_totalYlJq=t_totalYlJq+jqhospitalFoodFee;
                caseAssessmentObjReport2.setCompulAmount(syhospitalFoodFee);
                t_totalYlSy=t_totalYlSy+syhospitalFoodFee;
                this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport2);
            }

            //营养费
            CaseAssessmentObjReport caseAssessmentObjReport3=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_nutritionFeeId);
            Double  jqnutritionFee=DecimalUtil.twoDecimalTOFourFromFive((t_nutritionFee/estimateOneTotal)*compulsoryInsuranceOneFee);//营养费交强险
            Double synutritionFee=DecimalUtil.twoDecimalTOFourFromFive((t_nutritionFee/estimateOneTotal)*commercialInsuranceOneFee);//营养费商业险
            if(jqnutritionFee>0){
                caseAssessmentObjReport3.setCommerAmount(jqnutritionFee);
                t_totalYlJq=t_totalYlJq+jqnutritionFee;
                caseAssessmentObjReport3.setCompulAmount(synutritionFee);
                t_totalYlSy=t_totalYlSy+synutritionFee;
                this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport3);
            }




            //后续治疗费
            CaseAssessmentObjReport caseAssessmentObjReport4=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_againCureFeeId);
            Double jqagainCureFee=DecimalUtil.twoDecimalTOFourFromFive((t_againCureFee/estimateOneTotal)*compulsoryInsuranceOneFee);//后续治疗费交强险
            Double syagainCureFee=DecimalUtil.twoDecimalTOFourFromFive((t_againCureFee/estimateOneTotal)*commercialInsuranceOneFee);//后续治疗费商业险
            if(jqagainCureFee>0){
                caseAssessmentObjReport4.setCommerAmount(jqagainCureFee);
                t_totalYlJq=t_totalYlJq+jqagainCureFee;
                caseAssessmentObjReport4.setCompulAmount(syagainCureFee);
                t_totalYlSy=t_totalYlSy+syagainCureFee;
                this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport4);
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

            //判断精神抚慰金
            if(subtractSpiritComfortFee>=0){
                //护理费
                CaseAssessmentObjReport caseAssessmentObjReport5=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_nursingFeeId);
                Double jqnursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//护理费交强险
                Double  synursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal)*commercialInsuranceTwoFee);//护理费商业险
                if(jqnursingFee>0){
                    caseAssessmentObjReport5.setCommerAmount(jqnursingFee);
                    t_totalPcJq=t_totalPcJq+jqnursingFee;
                    caseAssessmentObjReport5.setCompulAmount(synursingFee);
                    t_totalPcSy=t_totalPcSy+jqnursingFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport5);
                }



                //误工费
                CaseAssessmentObjReport caseAssessmentObjReport6=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_lossWordFeeId);
                Double jqlossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//误工费交强险
                Double  sylossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal)*commercialInsuranceTwoFee);//误工费商业险
                if(jqlossWordFee>0){
                    caseAssessmentObjReport6.setCommerAmount(jqlossWordFee);
                    t_totalPcJq=t_totalPcJq+jqlossWordFee;
                    caseAssessmentObjReport6.setCompulAmount(sylossWordFee);
                    t_totalPcSy=t_totalPcSy+sylossWordFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport6);
                }



                //残疾赔偿金
                CaseAssessmentObjReport caseAssessmentObjReport7=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_invalidismIndemnifyFeeId);
                Double jqinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//残疾赔偿金交强险
                Double  syinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal)*commercialInsuranceTwoFee);//残疾赔偿金商业险
                if(jqinvalidismIndemnifyFee>0){
                    caseAssessmentObjReport7.setCommerAmount(jqinvalidismIndemnifyFee);
                    t_totalPcJq=t_totalPcJq+jqinvalidismIndemnifyFee;
                    caseAssessmentObjReport7.setCompulAmount(syinvalidismIndemnifyFee);
                    t_totalPcSy=t_totalPcSy+syinvalidismIndemnifyFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport7);
                }



                //精神抚慰金
                CaseAssessmentObjReport caseAssessmentObjReport8=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_spiritComfortFeeId);
                Double jqspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive(t_spiritComfortFee);//精神抚慰金交强险

                if(jqspiritComfortFee>0){
                    caseAssessmentObjReport8.setCommerAmount(jqspiritComfortFee);
                    t_totalPcJq=t_totalPcJq+jqspiritComfortFee;
                    caseAssessmentObjReport8.setCompulAmount(0D);
                    t_totalPcSy=t_totalPcSy+0D;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport8);
                }


                //交通费
                CaseAssessmentObjReport caseAssessmentObjReport9=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_trafficFeeId);
                Double jqtrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sytrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqtrafficFee>0){
                    caseAssessmentObjReport9.setCommerAmount(jqtrafficFee);
                    t_totalPcJq=t_totalPcJq+jqtrafficFee;
                    caseAssessmentObjReport9.setCompulAmount(sytrafficFee);
                    t_totalPcSy=t_totalPcSy+sytrafficFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport9);
                }



                //诉讼费
                CaseAssessmentObjReport caseAssessmentObjReport10=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_litigationFeeId);
                Double jqlitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sylitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqlitigationFee>0){
                    caseAssessmentObjReport10.setCommerAmount(jqlitigationFee);
                    t_totalPcJq=t_totalPcJq+jqlitigationFee;
                    caseAssessmentObjReport10.setCompulAmount(sylitigationFee);
                    t_totalPcSy=t_totalPcSy+sylitigationFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport10);
                }



                //残疾器具费
                CaseAssessmentObjReport caseAssessmentObjReport11=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_disabilityEquipmentFeeId);
                Double jqdisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sydisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqdisabilityEquipmentFee>0){
                    caseAssessmentObjReport11.setCommerAmount(jqdisabilityEquipmentFee);
                    t_totalPcJq=t_totalPcJq+jqdisabilityEquipmentFee;
                    caseAssessmentObjReport11.setCompulAmount(sydisabilityEquipmentFee);
                    t_totalPcSy=t_totalPcSy+sydisabilityEquipmentFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport11);
                }



                //鉴定费
                CaseAssessmentObjReport caseAssessmentObjReport12=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_appraisalFeeId);
                Double jqappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  syappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqappraisalFee>0){
                    caseAssessmentObjReport12.setCommerAmount(jqappraisalFee);
                    t_totalPcJq=t_totalPcJq+jqappraisalFee;
                    caseAssessmentObjReport12.setCompulAmount(syappraisalFee);
                    t_totalPcSy=t_totalPcSy+syappraisalFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport12);
                }
            }else{
                //护理费
                CaseAssessmentObjReport caseAssessmentObjReport5=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_nursingFeeId);
                Double jqnursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//护理费交强险
                Double  synursingFee=DecimalUtil.twoDecimalTOFourFromFive((t_nursingFee/estimateTwoTotal)*commercialInsuranceTwoFee);//护理费商业险
                if(jqnursingFee>0){
                    caseAssessmentObjReport5.setCommerAmount(jqnursingFee);
                    t_totalPcJq=t_totalPcJq+jqnursingFee;
                    caseAssessmentObjReport5.setCompulAmount(synursingFee);
                    t_totalPcSy=t_totalPcSy+jqnursingFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport5);
                }



                //误工费
                CaseAssessmentObjReport caseAssessmentObjReport6=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_lossWordFeeId);
                Double jqlossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//误工费交强险
                Double  sylossWordFee=DecimalUtil.twoDecimalTOFourFromFive((t_lossWordFee/estimateTwoTotal)*commercialInsuranceTwoFee);//误工费商业险
                if(jqlossWordFee>0){
                    caseAssessmentObjReport6.setCommerAmount(jqlossWordFee);
                    t_totalPcJq=t_totalPcJq+jqlossWordFee;
                    caseAssessmentObjReport6.setCompulAmount(sylossWordFee);
                    t_totalPcSy=t_totalPcSy+sylossWordFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport6);
                }



                //残疾赔偿金
                CaseAssessmentObjReport caseAssessmentObjReport7=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_invalidismIndemnifyFeeId);
                Double jqinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//残疾赔偿金交强险
                Double  syinvalidismIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_invalidismIndemnifyFee/estimateTwoTotal)*commercialInsuranceTwoFee);//残疾赔偿金商业险
                if(jqinvalidismIndemnifyFee>0){
                    caseAssessmentObjReport7.setCommerAmount(jqinvalidismIndemnifyFee);
                    t_totalPcJq=t_totalPcJq+jqinvalidismIndemnifyFee;
                    caseAssessmentObjReport7.setCompulAmount(syinvalidismIndemnifyFee);
                    t_totalPcSy=t_totalPcSy+syinvalidismIndemnifyFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport7);
                }



                //精神抚慰金
                CaseAssessmentObjReport caseAssessmentObjReport8=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_spiritComfortFeeId);
                Double jqspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive(110000);//精神抚慰金交强险
                Double  syspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/estimateTwoTotal)*commercialInsuranceTwoFee);//精神抚慰金商业险
                if(jqspiritComfortFee>0){
                    caseAssessmentObjReport8.setCommerAmount(jqspiritComfortFee);
                    t_totalPcJq=t_totalPcJq+jqspiritComfortFee;
                    caseAssessmentObjReport8.setCompulAmount(syspiritComfortFee);
                    t_totalPcSy=t_totalPcSy+syspiritComfortFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport8);
                }


                //交通费
                CaseAssessmentObjReport caseAssessmentObjReport9=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_trafficFeeId);
                Double jqtrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sytrafficFee=DecimalUtil.twoDecimalTOFourFromFive((t_trafficFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqtrafficFee>0){
                    caseAssessmentObjReport9.setCommerAmount(jqtrafficFee);
                    t_totalPcJq=t_totalPcJq+jqtrafficFee;
                    caseAssessmentObjReport9.setCompulAmount(sytrafficFee);
                    t_totalPcSy=t_totalPcSy+sytrafficFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport9);
                }



                //诉讼费
                CaseAssessmentObjReport caseAssessmentObjReport10=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_litigationFeeId);
                Double jqlitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sylitigationFee=DecimalUtil.twoDecimalTOFourFromFive((t_litigationFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqlitigationFee>0){
                    caseAssessmentObjReport10.setCommerAmount(jqlitigationFee);
                    t_totalPcJq=t_totalPcJq+jqlitigationFee;
                    caseAssessmentObjReport10.setCompulAmount(sylitigationFee);
                    t_totalPcSy=t_totalPcSy+sylitigationFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport10);
                }



                //残疾器具费
                CaseAssessmentObjReport caseAssessmentObjReport11=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_disabilityEquipmentFeeId);
                Double jqdisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  sydisabilityEquipmentFee=DecimalUtil.twoDecimalTOFourFromFive((t_disabilityEquipmentFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqdisabilityEquipmentFee>0){
                    caseAssessmentObjReport11.setCommerAmount(jqdisabilityEquipmentFee);
                    t_totalPcJq=t_totalPcJq+jqdisabilityEquipmentFee;
                    caseAssessmentObjReport11.setCompulAmount(sydisabilityEquipmentFee);
                    t_totalPcSy=t_totalPcSy+sydisabilityEquipmentFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport11);
                }



                //鉴定费
                CaseAssessmentObjReport caseAssessmentObjReport12=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_appraisalFeeId);
                Double jqappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal) *compulsoryInsuranceTwoFee);//交通费交强险
                Double  syappraisalFee=DecimalUtil.twoDecimalTOFourFromFive((t_appraisalFee/estimateTwoTotal)*commercialInsuranceTwoFee);//交通费商业险
                if(jqappraisalFee>0){
                    caseAssessmentObjReport12.setCommerAmount(jqappraisalFee);
                    t_totalPcJq=t_totalPcJq+jqappraisalFee;
                    caseAssessmentObjReport12.setCompulAmount(syappraisalFee);
                    t_totalPcSy=t_totalPcSy+syappraisalFee;
                    this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport12);
                }
            }






            //*****第三部分
            Double estimateThreeTotal =Double.parseDouble(t_estimateThree.get("estimateThreeTotal").toString());
            Double compulsoryInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("compulsoryInsuranceThreeFee").toString());
            t_totalCwJq=t_totalCwJq+compulsoryInsuranceThreeFee;
            Double commercialInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*blameQuotiety;
            t_totalCwSy=t_totalCwSy+commercialInsuranceThreeFee;
            Double ct_commercialInsuranceThreeFee =Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*(1-blameQuotiety);
            CaseAssessmentObjReport caseAssessmentObjReport13=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_financialLossId);
            if(compulsoryInsuranceThreeFee>0){
                caseAssessmentObjReport13.setCommerAmount(compulsoryInsuranceThreeFee);
                caseAssessmentObjReport13.setCompulAmount(commercialInsuranceThreeFee);
                this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport13);
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
            CaseAssessmentObjReport caseAssessmentObjReport1=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_medicalFeeId);
            Double jqMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/destimateOneTotal) *dcompulsoryInsuranceOneFee);//医疗费交强险
            Double  syMedicalFee=DecimalUtil.twoDecimalTOFourFromFive((t_medicalFee/destimateOneTotal)*dcommercialInsuranceOneFee);//医疗费商业险
            if(jqMedicalFee>0){
                caseAssessmentObjReport1.setCommerAmount(jqMedicalFee);
                t_totalYlJq=t_totalYlJq+jqMedicalFee;
                caseAssessmentObjReport1.setCompulAmount(syMedicalFee);
                t_totalYlSy=t_totalYlSy+syMedicalFee;
                this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport1);
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
            CaseAssessmentObjReport caseAssessmentObjReport2=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_deathIndemnifyFeeId);
            Double jqdeathIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_deathIndemnifyFee/destimateTwoTotal)*dcompulsoryInsuranceTwoFee);//死亡赔偿金交强险
            Double sydeathIndemnifyFee=DecimalUtil.twoDecimalTOFourFromFive((t_deathIndemnifyFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);//死亡赔偿金商业险
            if(jqdeathIndemnifyFee>0){
                caseAssessmentObjReport2.setCommerAmount(jqdeathIndemnifyFee);
                t_totalPcJq=t_totalPcJq+jqdeathIndemnifyFee;
                caseAssessmentObjReport2.setCompulAmount(sydeathIndemnifyFee);
                t_totalPcSy=t_totalPcSy+sydeathIndemnifyFee;
                this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport2);
            }



            //精神抚慰金
            CaseAssessmentObjReport caseAssessmentObjReport3=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_spiritComfortFeeId);
            Double jqspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/destimateTwoTotal)*dcompulsoryInsuranceTwoFee);//精神抚慰金
            Double syspiritComfortFee=DecimalUtil.twoDecimalTOFourFromFive((t_spiritComfortFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);//精神抚慰金
            if(jqspiritComfortFee>0){
                caseAssessmentObjReport3.setCommerAmount(jqspiritComfortFee);
                t_totalPcJq=t_totalPcJq+jqspiritComfortFee;
                caseAssessmentObjReport3.setCompulAmount(syspiritComfortFee);
                t_totalPcJq=t_totalPcJq+syspiritComfortFee;
                this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport3);
            }



            //丧葬费
            CaseAssessmentObjReport caseAssessmentObjReport4=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_funeralFeeId);
            Double jqfuneralFee=DecimalUtil.twoDecimalTOFourFromFive((t_funeralFee/destimateTwoTotal)*dcompulsoryInsuranceTwoFee);//丧葬费交强险
            Double syfuneralFee=DecimalUtil.twoDecimalTOFourFromFive((t_funeralFee/destimateTwoTotal)*dcommercialInsuranceTwoFee);//丧葬费商业险
            if(jqfuneralFee>0){
                caseAssessmentObjReport4.setCommerAmount(jqfuneralFee);
                t_totalPcJq=t_totalPcJq+jqfuneralFee;
                caseAssessmentObjReport4.setCompulAmount(syfuneralFee);
                t_totalPcJq=t_totalPcJq+syfuneralFee;
                this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReport4);
            }


            //*****第三部分
            Double estimateThreeTotal =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("estimateThreeTotal").toString()));
            Double compulsoryInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("compulsoryInsuranceThreeFee").toString()));
            t_totalCwJq=t_totalCwJq+compulsoryInsuranceThreeFee;
            Double commercialInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*blameQuotiety);
            t_totalCwSy=t_totalCwSy+compulsoryInsuranceThreeFee;
            Double ct_commercialInsuranceThreeFee =DecimalUtil.twoDecimalTOFourFromFive(Double.parseDouble(t_estimateThree.get("commercialInsuranceThreeFee").toString())*(1-blameQuotiety));
            CaseAssessmentObjReport caseAssessmentObjReportCw=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_financialLossId);
            caseAssessmentObjReportCw.setCommerAmount(compulsoryInsuranceThreeFee);
            caseAssessmentObjReportCw.setCompulAmount(commercialInsuranceThreeFee);
            this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReportCw);

        }
        CaseAssessmentObjReport caseAssessmentObjReportTotalYl=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_totalYlId);
        caseAssessmentObjReportTotalYl.setCommerAmount(t_totalYlJq);
        caseAssessmentObjReportTotalYl.setCompulAmount(t_totalYlSy);
        this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReportTotalYl);
        CaseAssessmentObjReport caseAssessmentObjReportTotalPc=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_totalPcId);
        caseAssessmentObjReportTotalPc.setCommerAmount(t_totalPcJq);
        caseAssessmentObjReportTotalPc.setCompulAmount(t_totalPcSy);
        this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReportTotalPc);
        CaseAssessmentObjReport caseAssessmentObjReportTotalTotal=this.caseAssessmentObjReportMapper.selectByPrimaryKey(t_totalFeeId);
        t_totalFeeJq=t_totalFeeJq+t_totalYlJq+t_totalPcJq+t_totalCwJq;
        t_totalFeeSy=t_totalFeeSy+t_totalYlSy+t_totalPcSy+t_totalCwSy;
        caseAssessmentObjReportTotalTotal.setCommerAmount(t_totalFeeJq);
        caseAssessmentObjReportTotalTotal.setCompulAmount(t_totalFeeSy);
        this.caseAssessmentObjReportMapper.updateByPrimaryKeySelective(caseAssessmentObjReportTotalTotal);
    }



    @ApiMethod(descript = "获取案件赔偿及保险理赔方案",value = "backend-get-case-assessment-obj-report")
    @Override
    public ApiResponse getCaseAssessmentObjReport(ApiRequest apiReq) {
        Long caseId = apiReq.getLong("caseId");
        String op = apiReq.getString("op");
        List<CaseAssessmentObjReport> caseAssessmentObjReports = caseAssessmentObjReportMapper.selectByCaseId(caseId);
        if (caseAssessmentObjReports == null || caseAssessmentObjReports.size() == 0){
            //初始化方案数据
            CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseId);
            Map<String,Object> mapNames = ReportProjectEnum.ASSESSMENT_TYPE.getMap();//初始化一个Map 与 测算报价的名称对应
            if ("view".equals(op)){
                for (String name : mapNames.keySet()){
                    CaseAssessmentObjReport caseAssessmentObjReport =initDataDetail(caseCenterInfo,mapNames,name);
                    caseAssessmentObjReports.add(caseAssessmentObjReport);
                }
            }else{
                for (String name : mapNames.keySet()){
                    CaseAssessmentObjReport caseAssessmentObjReport =initDataDetail(caseCenterInfo,mapNames,name);
                    caseAssessmentObjReportMapper.insertSelective(caseAssessmentObjReport);
                }
                insuranceCalculation(caseId);
                caseAssessmentObjReports = caseAssessmentObjReportMapper.selectByCaseId(caseCenterInfo.getId());
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,caseAssessmentObjReports);
    }


    public void  toPDF(CaseAssessmentReport caseAssessmentReport){
        Map<String,Object> map = new HashMap<String,Object>();
        List<CaseAssessmentObjReport> caseAssessmentObjReports = caseAssessmentObjReportMapper.selectByCaseId(caseAssessmentReport.getCaseId());
        map.put("caseAssessmentReport",caseAssessmentReport);
        map.put("caseAssessmentObjReports",caseAssessmentObjReports);
        File file = PDFUtil.createCaseAssessmentReportPDF(reportPathUrl,caseAssessmentReport.getCaseNo(),map);
        String organizeImgFilePath = reportPathUrl + "20180313100947.png";
        String companyName = "江苏乐凡保险公估有限公司";
        String organCode = "91320105302691898P";
        String companyAddress = "南京市建邺区奥体大街118号01幢1112室";
        String agentName = "高长青";
        String agentIdNo = "321102197312221917";
        RiskReportSign.doSignWithImageSealByStream(file.getPath(), file.getParent(), file.getName(), organizeImgFilePath, companyName, organCode, companyAddress, agentName, agentIdNo);
        //查询附件表是否已存在,根据文件路径查询路径是否存在. 若已存在则删除,然后重新保存
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

        //查询主表(CASE_CENTER_INFO) 的 id
        CaseCenterInfo caseCenterInfo = caseCenterInfoMapper.selectByPrimaryKey(caseAssessmentReport.getCaseId());
        CaseFileMid caseFileMid = new CaseFileMid();
        caseFileMid.setCaseId(caseCenterInfo.getId());
        caseFileMid.setFileId(commonFile.getId());
        caseFileMid.setCommonFile(commonFile);
        caseFileMid.setCatalogId(2L);
        caseFileMid.setCatalogName("其他材料");
        caseFileMid.setCaseNo(caseCenterInfo.getCaseNo());
        caseFileMidMapper.insert(caseFileMid);
    }

    @ApiMethod(descript = "生成公估报告PDF(已不用)",value = "backend-create-case-assessment-report-pdf")
    @Override
    public ApiResponse createCaseAssessmentReportPDF(ApiRequest apiReq) {
        Long id = apiReq.getLong("id");
        Long reportId = apiReq.getLong("reportId");
        String pdfName = apiReq.getString("pdfName");
        Long caseId = apiReq.getLong("caseId");
        String operType = apiReq.getString("operType");
        Map<String,Object> map = new HashMap<String,Object>();
        //保存公估报告
        if ("saveAssessmentReport".equals(operType)){
            if (StringUtils.isEmpty(id)){
                CaseAssessmentReport caseAssessmentReport = ConvertToBeanUtil.toBeanFromApiRequest(apiReq,CaseAssessmentReport.class);
                map.put("caseAssessmentReport",caseAssessmentReport);
            }else {
                CaseAssessmentReport caseAssessmentReport = caseAssessmentReportMapper.selectByPrimaryKey(id);
                map.put("caseAssessmentReport",caseAssessmentReport);
            }
        }else if("updCaseAssessmentObjReport".equals(operType)){//保存公估赔偿方案
            if (StringUtils.isEmpty(reportId)){
                CaseAssessmentReport caseAssessmentReport = ConvertToBeanUtil.toBeanFromApiRequest(apiReq, CaseAssessmentReport.class);
                map.put("caseAssessmentReport",caseAssessmentReport);
            }else{
                CaseAssessmentReport caseAssessmentReport = caseAssessmentReportMapper.selectByPrimaryKey(reportId);
                map.put("caseAssessmentReport",caseAssessmentReport);
            }
        }
        List<CaseAssessmentObjReport> caseAssessmentObjReports = caseAssessmentObjReportMapper.selectByCaseId(caseId);
        map.put("caseAssessmentObjReports",caseAssessmentObjReports);
        File file = PDFUtil.createCaseAssessmentReportPDF(reportPathUrl,pdfName,map);


//        organize_img_file_path_assessment=F:/data/filepath/20180308155641.png
//        organize_img_file_path_risk=F:/data/filepath/20180312141714.png
//        company_name=上海乐凡金融信息服务有限公司
//        organ_code=913101080677536805
//        company_address=共和新路340号
//        agent_name=艾利
//        agent_id_no=220301198705170035
        String organizeImgFilePath = reportPathUrl + "20180313100947.png";
        String companyName = "江苏乐凡保险公估有限公司";
        String organCode = "91320105302691898P";
        String companyAddress = "南京市建邺区奥体大街118号01幢1112室";
        String agentName = "高长青";
        String agentIdNo = "321102197312221917";
        RiskReportSign.doSignWithImageSealByStream(file.getPath(), file.getParent(), file.getName(), organizeImgFilePath, companyName, organCode, companyAddress, agentName, agentIdNo);


        //查询附件表是否已存在,根据文件路径查询路径是否存在. 若已存在则删除,然后重新保存
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

        //查询主表(CASE_CENTER_INFO) 的 id
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
     * 初始化主表数据(公估报告)
     * @param caseAssessmentReport  公估报告
     * @param caseCenterInfo         案件中心信息
     * @return
     */
    private CaseAssessmentReport initData(CaseAssessmentReport caseAssessmentReport,CaseCenterInfo caseCenterInfo){
        try {
            Map<String,Object> caseCenterInfoMap = new HashMap<>();
            caseCenterInfoMap.put("caseNo",caseCenterInfo.getCaseNo());
            PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectPEAByCaseCenterInfoNew(caseCenterInfoMap);
            PaymentEstimateInquiryDto paymentEstimateInquiryDto = caseCenterInfoMapper.selectNewestPayInquiryNew(caseCenterInfo.getId());
            caseAssessmentReport.setInjuredName(caseCenterInfo.getCaseName());
            caseAssessmentReport.setInjuredAgentName(caseCenterInfo.getCaseName());
            caseAssessmentReport.setInjuredAgentTel(caseCenterInfo.getCaseTel());
            if (paymentEstimateApply != null){
                caseAssessmentReport.setAccidentTime(paymentEstimateApply.getAccidentDate());
                caseAssessmentReport.setAccidentAddress(paymentEstimateApply.getAccidentProvince() + paymentEstimateApply.getAccidentCity() + paymentEstimateApply.getAccidentDistrict() + paymentEstimateApply.getAccidentAddress());
                switch (paymentEstimateApply.getMyAccidentLiability() == null ? -1 : paymentEstimateApply.getMyAccidentLiability())
                {
                    case 1:
                        caseAssessmentReport.setInsurantResponsibility("无");//被保险人承担责任
                        caseAssessmentReport.setInjuredResponsibility("全部");//伤者承担责任
                        break;
                    case 2:
                        caseAssessmentReport.setInsurantResponsibility("次要");//被保险人承担责任
                        caseAssessmentReport.setInjuredResponsibility("主要");//伤者承担责任
                        break;
                    case 3:
                        caseAssessmentReport.setInsurantResponsibility("同等");//被保险人承担责任
                        caseAssessmentReport.setInjuredResponsibility("同等");//伤者承担责任
                        break;
                    case 4:
                        caseAssessmentReport.setInsurantResponsibility("主要");//被保险人承担责任
                        caseAssessmentReport.setInjuredResponsibility("次要");//伤者承担责任
                        break;
                    case 5:
                        caseAssessmentReport.setInsurantResponsibility("全部");//被保险人承担责任
                        caseAssessmentReport.setInjuredResponsibility("无");//伤者承担责任
                        break;
                    case 6:
                        caseAssessmentReport.setInsurantResponsibility("无法认定");//被保险人承担责任
                        caseAssessmentReport.setInjuredResponsibility("无法认定");//伤者承担责任
                        break;
                    default:
                        caseAssessmentReport.setInsurantResponsibility(null);//被保险人承担责任
                        caseAssessmentReport.setInjuredResponsibility(null);//伤者承担责任
                }
                caseAssessmentReport.setMedicalFee(paymentEstimateApply.getMedicalFee());
                caseAssessmentReport.setIsAgainOperation(paymentEstimateApply.getIsAgainOperation());
                caseAssessmentReport.setCompensationStandard(paymentEstimateApply.getIrrType());
                caseAssessmentReport.setDisabilityGrade(paymentEstimateApply.getInvalidismGrade());
            }
            if (paymentEstimateInquiryDto != null){
                caseAssessmentReport.setStillMedicalFee(paymentEstimateInquiryDto.getStillNeedFee());
            }
        }catch (Exception e){
            e.printStackTrace();//打印出报错信息，但初始化数据还需返回已初始化的数据
        }
        return caseAssessmentReport;
    }

    /**
     *初始化详情数据(案件赔偿及保险理赔方案)
     * @param caseCenterInfo 案件中心信息
     * @Param map              测算项目名称与公估报告项目名称对应
     * @param projectName     项目名称
     * @return
     */
    private CaseAssessmentObjReport initDataDetail(CaseCenterInfo caseCenterInfo,Map mapNames,String projectName){
        CaseAssessmentObjReport caseAssessmentObjReport = new CaseAssessmentObjReport();
        caseAssessmentObjReport.setCaseId(caseCenterInfo.getId());
        caseAssessmentObjReport.setCaseNo(caseCenterInfo.getCaseNo());
        caseAssessmentObjReport.setProjectName(projectName);
        caseAssessmentObjReport.setCheckBasis(null);
        caseAssessmentObjReport.setCommerAmount(0D);
        caseAssessmentObjReport.setCompulAmount(0D);
        caseAssessmentObjReport.setCheckAmount(0D);
        Double amount =  0D;
        //初始化公估报告的核损金额
        Map<String,Object> caseCenterInfoMap = new HashMap<>();
        caseCenterInfoMap.put("caseNo",caseCenterInfo.getCaseNo());
        PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectPEAByCaseCenterInfoNew(caseCenterInfoMap);
        if (paymentEstimateApply != null){
            Map<String,Object> map = new HashMap<>();
            map.put("paymentEstimateId",paymentEstimateApply.getId());
            List<PaymentEstimateReport> reports = paymentEstimateReportMapper.selectPaymentEstimateReportByPeId(map);
            for (PaymentEstimateReport report : reports){
                if ("医疗小计".equals(projectName)){
                    List itemsDoctor = Arrays.asList("医疗费","住院伙食补助","营养费","后续治疗费");
                    for (Object item : itemsDoctor){
                        if (report.getPaymentProject().equals(mapNames.get(item))){
                            amount += (report.getCheckMedicalFee() == null ? (report.getMedicalFee() == null ? 0 : report.getMedicalFee()) : report.getCheckMedicalFee());
                        }
                    }
                    continue;
                }
                if ("赔款小计".equals(projectName)){
                    List itemsPay = Arrays.asList("误工费","护理费","交通费","残疾赔偿金","伤残辅助用具","丧葬费","死亡赔偿金","被抚养人生活费","精神抚慰金");
                    for (Object item : itemsPay){
                        if (report.getPaymentProject().equals(mapNames.get(item))){
                            amount += (report.getCheckMedicalFee() == null ? (report.getMedicalFee() == null ? 0 : report.getMedicalFee()) : report.getCheckMedicalFee());

                        }
                    }
                    continue;
                }
//                if ("合计".equals(projectName) && mapNames.values().contains(report.getPaymentProject())){
//                    amount += report.getCheckMedicalFee();
//                    continue;
//                }
                //如果存在对应关系,则初始化金额数据  跳出循环
                if (report.getPaymentProject().equals(mapNames.get(projectName))){
                    amount = (report.getCheckMedicalFee() == null ? (report.getMedicalFee() == null ? 0 : report.getMedicalFee()) : report.getCheckMedicalFee());
                    break;
                }
            }
        }
        caseAssessmentObjReport.setCheckAmount(DecimalUtil.twoDecimalTOFourFromFive(amount));
        return caseAssessmentObjReport;
    }

    /**
     * 计算合计 小计, 便于代码整洁
     * @param caseId
     * @param projectName
     * @return
     */
    private Map returnMap(Long caseId,String projectName){
        Map map = new HashMap();
        map.put("caseId",caseId);
        map.put("projectName",projectName);
        return map;
    }
}
