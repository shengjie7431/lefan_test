package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyInvestigatorOrgReport {
    private Long id;

    private Date reportDate;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Integer riskCaseNum = 0;

    private Integer hbCaseNum;
    private Double hbCaseNumRate;

    private Integer tbCaseNum;
    private Double tbCaseNumRate;

    private Double entrustOrgMoney;

    private Double score = 0D;

    private Double efficiency = 0D;

    private Integer sunNum;

    private Double positiveRate = 0D;

    private Integer returnNum;

    private Double returnRate = 0D;

    private Integer lossNum;

    private Double lossEfficiencyRate = 0D;

    private Integer insuranceCheckCaseNum = 0;

    private Integer riskCheckCaseNum = 0;

    private Long consignorOrgId;

    private String consignorOrgName;

    private Double investigationMoney = 0D;

    private Integer surveyOrgType;

    private Integer checkType; //审核类别（1.风控审核，2.保司审核）

    private String reportDateStr;

    private int returnType;//报表用  1 当前数据   2总数居   3最大值

    private Integer newSend = 0;//新增委派任务数

    //相对超期 绝对超期
    private Integer utterOver;
    private Double utterOverRate;
    private Integer opposeOver;
    private Double opposeOverRate;

    //委托方价格
    private Double entrustAgreementMoney;

    private Integer directionNum;

    private Integer markErrorNum;

    private Double markErrorRate;

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

    public Integer getNewSend() {
        return newSend;
    }

    public void setNewSend(Integer newSend) {
        this.newSend = newSend;
    }

    public Integer getUtterOver() {
        return utterOver;
    }

    public void setUtterOver(Integer utterOver) {
        this.utterOver = utterOver;
    }

    public Double getUtterOverRate() {
        return utterOverRate;
    }

    public void setUtterOverRate(Double utterOverRate) {
        this.utterOverRate = utterOverRate;
    }

    public Integer getOpposeOver() {
        return opposeOver;
    }

    public void setOpposeOver(Integer opposeOver) {
        this.opposeOver = opposeOver;
    }

    public Double getOpposeOverRate() {
        return opposeOverRate;
    }

    public void setOpposeOverRate(Double opposeOverRate) {
        this.opposeOverRate = opposeOverRate;
    }

    public Integer getSunNum() {
        return sunNum;
    }

    public void setSunNum(Integer sunNum) {
        this.sunNum = sunNum;
    }

    public Integer getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(Integer returnNum) {
        this.returnNum = returnNum;
    }

    public Integer getLossNum() {
        return lossNum;
    }

    public void setLossNum(Integer lossNum) {
        this.lossNum = lossNum;
    }

    public Double getEntrustAgreementMoney() {
        return entrustAgreementMoney;
    }

    public void setEntrustAgreementMoney(Double entrustAgreementMoney) {
        this.entrustAgreementMoney = entrustAgreementMoney;
    }

    public Double getEntrustOrgMoney() {
        return entrustOrgMoney;
    }

    public void setEntrustOrgMoney(Double entrustOrgMoney) {
        this.entrustOrgMoney = entrustOrgMoney;
    }

    public Integer getDirectionNum() {
        return directionNum;
    }

    public void setDirectionNum(Integer directionNum) {
        this.directionNum = directionNum;
    }

    public Integer getHbCaseNum() {
        return hbCaseNum;
    }

    public void setHbCaseNum(Integer hbCaseNum) {
        this.hbCaseNum = hbCaseNum;
    }

    public Double getHbCaseNumRate() {
        return hbCaseNumRate;
    }

    public void setHbCaseNumRate(Double hbCaseNumRate) {
        this.hbCaseNumRate = hbCaseNumRate;
    }

    public Integer getTbCaseNum() {
        return tbCaseNum;
    }

    public void setTbCaseNum(Integer tbCaseNum) {
        this.tbCaseNum = tbCaseNum;
    }

    public Double getTbCaseNumRate() {
        return tbCaseNumRate;
    }

    public void setTbCaseNumRate(Double tbCaseNumRate) {
        this.tbCaseNumRate = tbCaseNumRate;
    }

    public Integer getMarkErrorNum() {
        return markErrorNum;
    }

    public void setMarkErrorNum(Integer markErrorNum) {
        this.markErrorNum = markErrorNum;
    }

    public Double getMarkErrorRate() {
        return markErrorRate;
    }

    public void setMarkErrorRate(Double markErrorRate) {
        this.markErrorRate = markErrorRate;
    }
}