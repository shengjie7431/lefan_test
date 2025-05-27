package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyServiceOrgReport {
    private Long id;

    private Date reportDate;

    private Long serviceTypeId;

    private String serviceTypeName;

    private Integer entrustCaseNum;

    private Integer checkCaseNum;

    private Long consignorOrgId;

    private String consignorOrgName;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Integer checkType; //审核类别（1.风控审核，2.保司审核）

    private Integer positiveNum;//阳性数

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
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

    public Long getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Long surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
    }

    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
    }

    public Integer getCheckType() {
        return checkType;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    public Integer getPositiveNum() {
        return positiveNum;
    }

    public void setPositiveNum(Integer positiveNum) {
        this.positiveNum = positiveNum;
    }
}