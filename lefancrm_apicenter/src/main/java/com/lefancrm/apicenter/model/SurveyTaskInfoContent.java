package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

public class SurveyTaskInfoContent {
    private Long id;

    private String name;

    private Long taskInfoId;

    private String taskInfoName;

    private Long createById;

    private String createBy;

    private Date createTime;

    private Long updateById;

    private String updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private Integer sort;

    private String color;

    private Integer showState;//应用场景：调查处理，新增方向时，页面展示（任务类型、任务子类、方向结果的关联显示）

    private List<SurveyTaskDirectionResult> taskDirectionResults;

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

    public Long getTaskInfoId() {
        return taskInfoId;
    }

    public void setTaskInfoId(Long taskInfoId) {
        this.taskInfoId = taskInfoId;
    }

    public String getTaskInfoName() {
        return taskInfoName;
    }

    public void setTaskInfoName(String taskInfoName) {
        this.taskInfoName = taskInfoName;
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(Long updateById) {
        this.updateById = updateById;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getShowState() {
        return showState;
    }

    public void setShowState(Integer showState) {
        this.showState = showState;
    }

    public List<SurveyTaskDirectionResult> getTaskDirectionResults() {
        return taskDirectionResults;
    }

    public void setTaskDirectionResults(List<SurveyTaskDirectionResult> taskDirectionResults) {
        this.taskDirectionResults = taskDirectionResults;
    }
}