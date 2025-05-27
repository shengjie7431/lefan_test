package com.lefancrm.apicenter.dto.hzReport;

import com.lefancrm.apicenter.model.SurveyAssignOrgExtend;
import com.lefancrm.apicenter.model.SurveyCheckPreFlow;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OprUserDataItemDTO {
    private Long surveyAssorgCaseId;
    private Long surveyInfoId;
    private String surveyCaseNo;
    private String surveyPerson;
    private String entrustOrgName;
    private String orgName;
    private String orgCaseState;
    private String surveyItem;
    private String orgCaseType;

    private Date assignDate;
    private Date commitDate;
    private Date orgEndTime;
    private String assignDateStr;
    private String commitDateStr;
    private String orgEndTimeStr;
    private String agingOrg;
    private Boolean veto;
    private Boolean sun;

    private Date reviewTime;
    private Date reviewEndTime;
    private String reviewTimeStr;
    private String reviewEndTimeStr;
    private String agingReview;
    private Double basScore;
    private Double sunScore;
    private Double totalScore;

    private Date entrustReportEndDate;
    private String entrustReportEndDateStr;

    private Long agingReviewTwo; //毫秒级时间戳

    private List<Map<String,Object>> surveyCheckMapList;

    private SurveyAssignOrgExtend surveyAssignOrgExtend;

    public Long getSurveyAssorgCaseId() {
        return surveyAssorgCaseId;
    }

    public void setSurveyAssorgCaseId(Long surveyAssorgCaseId) {
        this.surveyAssorgCaseId = surveyAssorgCaseId;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCaseState() {
        return orgCaseState;
    }

    public void setOrgCaseState(String orgCaseState) {
        this.orgCaseState = orgCaseState;
    }

    public String getSurveyItem() {
        return surveyItem;
    }

    public void setSurveyItem(String surveyItem) {
        this.surveyItem = surveyItem;
    }

    public String getOrgCaseType() {
        return orgCaseType;
    }

    public void setOrgCaseType(String orgCaseType) {
        this.orgCaseType = orgCaseType;
    }

    public String getAssignDateStr() {
        return assignDateStr;
    }

    public void setAssignDateStr(String assignDateStr) {
        this.assignDateStr = assignDateStr;
    }

    public String getCommitDateStr() {
        return commitDateStr;
    }

    public void setCommitDateStr(String commitDateStr) {
        this.commitDateStr = commitDateStr;
    }

    public String getOrgEndTimeStr() {
        return orgEndTimeStr;
    }

    public void setOrgEndTimeStr(String orgEndTimeStr) {
        this.orgEndTimeStr = orgEndTimeStr;
    }

    public String getAgingOrg() {
        return agingOrg;
    }

    public void setAgingOrg(String agingOrg) {
        this.agingOrg = agingOrg;
    }

    public Boolean getVeto() {
        return veto;
    }

    public void setVeto(Boolean veto) {
        this.veto = veto;
    }

    public Boolean getSun() {
        return sun;
    }

    public void setSun(Boolean sun) {
        this.sun = sun;
    }

    public String getReviewTimeStr() {
        return reviewTimeStr;
    }

    public void setReviewTimeStr(String reviewTimeStr) {
        this.reviewTimeStr = reviewTimeStr;
    }

    public String getReviewEndTimeStr() {
        return reviewEndTimeStr;
    }

    public void setReviewEndTimeStr(String reviewEndTimeStr) {
        this.reviewEndTimeStr = reviewEndTimeStr;
    }

    public String getAgingReview() {
        return agingReview;
    }

    public void setAgingReview(String agingReview) {
        this.agingReview = agingReview;
    }

    public Double getBasScore() {
        return basScore;
    }

    public void setBasScore(Double basScore) {
        this.basScore = basScore;
    }

    public Double getSunScore() {
        return sunScore;
    }

    public void setSunScore(Double sunScore) {
        this.sunScore = sunScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }

    public Date getOrgEndTime() {
        return orgEndTime;
    }

    public void setOrgEndTime(Date orgEndTime) {
        this.orgEndTime = orgEndTime;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Date getReviewEndTime() {
        return reviewEndTime;
    }

    public void setReviewEndTime(Date reviewEndTime) {
        this.reviewEndTime = reviewEndTime;
    }

    public Date getEntrustReportEndDate() {
        return entrustReportEndDate;
    }

    public void setEntrustReportEndDate(Date entrustReportEndDate) {
        this.entrustReportEndDate = entrustReportEndDate;
    }

    public String getEntrustReportEndDateStr() {
        return entrustReportEndDateStr;
    }

    public void setEntrustReportEndDateStr(String entrustReportEndDateStr) {
        this.entrustReportEndDateStr = entrustReportEndDateStr;
    }

    public Long getAgingReviewTwo() {
        return agingReviewTwo;
    }

    public void setAgingReviewTwo(Long agingReviewTwo) {
        this.agingReviewTwo = agingReviewTwo;
    }

    public List<Map<String, Object>> getSurveyCheckMapList() {
        return surveyCheckMapList;
    }

    public void setSurveyCheckMapList(List<Map<String, Object>> surveyCheckMapList) {
        this.surveyCheckMapList = surveyCheckMapList;
    }

    public SurveyAssignOrgExtend getSurveyAssignOrgExtend() {
        return surveyAssignOrgExtend;
    }

    public void setSurveyAssignOrgExtend(SurveyAssignOrgExtend surveyAssignOrgExtend) {
        this.surveyAssignOrgExtend = surveyAssignOrgExtend;
    }
}
