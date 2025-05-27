package com.lefancrm.apicenter.model;

import java.util.Date;

public class FrontRoleMenu {
    private Long id;

    private Long frontRoleId;

    private Long frontMenuId;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFrontRoleId() {
        return frontRoleId;
    }

    public void setFrontRoleId(Long frontRoleId) {
        this.frontRoleId = frontRoleId;
    }

    public Long getFrontMenuId() {
        return frontMenuId;
    }

    public void setFrontMenuId(Long frontMenuId) {
        this.frontMenuId = frontMenuId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}