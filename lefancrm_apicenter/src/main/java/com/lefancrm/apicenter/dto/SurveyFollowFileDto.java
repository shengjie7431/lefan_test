package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyFollowFile;

import java.util.Date;

public class SurveyFollowFileDto extends SurveyFollowFile{
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}