package com.lefancrm.apicenter.model;

import java.util.List;

public class SurveyChannelCase {
    private Long id;

    private Long surveyInfoId;

    private Long surveyDirectionId;

    private Long surveyChannelId;

    private String surveyNo;

    private String surveyDirectionName;

    private List<SurveyChannelCase> surveyInfos;
    private List<SurveyChannelCase> directions;

    private List<SurveyChannelCase> selDirections;

    private List<SurveyChannelCase> allDirections;

    private String directionsStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Long getSurveyDirectionId() {
        return surveyDirectionId;
    }

    public void setSurveyDirectionId(Long surveyDirectionId) {
        this.surveyDirectionId = surveyDirectionId;
    }

    public Long getSurveyChannelId() {
        return surveyChannelId;
    }

    public void setSurveyChannelId(Long surveyChannelId) {
        this.surveyChannelId = surveyChannelId;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getSurveyDirectionName() {
        return surveyDirectionName;
    }

    public void setSurveyDirectionName(String surveyDirectionName) {
        this.surveyDirectionName = surveyDirectionName;
    }

    public List<SurveyChannelCase> getSelDirections() {
        return selDirections;
    }

    public void setSelDirections(List<SurveyChannelCase> selDirections) {
        this.selDirections = selDirections;
    }

    public List<SurveyChannelCase> getAllDirections() {
        return allDirections;
    }

    public void setAllDirections(List<SurveyChannelCase> allDirections) {
        this.allDirections = allDirections;
    }

    public String getDirectionsStr() {
        return directionsStr;
    }

    public void setDirectionsStr(String directionsStr) {
        this.directionsStr = directionsStr;
    }

    public List<SurveyChannelCase> getSurveyInfos() {
        return surveyInfos;
    }

    public void setSurveyInfos(List<SurveyChannelCase> surveyInfos) {
        this.surveyInfos = surveyInfos;
    }

    public List<SurveyChannelCase> getDirections() {
        return directions;
    }

    public void setDirections(List<SurveyChannelCase> directions) {
        this.directions = directions;
    }
}