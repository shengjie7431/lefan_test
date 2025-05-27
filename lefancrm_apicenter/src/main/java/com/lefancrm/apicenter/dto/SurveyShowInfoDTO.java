package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyCaseDirection;

import java.util.List;

public class SurveyShowInfoDTO {
    private int successNum = 0;
    private Boolean isAllSuccess = true;
    private int type;//1单机构单人任务提交    2单机构多人任务的最后一位提交    3多机构 辅助机构最后一位提交    4多机构  主机构最后一位提交
    private List<SurveyCaseDirectionDto> directionDtos;
    private List<SurveyAssignOrgDto> tasks;// 机构 + 调查员任务 + 方向
    private SurveyRiskCaseInfoDto surveyRiskCaseInfo;
    private SurveyInvestigatorCaseDto surveyInvestigatorCase;
    private SurveyAssignOrgDto surveyAssignOrg;//当前审核机构

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SurveyCaseDirectionDto> getDirectionDtos() {
        return directionDtos;
    }

    public void setDirectionDtos(List<SurveyCaseDirectionDto> directionDtos) {
        this.directionDtos = directionDtos;
    }

    public List<SurveyAssignOrgDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<SurveyAssignOrgDto> tasks) {
        this.tasks = tasks;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public Boolean getAllSuccess() {
        return isAllSuccess;
    }

    public void setAllSuccess(Boolean allSuccess) {
        isAllSuccess = allSuccess;
    }

    public SurveyRiskCaseInfoDto getSurveyRiskCaseInfo() {
        return surveyRiskCaseInfo;
    }

    public void setSurveyRiskCaseInfo(SurveyRiskCaseInfoDto surveyRiskCaseInfo) {
        this.surveyRiskCaseInfo = surveyRiskCaseInfo;
    }

    public SurveyAssignOrgDto getSurveyAssignOrg() {
        return surveyAssignOrg;
    }

    public void setSurveyAssignOrg(SurveyAssignOrgDto surveyAssignOrg) {
        this.surveyAssignOrg = surveyAssignOrg;
    }

    public SurveyInvestigatorCaseDto getSurveyInvestigatorCase() {
        return surveyInvestigatorCase;
    }

    public void setSurveyInvestigatorCase(SurveyInvestigatorCaseDto surveyInvestigatorCase) {
        this.surveyInvestigatorCase = surveyInvestigatorCase;
    }
}
