package com.lefancrm.backend.dto;

import java.util.Date;

public class SurveyCashInfoRecordDto {
    private Long id;

    private Long surveyId;

    private Long surveyInfoId;

    private Long surveyInvestigatorCaseId;

    private String surveyCaseName;

    private Long surveyUserId;

    private String surveyUserName;

    private Double surveyTaskMoney;

    private Long franchiseeId;

    private String franchiseeName;

    private Integer cashState;

    private Integer cashType;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer deleteFlag;

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

    public Long getSurveyInvestigatorCaseId() {
        return surveyInvestigatorCaseId;
    }

    public void setSurveyInvestigatorCaseId(Long surveyInvestigatorCaseId) {
        this.surveyInvestigatorCaseId = surveyInvestigatorCaseId;
    }

    public String getSurveyCaseName() {
        return surveyCaseName;
    }

    public void setSurveyCaseName(String surveyCaseName) {
        this.surveyCaseName = surveyCaseName;
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

    public Double getSurveyTaskMoney() {
        return surveyTaskMoney;
    }

    public void setSurveyTaskMoney(Double surveyTaskMoney) {
        this.surveyTaskMoney = surveyTaskMoney;
    }

    public Long getFranchiseeId() {
        return franchiseeId;
    }

    public void setFranchiseeId(Long franchiseeId) {
        this.franchiseeId = franchiseeId;
    }

    public String getFranchiseeName() {
        return franchiseeName;
    }

    public void setFranchiseeName(String franchiseeName) {
        this.franchiseeName = franchiseeName;
    }

    public Integer getCashState() {
        return cashState;
    }

    public void setCashState(Integer cashState) {
        this.cashState = cashState;
    }

    public Integer getCashType() {
        return cashType;
    }

    public void setCashType(Integer cashType) {
        this.cashType = cashType;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}