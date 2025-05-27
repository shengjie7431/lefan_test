package com.lefancrm.apicenter.fina.model;

import java.util.List;

public class FinaSurveyCoefficientModelArea {
    private Long id;

    private String name;

    private Long modelId;

    private String modelName;

    private List<FinaSurveyCoefficientModelInfo> modelInfos;

    private List<FinaSurveyCoefficientAreaCity> areaCities;

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

    public List<FinaSurveyCoefficientModelInfo> getModelInfos() {
        return modelInfos;
    }

    public void setModelInfos(List<FinaSurveyCoefficientModelInfo> modelInfos) {
        this.modelInfos = modelInfos;
    }

    public List<FinaSurveyCoefficientAreaCity> getAreaCities() {
        return areaCities;
    }

    public void setAreaCities(List<FinaSurveyCoefficientAreaCity> areaCities) {
        this.areaCities = areaCities;
    }
}