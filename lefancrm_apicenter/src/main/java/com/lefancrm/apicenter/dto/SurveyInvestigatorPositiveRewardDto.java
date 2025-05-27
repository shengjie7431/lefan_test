package com.lefancrm.apicenter.dto;

import java.util.Date;

/**
 * @author EDZ
 */
public class SurveyInvestigatorPositiveRewardDto {

    /**
     * 调查员id
     */
    private Long userId;

    /**
     * 调查员名称
     */
    private String realName;

    /**
     * 调查机构名称
     */
    private String investigationName;

    /**
     * 阳性奖励金额
     */
    private Double sunMoney;

    /**
     * 案件编号
     */
    private String surveyNo;

    /**
     * 委托机构
     */
    private String entrustedName;

    /**
     * 调查子表id
     */
    private Long surveyInfoId;

    /**
     * 发现阳性时间
     */
    private Date sunTime;

    /**
     * 保司终审通过时间
     */
    private Date entrustReportEndDate;

    /**
     * 被调查人
     */
    private String surveyPerson;

    private String surveyCaseNo;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getInvestigationName() {
        return investigationName;
    }

    public void setInvestigationName(String investigationName) {
        this.investigationName = investigationName;
    }

    public Double getSunMoney() {
        return sunMoney;
    }

    public void setSunMoney(Double sunMoney) {
        this.sunMoney = sunMoney;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getEntrustedName() {
        return entrustedName;
    }

    public void setEntrustedName(String entrustedName) {
        this.entrustedName = entrustedName;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Date getSunTime() {
        return sunTime;
    }

    public void setSunTime(Date sunTime) {
        this.sunTime = sunTime;
    }

    public Date getEntrustReportEndDate() {
        return entrustReportEndDate;
    }

    public void setEntrustReportEndDate(Date entrustReportEndDate) {
        this.entrustReportEndDate = entrustReportEndDate;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }
}
