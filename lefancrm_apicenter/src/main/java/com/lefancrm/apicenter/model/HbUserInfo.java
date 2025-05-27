package com.lefancrm.apicenter.model;

import java.util.Date;

public class HbUserInfo {
    private Long userId;

    private Long hbOrgId;

    private String hbOrgName;

    private Integer hbUserStatus;

    private String hbUserCode;

    private Date createTime;

    private String createBy;

    private String hbUserName;

    private String hbUserTel;

    private String hbOpenId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getHbUserStatus() {
        return hbUserStatus;
    }

    public void setHbUserStatus(Integer hbUserStatus) {
        this.hbUserStatus = hbUserStatus;
    }

    public String getHbUserCode() {
        return hbUserCode;
    }

    public void setHbUserCode(String hbUserCode) {
        this.hbUserCode = hbUserCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getHbUserName() {
        return hbUserName;
    }

    public void setHbUserName(String hbUserName) {
        this.hbUserName = hbUserName;
    }

    public String getHbUserTel() {
        return hbUserTel;
    }

    public void setHbUserTel(String hbUserTel) {
        this.hbUserTel = hbUserTel;
    }

    public String getHbOpenId() {
        return hbOpenId;
    }

    public void setHbOpenId(String hbOpenId) {
        this.hbOpenId = hbOpenId;
    }
}