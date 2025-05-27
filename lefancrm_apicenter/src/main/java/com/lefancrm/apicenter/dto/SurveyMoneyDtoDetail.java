package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyInvestigatorCase;
import com.lefancrm.apicenter.model.SurveyRiskCase;
import com.lefancrm.apicenter.model.SurveyRiskCaseInfo;

/**
 * Created by lixianfeng on 2019/4/26.
 */
public class SurveyMoneyDtoDetail {
    private Long orgId;
    private String orgName;
    private Long parentOrgId;
    private String parentOrgName;
    private Double money;
    private String surveyNo;
    private String surveyPerson;
    private String surveryPersonTel;
    private String entrustOrgName;
    private String surveyUserName;
    private Double entrustMoney;

    private Long surveyAssignOrgId;//机构案件id
    private int orgDays;//机构调查时效
    private String orgStartTimeStr;//机构提交时间
    private String orgEndTimeStr;   //机构案件截止时间
    private Integer extensionState;

    private Long servicesId;
    private String servicesName;

    private int caseDays;//案件调查时效
    private String caseStartTimeStr; //案件委托时间
    private String caseEndTimeStr;

    private String surveyDateStr;   //提交保司时间
    private String entrustDateStr;  //保司审核通过时间

    private Long surveyInvestigatorCaseId;

    private SurveyInvestigatorCase surveyInvestigatorCase;
    private SurveyRiskCaseInfo surveyRiskCaseInfo;
    private SurveyRiskCase surveyRiskCase;

    private String orgCreateTimeStr;//机构分派时间
    private Long surveyInfoId;
    private String remarkAll;//案件延期 备注信息

    private Integer areaType;//区域类别
    private Integer agingDay;//机构时效
    private Integer agingCheck;//考核时效
    private Integer agingOver;//超期天数
    private String areaName;
    private String surveyCaseNo;//案件编号

    private Integer efficiencyAttr; //委托方'时效设置（1：工作日；2、自然日）
    private String utterEndTime; //机构绝对截止日期（根据区域匹配的截止日期）

    private Integer isSun;//阳性

    private Double overdueAgingRate;//超期考核时效系数
    private Double assessMoney;//考核前机构价格 + 考核前减损价格

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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public String getSurveryPersonTel() {
        return surveryPersonTel;
    }

    public void setSurveryPersonTel(String surveryPersonTel) {
        this.surveryPersonTel = surveryPersonTel;
    }

    public String getEntrustOrgName() {
        return entrustOrgName;
    }

    public void setEntrustOrgName(String entrustOrgName) {
        this.entrustOrgName = entrustOrgName;
    }

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
    }

    public Double getEntrustMoney() {
        return entrustMoney;
    }

    public void setEntrustMoney(Double entrustMoney) {
        this.entrustMoney = entrustMoney;
    }

    public int getOrgDays() {
        return orgDays;
    }

    public void setOrgDays(int orgDays) {
        this.orgDays = orgDays;
    }

    public String getOrgStartTimeStr() {
        return orgStartTimeStr;
    }

    public void setOrgStartTimeStr(String orgStartTimeStr) {
        this.orgStartTimeStr = orgStartTimeStr;
    }

    public String getOrgEndTimeStr() {
        return orgEndTimeStr;
    }

    public void setOrgEndTimeStr(String orgEndTimeStr) {
        this.orgEndTimeStr = orgEndTimeStr;
    }

    public int getCaseDays() {
        return caseDays;
    }

    public void setCaseDays(int caseDays) {
        this.caseDays = caseDays;
    }

    public String getCaseStartTimeStr() {
        return caseStartTimeStr;
    }

    public void setCaseStartTimeStr(String caseStartTimeStr) {
        this.caseStartTimeStr = caseStartTimeStr;
    }

    public String getCaseEndTimeStr() {
        return caseEndTimeStr;
    }

    public void setCaseEndTimeStr(String caseEndTimeStr) {
        this.caseEndTimeStr = caseEndTimeStr;
    }

    public String getSurveyDateStr() {
        return surveyDateStr;
    }

    public void setSurveyDateStr(String surveyDateStr) {
        this.surveyDateStr = surveyDateStr;
    }

    public String getEntrustDateStr() {
        return entrustDateStr;
    }

    public void setEntrustDateStr(String entrustDateStr) {
        this.entrustDateStr = entrustDateStr;
    }

    public Long getSurveyInvestigatorCaseId() {
        return surveyInvestigatorCaseId;
    }

    public void setSurveyInvestigatorCaseId(Long surveyInvestigatorCaseId) {
        this.surveyInvestigatorCaseId = surveyInvestigatorCaseId;
    }

    public SurveyInvestigatorCase getSurveyInvestigatorCase() {
        return surveyInvestigatorCase;
    }

    public void setSurveyInvestigatorCase(SurveyInvestigatorCase surveyInvestigatorCase) {
        this.surveyInvestigatorCase = surveyInvestigatorCase;
    }

    public SurveyRiskCaseInfo getSurveyRiskCaseInfo() {
        return surveyRiskCaseInfo;
    }

    public void setSurveyRiskCaseInfo(SurveyRiskCaseInfo surveyRiskCaseInfo) {
        this.surveyRiskCaseInfo = surveyRiskCaseInfo;
    }

    public SurveyRiskCase getSurveyRiskCase() {
        return surveyRiskCase;
    }

    public void setSurveyRiskCase(SurveyRiskCase surveyRiskCase) {
        this.surveyRiskCase = surveyRiskCase;
    }

    public String getOrgCreateTimeStr() {
        return orgCreateTimeStr;
    }

    public void setOrgCreateTimeStr(String orgCreateTimeStr) {
        this.orgCreateTimeStr = orgCreateTimeStr;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public String getRemarkAll() {
        return remarkAll;
    }

    public void setRemarkAll(String remarkAll) {
        this.remarkAll = remarkAll;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public Integer getAgingDay() {
        return agingDay;
    }

    public void setAgingDay(Integer agingDay) {
        this.agingDay = agingDay;
    }

    public Integer getEfficiencyAttr() {
        return efficiencyAttr;
    }

    public void setEfficiencyAttr(Integer efficiencyAttr) {
        this.efficiencyAttr = efficiencyAttr;
    }

    public String getUtterEndTime() {
        return utterEndTime;
    }

    public void setUtterEndTime(String utterEndTime) {
        this.utterEndTime = utterEndTime;
    }

    public Long getSurveyAssignOrgId() {
        return surveyAssignOrgId;
    }

    public void setSurveyAssignOrgId(Long surveyAssignOrgId) {
        this.surveyAssignOrgId = surveyAssignOrgId;
    }

    public Long getServicesId() {
        return servicesId;
    }

    public void setServicesId(Long servicesId) {
        this.servicesId = servicesId;
    }

    public Integer getExtensionState() {
        return extensionState;
    }

    public void setExtensionState(Integer extensionState) {
        this.extensionState = extensionState;
    }

    public Long getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(Long parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName;
    }

    public Integer getAgingCheck() {
        return agingCheck;
    }

    public void setAgingCheck(Integer agingCheck) {
        this.agingCheck = agingCheck;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public Integer getAgingOver() {
        return agingOver;
    }

    public void setAgingOver(Integer agingOver) {
        this.agingOver = agingOver;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }

    public Integer getIsSun() {
        return isSun;
    }

    public void setIsSun(Integer isSun) {
        this.isSun = isSun;
    }

    public Double getOverdueAgingRate() {
        return overdueAgingRate;
    }

    public void setOverdueAgingRate(Double overdueAgingRate) {
        this.overdueAgingRate = overdueAgingRate;
    }

    public Double getAssessMoney() {
        return assessMoney;
    }

    public void setAssessMoney(Double assessMoney) {
        this.assessMoney = assessMoney;
    }
}
