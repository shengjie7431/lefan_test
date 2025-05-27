package com.lefancrm.backend.dto.financial;

import java.util.Date;

public class FinancialCostBearDto {
    private Long id;

    private Long financialReApplyId;

    private Long departmentId;

    private String departmentName;

    private Double shareRate;

    private Double shareCost;

    private String costDesc;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    private Long organManagerUserId;

    private Long superiorManagerUserId;

    private Integer organManagerState;

    private Integer superiorManagerState;

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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Double getShareRate() {
        return shareRate;
    }

    public void setShareRate(Double shareRate) {
        this.shareRate = shareRate;
    }

    public Double getShareCost() {
        return shareCost;
    }

    public void setShareCost(Double shareCost) {
        this.shareCost = shareCost;
    }

    public String getCostDesc() {
        return costDesc;
    }

    public void setCostDesc(String costDesc) {
        this.costDesc = costDesc;
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

    public Long getOrganManagerUserId() {
        return organManagerUserId;
    }

    public void setOrganManagerUserId(Long organManagerUserId) {
        this.organManagerUserId = organManagerUserId;
    }

    public Long getSuperiorManagerUserId() {
        return superiorManagerUserId;
    }

    public void setSuperiorManagerUserId(Long superiorManagerUserId) {
        this.superiorManagerUserId = superiorManagerUserId;
    }

    public Integer getOrganManagerState() {
        return organManagerState;
    }

    public void setOrganManagerState(Integer organManagerState) {
        this.organManagerState = organManagerState;
    }

    public Integer getSuperiorManagerState() {
        return superiorManagerState;
    }

    public void setSuperiorManagerState(Integer superiorManagerState) {
        this.superiorManagerState = superiorManagerState;
    }
}