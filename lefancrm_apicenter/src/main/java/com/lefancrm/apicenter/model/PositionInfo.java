package com.lefancrm.apicenter.model;

import java.util.Date;

public class PositionInfo {
    private Long id;

    private String positionName;

    private String positionDesc;

    private Date createTime;

    private Date updateTime;

    private Long positionLevelId;

    private String positionLevelName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionDesc() {
        return positionDesc;
    }

    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getPositionLevelId() {
        return positionLevelId;
    }

    public void setPositionLevelId(Long positionLevelId) {
        this.positionLevelId = positionLevelId;
    }

    public String getPositionLevelName() {
        return positionLevelName;
    }

    public void setPositionLevelName(String positionLevelName) {
        this.positionLevelName = positionLevelName;
    }
}