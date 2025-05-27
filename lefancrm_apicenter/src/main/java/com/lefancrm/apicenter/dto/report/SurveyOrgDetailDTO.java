package com.lefancrm.apicenter.dto.report;

import java.util.Date;

public class SurveyOrgDetailDTO {
    private String surveyCaseNo;        //案件编号
    private Long surveyId;
    private Long surveyInfoId;

    private Long entrustOrgId;
    private String entrustOrgName;      //委托机构
    private String surveyPerson;        //被调查人
    private String surveryPersonTel;    //联系方式
    private Integer surveyState;
    private String surveyStateName;     //案件状态
    private Date entrustTime;           //案件委托时间

    private Long surveyAssignOrgId;     //机构案件id
    private Long surveyOrgId;
    private String surveyOrgName;       //调查机构
    private Integer orgSurveyState;     //机构状态
    private String orgSurveyStateName;  //机构案件状态
    private Date orgAssignDate;         //机构分派时间
    private Date orgReportDate;         //机构提交时间
    private Date orgEndTime;            //机构截止时间
    private String orgEfficiencyState;  //机构案件时效具体状态
    private String orgEfficiencyStateColor;//机构案件时效具体状态颜色

    private int vetoNum;               //驳回次数
    private Date endTime;               //案件截止时间
    private String efficiencyState;     //案件时效具体状态
    private String efficiencyStateColor;//案件时效具体状态颜色

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Long getEntrustOrgId() {
        return entrustOrgId;
    }

    public void setEntrustOrgId(Long entrustOrgId) {
        this.entrustOrgId = entrustOrgId;
    }

    public String getEntrustOrgName() {
        return entrustOrgName;
    }

    public void setEntrustOrgName(String entrustOrgName) {
        this.entrustOrgName = entrustOrgName;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public String getSurveryPersonTel() {
        return surveryPersonTel;
    }

    public void setSurveryPersonTel(String surveryPersonTel) {
        this.surveryPersonTel = surveryPersonTel;
    }

    public Integer getSurveyState() {
        return surveyState;
    }

    public void setSurveyState(Integer surveyState) {
        this.surveyState = surveyState;
    }

    public String getSurveyStateName() {
        return surveyStateName;
    }

    public void setSurveyStateName(String surveyStateName) {
        this.surveyStateName = surveyStateName;
    }

    public Date getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(Date entrustTime) {
        this.entrustTime = entrustTime;
    }

    public Long getSurveyAssignOrgId() {
        return surveyAssignOrgId;
    }

    public void setSurveyAssignOrgId(Long surveyAssignOrgId) {
        this.surveyAssignOrgId = surveyAssignOrgId;
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

    public Integer getOrgSurveyState() {
        return orgSurveyState;
    }

    public void setOrgSurveyState(Integer orgSurveyState) {
        this.orgSurveyState = orgSurveyState;
    }

    public String getOrgSurveyStateName() {
        return orgSurveyStateName;
    }

    public void setOrgSurveyStateName(String orgSurveyStateName) {
        this.orgSurveyStateName = orgSurveyStateName;
    }

    public Date getOrgAssignDate() {
        return orgAssignDate;
    }

    public void setOrgAssignDate(Date orgAssignDate) {
        this.orgAssignDate = orgAssignDate;
    }

    public Date getOrgReportDate() {
        return orgReportDate;
    }

    public void setOrgReportDate(Date orgReportDate) {
        this.orgReportDate = orgReportDate;
    }

    public Date getOrgEndTime() {
        return orgEndTime;
    }

    public void setOrgEndTime(Date orgEndTime) {
        this.orgEndTime = orgEndTime;
    }

    public String getOrgEfficiencyState() {
        return orgEfficiencyState;
    }

    public void setOrgEfficiencyState(String orgEfficiencyState) {
        this.orgEfficiencyState = orgEfficiencyState;
    }

    public String getOrgEfficiencyStateColor() {
        return orgEfficiencyStateColor;
    }

    public void setOrgEfficiencyStateColor(String orgEfficiencyStateColor) {
        this.orgEfficiencyStateColor = orgEfficiencyStateColor;
    }

    public int getVetoNum() {
        return vetoNum;
    }

    public void setVetoNum(int vetoNum) {
        this.vetoNum = vetoNum;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEfficiencyState() {
        return efficiencyState;
    }

    public void setEfficiencyState(String efficiencyState) {
        this.efficiencyState = efficiencyState;
    }

    public String getEfficiencyStateColor() {
        return efficiencyStateColor;
    }

    public void setEfficiencyStateColor(String efficiencyStateColor) {
        this.efficiencyStateColor = efficiencyStateColor;
    }
}
