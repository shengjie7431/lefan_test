package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

public class SurveyTaskReport {
    private Long id;

    private Date reportDate;

    private Long consignorOrgId;

    private String consignorOrgName;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Long surveyUserId;

    private String surveyUserName;

    private Long parentId;

    private String parentName;

    private Long taskId;

    private String taskName;

    private Integer entrustCaseNum;

    private Integer searchType;

    private Integer orgAttr;

    //任务子类列表
    List<SurveyTaskReport> surveyTaskReports;

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

    public Long getSurveyUserId() {
        return surveyUserId;
    }

    public void setSurveyUserId(Long surveyUserId) {
        this.surveyUserId = surveyUserId;
    }

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public List<SurveyTaskReport> getSurveyTaskReports() {
        return surveyTaskReports;
    }

    public void setSurveyTaskReports(List<SurveyTaskReport> surveyTaskReports) {
        this.surveyTaskReports = surveyTaskReports;
    }

    public Integer getOrgAttr() {
        return orgAttr;
    }

    public void setOrgAttr(Integer orgAttr) {
        this.orgAttr = orgAttr;
    }
}