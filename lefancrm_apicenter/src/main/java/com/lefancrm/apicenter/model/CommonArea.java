package com.lefancrm.apicenter.model;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.List;

public class CommonArea {
    private Long areaId;

    private String areaName;

    private Long parentId;

    private Integer areaType;

    private String areaShortname;

    private String areaLongname;

    private Integer areaOrder;

    private String longPinyin;

    private String shortPinyin;

    private Integer deleteFlag;

    private Integer isShow;

    private Integer cityType;

    private Integer allChildrenNum;
    private Integer selectedChildrenNum;
    private String typeName;
    private Integer showType;//显示方式 0 不显示 1 显示方块  2显示对勾
    private Boolean selected;
    private List<CommonArea> childrens;
    private Long areaCateGoriesId;
    private String selectAreaIds;

    public String getSelectAreaIds() {
        return selectAreaIds;
    }

    public void setSelectAreaIds(String selectAreaIds) {
        this.selectAreaIds = selectAreaIds;
    }

    public Long getAreaCateGoriesId() {
        return areaCateGoriesId;
    }

    public void setAreaCateGoriesId(Long areaCateGoriesId) {
        this.areaCateGoriesId = areaCateGoriesId;
    }

    public List<CommonArea> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<CommonArea> childrens) {
        this.childrens = childrens;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getAllChildrenNum() {
        return allChildrenNum;
    }

    public void setAllChildrenNum(Integer allChildrenNum) {
        this.allChildrenNum = allChildrenNum;
    }

    public Integer getSelectedChildrenNum() {
        return selectedChildrenNum;
    }

    public void setSelectedChildrenNum(Integer selectedChildrenNum) {
        this.selectedChildrenNum = selectedChildrenNum;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }


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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
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

    public Integer getCityType() {
        return cityType;
    }

    public void setCityType(Integer cityType) {
        this.cityType = cityType;
    }
}