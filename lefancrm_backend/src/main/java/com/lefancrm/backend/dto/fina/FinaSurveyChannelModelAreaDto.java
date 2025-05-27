package com.lefancrm.backend.dto.fina;

import com.lefancrm.backend.dto.SurveyChannelModelAreaCity;
import com.lefancrm.backend.dto.SurveyChannelModelInfo;

import java.util.List;

public class FinaSurveyChannelModelAreaDto {
    private Long id;

    private String name;

    private Long modelId;

    private String modelName;

    List<FinaSurveyChannelModelAreaCityDto> areaCities;

    List<FinaSurveyChannelModelInfoDto> modelInfos;

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

    public List<FinaSurveyChannelModelAreaCityDto> getAreaCities() {
        return areaCities;
    }

    public void setAreaCities(List<FinaSurveyChannelModelAreaCityDto> areaCities) {
        this.areaCities = areaCities;
    }

    public List<FinaSurveyChannelModelInfoDto> getModelInfos() {
        return modelInfos;
    }

    public void setModelInfos(List<FinaSurveyChannelModelInfoDto> modelInfos) {
        this.modelInfos = modelInfos;
    }
}