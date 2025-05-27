package com.lefancrm.backend.dto;

public class CaseFollowInfoFile {
    private Long id;

    private Long followInfoId;

    private String filePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowInfoId() {
        return followInfoId;
    }

    public void setFollowInfoId(Long followInfoId) {
        this.followInfoId = followInfoId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}