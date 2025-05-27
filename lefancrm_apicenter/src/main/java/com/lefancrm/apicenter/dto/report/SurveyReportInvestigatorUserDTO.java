package com.lefancrm.apicenter.dto.report;

import com.lefancrm.apicenter.model.SurveyInvestigator;
import com.lefancrm.apicenter.model.SurveyInvestigatorUserReport;

public class SurveyReportInvestigatorUserDTO {

    //基础信息
    private SurveyInvestigator surveyInvestigator;
    private int basNotReceive;       //待接收
    private int basSurveying;       //调查中
    private int basOrgReviewing;    //初审中
    private int basLefanApv;        //平台待终审
    private int basSurveyed;        //已调查

    //关键指标 (调查方报表)
    private SurveyInvestigatorUserReport surveyInvestigatorUserReport;

    public SurveyInvestigator getSurveyInvestigator() {
        return surveyInvestigator;
    }

    public void setSurveyInvestigator(SurveyInvestigator surveyInvestigator) {
        this.surveyInvestigator = surveyInvestigator;
    }

    public int getBasNotReceive() {
        return basNotReceive;
    }

    public void setBasNotReceive(int basNotReceive) {
        this.basNotReceive = basNotReceive;
    }

    public int getBasSurveying() {
        return basSurveying;
    }

    public void setBasSurveying(int basSurveying) {
        this.basSurveying = basSurveying;
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

    public int getBasSurveyed() {
        return basSurveyed;
    }

    public void setBasSurveyed(int basSurveyed) {
        this.basSurveyed = basSurveyed;
    }

    public SurveyInvestigatorUserReport getSurveyInvestigatorUserReport() {
        return surveyInvestigatorUserReport;
    }

    public void setSurveyInvestigatorUserReport(SurveyInvestigatorUserReport surveyInvestigatorUserReport) {
        this.surveyInvestigatorUserReport = surveyInvestigatorUserReport;
    }
}
