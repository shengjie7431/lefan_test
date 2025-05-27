package com.lefancrm.apicenter.model;

public class SurveyCaseAttributesReport {
    private Long id;

    private Long reportDate;

    private Long businessAttributesId;

    private String businessAttributesName;

    private Integer entrustCaseNum;

    private Integer checkCaseNum;

    private Long consignorOrgId;

    private String consignorOrgName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReportDate() {
        return reportDate;
    }

    public void setReportDate(Long reportDate) {
        this.reportDate = reportDate;
    }

    public Long getBusinessAttributesId() {
        return businessAttributesId;
    }

    public void setBusinessAttributesId(Long businessAttributesId) {
        this.businessAttributesId = businessAttributesId;
    }

    public String getBusinessAttributesName() {
        return businessAttributesName;
    }

    public void setBusinessAttributesName(String businessAttributesName) {
        this.businessAttributesName = businessAttributesName;
    }

    public Integer getEntrustCaseNum() {
        return entrustCaseNum;
    }

    public void setEntrustCaseNum(Integer entrustCaseNum) {
        this.entrustCaseNum = entrustCaseNum;
    }

    public Integer getCheckCaseNum() {
        return checkCaseNum;
    }

    public void setCheckCaseNum(Integer checkCaseNum) {
        this.checkCaseNum = checkCaseNum;
    }

    public Long getConsignorOrgId() {
        return consignorOrgId;
    }

    public void setConsignorOrgId(Long consignorOrgId) {
        this.consignorOrgId = consignorOrgId;
    }

    public String getConsignorOrgName() {
        return consignorOrgName;
    }

    public void setConsignorOrgName(String consignorOrgName) {
        this.consignorOrgName = consignorOrgName;
    }
}