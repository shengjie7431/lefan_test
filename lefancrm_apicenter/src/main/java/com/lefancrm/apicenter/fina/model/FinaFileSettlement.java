package com.lefancrm.apicenter.fina.model;

import java.util.Date;

public class FinaFileSettlement {
    private Long id;

    private Long settlementInfoId;

    private Long fileEnumId;

    private String fileEnumName;

    private String fileName;

    private String filePath;

    private Date uploadTime;

    private String uploadBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSettlementInfoId() {
        return settlementInfoId;
    }

    public void setSettlementInfoId(Long settlementInfoId) {
        this.settlementInfoId = settlementInfoId;
    }

    public Long getFileEnumId() {
        return fileEnumId;
    }

    public void setFileEnumId(Long fileEnumId) {
        this.fileEnumId = fileEnumId;
    }

    public String getFileEnumName() {
        return fileEnumName;
    }

    public void setFileEnumName(String fileEnumName) {
        this.fileEnumName = fileEnumName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(String uploadBy) {
        this.uploadBy = uploadBy;
    }
}