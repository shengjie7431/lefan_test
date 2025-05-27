package com.lefancrm.backend.dto.fina;

import java.util.List;

public class FinaSurveyCoefficientModelAreaDto {
    private Long id;

    private String name;

    private Long modelId;

    private String modelName;

    private List<FinaSurveyCoefficientModelInfoDto> modelInfos;

    private List<FinaSurveyCoefficientAreaCityDto> areaCities;

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

    public List<FinaSurveyCoefficientModelInfoDto> getModelInfos() {
        return modelInfos;
    }

    public void setModelInfos(List<FinaSurveyCoefficientModelInfoDto> modelInfos) {
        this.modelInfos = modelInfos;
    }

    public List<FinaSurveyCoefficientAreaCityDto> getAreaCities() {
        return areaCities;
    }

    public void setAreaCities(List<FinaSurveyCoefficientAreaCityDto> areaCities) {
        this.areaCities = areaCities;
    }
}