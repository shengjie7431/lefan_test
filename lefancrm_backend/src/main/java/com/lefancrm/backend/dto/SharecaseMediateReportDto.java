package com.lefancrm.backend.dto;

import java.util.Date;

public class SharecaseMediateReportDto {
    private Long id;

    private Long caseId;

    private String projectName;

    private Double opinionMoney;

    private Integer opinionState;

    private Double auditingMoney;

    private Integer auditingState;

    private Double proposalMoney;

    private Integer proposalState;

    private String opinionMoneyDesc;

    private String auditingMoneyDesc;

    private String proposalMoneyDesc;

    private Date createTime;

    private String createBy;

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

    public Integer getOpinionState() {
        return opinionState;
    }

    public void setOpinionState(Integer opinionState) {
        this.opinionState = opinionState;
    }

    public Double getAuditingMoney() {
        return auditingMoney;
    }

    public void setAuditingMoney(Double auditingMoney) {
        this.auditingMoney = auditingMoney;
    }

    public Integer getAuditingState() {
        return auditingState;
    }

    public void setAuditingState(Integer auditingState) {
        this.auditingState = auditingState;
    }

    public Double getProposalMoney() {
        return proposalMoney;
    }

    public void setProposalMoney(Double proposalMoney) {
        this.proposalMoney = proposalMoney;
    }

    public Integer getProposalState() {
        return proposalState;
    }

    public void setProposalState(Integer proposalState) {
        this.proposalState = proposalState;
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

    public String getProposalMoneyDesc() {
        return proposalMoneyDesc;
    }

    public void setProposalMoneyDesc(String proposalMoneyDesc) {
        this.proposalMoneyDesc = proposalMoneyDesc;
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
}