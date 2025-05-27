package com.lefancrm.backend.dto.help;

import com.lefancrm.backend.dto.SurveyModelInfoDto;
import com.lefancrm.backend.dto.SurveyRiskCaseInfoDto;

public class TemplateHelpData {
    private String reportName;
    private SurveyRiskCaseInfoDto surveyRiskCaseInfo;
    private SurveyModelInfoDto surveyModelInfo;
    private HelpData1 helpData1;
    private HelpData2 helpData2;
    private HelpDataLists helpDataLists;

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public HelpData1 getHelpData1() {
        return helpData1;
    }

    public void setHelpData1(HelpData1 helpData1) {
        this.helpData1 = helpData1;
    }

    public HelpData2 getHelpData2() {
        return helpData2;
    }

    public void setHelpData2(HelpData2 helpData2) {
        this.helpData2 = helpData2;
    }

    public HelpDataLists getHelpDataLists() {
        return helpDataLists;
    }

    public void setHelpDataLists(HelpDataLists helpDataLists) {
        this.helpDataLists = helpDataLists;
    }

    public SurveyModelInfoDto getSurveyModelInfo() {
        return surveyModelInfo;
    }

    public void setSurveyModelInfo(SurveyModelInfoDto surveyModelInfo) {
        this.surveyModelInfo = surveyModelInfo;
    }

    public SurveyRiskCaseInfoDto getSurveyRiskCaseInfo() {
        return surveyRiskCaseInfo;
    }

    public void setSurveyRiskCaseInfo(SurveyRiskCaseInfoDto surveyRiskCaseInfo) {
        this.surveyRiskCaseInfo = surveyRiskCaseInfo;
    }
}
