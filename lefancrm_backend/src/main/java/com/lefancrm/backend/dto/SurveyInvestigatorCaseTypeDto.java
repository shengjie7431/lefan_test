package com.lefancrm.backend.dto;

public class SurveyInvestigatorCaseTypeDto {
    private Long id;

    private Long taskId;

    private String taskName;

    private Long surveyUserId;

    private String surveyUserName;

    private Long surveyUserCaseId;

    private Integer taskSort;//任务类型的排序

    private String taskColor;//任务类型的颜色

    private String taskRemark;//任务描述

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getSurveyUserCaseId() {
        return surveyUserCaseId;
    }

    public void setSurveyUserCaseId(Long surveyUserCaseId) {
        this.surveyUserCaseId = surveyUserCaseId;
    }

    public Integer getTaskSort() {
        return taskSort;
    }

    public void setTaskSort(Integer taskSort) {
        this.taskSort = taskSort;
    }

    public String getTaskColor() {
        return taskColor;
    }

    public void setTaskColor(String taskColor) {
        this.taskColor = taskColor;
    }

    public String getTaskRemark() {
        return taskRemark;
    }

    public void setTaskRemark(String taskRemark) {
        this.taskRemark = taskRemark;
    }
}