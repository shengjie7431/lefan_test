package com.lefancrm.backend.dto;

import java.util.Date;

public class SurveyCaseWorkflowDto {
    private Long id;

    private String stepName;

    private Long dealUserId;

    private String dealUserName;

    private Date startTime;

    private Date endTime;

    private Long hours;

    private Long surveyId;

    private Long surveyInfoId;

    private String showTimeStr;//显示得时效字符串

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public Long getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(Long dealUserId) {
        this.dealUserId = dealUserId;
    }

    public String getDealUserName() {
        return dealUserName;
    }

    public void setDealUserName(String dealUserName) {
        this.dealUserName = dealUserName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
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

    public String getShowTimeStr() {
        return showTimeStr;
    }

    public void setShowTimeStr(String showTimeStr) {
        this.showTimeStr = showTimeStr;
    }
}