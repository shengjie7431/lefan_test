package com.lefancrm.backend.dto;

import java.util.List;

public class SurveyOprInfoDTO {
    private List<SurveyCaseDirectionDto> directions;
    private List<SurveyFileInfoDTO> files;
    private TemplateData templateData;
    private SurveyRiskCaseInfoDto surveyRiskInfo;
    private SurveyAssignOrgDto surveyAssignOrg;

    public List<SurveyCaseDirectionDto> getDirections() {
        return directions;
    }

    public void setDirections(List<SurveyCaseDirectionDto> directions) {
        this.directions = directions;
    }

    public List<SurveyFileInfoDTO> getFiles() {
        return files;
    }

    public void setFiles(List<SurveyFileInfoDTO> files) {
        this.files = files;
    }

    public TemplateData getTemplateData() {
        return templateData;
    }

    public void setTemplateData(TemplateData templateData) {
        this.templateData = templateData;
    }

    public SurveyRiskCaseInfoDto getSurveyRiskInfo() {
        return surveyRiskInfo;
    }

    public void setSurveyRiskInfo(SurveyRiskCaseInfoDto surveyRiskInfo) {
        this.surveyRiskInfo = surveyRiskInfo;
    }

    public SurveyAssignOrgDto getSurveyAssignOrg() {
        return surveyAssignOrg;
    }

    public void setSurveyAssignOrg(SurveyAssignOrgDto surveyAssignOrg) {
        this.surveyAssignOrg = surveyAssignOrg;
    }
}
