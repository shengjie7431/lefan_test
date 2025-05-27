package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyCrossRegionReport {
    private Long id;

    private Date reportDate;

    private Long consignorOrgId;

    private String consignorOrgName;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Integer areaLevel;

    private Integer entrustCaseNum;

    private Integer searchType;


    //非数据库字段
    private String areaLevelName;

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

    public Integer getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }

    public Integer getEntrustCaseNum() {
        return entrustCaseNum;
    }

    public void setEntrustCaseNum(Integer entrustCaseNum) {
        this.entrustCaseNum = entrustCaseNum;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public String getAreaLevelName() {
        return areaLevelName;
    }

    public void setAreaLevelName(String areaLevelName) {
        this.areaLevelName = areaLevelName;
    }
}