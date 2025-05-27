package com.lefancrm.backend.dto.survey;

import com.lefancrm.backend.dto.SurveyRiskCaseDto;
import com.lefancrm.backend.dto.SurveyRiskCaseInfoDto;

import java.util.Date;

public class SurveyEmailInfoDTO {
    private Long id;

    private String emailUserName;

    private String emailAddress;

    private String emailAuthPassword;

    private String emailPassword;

    private String createBy;

    private Date createTime;

    private Date updateBy;

    private Date uodateTime;

    private Integer deleteFlag;

    private String toEmailAddress;

    private Boolean send;

    private String makeEmail;

    private Double maxSize;

    private String emailContent;//邮件正文

    private SurveyRiskCaseDto surveyRiskCase;

    private SurveyRiskCaseInfoDto surveyRiskCaseInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailUserName() {
        return emailUserName;
    }

    public void setEmailUserName(String emailUserName) {
        this.emailUserName = emailUserName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAuthPassword() {
        return emailAuthPassword;
    }

    public void setEmailAuthPassword(String emailAuthPassword) {
        this.emailAuthPassword = emailAuthPassword;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
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

    public Date getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Date updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUodateTime() {
        return uodateTime;
    }

    public void setUodateTime(Date uodateTime) {
        this.uodateTime = uodateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getToEmailAddress() {
        return toEmailAddress;
    }

    public void setToEmailAddress(String toEmailAddress) {
        this.toEmailAddress = toEmailAddress;
    }

    public Boolean getSend() {
        return send;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }

    public String getMakeEmail() {
        return makeEmail;
    }

    public void setMakeEmail(String makeEmail) {
        this.makeEmail = makeEmail;
    }

    public Double getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Double maxSize) {
        this.maxSize = maxSize;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public SurveyRiskCaseDto getSurveyRiskCase() {
        return surveyRiskCase;
    }

    public void setSurveyRiskCase(SurveyRiskCaseDto surveyRiskCase) {
        this.surveyRiskCase = surveyRiskCase;
    }

    public SurveyRiskCaseInfoDto getSurveyRiskCaseInfo() {
        return surveyRiskCaseInfo;
    }

    public void setSurveyRiskCaseInfo(SurveyRiskCaseInfoDto surveyRiskCaseInfo) {
        this.surveyRiskCaseInfo = surveyRiskCaseInfo;
    }

    @Override
    public String toString() {
        return "SurveyEmailInfoDTO{" +
                "id=" + id +
                ", emailUserName='" + emailUserName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", emailAuthPassword='" + emailAuthPassword + '\'' +
                ", emailPassword='" + emailPassword + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", uodateTime=" + uodateTime +
                ", deleteFlag=" + deleteFlag +
                ", toEmailAddress='" + toEmailAddress + '\'' +
                '}';
    }
}