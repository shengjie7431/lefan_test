package com.lefancrm.backend.dto;

public class CaseClosedObjReportDto {
    private Long id;

    private Long caseId;

    private String caseNo;

    private Long closedReportId;

    private String objectName;

    private Double opinionMoney;

    private Double insOmpanyMoney;

    private Double driverMoney;

    private Double commerMoney;

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

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public Long getClosedReportId() {
        return closedReportId;
    }

    public void setClosedReportId(Long closedReportId) {
        this.closedReportId = closedReportId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Double getOpinionMoney() {
        return opinionMoney;
    }

    public void setOpinionMoney(Double opinionMoney) {
        this.opinionMoney = opinionMoney;
    }

    public Double getInsOmpanyMoney() {
        return insOmpanyMoney;
    }

    public void setInsOmpanyMoney(Double insOmpanyMoney) {
        this.insOmpanyMoney = insOmpanyMoney;
    }

    public Double getDriverMoney() {
        return driverMoney;
    }

    public void setDriverMoney(Double driverMoney) {
        this.driverMoney = driverMoney;
    }

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
}