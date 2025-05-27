package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyAttrUpdRecord {
    private Long id;

    private Long surveyInfoId;

    private String updAttr;

    private String updBeforeValue;

    private String updAfterValue;

    private Date updTime;

    private String updRemark;

    private String updUserName;

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

    public String getUpdAttr() {
        return updAttr;
    }

    public void setUpdAttr(String updAttr) {
        this.updAttr = updAttr;
    }

    public String getUpdBeforeValue() {
        return updBeforeValue;
    }

    public void setUpdBeforeValue(String updBeforeValue) {
        this.updBeforeValue = updBeforeValue;
    }

    public String getUpdAfterValue() {
        return updAfterValue;
    }

    public void setUpdAfterValue(String updAfterValue) {
        this.updAfterValue = updAfterValue;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public String getUpdRemark() {
        return updRemark;
    }

    public void setUpdRemark(String updRemark) {
        this.updRemark = updRemark;
    }

    public String getUpdUserName() {
        return updUserName;
    }

    public void setUpdUserName(String updUserName) {
        this.updUserName = updUserName;
    }
}