package com.lefancrm.apicenter.model;

import java.util.Date;

public class BillingApplyRefund {
    private Long id;

    private Long billId;

    private Double refundMoney;

    private Date refundTime;

    private Long refundUser;

    private String refundUserBy;

    private String remark;

    private Date createTime;

    private Integer deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Long getRefundUser() {
        return refundUser;
    }

    public void setRefundUser(Long refundUser) {
        this.refundUser = refundUser;
    }

    public String getRefundUserBy() {
        return refundUserBy;
    }

    public void setRefundUserBy(String refundUserBy) {
        this.refundUserBy = refundUserBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}