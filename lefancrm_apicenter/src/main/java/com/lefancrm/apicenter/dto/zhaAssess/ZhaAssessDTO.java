package com.lefancrm.apicenter.dto.zhaAssess;

import java.util.Date;

public class ZhaAssessDTO {
    private String claimNo;
    private Long entrustOrgId;
    private String surveyCaseNo;
    private Double entrustSubmitMoney;
    private String surveyPerson;
    private Long orgId;
    private String orgName;
    private Date reportDate;
    private Long surveyInfoId;
    private Long surveyInfoIdSun;
    private Integer num;
    private Double surveySubmitMoney;

    private Integer yb;//有无医保  1有  0无

    public String getClaimNo() {
        return claimNo;
    }

    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }

    public Long getEntrustOrgId() {
        return entrustOrgId;
    }

    public void setEntrustOrgId(Long entrustOrgId) {
        this.entrustOrgId = entrustOrgId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Long getSurveyInfoIdSun() {
        return surveyInfoIdSun;
    }

    public void setSurveyInfoIdSun(Long surveyInfoIdSun) {
        this.surveyInfoIdSun = surveyInfoIdSun;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }

    public Double getEntrustSubmitMoney() {
        return entrustSubmitMoney;
    }

    public void setEntrustSubmitMoney(Double entrustSubmitMoney) {
        this.entrustSubmitMoney = entrustSubmitMoney;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Double getSurveySubmitMoney() {
        return surveySubmitMoney;
    }

    public void setSurveySubmitMoney(Double surveySubmitMoney) {
        this.surveySubmitMoney = surveySubmitMoney;
    }

    public Integer getYb() {
        return yb;
    }

    public void setYb(Integer yb) {
        this.yb = yb;
    }
}
