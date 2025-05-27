package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyRiskCaseInfo {
    private Long id;

    private String surveyCno;

    private String surveyNo;

    private Long surveyId;

    private Long surveyBusId;

    private String surveyBusName;

    private Long servicesId;

    private String servicesName;

    private Double surveyMoney;

    private Double entrustMoney;

    private Date endTime;

    private String surveyInfo;

    private String surveyItem;

    private Integer surveyPhase;

    private Integer surveyState;

    private String surveyStateName;

    private Long surveyUserId;

    private String surveyUserName;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Long entrustOrgId;

    private String entrustOrgName;

    private Integer isSun;

    private Long createUserId;

    private String createUserName;

    private Integer entrustCredit;

    private Double entrustCreditMoney;

    private Integer entrustCreditIsPay;

    private String opinion;

    private Long reportId;

    private String reportName;

    private Integer reportState;

    private Date reportDate;

    private Integer payType;

    private Double entrustReLosses;

    private Double surveryReLosses;

    private String entrustReLossesRemark;

    private String surveryReLoossesRemark;

    private Integer reLossesType;

    private Integer supplementState;

    private Integer isHelpCase;

    private Integer assignState;

    private Integer acceptState;

    private Date entrustStartDate;

    private Date entrustEndDate;

    private Date lefanReportDate;

    private String lefanReportRemark;

    private String entrustReportRemark;

    private Date entrustReportStartDate;

    private Date entrustReportEndDate;

    private Integer isPayEntrustFee;

    private Integer isSendReport;

    private Date sendDate;

    private Date arrivalDate;

    private Date closeStartDate;

    private Date closeEndDate;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String other1;

    private String other2;

    private Integer deleteFlag;

    private Integer isClassic;

    private Double entrustOkPrice1;

    private Double entrustOkPrice2;

    private Integer price1IsCalc;

    private Integer price2IsCalc;

    private Integer taskDispatchState;

    private String taskDispatchRemark;

    private String searchCondition;

    private Long agentUserId;

    private Double billingMoney;

    private Double confirmAccountMoney;

    private Long sourceOrgId;

    private String sourceOrgName;

    private Integer sourceSupportType;

    private Integer orgAssign;

    private Integer sendReportState;//发送报告状态 0或NULL未发送  1已发送（制作中）  2已提交
    private Long sendReportUserId;//报告制作人
    private String sendReportUserName;

    private Long belongUserId;//案件归属人
    private String belongUserName;

    private String reportCompletion;

    private Integer useLetterInfo;

    private String orgOprOpinion;

    private Integer orgReturn; //机构主动退回

    private Integer guide; //是否指导 0否 1是

    private Integer guideState; //指导状态(0：未指导，1:已指导)

    private Integer reimState;

    private Integer caseState;//案件类型：1、单点；2、单点+单点；3、全案；4、全案+单点；5、全案+全案

    private Integer agingDay;

    private Long subServiceId;

    private Integer performanceState; // 结算绩效的状态 （0、未结算；1、结算中；2、已结算）

    private Date performanceDate; //结算绩效时间

    private Long performanceId;//绩效id


    //调查工作流，字段
    private String stepName;//
    private Long dealUserId; //
    private String dealUserName;//
    private Long hours;

    private Long handleId;//众安案件id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurveyCno() {
        return surveyCno;
    }

    public void setSurveyCno(String surveyCno) {
        this.surveyCno = surveyCno;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getSurveyBusId() {
        return surveyBusId;
    }

    public void setSurveyBusId(Long surveyBusId) {
        this.surveyBusId = surveyBusId;
    }

    public String getSurveyBusName() {
        return surveyBusName;
    }

    public void setSurveyBusName(String surveyBusName) {
        this.surveyBusName = surveyBusName;
    }

    public Double getSurveyMoney() {
        return surveyMoney;
    }

    public void setSurveyMoney(Double surveyMoney) {
        this.surveyMoney = surveyMoney;
    }

    public Double getEntrustMoney() {
        return entrustMoney;
    }

    public void setEntrustMoney(Double entrustMoney) {
        this.entrustMoney = entrustMoney;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSurveyInfo() {
        return surveyInfo;
    }

    public void setSurveyInfo(String surveyInfo) {
        this.surveyInfo = surveyInfo;
    }

    public String getSurveyItem() {
        return surveyItem;
    }

    public void setSurveyItem(String surveyItem) {
        this.surveyItem = surveyItem;
    }

    public Integer getSurveyPhase() {
        return surveyPhase;
    }

    public void setSurveyPhase(Integer surveyPhase) {
        this.surveyPhase = surveyPhase;
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

    public Integer getIsSun() {
        return isSun;
    }

    public void setIsSun(Integer isSun) {
        this.isSun = isSun;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
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

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Double getEntrustReLosses() {
        return entrustReLosses;
    }

    public void setEntrustReLosses(Double entrustReLosses) {
        this.entrustReLosses = entrustReLosses;
    }

    public Double getSurveryReLosses() {
        return surveryReLosses;
    }

    public void setSurveryReLosses(Double surveryReLosses) {
        this.surveryReLosses = surveryReLosses;
    }

    public Integer getSupplementState() {
        return supplementState;
    }

    public void setSupplementState(Integer supplementState) {
        this.supplementState = supplementState;
    }

    public Integer getIsHelpCase() {
        return isHelpCase;
    }

    public void setIsHelpCase(Integer isHelpCase) {
        this.isHelpCase = isHelpCase;
    }

    public Integer getAssignState() {
        return assignState;
    }

    public void setAssignState(Integer assignState) {
        this.assignState = assignState;
    }

    public Integer getAcceptState() {
        return acceptState;
    }

    public void setAcceptState(Integer acceptState) {
        this.acceptState = acceptState;
    }

    public Date getEntrustStartDate() {
        return entrustStartDate;
    }

    public void setEntrustStartDate(Date entrustStartDate) {
        this.entrustStartDate = entrustStartDate;
    }

    public Date getEntrustEndDate() {
        return entrustEndDate;
    }

    public void setEntrustEndDate(Date entrustEndDate) {
        this.entrustEndDate = entrustEndDate;
    }

    public Date getLefanReportDate() {
        return lefanReportDate;
    }

    public void setLefanReportDate(Date lefanReportDate) {
        this.lefanReportDate = lefanReportDate;
    }

    public String getLefanReportRemark() {
        return lefanReportRemark;
    }

    public void setLefanReportRemark(String lefanReportRemark) {
        this.lefanReportRemark = lefanReportRemark;
    }

    public String getEntrustReportRemark() {
        return entrustReportRemark;
    }

    public void setEntrustReportRemark(String entrustReportRemark) {
        this.entrustReportRemark = entrustReportRemark;
    }

    public Date getEntrustReportStartDate() {
        return entrustReportStartDate;
    }

    public void setEntrustReportStartDate(Date entrustReportStartDate) {
        this.entrustReportStartDate = entrustReportStartDate;
    }

    public Date getEntrustReportEndDate() {
        return entrustReportEndDate;
    }

    public void setEntrustReportEndDate(Date entrustReportEndDate) {
        this.entrustReportEndDate = entrustReportEndDate;
    }

    public Integer getIsPayEntrustFee() {
        return isPayEntrustFee;
    }

    public void setIsPayEntrustFee(Integer isPayEntrustFee) {
        this.isPayEntrustFee = isPayEntrustFee;
    }

    public Integer getIsSendReport() {
        return isSendReport;
    }

    public void setIsSendReport(Integer isSendReport) {
        this.isSendReport = isSendReport;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getCloseStartDate() {
        return closeStartDate;
    }

    public void setCloseStartDate(Date closeStartDate) {
        this.closeStartDate = closeStartDate;
    }

    public Date getCloseEndDate() {
        return closeEndDate;
    }

    public void setCloseEndDate(Date closeEndDate) {
        this.closeEndDate = closeEndDate;
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

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public String getEntrustReLossesRemark() {
        return entrustReLossesRemark;
    }

    public void setEntrustReLossesRemark(String entrustReLossesRemark) {
        this.entrustReLossesRemark = entrustReLossesRemark;
    }

    public String getSurveryReLoossesRemark() {
        return surveryReLoossesRemark;
    }

    public void setSurveryReLoossesRemark(String surveryReLoossesRemark) {
        this.surveryReLoossesRemark = surveryReLoossesRemark;
    }

    public Integer getReLossesType() {
        return reLossesType;
    }

    public void setReLossesType(Integer reLossesType) {
        this.reLossesType = reLossesType;
    }

    public Integer getEntrustCredit() {
        return entrustCredit;
    }

    public void setEntrustCredit(Integer entrustCredit) {
        this.entrustCredit = entrustCredit;
    }

    public Double getEntrustCreditMoney() {
        return entrustCreditMoney;
    }

    public void setEntrustCreditMoney(Double entrustCreditMoney) {
        this.entrustCreditMoney = entrustCreditMoney;
    }

    public Integer getEntrustCreditIsPay() {
        return entrustCreditIsPay;
    }

    public void setEntrustCreditIsPay(Integer entrustCreditIsPay) {
        this.entrustCreditIsPay = entrustCreditIsPay;
    }

    public Integer getIsClassic() {
        return isClassic;
    }

    public void setIsClassic(Integer isClassic) {
        this.isClassic = isClassic;
    }

    public Double getEntrustOkPrice1() {
        return entrustOkPrice1;
    }

    public void setEntrustOkPrice1(Double entrustOkPrice1) {
        this.entrustOkPrice1 = entrustOkPrice1;
    }

    public Double getEntrustOkPrice2() {
        return entrustOkPrice2;
    }

    public void setEntrustOkPrice2(Double entrustOkPrice2) {
        this.entrustOkPrice2 = entrustOkPrice2;
    }

    public Integer getPrice1IsCalc() {
        return price1IsCalc;
    }

    public void setPrice1IsCalc(Integer price1IsCalc) {
        this.price1IsCalc = price1IsCalc;
    }

    public Integer getPrice2IsCalc() {
        return price2IsCalc;
    }

    public void setPrice2IsCalc(Integer price2IsCalc) {
        this.price2IsCalc = price2IsCalc;
    }

    public Integer getTaskDispatchState() {
        return taskDispatchState;
    }

    public void setTaskDispatchState(Integer taskDispatchState) {
        this.taskDispatchState = taskDispatchState;
    }

    public String getTaskDispatchRemark() {
        return taskDispatchRemark;
    }

    public void setTaskDispatchRemark(String taskDispatchRemark) {
        this.taskDispatchRemark = taskDispatchRemark;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public Long getAgentUserId() {
        return agentUserId;
    }

    public void setAgentUserId(Long agentUserId) {
        this.agentUserId = agentUserId;
    }

    public Double getBillingMoney() {
        return billingMoney;
    }

    public void setBillingMoney(Double billingMoney) {
        this.billingMoney = billingMoney;
    }

    public Double getConfirmAccountMoney() {
        return confirmAccountMoney;
    }

    public void setConfirmAccountMoney(Double confirmAccountMoney) {
        this.confirmAccountMoney = confirmAccountMoney;
    }

    public Long getSourceOrgId() {
        return sourceOrgId;
    }

    public void setSourceOrgId(Long sourceOrgId) {
        this.sourceOrgId = sourceOrgId;
    }

    public String getSourceOrgName() {
        return sourceOrgName;
    }

    public void setSourceOrgName(String sourceOrgName) {
        this.sourceOrgName = sourceOrgName;
    }

    public Integer getSourceSupportType() {
        return sourceSupportType;
    }

    public void setSourceSupportType(Integer sourceSupportType) {
        this.sourceSupportType = sourceSupportType;
    }

    public Integer getOrgAssign() {
        return orgAssign;
    }

    public void setOrgAssign(Integer orgAssign) {
        this.orgAssign = orgAssign;
    }

    public Integer getSendReportState() {
        return sendReportState;
    }

    public void setSendReportState(Integer sendReportState) {
        this.sendReportState = sendReportState;
    }

    public Long getSendReportUserId() {
        return sendReportUserId;
    }

    public void setSendReportUserId(Long sendReportUserId) {
        this.sendReportUserId = sendReportUserId;
    }

    public String getSendReportUserName() {
        return sendReportUserName;
    }

    public void setSendReportUserName(String sendReportUserName) {
        this.sendReportUserName = sendReportUserName;
    }

    public Long getBelongUserId() {
        return belongUserId;
    }

    public void setBelongUserId(Long belongUserId) {
        this.belongUserId = belongUserId;
    }

    public String getBelongUserName() {
        return belongUserName;
    }

    public void setBelongUserName(String belongUserName) {
        this.belongUserName = belongUserName;
    }

    public String getReportCompletion() {
        return reportCompletion;
    }

    public void setReportCompletion(String reportCompletion) {
        this.reportCompletion = reportCompletion;
    }

    public Integer getUseLetterInfo() {
        return useLetterInfo;
    }

    public void setUseLetterInfo(Integer useLetterInfo) {
        this.useLetterInfo = useLetterInfo;
    }

    public String getOrgOprOpinion() {
        return orgOprOpinion;
    }

    public void setOrgOprOpinion(String orgOprOpinion) {
        this.orgOprOpinion = orgOprOpinion;
    }

    public Integer getOrgReturn() {
        return orgReturn;
    }

    public void setOrgReturn(Integer orgReturn) {
        this.orgReturn = orgReturn;
    }

    public Integer getGuide() {
        return guide;
    }

    public void setGuide(Integer guide) {
        this.guide = guide;
    }

    public Integer getGuideState() {
        return guideState;
    }

    public void setGuideState(Integer guideState) {
        this.guideState = guideState;
    }

    public Integer getReimState() {
        return reimState;
    }

    public void setReimState(Integer reimState) {
        this.reimState = reimState;
    }

    public Integer getCaseState() {
        return caseState;
    }

    public void setCaseState(Integer caseState) {
        this.caseState = caseState;
    }

    public Integer getAgingDay() {
        return agingDay;
    }

    public void setAgingDay(Integer agingDay) {
        this.agingDay = agingDay;
    }

    public Long getSubServiceId() {
        return subServiceId;
    }

    public void setSubServiceId(Long subServiceId) {
        this.subServiceId = subServiceId;
    }

    public Integer getPerformanceState() {
        return performanceState;
    }

    public void setPerformanceState(Integer performanceState) {
        this.performanceState = performanceState;
    }

    public Date getPerformanceDate() {
        return performanceDate;
    }

    public void setPerformanceDate(Date performanceDate) {
        this.performanceDate = performanceDate;
    }

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public Long getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(Long dealUserId) {
        this.dealUserId = dealUserId;
    }

    public String getDealUserName() {
        return dealUserName;
    }

    public void setDealUserName(String dealUserName) {
        this.dealUserName = dealUserName;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public Long getHandleId() {
        return handleId;
    }

    public void setHandleId(Long handleId) {
        this.handleId = handleId;
    }
}