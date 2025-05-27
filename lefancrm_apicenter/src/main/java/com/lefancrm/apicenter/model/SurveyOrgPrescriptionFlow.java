package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyOrgPrescriptionFlow {
    private Long id;

    private Long surveyInfoId;

    private Long surveyAssignOrgId;

    private Date startTime;

    private Integer operateType;

    private Date endTime;

    private Double days;

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

    public Long getSurveyAssignOrgId() {
        return surveyAssignOrgId;
    }

    public void setSurveyAssignOrgId(Long surveyAssignOrgId) {
        this.surveyAssignOrgId = surveyAssignOrgId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getDays() {
        return days;
    }

    public void setDays(Double days) {
        this.days = days;
    }
}