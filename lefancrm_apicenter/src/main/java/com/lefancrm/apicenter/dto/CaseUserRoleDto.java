package com.lefancrm.apicenter.dto;

/**
 * Created by lixianfeng on 2018/5/16.
 */
public class CaseUserRoleDto {
    private Boolean assessor;//评估员
    private Boolean assessorManager;//评估主管
    private Boolean riskSuper;//风控主管

    private Boolean claims;//索赔主管
    private Boolean claimsManager;//索赔主管

    private Boolean legal;//诉讼员
    private Boolean legalManager;//诉讼主管  同  索赔主管

    private Boolean customer;//客服

    private Boolean marketingManager;//市场总监


    private Boolean isAssess = false;//是否评估师
    private Boolean isComplex  = false;//是否综合内勤
    private Boolean isAssessSuper  = false;//是否评估主管
    private Boolean isAssessManager  = false;//是否评估经理

    private Boolean isFinancialAuditor  = false;//是否财务审核
    private Boolean isFinancial  = false;//是否财务部

    private Boolean customerManager;//客服主管


    private Boolean surveyEntrust;//调查委托人
    private Boolean surveyAgentEntrust;//调查代理委托人权限

    private Boolean isBelong;//狄大人案件归属改派
    private Boolean isTest;//测试角色

    private Boolean isFinalUserManage;//复审人员主管

    public Boolean getAssessor() {
        return assessor;
    }

    public void setAssessor(Boolean assessor) {
        this.assessor = assessor;
    }

    public Boolean getAssessorManager() {
        return assessorManager;
    }

    public void setAssessorManager(Boolean assessorManager) {
        this.assessorManager = assessorManager;
    }

    public Boolean getRiskSuper() {
        return riskSuper;
    }

    public void setRiskSuper(Boolean riskSuper) {
        this.riskSuper = riskSuper;
    }

    public Boolean getClaims() {
        return claims;
    }

    public void setClaims(Boolean claims) {
        this.claims = claims;
    }

    public Boolean getClaimsManager() {
        return claimsManager;
    }

    public void setClaimsManager(Boolean claimsManager) {
        this.claimsManager = claimsManager;
    }

    public Boolean getLegal() {
        return legal;
    }

    public void setLegal(Boolean legal) {
        this.legal = legal;
    }

    public Boolean getLegalManager() {
        return legalManager;
    }

    public void setLegalManager(Boolean legalManager) {
        this.legalManager = legalManager;
    }

    public Boolean getCustomer() {
        return customer;
    }

    public void setCustomer(Boolean customer) {
        this.customer = customer;
    }

    public Boolean getMarketingManager() {
        return marketingManager;
    }

    public void setMarketingManager(Boolean marketingManager) {
        this.marketingManager = marketingManager;
    }

    public Boolean getIsAssess() {
        return isAssess;
    }

    public void setIsAssess(Boolean isAssess) {
        this.isAssess = isAssess;
    }

    public Boolean getIsComplex() {
        return isComplex;
    }

    public void setIsComplex(Boolean isComplex) {
        this.isComplex = isComplex;
    }

    public Boolean getIsAssessSuper() {
        return isAssessSuper;
    }

    public void setIsAssessSuper(Boolean isAssessSuper) {
        this.isAssessSuper = isAssessSuper;
    }

    public Boolean getIsAssessManager() {
        return isAssessManager;
    }

    public void setIsAssessManager(Boolean isAssessManager) {
        this.isAssessManager = isAssessManager;
    }

    public Boolean getIsFinancialAuditor() {
        return isFinancialAuditor;
    }

    public void setIsFinancialAuditor(Boolean isFinancialAuditor) {
        this.isFinancialAuditor = isFinancialAuditor;
    }

    public Boolean getIsFinancial() {
        return isFinancial;
    }

    public void setIsFinancial(Boolean isFinancial) {
        this.isFinancial = isFinancial;
    }

    public Boolean getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(Boolean customerManager) {
        this.customerManager = customerManager;
    }

    public Boolean getSurveyAgentEntrust() {
        return surveyAgentEntrust;
    }

    public void setSurveyAgentEntrust(Boolean surveyAgentEntrust) {
        this.surveyAgentEntrust = surveyAgentEntrust;
    }

    public Boolean getSurveyEntrust() {
        return surveyEntrust;
    }

    public void setSurveyEntrust(Boolean surveyEntrust) {
        this.surveyEntrust = surveyEntrust;
    }

    public Boolean getIsBelong() {
        return isBelong;
    }

    public void setIsBelong(Boolean isBelong) {
        this.isBelong = isBelong;
    }

    public Boolean getIsTest() {
        return isTest;
    }

    public void setIsTest(Boolean isTest) {
        this.isTest = isTest;
    }

    public Boolean getIsFinalUserManage() {
        return isFinalUserManage;
    }

    public void setIsFinalUserManage(Boolean isFinalUserManage) {
        this.isFinalUserManage = isFinalUserManage;
    }
}
