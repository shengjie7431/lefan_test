package com.lefancrm.apicenter.dto;

import java.util.Date;

public class CaseApplyDto {
    private Long id;

    private String userName;

    private String phone;

    private Integer state;

    private String carNumber;

    private String cureHospital;

    private Integer injuryType;

    private Integer otherType;

    private Integer weType;

    private String caseProvince;

    private Long caseProvinceId;

    private String caseCity;

    private Long caseCityId;

    private String caseDistrict;

    private Long caseDistrictId;

    private String caseAddress;

    private Date dangerTime;

    private Date createTime;

    private String caseNo;

    private String caseSources;

    private Integer isNeedAdvance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCureHospital() {
        return cureHospital;
    }

    public void setCureHospital(String cureHospital) {
        this.cureHospital = cureHospital;
    }

    public Integer getInjuryType() {
        return injuryType;
    }

    public void setInjuryType(Integer injuryType) {
        this.injuryType = injuryType;
    }

    public Integer getOtherType() {
        return otherType;
    }

    public void setOtherType(Integer otherType) {
        this.otherType = otherType;
    }

    public Integer getWeType() {
        return weType;
    }

    public void setWeType(Integer weType) {
        this.weType = weType;
    }

    public String getCaseProvince() {
        return caseProvince;
    }

    public void setCaseProvince(String caseProvince) {
        this.caseProvince = caseProvince;
    }

    public Long getCaseProvinceId() {
        return caseProvinceId;
    }

    public void setCaseProvinceId(Long caseProvinceId) {
        this.caseProvinceId = caseProvinceId;
    }

    public String getCaseCity() {
        return caseCity;
    }

    public void setCaseCity(String caseCity) {
        this.caseCity = caseCity;
    }

    public Long getCaseCityId() {
        return caseCityId;
    }

    public void setCaseCityId(Long caseCityId) {
        this.caseCityId = caseCityId;
    }

    public String getCaseDistrict() {
        return caseDistrict;
    }

    public void setCaseDistrict(String caseDistrict) {
        this.caseDistrict = caseDistrict;
    }

    public Long getCaseDistrictId() {
        return caseDistrictId;
    }

    public void setCaseDistrictId(Long caseDistrictId) {
        this.caseDistrictId = caseDistrictId;
    }

    public Date getDangerTime() {
        return dangerTime;
    }

    public void setDangerTime(Date dangerTime) {
        this.dangerTime = dangerTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseSources() {
        return caseSources;
    }

    public void setCaseSources(String caseSources) {
        this.caseSources = caseSources;
    }

    public Integer getIsNeedAdvance() {
        return isNeedAdvance;
    }

    public void setIsNeedAdvance(Integer isNeedAdvance) {
        this.isNeedAdvance = isNeedAdvance;
    }

    public String getCaseAddress() {
        return caseAddress;
    }

    public void setCaseAddress(String caseAddress) {
        this.caseAddress = caseAddress;
    }
}