package com.lefancrm.backend.dto;

import java.util.List;
import java.util.Map;

public class OrgInfoDto {
    private Long id;

    private String orgName;

    private String orgTel;

    private String linkName;

    private String linkTel;

    private String orgProvince;

    private String orgCity;

    private String orgDistrict;

    private Long orgProvinceId;

    private Long orgCityId;

    private Long orgDistrictId;

    private String orgAddress;

    private Integer state;

    private Integer deleteFlag;

    private Integer orgType;

    private Long orgParentid;

    private Boolean selected;

//    private List<Map<String,Object>> enumMoneyList;

    private List<CommonEnumDto> enumMoneyList;

    private Double money;

    private Double unmatchMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgTel() {
        return orgTel;
    }

    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getOrgProvince() {
        return orgProvince;
    }

    public void setOrgProvince(String orgProvince) {
        this.orgProvince = orgProvince;
    }

    public String getOrgCity() {
        return orgCity;
    }

    public void setOrgCity(String orgCity) {
        this.orgCity = orgCity;
    }

    public String getOrgDistrict() {
        return orgDistrict;
    }

    public void setOrgDistrict(String orgDistrict) {
        this.orgDistrict = orgDistrict;
    }

    public Long getOrgProvinceId() {
        return orgProvinceId;
    }

    public void setOrgProvinceId(Long orgProvinceId) {
        this.orgProvinceId = orgProvinceId;
    }

    public Long getOrgCityId() {
        return orgCityId;
    }

    public void setOrgCityId(Long orgCityId) {
        this.orgCityId = orgCityId;
    }

    public Long getOrgDistrictId() {
        return orgDistrictId;
    }

    public void setOrgDistrictId(Long orgDistrictId) {
        this.orgDistrictId = orgDistrictId;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public Long getOrgParentid() {
        return orgParentid;
    }

    public void setOrgParentid(Long orgParentid) {
        this.orgParentid = orgParentid;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public List<CommonEnumDto> getEnumMoneyList() {
        return enumMoneyList;
    }

    public void setEnumMoneyList(List<CommonEnumDto> enumMoneyList) {
        this.enumMoneyList = enumMoneyList;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getUnmatchMoney() {
        return unmatchMoney;
    }

    public void setUnmatchMoney(Double unmatchMoney) {
        this.unmatchMoney = unmatchMoney;
    }
}