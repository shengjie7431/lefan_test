package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.fina.model.FinaHospitalAccount;
import com.lefancrm.apicenter.fina.model.FinaHospitalInfo;
import com.lefancrm.apicenter.model.*;

import java.util.List;

public class SurveyPayInfoDTO extends SurveyPayInfo {
    private String startTimeStr;
    private String endTimeStr;
    private String payUserTel;
    private int surveyCaseNum;
    private SurveyFranchisee surveyFranchisee;
    private int investigatorCount;
    private SurveyInvestigatorDto surveyInvestigatorDto;
    private List<String> images;
    private String reCreateBy;//报销单创建人
    private FinaHospitalAccount finaHospitalAccount;//医院信息
    private FinancialReApply financialReApply;// 每刻报销申请表

    private SurveyInvestigatorReInfo reInfo;
    private List<FinancialCostDetails> costDetails;
    private List<FinancialCostBear> costBears;

    public SurveyFranchisee getSurveyFranchisee() {
        return surveyFranchisee;
    }

    public void setSurveyFranchisee(SurveyFranchisee surveyFranchisee) {
        this.surveyFranchisee = surveyFranchisee;
    }

    public int getSurveyCaseNum() {
        return surveyCaseNum;
    }

    public void setSurveyCaseNum(int surveyCaseNum) {
        this.surveyCaseNum = surveyCaseNum;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getPayUserTel() {
        return payUserTel;
    }

    public void setPayUserTel(String payUserTel) {
        this.payUserTel = payUserTel;
    }

    public int getInvestigatorCount() {
        return investigatorCount;
    }

    public void setInvestigatorCount(int investigatorCount) {
        this.investigatorCount = investigatorCount;
    }

    public SurveyInvestigatorDto getSurveyInvestigatorDto() {
        return surveyInvestigatorDto;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setSurveyInvestigatorDto(SurveyInvestigatorDto surveyInvestigatorDto) {
        this.surveyInvestigatorDto = surveyInvestigatorDto;
    }

    public String getReCreateBy() {
        return reCreateBy;
    }

    public void setReCreateBy(String reCreateBy) {
        this.reCreateBy = reCreateBy;
    }

    public FinaHospitalAccount getFinaHospitalAccount() {
        return finaHospitalAccount;
    }

    public void setFinaHospitalAccount(FinaHospitalAccount finaHospitalAccount) {
        this.finaHospitalAccount = finaHospitalAccount;
    }

    public FinancialReApply getFinancialReApply() {
        return financialReApply;
    }

    public void setFinancialReApply(FinancialReApply financialReApply) {
        this.financialReApply = financialReApply;
    }

    public SurveyInvestigatorReInfo getReInfo() {
        return reInfo;
    }

    public void setReInfo(SurveyInvestigatorReInfo reInfo) {
        this.reInfo = reInfo;
    }

    public List<FinancialCostDetails> getCostDetails() {
        return costDetails;
    }

    public void setCostDetails(List<FinancialCostDetails> costDetails) {
        this.costDetails = costDetails;
    }

    public List<FinancialCostBear> getCostBears() {
        return costBears;
    }

    public void setCostBears(List<FinancialCostBear> costBears) {
        this.costBears = costBears;
    }
}
