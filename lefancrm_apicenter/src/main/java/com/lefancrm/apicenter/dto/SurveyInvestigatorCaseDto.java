package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.model.CommonFile;

import java.util.Date;
import java.util.List;

/**
 * Created by lixianfeng on 2018/12/18.
 */
public class SurveyInvestigatorCaseDto extends SurveyInvestigatorCase {
    private Double assessBaseScore;//基础分
    private Double baseScore;//基础分
    private Double sunScore;//阳性分

    private CommonFile commonFile;//调查报告
    private SurveyRiskCase surveyRiskCase;
    private SurveyRiskCaseInfo surveyRiskCaseInfo;
    private Boolean isCurOrg;//是否是当前机构下的案件  true是 false否
    private SurveyFranchisee surveyFranchisee;//调查调查方机构信息
    private SurveyAssignOrg surveyAssignOrg;//主调查员 的 机构案件信息

    private List<SurveyTaskType> surveyTaskTypes;
    //调查员任务类型列表
    private List<SurveyInvestigatorCaseType> tasks;
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

    private Boolean isUserLetterInfo = true; //是否使用了介绍信
    private String nullCode;//基础信息为NULL的编码 如果不为双引号说明 基础信息不完整
    private List<SurveyCaseDirection> caseDirections;
    private String backgroundColor;//背景色
    private String efficiencyState;//案件时效具体状态
    private String efficiencyStateColor;//案件时效具体状态颜色
    private Boolean showExpenseReimbursementValue;//是否显示费用报销数值
    private Boolean showBaoSi;//是否显示费用报销数值
    private String expenseReimbursementValue;//费用报销数值

    private Double investigatorReMoney; //费用报销数值
    private Integer reState;//费用合计报销状态
    private String reStateStr;//费用报销状态名称

    private Integer efficiencyAttr;

    //保司通过时间
    private Date reviewTime;

    //委托日期
    private Date entrustTime;

    //机构任务类型ID
    private Integer servicesId;

    private String servicesName;

    //被调查人
    private String surveyPerson;

    //案件编号
    private String surveyNo;

    //累计积分
    private Double cumulativePoints;

    //机构id
    private Integer orgId;

    private Integer surveyUserCaseId;

    private String surveyCaseNo;

    private Integer mechanismType;

    private Double totalSurveyPoints;

    private Double positiveTotalScore;

    private Integer entrustOrgType;

    private String replyContent;

    private String replyFiles;

    private List<CommonFile> replyCommonFiles;

    private Integer caseNum;

    private Integer replyType;

    private Integer replyId;

    private Long riskHandleId;

    private Integer haveNwAccount;

    private Boolean zhongan;

    private Boolean urgent;

//    private Boolean showKey = false;

    public Double getCumulativePoints() {
        return cumulativePoints;
    }

    public void setCumulativePoints(Double cumulativePoints) {
        this.cumulativePoints = cumulativePoints;
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

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    private Boolean rateEdit;

    public String getReStateStr() {
        return reStateStr;
    }

    public void setReStateStr(String reStateStr) {
        this.reStateStr = reStateStr;
    }

    public String getExpenseReimbursementValue() {
        return expenseReimbursementValue;
    }

    public void setExpenseReimbursementValue(String expenseReimbursementValue) {
        this.expenseReimbursementValue = expenseReimbursementValue;
    }
    public CommonFile getCommonFile() {
        return commonFile;
    }

    public void setCommonFile(CommonFile commonFile) {
        this.commonFile = commonFile;
    }

    public SurveyRiskCase getSurveyRiskCase() {
        return surveyRiskCase;
    }

    public void setSurveyRiskCase(SurveyRiskCase surveyRiskCase) {
        this.surveyRiskCase = surveyRiskCase;
    }

    public SurveyRiskCaseInfo getSurveyRiskCaseInfo() {
        return surveyRiskCaseInfo;
    }

    public void setSurveyRiskCaseInfo(SurveyRiskCaseInfo surveyRiskCaseInfo) {
        this.surveyRiskCaseInfo = surveyRiskCaseInfo;
    }

    public Boolean getIsCurOrg() {
        return isCurOrg;
    }

    public void setIsCurOrg(Boolean isCurOrg) {
        this.isCurOrg = isCurOrg;
    }

    public SurveyFranchisee getSurveyFranchisee() {
        return surveyFranchisee;
    }

    public void setSurveyFranchisee(SurveyFranchisee surveyFranchisee) {
        this.surveyFranchisee = surveyFranchisee;
    }

    public SurveyAssignOrg getSurveyAssignOrg() {
        return surveyAssignOrg;
    }

    public void setSurveyAssignOrg(SurveyAssignOrg surveyAssignOrg) {
        this.surveyAssignOrg = surveyAssignOrg;
    }

    public List<SurveyTaskType> getSurveyTaskTypes() {
        return surveyTaskTypes;
    }

    public void setSurveyTaskTypes(List<SurveyTaskType> surveyTaskTypes) {
        this.surveyTaskTypes = surveyTaskTypes;
    }

    public List<SurveyInvestigatorCaseType> getTasks() {
        return tasks;
    }

    public void setTasks(List<SurveyInvestigatorCaseType> tasks) {
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

    public String getNullCode() {
        return nullCode;
    }

    public void setNullCode(String nullCode) {
        this.nullCode = nullCode;
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

    public List<SurveyCaseDirection> getCaseDirections() {
        return caseDirections;
    }

    public void setCaseDirections(List<SurveyCaseDirection> caseDirections) {
        this.caseDirections = caseDirections;
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

    public Boolean getShowExpenseReimbursementValue() {
        return showExpenseReimbursementValue;
    }

    public void setShowExpenseReimbursementValue(Boolean showExpenseReimbursementValue) {
        this.showExpenseReimbursementValue = showExpenseReimbursementValue;
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

    public Integer getEfficiencyAttr() {
        return efficiencyAttr;
    }

    public void setEfficiencyAttr(Integer efficiencyAttr) {
        this.efficiencyAttr = efficiencyAttr;
    }

    public Boolean getRateEdit() {
        return rateEdit;
    }

    public void setRateEdit(Boolean rateEdit) {
        this.rateEdit = rateEdit;
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

    public Integer getSurveyUserCaseId() {
        return surveyUserCaseId;
    }

    public void setSurveyUserCaseId(Integer surveyUserCaseId) {
        this.surveyUserCaseId = surveyUserCaseId;
    }

    public Boolean getShowBaoSi() {
        return showBaoSi;
    }

    public void setShowBaoSi(Boolean showBaoSi) {
        this.showBaoSi = showBaoSi;
    }

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
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

    public Integer getEntrustOrgType() {
        return entrustOrgType;
    }

    public void setEntrustOrgType(Integer entrustOrgType) {
        this.entrustOrgType = entrustOrgType;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyFiles() {
        return replyFiles;
    }

    public void setReplyFiles(String replyFiles) {
        this.replyFiles = replyFiles;
    }

    public List<CommonFile> getReplyCommonFiles() {
        return replyCommonFiles;
    }

    public void setReplyCommonFiles(List<CommonFile> replyCommonFiles) {
        this.replyCommonFiles = replyCommonFiles;
    }

    public Integer getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(Integer caseNum) {
        this.caseNum = caseNum;
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

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
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

    //    public Boolean getShowKey() {
//        return showKey;
//    }
//
//    public void setShowKey(Boolean showKey) {
//        this.showKey = showKey;
//    }
}
