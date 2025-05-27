package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyFranchiseePrice {
    private Long id;

    private Long franchiseeId;

    private String franchiseeName;

    private Long taskId;

    private String taskName;

    private Double taskPrice;

    private Long createBy;

    private String createByName;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private Integer areaId;

    private String areaName;

    private Integer areaType;

    private String province;

    private String city;

    private String district;

    private Integer provinceId;

    private Integer cityId;

    private Integer districtId;

    private Integer cityType;

    private Long taskInfoContentId;

    private String taskInfoContentName;

    private Long directionResultTypeId;

    private String directionResultTypeName;

    private Integer priceType;//价格类型（1：保险版；2、互助版）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Double getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(Double taskPrice) {
        this.taskPrice = taskPrice;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
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

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getCityType() {
        return cityType;
    }

    public void setCityType(Integer cityType) {
        this.cityType = cityType;
    }

    public Long getTaskInfoContentId() {
        return taskInfoContentId;
    }

    public void setTaskInfoContentId(Long taskInfoContentId) {
        this.taskInfoContentId = taskInfoContentId;
    }

    public String getTaskInfoContentName() {
        return taskInfoContentName;
    }

    public void setTaskInfoContentName(String taskInfoContentName) {
        this.taskInfoContentName = taskInfoContentName;
    }

    public Long getDirectionResultTypeId() {
        return directionResultTypeId;
    }

    public void setDirectionResultTypeId(Long directionResultTypeId) {
        this.directionResultTypeId = directionResultTypeId;
    }

    public String getDirectionResultTypeName() {
        return directionResultTypeName;
    }

    public void setDirectionResultTypeName(String directionResultTypeName) {
        this.directionResultTypeName = directionResultTypeName;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }
}