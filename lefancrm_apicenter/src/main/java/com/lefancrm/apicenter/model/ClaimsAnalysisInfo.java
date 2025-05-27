package com.lefancrm.apicenter.model;

import java.util.Date;

public class ClaimsAnalysisInfo {
    private Long id;

    private String linkName;

    private String linkTel;

    private Date accidentTime;

    private String accidentAddress;

    private Double accidentXLbs;

    private Double accidentYLbs;

    private String accidentDesc;

    private Integer handleState;

    private String handleUser;

    private Date handleTime;

    private Date createTime;

    private String createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public Date getAccidentTime() {
        return accidentTime;
    }

    public void setAccidentTime(Date accidentTime) {
        this.accidentTime = accidentTime;
    }

    public String getAccidentDesc() {
        return accidentDesc;
    }

    public void setAccidentDesc(String accidentDesc) {
        this.accidentDesc = accidentDesc;
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

    public String getAccidentAddress() {
        return accidentAddress;
    }

    public void setAccidentAddress(String accidentAddress) {
        this.accidentAddress = accidentAddress;
    }

    public Double getAccidentXLbs() {
        return accidentXLbs;
    }

    public void setAccidentXLbs(Double accidentXLbs) {
        this.accidentXLbs = accidentXLbs;
    }

    public Double getAccidentYLbs() {
        return accidentYLbs;
    }

    public void setAccidentYLbs(Double accidentYLbs) {
        this.accidentYLbs = accidentYLbs;
    }
}