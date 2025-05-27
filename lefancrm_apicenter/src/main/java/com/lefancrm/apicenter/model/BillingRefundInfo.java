package com.lefancrm.apicenter.model;

import java.util.Date;

public class BillingRefundInfo {
    private Long id;

    private Long billingMatchId;

    private Double refundMoney;

    private Long refundUserId;

    private String refundUserName;

    private Integer refundType;

    private Integer refundStatus;

    private String refundReason;

    private Date createTime;

    private String payee;

    private String bankCarNo;

    private String bankName;

    private String bankBranch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillingMatchId() {
        return billingMatchId;
    }

    public void setBillingMatchId(Long billingMatchId) {
        this.billingMatchId = billingMatchId;
    }

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Long getRefundUserId() {
        return refundUserId;
    }

    public void setRefundUserId(Long refundUserId) {
        this.refundUserId = refundUserId;
    }

    public String getRefundUserName() {
        return refundUserName;
    }

    public void setRefundUserName(String refundUserName) {
        this.refundUserName = refundUserName;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getBankCarNo() {
        return bankCarNo;
    }

    public void setBankCarNo(String bankCarNo) {
        this.bankCarNo = bankCarNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }
}