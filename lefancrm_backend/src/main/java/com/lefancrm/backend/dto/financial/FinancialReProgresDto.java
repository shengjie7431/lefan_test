package com.lefancrm.backend.dto.financial;

import java.util.Date;

public class FinancialReProgresDto {
    private Long id;

    private Long financialReApplyId;

    private Long progressUserId;

    private String progressUserName;

    private String progressName;

    private String progressDesc;

    private Date progressTime;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private String stateStr;

    private String details;

    private Integer progressType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinancialReApplyId() {
        return financialReApplyId;
    }

    public void setFinancialReApplyId(Long financialReApplyId) {
        this.financialReApplyId = financialReApplyId;
    }

    public Long getProgressUserId() {
        return progressUserId;
    }

    public void setProgressUserId(Long progressUserId) {
        this.progressUserId = progressUserId;
    }

    public String getProgressUserName() {
        return progressUserName;
    }

    public void setProgressUserName(String progressUserName) {
        this.progressUserName = progressUserName;
    }

    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
    }

    public String getProgressDesc() {
        return progressDesc;
    }

    public void setProgressDesc(String progressDesc) {
        this.progressDesc = progressDesc;
    }

    public Date getProgressTime() {
        return progressTime;
    }

    public void setProgressTime(Date progressTime) {
        this.progressTime = progressTime;
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

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getProgressType() {
        return progressType;
    }

    public void setProgressType(Integer progressType) {
        this.progressType = progressType;
    }
}