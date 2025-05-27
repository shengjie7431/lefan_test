package com.lefancrm.apicenter.model;

public class SurveyBusinessTaskType {
    private Long id;

    private Long businessTypeId;

    private String businessTypeName;

    private Long taskInfoId;

    private String taskInfoName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public Long getTaskInfoId() {
        return taskInfoId;
    }

    public void setTaskInfoId(Long taskInfoId) {
        this.taskInfoId = taskInfoId;
    }

    public String getTaskInfoName() {
        return taskInfoName;
    }

    public void setTaskInfoName(String taskInfoName) {
        this.taskInfoName = taskInfoName;
    }
}