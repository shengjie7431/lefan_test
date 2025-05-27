package com.lefancrm.apicenter.dto;

/**
 * Created by lixianfeng on 2019/1/3.
 */
public class SurveyFilesDto {
    private Long surveyInfoId;
    private Long catalogId;
    private String catalogName;
    private Long commonFileId;

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public Long getCommonFileId() {
        return commonFileId;
    }

    public void setCommonFileId(Long commonFileId) {
        this.commonFileId = commonFileId;
    }
}
