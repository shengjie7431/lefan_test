package com.lefancrm.apicenter.model;

import java.util.Date;

public class ClaimsVisitInfo {
    private Long id;

    private String driverName;

    private String driverTel;

    private String injuredName;

    private String injuredTel;

    private String hospital;

    private String department;

    private String bed;

    private Date createTime;

    private String createBy;

    private Integer handleState;

    private String handleUser;

    private Date handleTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverTel() {
        return driverTel;
    }

    public void setDriverTel(String driverTel) {
        this.driverTel = driverTel;
    }

    public String getInjuredName() {
        return injuredName;
    }

    public void setInjuredName(String injuredName) {
        this.injuredName = injuredName;
    }

    public String getInjuredTel() {
        return injuredTel;
    }

    public void setInjuredTel(String injuredTel) {
        this.injuredTel = injuredTel;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
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

    public Integer getHandleState() {
        return handleState;
    }

    public void setHandleState(Integer handleState) {
        this.handleState = handleState;
    }

    public String getHandleUser() {
        return handleUser;
    }

    public void setHandleUser(String handleUser) {
        this.handleUser = handleUser;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }
}