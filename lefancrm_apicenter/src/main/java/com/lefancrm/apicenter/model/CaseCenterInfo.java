package com.lefancrm.apicenter.model;

import java.util.Date;

public class CaseCenterInfo {
    private Long id;

    private Integer type;

    private Long caseId;

    private String caseNo;

    private String caseTitle;

    private String caseName;

    private String caseTel;

    private Integer caseState;

    private String caseStateStr;

    private String orgName;

    private Long orgId;

    private Long orgUserId;

    private String orgUserName;

    private String cOrgName;

    private Long cOrgUserId;

    private Integer cOrgType;

    private Date createTime;

    private Long createBy;

    private Long salesmanId;

    private String salesmanName;

    private Integer gradationState;

    private Long insOfficerId;

    private String insOfficerName;

    private Long operatorId;

    private String operatorName;

    private Long assessId;

    private String assessName;

    private Long claimantId;

    private String claimantName;

    private Long insuranceCompanyId;

    private String insuranceCompany;

    private Date dangerTime;

    private String insured;

    private String carNo;

    private Integer isRead;

    private Integer insIsRead;

    private Long cardId;

    private String bankCardNo;

    private Integer releaseState;

    private String releaseReason;

    private Integer issuanceState;

    private String issuanceReason;

    private Integer claimState;

    private String claimReason;

    private Integer isFeedback;

    private Integer closedState;

    private String closedReason;

    private Date distributionTime;

    private Date updateTime;

    private Integer negotiateState;

    private String negotiateReason;

    private Date handOutTime;

    private String handOutFlag;

    private Date handInTime;

    private String handInFlag;

    private Integer closeReportState;

    //2018年5月8日10:03:13 增加 同意委托时间 列表状态 审核原因
    private Date agreeSignTime;
    private Integer listState;
    private String listStateName;
    private String operReason;
    private Double insuredAmount;

    private Long legalUserId;//诉讼员ID
    private String legalUserName;

    private Integer isTestcase;//是否测试案件

    private Integer deleteFlag;//删除标识

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getCaseTel() {
        return caseTel;
    }

    public void setCaseTel(String caseTel) {
        this.caseTel = caseTel;
    }

    public Integer getCaseState() {
        return caseState;
    }

    public void setCaseState(Integer caseState) {
        this.caseState = caseState;
    }

    public String getCaseStateStr() {
        return caseStateStr;
    }

    public void setCaseStateStr(String caseStateStr) {
        this.caseStateStr = caseStateStr;
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

    public Long getOrgUserId() {
        return orgUserId;
    }

    public void setOrgUserId(Long orgUserId) {
        this.orgUserId = orgUserId;
    }

    public String getOrgUserName() {
        return orgUserName;
    }

    public void setOrgUserName(String orgUserName) {
        this.orgUserName = orgUserName;
    }

    public String getcOrgName() {
        return cOrgName;
    }

    public void setcOrgName(String cOrgName) {
        this.cOrgName = cOrgName;
    }

    public Long getcOrgUserId() {
        return cOrgUserId;
    }

    public void setcOrgUserId(Long cOrgUserId) {
        this.cOrgUserId = cOrgUserId;
    }

    public Integer getcOrgType() {
        return cOrgType;
    }

    public void setcOrgType(Integer cOrgType) {
        this.cOrgType = cOrgType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public Integer getGradationState() {
        return gradationState;
    }

    public void setGradationState(Integer gradationState) {
        this.gradationState = gradationState;
    }

    public Long getInsOfficerId() {
        return insOfficerId;
    }

    public void setInsOfficerId(Long insOfficerId) {
        this.insOfficerId = insOfficerId;
    }

    public String getInsOfficerName() {
        return insOfficerName;
    }

    public void setInsOfficerName(String insOfficerName) {
        this.insOfficerName = insOfficerName;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Long getAssessId() {
        return assessId;
    }

    public void setAssessId(Long assessId) {
        this.assessId = assessId;
    }

    public String getAssessName() {
        return assessName;
    }

    public void setAssessName(String assessName) {
        this.assessName = assessName;
    }

    public Long getClaimantId() {
        return claimantId;
    }

    public void setClaimantId(Long claimantId) {
        this.claimantId = claimantId;
    }

    public String getClaimantName() {
        return claimantName;
    }

    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    public Long getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public void setInsuranceCompanyId(Long insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public Date getDangerTime() {
        return dangerTime;
    }

    public void setDangerTime(Date dangerTime) {
        this.dangerTime = dangerTime;
    }

    public String getInsured() {
        return insured;
    }

    public void setInsured(String insured) {
        this.insured = insured;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getInsIsRead() {
        return insIsRead;
    }

    public void setInsIsRead(Integer insIsRead) {
        this.insIsRead = insIsRead;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public Integer getReleaseState() {
        return releaseState;
    }

    public void setReleaseState(Integer releaseState) {
        this.releaseState = releaseState;
    }

    public String getReleaseReason() {
        return releaseReason;
    }

    public void setReleaseReason(String releaseReason) {
        this.releaseReason = releaseReason;
    }

    public Integer getIssuanceState() {
        return issuanceState;
    }

    public void setIssuanceState(Integer issuanceState) {
        this.issuanceState = issuanceState;
    }

    public String getIssuanceReason() {
        return issuanceReason;
    }

    public void setIssuanceReason(String issuanceReason) {
        this.issuanceReason = issuanceReason;
    }

    public Integer getClaimState() {
        return claimState;
    }

    public void setClaimState(Integer claimState) {
        this.claimState = claimState;
    }

    public String getClaimReason() {
        return claimReason;
    }

    public void setClaimReason(String claimReason) {
        this.claimReason = claimReason;
    }

    public Integer getIsFeedback() {
        return isFeedback;
    }

    public void setIsFeedback(Integer isFeedback) {
        this.isFeedback = isFeedback;
    }

    public Integer getClosedState() {
        return closedState;
    }

    public void setClosedState(Integer closedState) {
        this.closedState = closedState;
    }

    public String getClosedReason() {
        return closedReason;
    }

    public void setClosedReason(String closedReason) {
        this.closedReason = closedReason;
    }

    public Date getDistributionTime() {
        return distributionTime;
    }

    public void setDistributionTime(Date distributionTime) {
        this.distributionTime = distributionTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getNegotiateState() {
        return negotiateState;
    }

    public void setNegotiateState(Integer negotiateState) {
        this.negotiateState = negotiateState;
    }

    public String getNegotiateReason() {
        return negotiateReason;
    }

    public void setNegotiateReason(String negotiateReason) {
        this.negotiateReason = negotiateReason;
    }

    public Date getHandOutTime() {
        return handOutTime;
    }

    public void setHandOutTime(Date handOutTime) {
        this.handOutTime = handOutTime;
    }

    public String getHandOutFlag() {
        return handOutFlag;
    }

    public void setHandOutFlag(String handOutFlag) {
        this.handOutFlag = handOutFlag;
    }

    public Date getHandInTime() {
        return handInTime;
    }

    public void setHandInTime(Date handInTime) {
        this.handInTime = handInTime;
    }

    public String getHandInFlag() {
        return handInFlag;
    }

    public void setHandInFlag(String handInFlag) {
        this.handInFlag = handInFlag;
    }

    public Integer getCloseReportState() {
        return closeReportState;
    }

    public void setCloseReportState(Integer closeReportState) {
        this.closeReportState = closeReportState;
    }

    public Date getAgreeSignTime() {
        return agreeSignTime;
    }

    public void setAgreeSignTime(Date agreeSignTime) {
        this.agreeSignTime = agreeSignTime;
    }

    public Integer getListState() {
        return listState;
    }

    public void setListState(Integer listState) {
        this.listState = listState;
    }

    public String getListStateName() {
        return listStateName;
    }

    public void setListStateName(String listStateName) {
        this.listStateName = listStateName;
    }

    public String getOperReason() {
        return operReason;
    }

    public void setOperReason(String operReason) {
        this.operReason = operReason;
    }

    public Double getInsuredAmount() {
        return insuredAmount;
    }

    public void setInsuredAmount(Double insuredAmount) {
        this.insuredAmount = insuredAmount;
    }

    public Long getLegalUserId() {
        return legalUserId;
    }

    public void setLegalUserId(Long legalUserId) {
        this.legalUserId = legalUserId;
    }

    public String getLegalUserName() {
        return legalUserName;
    }

    public void setLegalUserName(String legalUserName) {
        this.legalUserName = legalUserName;
    }

    public Integer getIsTestcase() {
        return isTestcase;
    }

    public void setIsTestcase(Integer isTestcase) {
        this.isTestcase = isTestcase;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}