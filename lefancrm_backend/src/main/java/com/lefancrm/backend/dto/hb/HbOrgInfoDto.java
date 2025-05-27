package com.lefancrm.backend.dto.hb;

import java.util.Date;

public class HbOrgInfoDto {
    private Long id;

    private String hbOrgName;

    private Integer hbOrgStatus;

    private Integer num;

    private Date createTime;

    private String createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHbOrgName() {
        return hbOrgName;
    }

    public void setHbOrgName(String hbOrgName) {
        this.hbOrgName = hbOrgName;
    }

    public Integer getHbOrgStatus() {
        return hbOrgStatus;
    }

    public void setHbOrgStatus(Integer hbOrgStatus) {
        this.hbOrgStatus = hbOrgStatus;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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