package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyServiceAdvantage {
    private Long id;

    private String serviceAdTitle;

    private String serviceAdAbstract;

    private String serviceAdContent;

    private String serviceAdImg;

    private Long createBy;

    private Long updateBy;

    private Date createTime;

    private Date updateTime;

    private Integer deleteFlag;

    private String createByName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceAdTitle() {
        return serviceAdTitle;
    }

    public void setServiceAdTitle(String serviceAdTitle) {
        this.serviceAdTitle = serviceAdTitle;
    }

    public String getServiceAdAbstract() {
        return serviceAdAbstract;
    }

    public void setServiceAdAbstract(String serviceAdAbstract) {
        this.serviceAdAbstract = serviceAdAbstract;
    }

    public String getServiceAdContent() {
        return serviceAdContent;
    }

    public void setServiceAdContent(String serviceAdContent) {
        this.serviceAdContent = serviceAdContent;
    }

    public String getServiceAdImg() {
        return serviceAdImg;
    }

    public void setServiceAdImg(String serviceAdImg) {
        this.serviceAdImg = serviceAdImg;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }
}