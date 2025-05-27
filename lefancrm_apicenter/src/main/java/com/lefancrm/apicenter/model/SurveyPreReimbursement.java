package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

public class SurveyPreReimbursement {
    private Long id;

    private Long surveyUserId;

    private String surveyUserName;

    private Long surveyOrgId;

    private Double fileMoney;

    private Double trafficMoney;

    private Double totalMoney;

    private Integer state;

    private Date payDate;

    private Double payMoney;

    private Double nightMoney;

    private Double mileageMoney;

    private String orgName;

    private String returnText;

    private Integer caseCount;
    private Double avgMoney;
    private Double helpMoney;
    private Double insMoney;
    private Date createTime;
    private String clockIds;

    private Double cityinDrivingMoney;
    private Double medicalHistoryMoney;
    private Double troubleshootingMoney;
    private Double opcTroubleshootingMoney;
    private Double printingMoney;
    private Double accommodatioMoney;
    private Double crossDrivingMoney;
    private Double selfDrivingMoney;
    private Double otherMoney;
    private Double huanbiMoney;//件均环比上月
    private Double orgAvgMoney;//机构件均

    public Double getCityinDrivingMoney() {
        return cityinDrivingMoney;
    }

    public void setCityinDrivingMoney(Double cityinDrivingMoney) {
        this.cityinDrivingMoney = cityinDrivingMoney;
    }

    public Double getMedicalHistoryMoney() {
        return medicalHistoryMoney;
    }

    public void setMedicalHistoryMoney(Double medicalHistoryMoney) {
        this.medicalHistoryMoney = medicalHistoryMoney;
    }

    public Double getTroubleshootingMoney() {
        return troubleshootingMoney;
    }

    public void setTroubleshootingMoney(Double troubleshootingMoney) {
        this.troubleshootingMoney = troubleshootingMoney;
    }

    public Double getOpcTroubleshootingMoney() {
        return opcTroubleshootingMoney;
    }

    public void setOpcTroubleshootingMoney(Double opcTroubleshootingMoney) {
        this.opcTroubleshootingMoney = opcTroubleshootingMoney;
    }

    public Double getPrintingMoney() {
        return printingMoney;
    }

    public void setPrintingMoney(Double printingMoney) {
        this.printingMoney = printingMoney;
    }

    public Double getAccommodatioMoney() {
        return accommodatioMoney;
    }

    public void setAccommodatioMoney(Double accommodatioMoney) {
        this.accommodatioMoney = accommodatioMoney;
    }

    public Double getCrossDrivingMoney() {
        return crossDrivingMoney;
    }

    public void setCrossDrivingMoney(Double crossDrivingMoney) {
        this.crossDrivingMoney = crossDrivingMoney;
    }

    public Double getSelfDrivingMoney() {
        return selfDrivingMoney;
    }

    public void setSelfDrivingMoney(Double selfDrivingMoney) {
        this.selfDrivingMoney = selfDrivingMoney;
    }

    public Double getOtherMoney() {
        return otherMoney;
    }

    public void setOtherMoney(Double otherMoney) {
        this.otherMoney = otherMoney;
    }

    public Double getHuanbiMoney() {
        return huanbiMoney;
    }

    public void setHuanbiMoney(Double huanbiMoney) {
        this.huanbiMoney = huanbiMoney;
    }

    public Double getOrgAvgMoney() {
        return orgAvgMoney;
    }

    public void setOrgAvgMoney(Double orgAvgMoney) {
        this.orgAvgMoney = orgAvgMoney;
    }

    public String getClockIds() {
        return clockIds;
    }

    public void setClockIds(String clockIds) {
        this.clockIds = clockIds;
    }

    public Double getHelpMoney() {
        return helpMoney;
    }

    public void setHelpMoney(Double helpMoney) {
        this.helpMoney = helpMoney;
    }

    public Double getInsMoney() {
        return insMoney;
    }

    public void setInsMoney(Double insMoney) {
        this.insMoney = insMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getAvgMoney() {
        return avgMoney;
    }

    public void setAvgMoney(Double avgMoney) {
        this.avgMoney = avgMoney;
    }

    public Integer getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(Integer caseCount) {
        this.caseCount = caseCount;
    }

    List<InvestigatorPreDetails> investigatorPreDetailsDtos;

    public Long getId() {
        return id;
    }

    public List<InvestigatorPreDetails> getInvestigatorPreDetailsDtos() {
        return investigatorPreDetailsDtos;
    }

    public void setInvestigatorPreDetailsDtos(List<InvestigatorPreDetails> investigatorPreDetailsDtos) {
        this.investigatorPreDetailsDtos = investigatorPreDetailsDtos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyUserId() {
        return surveyUserId;
    }

    public void setSurveyUserId(Long surveyUserId) {
        this.surveyUserId = surveyUserId;
    }

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
    }

    public Double getFileMoney() {
        return fileMoney;
    }

    public void setFileMoney(Double fileMoney) {
        this.fileMoney = fileMoney;
    }

    public Double getTrafficMoney() {
        return trafficMoney;
    }

    public void setTrafficMoney(Double trafficMoney) {
        this.trafficMoney = trafficMoney;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Double getNightMoney() {
        return nightMoney;
    }

    public void setNightMoney(Double nightMoney) {
        this.nightMoney = nightMoney;
    }

    public Double getMileageMoney() {
        return mileageMoney;
    }

    public void setMileageMoney(Double mileageMoney) {
        this.mileageMoney = mileageMoney;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getReturnText() {
        return returnText;
    }

    public void setReturnText(String returnText) {
        this.returnText = returnText;
    }

    public Long getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Long surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
    }
}