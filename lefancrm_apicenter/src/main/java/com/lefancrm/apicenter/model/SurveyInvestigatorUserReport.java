package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyInvestigatorUserReport {
    private Long id;

    private Date reportDate;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Long surveyUserId;

    private String surveyUserName;

    private Integer riskCaseNum =0 ;

    private Double score = 0D;

    private Double efficiency  = 0D;

    private Double positiveRate  = 0D;

    private Double returnRate = 0D;

    private Double lossEfficiencyRate = 0D;

    private Integer insuranceCheckCaseNum= 0;

    private Integer riskCheckCaseNum = 0;

    private Long consignorOrgId;

    private String consignorOrgName;

    private Double investigationMoney = 0D;

    private Integer surveyOrgType;

    private Integer checkType; //审核类别（1.风控审核，2.保司审核）

    private String reportDateStr;

    private int returnType;//报表用  1 当前数据   2总数居   3最大值

    private Integer directionNum = 0;

    private Integer newSend = 0;//新增委派任务数

    private Integer sunNum;

    private Integer lossNum;

    private Integer returnNum;

    private Integer hege;
    private Integer you;
    private Integer cha;

    private Double hegeRate;
    private Double youRate;
    private Double chaRate;

    private Double hegeScore;
    private Double youScore;
    private Double chaScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Long getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Long surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
    }

    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
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

    public Integer getRiskCaseNum() {
        return riskCaseNum;
    }

    public void setRiskCaseNum(Integer riskCaseNum) {
        this.riskCaseNum = riskCaseNum;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public Double getPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(Double positiveRate) {
        this.positiveRate = positiveRate;
    }

    public Double getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(Double returnRate) {
        this.returnRate = returnRate;
    }

    public Double getLossEfficiencyRate() {
        return lossEfficiencyRate;
    }

    public void setLossEfficiencyRate(Double lossEfficiencyRate) {
        this.lossEfficiencyRate = lossEfficiencyRate;
    }

    public Integer getInsuranceCheckCaseNum() {
        return insuranceCheckCaseNum;
    }

    public void setInsuranceCheckCaseNum(Integer insuranceCheckCaseNum) {
        this.insuranceCheckCaseNum = insuranceCheckCaseNum;
    }

    public Integer getRiskCheckCaseNum() {
        return riskCheckCaseNum;
    }

    public void setRiskCheckCaseNum(Integer riskCheckCaseNum) {
        this.riskCheckCaseNum = riskCheckCaseNum;
    }

    public Long getConsignorOrgId() {
        return consignorOrgId;
    }

    public void setConsignorOrgId(Long consignorOrgId) {
        this.consignorOrgId = consignorOrgId;
    }

    public String getConsignorOrgName() {
        return consignorOrgName;
    }

    public void setConsignorOrgName(String consignorOrgName) {
        this.consignorOrgName = consignorOrgName;
    }

    public Double getInvestigationMoney() {
        return investigationMoney;
    }

    public void setInvestigationMoney(Double investigationMoney) {
        this.investigationMoney = investigationMoney;
    }

    public Integer getSurveyOrgType() {
        return surveyOrgType;
    }

    public void setSurveyOrgType(Integer surveyOrgType) {
        this.surveyOrgType = surveyOrgType;
    }

    public String getReportDateStr() {
        return reportDateStr;
    }

    public void setReportDateStr(String reportDateStr) {
        this.reportDateStr = reportDateStr;
    }

    public int getReturnType() {
        return returnType;
    }

    public void setReturnType(int returnType) {
        this.returnType = returnType;
    }

    public Integer getCheckType() {
        return checkType;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    public Integer getDirectionNum() {
        return directionNum;
    }

    public void setDirectionNum(Integer directionNum) {
        this.directionNum = directionNum;
    }

    public Integer getNewSend() {
        return newSend;
    }

    public void setNewSend(Integer newSend) {
        this.newSend = newSend;
    }

    public Integer getSunNum() {
        return sunNum;
    }

    public void setSunNum(Integer sunNum) {
        this.sunNum = sunNum;
    }

    public Integer getLossNum() {
        return lossNum;
    }

    public void setLossNum(Integer lossNum) {
        this.lossNum = lossNum;
    }

    public Integer getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(Integer returnNum) {
        this.returnNum = returnNum;
    }

    public Integer getHege() {
        return hege;
    }

    public void setHege(Integer hege) {
        this.hege = hege;
    }

    public Integer getYou() {
        return you;
    }

    public void setYou(Integer you) {
        this.you = you;
    }

    public Integer getCha() {
        return cha;
    }

    public void setCha(Integer cha) {
        this.cha = cha;
    }

    public Double getHegeRate() {
        return hegeRate;
    }

    public void setHegeRate(Double hegeRate) {
        this.hegeRate = hegeRate;
    }

    public Double getYouRate() {
        return youRate;
    }

    public void setYouRate(Double youRate) {
        this.youRate = youRate;
    }

    public Double getChaRate() {
        return chaRate;
    }

    public void setChaRate(Double chaRate) {
        this.chaRate = chaRate;
    }

    public Double getHegeScore() {
        return hegeScore;
    }

    public void setHegeScore(Double hegeScore) {
        this.hegeScore = hegeScore;
    }

    public Double getYouScore() {
        return youScore;
    }

    public void setYouScore(Double youScore) {
        this.youScore = youScore;
    }

    public Double getChaScore() {
        return chaScore;
    }

    public void setChaScore(Double chaScore) {
        this.chaScore = chaScore;
    }
}