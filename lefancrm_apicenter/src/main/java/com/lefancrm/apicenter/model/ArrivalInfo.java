package com.lefancrm.apicenter.model;

import java.util.Date;

public class ArrivalInfo {
    private Long id;

    private Long caseId;

    private String caseNo;

    private String caseTitle;

    private Double arrivalMoney;

    private Date arrivalTime;

    private Long userCommissionId;

    /**
     * 增加本金 利息  通道费  保险费 便于报表查询
     */
    private Double loanAmount;

    private Double interestAmount;

    private Double stillAmount;

    private Double insuranceAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public Double getArrivalMoney() {
        return arrivalMoney;
    }

    public void setArrivalMoney(Double arrivalMoney) {
        this.arrivalMoney = arrivalMoney;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getUserCommissionId() {
        return userCommissionId;
    }

    public void setUserCommissionId(Long userCommissionId) {
        this.userCommissionId = userCommissionId;
    }

    public Double getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(Double interestAmount) {
        this.interestAmount = interestAmount;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getStillAmount() {
        return stillAmount;
    }

    public void setStillAmount(Double stillAmount) {
        this.stillAmount = stillAmount;
    }

    public Double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(Double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }
}