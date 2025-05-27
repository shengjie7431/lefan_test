package com.lefancrm.backend.dto;

public class SurveyFileDTO {
    private String filePath;
    private String httpFilePath;
    private String fileName;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getHttpFilePath() {
        return httpFilePath;
    }

    public void setHttpFilePath(String httpFilePath) {
        this.httpFilePath = httpFilePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
