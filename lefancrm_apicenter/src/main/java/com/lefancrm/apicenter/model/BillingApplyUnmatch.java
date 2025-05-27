package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

public class BillingApplyUnmatch {
    private Long id;

    private String unmatchNo;

    private Double money;

    private Long createById;

    private String createBy;

    private Date createTime;

    private Integer state;

    private Long claimById;

    private String claimBy;

    private Date claimTime;

    private Long billId;

    private String billNo;

    private String payer;

    private String remark;

    private Date payTime;

    private String comments;

    private Integer billingItemsId;

    private String billingItemsName;

    private Integer receivingCompanyId;

    private String receivingCompanyName;

    private Double matchMoney;

    private Double unmatchMoney;

    private Boolean waitCheck;

    private Double refundMoney;//退费合计

    private List<BillingReceiveInfo> receiveInfos;//认领明细

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnmatchNo() {
        return unmatchNo;
    }

    public void setUnmatchNo(String unmatchNo) {
        this.unmatchNo = unmatchNo;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getClaimById() {
        return claimById;
    }

    public void setClaimById(Long claimById) {
        this.claimById = claimById;
    }

    public String getClaimBy() {
        return claimBy;
    }

    public void setClaimBy(String claimBy) {
        this.claimBy = claimBy;
    }

    public Date getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(Date claimTime) {
        this.claimTime = claimTime;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getBillingItemsId() {
        return billingItemsId;
    }

    public void setBillingItemsId(Integer billingItemsId) {
        this.billingItemsId = billingItemsId;
    }

    public String getBillingItemsName() {
        return billingItemsName;
    }

    public void setBillingItemsName(String billingItemsName) {
        this.billingItemsName = billingItemsName;
    }

    public Integer getReceivingCompanyId() {
        return receivingCompanyId;
    }

    public void setReceivingCompanyId(Integer receivingCompanyId) {
        this.receivingCompanyId = receivingCompanyId;
    }

    public String getReceivingCompanyName() {
        return receivingCompanyName;
    }

    public void setReceivingCompanyName(String receivingCompanyName) {
        this.receivingCompanyName = receivingCompanyName;
    }

    public Double getMatchMoney() {
        return matchMoney;
    }

    public void setMatchMoney(Double matchMoney) {
        this.matchMoney = matchMoney;
    }

    public Double getUnmatchMoney() {
        return unmatchMoney;
    }

    public void setUnmatchMoney(Double unmatchMoney) {
        this.unmatchMoney = unmatchMoney;
    }

    public Boolean getWaitCheck() {
        return waitCheck;
    }

    public void setWaitCheck(Boolean waitCheck) {
        this.waitCheck = waitCheck;
    }

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    public List<BillingReceiveInfo> getReceiveInfos() {
        return receiveInfos;
    }

    public void setReceiveInfos(List<BillingReceiveInfo> receiveInfos) {
        this.receiveInfos = receiveInfos;
    }
}