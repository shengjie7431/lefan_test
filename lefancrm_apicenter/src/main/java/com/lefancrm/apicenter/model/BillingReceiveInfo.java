package com.lefancrm.apicenter.model;

import java.util.Date;

/**
 * 认领明细表
 * @author EDZ
 */
public class BillingReceiveInfo {

    /**
     * 主键标识ID
     */
    private Integer id;

    /**
     * 未匹配收款表ID
     */
    private Integer billingMatchId;

    /**
     * 开票ID
     */
    private Integer billingId;

    /**
     * 开票明细ID
     */
    private Integer billingImgsId;

    /**
     * 认领金额
     */
    private Double receiveMoney;

    /**
     * 认领人
     */
    private Integer receiveUserId;

    /**
     * 认领人姓名
     */
    private String receiveUserName;

    /**
     * 认领时间
     */
    private Date receiveTime;

    private Integer receiveType;
    private Integer receiveStatus;
    private String rejectReason;

    private String billProName;
    private String billComp;
    private String billStaffOrgName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBillingMatchId() {
        return billingMatchId;
    }

    public void setBillingMatchId(Integer billingMatchId) {
        this.billingMatchId = billingMatchId;
    }

    public Integer getBillingId() {
        return billingId;
    }

    public void setBillingId(Integer billingId) {
        this.billingId = billingId;
    }

    public Integer getBillingImgsId() {
        return billingImgsId;
    }

    public void setBillingImgsId(Integer billingImgsId) {
        this.billingImgsId = billingImgsId;
    }

    public Double getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(Double receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public Integer getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Integer receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    public Integer getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(Integer receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getBillProName() {
        return billProName;
    }

    public void setBillProName(String billProName) {
        this.billProName = billProName;
    }

    public String getBillComp() {
        return billComp;
    }

    public void setBillComp(String billComp) {
        this.billComp = billComp;
    }

    public String getBillStaffOrgName() {
        return billStaffOrgName;
    }

    public void setBillStaffOrgName(String billStaffOrgName) {
        this.billStaffOrgName = billStaffOrgName;
    }
}
