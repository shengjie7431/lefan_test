package com.lefancrm.backend.dto.fina;

import com.lefancrm.backend.dto.SurveyAreaCategoriesAreaCityDto;
import com.lefancrm.backend.dto.SurveyPriceDto;

import java.util.List;

public class FinaSurveyPriceModelAreaCategoriesDto {
    private Long id;

    private String name;

    private Long priceModelId;

    private String priceModelName;

    private List<FinaSurveyAreaCategoriesAreaCityDto> areaCitys; //区域类别对应的具体城市

    private List<FinaSurveyPriceDto> prices;//区域类别的价格

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

    public List<FinaSurveyAreaCategoriesAreaCityDto> getAreaCitys() {
        return areaCitys;
    }

    public void setAreaCitys(List<FinaSurveyAreaCategoriesAreaCityDto> areaCitys) {
        this.areaCitys = areaCitys;
    }

    public List<FinaSurveyPriceDto> getPrices() {
        return prices;
    }

    public void setPrices(List<FinaSurveyPriceDto> prices) {
        this.prices = prices;
    }
}