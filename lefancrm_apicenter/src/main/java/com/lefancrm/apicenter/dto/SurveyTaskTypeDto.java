package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyAssignOrgType;
import com.lefancrm.apicenter.model.SurveyBusinessTaskType;
import com.lefancrm.apicenter.model.SurveyInvestigatorCaseType;
import com.lefancrm.apicenter.model.SurveyTaskType;

public class SurveyTaskTypeDto  {

    private Integer selectType;//分派调查员时，判断该任务类型是否已被分派过 1、未分配；2、已分配，没有做方向；3已分配，并做了方向

    private SurveyTaskType surveyTaskType;

    private SurveyBusinessTaskType surveyBusinessTaskType;

    private SurveyAssignOrgType surveyAssignOrgType;

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public SurveyTaskType getSurveyTaskType() {
        return surveyTaskType;
    }

    public void setSurveyTaskType(SurveyTaskType surveyTaskType) {
        this.surveyTaskType = surveyTaskType;
    }

    public SurveyBusinessTaskType getSurveyBusinessTaskType() {
        return surveyBusinessTaskType;
    }

    public void setSurveyBusinessTaskType(SurveyBusinessTaskType surveyBusinessTaskType) {
        this.surveyBusinessTaskType = surveyBusinessTaskType;
    }

    public SurveyAssignOrgType getSurveyAssignOrgType() {
        return surveyAssignOrgType;
    }

    public void setSurveyAssignOrgType(SurveyAssignOrgType surveyAssignOrgType) {
        this.surveyAssignOrgType = surveyAssignOrgType;
    }
}