package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyZhaAssess {
    private Long id;

    private String listName;

    private Integer caseNum;

    private Double derogationMoney;

    private Integer sunCaseNum;

    private Double caeTotalEff;

    private Double caeAvgEff;

    private Long operateId;

    private String operateName;

    private Date oparateTime;

    private Integer deleteFlag;

    private Double entrustSubmitMoney;

    private Double surveySubmitMoney;

    private Double rate;

    private Double tcb;

    private Double pfAmt;

    private Double jsAmt;

    private String pfAmtStr;

    private String jsAmtStr;


    private Double sunRate;//阳性率

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Integer getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(Integer caseNum) {
        this.caseNum = caseNum;
    }

    public Double getDerogationMoney() {
        return derogationMoney;
    }

    public void setDerogationMoney(Double derogationMoney) {
        this.derogationMoney = derogationMoney;
    }

    public Integer getSunCaseNum() {
        return sunCaseNum;
    }

    public void setSunCaseNum(Integer sunCaseNum) {
        this.sunCaseNum = sunCaseNum;
    }

    public Double getCaeTotalEff() {
        return caeTotalEff;
    }

    public void setCaeTotalEff(Double caeTotalEff) {
        this.caeTotalEff = caeTotalEff;
    }

    public Double getCaeAvgEff() {
        return caeAvgEff;
    }

    public void setCaeAvgEff(Double caeAvgEff) {
        this.caeAvgEff = caeAvgEff;
    }

    public Long getOperateId() {
        return operateId;
    }

    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public Date getOparateTime() {
        return oparateTime;
    }

    public void setOparateTime(Date oparateTime) {
        this.oparateTime = oparateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Double getEntrustSubmitMoney() {
        return entrustSubmitMoney;
    }

    public void setEntrustSubmitMoney(Double entrustSubmitMoney) {
        this.entrustSubmitMoney = entrustSubmitMoney;
    }

    public Double getSurveySubmitMoney() {
        return surveySubmitMoney;
    }

    public void setSurveySubmitMoney(Double surveySubmitMoney) {
        this.surveySubmitMoney = surveySubmitMoney;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getTcb() {
        return tcb;
    }

    public void setTcb(Double tcb) {
        this.tcb = tcb;
    }

    public Double getPfAmt() {
        return pfAmt;
    }

    public void setPfAmt(Double pfAmt) {
        this.pfAmt = pfAmt;
    }

    public Double getJsAmt() {
        return jsAmt;
    }

    public void setJsAmt(Double jsAmt) {
        this.jsAmt = jsAmt;
    }

    public Double getSunRate() {
        return sunRate;
    }

    public void setSunRate(Double sunRate) {
        this.sunRate = sunRate;
    }

    public String getPfAmtStr() {
        return pfAmtStr;
    }

    public void setPfAmtStr(String pfAmtStr) {
        this.pfAmtStr = pfAmtStr;
    }

    public String getJsAmtStr() {
        return jsAmtStr;
    }

    public void setJsAmtStr(String jsAmtStr) {
        this.jsAmtStr = jsAmtStr;
    }
}