package com.lefancrm.apicenter.fina.model;

import java.util.Date;
import java.util.List;

public class FinaConfirmAccount {
    private Long id;

    private Long finaId;

    private Long finaInfoId;

    private Integer confirmType;

    private Double accountMoney;

    private Date accountTime;

    private Long surveyUserId;

    private String surveyUserName;

    private String signFilePath;

    private List<FinaFile> finaFiles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinaId() {
        return finaId;
    }

    public void setFinaId(Long finaId) {
        this.finaId = finaId;
    }

    public Long getFinaInfoId() {
        return finaInfoId;
    }

    public void setFinaInfoId(Long finaInfoId) {
        this.finaInfoId = finaInfoId;
    }

    public Integer getConfirmType() {
        return confirmType;
    }

    public void setConfirmType(Integer confirmType) {
        this.confirmType = confirmType;
    }

    public Double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Date getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(Date accountTime) {
        this.accountTime = accountTime;
    }

    public Long getSurveyUserId() {
        return surveyUserId;
    }

    public void setSurveyUserId(Long surveyUserId) {
        this.surveyUserId = surveyUserId;
    }

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
    }

    public String getSignFilePath() {
        return signFilePath;
    }

    public void setSignFilePath(String signFilePath) {
        this.signFilePath = signFilePath;
    }

    public List<FinaFile> getFinaFiles() {
        return finaFiles;
    }

    public void setFinaFiles(List<FinaFile> finaFiles) {
        this.finaFiles = finaFiles;
    }
}