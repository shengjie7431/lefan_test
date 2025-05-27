package com.lefancrm.backend.dto;

import java.util.Date;

public class LoanApplication {
    private Long id;

    private Long userId;

    private String userName;

    private String loanNo;

    private String userPhone;

    private Integer isTrafficAccident;

    private Double loanMoney;

    private Integer loanPurpose;

    private String accidentProvince;

    private String accidentProvinceName;

    private String accidentCity;

    private String accidentCityName;

    private Integer state;

    private String accidentDistrict;

    private String accidentDistrictName;

    private String accidentAddress;

    private Date createTime;

    private Integer deleteFlag;

    private String reson;

    private Date accidentTime;

    private Integer isFined;

    private String userPromotedName;//推广人姓名(来自表user_promoted)

    private String userPromotedPhone;//推广人电话(来自表user_promoted)

    private Integer isTestcase;//是否测试案件

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

    public Integer getIsTrafficAccident() {
        return isTrafficAccident;
    }

    public void setIsTrafficAccident(Integer isTrafficAccident) {
        this.isTrafficAccident = isTrafficAccident;
    }

    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    public Integer getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(Integer loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public String getAccidentProvince() {
        return accidentProvince;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public String getAccidentProvinceName() {
        return accidentProvinceName;
    }

    public void setAccidentProvinceName(String accidentProvinceName) {
        this.accidentProvinceName = accidentProvinceName;
    }

    public String getAccidentCityName() {
        return accidentCityName;
    }

    public void setAccidentCityName(String accidentCityName) {
        this.accidentCityName = accidentCityName;
    }

    public String getAccidentDistrictName() {
        return accidentDistrictName;
    }

    public void setAccidentDistrictName(String accidentDistrictName) {
        this.accidentDistrictName = accidentDistrictName;
    }

    public Date getAccidentTime() {
        return accidentTime;
    }

    public void setAccidentTime(Date accidentTime) {
        this.accidentTime = accidentTime;
    }

    public Integer getIsFined() {
        return isFined;
    }

    public void setIsFined(Integer isFined) {
        this.isFined = isFined;
    }

    public String getUserPromotedName() {
        return userPromotedName;
    }

    public void setUserPromotedName(String userPromotedName) {
        this.userPromotedName = userPromotedName;
    }

    public String getUserPromotedPhone() {
        return userPromotedPhone;
    }

    public void setUserPromotedPhone(String userPromotedPhone) {
        this.userPromotedPhone = userPromotedPhone;
    }

    public Integer getIsTestcase() {
        return isTestcase;
    }

    public void setIsTestcase(Integer isTestcase) {
        this.isTestcase = isTestcase;
    }
}