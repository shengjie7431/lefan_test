package com.lefancrm.backend.dto.claims;

import com.lefancrm.backend.annotation.Excel;

import java.util.Date;

public class ClaimsVisitInfo {
    private Long id;

    @Excel(name = "驾驶员姓名",excelIndex = 0)
    private String driverName;

    @Excel(name = "驾驶员电话",excelIndex = 1)
    private String driverTel;

    @Excel(name = "伤者姓名",excelIndex = 2)
    private String injuredName;

    @Excel(name = "伤者电话",excelIndex = 3)
    private String injuredTel;

    @Excel(name = "医院",excelIndex = 4)
    private String hospital;

    @Excel(name = "科室",excelIndex = 5)
    private String department;

    @Excel(name = "床位号",excelIndex = 6)
    private String bed;

    @Excel(name = "创建时间",excelIndex = 7, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    private String createBy;

    @Excel(name = "处理状态",excelIndex = 8,enumValue = "0=未处理,1=已处理")
    private Integer handleState;

    @Excel(name = "处理人",excelIndex = 9)
    private String handleUser;

    @Excel(name = "处理时间",excelIndex = 10, dateFormat = "yyyy-MM-dd")
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