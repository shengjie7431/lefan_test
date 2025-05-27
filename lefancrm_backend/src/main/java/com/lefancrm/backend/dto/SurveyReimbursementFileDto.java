package com.lefancrm.backend.dto;

import java.util.Date;

public class SurveyReimbursementFileDto {
    private Long id;

    private Long surveyInfoId;

    private Long reimbursementId;

    private String fileCode;

    private String fileUrl;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
    private int medhCount;
    private int trouCount;
    private int printCount;
    private int accoCount;
    private int carCount;
    private int trainCount;
    private int aircraftCount;
    private int selfCount;
    private int otherCount;
    private int innerCount;

    public int getInnerCount() {
        return innerCount;
    }

    public void setInnerCount(int innerCount) {
        this.innerCount = innerCount;
    }

    public int getMedhCount() {
        return medhCount;
    }

    public void setMedhCount(int medhCount) {
        this.medhCount = medhCount;
    }

    public int getTrouCount() {
        return trouCount;
    }

    public void setTrouCount(int trouCount) {
        this.trouCount = trouCount;
    }

    public int getPrintCount() {
        return printCount;
    }

    public void setPrintCount(int printCount) {
        this.printCount = printCount;
    }

    public int getAccoCount() {
        return accoCount;
    }

    public void setAccoCount(int accoCount) {
        this.accoCount = accoCount;
    }

    public int getCarCount() {
        return carCount;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    public int getTrainCount() {
        return trainCount;
    }

    public void setTrainCount(int trainCount) {
        this.trainCount = trainCount;
    }

    public int getAircraftCount() {
        return aircraftCount;
    }

    public void setAircraftCount(int aircraftCount) {
        this.aircraftCount = aircraftCount;
    }

    public int getSelfCount() {
        return selfCount;
    }

    public void setSelfCount(int selfCount) {
        this.selfCount = selfCount;
    }

    public int getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(int otherCount) {
        this.otherCount = otherCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Long getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(Long reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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
}