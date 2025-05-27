package com.lefancrm.backend.dto;

import java.util.Date;

public class CaseClaimGuidanceDto {
    private Long id;

    private String caseNo;

    private Long caseId;

    private String disability;

    private String domicile;

    private String delayWork;

    private String accidentProperty;

    private String insuranceLiability;

    private String other;

    private Long guidancePersonId;

    private String guidancePersonName;

    private Integer guidanceState;

    private String guidanceReject;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getDelayWork() {
        return delayWork;
    }

    public void setDelayWork(String delayWork) {
        this.delayWork = delayWork;
    }

    public String getAccidentProperty() {
        return accidentProperty;
    }

    public void setAccidentProperty(String accidentProperty) {
        this.accidentProperty = accidentProperty;
    }

    public String getInsuranceLiability() {
        return insuranceLiability;
    }

    public void setInsuranceLiability(String insuranceLiability) {
        this.insuranceLiability = insuranceLiability;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Long getGuidancePersonId() {
        return guidancePersonId;
    }

    public void setGuidancePersonId(Long guidancePersonId) {
        this.guidancePersonId = guidancePersonId;
    }

    public String getGuidancePersonName() {
        return guidancePersonName;
    }

    public void setGuidancePersonName(String guidancePersonName) {
        this.guidancePersonName = guidancePersonName;
    }

    public Integer getGuidanceState() {
        return guidanceState;
    }

    public void setGuidanceState(Integer guidanceState) {
        this.guidanceState = guidanceState;
    }

    public String getGuidanceReject() {
        return guidanceReject;
    }

    public void setGuidanceReject(String guidanceReject) {
        this.guidanceReject = guidanceReject;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}