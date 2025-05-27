package com.lefancrm.apicenter.dto.report;

import java.util.Date;

public class SurveyManPowerItemDTO {
    private Long surveyUserId;
    private String surveyUserName;
    private Date quitTime;
    private Date entryTime;
    private Integer deleteFlag;
    private Double score;
    private Double momScore;
    private Long sonOrgId;
    private String sonOrgName;
    private Long orgId;
    private String orgName;
    private Integer orgLevel;

    private int surveyType;
    private String surveyTypeName;
    private Double scoreRate;

    private Integer caseNum;//案件数
    private Integer momCaseNum;//环比案件数量

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getMomScore() {
        return momScore;
    }

    public void setMomScore(Double momScore) {
        this.momScore = momScore;
    }

    public Long getSonOrgId() {
        return sonOrgId;
    }

    public void setSonOrgId(Long sonOrgId) {
        this.sonOrgId = sonOrgId;
    }

    public String getSonOrgName() {
        return sonOrgName;
    }

    public void setSonOrgName(String sonOrgName) {
        this.sonOrgName = sonOrgName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public int getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(int surveyType) {
        this.surveyType = surveyType;
    }

    public String getSurveyTypeName() {
        return surveyTypeName;
    }

    public void setSurveyTypeName(String surveyTypeName) {
        this.surveyTypeName = surveyTypeName;
    }

    public Double getScoreRate() {
        return scoreRate;
    }

    public void setScoreRate(Double scoreRate) {
        this.scoreRate = scoreRate;
    }

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public Integer getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(Integer caseNum) {
        this.caseNum = caseNum;
    }

    public Integer getMomCaseNum() {
        return momCaseNum;
    }

    public void setMomCaseNum(Integer momCaseNum) {
        this.momCaseNum = momCaseNum;
    }
}
