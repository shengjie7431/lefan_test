package com.lefancrm.apicenter.fina.model;

import java.util.Date;
import java.util.List;

public class FinaSettlementTrack {
    private Long id;

    private Long settlementInfoId;

    private String trackDesc;

    private Date trackFtime;

    private Long trackUserId;

    private String trackUserName;

    private Date nextTrackTime;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;


    private List<FinaFile> finaFiles;

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

    public String getTrackDesc() {
        return trackDesc;
    }

    public void setTrackDesc(String trackDesc) {
        this.trackDesc = trackDesc;
    }

    public Date getTrackFtime() {
        return trackFtime;
    }

    public void setTrackFtime(Date trackFtime) {
        this.trackFtime = trackFtime;
    }

    public Long getTrackUserId() {
        return trackUserId;
    }

    public void setTrackUserId(Long trackUserId) {
        this.trackUserId = trackUserId;
    }

    public String getTrackUserName() {
        return trackUserName;
    }

    public void setTrackUserName(String trackUserName) {
        this.trackUserName = trackUserName;
    }

    public Date getNextTrackTime() {
        return nextTrackTime;
    }

    public void setNextTrackTime(Date nextTrackTime) {
        this.nextTrackTime = nextTrackTime;
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
}