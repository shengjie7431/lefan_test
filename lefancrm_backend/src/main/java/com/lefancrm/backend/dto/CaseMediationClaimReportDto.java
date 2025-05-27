package com.lefancrm.backend.dto;

public class CaseMediationClaimReportDto {
    private Long id;

    private Long caseId;

    private Integer caseType;

    private String projectName;

    private Double opinionMoney;

    private Double auditingMoney;

    private Double icAuditingMoney;

    private String checkBasis;

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