package com.lefancrm.apicenter.fina.model;

import java.util.Date;

public class FinaApplicant {
    private Long id;

    private Long finaUserId;

    private String finaUserName;

    private String finaUserIdcard;

    private String finaUserTel;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinaUserId() {
        return finaUserId;
    }

    public void setFinaUserId(Long finaUserId) {
        this.finaUserId = finaUserId;
    }

    public String getFinaUserName() {
        return finaUserName;
    }

    public void setFinaUserName(String finaUserName) {
        this.finaUserName = finaUserName;
    }

    public String getFinaUserIdcard() {
        return finaUserIdcard;
    }

    public void setFinaUserIdcard(String finaUserIdcard) {
        this.finaUserIdcard = finaUserIdcard;
    }

    public String getFinaUserTel() {
        return finaUserTel;
    }

    public void setFinaUserTel(String finaUserTel) {
        this.finaUserTel = finaUserTel;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}