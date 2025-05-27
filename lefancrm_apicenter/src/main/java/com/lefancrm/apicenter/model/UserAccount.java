package com.lefancrm.apicenter.model;

public class UserAccount {
    private Long userId;

    private Double userRecharge;

    private Double withdrawDeposit;

    private Double todayTotal;

    private Double monthTotal;


    private Double totalMoney;//累积总额

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }


    public Double getTodayTotal() {
        return todayTotal;
    }

    public void setTodayTotal(Double todayTotal) {
        this.todayTotal = todayTotal;
    }

    public Double getMonthTotal() {
        return monthTotal;
    }

    public void setMonthTotal(Double monthTotal) {
        this.monthTotal = monthTotal;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getUserRecharge() {
        return userRecharge;
    }

    public void setUserRecharge(Double userRecharge) {
        this.userRecharge = userRecharge;
    }

    public Double getWithdrawDeposit() {
        return withdrawDeposit;
    }

    public void setWithdrawDeposit(Double withdrawDeposit) {
        this.withdrawDeposit = withdrawDeposit;
    }
}