package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyKnowledgeComment {
    private Long id;

    private Long knowledgeBaseId;

    private String knowledgeBaseTitle;

    private Long userId;

    private String userName;

    private String content;

    private Date date;

    private Integer isDelete;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKnowledgeBaseId() {
        return knowledgeBaseId;
    }

    public void setKnowledgeBaseId(Long knowledgeBaseId) {
        this.knowledgeBaseId = knowledgeBaseId;
    }

    public String getKnowledgeBaseTitle() {
        return knowledgeBaseTitle;
    }

    public void setKnowledgeBaseTitle(String knowledgeBaseTitle) {
        this.knowledgeBaseTitle = knowledgeBaseTitle;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}