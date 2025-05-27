package com.lefancrm.apicenter.fina.model;

import java.util.Date;

public class FinaApplicantMoney {
    private Long finaInfoId;

    private Double estimateMoney;

    private Double proposalMoney;

    private Double actualMoney;

    private Integer riskLevel;

    private Date realLoanTime;

    private Integer confirmType;

    private Long checkManId;

    private String checkManName;

    private Long doubleCheckManId;

    private String doubleCheckManName;

    private Date checkTime;

    private Date doubleCheckTime;

    private Long customerId;

    private String customerName;

    private Date customerCheckTime;

    public Long getFinaInfoId() {
        return finaInfoId;
    }

    public void setFinaInfoId(Long finaInfoId) {
        this.finaInfoId = finaInfoId;
    }

    public Double getEstimateMoney() {
        return estimateMoney;
    }

    public void setEstimateMoney(Double estimateMoney) {
        this.estimateMoney = estimateMoney;
    }

    public Double getProposalMoney() {
        return proposalMoney;
    }

    public void setProposalMoney(Double proposalMoney) {
        this.proposalMoney = proposalMoney;
    }

    public Double getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(Double actualMoney) {
        this.actualMoney = actualMoney;
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Date getRealLoanTime() {
        return realLoanTime;
    }

    public void setRealLoanTime(Date realLoanTime) {
        this.realLoanTime = realLoanTime;
    }

    public Integer getConfirmType() {
        return confirmType;
    }

    public void setConfirmType(Integer confirmType) {
        this.confirmType = confirmType;
    }

    public Long getCheckManId() {
        return checkManId;
    }

    public void setCheckManId(Long checkManId) {
        this.checkManId = checkManId;
    }

    public String getCheckManName() {
        return checkManName;
    }

    public void setCheckManName(String checkManName) {
        this.checkManName = checkManName;
    }

    public Long getDoubleCheckManId() {
        return doubleCheckManId;
    }

    public void setDoubleCheckManId(Long doubleCheckManId) {
        this.doubleCheckManId = doubleCheckManId;
    }

    public String getDoubleCheckManName() {
        return doubleCheckManName;
    }

    public void setDoubleCheckManName(String doubleCheckManName) {
        this.doubleCheckManName = doubleCheckManName;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getDoubleCheckTime() {
        return doubleCheckTime;
    }

    public void setDoubleCheckTime(Date doubleCheckTime) {
        this.doubleCheckTime = doubleCheckTime;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getCustomerCheckTime() {
        return customerCheckTime;
    }

    public void setCustomerCheckTime(Date customerCheckTime) {
        this.customerCheckTime = customerCheckTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}