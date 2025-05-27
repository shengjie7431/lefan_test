package com.lefancrm.apicenter.model;

public class SurveyInvestigatorRewordMid {
    private Long id;

    private Long surveyInvestigatorId;

    private Long rewardListId;

    private Integer deleteFlag;

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyInvestigatorId() {
        return surveyInvestigatorId;
    }

    public void setSurveyInvestigatorId(Long surveyInvestigatorId) {
        this.surveyInvestigatorId = surveyInvestigatorId;
    }

    public Long getRewardListId() {
        return rewardListId;
    }

    public void setRewardListId(Long rewardListId) {
        this.rewardListId = rewardListId;
    }
}