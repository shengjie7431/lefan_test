package com.lefancrm.backend.dto;

import java.io.Serializable;
import java.util.Date;

public class PositionLevelDto implements Serializable {
    private Long id;

    private String levelCode;

    private String levelDesc;

    private Date createTime;

    private Date updateTime;

    private Double baseWages;

    private Double evaWages;

    private Long parentId;

    private Long managerComrateId;

    private String parentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public String getLevelDesc() {
        return levelDesc;
    }

    public void setLevelDesc(String levelDesc) {
        this.levelDesc = levelDesc;
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

    public Double getBaseWages() {
        return baseWages;
    }

    public void setBaseWages(Double baseWages) {
        this.baseWages = baseWages;
    }

    public Double getEvaWages() {
        return evaWages;
    }

    public void setEvaWages(Double evaWages) {
        this.evaWages = evaWages;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getManagerComrateId() {
        return managerComrateId;
    }

    public void setManagerComrateId(Long managerComrateId) {
        this.managerComrateId = managerComrateId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}