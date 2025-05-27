package com.lefancrm.apicenter.model;

public class SurveyChannelCostItem {
    private Long id;

    private Long surveyChannelId;

    private Long surveyInfoId;

    private String surveyCaseNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyChannelId() {
        return surveyChannelId;
    }

    public void setSurveyChannelId(Long surveyChannelId) {
        this.surveyChannelId = surveyChannelId;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }
}