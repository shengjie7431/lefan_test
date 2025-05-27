package com.lefancrm.apicenter.model;

public class SurveyCommonAreaPrice {
    private Long areaId;

    private String areaName;

    private Integer parentId;

    private String areaType;

    private String areaShortname;

    private String areaLongname;

    private Integer areaOrder;

    private String longPinyin;

    private String shortPinyin;

    private Integer deleteFlag;

    private Integer isShow;

    private Integer taskId;

    private String taskName;

    private Double price;

    private Integer cityType;

    private Long taskInfoContentId;

    private String taskInfoContentName;

    private Long directionResultTypeId;

    private String directionResultTypeName;

    private Integer priceType;//价格类型（1：保险版；2、互助版）

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getAreaShortname() {
        return areaShortname;
    }

    public void setAreaShortname(String areaShortname) {
        this.areaShortname = areaShortname;
    }

    public String getAreaLongname() {
        return areaLongname;
    }

    public void setAreaLongname(String areaLongname) {
        this.areaLongname = areaLongname;
    }

    public Integer getAreaOrder() {
        return areaOrder;
    }

    public void setAreaOrder(Integer areaOrder) {
        this.areaOrder = areaOrder;
    }

    public String getLongPinyin() {
        return longPinyin;
    }

    public void setLongPinyin(String longPinyin) {
        this.longPinyin = longPinyin;
    }

    public String getShortPinyin() {
        return shortPinyin;
    }

    public void setShortPinyin(String shortPinyin) {
        this.shortPinyin = shortPinyin;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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