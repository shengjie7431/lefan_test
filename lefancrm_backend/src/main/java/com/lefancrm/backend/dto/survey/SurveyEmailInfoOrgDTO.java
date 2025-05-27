package com.lefancrm.backend.dto.survey;

public class SurveyEmailInfoOrgDTO {
    private Long id;

    private Long emailInfoId;

    private String emailInfoName;

    private Long entrustOrgId;

    private String entrustOrgName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmailInfoId() {
        return emailInfoId;
    }

    public void setEmailInfoId(Long emailInfoId) {
        this.emailInfoId = emailInfoId;
    }

    public String getEmailInfoName() {
        return emailInfoName;
    }

    public void setEmailInfoName(String emailInfoName) {
        this.emailInfoName = emailInfoName;
    }

    public Long getEntrustOrgId() {
        return entrustOrgId;
    }

    public void setEntrustOrgId(Long entrustOrgId) {
        this.entrustOrgId = entrustOrgId;
    }

    public String getEntrustOrgName() {
        return entrustOrgName;
    }

    public void setEntrustOrgName(String entrustOrgName) {
        this.entrustOrgName = entrustOrgName;
    }
}