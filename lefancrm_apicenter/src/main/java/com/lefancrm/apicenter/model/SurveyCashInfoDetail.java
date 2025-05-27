package com.lefancrm.apicenter.model;

public class SurveyCashInfoDetail {
    private Long id;

    private Long cashInfoId;

    private Long surveyId;

    private Long surveyInfoId;

    private Long surveyInvestigatorCaseId;

    private String surveyNo;

    private String surveyPerson;

    private String surveyPersonTel;

    private Long surveyUserId;

    private String surveyUserName;

    private Double surveyTaskMoney;

    private Integer deleteFlag;

    private Long surveyInfoRecordId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCashInfoId() {
        return cashInfoId;
    }

    public void setCashInfoId(Long cashInfoId) {
        this.cashInfoId = cashInfoId;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Long getSurveyInvestigatorCaseId() {
        return surveyInvestigatorCaseId;
    }

    public void setSurveyInvestigatorCaseId(Long surveyInvestigatorCaseId) {
        this.surveyInvestigatorCaseId = surveyInvestigatorCaseId;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public String getSurveyPersonTel() {
        return surveyPersonTel;
    }

    public void setSurveyPersonTel(String surveyPersonTel) {
        this.surveyPersonTel = surveyPersonTel;
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

    public Double getSurveyTaskMoney() {
        return surveyTaskMoney;
    }

    public void setSurveyTaskMoney(Double surveyTaskMoney) {
        this.surveyTaskMoney = surveyTaskMoney;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getSurveyInfoRecordId() {
        return surveyInfoRecordId;
    }

    public void setSurveyInfoRecordId(Long surveyInfoRecordId) {
        this.surveyInfoRecordId = surveyInfoRecordId;
    }
}