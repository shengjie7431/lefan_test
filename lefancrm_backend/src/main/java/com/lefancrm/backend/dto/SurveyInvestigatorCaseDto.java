package com.lefancrm.backend.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SurveyInvestigatorCaseDto {
    private Long id;

    private Long surveyId;

    private Long surveyInfoId;

    private Double surveyTaskMoney;

    private Double entrustTaskMoney;

    private Double entrustReLosses;

    private Double surveryReLosses;

    private String entrustReLossesRemark;

    private String surveryReLoossesRemark;

    private Long surveyUserId;

    private String surveyUserName;

    private Integer surveyUserType;

    private String surveyRemark;

    private Integer surveyState;

    private String surveyStateName;

    private Date assignDate;

    private Date acceptDate;

    private String surveyInfo;

    private String surveyItem;

    private Long entrustOrgId;

    private String entrustOrgName;

    private Long creportId;

    private String creportName;

    private Integer creportState;

    private Date creportDate;

    private Integer surveryUserOrgType;

    private Integer isSun;

    private Integer scoreState;

    private Integer scoreLevel;

    private Integer userCashState;

    private String searchCondition;

    private Date surveyEndTime;

    private String scoreRemark;

    private String surveyTaskRemark;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Integer isDirectionSuccess;

    private Integer sunState;

    private String sunRemark ;

    private Integer returnState;

    private Integer surveyPay;

    private String surveySummary;
    private Integer reviewOff;

    private Double baseScore;//基础分
    private Double sunScore;//阳性分
    private Double assessBaseScore;//基础分

    private CommonFile commonFile;

    private SurveyRiskCaseDto surveyRiskCase;
    private SurveyRiskCaseInfoDto surveyRiskCaseInfo;
    private Boolean isCurOrg;//是否是当前机构下的案件  true是 false否
    private SurveyFranchiseeDto surveyFranchisee;//调查调查方机构信息
    private SurveyAssignOrgDto surveyAssignOrg;//主调查员 的 机构案件信息

    private List<SurveyTaskTypeDto> surveyTaskTypes;
    //调查员任务类型列表
    private List<SurveyInvestigatorCaseTypeDto> tasks;
    private List<SurveyCaseDirectionDto> surveyCaseDirections;
    private List<SurveyInvestigatorCaseDto> surveyInvestigatorCases;
    private List<SurveyBackCaseDto> surveyBackCases;

    private Integer overTimeType;

    private Boolean showAddDirectionBtn;
    private Boolean showCommitBtn;
    private Boolean showOrgSummaryBtn;
    private Boolean showReportCompletionBtn;
    private Boolean showOrgCommitBtn;

    private int fileSize;
    private int successNum = 0;
    private int allNum = 0;

    private Boolean isUserLetterInfo; //是否使用了介绍信
    private String nullCode;//基础信息为NULL的编码 如果不为双引号说明 基础信息不完整
    private List<SurveyCaseDirectionDto> caseDirections;
    private String backgroundColor;//背景色
    private String efficiencyState;//案件时效具体状态
    private String efficiencyStateColor;//案件时效具体状态颜色
    private Boolean showExpenseReimbursementValue;//是否显示费用报销数值
    private String expenseReimbursementValue;//费用报销数值
    private Double score; //分值

    private Date sunTime;

    private Integer sunType;

    private Long sunUserId;

    private String sunUserName;

    private String specialRemark;

    private Long surveyAssorgCaseId;

    private Double scoreSun;

    private Double bsScore;

    private Double otherScore;

    private Double investigatorReMoney; //费用报销数值
    private Integer reState;//费用合计报销状态

    private String reStateStr;//费用合计报销状态

    private Integer haveFile;//凭证材料
    private Integer haveSound;//录音

    private Double overdueAgingRate;

    private Double assessScore;

    private Double assessSunScore;

    private Double assessBsScore;

    private Double assessOtherScore;

    private Boolean rateEdit;

    //委托日期
    private Date entrustTime;

    //机构任务类型ID
    private Integer servicesId;

    private String servicesName;

    //被调查人
    private String surveyPerson;

    //案件编号
    private String surveyNo;

    private Date reviewTime;

    //累计积分
    private Double cumulativePoints;

    //机构id
    private Integer orgId;

    //任务颜色
    private String taskColor;

    private Integer surveyUserCaseId;

    List<Map<String,Object>> urgPreList;
    private Boolean showBaoSi;
    private Integer oveDay;//超期天数

    private Integer assDay;//考核时效survey/case/info

    private Integer agingDay;

    private Double agingCheck;
    private Double agingReal;
    private Double agingOver;

    private Integer mechanismType;

    private Double totalSurveyPoints;

    private Double positiveTotalScore;

    private Double sunMoney;//阳性奖励

    /**
     * 调查员类型
     */
    private Integer investigatorType;

    private Integer entrustOrgType;

    private SurveyInvestigatorCaseSubDto surveyInvestigatorCaseSub;//扩展表

    private String replyContent;

    private List<CommonFile> replyCommonFiles;
    private Integer replyType;

    private Integer replyId;

    private Long riskHandleId;

    private Integer haveNwAccount;//是否有暖哇账号

    private Integer newCase;

    private Boolean showKey = false;

    private Boolean zhongan;

    private Boolean urgent;

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

    public Integer getAgingDay() {
        return agingDay;
    }

    public void setAgingDay(Integer agingDay) {
        this.agingDay = agingDay;
    }

    public Integer getOveDay() {
        return oveDay;
    }

    public void setOveDay(Integer oveDay) {
        this.oveDay = oveDay;
    }

    public Integer getAssDay() {
        return assDay;
    }

    public void setAssDay(Integer assDay) {
        this.assDay = assDay;
    }

    public Double getCumulativePoints() {
        return cumulativePoints;
    }

    public void setCumulativePoints(Double cumulativePoints) {
        this.cumulativePoints = cumulativePoints;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Date getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(Date entrustTime) {
        this.entrustTime = entrustTime;
    }

    public Integer getServicesId() {
        return servicesId;
    }

    public void setServicesId(Integer servicesId) {
        this.servicesId = servicesId;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public Integer getHaveFile() {
        return haveFile;
    }

    public void setHaveFile(Integer haveFile) {
        this.haveFile = haveFile;
    }

    public Integer getHaveSound() {
        return haveSound;
    }

    public void setHaveSound(Integer haveSound) {
        this.haveSound = haveSound;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getSurveyTaskMoney() {
        return surveyTaskMoney;
    }

    public void setSurveyTaskMoney(Double surveyTaskMoney) {
        this.surveyTaskMoney = surveyTaskMoney;
    }

    public Double getEntrustTaskMoney() {
        return entrustTaskMoney;
    }

    public void setEntrustTaskMoney(Double entrustTaskMoney) {
        this.entrustTaskMoney = entrustTaskMoney;
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

    public Integer getSurveyUserType() {
        return surveyUserType;
    }

    public void setSurveyUserType(Integer surveyUserType) {
        this.surveyUserType = surveyUserType;
    }

    public String getSurveyRemark() {
        return surveyRemark;
    }

    public void setSurveyRemark(String surveyRemark) {
        this.surveyRemark = surveyRemark;
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

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
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

    public Long getCreportId() {
        return creportId;
    }

    public void setCreportId(Long creportId) {
        this.creportId = creportId;
    }

    public String getCreportName() {
        return creportName;
    }

    public void setCreportName(String creportName) {
        this.creportName = creportName;
    }

    public Integer getCreportState() {
        return creportState;
    }

    public void setCreportState(Integer creportState) {
        this.creportState = creportState;
    }

    public Date getCreportDate() {
        return creportDate;
    }

    public void setCreportDate(Date creportDate) {
        this.creportDate = creportDate;
    }

    public Integer getSurveryUserOrgType() {
        return surveryUserOrgType;
    }

    public void setSurveryUserOrgType(Integer surveryUserOrgType) {
        this.surveryUserOrgType = surveryUserOrgType;
    }

    public Integer getIsSun() {
        return isSun;
    }

    public void setIsSun(Integer isSun) {
        this.isSun = isSun;
    }

    public Integer getScoreState() {
        return scoreState;
    }

    public void setScoreState(Integer scoreState) {
        this.scoreState = scoreState;
    }

    public Integer getScoreLevel() {
        return scoreLevel;
    }

    public void setScoreLevel(Integer scoreLevel) {
        this.scoreLevel = scoreLevel;
    }

    public Integer getUserCashState() {
        return userCashState;
    }

    public void setUserCashState(Integer userCashState) {
        this.userCashState = userCashState;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public Date getSurveyEndTime() {
        return surveyEndTime;
    }

    public void setSurveyEndTime(Date surveyEndTime) {
        this.surveyEndTime = surveyEndTime;
    }

    public String getScoreRemark() {
        return scoreRemark;
    }

    public void setScoreRemark(String scoreRemark) {
        this.scoreRemark = scoreRemark;
    }

    public String getSurveyTaskRemark() {
        return surveyTaskRemark;
    }

    public void setSurveyTaskRemark(String surveyTaskRemark) {
        this.surveyTaskRemark = surveyTaskRemark;
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

    public Integer getIsDirectionSuccess() {
        return isDirectionSuccess;
    }

    public void setIsDirectionSuccess(Integer isDirectionSuccess) {
        this.isDirectionSuccess = isDirectionSuccess;
    }

    public CommonFile getCommonFile() {
        return commonFile;
    }

    public void setCommonFile(CommonFile commonFile) {
        this.commonFile = commonFile;
    }

    public SurveyRiskCaseDto getSurveyRiskCase() {
        return surveyRiskCase;
    }

    public void setSurveyRiskCase(SurveyRiskCaseDto surveyRiskCase) {
        this.surveyRiskCase = surveyRiskCase;
    }

    public SurveyRiskCaseInfoDto getSurveyRiskCaseInfo() {
        return surveyRiskCaseInfo;
    }

    public void setSurveyRiskCaseInfo(SurveyRiskCaseInfoDto surveyRiskCaseInfo) {
        this.surveyRiskCaseInfo = surveyRiskCaseInfo;
    }

    public Boolean getIsCurOrg() {
        return isCurOrg;
    }

    public void setIsCurOrg(Boolean isCurOrg) {
        this.isCurOrg = isCurOrg;
    }

    public SurveyFranchiseeDto getSurveyFranchisee() {
        return surveyFranchisee;
    }

    public void setSurveyFranchisee(SurveyFranchiseeDto surveyFranchisee) {
        this.surveyFranchisee = surveyFranchisee;
    }

    public SurveyAssignOrgDto getSurveyAssignOrg() {
        return surveyAssignOrg;
    }

    public void setSurveyAssignOrg(SurveyAssignOrgDto surveyAssignOrg) {
        this.surveyAssignOrg = surveyAssignOrg;
    }

    public List<SurveyTaskTypeDto> getSurveyTaskTypes() {
        return surveyTaskTypes;
    }

    public void setSurveyTaskTypes(List<SurveyTaskTypeDto> surveyTaskTypes) {
        this.surveyTaskTypes = surveyTaskTypes;
    }

    public List<SurveyInvestigatorCaseTypeDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<SurveyInvestigatorCaseTypeDto> tasks) {
        this.tasks = tasks;
    }

    public List<SurveyCaseDirectionDto> getSurveyCaseDirections() {
        return surveyCaseDirections;
    }

    public void setSurveyCaseDirections(List<SurveyCaseDirectionDto> surveyCaseDirections) {
        this.surveyCaseDirections = surveyCaseDirections;
    }

    public List<SurveyInvestigatorCaseDto> getSurveyInvestigatorCases() {
        return surveyInvestigatorCases;
    }

    public void setSurveyInvestigatorCases(List<SurveyInvestigatorCaseDto> surveyInvestigatorCases) {
        this.surveyInvestigatorCases = surveyInvestigatorCases;
    }

    public List<SurveyBackCaseDto> getSurveyBackCases() {
        return surveyBackCases;
    }

    public void setSurveyBackCases(List<SurveyBackCaseDto> surveyBackCases) {
        this.surveyBackCases = surveyBackCases;
    }

    public Integer getOverTimeType() {
        return overTimeType;
    }

    public void setOverTimeType(Integer overTimeType) {
        this.overTimeType = overTimeType;
    }

    public Boolean getShowAddDirectionBtn() {
        return showAddDirectionBtn;
    }

    public void setShowAddDirectionBtn(Boolean showAddDirectionBtn) {
        this.showAddDirectionBtn = showAddDirectionBtn;
    }

    public Boolean getShowCommitBtn() {
        return showCommitBtn;
    }

    public void setShowCommitBtn(Boolean showCommitBtn) {
        this.showCommitBtn = showCommitBtn;
    }

    public Boolean getShowOrgSummaryBtn() {
        return showOrgSummaryBtn;
    }

    public void setShowOrgSummaryBtn(Boolean showOrgSummaryBtn) {
        this.showOrgSummaryBtn = showOrgSummaryBtn;
    }

    public Boolean getShowReportCompletionBtn() {
        return showReportCompletionBtn;
    }

    public void setShowReportCompletionBtn(Boolean showReportCompletionBtn) {
        this.showReportCompletionBtn = showReportCompletionBtn;
    }

    public Boolean getShowOrgCommitBtn() {
        return showOrgCommitBtn;
    }

    public void setShowOrgCommitBtn(Boolean showOrgCommitBtn) {
        this.showOrgCommitBtn = showOrgCommitBtn;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public Boolean getIsUserLetterInfo() {
        return isUserLetterInfo;
    }

    public void setIsUserLetterInfo(Boolean isUserLetterInfo) {
        this.isUserLetterInfo = isUserLetterInfo;
    }

    public Boolean getCurOrg() {
        return isCurOrg;
    }

    public void setCurOrg(Boolean curOrg) {
        isCurOrg = curOrg;
    }

    public Boolean getUserLetterInfo() {
        return isUserLetterInfo;
    }

    public void setUserLetterInfo(Boolean userLetterInfo) {
        isUserLetterInfo = userLetterInfo;
    }

    public String getNullCode() {
        return nullCode;
    }

    public void setNullCode(String nullCode) {
        this.nullCode = nullCode;
    }

    public Integer getSunState() {
        return sunState;
    }

    public void setSunState(Integer sunState) {
        this.sunState = sunState;
    }

    public String getSunRemark() {
        return sunRemark;
    }

    public void setSunRemark(String sunRemark) {
        this.sunRemark = sunRemark;
    }

    public List<SurveyCaseDirectionDto> getCaseDirections() {
        return caseDirections;
    }

    public void setCaseDirections(List<SurveyCaseDirectionDto> caseDirections) {
        this.caseDirections = caseDirections;
    }

    public Integer getSurveyPay() {
        return surveyPay;
    }

    public void setSurveyPay(Integer surveyPay) {
        this.surveyPay = surveyPay;
    }

    public Integer getReturnState() {
        return returnState;
    }

    public void setReturnState(Integer returnState) {
        this.returnState = returnState;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
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

    public String getSurveySummary() {
        return surveySummary;
    }

    public void setSurveySummary(String surveySummary) {
        this.surveySummary = surveySummary;
    }

    public Integer getReviewOff() {
        return reviewOff;
    }

    public void setReviewOff(Integer reviewOff) {
        this.reviewOff = reviewOff;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getSunTime() {
        return sunTime;
    }

    public void setSunTime(Date sunTime) {
        this.sunTime = sunTime;
    }

    public Integer getSunType() {
        return sunType;
    }

    public void setSunType(Integer sunType) {
        this.sunType = sunType;
    }

    public Long getSunUserId() {
        return sunUserId;
    }

    public void setSunUserId(Long sunUserId) {
        this.sunUserId = sunUserId;
    }

    public String getSunUserName() {
        return sunUserName;
    }

    public void setSunUserName(String sunUserName) {
        this.sunUserName = sunUserName;
    }

    public String getSpecialRemark() {
        return specialRemark;
    }

    public void setSpecialRemark(String specialRemark) {
        this.specialRemark = specialRemark;
    }

    public Long getSurveyAssorgCaseId() {
        return surveyAssorgCaseId;
    }

    public void setSurveyAssorgCaseId(Long surveyAssorgCaseId) {
        this.surveyAssorgCaseId = surveyAssorgCaseId;
    }

    public Double getScoreSun() {
        return scoreSun;
    }

    public void setScoreSun(Double scoreSun) {
        this.scoreSun = scoreSun;
    }

    public Double getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(Double baseScore) {
        this.baseScore = baseScore;
    }

    public Double getSunScore() {
        return sunScore;
    }

    public void setSunScore(Double sunScore) {
        this.sunScore = sunScore;
    }

    public Double getBsScore() {
        return bsScore;
    }

    public void setBsScore(Double bsScore) {
        this.bsScore = bsScore;
    }

    public Double getOtherScore() {
        return otherScore;
    }

    public void setOtherScore(Double otherScore) {
        this.otherScore = otherScore;
    }

    public Boolean getShowExpenseReimbursementValue() {
        return showExpenseReimbursementValue;
    }

    public void setShowExpenseReimbursementValue(Boolean showExpenseReimbursementValue) {
        this.showExpenseReimbursementValue = showExpenseReimbursementValue;
    }

    public String getExpenseReimbursementValue() {
        return expenseReimbursementValue;
    }

    public void setExpenseReimbursementValue(String expenseReimbursementValue) {
        this.expenseReimbursementValue = expenseReimbursementValue;
    }

    public Double getInvestigatorReMoney() {
        return investigatorReMoney;
    }

    public void setInvestigatorReMoney(Double investigatorReMoney) {
        this.investigatorReMoney = investigatorReMoney;
    }

    public Integer getReState() {
        return reState;
    }

    public void setReState(Integer reState) {
        this.reState = reState;
    }

    public String getReStateStr() {
        return reStateStr;
    }

    public void setReStateStr(String reStateStr) {
        this.reStateStr = reStateStr;
    }

    public Double getOverdueAgingRate() {
        return overdueAgingRate;
    }

    public void setOverdueAgingRate(Double overdueAgingRate) {
        this.overdueAgingRate = overdueAgingRate;
    }

    public Double getAssessScore() {
        return assessScore;
    }

    public void setAssessScore(Double assessScore) {
        this.assessScore = assessScore;
    }

    public Double getAssessSunScore() {
        return assessSunScore;
    }

    public void setAssessSunScore(Double assessSunScore) {
        this.assessSunScore = assessSunScore;
    }

    public Boolean getRateEdit() {
        return rateEdit;
    }

    public void setRateEdit(Boolean rateEdit) {
        this.rateEdit = rateEdit;
    }

    public Double getAssessBsScore() {
        return assessBsScore;
    }

    public void setAssessBsScore(Double assessBsScore) {
        this.assessBsScore = assessBsScore;
    }

    public Double getAssessOtherScore() {
        return assessOtherScore;
    }

    public void setAssessOtherScore(Double assessOtherScore) {
        this.assessOtherScore = assessOtherScore;
    }

    public Double getAssessBaseScore() {
        return assessBaseScore;
    }

    public void setAssessBaseScore(Double assessBaseScore) {
        this.assessBaseScore = assessBaseScore;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getTaskColor() {
        return taskColor;
    }

    public void setTaskColor(String taskColor) {
        this.taskColor = taskColor;
    }

    public Integer getSurveyUserCaseId() {
        return surveyUserCaseId;
    }

    public void setSurveyUserCaseId(Integer surveyUserCaseId) {
        this.surveyUserCaseId = surveyUserCaseId;
    }

    public List<Map<String, Object>> getUrgPreList() {
        return urgPreList;
    }

    public void setUrgPreList(List<Map<String, Object>> urgPreList) {
        this.urgPreList = urgPreList;
    }

    public Boolean getShowBaoSi() {
        return showBaoSi;
    }

    public void setShowBaoSi(Boolean showBaoSi) {
        this.showBaoSi = showBaoSi;
    }

    public Integer getMechanismType() {
        return mechanismType;
    }

    public void setMechanismType(Integer mechanismType) {
        this.mechanismType = mechanismType;
    }

    public Double getTotalSurveyPoints() {
        return totalSurveyPoints;
    }

    public void setTotalSurveyPoints(Double totalSurveyPoints) {
        this.totalSurveyPoints = totalSurveyPoints;
    }

    public Double getPositiveTotalScore() {
        return positiveTotalScore;
    }

    public void setPositiveTotalScore(Double positiveTotalScore) {
        this.positiveTotalScore = positiveTotalScore;
    }

    public Double getSunMoney() {
        return sunMoney;
    }

    public void setSunMoney(Double sunMoney) {
        this.sunMoney = sunMoney;
    }

    public Integer getInvestigatorType() {
        return investigatorType;
    }

    public void setInvestigatorType(Integer investigatorType) {
        this.investigatorType = investigatorType;
    }

    public Integer getEntrustOrgType() {
        return entrustOrgType;
    }

    public void setEntrustOrgType(Integer entrustOrgType) {
        this.entrustOrgType = entrustOrgType;
    }

    public SurveyInvestigatorCaseSubDto getSurveyInvestigatorCaseSub() {
        return surveyInvestigatorCaseSub;
    }

    public void setSurveyInvestigatorCaseSub(SurveyInvestigatorCaseSubDto surveyInvestigatorCaseSub) {
        this.surveyInvestigatorCaseSub = surveyInvestigatorCaseSub;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public List<CommonFile> getReplyCommonFiles() {
        return replyCommonFiles;
    }

    public void setReplyCommonFiles(List<CommonFile> replyCommonFiles) {
        this.replyCommonFiles = replyCommonFiles;
    }

    public Integer getReplyType() {
        return replyType;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Long getRiskHandleId() {
        return riskHandleId;
    }

    public void setRiskHandleId(Long riskHandleId) {
        this.riskHandleId = riskHandleId;
    }

    public Integer getHaveNwAccount() {
        return haveNwAccount;
    }

    public void setHaveNwAccount(Integer haveNwAccount) {
        this.haveNwAccount = haveNwAccount;
    }

    public Integer getNewCase() {
        return newCase;
    }

    public void setNewCase(Integer newCase) {
        this.newCase = newCase;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public Boolean getShowKey() {
        return showKey;
    }

    public void setShowKey(Boolean showKey) {
        this.showKey = showKey;
    }

    public Boolean getZhongan() {
        return zhongan;
    }

    public void setZhongan(Boolean zhongan) {
        this.zhongan = zhongan;
    }

    public Boolean getUrgent() {
        return urgent;
    }

    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
    }
}