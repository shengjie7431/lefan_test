package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.model.CommonFile;

import java.util.Date;
import java.util.List;

/**
 * Created by lixianfeng on 2018/12/18.
 */
public class SurveyRiskCaseInfoDto extends SurveyRiskCaseInfo {


    private Boolean help;//是否是互助
    private Boolean isPrimaryUser = true; //true 是 false否
    private Boolean lfSuper;//狄大人平台主管
    private Boolean lfManager;//狄大人平台经理
    private Boolean lfUpload;//狄大人平台制作报告
    private Boolean lfCommit;//狄大人平台提交终审
    private Boolean lxfTest;
    private Boolean surveyAgentEntrust;//调查代理委托人权限
    private CommonFile commonFile;//主调查报告
    private SurveyRiskCase surveyRiskCase;
    private List<SurveyTaskType> surveyTaskTypes;
    private String surveyTaskTypeIds;
    private List<SurveyInvestigatorCaseDto> surveyInvestigatorCases;
    private List<SurveyCaseDirectionDto> surveyCaseDirections;
    private SurveyAssignOrgDto currentSurveyAssignOrg;//当前操作的机构案件信息
    private List<SurveyAssignOrgDto> surveyAssignOrgs;//已分配机构列表
    private SurveyBackCaseDto currentSurveyBackCase;//当前操作的逆向案件信息
    //费用清单
    private List<SurveyFeeDetails> surveyFeeDetails;
    //回复清单
    private List<SurveyBackReply> surveyBackReplies;

    private Boolean isOverTime;//截止日期是否超时

    private BillingApply billingApply;//开票信息
    private Double orgPrice1;//机构价格 基本费
    private Double orgPrice2;//机构价格 减损奖励

    private Double surveyOKMoney;//调查方确认结算价格
    private Double surveryOKReLosses;

    private Long riskCaseId;
    private String riskCaseSurveyNo;
    private String riskCaseSurveyPerson;
    private String riskCaseSurveryPersonTel;
    private Integer riskCaseSex;
    private String riskCasePolicyNo;
    private String riskCaseClaimsNo;
    private Double riskCaseClaimsMoney;
    private Long riskCaseEntrustUserId;
    private String riskCaseEntrustUserName;
    private Long riskCaseEntrustOrgId;
    private String riskCaseEntrustOrgName;
    private Date riskCaseEntrustTime;
    private int fileSize;
    private Boolean showExpenseReimbursementValue;//是否显示费用报销数值
    private Double totalMoney;//费用报销合计

    private Integer colorTimeType;// （1、红色；2、黄色；3、黑色）需求：用于“调查审核”菜单（显示不同颜色：当前时间-案件截止时间；超期 显示红色；2天之内整条显示黄色，2天之外）

    private SurveyConsignorReportRule surveyConsignorReportRule;//模板
    private SurveyConsignor surveyConsignor;

    private int efficiency;//案件时效
    private String efficiencyState;//案件时效具体状态：(场景时效跟踪菜单)
    private String efficiencyStateColor;//案件时效具体状态颜色：(场景时效跟踪菜单)

    private List<SurveyAssignOrg> returnAssignOrgs;//主动退回的机构
    private List<SurveyInvestigatorCase> returnInvestigatorCases;//主动退回的调查员

    private Date reviewTime;
    private String surveyCaseNo;
    private String serviceName;
    private String surveyPerson;
    private Double applyinsMoney;//申请委托方价格

    private String claimsNo;//互助案件编号

    /**
     * 填写报告结论 机构名称
     */
    private String reportCompletionOrgName;

    /**
     * 打卡次数
     */
    private Integer punchClockCount;

    private String surveyOrgNames;

    private Integer archivesState;//归档状态

    private Integer efficiencyAttr;

    private Double sunMoney;//阳性奖励

    private Boolean openBill;//是否开票

    private String departmentName;

    private List<String> top3insures;

    public String getClaimsNo() {
        return claimsNo;
    }

    public void setClaimsNo(String claimsNo) {
        this.claimsNo = claimsNo;
    }

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public Double getApplyinsMoney() {
        return applyinsMoney;
    }

    public void setApplyinsMoney(Double applyinsMoney) {
        this.applyinsMoney = applyinsMoney;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Boolean getLfSuper() {
        return lfSuper;
    }

    public CommonFile getCommonFile() {
        return commonFile;
    }

    public void setCommonFile(CommonFile commonFile) {
        this.commonFile = commonFile;
    }

    public void setLfSuper(Boolean lfSuper) {
        this.lfSuper = lfSuper;
    }

    public Boolean getLfManager() {
        return lfManager;
    }

    public void setLfManager(Boolean lfManager) {
        this.lfManager = lfManager;
    }

    public SurveyRiskCase getSurveyRiskCase() {
        return surveyRiskCase;
    }

    public void setSurveyRiskCase(SurveyRiskCase surveyRiskCase) {
        this.surveyRiskCase = surveyRiskCase;
    }

    public List<SurveyTaskType> getSurveyTaskTypes() {
        return surveyTaskTypes;
    }

    public void setSurveyTaskTypes(List<SurveyTaskType> surveyTaskTypes) {
        this.surveyTaskTypes = surveyTaskTypes;
    }

    public List<SurveyInvestigatorCaseDto> getSurveyInvestigatorCases() {
        return surveyInvestigatorCases;
    }

    public void setSurveyInvestigatorCases(List<SurveyInvestigatorCaseDto> surveyInvestigatorCases) {
        this.surveyInvestigatorCases = surveyInvestigatorCases;
    }

    public List<SurveyCaseDirectionDto> getSurveyCaseDirections() {
        return surveyCaseDirections;
    }

    public void setSurveyCaseDirections(List<SurveyCaseDirectionDto> surveyCaseDirections) {
        this.surveyCaseDirections = surveyCaseDirections;
    }

    public boolean isOverTime() {
        return isOverTime;
    }

    public List<SurveyFeeDetails> getSurveyFeeDetails() {
        return surveyFeeDetails;
    }

    public void setSurveyFeeDetails(List<SurveyFeeDetails> surveyFeeDetails) {
        this.surveyFeeDetails = surveyFeeDetails;
    }

    public List<SurveyBackReply> getSurveyBackReplies() {
        return surveyBackReplies;
    }

    public void setSurveyBackReplies(List<SurveyBackReply> surveyBackReplies) {
        this.surveyBackReplies = surveyBackReplies;
    }

    public List<SurveyAssignOrgDto> getSurveyAssignOrgs() {
        return surveyAssignOrgs;
    }

    public void setSurveyAssignOrgs(List<SurveyAssignOrgDto> surveyAssignOrgs) {
        this.surveyAssignOrgs = surveyAssignOrgs;
    }

    public SurveyAssignOrgDto getCurrentSurveyAssignOrg() {
        return currentSurveyAssignOrg;
    }

    public void setCurrentSurveyAssignOrg(SurveyAssignOrgDto currentSurveyAssignOrg) {
        this.currentSurveyAssignOrg = currentSurveyAssignOrg;
    }

    public Boolean getIsOverTime() {
        return isOverTime;
    }

    public void setIsOverTime(Boolean isOverTime) {
        this.isOverTime = isOverTime;
    }

    public BillingApply getBillingApply() {
        return billingApply;
    }

    public void setBillingApply(BillingApply billingApply) {
        this.billingApply = billingApply;
    }

    public SurveyBackCaseDto getCurrentSurveyBackCase() {
        return currentSurveyBackCase;
    }

    public void setCurrentSurveyBackCase(SurveyBackCaseDto currentSurveyBackCase) {
        this.currentSurveyBackCase = currentSurveyBackCase;
    }

    public Boolean getIsPrimaryUser() {
        return isPrimaryUser;
    }

    public void setIsPrimaryUser(Boolean isPrimaryUser) {
        this.isPrimaryUser = isPrimaryUser;
    }

    public Boolean getLfUpload() {
        return lfUpload;
    }

    public void setLfUpload(Boolean lfUpload) {
        this.lfUpload = lfUpload;
    }

    public Boolean getLfCommit() {
        return lfCommit;
    }

    public void setLfCommit(Boolean lfCommit) {
        this.lfCommit = lfCommit;
    }

    public Double getOrgPrice1() {
        return orgPrice1;
    }

    public void setOrgPrice1(Double orgPrice1) {
        this.orgPrice1 = orgPrice1;
    }

    public Double getOrgPrice2() {
        return orgPrice2;
    }

    public void setOrgPrice2(Double orgPrice2) {
        this.orgPrice2 = orgPrice2;
    }

    public Double getSurveyOKMoney() {
        return surveyOKMoney;
    }

    public void setSurveyOKMoney(Double surveyOKMoney) {
        this.surveyOKMoney = surveyOKMoney;
    }

    public Double getSurveryOKReLosses() {
        return surveryOKReLosses;
    }

    public void setSurveryOKReLosses(Double surveryOKReLosses) {
        this.surveryOKReLosses = surveryOKReLosses;
    }

    public Long getRiskCaseId() {
        return riskCaseId;
    }

    public void setRiskCaseId(Long riskCaseId) {
        this.riskCaseId = riskCaseId;
    }

    public String getRiskCaseSurveyNo() {
        return riskCaseSurveyNo;
    }

    public void setRiskCaseSurveyNo(String riskCaseSurveyNo) {
        this.riskCaseSurveyNo = riskCaseSurveyNo;
    }

    public String getRiskCaseSurveyPerson() {
        return riskCaseSurveyPerson;
    }

    public void setRiskCaseSurveyPerson(String riskCaseSurveyPerson) {
        this.riskCaseSurveyPerson = riskCaseSurveyPerson;
    }

    public String getRiskCaseSurveryPersonTel() {
        return riskCaseSurveryPersonTel;
    }

    public void setRiskCaseSurveryPersonTel(String riskCaseSurveryPersonTel) {
        this.riskCaseSurveryPersonTel = riskCaseSurveryPersonTel;
    }

    public Integer getRiskCaseSex() {
        return riskCaseSex;
    }

    public void setRiskCaseSex(Integer riskCaseSex) {
        this.riskCaseSex = riskCaseSex;
    }

    public String getRiskCasePolicyNo() {
        return riskCasePolicyNo;
    }

    public void setRiskCasePolicyNo(String riskCasePolicyNo) {
        this.riskCasePolicyNo = riskCasePolicyNo;
    }

    public String getRiskCaseClaimsNo() {
        return riskCaseClaimsNo;
    }

    public void setRiskCaseClaimsNo(String riskCaseClaimsNo) {
        this.riskCaseClaimsNo = riskCaseClaimsNo;
    }

    public Double getRiskCaseClaimsMoney() {
        return riskCaseClaimsMoney;
    }

    public void setRiskCaseClaimsMoney(Double riskCaseClaimsMoney) {
        this.riskCaseClaimsMoney = riskCaseClaimsMoney;
    }

    public Long getRiskCaseEntrustUserId() {
        return riskCaseEntrustUserId;
    }

    public void setRiskCaseEntrustUserId(Long riskCaseEntrustUserId) {
        this.riskCaseEntrustUserId = riskCaseEntrustUserId;
    }

    public String getRiskCaseEntrustUserName() {
        return riskCaseEntrustUserName;
    }

    public void setRiskCaseEntrustUserName(String riskCaseEntrustUserName) {
        this.riskCaseEntrustUserName = riskCaseEntrustUserName;
    }

    public Long getRiskCaseEntrustOrgId() {
        return riskCaseEntrustOrgId;
    }

    public void setRiskCaseEntrustOrgId(Long riskCaseEntrustOrgId) {
        this.riskCaseEntrustOrgId = riskCaseEntrustOrgId;
    }

    public String getRiskCaseEntrustOrgName() {
        return riskCaseEntrustOrgName;
    }

    public void setRiskCaseEntrustOrgName(String riskCaseEntrustOrgName) {
        this.riskCaseEntrustOrgName = riskCaseEntrustOrgName;
    }

    public Integer getColorTimeType() {
        return colorTimeType;
    }

    public void setColorTimeType(Integer colorTimeType) {
        this.colorTimeType = colorTimeType;
    }

    public SurveyConsignorReportRule getSurveyConsignorReportRule() {
        return surveyConsignorReportRule;
    }

    public void setSurveyConsignorReportRule(SurveyConsignorReportRule surveyConsignorReportRule) {
        this.surveyConsignorReportRule = surveyConsignorReportRule;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public String getSurveyTaskTypeIds() {
        return surveyTaskTypeIds;
    }

    public void setSurveyTaskTypeIds(String surveyTaskTypeIds) {
        this.surveyTaskTypeIds = surveyTaskTypeIds;
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

    public Boolean getSurveyAgentEntrust() {
        return surveyAgentEntrust;
    }

    public void setSurveyAgentEntrust(Boolean surveyAgentEntrust) {
        this.surveyAgentEntrust = surveyAgentEntrust;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public List<SurveyAssignOrg> getReturnAssignOrgs() {
        return returnAssignOrgs;
    }

    public void setReturnAssignOrgs(List<SurveyAssignOrg> returnAssignOrgs) {
        this.returnAssignOrgs = returnAssignOrgs;
    }

    public List<SurveyInvestigatorCase> getReturnInvestigatorCases() {
        return returnInvestigatorCases;
    }

    public void setReturnInvestigatorCases(List<SurveyInvestigatorCase> returnInvestigatorCases) {
        this.returnInvestigatorCases = returnInvestigatorCases;
    }

    public SurveyConsignor getSurveyConsignor() {
        return surveyConsignor;
    }

    public void setSurveyConsignor(SurveyConsignor surveyConsignor) {
        this.surveyConsignor = surveyConsignor;
    }

    public Boolean getLxfTest() {
        return lxfTest;
    }

    public void setLxfTest(Boolean lxfTest) {
        this.lxfTest = lxfTest;
    }

    public Boolean getHelp() {
        return help;
    }

    public void setHelp(Boolean help) {
        this.help = help;
    }

    public Boolean getShowExpenseReimbursementValue() {
        return showExpenseReimbursementValue;
    }

    public void setShowExpenseReimbursementValue(Boolean showExpenseReimbursementValue) {
        this.showExpenseReimbursementValue = showExpenseReimbursementValue;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getRiskCaseEntrustTime() {
        return riskCaseEntrustTime;
    }

    public void setRiskCaseEntrustTime(Date riskCaseEntrustTime) {
        this.riskCaseEntrustTime = riskCaseEntrustTime;
    }

    public Integer getPunchClockCount() {
        return punchClockCount;
    }

    public void setPunchClockCount(Integer punchClockCount) {
        this.punchClockCount = punchClockCount;
    }

    public String getReportCompletionOrgName() {
        return reportCompletionOrgName;
    }

    public void setReportCompletionOrgName(String reportCompletionOrgName) {
        this.reportCompletionOrgName = reportCompletionOrgName;
    }

    public String getSurveyOrgNames() {
        return surveyOrgNames;
    }

    public void setSurveyOrgNames(String surveyOrgNames) {
        this.surveyOrgNames = surveyOrgNames;
    }

    public Integer getArchivesState() {
        return archivesState;
    }

    public void setArchivesState(Integer archivesState) {
        this.archivesState = archivesState;
    }

    public Integer getEfficiencyAttr() {
        return efficiencyAttr;
    }

    public void setEfficiencyAttr(Integer efficiencyAttr) {
        this.efficiencyAttr = efficiencyAttr;
    }

    public Double getSunMoney() {
        return sunMoney;
    }

    public void setSunMoney(Double sunMoney) {
        this.sunMoney = sunMoney;
    }

    public Boolean getOpenBill() {
        return openBill;
    }

    public void setOpenBill(Boolean openBill) {
        this.openBill = openBill;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<String> getTop3insures() {
        return top3insures;
    }

    public void setTop3insures(List<String> top3insures) {
        this.top3insures = top3insures;
    }
}
