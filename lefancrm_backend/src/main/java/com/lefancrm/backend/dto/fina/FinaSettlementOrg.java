package com.lefancrm.backend.dto.fina;

import java.util.Date;

public class FinaSettlementOrg {
    private Long id;

    private Long settlementInfoId;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Integer settlementOrgState;

    private Date orgAssignTime;

    private Date orgSubmitTime;

    private Date orgEndTime;

    private Double orgAging;

    private Date createTime;

    private String createBy;

    private Integer settlementTaskType;

    private Double overAging;

    private Double finshAging;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    private Double serviceMoney;

    private String settlementTaskTypeStr;

    private String agingHtml;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSettlementInfoId() {
        return settlementInfoId;
    }

    public void setSettlementInfoId(Long settlementInfoId) {
        this.settlementInfoId = settlementInfoId;
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

    public Integer getSettlementOrgState() {
        return settlementOrgState;
    }

    public void setSettlementOrgState(Integer settlementOrgState) {
        this.settlementOrgState = settlementOrgState;
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

    public Integer getSettlementTaskType() {
        return settlementTaskType;
    }

    public void setSettlementTaskType(Integer settlementTaskType) {
        this.settlementTaskType = settlementTaskType;
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

    public String getSettlementTaskTypeStr() {
        return settlementTaskTypeStr;
    }

    public void setSettlementTaskTypeStr(String settlementTaskTypeStr) {
        this.settlementTaskTypeStr = settlementTaskTypeStr;
    }

    public Double getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(Double serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

    public String getAgingHtml() {
        return agingHtml;
    }

    public void setAgingHtml(String agingHtml) {
        this.agingHtml = agingHtml;
    }
}