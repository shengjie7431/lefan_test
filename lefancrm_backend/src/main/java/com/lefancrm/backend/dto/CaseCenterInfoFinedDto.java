package com.lefancrm.backend.dto;

import java.util.Date;

public class CaseCenterInfoFinedDto {
    private Long id;

    private Long caseId;

    private String caseNo;

    private Integer caseType;

    private Integer gradationState;

    private Integer caseState;

    private String caseTitle;

    private String caseName;

    private String orgName;

    private Long orgId;

    private Integer isFined;

    private Integer finedType;

    private Double finedMoney;

    private Long finedById;

    private String finedBy;

    private Date finedTime;

    private String finedTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public Integer getGradationState() {
        return gradationState;
    }

    public void setGradationState(Integer gradationState) {
        this.gradationState = gradationState;
    }

    public Integer getCaseState() {
        return caseState;
    }

    public void setCaseState(Integer caseState) {
        this.caseState = caseState;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Integer getIsFined() {
        return isFined;
    }

    public void setIsFined(Integer isFined) {
        this.isFined = isFined;
    }

    public Integer getFinedType() {
        return finedType;
    }

    public void setFinedType(Integer finedType) {
        this.finedType = finedType;
    }

    public Double getFinedMoney() {
        return finedMoney;
    }

    public void setFinedMoney(Double finedMoney) {
        this.finedMoney = finedMoney;
    }

    public Long getFinedById() {
        return finedById;
    }

    public void setFinedById(Long finedById) {
        this.finedById = finedById;
    }

    public String getFinedBy() {
        return finedBy;
    }

    public void setFinedBy(String finedBy) {
        this.finedBy = finedBy;
    }

    public Date getFinedTime() {
        return finedTime;
    }

    public void setFinedTime(Date finedTime) {
        this.finedTime = finedTime;
    }

    public String getFinedTypeName() {
        return finedTypeName;
    }

    public void setFinedTypeName(String finedTypeName) {
        this.finedTypeName = finedTypeName;
    }
}