package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyChannelCostNew {
    private Long id;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Long surveyUserId;

    private String surveyUserName;

    private Long surveyInfoId;

    private Long surveyDirectionId;

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

    private Integer deleteFlag;

    private Integer isProPay;

    private Integer deleteDirectionState;


    //用于展示
    private Double maxChannelMoney;
    private Date payRealTime;

    private String surveyCaseNo;

    private Long entrustOrgId;

    private String entrustOrgName;

    private String surveyDirectionName;

    private Long surveyAssorgCaseId;//用于传参

    private String surveyUsersStr;

    private String ids;

    //人事管理-员工信息同步
    private Long socialSecurityCompanyId;
    private String socialSecurityCompany;
    private Long organId;
    private String organ;
    private Long departmentId;
    private String department;
    private Long teamId;
    private String team;
    private Long jobPostId;
    private String jobPost;

    private String directionName;


    private String areaName;
    private String taskName;
    private String contentTaskName;

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

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Long getSurveyDirectionId() {
        return surveyDirectionId;
    }

    public void setSurveyDirectionId(Long surveyDirectionId) {
        this.surveyDirectionId = surveyDirectionId;
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

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getIsProPay() {
        return isProPay;
    }

    public void setIsProPay(Integer isProPay) {
        this.isProPay = isProPay;
    }

    public Long getSurveyAssorgCaseId() {
        return surveyAssorgCaseId;
    }

    public void setSurveyAssorgCaseId(Long surveyAssorgCaseId) {
        this.surveyAssorgCaseId = surveyAssorgCaseId;
    }

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }

    public String getSurveyDirectionName() {
        return surveyDirectionName;
    }

    public void setSurveyDirectionName(String surveyDirectionName) {
        this.surveyDirectionName = surveyDirectionName;
    }

    public String getSurveyUsersStr() {
        return surveyUsersStr;
    }

    public void setSurveyUsersStr(String surveyUsersStr) {
        this.surveyUsersStr = surveyUsersStr;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Long getSocialSecurityCompanyId() {
        return socialSecurityCompanyId;
    }

    public void setSocialSecurityCompanyId(Long socialSecurityCompanyId) {
        this.socialSecurityCompanyId = socialSecurityCompanyId;
    }

    public String getSocialSecurityCompany() {
        return socialSecurityCompany;
    }

    public void setSocialSecurityCompany(String socialSecurityCompany) {
        this.socialSecurityCompany = socialSecurityCompany;
    }

    public Long getOrganId() {
        return organId;
    }

    public void setOrganId(Long organId) {
        this.organId = organId;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Date getPayRealTime() {
        return payRealTime;
    }

    public void setPayRealTime(Date payRealTime) {
        this.payRealTime = payRealTime;
    }

    public Double getMaxChannelMoney() {
        return maxChannelMoney;
    }

    public void setMaxChannelMoney(Double maxChannelMoney) {
        this.maxChannelMoney = maxChannelMoney;
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

    public Long getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Long jobPostId) {
        this.jobPostId = jobPostId;
    }

    public String getJobPost() {
        return jobPost;
    }

    public void setJobPost(String jobPost) {
        this.jobPost = jobPost;
    }

    public Integer getDeleteDirectionState() {
        return deleteDirectionState;
    }

    public void setDeleteDirectionState(Integer deleteDirectionState) {
        this.deleteDirectionState = deleteDirectionState;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getContentTaskName() {
        return contentTaskName;
    }

    public void setContentTaskName(String contentTaskName) {
        this.contentTaskName = contentTaskName;
    }
}