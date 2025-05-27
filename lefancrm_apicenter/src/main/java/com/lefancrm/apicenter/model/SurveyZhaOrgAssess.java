package com.lefancrm.apicenter.model;

public class SurveyZhaOrgAssess {
    private Long id;

    private Long zhaAssessId;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Double derogationMoney;

    private Integer isSun;

    private Double caeEff;

    private Double entrustSubmitMoney;

    private String surveyNo;

    private String claimsNo;

    private Long surveyInfoId;

    private String surveyPerson;

    private Double surveySubmitMoney;

    /**
     * 赔付金额
     */
    private Double pfAmt;

    /**
     * 减损奖励金
     */
    private Double jsAmt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getZhaAssessId() {
        return zhaAssessId;
    }

    public void setZhaAssessId(Long zhaAssessId) {
        this.zhaAssessId = zhaAssessId;
    }

    public Long getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Long surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
    }

    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
    }

    public Double getDerogationMoney() {
        return derogationMoney;
    }

    public void setDerogationMoney(Double derogationMoney) {
        this.derogationMoney = derogationMoney;
    }

    public Integer getIsSun() {
        return isSun;
    }

    public void setIsSun(Integer isSun) {
        this.isSun = isSun;
    }

    public Double getCaeEff() {
        return caeEff;
    }

    public void setCaeEff(Double caeEff) {
        this.caeEff = caeEff;
    }

    public Double getEntrustSubmitMoney() {
        return entrustSubmitMoney;
    }

    public void setEntrustSubmitMoney(Double entrustSubmitMoney) {
        this.entrustSubmitMoney = entrustSubmitMoney;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getClaimsNo() {
        return claimsNo;
    }

    public void setClaimsNo(String claimsNo) {
        this.claimsNo = claimsNo;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public Double getSurveySubmitMoney() {
        return surveySubmitMoney;
    }

    public void setSurveySubmitMoney(Double surveySubmitMoney) {
        this.surveySubmitMoney = surveySubmitMoney;
    }

    public Double getPfAmt() {
        return pfAmt;
    }

    public void setPfAmt(Double pfAmt) {
        this.pfAmt = pfAmt;
    }

    public Double getJsAmt() {
        return jsAmt;
    }

    public void setJsAmt(Double jsAmt) {
        this.jsAmt = jsAmt;
    }

    @Override
    public String toString() {
        return "SurveyZhaOrgAssess{" +
                "id=" + id +
                ", zhaAssessId=" + zhaAssessId +
                ", surveyOrgId=" + surveyOrgId +
                ", surveyOrgName='" + surveyOrgName + '\'' +
                ", derogationMoney=" + derogationMoney +
                ", isSun=" + isSun +
                ", caeEff=" + caeEff +
                ", entrustSubmitMoney=" + entrustSubmitMoney +
                ", surveyNo='" + surveyNo + '\'' +
                ", claimsNo='" + claimsNo + '\'' +
                ", surveyInfoId=" + surveyInfoId +
                ", surveyPerson='" + surveyPerson + '\'' +
                ", surveySubmitMoney=" + surveySubmitMoney +
                '}';
    }
}