package com.lefancrm.apicenter.model;

import java.util.List;

public class SurveyScoreModelArea {
    private Long id;

    private String name;

    private Long modelId;

    private String modelName;

    List<SurveyScoreModelAreaCity> areaCities;

    List<SurveyScoreModelInfo> modelInfos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public List<SurveyScoreModelAreaCity> getAreaCities() {
        return areaCities;
    }

    public void setAreaCities(List<SurveyScoreModelAreaCity> areaCities) {
        this.areaCities = areaCities;
    }

    public List<SurveyScoreModelInfo> getModelInfos() {
        return modelInfos;
    }

    public void setModelInfos(List<SurveyScoreModelInfo> modelInfos) {
        this.modelInfos = modelInfos;
    }
}