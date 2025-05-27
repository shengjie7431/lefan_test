package com.lefancrm.backend.dto;

public class SurveyChannelModelInfo {
    private Long id;

    private Long modelId;

    private String modelName;

    private Long areaCategoriesId;

    private String areaCategoriesName;

    private Long taskId;

    private String taskName;

    private Long taskContentId;

    private String taskContentName;

    private Long taskContentResultId;

    private String taskContentResultName;

    private Double price;

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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getTaskContentId() {
        return taskContentId;
    }

    public void setTaskContentId(Long taskContentId) {
        this.taskContentId = taskContentId;
    }

    public String getTaskContentName() {
        return taskContentName;
    }

    public void setTaskContentName(String taskContentName) {
        this.taskContentName = taskContentName;
    }

    public Long getTaskContentResultId() {
        return taskContentResultId;
    }

    public void setTaskContentResultId(Long taskContentResultId) {
        this.taskContentResultId = taskContentResultId;
    }

    public String getTaskContentResultName() {
        return taskContentResultName;
    }

    public void setTaskContentResultName(String taskContentResultName) {
        this.taskContentResultName = taskContentResultName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}