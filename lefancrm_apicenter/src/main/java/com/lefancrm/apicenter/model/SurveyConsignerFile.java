package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyConsignerFile {
    private Long id;

    private Long consignId;

    private Integer consignType;

    private Long fileId;

    private String fileName;

    private Long createBy;

    private String createByName;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsignId() {
        return consignId;
    }

    public void setConsignId(Long consignId) {
        this.consignId = consignId;
    }

    public Integer getConsignType() {
        return consignType;
    }

    public void setConsignType(Integer consignType) {
        this.consignType = consignType;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
}