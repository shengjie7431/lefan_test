package com.lefancrm.backend.dto;

import java.util.Date;

public class SurveyFwProgressDTO {
    private Long id;

    private Long fwId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFwId() {
        return fwId;
    }

    public void setFwId(Long fwId) {
        this.fwId = fwId;
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
}