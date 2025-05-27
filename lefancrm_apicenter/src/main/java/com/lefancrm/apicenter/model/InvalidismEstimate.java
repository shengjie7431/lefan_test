package com.lefancrm.apicenter.model;

import java.util.Date;

public class InvalidismEstimate {
    private Long id;

    private Long userId;

    private String userName;

    private String userPhone;

    private String accidentProvince;

    private String accidentCity;

    private String accidentDistrict;

    private String accidentAddress;

    private Integer accidentType;

    private Integer state;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    private Long accidentProvinceId;

    private Long accidentCityId;

    private Long accidentDistrictId;

    private Long turnStatus;

    private String promoterName;

    private Long caseId;

    private String caseNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getAccidentProvince() {
        return accidentProvince;
    }

    public void setAccidentProvince(String accidentProvince) {
        this.accidentProvince = accidentProvince;
    }

    public String getAccidentCity() {
        return accidentCity;
    }

    public void setAccidentCity(String accidentCity) {
        this.accidentCity = accidentCity;
    }

    public String getAccidentDistrict() {
        return accidentDistrict;
    }

    public void setAccidentDistrict(String accidentDistrict) {
        this.accidentDistrict = accidentDistrict;
    }

    public String getAccidentAddress() {
        return accidentAddress;
    }

    public void setAccidentAddress(String accidentAddress) {
        this.accidentAddress = accidentAddress;
    }

    public Integer getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(Integer accidentType) {
        this.accidentType = accidentType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Long getAccidentProvinceId() {
        return accidentProvinceId;
    }

    public void setAccidentProvinceId(Long accidentProvinceId) {
        this.accidentProvinceId = accidentProvinceId;
    }

    public Long getAccidentCityId() {
        return accidentCityId;
    }

    public void setAccidentCityId(Long accidentCityId) {
        this.accidentCityId = accidentCityId;
    }

    public Long getAccidentDistrictId() {
        return accidentDistrictId;
    }

    public void setAccidentDistrictId(Long accidentDistrictId) {
        this.accidentDistrictId = accidentDistrictId;
    }

    public String getPromoterName() {
        return promoterName;
    }

    public void setPromoterName(String promoterName) {
        this.promoterName = promoterName;
    }

    public Long getTurnStatus() {
        return turnStatus;
    }

    public void setTurnStatus(Long turnStatus) {
        this.turnStatus = turnStatus;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }
}