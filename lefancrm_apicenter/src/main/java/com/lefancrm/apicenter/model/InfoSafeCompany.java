package com.lefancrm.apicenter.model;

import java.util.Date;

public class InfoSafeCompany {
    private Long id;

    private Long safeId;

    private String safeName;

    private String safeTel;

    private String safeUser;

    private Long createBy;

    private String createByName;

    private Date createTime;

    private Long updateBy;

    private String updateByName;

    private Date updateTime;

    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSafeId() {
        return safeId;
    }

    public void setSafeId(Long safeId) {
        this.safeId = safeId;
    }

    public String getSafeName() {
        return safeName;
    }

    public void setSafeName(String safeName) {
        this.safeName = safeName;
    }

    public String getSafeTel() {
        return safeTel;
    }

    public void setSafeTel(String safeTel) {
        this.safeTel = safeTel;
    }

    public String getSafeUser() {
        return safeUser;
    }

    public void setSafeUser(String safeUser) {
        this.safeUser = safeUser;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}