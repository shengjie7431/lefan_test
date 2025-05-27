package com.lefancrm.backend.dto.channel;

import java.util.Date;
import java.util.List;

public class SurveyChannelCost {
    private Long id;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Long surveyUserId;

    private String surveyUserName;

    private Double chnannelMoney;

    private Integer state;

    private Date operationTime;

    private Long operationUserId;

    private String operationUserName;

    private String payeeUserName;

    private String bankDeposit;

    private String bankBranch;

    private String bankNo;

    private String rejectDesc;

    private Long reviewerUserId;

    private String reviewerUserName;

    private Date reviewerTime;

    private String channelDesc;

    private Integer isProPay;

    private List<SurveyChannelCase> surveyInfos;//案件集合

    private List<SurveyChannelCase> surveyChannelCases;

    private String surveyChannelCasesJson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getChnannelMoney() {
        return chnannelMoney;
    }

    public void setChnannelMoney(Double chnannelMoney) {
        this.chnannelMoney = chnannelMoney;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public Long getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(Long operationUserId) {
        this.operationUserId = operationUserId;
    }

    public String getOperationUserName() {
        return operationUserName;
    }

    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

    public String getPayeeUserName() {
        return payeeUserName;
    }

    public void setPayeeUserName(String payeeUserName) {
        this.payeeUserName = payeeUserName;
    }

    public String getBankDeposit() {
        return bankDeposit;
    }

    public void setBankDeposit(String bankDeposit) {
        this.bankDeposit = bankDeposit;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getRejectDesc() {
        return rejectDesc;
    }

    public void setRejectDesc(String rejectDesc) {
        this.rejectDesc = rejectDesc;
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

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
    }

    public List<SurveyChannelCase> getSurveyChannelCases() {
        return surveyChannelCases;
    }

    public void setSurveyChannelCases(List<SurveyChannelCase> surveyChannelCases) {
        this.surveyChannelCases = surveyChannelCases;
    }

    public List<SurveyChannelCase> getSurveyInfos() {
        return surveyInfos;
    }

    public void setSurveyInfos(List<SurveyChannelCase> surveyInfos) {
        this.surveyInfos = surveyInfos;
    }

    public String getSurveyChannelCasesJson() {
        return surveyChannelCasesJson;
    }

    public void setSurveyChannelCasesJson(String surveyChannelCasesJson) {
        this.surveyChannelCasesJson = surveyChannelCasesJson;
    }

    public Integer getIsProPay() {
        return isProPay;
    }

    public void setIsProPay(Integer isProPay) {
        this.isProPay = isProPay;
    }
}