package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyCaseArchives {
    private Long id;

    private Long surveyId;

    private Long surveyInfoId;

    private String surveyNo;

    private String surveyCaseNo;

    private Integer interviewRecord;

    private Integer medicalRecord;

    private Integer medicalReport;

    private Integer socialInsurance;

    private Integer isDeath;

    private Integer legalHeir;

    private Integer legalHeirRelationship;

    private Integer deathCertificate;

    private Integer isGiveUp;

    private Integer claimsGiveUp;

    private Integer otherStatement;

    private Integer isAccident;

    private Integer anAccident;

    private Integer noAccident;

    private Integer publicInspection;

    private Integer appraisalReport;

    private Integer legalInstrument;

    private Integer otherItems;

    private Date createTime;

    private String createBy;

    private Integer archivesState;

    private Date archivesTime;

    private Integer deleteFlag;

    private String archivesBy;

    private Date entrustReportStartDate;//平台复审时间
    private Date entrustReportEndDate;//保司终审时间
    private String surveyPerson;//被调查人
    private String entrustOrgName;//委托方机构
    private String surveyOrgName;//调查方机构

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }

    public Integer getInterviewRecord() {
        return interviewRecord;
    }

    public void setInterviewRecord(Integer interviewRecord) {
        this.interviewRecord = interviewRecord;
    }

    public Integer getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(Integer medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Integer getSocialInsurance() {
        return socialInsurance;
    }

    public void setSocialInsurance(Integer socialInsurance) {
        this.socialInsurance = socialInsurance;
    }

    public Integer getIsDeath() {
        return isDeath;
    }

    public void setIsDeath(Integer isDeath) {
        this.isDeath = isDeath;
    }

    public Integer getLegalHeir() {
        return legalHeir;
    }

    public void setLegalHeir(Integer legalHeir) {
        this.legalHeir = legalHeir;
    }

    public Integer getLegalHeirRelationship() {
        return legalHeirRelationship;
    }

    public void setLegalHeirRelationship(Integer legalHeirRelationship) {
        this.legalHeirRelationship = legalHeirRelationship;
    }

    public Integer getDeathCertificate() {
        return deathCertificate;
    }

    public void setDeathCertificate(Integer deathCertificate) {
        this.deathCertificate = deathCertificate;
    }

    public Integer getIsGiveUp() {
        return isGiveUp;
    }

    public void setIsGiveUp(Integer isGiveUp) {
        this.isGiveUp = isGiveUp;
    }

    public Integer getClaimsGiveUp() {
        return claimsGiveUp;
    }

    public void setClaimsGiveUp(Integer claimsGiveUp) {
        this.claimsGiveUp = claimsGiveUp;
    }

    public Integer getOtherStatement() {
        return otherStatement;
    }

    public void setOtherStatement(Integer otherStatement) {
        this.otherStatement = otherStatement;
    }

    public Integer getIsAccident() {
        return isAccident;
    }

    public void setIsAccident(Integer isAccident) {
        this.isAccident = isAccident;
    }

    public Integer getAnAccident() {
        return anAccident;
    }

    public void setAnAccident(Integer anAccident) {
        this.anAccident = anAccident;
    }

    public Integer getNoAccident() {
        return noAccident;
    }

    public void setNoAccident(Integer noAccident) {
        this.noAccident = noAccident;
    }

    public Integer getPublicInspection() {
        return publicInspection;
    }

    public void setPublicInspection(Integer publicInspection) {
        this.publicInspection = publicInspection;
    }

    public Integer getAppraisalReport() {
        return appraisalReport;
    }

    public void setAppraisalReport(Integer appraisalReport) {
        this.appraisalReport = appraisalReport;
    }

    public Integer getLegalInstrument() {
        return legalInstrument;
    }

    public void setLegalInstrument(Integer legalInstrument) {
        this.legalInstrument = legalInstrument;
    }

    public Integer getOtherItems() {
        return otherItems;
    }

    public void setOtherItems(Integer otherItems) {
        this.otherItems = otherItems;
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

    public Integer getArchivesState() {
        return archivesState;
    }

    public void setArchivesState(Integer archivesState) {
        this.archivesState = archivesState;
    }

    public Date getArchivesTime() {
        return archivesTime;
    }

    public void setArchivesTime(Date archivesTime) {
        this.archivesTime = archivesTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getEntrustReportEndDate() {
        return entrustReportEndDate;
    }

    public void setEntrustReportEndDate(Date entrustReportEndDate) {
        this.entrustReportEndDate = entrustReportEndDate;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public String getEntrustOrgName() {
        return entrustOrgName;
    }

    public void setEntrustOrgName(String entrustOrgName) {
        this.entrustOrgName = entrustOrgName;
    }

    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
    }

    public String getArchivesBy() {
        return archivesBy;
    }

    public void setArchivesBy(String archivesBy) {
        this.archivesBy = archivesBy;
    }

    public Integer getMedicalReport() {
        return medicalReport;
    }

    public void setMedicalReport(Integer medicalReport) {
        this.medicalReport = medicalReport;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getEntrustReportStartDate() {
        return entrustReportStartDate;
    }

    public void setEntrustReportStartDate(Date entrustReportStartDate) {
        this.entrustReportStartDate = entrustReportStartDate;
    }
}