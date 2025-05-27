package com.lefancrm.apicenter.model;

import java.util.Date;

public class CommonFineEnum {
    private Long id;

    private String fineEnumName;

    private Double fineMoney;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFineEnumName() {
        return fineEnumName;
    }

    public void setFineEnumName(String fineEnumName) {
        this.fineEnumName = fineEnumName;
    }

    public Double getFineMoney() {
        return fineMoney;
    }

    public void setFineMoney(Double fineMoney) {
        this.fineMoney = fineMoney;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}