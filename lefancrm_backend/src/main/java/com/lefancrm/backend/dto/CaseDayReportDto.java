package com.lefancrm.backend.dto;

import java.util.Date;

public class CaseDayReportDto {
    private Long id;

    private Integer promotersNum;

    private Integer caseEffectiveNum;

    private Integer caseSignedNum;

    private Integer paymentEstimateNum;

    private Integer invalidismEstimateNum;

    private Integer medicalFeeNum;

    private Integer paymentFeeNum;

    private Integer agentNum;

    private Long userId;

    private String userName;

    private Long orgId;

    private String orgName;

    private Date reportDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPromotersNum() {
        return promotersNum;
    }

    public void setPromotersNum(Integer promotersNum) {
        this.promotersNum = promotersNum;
    }

    public Integer getCaseEffectiveNum() {
        return caseEffectiveNum;
    }

    public void setCaseEffectiveNum(Integer caseEffectiveNum) {
        this.caseEffectiveNum = caseEffectiveNum;
    }

    public Integer getCaseSignedNum() {
        return caseSignedNum;
    }

    public void setCaseSignedNum(Integer caseSignedNum) {
        this.caseSignedNum = caseSignedNum;
    }

    public Integer getPaymentEstimateNum() {
        return paymentEstimateNum;
    }

    public void setPaymentEstimateNum(Integer paymentEstimateNum) {
        this.paymentEstimateNum = paymentEstimateNum;
    }

    public Integer getInvalidismEstimateNum() {
        return invalidismEstimateNum;
    }

    public void setInvalidismEstimateNum(Integer invalidismEstimateNum) {
        this.invalidismEstimateNum = invalidismEstimateNum;
    }

    public Integer getMedicalFeeNum() {
        return medicalFeeNum;
    }

    public void setMedicalFeeNum(Integer medicalFeeNum) {
        this.medicalFeeNum = medicalFeeNum;
    }

    public Integer getPaymentFeeNum() {
        return paymentFeeNum;
    }

    public void setPaymentFeeNum(Integer paymentFeeNum) {
        this.paymentFeeNum = paymentFeeNum;
    }

    public Integer getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(Integer agentNum) {
        this.agentNum = agentNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
}