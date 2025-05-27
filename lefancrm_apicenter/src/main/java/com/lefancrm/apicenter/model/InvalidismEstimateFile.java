package com.lefancrm.apicenter.model;

public class InvalidismEstimateFile {
    private Long id;

    private Long invalidismEstimateId;

    private String fileName;

    private String filePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvalidismEstimateId() {
        return invalidismEstimateId;
    }

    public void setInvalidismEstimateId(Long invalidismEstimateId) {
        this.invalidismEstimateId = invalidismEstimateId;
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
}