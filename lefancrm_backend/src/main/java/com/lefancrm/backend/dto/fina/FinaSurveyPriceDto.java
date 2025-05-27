package com.lefancrm.backend.dto.fina;

import java.util.Date;

public class FinaSurveyPriceDto {
    private Long id;

    private Long areaCategoriesId;

    private String areaCategoriesName;

    private Double taskPrice;

    private Long taskId;

    private String taskName;

    private Long taskInfoContentId;

    private String taskInfoContentName;

    private Long directionResultTypeId;

    private String directionResultTypeName;

    private Long createBy;

    private String createByName;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(Double taskPrice) {
        this.taskPrice = taskPrice;
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

    public Long getTaskInfoContentId() {
        return taskInfoContentId;
    }

    public void setTaskInfoContentId(Long taskInfoContentId) {
        this.taskInfoContentId = taskInfoContentId;
    }

    public String getTaskInfoContentName() {
        return taskInfoContentName;
    }

    public void setTaskInfoContentName(String taskInfoContentName) {
        this.taskInfoContentName = taskInfoContentName;
    }

    public Long getDirectionResultTypeId() {
        return directionResultTypeId;
    }

    public void setDirectionResultTypeId(Long directionResultTypeId) {
        this.directionResultTypeId = directionResultTypeId;
    }

    public String getDirectionResultTypeName() {
        return directionResultTypeName;
    }

    public void setDirectionResultTypeName(String directionResultTypeName) {
        this.directionResultTypeName = directionResultTypeName;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}