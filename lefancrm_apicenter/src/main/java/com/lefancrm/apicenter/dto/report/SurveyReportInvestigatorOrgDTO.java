package com.lefancrm.apicenter.dto.report;

import com.lefancrm.apicenter.model.SurveyFranchisee;
import com.lefancrm.apicenter.model.SurveyInvestigatorOrgReport;

public class SurveyReportInvestigatorOrgDTO {

    //基础信息
    private SurveyFranchisee surveyFranchisee;
    private int basSurveyUsers;     //名下人员数量
    private Double basMoneyNotAcc;  //未结算费用
    private int basOrgNotAss;       //机构待分派
    private int basOrgSurveying;    //机构调查中
    private int basOrgReviewing;    //初审中
    private int basLefanApv;        //平台待终审
    private int basEntrustApv;      //保司待审核
    private int basNotClose;        //财务待结案

    //关键指标 (调查方机构报表)
    private SurveyInvestigatorOrgReport surveyInvestigatorOrgReport;

    public SurveyFranchisee getSurveyFranchisee() {
        return surveyFranchisee;
    }

    public void setSurveyFranchisee(SurveyFranchisee surveyFranchisee) {
        this.surveyFranchisee = surveyFranchisee;
    }

    public int getBasSurveyUsers() {
        return basSurveyUsers;
    }

    public void setBasSurveyUsers(int basSurveyUsers) {
        this.basSurveyUsers = basSurveyUsers;
    }

    public Double getBasMoneyNotAcc() {
        return basMoneyNotAcc;
    }

    public void setBasMoneyNotAcc(Double basMoneyNotAcc) {
        this.basMoneyNotAcc = basMoneyNotAcc;
    }

    public int getBasOrgNotAss() {
        return basOrgNotAss;
    }

    public void setBasOrgNotAss(int basOrgNotAss) {
        this.basOrgNotAss = basOrgNotAss;
    }

    public int getBasOrgSurveying() {
        return basOrgSurveying;
    }

    public void setBasOrgSurveying(int basOrgSurveying) {
        this.basOrgSurveying = basOrgSurveying;
    }

    public int getBasOrgReviewing() {
        return basOrgReviewing;
    }

    public void setBasOrgReviewing(int basOrgReviewing) {
        this.basOrgReviewing = basOrgReviewing;
    }

    public int getBasLefanApv() {
        return basLefanApv;
    }

    public void setBasLefanApv(int basLefanApv) {
        this.basLefanApv = basLefanApv;
    }

    public int getBasEntrustApv() {
        return basEntrustApv;
    }

    public void setBasEntrustApv(int basEntrustApv) {
        this.basEntrustApv = basEntrustApv;
    }

    public int getBasNotClose() {
        return basNotClose;
    }

    public void setBasNotClose(int basNotClose) {
        this.basNotClose = basNotClose;
    }

    public SurveyInvestigatorOrgReport getSurveyInvestigatorOrgReport() {
        return surveyInvestigatorOrgReport;
    }

    public void setSurveyInvestigatorOrgReport(SurveyInvestigatorOrgReport surveyInvestigatorOrgReport) {
        this.surveyInvestigatorOrgReport = surveyInvestigatorOrgReport;
    }
}
