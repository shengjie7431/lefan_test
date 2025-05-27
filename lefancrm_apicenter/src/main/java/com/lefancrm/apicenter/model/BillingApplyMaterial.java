package com.lefancrm.apicenter.model;

import java.util.Date;

public class BillingApplyMaterial {
    private Long id;

    private Long billId;

    private String materialImgs;

    private Long createById;

    private String createBy;

    private Date createTime;

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

    public String getMaterialImgs() {
        return materialImgs;
    }

    public void setMaterialImgs(String materialImgs) {
        this.materialImgs = materialImgs;
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
}