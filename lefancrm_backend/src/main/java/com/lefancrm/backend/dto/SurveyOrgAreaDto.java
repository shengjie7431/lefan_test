package com.lefancrm.backend.dto;

import java.util.Date;

/**
 * 机构片区表
 * survey_org_area
 * @author LSG
 */
public class SurveyOrgAreaDto {
    /**
     * 主键标识ID
     */
    private Integer id;

    /**
     * 机构ID
     */
    private Integer surveyOrgId;

    /**
     * 机构名称
     */
    private String surveyOrgName;

    /**
     * 片区名称
     */
    private String surveyAreaName;

    /**
     * 片区状态（1：正常，2：停用）
     */
    private Integer state;

    /**
     * 删除状态（0:否 1：是）
     */
    private Integer deleteFlag;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Integer surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
    }

    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
    }

    public String getSurveyAreaName() {
        return surveyAreaName;
    }

    public void setSurveyAreaName(String surveyAreaName) {
        this.surveyAreaName = surveyAreaName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
