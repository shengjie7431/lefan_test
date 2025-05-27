package com.lefancrm.apicenter.dto;

public class SurveyZhaOrgAssessUpdateDTO {
    private Long id;
    private Long surveyOrgId;

    private String surveyOrgName;

    private String surveyNo;

    private Long surveyInfoId;

    private String surveyPerson;

    private Double surveySubmitMoney;

    private Integer isSun;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public Double getSurveySubmitMoney() {
        return surveySubmitMoney;
    }

    public void setSurveySubmitMoney(Double surveySubmitMoney) {
        this.surveySubmitMoney = surveySubmitMoney;
    }

    public Integer getIsSun() {
        return isSun;
    }

    public void setIsSun(Integer isSun) {
        this.isSun = isSun;
    }
}
