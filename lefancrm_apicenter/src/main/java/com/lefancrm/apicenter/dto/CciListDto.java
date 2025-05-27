package com.lefancrm.apicenter.dto;

import java.util.Date;

/**
 * Created by jun on 2017/12/29.
 */
public class CciListDto {

    private Long id;
    private String userName;
    private Integer isIntention;
    private Integer caseProgress;
    private Integer caseSource;
    private Date followTime;
    private Date nextFollowTime;
    private String ccName;
    private Long ccId;
    private Long orgId;
    private String orgName;
    private Date updateTime;
    private String followDesc;
    private String familyAddress;
    private String userPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIsIntention() {
        return isIntention;
    }

    public void setIsIntention(Integer isIntention) {
        this.isIntention = isIntention;
    }

    public Integer getCaseProgress() {
        return caseProgress;
    }

    public void setCaseProgress(Integer caseProgress) {
        this.caseProgress = caseProgress;
    }

    public Integer getCaseSource() {
        return caseSource;
    }

    public void setCaseSource(Integer caseSource) {
        this.caseSource = caseSource;
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

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public Long getCcId() {
        return ccId;
    }

    public void setCcId(Long ccId) {
        this.ccId = ccId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFollowDesc() {
        return followDesc;
    }

    public void setFollowDesc(String followDesc) {
        this.followDesc = followDesc;
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
