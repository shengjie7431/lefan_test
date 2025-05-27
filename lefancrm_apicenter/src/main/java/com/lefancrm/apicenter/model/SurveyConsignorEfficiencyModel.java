package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

public class SurveyConsignorEfficiencyModel {
    private Long id;

    private String name;

    private Integer efficiencyAttr;

    private Long createBy;

    private String createByName;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private Integer type;

    private List<SurveyConsignorEfficiencyModelInfo> efficiencyModelInfos; //时效体系

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

    public Integer getEfficiencyAttr() {
        return efficiencyAttr;
    }

    public void setEfficiencyAttr(Integer efficiencyAttr) {
        this.efficiencyAttr = efficiencyAttr;
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

    public List<SurveyConsignorEfficiencyModelInfo> getEfficiencyModelInfos() {
        return efficiencyModelInfos;
    }

    public void setEfficiencyModelInfos(List<SurveyConsignorEfficiencyModelInfo> efficiencyModelInfos) {
        this.efficiencyModelInfos = efficiencyModelInfos;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}