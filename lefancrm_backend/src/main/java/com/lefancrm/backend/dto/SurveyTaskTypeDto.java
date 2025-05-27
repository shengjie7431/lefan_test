package com.lefancrm.backend.dto;

public class SurveyTaskTypeDto {
    private Long id;

    private Long surveyId;

    private Long surveyInfoId;

    private Long taskId;

    private String taskName;

    private String taskColor;

    private Integer selectType; //1、未分配；2、已分配，没有做方向；3已分配，并做了方向

    private SurveyBusinessTaskTypeDto surveyBusinessTaskType;

    private SurveyAssignOrgTypeDto surveyAssignOrgType;

    private SurveyTaskTypeDto surveyTaskType;

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

    public String getTaskColor() {
        return taskColor;
    }

    public void setTaskColor(String taskColor) {
        this.taskColor = taskColor;
    }

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public SurveyBusinessTaskTypeDto getSurveyBusinessTaskType() {
        return surveyBusinessTaskType;
    }

    public void setSurveyBusinessTaskType(SurveyBusinessTaskTypeDto surveyBusinessTaskType) {
        this.surveyBusinessTaskType = surveyBusinessTaskType;
    }

    public SurveyAssignOrgTypeDto getSurveyAssignOrgType() {
        return surveyAssignOrgType;
    }

    public void setSurveyAssignOrgType(SurveyAssignOrgTypeDto surveyAssignOrgType) {
        this.surveyAssignOrgType = surveyAssignOrgType;
    }

    public SurveyTaskTypeDto getSurveyTaskType() {
        return surveyTaskType;
    }

    public void setSurveyTaskType(SurveyTaskTypeDto surveyTaskType) {
        this.surveyTaskType = surveyTaskType;
    }
}