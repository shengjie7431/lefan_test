package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyModelInfo;
import com.lefancrm.apicenter.model.SurveyRiskCaseInfo;

import java.util.List;

/**
 * Created by lixianfeng on 2019/7/15.
 */
public class TemplateData {
    private String reportName;
    private SurveyModelInfo model;
    private SurveyRiskCaseInfoDto surveyRiskCaseInfo;
    private TemplateDataLF templateDataLF;
    private TemplateDataZY templateDataZY;
    private TemplateDataZD templateDataZD;
    private TemplateDataZH templateDataZH;

    private String surveyUserImg;//调查员签名
    private String lfUserImg;//平台终审人员签名
    private List<SurveyCaseDirectionDto> directions;
    private List<SurveyRiskCaseInfoDto> riskCaseInfos;

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public SurveyModelInfo getModel() {
        return model;
    }

    public void setModel(SurveyModelInfo model) {
        this.model = model;
    }

    public SurveyRiskCaseInfoDto getSurveyRiskCaseInfo() {
        return surveyRiskCaseInfo;
    }

    public void setSurveyRiskCaseInfo(SurveyRiskCaseInfoDto surveyRiskCaseInfo) {
        this.surveyRiskCaseInfo = surveyRiskCaseInfo;
    }

    public TemplateDataLF getTemplateDataLF() {
        return templateDataLF;
    }

    public void setTemplateDataLF(TemplateDataLF templateDataLF) {
        this.templateDataLF = templateDataLF;
    }

    public TemplateDataZY getTemplateDataZY() {
        return templateDataZY;
    }

    public void setTemplateDataZY(TemplateDataZY templateDataZY) {
        this.templateDataZY = templateDataZY;
    }

    public TemplateDataZD getTemplateDataZD() {
        return templateDataZD;
    }

    public void setTemplateDataZD(TemplateDataZD templateDataZD) {
        this.templateDataZD = templateDataZD;
    }

    public TemplateDataZH getTemplateDataZH() {
        return templateDataZH;
    }

    public void setTemplateDataZH(TemplateDataZH templateDataZH) {
        this.templateDataZH = templateDataZH;
    }

    public List<SurveyCaseDirectionDto> getDirections() {
        return directions;
    }

    public void setDirections(List<SurveyCaseDirectionDto> directions) {
        this.directions = directions;
    }

    public String getSurveyUserImg() {
        return surveyUserImg;
    }

    public void setSurveyUserImg(String surveyUserImg) {
        this.surveyUserImg = surveyUserImg;
    }

    public String getLfUserImg() {
        return lfUserImg;
    }

    public void setLfUserImg(String lfUserImg) {
        this.lfUserImg = lfUserImg;
    }

    public List<SurveyRiskCaseInfoDto> getRiskCaseInfos() {
        return riskCaseInfos;
    }

    public void setRiskCaseInfos(List<SurveyRiskCaseInfoDto> riskCaseInfos) {
        this.riskCaseInfos = riskCaseInfos;
    }
}
