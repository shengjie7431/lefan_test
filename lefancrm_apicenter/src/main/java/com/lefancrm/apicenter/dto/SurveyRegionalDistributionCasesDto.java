package com.lefancrm.apicenter.dto;

import java.util.Date;

/**
 * 案件区域分布实体表
 * @author EDZ
 */
public class SurveyRegionalDistributionCasesDto {
    /**
     * 案件编号
     */
    private String surveyNo;

    /**
     * 案件Id
     */
    private String surveyInfoId;

    /**
     * 机构ID
     */
    private String orgId;

    /**
     * 被调查人
     */
    private String surveyPerson;

    /**
     * 互助平台
     */
    private String entrustOrgName;

    /**
     * 调查机构
     */
    private String surveyOrgName;

    /**
     * 区域
     */
    private String areaType;

    /**
     * 区域方向
     */
    private String regionType;

    /**
     * 机构案件类型
     */
    private String caseState;

    /**
     * 分派机构日期
     */
    private Date createTime;

    /**
     * 机构提交日期
     */
    private Date reportDate;

    /**
     * 机构截止日期
     */
    private Date orgEndTime;

    /**
     * 机构时效
     */
    private String agingDay;

    public String getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(String surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public String getEntrustOrgName() {
        return entrustOrgName;
    }

    public void setEntrustOrgName(String entrustOrgName) {
        this.entrustOrgName = entrustOrgName;
    }

    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getRegionType() {
        return regionType;
    }

    public void setRegionType(String regionType) {
        this.regionType = regionType;
    }

    public String getCaseState() {
        return caseState;
    }

    public void setCaseState(String caseState) {
        this.caseState = caseState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Date getOrgEndTime() {
        return orgEndTime;
    }

    public void setOrgEndTime(Date orgEndTime) {
        this.orgEndTime = orgEndTime;
    }

    public String getAgingDay() {
        return agingDay;
    }

    public void setAgingDay(String agingDay) {
        this.agingDay = agingDay;
    }
}
