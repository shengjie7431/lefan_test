package com.lefancrm.backend.dto;

public class InvestigatorPreDetailsDto {
    private Long id;

    private Long surveyInfoId;

    private Long investigatorReId;

    private Long investigatorCaseId;

    private Double investigatorReMoney;

    private String surveyCaseNo;
    private String surveyPerson;
    private String entrustOrgName;
    private String surveyStateName;

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public String getEntrustOrgName() {
        return entrustOrgName;
    }

    public void setEntrustOrgName(String entrustOrgName) {
        this.entrustOrgName = entrustOrgName;
    }

    public String getSurveyStateName() {
        return surveyStateName;
    }

    public void setSurveyStateName(String surveyStateName) {
        this.surveyStateName = surveyStateName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Long getInvestigatorReId() {
        return investigatorReId;
    }

    public void setInvestigatorReId(Long investigatorReId) {
        this.investigatorReId = investigatorReId;
    }

    public Long getInvestigatorCaseId() {
        return investigatorCaseId;
    }

    public void setInvestigatorCaseId(Long investigatorCaseId) {
        this.investigatorCaseId = investigatorCaseId;
    }

    public Double getInvestigatorReMoney() {
        return investigatorReMoney;
    }

    public void setInvestigatorReMoney(Double investigatorReMoney) {
        this.investigatorReMoney = investigatorReMoney;
    }
}