package com.lefancrm.apicenter.model;

import java.util.List;

public class SurveyPriceModelAreaCategories {
    private Long id;

    private String name;

    private Long priceModelId;

    private String priceModelName;

    private List<SurveyAreaCategoriesAreaCity> areaCitys;

    private List<SurveyPrice> prices;

    private Long oldAreaCategoriesId;

    public Long getOldAreaCategoriesId() {
        return oldAreaCategoriesId;
    }

    public void setOldAreaCategoriesId(Long oldAreaCategoriesId) {
        this.oldAreaCategoriesId = oldAreaCategoriesId;
    }

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

    public List<SurveyAreaCategoriesAreaCity> getAreaCitys() {
        return areaCitys;
    }

    public void setAreaCitys(List<SurveyAreaCategoriesAreaCity> areaCitys) {
        this.areaCitys = areaCitys;
    }

    public List<SurveyPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<SurveyPrice> prices) {
        this.prices = prices;
    }
}