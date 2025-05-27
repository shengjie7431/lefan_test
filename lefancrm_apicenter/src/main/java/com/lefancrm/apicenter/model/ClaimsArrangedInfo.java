package com.lefancrm.apicenter.model;

import java.util.Date;

public class ClaimsArrangedInfo {
    private Long id;

    private String linkName;

    private String linkTel;

    private String accidentAddress;

    private Double accidentXLbs;

    private Double accidentYLbs;

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