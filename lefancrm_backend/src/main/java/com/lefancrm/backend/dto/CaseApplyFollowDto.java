package com.lefancrm.backend.dto;

import java.util.Date;

public class CaseApplyFollowDto {
    private Long id;

    private Long applyId;

    private String followDesc;

    private Long followById;

    private String followBy;

    private Date followTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getFollowDesc() {
        return followDesc;
    }

    public void setFollowDesc(String followDesc) {
        this.followDesc = followDesc;
    }

    public Long getFollowById() {
        return followById;
    }

    public void setFollowById(Long followById) {
        this.followById = followById;
    }

    public String getFollowBy() {
        return followBy;
    }

    public void setFollowBy(String followBy) {
        this.followBy = followBy;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }
}