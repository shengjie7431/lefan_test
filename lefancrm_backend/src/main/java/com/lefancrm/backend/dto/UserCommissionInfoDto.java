package com.lefancrm.backend.dto;

public class UserCommissionInfoDto {
    private Long id;

    private Long userId;

    private String userName;

    private Integer month;

    private Integer year;

    private Integer monthNewSign;

    private Double monthServiceMoney;

    private Double arrivalMoney;

    private Double commissionMoney;

    private Double wages;

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

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonthNewSign() {
        return monthNewSign;
    }

    public void setMonthNewSign(Integer monthNewSign) {
        this.monthNewSign = monthNewSign;
    }

    public Double getMonthServiceMoney() {
        return monthServiceMoney;
    }

    public void setMonthServiceMoney(Double monthServiceMoney) {
        this.monthServiceMoney = monthServiceMoney;
    }

    public Double getArrivalMoney() {
        return arrivalMoney;
    }

    public void setArrivalMoney(Double arrivalMoney) {
        this.arrivalMoney = arrivalMoney;
    }

    public Double getCommissionMoney() {
        return commissionMoney;
    }

    public void setCommissionMoney(Double commissionMoney) {
        this.commissionMoney = commissionMoney;
    }

    public Double getWages() {
        return wages;
    }

    public void setWages(Double wages) {
        this.wages = wages;
    }
}