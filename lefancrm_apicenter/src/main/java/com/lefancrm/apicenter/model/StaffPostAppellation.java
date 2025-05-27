package com.lefancrm.apicenter.model;

import java.util.Date;

public class StaffPostAppellation {
    private Long id;

    private String appellationName;

    private String appellationDesc;

    private Integer deleteFlag;

    private Date createTime;

    private String createBy;

    private Integer  state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppellationName() {
        return appellationName;
    }

    public void setAppellationName(String appellationName) {
        this.appellationName = appellationName;
    }

    public String getAppellationDesc() {
        return appellationDesc;
    }

    public void setAppellationDesc(String appellationDesc) {
        this.appellationDesc = appellationDesc;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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
}