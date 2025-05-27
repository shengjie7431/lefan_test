package com.lefancrm.apicenter.model;

import com.lefancrm.apicenter.dto.SurveyReimbursementFileDto;

import java.util.Date;

public class SurveyReimbursementInfo {
    private Long id;

    private Long surveyInfoId;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Long surveyUserId;

    private String surveyUserName;

    private Long surveyDirectionId;

    private Double medicalHistoryMoney;

    private String investigatorCaseId;

    private Double troubleshootingMoney;

    private Double printingMoney;

    private Double accommodatioMoney;

    private Double trainMoney;

    private Double carMoney;

    private Double aircraftMoney;

    private Double selfDrivingMoney;

    private Double otherMoney;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private String medicalHistoryDesc;

    private String troubleshootingDesc;

    private String printingDesc;

    private String accommodatioDesc;

    private String trainDesc;

    private String carDesc;

    private String aircraftDesc;

    private String selfDrivingDesc;

    private String otherDesc;

    private Double kilometresNum;

    private Integer accommodatioDays;

    private Double innerCityMoney;

    private String innerCityDesc;

    private Double tollMoney;

    public Double getTollMoney() {
        return tollMoney;
    }

    public void setTollMoney(Double tollMoney) {
        this.tollMoney = tollMoney;
    }

    public Double getInnerCityMoney() {
        return innerCityMoney;
    }

    public void setInnerCityMoney(Double innerCityMoney) {
        this.innerCityMoney = innerCityMoney;
    }

    public String getInnerCityDesc() {
        return innerCityDesc;
    }

    public void setInnerCityDesc(String innerCityDesc) {
        this.innerCityDesc = innerCityDesc;
    }

    public Integer getAccommodatioDays() {
        return accommodatioDays;
    }

    public void setAccommodatioDays(Integer accommodatioDays) {
        this.accommodatioDays = accommodatioDays;
    }

    private SurveyReimbursementFileDto surveyReimbursementFileDto;

    public SurveyReimbursementFileDto getSurveyReimbursementFileDto() {
        return surveyReimbursementFileDto;
    }

    public void setSurveyReimbursementFileDto(SurveyReimbursementFileDto surveyReimbursementFileDto) {
        this.surveyReimbursementFileDto = surveyReimbursementFileDto;
    }

    public Double getKilometresNum() {
        return kilometresNum;
    }

    public void setKilometresNum(Double kilometresNum) {
        this.kilometresNum = kilometresNum;
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

    public Long getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Long surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
    }

    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
    }

    public Long getSurveyUserId() {
        return surveyUserId;
    }

    public void setSurveyUserId(Long surveyUserId) {
        this.surveyUserId = surveyUserId;
    }

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
    }

    public Long getSurveyDirectionId() {
        return surveyDirectionId;
    }

    public void setSurveyDirectionId(Long surveyDirectionId) {
        this.surveyDirectionId = surveyDirectionId;
    }

    public Double getMedicalHistoryMoney() {
        return medicalHistoryMoney;
    }

    public void setMedicalHistoryMoney(Double medicalHistoryMoney) {
        this.medicalHistoryMoney = medicalHistoryMoney;
    }

    public String getInvestigatorCaseId() {
        return investigatorCaseId;
    }

    public void setInvestigatorCaseId(String investigatorCaseId) {
        this.investigatorCaseId = investigatorCaseId;
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

    public Double getTrainMoney() {
        return trainMoney;
    }

    public void setTrainMoney(Double trainMoney) {
        this.trainMoney = trainMoney;
    }

    public Double getCarMoney() {
        return carMoney;
    }

    public void setCarMoney(Double carMoney) {
        this.carMoney = carMoney;
    }

    public Double getAircraftMoney() {
        return aircraftMoney;
    }

    public void setAircraftMoney(Double aircraftMoney) {
        this.aircraftMoney = aircraftMoney;
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

    public String getMedicalHistoryDesc() {
        return medicalHistoryDesc;
    }

    public void setMedicalHistoryDesc(String medicalHistoryDesc) {
        this.medicalHistoryDesc = medicalHistoryDesc;
    }

    public String getTroubleshootingDesc() {
        return troubleshootingDesc;
    }

    public void setTroubleshootingDesc(String troubleshootingDesc) {
        this.troubleshootingDesc = troubleshootingDesc;
    }

    public String getPrintingDesc() {
        return printingDesc;
    }

    public void setPrintingDesc(String printingDesc) {
        this.printingDesc = printingDesc;
    }

    public String getAccommodatioDesc() {
        return accommodatioDesc;
    }

    public void setAccommodatioDesc(String accommodatioDesc) {
        this.accommodatioDesc = accommodatioDesc;
    }

    public String getTrainDesc() {
        return trainDesc;
    }

    public void setTrainDesc(String trainDesc) {
        this.trainDesc = trainDesc;
    }

    public String getCarDesc() {
        return carDesc;
    }

    public void setCarDesc(String carDesc) {
        this.carDesc = carDesc;
    }

    public String getAircraftDesc() {
        return aircraftDesc;
    }

    public void setAircraftDesc(String aircraftDesc) {
        this.aircraftDesc = aircraftDesc;
    }

    public String getSelfDrivingDesc() {
        return selfDrivingDesc;
    }

    public void setSelfDrivingDesc(String selfDrivingDesc) {
        this.selfDrivingDesc = selfDrivingDesc;
    }

    public String getOtherDesc() {
        return otherDesc;
    }

    public void setOtherDesc(String otherDesc) {
        this.otherDesc = otherDesc;
    }
}