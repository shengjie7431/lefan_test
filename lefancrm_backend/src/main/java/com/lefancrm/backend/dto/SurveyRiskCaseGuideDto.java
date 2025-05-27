package com.lefancrm.backend.dto;

import java.util.Date;

public class SurveyRiskCaseGuideDto {
    private Long id;

    private Long surveyId;

    private Long surveyInfoId;

    private String disease;

    private String maySun;

    private String direction;

    private String note;

    private Long guideBy;

    private String guideByName;

    private Date guideTime;

    private Long createBy;

    private String createByName;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private Integer isSun;

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

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getMaySun() {
        return maySun;
    }

    public void setMaySun(String maySun) {
        this.maySun = maySun;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getGuideBy() {
        return guideBy;
    }

    public void setGuideBy(Long guideBy) {
        this.guideBy = guideBy;
    }

    public String getGuideByName() {
        return guideByName;
    }

    public void setGuideByName(String guideByName) {
        this.guideByName = guideByName;
    }

    public Date getGuideTime() {
        return guideTime;
    }

    public void setGuideTime(Date guideTime) {
        this.guideTime = guideTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getIsSun() {
        return isSun;
    }

    public void setIsSun(Integer isSun) {
        this.isSun = isSun;
    }
}