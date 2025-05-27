package com.lefancrm.backend.dto;

public class DistributionBasicDto {
    private Long id;

    private Double foregiftMoney;

    private Double oneLevelMoney;

    private Double twoLevelMoney;

    private Double threeLevelMoney;

    private Double caseMoney;

    private Double withdrawalsMoney;

    private Double oneCaseMoney;

    private Double twoCaseMoney;

    private Double threeCaseMoney;

    private Double loanInterestRate;

    private Double loanRate;

    public Double getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(Double loanRate) {
        this.loanRate = loanRate;
    }

    public Double getLoanInterestRate() {
        return loanInterestRate;
    }

    public void setLoanInterestRate(Double loanInterestRate) {
        this.loanInterestRate = loanInterestRate;
    }

    private Double oneCompanyMoney;

    private Double twoCompanyMoney;

    private Double threeCompanyMoney;

    public Double getWithdrawalsMoney() {
        return withdrawalsMoney;
    }

    public void setWithdrawalsMoney(Double withdrawalsMoney) {
        this.withdrawalsMoney = withdrawalsMoney;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getForegiftMoney() {
        return foregiftMoney;
    }

    public void setForegiftMoney(Double foregiftMoney) {
        this.foregiftMoney = foregiftMoney;
    }

    public Double getOneLevelMoney() {
        return oneLevelMoney;
    }

    public void setOneLevelMoney(Double oneLevelMoney) {
        this.oneLevelMoney = oneLevelMoney;
    }

    public Double getTwoLevelMoney() {
        return twoLevelMoney;
    }

    public void setTwoLevelMoney(Double twoLevelMoney) {
        this.twoLevelMoney = twoLevelMoney;
    }

    public Double getThreeLevelMoney() {
        return threeLevelMoney;
    }

    public void setThreeLevelMoney(Double threeLevelMoney) {
        this.threeLevelMoney = threeLevelMoney;
    }

    public Double getCaseMoney() {
        return caseMoney;
    }

    public void setCaseMoney(Double caseMoney) {
        this.caseMoney = caseMoney;
    }

    public Double getOneCaseMoney() {
        return oneCaseMoney;
    }

    public void setOneCaseMoney(Double oneCaseMoney) {
        this.oneCaseMoney = oneCaseMoney;
    }

    public Double getTwoCaseMoney() {
        return twoCaseMoney;
    }

    public void setTwoCaseMoney(Double twoCaseMoney) {
        this.twoCaseMoney = twoCaseMoney;
    }

    public Double getThreeCaseMoney() {
        return threeCaseMoney;
    }

    public void setThreeCaseMoney(Double threeCaseMoney) {
        this.threeCaseMoney = threeCaseMoney;
    }

    public Double getOneCompanyMoney() {
        return oneCompanyMoney;
    }

    public void setOneCompanyMoney(Double oneCompanyMoney) {
        this.oneCompanyMoney = oneCompanyMoney;
    }

    public Double getTwoCompanyMoney() {
        return twoCompanyMoney;
    }

    public void setTwoCompanyMoney(Double twoCompanyMoney) {
        this.twoCompanyMoney = twoCompanyMoney;
    }

    public Double getThreeCompanyMoney() {
        return threeCompanyMoney;
    }

    public void setThreeCompanyMoney(Double threeCompanyMoney) {
        this.threeCompanyMoney = threeCompanyMoney;
    }
}