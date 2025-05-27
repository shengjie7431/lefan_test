package com.lefancrm.apicenter.fina.model;

import java.util.Date;
import java.util.List;

public class FinaUrgeInfo {
    private Long id;

    private Long settlementInfoId;

    private Long urgeUserId;

    private String urgeUserName;

    private String urgeDesc;

    private Double urgeMoney;

    private Date repaymentTime;

    private Integer urgeType;

    private String linkTel;

    private String linkUser;

    private Date linkTime;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    private List<FinaFile> finaFiles;

    private List<FinaFile> oneFiles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSettlementInfoId() {
        return settlementInfoId;
    }

    public void setSettlementInfoId(Long settlementInfoId) {
        this.settlementInfoId = settlementInfoId;
    }

    public Long getUrgeUserId() {
        return urgeUserId;
    }

    public void setUrgeUserId(Long urgeUserId) {
        this.urgeUserId = urgeUserId;
    }

    public String getUrgeUserName() {
        return urgeUserName;
    }

    public void setUrgeUserName(String urgeUserName) {
        this.urgeUserName = urgeUserName;
    }

    public String getUrgeDesc() {
        return urgeDesc;
    }

    public void setUrgeDesc(String urgeDesc) {
        this.urgeDesc = urgeDesc;
    }

    public Double getUrgeMoney() {
        return urgeMoney;
    }

    public void setUrgeMoney(Double urgeMoney) {
        this.urgeMoney = urgeMoney;
    }

    public Date getRepaymentTime() {
        return repaymentTime;
    }

    public void setRepaymentTime(Date repaymentTime) {
        this.repaymentTime = repaymentTime;
    }

    public Integer getUrgeType() {
        return urgeType;
    }

    public void setUrgeType(Integer urgeType) {
        this.urgeType = urgeType;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getLinkUser() {
        return linkUser;
    }

    public void setLinkUser(String linkUser) {
        this.linkUser = linkUser;
    }

    public Date getLinkTime() {
        return linkTime;
    }

    public void setLinkTime(Date linkTime) {
        this.linkTime = linkTime;
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

    public List<FinaFile> getFinaFiles() {
        return finaFiles;
    }

    public void setFinaFiles(List<FinaFile> finaFiles) {
        this.finaFiles = finaFiles;
    }

    public List<FinaFile> getOneFiles() {
        return oneFiles;
    }

    public void setOneFiles(List<FinaFile> oneFiles) {
        this.oneFiles = oneFiles;
    }
}