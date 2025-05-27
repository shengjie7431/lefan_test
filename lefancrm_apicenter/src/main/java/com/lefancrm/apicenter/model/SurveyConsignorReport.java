package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyConsignorReport {
    private Long id;

    private Date reportDate;

    private String reportDateStr;

    private Long consignorOrgId;

    private String consignorOrgName;

    private Integer newSurveyCaseNum = 0;

    private Integer tbNewSurveyCaseNum;

    private Double tbNewSurveyCaseNumRate;

    private Integer hbNewSurveyCaseNum;

    private Double hbNewSurveyCaseNumRate;

    private Integer momNewSurveyCaseNum = 0;

    private Double newSurveyCaseNumRate = 0D;

    private Integer newCheckNum = 0;

    private Double surveyMoney = 0D;

    private Double surveyBilling = 0D;

    private Double surveyAccount = 0D;

    private Double average = 0D;

    private Double positiveRate = 0D;

    private Double regional = 0D;

    private Double efficiency = 0D;

    private Double lossEfficiencyRate = 0D;

    private int directionNum;

    private int sunNum;

    private int lossNum;

    private Double directionAvgMoney;

    private int returnType;//报表用  1 当前数据   2总数居   3最大值

    private int orgCommitNum;

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

    public Integer getNewSurveyCaseNum() {
        return newSurveyCaseNum;
    }

    public void setNewSurveyCaseNum(Integer newSurveyCaseNum) {
        this.newSurveyCaseNum = newSurveyCaseNum;
    }

    public Integer getNewCheckNum() {
        return newCheckNum;
    }

    public void setNewCheckNum(Integer newCheckNum) {
        this.newCheckNum = newCheckNum;
    }

    public Double getSurveyMoney() {
        return surveyMoney;
    }

    public void setSurveyMoney(Double surveyMoney) {
        this.surveyMoney = surveyMoney;
    }

    public Double getSurveyBilling() {
        return surveyBilling;
    }

    public void setSurveyBilling(Double surveyBilling) {
        this.surveyBilling = surveyBilling;
    }

    public Double getSurveyAccount() {
        return surveyAccount;
    }

    public void setSurveyAccount(Double surveyAccount) {
        this.surveyAccount = surveyAccount;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(Double positiveRate) {
        this.positiveRate = positiveRate;
    }

    public Double getRegional() {
        return regional;
    }

    public void setRegional(Double regional) {
        this.regional = regional;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public Double getLossEfficiencyRate() {
        return lossEfficiencyRate;
    }

    public void setLossEfficiencyRate(Double lossEfficiencyRate) {
        this.lossEfficiencyRate = lossEfficiencyRate;
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

    public int getDirectionNum() {
        return directionNum;
    }

    public void setDirectionNum(int directionNum) {
        this.directionNum = directionNum;
    }

    public Double getDirectionAvgMoney() {
        return directionAvgMoney;
    }

    public void setDirectionAvgMoney(Double directionAvgMoney) {
        this.directionAvgMoney = directionAvgMoney;
    }

    public int getSunNum() {
        return sunNum;
    }

    public void setSunNum(int sunNum) {
        this.sunNum = sunNum;
    }

    public int getLossNum() {
        return lossNum;
    }

    public void setLossNum(int lossNum) {
        this.lossNum = lossNum;
    }

    public Integer getMomNewSurveyCaseNum() {
        return momNewSurveyCaseNum;
    }

    public void setMomNewSurveyCaseNum(Integer momNewSurveyCaseNum) {
        this.momNewSurveyCaseNum = momNewSurveyCaseNum;
    }

    public Double getNewSurveyCaseNumRate() {
        return newSurveyCaseNumRate;
    }

    public void setNewSurveyCaseNumRate(Double newSurveyCaseNumRate) {
        this.newSurveyCaseNumRate = newSurveyCaseNumRate;
    }

    public Integer getTbNewSurveyCaseNum() {
        return tbNewSurveyCaseNum;
    }

    public void setTbNewSurveyCaseNum(Integer tbNewSurveyCaseNum) {
        this.tbNewSurveyCaseNum = tbNewSurveyCaseNum;
    }

    public Double getTbNewSurveyCaseNumRate() {
        return tbNewSurveyCaseNumRate;
    }

    public void setTbNewSurveyCaseNumRate(Double tbNewSurveyCaseNumRate) {
        this.tbNewSurveyCaseNumRate = tbNewSurveyCaseNumRate;
    }

    public Integer getHbNewSurveyCaseNum() {
        return hbNewSurveyCaseNum;
    }

    public void setHbNewSurveyCaseNum(Integer hbNewSurveyCaseNum) {
        this.hbNewSurveyCaseNum = hbNewSurveyCaseNum;
    }

    public Double getHbNewSurveyCaseNumRate() {
        return hbNewSurveyCaseNumRate;
    }

    public void setHbNewSurveyCaseNumRate(Double hbNewSurveyCaseNumRate) {
        this.hbNewSurveyCaseNumRate = hbNewSurveyCaseNumRate;
    }

    public int getOrgCommitNum() {
        return orgCommitNum;
    }

    public void setOrgCommitNum(int orgCommitNum) {
        this.orgCommitNum = orgCommitNum;
    }
}