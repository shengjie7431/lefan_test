package com.lefancrm.apicenter.dto;

public class SurveyCaseXHB {
    private Long surveyInfoId;
    private String claimsNo;
    private String surveyPerson;
    private String statusName;
    private Double settlementMoney;

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public String getClaimsNo() {
        return claimsNo;
    }

    public void setClaimsNo(String claimsNo) {
        this.claimsNo = claimsNo;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Double getSettlementMoney() {
        return settlementMoney;
    }

    public void setSettlementMoney(Double settlementMoney) {
        this.settlementMoney = settlementMoney;
    }
}
