package com.lefancrm.backend.dto;

/**
 * Created by lixianfeng on 2019/4/26.
 */
public class SurveyMoneyDtoDetail {
    private Long orgId;
    private String orgName;
    private Long parentOrgId;
    private String parentOrgName;
    private Double money;
    private Long surveyInvestigatorCaseId;
    private String surveyNo;
    private String surveyPerson;
    private String surveryPersonTel;
    private String entrustOrgName;
    private String surveyUserName;
    private Double entrustMoney;

    private Long servicesId;
    private String servicesName;

    private int orgDays;//机构调查时效
    private int orgDays1;
    private String orgStartTimeStr;
    private String orgEndTimeStr;

    private int caseDays;//案件调查时效
    private int caseDays1;
    private String caseStartTimeStr;
    private String caseEndTimeStr;

    private String surveyDateStr;
    private String entrustDateStr;

    private SurveyInvestigatorCaseDto surveyInvestigatorCase;
    private SurveyRiskCaseInfoDto surveyRiskCaseInfo;
    private SurveyRiskCaseDto surveyRiskCase;
    private Integer agingCheck;//考核时效
    private Integer agingOver;//超期天数
    private String surveyCaseNo;//案件编号
    //取绝对值
    public int getOrgDays1() {
        return Math.abs(orgDays);
    }
    public int getCaseDays1() {
        return Math.abs(caseDays);
    }

    private String orgCreateTimeStr;//机构分派时间
    private Long surveyInfoId;
    private String remarkAll;//案件延期 备注信息
    private Integer areaType;//区域类别
    private Integer agingDay;//考核时效
    private String areaName;

    private Integer isSun;//阳性

    private Double overdueAgingRate;//超期考核时效系数
    private Double assessMoney;//考核前机构价格 + 考核前减损价格

    public int getAgingDay1() {
        if(agingDay!=null){
            return Math.abs(agingDay);
        }
        return 0;
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Long getSurveyInvestigatorCaseId() {
        return surveyInvestigatorCaseId;
    }

    public void setSurveyInvestigatorCaseId(Long surveyInvestigatorCaseId) {
        this.surveyInvestigatorCaseId = surveyInvestigatorCaseId;
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

    public SurveyInvestigatorCaseDto getSurveyInvestigatorCase() {
        return surveyInvestigatorCase;
    }

    public void setSurveyInvestigatorCase(SurveyInvestigatorCaseDto surveyInvestigatorCase) {
        this.surveyInvestigatorCase = surveyInvestigatorCase;
    }

    public SurveyRiskCaseInfoDto getSurveyRiskCaseInfo() {
        return surveyRiskCaseInfo;
    }

    public void setSurveyRiskCaseInfo(SurveyRiskCaseInfoDto surveyRiskCaseInfo) {
        this.surveyRiskCaseInfo = surveyRiskCaseInfo;
    }

    public SurveyRiskCaseDto getSurveyRiskCase() {
        return surveyRiskCase;
    }

    public void setSurveyRiskCase(SurveyRiskCaseDto surveyRiskCase) {
        this.surveyRiskCase = surveyRiskCase;
    }

    public void setOrgDays1(int orgDays1) {
        this.orgDays1 = orgDays1;
    }

    public void setCaseDays1(int caseDays1) {
        this.caseDays1 = caseDays1;
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

    public Integer getAgingCheck() {
        return agingCheck;
    }

    public void setAgingCheck(Integer agingCheck) {
        this.agingCheck = agingCheck;
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
