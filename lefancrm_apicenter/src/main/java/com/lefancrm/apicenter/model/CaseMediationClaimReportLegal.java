package com.lefancrm.apicenter.model;

public class CaseMediationClaimReportLegal {
    private Long id;

    private Long caseId;

    private Integer caseType;

    private String projectName;

    private Double opinionMoney;

    private Double auditingMoney;

    private Double icAuditingMoney;

    private Double commerMoney;

    private String checkBasis;

    public Double getCommerMoney() {
        return commerMoney;
    }

    public void setCommerMoney(Double commerMoney) {
        this.commerMoney = commerMoney;
    }

    public Double getCompulMoney() {
        return compulMoney;
    }

    public void setCompulMoney(Double compulMoney) {
        this.compulMoney = compulMoney;
    }

    private Double compulMoney;

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

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getOpinionMoney() {
        return opinionMoney;
    }

    public void setOpinionMoney(Double opinionMoney) {
        this.opinionMoney = opinionMoney;
    }

    public Double getAuditingMoney() {
        return auditingMoney;
    }

    public void setAuditingMoney(Double auditingMoney) {
        this.auditingMoney = auditingMoney;
    }

    public Double getIcAuditingMoney() {
        return icAuditingMoney;
    }

    public void setIcAuditingMoney(Double icAuditingMoney) {
        this.icAuditingMoney = icAuditingMoney;
    }

    public String getCheckBasis() {
        return checkBasis;
    }

    public void setCheckBasis(String checkBasis) {
        this.checkBasis = checkBasis;
    }
}