package com.lefancrm.backend.dto;

import java.util.List;

public class SurveyChannelModelArea {
    private Long id;

    private String name;

    private Long modelId;

    private String modelName;

    List<SurveyChannelModelAreaCity> areaCities;

    List<SurveyChannelModelInfo> modelInfos;

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

    public List<SurveyChannelModelAreaCity> getAreaCities() {
        return areaCities;
    }

    public void setAreaCities(List<SurveyChannelModelAreaCity> areaCities) {
        this.areaCities = areaCities;
    }

    public List<SurveyChannelModelInfo> getModelInfos() {
        return modelInfos;
    }

    public void setModelInfos(List<SurveyChannelModelInfo> modelInfos) {
        this.modelInfos = modelInfos;
    }
}