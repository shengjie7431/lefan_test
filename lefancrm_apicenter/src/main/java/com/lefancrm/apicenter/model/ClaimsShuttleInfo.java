package com.lefancrm.apicenter.model;

import java.util.Date;

public class ClaimsShuttleInfo {
    private Long id;

    private String linkName;

    private String linkTel;

    private String startAddress;

    private String endAddress;

    private Double startXLbs;

    private Double startYLbs;

    private Double endXLbs;

    private Double endYLbs;

    private Date shuttleTime;

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

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public Double getStartXLbs() {
        return startXLbs;
    }

    public void setStartXLbs(Double startXLbs) {
        this.startXLbs = startXLbs;
    }

    public Double getStartYLbs() {
        return startYLbs;
    }

    public void setStartYLbs(Double startYLbs) {
        this.startYLbs = startYLbs;
    }

    public Double getEndXLbs() {
        return endXLbs;
    }

    public void setEndXLbs(Double endXLbs) {
        this.endXLbs = endXLbs;
    }

    public Double getEndYLbs() {
        return endYLbs;
    }

    public void setEndYLbs(Double endYLbs) {
        this.endYLbs = endYLbs;
    }

    public Date getShuttleTime() {
        return shuttleTime;
    }

    public void setShuttleTime(Date shuttleTime) {
        this.shuttleTime = shuttleTime;
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
}