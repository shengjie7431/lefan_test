package com.lefancrm.apicenter.model;

public class ClaimsApplyFile {
    private Long id;

    private Long claimsApplyId;

    private Long commonFileId;

    private Long enumId;

    private String filePath;
    private String fileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClaimsApplyId() {
        return claimsApplyId;
    }

    public void setClaimsApplyId(Long claimsApplyId) {
        this.claimsApplyId = claimsApplyId;
    }

    public Long getCommonFileId() {
        return commonFileId;
    }

    public void setCommonFileId(Long commonFileId) {
        this.commonFileId = commonFileId;
    }

    public Long getEnumId() {
        return enumId;
    }

    public void setEnumId(Long enumId) {
        this.enumId = enumId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}