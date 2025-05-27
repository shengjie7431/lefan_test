package com.lefancrm.apicenter.model;

public class InvestigatorReDetails {
    private Long id;

    private Long surveyInfoId;

    private Long investigatorReId;

    private Long investigatorCaseId;

    private Double investigatorReMoney;

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