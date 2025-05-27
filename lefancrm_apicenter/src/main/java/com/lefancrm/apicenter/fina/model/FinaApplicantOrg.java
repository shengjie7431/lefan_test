package com.lefancrm.apicenter.fina.model;

import java.util.Date;

public class FinaApplicantOrg {
    private Long id;

    private Long finaId;

    private Long finaInfoId;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Integer finaOrgState;

    private Date orgAssignTime;

    private Date orgSubmitTime;

    private Date orgEndTime;

    private Double orgAging;

    private Date createTime;

    private String createBy;

    private Integer orgTaskType;
    private String orgTaskTypeStr;

    private Integer deleteFlag;

    private Double overAging;

    private Double finshAging;

    private Double serviceMoney;

    private Double score;

    private String taskDesc;

    private Date updateTime;

    private String updateBy;

    private Boolean assignUser = false; //是否已分派调查员

    private String agingHtml;//时效状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getFinaOrgState() {
        return finaOrgState;
    }

    public void setFinaOrgState(Integer finaOrgState) {
        this.finaOrgState = finaOrgState;
    }

    public Date getOrgAssignTime() {
        return orgAssignTime;
    }

    public void setOrgAssignTime(Date orgAssignTime) {
        this.orgAssignTime = orgAssignTime;
    }

    public Date getOrgSubmitTime() {
        return orgSubmitTime;
    }

    public void setOrgSubmitTime(Date orgSubmitTime) {
        this.orgSubmitTime = orgSubmitTime;
    }

    public Date getOrgEndTime() {
        return orgEndTime;
    }

    public void setOrgEndTime(Date orgEndTime) {
        this.orgEndTime = orgEndTime;
    }

    public Double getOrgAging() {
        return orgAging;
    }

    public void setOrgAging(Double orgAging) {
        this.orgAging = orgAging;
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

    public Integer getOrgTaskType() {
        return orgTaskType;
    }

    public void setOrgTaskType(Integer orgTaskType) {
        this.orgTaskType = orgTaskType;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public Boolean getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(Boolean assignUser) {
        this.assignUser = assignUser;
    }

    public String getAgingHtml() {
        return agingHtml;
    }

    public void setAgingHtml(String agingHtml) {
        this.agingHtml = agingHtml;
    }

    public String getOrgTaskTypeStr() {
        return orgTaskTypeStr;
    }

    public void setOrgTaskTypeStr(String orgTaskTypeStr) {
        this.orgTaskTypeStr = orgTaskTypeStr;
    }
}