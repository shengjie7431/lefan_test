package com.lefancrm.apicenter.model;

public class SurveyAssignOrgType {
    private Long id;

    private Long surveyId;

    private Long surveyInfoId;

    private Long surveyAssignOrgId;

    private Long taskId;

    private String taskName;

    private Long surveyOrgId;

    private String surveyOrgName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getSurveyAssignOrgId() {
        return surveyAssignOrgId;
    }

    public void setSurveyAssignOrgId(Long surveyAssignOrgId) {
        this.surveyAssignOrgId = surveyAssignOrgId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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
}