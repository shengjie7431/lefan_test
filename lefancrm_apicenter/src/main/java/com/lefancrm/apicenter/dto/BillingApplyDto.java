package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.BillingApply;
import com.lefancrm.apicenter.model.BillingApplyAccounts;
import com.lefancrm.apicenter.model.BillingApplyRefund;

import java.util.Date;
import java.util.List;

/**
 * Created by lixianfeng on 2018/8/1.
 */
public class BillingApplyDto extends BillingApply {
    private Boolean orgManager;//是否机构经理
    private Boolean boss;//是否总经理

    private String billingEnumName;//开票类目name(导出时使用)

    private String billingItemName;//开票项目name(导出时使用)

    private Integer state;

    /**
     * 开票申请表ID
     */
    private Integer billingId;

    /**
     * 开票申请表附表
     */
    private Integer billingImgsId;


    /**
     * 到账金额
     */
    private Double theAccountMoney;

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
    private Long receiveId;

    private List<BillingApplyRefund> refunds;

    public Boolean getOrgManager() {
        return orgManager;
    }

    public void setOrgManager(Boolean orgManager) {
        this.orgManager = orgManager;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public String getBillingEnumName() {
        return billingEnumName;
    }

    public void setBillingEnumName(String billingEnumName) {
        this.billingEnumName = billingEnumName;
    }

    public String getBillingItemName() {
        return billingItemName;
    }

    public void setBillingItemName(String billingItemName) {
        this.billingItemName = billingItemName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Double getTheAccountMoney() {
        return theAccountMoney;
    }

    public void setTheAccountMoney(Double theAccountMoney) {
        this.theAccountMoney = theAccountMoney;
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

    public Long getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Long receiveId) {
        this.receiveId = receiveId;
    }

    public List<BillingApplyRefund> getRefunds() {
        return refunds;
    }

    public void setRefunds(List<BillingApplyRefund> refunds) {
        this.refunds = refunds;
    }
}
