package com.lefancrm.apicenter.dto;

import java.util.Date;

public class SurveyCostApplyDto {
    private Long id;

    private Integer costType;

    private String costTypeName;

    private Double applyMoney;

    private Date produceDate;

    private String costDesc;

    private String startAddress;

    private String endAddress;

    private Double startLbsX;

    private Double startLbsY;

    private Double endLbsX;

    private Double endLbsY;

    private Double kmNum;

    private Double kmNumMoney;

    private Date createTime;

    private String createBy;

    private Integer state;

    private Long reviewerUserId;

    private String reviewerUserName;

    private Date reviewerTime;

    private String rejectDesc;

    private Long surveyUserId;

    private String surveyUserName;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Long twoReviewerUserId;

    private String twoReviewerUserName;

    private Date twoReviewerTime;

    private String twoRejectDesc;

    private String surveyPersons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCostType() {
        return costType;
    }

    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    public String getCostTypeName() {
        return costTypeName;
    }

    public void setCostTypeName(String costTypeName) {
        this.costTypeName = costTypeName;
    }

    public Double getApplyMoney() {
        return applyMoney;
    }

    public void setApplyMoney(Double applyMoney) {
        this.applyMoney = applyMoney;
    }

    public Date getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }

    public String getCostDesc() {
        return costDesc;
    }

    public void setCostDesc(String costDesc) {
        this.costDesc = costDesc;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public Double getStartLbsX() {
        return startLbsX;
    }

    public void setStartLbsX(Double startLbsX) {
        this.startLbsX = startLbsX;
    }

    public Double getStartLbsY() {
        return startLbsY;
    }

    public void setStartLbsY(Double startLbsY) {
        this.startLbsY = startLbsY;
    }

    public Double getEndLbsX() {
        return endLbsX;
    }

    public void setEndLbsX(Double endLbsX) {
        this.endLbsX = endLbsX;
    }

    public Double getEndLbsY() {
        return endLbsY;
    }

    public void setEndLbsY(Double endLbsY) {
        this.endLbsY = endLbsY;
    }

    public Double getKmNum() {
        return kmNum;
    }

    public void setKmNum(Double kmNum) {
        this.kmNum = kmNum;
    }

    public Double getKmNumMoney() {
        return kmNumMoney;
    }

    public void setKmNumMoney(Double kmNumMoney) {
        this.kmNumMoney = kmNumMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getReviewerUserId() {
        return reviewerUserId;
    }

    public void setReviewerUserId(Long reviewerUserId) {
        this.reviewerUserId = reviewerUserId;
    }

    public String getReviewerUserName() {
        return reviewerUserName;
    }

    public void setReviewerUserName(String reviewerUserName) {
        this.reviewerUserName = reviewerUserName;
    }

    public Date getReviewerTime() {
        return reviewerTime;
    }

    public void setReviewerTime(Date reviewerTime) {
        this.reviewerTime = reviewerTime;
    }

    public String getRejectDesc() {
        return rejectDesc;
    }

    public void setRejectDesc(String rejectDesc) {
        this.rejectDesc = rejectDesc;
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

    public Long getTwoReviewerUserId() {
        return twoReviewerUserId;
    }

    public void setTwoReviewerUserId(Long twoReviewerUserId) {
        this.twoReviewerUserId = twoReviewerUserId;
    }

    public String getTwoReviewerUserName() {
        return twoReviewerUserName;
    }

    public void setTwoReviewerUserName(String twoReviewerUserName) {
        this.twoReviewerUserName = twoReviewerUserName;
    }

    public Date getTwoReviewerTime() {
        return twoReviewerTime;
    }

    public void setTwoReviewerTime(Date twoReviewerTime) {
        this.twoReviewerTime = twoReviewerTime;
    }

    public String getTwoRejectDesc() {
        return twoRejectDesc;
    }

    public void setTwoRejectDesc(String twoRejectDesc) {
        this.twoRejectDesc = twoRejectDesc;
    }


    public String getSurveyPersons() {
        return surveyPersons;
    }

    public void setSurveyPersons(String surveyPersons) {
        this.surveyPersons = surveyPersons;
    }
}