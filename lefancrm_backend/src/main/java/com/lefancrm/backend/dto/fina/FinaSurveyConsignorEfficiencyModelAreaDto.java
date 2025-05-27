package com.lefancrm.backend.dto.fina;

import com.lefancrm.backend.dto.SurveyConsignorEfficiencyModelInfoDto;

import java.util.List;

public class FinaSurveyConsignorEfficiencyModelAreaDto {
    private Long id;

    private String name;

    private Long modelId;

    private String modelName;

    List<FinaSurveyConsignorEfficiencyAreaCityDto> areaCities;

    List<SurveyConsignorEfficiencyModelInfoDto> modelInfos;

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

    public List<FinaSurveyConsignorEfficiencyAreaCityDto> getAreaCities() {
        return areaCities;
    }

    public void setAreaCities(List<FinaSurveyConsignorEfficiencyAreaCityDto> areaCities) {
        this.areaCities = areaCities;
    }

    public List<SurveyConsignorEfficiencyModelInfoDto> getModelInfos() {
        return modelInfos;
    }

    public void setModelInfos(List<SurveyConsignorEfficiencyModelInfoDto> modelInfos) {
        this.modelInfos = modelInfos;
    }
}