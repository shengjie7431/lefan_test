package com.lefancrm.apicenter.model;

import java.util.Date;

public class CaseMediationReport {
    private Long id;

    private Long caseId;

    private Integer caseType;

    private String projectName;

    private Double opinionMoney;

    private Double auditingMoney;

    private String opinionMoneyDesc;

    private String auditingMoneyDesc;

    private Date createTime;

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

    public String getOpinionMoneyDesc() {
        return opinionMoneyDesc;
    }

    public void setOpinionMoneyDesc(String opinionMoneyDesc) {
        this.opinionMoneyDesc = opinionMoneyDesc;
    }

    public String getAuditingMoneyDesc() {
        return auditingMoneyDesc;
    }

    public void setAuditingMoneyDesc(String auditingMoneyDesc) {
        this.auditingMoneyDesc = auditingMoneyDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}