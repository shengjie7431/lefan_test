package com.lefancrm.backend.dto;

public class SurveyConsignorModelDto {
    private Long id;

    private Long modelId;

    private String modelName;

    private Long consignorId;

    private String consignorName;

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

    public Long getConsignorId() {
        return consignorId;
    }

    public void setConsignorId(Long consignorId) {
        this.consignorId = consignorId;
    }

    public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName;
    }
}