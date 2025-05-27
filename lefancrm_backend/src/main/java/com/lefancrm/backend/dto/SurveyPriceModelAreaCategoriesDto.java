package com.lefancrm.backend.dto;

import java.util.List;

public class SurveyPriceModelAreaCategoriesDto {
    private Long id;

    private String name;

    private Long priceModelId;

    private String priceModelName;

    private List<SurveyAreaCategoriesAreaCityDto> areaCitys; //区域类别对应的具体城市

    private List<SurveyPriceDto> prices;//区域类别的价格

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

    public Long getPriceModelId() {
        return priceModelId;
    }

    public void setPriceModelId(Long priceModelId) {
        this.priceModelId = priceModelId;
    }

    public String getPriceModelName() {
        return priceModelName;
    }

    public void setPriceModelName(String priceModelName) {
        this.priceModelName = priceModelName;
    }

    public List<SurveyAreaCategoriesAreaCityDto> getAreaCitys() {
        return areaCitys;
    }

    public void setAreaCitys(List<SurveyAreaCategoriesAreaCityDto> areaCitys) {
        this.areaCitys = areaCitys;
    }

    public List<SurveyPriceDto> getPrices() {
        return prices;
    }

    public void setPrices(List<SurveyPriceDto> prices) {
        this.prices = prices;
    }
}