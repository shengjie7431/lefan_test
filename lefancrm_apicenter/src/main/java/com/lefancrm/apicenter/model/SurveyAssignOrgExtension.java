package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

public class SurveyAssignOrgExtension {
    private Long id;

    private Long surveyId;

    private Long surveyInfoId;

    private Long surveyAssignOrgId;

    private Integer extensionState;

    private Date extensionTime;

    private String extensionReason;

    /**
     * 延期结论
     */
    private String extensionReasonResult;

    private String extensionBackReason;

    private String extensionFiles;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private List<CommonFile> commonFiles; //申请延期时：证据附件

    private Date oldOrgEndTime;//机构原截止时间（机构案件）
    private Date endTime;//案件截止时间（主案件）

    private Boolean sendEmail = false;
    private SurveyEmailInfo surveyEmailInfo;//发送邮件的信息

    private Boolean newestInfo = false;//是否是最新的一条（用于判断是有有撤回按钮）

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

    public Long getSurveyAssignOrgId() {
        return surveyAssignOrgId;
    }

    public void setSurveyAssignOrgId(Long surveyAssignOrgId) {
        this.surveyAssignOrgId = surveyAssignOrgId;
    }

    public Integer getExtensionState() {
        return extensionState;
    }

    public void setExtensionState(Integer extensionState) {
        this.extensionState = extensionState;
    }

    public Date getExtensionTime() {
        return extensionTime;
    }

    public void setExtensionTime(Date extensionTime) {
        this.extensionTime = extensionTime;
    }

    public String getExtensionReason() {
        return extensionReason;
    }

    public void setExtensionReason(String extensionReason) {
        this.extensionReason = extensionReason;
    }

    public String getExtensionBackReason() {
        return extensionBackReason;
    }

    public void setExtensionBackReason(String extensionBackReason) {
        this.extensionBackReason = extensionBackReason;
    }

    public String getExtensionFiles() {
        return extensionFiles;
    }

    public void setExtensionFiles(String extensionFiles) {
        this.extensionFiles = extensionFiles;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
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

    public List<CommonFile> getCommonFiles() {
        return commonFiles;
    }

    public void setCommonFiles(List<CommonFile> commonFiles) {
        this.commonFiles = commonFiles;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getOldOrgEndTime() {
        return oldOrgEndTime;
    }

    public void setOldOrgEndTime(Date oldOrgEndTime) {
        this.oldOrgEndTime = oldOrgEndTime;
    }

    public Boolean getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(Boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public SurveyEmailInfo getSurveyEmailInfo() {
        return surveyEmailInfo;
    }

    public void setSurveyEmailInfo(SurveyEmailInfo surveyEmailInfo) {
        this.surveyEmailInfo = surveyEmailInfo;
    }

    public Boolean getNewestInfo() {
        return newestInfo;
    }

    public void setNewestInfo(Boolean newestInfo) {
        this.newestInfo = newestInfo;
    }

    public String getExtensionReasonResult() {
        return extensionReasonResult;
    }

    public void setExtensionReasonResult(String extensionReasonResult) {
        this.extensionReasonResult = extensionReasonResult;
    }
}