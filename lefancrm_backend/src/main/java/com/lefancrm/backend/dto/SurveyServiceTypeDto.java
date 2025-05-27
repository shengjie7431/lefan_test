package com.lefancrm.backend.dto;

import java.util.Date;
import java.util.List;

public class SurveyServiceTypeDto {
    private Long id;

    private String name;

    private String remark;

    private Long createBy;

    private String createByName;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private Integer sort;

    private List<SurveyServiceSubTypeDto> surveyServiceSubTypes;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<SurveyServiceSubTypeDto> getSurveyServiceSubTypes() {
        return surveyServiceSubTypes;
    }

    public void setSurveyServiceSubTypes(List<SurveyServiceSubTypeDto> surveyServiceSubTypes) {
        this.surveyServiceSubTypes = surveyServiceSubTypes;
    }
}