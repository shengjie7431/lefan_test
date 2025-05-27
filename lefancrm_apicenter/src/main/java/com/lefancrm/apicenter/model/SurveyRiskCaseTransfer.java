package com.lefancrm.apicenter.model;

public class SurveyRiskCaseTransfer {
    private Long id;

    private Long surveyId;

    private Long surveyParentId;

    private Integer transferType;

    private String transferTypeName;

    private String ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getSurveyParentId() {
        return surveyParentId;
    }

    public void setSurveyParentId(Long surveyParentId) {
        this.surveyParentId = surveyParentId;
    }

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
    }

    public String getTransferTypeName() {
        return transferTypeName;
    }

    public void setTransferTypeName(String transferTypeName) {
        this.transferTypeName = transferTypeName;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}