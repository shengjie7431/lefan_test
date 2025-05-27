package com.lefancrm.apicenter.fina.model;

import java.util.List;

public class FinaSurveyConsignorEfficiencyModelArea {
    private Long id;

    private String name;

    private Long modelId;

    private String modelName;

    List<FinaSurveyConsignorEfficiencyAreaCity> areaCities;

    List<FinaSurveyConsignorEfficiencyModelInfo> modelInfos;

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

    public List<FinaSurveyConsignorEfficiencyAreaCity> getAreaCities() {
        return areaCities;
    }

    public void setAreaCities(List<FinaSurveyConsignorEfficiencyAreaCity> areaCities) {
        this.areaCities = areaCities;
    }

    public List<FinaSurveyConsignorEfficiencyModelInfo> getModelInfos() {
        return modelInfos;
    }

    public void setModelInfos(List<FinaSurveyConsignorEfficiencyModelInfo> modelInfos) {
        this.modelInfos = modelInfos;
    }
}