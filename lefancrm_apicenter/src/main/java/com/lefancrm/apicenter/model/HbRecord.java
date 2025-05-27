package com.lefancrm.apicenter.model;

import java.util.Date;

public class HbRecord {
    private Long id;

    private Long hbOrgId;

    private String hbOrgName;

    private Long hbUserId;

    private String hbUserName;

    private String productName;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHbOrgId() {
        return hbOrgId;
    }

    public void setHbOrgId(Long hbOrgId) {
        this.hbOrgId = hbOrgId;
    }

    public String getHbOrgName() {
        return hbOrgName;
    }

    public void setHbOrgName(String hbOrgName) {
        this.hbOrgName = hbOrgName;
    }

    public Long getHbUserId() {
        return hbUserId;
    }

    public void setHbUserId(Long hbUserId) {
        this.hbUserId = hbUserId;
    }

    public String getHbUserName() {
        return hbUserName;
    }

    public void setHbUserName(String hbUserName) {
        this.hbUserName = hbUserName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}