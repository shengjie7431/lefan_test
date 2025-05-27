package com.lefancrm.backend.dto;

import java.util.Date;

public class CaseCenterInfo {
    private Long id;

    private Integer type; //案件类型  1：医疗费垫付，2：赔偿款垫付，3：代办理赔，4：其他

    private Long caseId;

    private String caseNo; //案件编号

    private String caseTitle; //案件标题，合作规则 城市名+姓名+申请类型

    private String caseName;  //案件用户姓名

    private String caseTel; //案件用户手机号码

    private Integer caseState;

    private String caseStateStr;

    private String orgName;

    private Long orgId;

    private Long orgUserId;

    private String orgUserName;

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

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
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

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
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