package com.lefancrm.apicenter.model;

public class SurveyHelpInfo {
    private Long id;

    private Long surveyInfoId;

    private String sun;

    private String sunType;

    private String sunRemark;

    private String custGive;

    private String feedProblem;

    private String problemRemark;

    private String surveyAllRemark;

    private Long surveyOrgId;

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

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }

    public String getSunType() {
        return sunType;
    }

    public void setSunType(String sunType) {
        this.sunType = sunType;
    }

    public String getSunRemark() {
        return sunRemark;
    }

    public void setSunRemark(String sunRemark) {
        this.sunRemark = sunRemark;
    }

    public String getCustGive() {
        return custGive;
    }

    public void setCustGive(String custGive) {
        this.custGive = custGive;
    }

    public String getFeedProblem() {
        return feedProblem;
    }

    public void setFeedProblem(String feedProblem) {
        this.feedProblem = feedProblem;
    }

    public String getProblemRemark() {
        return problemRemark;
    }

    public void setProblemRemark(String problemRemark) {
        this.problemRemark = problemRemark;
    }

    public String getSurveyAllRemark() {
        return surveyAllRemark;
    }

    public void setSurveyAllRemark(String surveyAllRemark) {
        this.surveyAllRemark = surveyAllRemark;
    }

    public Long getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Long surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
    }
}