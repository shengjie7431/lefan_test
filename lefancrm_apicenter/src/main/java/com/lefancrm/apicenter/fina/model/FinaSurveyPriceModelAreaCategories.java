package com.lefancrm.apicenter.fina.model;

import java.util.List;

public class FinaSurveyPriceModelAreaCategories {
    private Long id;

    private String name;

    private Long priceModelId;

    private String priceModelName;

    private List<FinaSurveyAreaCategoriesAreaCity> areaCitys;

    private List<FinaSurveyPrice> prices;

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

    public List<FinaSurveyAreaCategoriesAreaCity> getAreaCitys() {
        return areaCitys;
    }

    public void setAreaCitys(List<FinaSurveyAreaCategoriesAreaCity> areaCitys) {
        this.areaCitys = areaCitys;
    }

    public List<FinaSurveyPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<FinaSurveyPrice> prices) {
        this.prices = prices;
    }
}