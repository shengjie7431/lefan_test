package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyAssignOrgReply {
    private Long id;

    private Long surveyAssorgCaseId;

    private String replyContent;

    private String replyFiles;

    private Date replyTime;

    private Integer replyType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyAssorgCaseId() {
        return surveyAssorgCaseId;
    }

    public void setSurveyAssorgCaseId(Long surveyAssorgCaseId) {
        this.surveyAssorgCaseId = surveyAssorgCaseId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyFiles() {
        return replyFiles;
    }

    public void setReplyFiles(String replyFiles) {
        this.replyFiles = replyFiles;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getReplyType() {
        return replyType;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }
}