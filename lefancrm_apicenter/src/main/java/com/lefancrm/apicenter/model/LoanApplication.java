package com.lefancrm.apicenter.model;

import java.util.Date;

public class LoanApplication {
    private Long id;

    private Long userId;

    private String userName;

    private String userPhone;

    private Integer isTrafficAccident;

    private String loanNo;

    private Double loanMoney;

    private Integer loanPurpose;

    private String accidentProvince;

    private String accidentCity;

    private Integer state;

    private String accidentDistrict;

    private String accidentAddress;

    private Date createTime;

    private Integer deleteFlag;

    private String reson;

    private Date accidentTime;

    private String accidentCityName;

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

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
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

    public Date getAccidentTime() {
        return accidentTime;
    }

    public void setAccidentTime(Date accidentTime) {
        this.accidentTime = accidentTime;
    }

    public String getAccidentCityName() {
        return accidentCityName;
    }

    public void setAccidentCityName(String accidentCityName) {
        this.accidentCityName = accidentCityName;
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