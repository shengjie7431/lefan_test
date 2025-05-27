package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyAssignOrg {
    private Long id;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Long surveyId;

    private Long surveyInfoId;

    private Long reportId;

    private String reportName;

    private Integer reportState;

    private Date reportDate;

    private Integer orgSurveyState;

    private String orgSurveyStateName;

    private Long surveyInvestigatorCaseId;

    private Date orgEndTime;

    private String orgTaskRemark;

    private String orgOpinion;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private Integer orgPrimaryType;

    private String orgSummary;

    private Integer returnState;

    private Long servicesId;

    private String servicesName;

    private Integer payType;

    private String surveryReLoossesRemark;

    private Double surveyMoney;

    private Double surveryReLosses;

    private Double surveyMoneySubmit;

    private Double surveryReLossesSubmit;

    private String surveryReportRemark;

    private Integer surveyPay;

    private Integer surveyReturn; //调查员主动退回

    private Integer extensionState;

    private Date extensionTime;

    private String extensionReason;

    private String extensionBackReason;

    private String extensionFiles;

    private Integer reviewOff;

    private Long reviewUserId;

    private String reviewUserName;

    private Date reviewTime;

    private Double inscompanyMoney;

    private Double inscompanyDeMoney;
    /**
     * 旧的委托方价格
     */
    private Double oldInscompanyMoney;
    /**
     * 旧的委托方减损价格
     */
    private Double oldInscompanyDeMoney;
    /**
     * 价格修改备注
     */
    private String inscompanyMoneyDesc;

    /**
     *确认委托方价格
     * @return
     */
    private Double inscompanySubmitMoney;

    /**
     *旧的确认委托方价格
     * @return
     */
    private Double oldInscompanySubmitMoney;

    /**
     *确认委托方价格修改备注
     * @return
     */
    private Double inscompanySubmitMoneyDesc;

    private Integer agingDay;

    private Date oldOrgEndTime;

    private Date utterEndTime;

    private String followInformation;
    private Date followTime;
    private Long followUserId;
    private String followUserName;

    private Double overdueAgingRate;

    private Double assessOrgMoney;

    private Double assessOrgLossesMoney;

    private Double agingCheck;
    private Double agingReal;
    private Double agingOver;

    private Double accMoney;

    private Integer newCase;

    private Integer markError;

    private String markErrorRemark;

    public Double getAgingCheck() {
        return agingCheck;
    }

    public void setAgingCheck(Double agingCheck) {
        this.agingCheck = agingCheck;
    }

    public Double getAgingReal() {
        return agingReal;
    }

    public void setAgingReal(Double agingReal) {
        this.agingReal = agingReal;
    }

    public Double getAgingOver() {
        return agingOver;
    }

    public void setAgingOver(Double agingOver) {
        this.agingOver = agingOver;
    }

    public String getFollowInformation() {
        return followInformation;
    }

    public void setFollowInformation(String followInformation) {
        this.followInformation = followInformation;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public Long getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(Long followUserId) {
        this.followUserId = followUserId;
    }

    public String getFollowUserName() {
        return followUserName;
    }

    public void setFollowUserName(String followUserName) {
        this.followUserName = followUserName;
    }

    public Double getInscompanySubmitMoney() {
        return inscompanySubmitMoney;
    }

    public void setInscompanySubmitMoney(Double inscompanySubmitMoney) {
        this.inscompanySubmitMoney = inscompanySubmitMoney;
    }

    public Double getOldInscompanySubmitMoney() {
        return oldInscompanySubmitMoney;
    }

    public void setOldInscompanySubmitMoney(Double oldInscompanySubmitMoney) {
        this.oldInscompanySubmitMoney = oldInscompanySubmitMoney;
    }

    public Double getInscompanySubmitMoneyDesc() {
        return inscompanySubmitMoneyDesc;
    }

    public void setInscompanySubmitMoneyDesc(Double inscompanySubmitMoneyDesc) {
        this.inscompanySubmitMoneyDesc = inscompanySubmitMoneyDesc;
    }

    public Double getOldInscompanyMoney() {
        return oldInscompanyMoney;
    }

    public void setOldInscompanyMoney(Double oldInscompanyMoney) {
        this.oldInscompanyMoney = oldInscompanyMoney;
    }

    public Double getOldInscompanyDeMoney() {
        return oldInscompanyDeMoney;
    }

    public void setOldInscompanyDeMoney(Double oldInscompanyDeMoney) {
        this.oldInscompanyDeMoney = oldInscompanyDeMoney;
    }

    public String getInscompanyMoneyDesc() {
        return inscompanyMoneyDesc;
    }

    public void setInscompanyMoneyDesc(String inscompanyMoneyDesc) {
        this.inscompanyMoneyDesc = inscompanyMoneyDesc;
    }

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

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Integer getReportState() {
        return reportState;
    }

    public void setReportState(Integer reportState) {
        this.reportState = reportState;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getSurveyInvestigatorCaseId() {
        return surveyInvestigatorCaseId;
    }

    public void setSurveyInvestigatorCaseId(Long surveyInvestigatorCaseId) {
        this.surveyInvestigatorCaseId = surveyInvestigatorCaseId;
    }

    public Date getOrgEndTime() {
        return orgEndTime;
    }

    public void setOrgEndTime(Date orgEndTime) {
        this.orgEndTime = orgEndTime;
    }

    public String getOrgTaskRemark() {
        return orgTaskRemark;
    }

    public void setOrgTaskRemark(String orgTaskRemark) {
        this.orgTaskRemark = orgTaskRemark;
    }

    public String getOrgOpinion() {
        return orgOpinion;
    }

    public void setOrgOpinion(String orgOpinion) {
        this.orgOpinion = orgOpinion;
    }

    public Integer getOrgPrimaryType() {
        return orgPrimaryType;
    }

    public void setOrgPrimaryType(Integer orgPrimaryType) {
        this.orgPrimaryType = orgPrimaryType;
    }

    public String getOrgSummary() {
        return orgSummary;
    }

    public void setOrgSummary(String orgSummary) {
        this.orgSummary = orgSummary;
    }

    public Integer getReturnState() {
        return returnState;
    }

    public void setReturnState(Integer returnState) {
        this.returnState = returnState;
    }

    public Long getServicesId() {
        return servicesId;
    }

    public void setServicesId(Long servicesId) {
        this.servicesId = servicesId;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getSurveryReLoossesRemark() {
        return surveryReLoossesRemark;
    }

    public void setSurveryReLoossesRemark(String surveryReLoossesRemark) {
        this.surveryReLoossesRemark = surveryReLoossesRemark;
    }

    public Double getSurveyMoney() {
        return surveyMoney;
    }

    public void setSurveyMoney(Double surveyMoney) {
        this.surveyMoney = surveyMoney;
    }

    public Double getSurveryReLosses() {
        return surveryReLosses;
    }

    public void setSurveryReLosses(Double surveryReLosses) {
        this.surveryReLosses = surveryReLosses;
    }

    public Double getSurveyMoneySubmit() {
        return surveyMoneySubmit;
    }

    public void setSurveyMoneySubmit(Double surveyMoneySubmit) {
        this.surveyMoneySubmit = surveyMoneySubmit;
    }

    public Double getSurveryReLossesSubmit() {
        return surveryReLossesSubmit;
    }

    public void setSurveryReLossesSubmit(Double surveryReLossesSubmit) {
        this.surveryReLossesSubmit = surveryReLossesSubmit;
    }

    public String getSurveryReportRemark() {
        return surveryReportRemark;
    }

    public void setSurveryReportRemark(String surveryReportRemark) {
        this.surveryReportRemark = surveryReportRemark;
    }

    public Integer getSurveyPay() {
        return surveyPay;
    }

    public void setSurveyPay(Integer surveyPay) {
        this.surveyPay = surveyPay;
    }

    public Integer getSurveyReturn() {
        return surveyReturn;
    }

    public void setSurveyReturn(Integer surveyReturn) {
        this.surveyReturn = surveyReturn;
    }

    public Integer getExtensionState() {
        return extensionState;
    }

    public void setExtensionState(Integer extensionState) {
        this.extensionState = extensionState;
    }

    public Date getExtensionTime() {
        return extensionTime;
    }

    public void setExtensionTime(Date extensionTime) {
        this.extensionTime = extensionTime;
    }

    public String getExtensionReason() {
        return extensionReason;
    }

    public void setExtensionReason(String extensionReason) {
        this.extensionReason = extensionReason;
    }

    public String getExtensionBackReason() {
        return extensionBackReason;
    }

    public void setExtensionBackReason(String extensionBackReason) {
        this.extensionBackReason = extensionBackReason;
    }

    public Integer getReviewOff() {
        return reviewOff;
    }

    public void setReviewOff(Integer reviewOff) {
        this.reviewOff = reviewOff;
    }

    public String getExtensionFiles() {
        return extensionFiles;
    }

    public void setExtensionFiles(String extensionFiles) {
        this.extensionFiles = extensionFiles;
    }

    public Long getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(Long reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public String getReviewUserName() {
        return reviewUserName;
    }

    public void setReviewUserName(String reviewUserName) {
        this.reviewUserName = reviewUserName;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Double getInscompanyMoney() {
        return inscompanyMoney;
    }

    public void setInscompanyMoney(Double inscompanyMoney) {
        this.inscompanyMoney = inscompanyMoney;
    }

    public Double getInscompanyDeMoney() {
        return inscompanyDeMoney;
    }

    public void setInscompanyDeMoney(Double inscompanyDeMoney) {
        this.inscompanyDeMoney = inscompanyDeMoney;
    }

    public Date getOldOrgEndTime() {
        return oldOrgEndTime;
    }

    public void setOldOrgEndTime(Date oldOrgEndTime) {
        this.oldOrgEndTime = oldOrgEndTime;
    }

    public Date getUtterEndTime() {
        return utterEndTime;
    }

    public void setUtterEndTime(Date utterEndTime) {
        this.utterEndTime = utterEndTime;
    }

    public Integer getAgingDay() {
        return agingDay;
    }

    public void setAgingDay(Integer agingDay) {
        this.agingDay = agingDay;
    }

    public Double getOverdueAgingRate() {
        return overdueAgingRate;
    }

    public void setOverdueAgingRate(Double overdueAgingRate) {
        this.overdueAgingRate = overdueAgingRate;
    }

    public Double getAssessOrgMoney() {
        return assessOrgMoney;
    }

    public void setAssessOrgMoney(Double assessOrgMoney) {
        this.assessOrgMoney = assessOrgMoney;
    }

    public Double getAssessOrgLossesMoney() {
        return assessOrgLossesMoney;
    }

    public void setAssessOrgLossesMoney(Double assessOrgLossesMoney) {
        this.assessOrgLossesMoney = assessOrgLossesMoney;
    }

    public Double getAccMoney() {
        return accMoney;
    }

    public void setAccMoney(Double accMoney) {
        this.accMoney = accMoney;
    }

    public Integer getNewCase() {
        return newCase;
    }

    public void setNewCase(Integer newCase) {
        this.newCase = newCase;
    }

    public Integer getMarkError() {
        return markError;
    }

    public void setMarkError(Integer markError) {
        this.markError = markError;
    }

    public String getMarkErrorRemark() {
        return markErrorRemark;
    }

    public void setMarkErrorRemark(String markErrorRemark) {
        this.markErrorRemark = markErrorRemark;
    }
}