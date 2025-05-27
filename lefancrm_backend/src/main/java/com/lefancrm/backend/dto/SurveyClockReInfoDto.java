package com.lefancrm.backend.dto;

import java.util.Date;

public class SurveyClockReInfoDto {
    private Long id;

    private Long clockId;

    private Double cityinDrivingMoney;

    private Double medicalHistoryMoney;

    private Double troubleshootingMoney;

    private Double printingMoney;

    private Double accommodatioMoney;

    private Double crossDrivingMoney;

    private Double selfDrivingMoney;

    private Double otherMoney;

    private String clockDesc;

    private Date createTime;

    private Date createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClockId() {
        return clockId;
    }

    public void setClockId(Long clockId) {
        this.clockId = clockId;
    }

    public Double getCityinDrivingMoney() {
        return cityinDrivingMoney;
    }

    public void setCityinDrivingMoney(Double cityinDrivingMoney) {
        this.cityinDrivingMoney = cityinDrivingMoney;
    }

    public Double getMedicalHistoryMoney() {
        return medicalHistoryMoney;
    }

    public void setMedicalHistoryMoney(Double medicalHistoryMoney) {
        this.medicalHistoryMoney = medicalHistoryMoney;
    }

    public Double getTroubleshootingMoney() {
        return troubleshootingMoney;
    }

    public void setTroubleshootingMoney(Double troubleshootingMoney) {
        this.troubleshootingMoney = troubleshootingMoney;
    }

    public Double getPrintingMoney() {
        return printingMoney;
    }

    public void setPrintingMoney(Double printingMoney) {
        this.printingMoney = printingMoney;
    }

    public Double getAccommodatioMoney() {
        return accommodatioMoney;
    }

    public void setAccommodatioMoney(Double accommodatioMoney) {
        this.accommodatioMoney = accommodatioMoney;
    }

    public Double getCrossDrivingMoney() {
        return crossDrivingMoney;
    }

    public void setCrossDrivingMoney(Double crossDrivingMoney) {
        this.crossDrivingMoney = crossDrivingMoney;
    }

    public Double getSelfDrivingMoney() {
        return selfDrivingMoney;
    }

    public void setSelfDrivingMoney(Double selfDrivingMoney) {
        this.selfDrivingMoney = selfDrivingMoney;
    }

    public Double getOtherMoney() {
        return otherMoney;
    }

    public void setOtherMoney(Double otherMoney) {
        this.otherMoney = otherMoney;
    }

    public String getClockDesc() {
        return clockDesc;
    }

    public void setClockDesc(String clockDesc) {
        this.clockDesc = clockDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }
}