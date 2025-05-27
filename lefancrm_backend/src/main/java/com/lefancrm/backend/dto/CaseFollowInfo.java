package com.lefancrm.backend.dto;

import java.util.Date;
import java.util.List;

public class CaseFollowInfo {
    private Long id;

    private Integer type;

    private Long caseId;

    private Integer caseState;

    private String caseStateStr;

    private String followDesc;

    private String followBy;

    private Long followById;

    private Date followTime;

    private List<CaseFollowInfoFile> list;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Integer getCaseState() {
        return caseState;
    }

    public void setCaseState(Integer caseState) {
        this.caseState = caseState;
    }

    public String getCaseStateStr() {
        return caseStateStr;
    }

    public void setCaseStateStr(String caseStateStr) {
        this.caseStateStr = caseStateStr;
    }

    public String getFollowDesc() {
        return followDesc;
    }

    public void setFollowDesc(String followDesc) {
        this.followDesc = followDesc;
    }

    public String getFollowBy() {
        return followBy;
    }

    public void setFollowBy(String followBy) {
        this.followBy = followBy;
    }

    public Long getFollowById() {
        return followById;
    }

    public void setFollowById(Long followById) {
        this.followById = followById;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

    public List<CaseFollowInfoFile> getList() {
        return list;
    }

    public void setList(List<CaseFollowInfoFile> list) {
        this.list = list;
    }
}