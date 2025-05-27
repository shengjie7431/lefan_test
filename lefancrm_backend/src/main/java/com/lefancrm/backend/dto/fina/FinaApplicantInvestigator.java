package com.lefancrm.backend.dto.fina;

import java.util.Date;

public class FinaApplicantInvestigator {
    private Long id;

    private Long applicantOrgId;

    private Long finaId;

    private Long finaInfoId;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Long surveyUserId;

    private String surveyUserName;

    private Integer state;

    private Date userAssignTime;

    private Date userSubmitTime;

    private Date orgEndTime;

    private Double userAging;

    private Date createTime;

    private String createBy;

    private Integer userTaskType;
    private String userTaskTypeStr;

    private Double serviceMoney;

    private Double score;

    private Double overAging;

    private Double finshAging;

    private String taskDesc;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    private String agingHtml;//时效状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicantOrgId() {
        return applicantOrgId;
    }

    public void setApplicantOrgId(Long applicantOrgId) {
        this.applicantOrgId = applicantOrgId;
    }

    public Long getFinaId() {
        return finaId;
    }

    public void setFinaId(Long finaId) {
        this.finaId = finaId;
    }

    public Long getFinaInfoId() {
        return finaInfoId;
    }

    public void setFinaInfoId(Long finaInfoId) {
        this.finaInfoId = finaInfoId;
    }

    public Long getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Long surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
    }

    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getUserAssignTime() {
        return userAssignTime;
    }

    public void setUserAssignTime(Date userAssignTime) {
        this.userAssignTime = userAssignTime;
    }

    public Date getUserSubmitTime() {
        return userSubmitTime;
    }

    public void setUserSubmitTime(Date userSubmitTime) {
        this.userSubmitTime = userSubmitTime;
    }

    public Date getOrgEndTime() {
        return orgEndTime;
    }

    public void setOrgEndTime(Date orgEndTime) {
        this.orgEndTime = orgEndTime;
    }

    public Double getUserAging() {
        return userAging;
    }

    public void setUserAging(Double userAging) {
        this.userAging = userAging;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Integer getUserTaskType() {
        return userTaskType;
    }

    public void setUserTaskType(Integer userTaskType) {
        this.userTaskType = userTaskType;
    }

    public Double getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(Double serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getOverAging() {
        return overAging;
    }

    public void setOverAging(Double overAging) {
        this.overAging = overAging;
    }

    public Double getFinshAging() {
        return finshAging;
    }

    public void setFinshAging(Double finshAging) {
        this.finshAging = finshAging;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getAgingHtml() {
        return agingHtml;
    }

    public void setAgingHtml(String agingHtml) {
        this.agingHtml = agingHtml;
    }

    public String getUserTaskTypeStr() {
        return userTaskTypeStr;
    }

    public void setUserTaskTypeStr(String userTaskTypeStr) {
        this.userTaskTypeStr = userTaskTypeStr;
    }
}