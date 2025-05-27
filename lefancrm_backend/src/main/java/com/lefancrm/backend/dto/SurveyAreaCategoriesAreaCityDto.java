package com.lefancrm.backend.dto;

public class SurveyAreaCategoriesAreaCityDto {
    private Long id;

    private Long priceModelId;

    private String priceModelName;

    private Long areaCategoriesId;

    private String areaCategoriesName;

    private Long areaId;

    private String areaName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getAreaCategoriesId() {
        return areaCategoriesId;
    }

    public void setAreaCategoriesId(Long areaCategoriesId) {
        this.areaCategoriesId = areaCategoriesId;
    }

    public String getAreaCategoriesName() {
        return areaCategoriesName;
    }

    public void setAreaCategoriesName(String areaCategoriesName) {
        this.areaCategoriesName = areaCategoriesName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}