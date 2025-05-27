package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyAchieveDetail {
    private Long id;

    private Long achNum;

    private String achRemark;

    private Long surveyUserId;

    private String surveyUserName;

    private Date createTime;

    private Long oprId;

    private Integer oprType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAchNum() {
        return achNum;
    }

    public void setAchNum(Long achNum) {
        this.achNum = achNum;
    }

    public String getAchRemark() {
        return achRemark;
    }

    public void setAchRemark(String achRemark) {
        this.achRemark = achRemark;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOprId() {
        return oprId;
    }

    public void setOprId(Long oprId) {
        this.oprId = oprId;
    }

    public Integer getOprType() {
        return oprType;
    }

    public void setOprType(Integer oprType) {
        this.oprType = oprType;
    }
}