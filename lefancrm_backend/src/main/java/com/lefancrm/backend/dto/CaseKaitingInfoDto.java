package com.lefancrm.backend.dto;

import java.util.Date;

public class CaseKaitingInfoDto {
    private Long id;

    private Long caseCenterId;

    private Date kaitingTime;

    private String createBy;

    private Date createTime;

    private Integer deleteFlag;

    private String kaitingPlace;

    private String kaitingContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseCenterId() {
        return caseCenterId;
    }

    public void setCaseCenterId(Long caseCenterId) {
        this.caseCenterId = caseCenterId;
    }

    public Date getKaitingTime() {
        return kaitingTime;
    }

    public void setKaitingTime(Date kaitingTime) {
        this.kaitingTime = kaitingTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getKaitingPlace() {
        return kaitingPlace;
    }

    public void setKaitingPlace(String kaitingPlace) {
        this.kaitingPlace = kaitingPlace;
    }

    public String getKaitingContent() {
        return kaitingContent;
    }

    public void setKaitingContent(String kaitingContent) {
        this.kaitingContent = kaitingContent;
    }
}