package com.lefancrm.apicenter.model;

public class CaseAssessmentObjReport {
    private Long id;

    private Long caseId;

    private String caseNo;

    private String projectName;

    private Double checkAmount;

    private String checkBasis;

    private Double commerAmount;

    private Double compulAmount;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(Double checkAmount) {
        this.checkAmount = checkAmount;
    }

    public String getCheckBasis() {
        return checkBasis;
    }

    public void setCheckBasis(String checkBasis) {
        this.checkBasis = checkBasis;
    }

    public Double getCommerAmount() {
        return commerAmount;
    }

    public void setCommerAmount(Double commerAmount) {
        this.commerAmount = commerAmount;
    }

    public Double getCompulAmount() {
        return compulAmount;
    }

    public void setCompulAmount(Double compulAmount) {
        this.compulAmount = compulAmount;
    }
}