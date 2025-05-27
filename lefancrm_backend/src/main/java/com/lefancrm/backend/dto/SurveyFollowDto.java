package com.lefancrm.backend.dto;

import java.util.Date;
import java.util.List;

public class SurveyFollowDto{

    private Long id;

    private Long surveyId;

    private Long surveyInfoId;

    private Long followUserId;

    private String followUserName;

    private Date followTime;

    private Date nextFollowTime;

    private String contents;

    private List<SurveyFollowFileDto> surveyFollowFiles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Long getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(Long followUserId) {
        this.followUserId = followUserId;
    }

    public String getFollowUserName() {
        return followUserName;
    }

    public void setFollowUserName(String followUserName) {
        this.followUserName = followUserName;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public Date getNextFollowTime() {
        return nextFollowTime;
    }

    public void setNextFollowTime(Date nextFollowTime) {
        this.nextFollowTime = nextFollowTime;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public List<SurveyFollowFileDto> getSurveyFollowFiles() {
        return surveyFollowFiles;
    }

    public void setSurveyFollowFiles(List<SurveyFollowFileDto> surveyFollowFiles) {
        this.surveyFollowFiles = surveyFollowFiles;
    }
}