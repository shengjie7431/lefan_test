package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyFollow;
import com.lefancrm.apicenter.model.SurveyFollowFile;

import java.util.Date;
import java.util.List;

public class SurveyFollowDto extends SurveyFollow{
    private List<SurveyFollowFileDto> surveyFollowFiles;

    public List<SurveyFollowFileDto> getSurveyFollowFiles() {
        return surveyFollowFiles;
    }

    public void setSurveyFollowFiles(List<SurveyFollowFileDto> surveyFollowFiles) {
        this.surveyFollowFiles = surveyFollowFiles;
    }
}