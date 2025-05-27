package com.lefancrm.backend.dto;

public class SurveyInvestigatorCaseSubDto {
    private Long id;

    private Long surveyInvestigatorCaseId;

    private String staffOpinion;

    private Integer staffOpinionState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyInvestigatorCaseId() {
        return surveyInvestigatorCaseId;
    }

    public void setSurveyInvestigatorCaseId(Long surveyInvestigatorCaseId) {
        this.surveyInvestigatorCaseId = surveyInvestigatorCaseId;
    }

    public String getStaffOpinion() {
        return staffOpinion;
    }

    public void setStaffOpinion(String staffOpinion) {
        this.staffOpinion = staffOpinion;
    }

    public Integer getStaffOpinionState() {
        return staffOpinionState;
    }

    public void setStaffOpinionState(Integer staffOpinionState) {
        this.staffOpinionState = staffOpinionState;
    }
}