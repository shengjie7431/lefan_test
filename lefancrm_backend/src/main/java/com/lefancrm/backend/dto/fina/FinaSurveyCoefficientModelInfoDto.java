package com.lefancrm.backend.dto.fina;

public class FinaSurveyCoefficientModelInfoDto {
    private Long id;

    private Long modelId;

    private String modelName;

    private Integer cityType;

    private Double coeff;

    private Long areaCategoriesId;

    private String areaCategoriesName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getCityType() {
        return cityType;
    }

    public void setCityType(Integer cityType) {
        this.cityType = cityType;
    }

    public Double getCoeff() {
        return coeff;
    }

    public void setCoeff(Double coeff) {
        this.coeff = coeff;
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
}