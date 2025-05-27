package com.lefancrm.apicenter.dto.think;

public class YWDTO {
    private Long orgId;
    private String orgName;
    private Long surveyInfoId;
    private Integer serviceType; //1全案  2单点
    private Integer orgAttr;//1保司 2互助
    private Integer sharp;//是否反欺诈 0否 1是
    private Double accScore;
    private Double entrustMoney;//机构的委托方金额
    private Double accMoney;//机构的核算收入

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

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getOrgAttr() {
        return orgAttr;
    }

    public void setOrgAttr(Integer orgAttr) {
        this.orgAttr = orgAttr;
    }

    public Integer getSharp() {
        return sharp;
    }

    public void setSharp(Integer sharp) {
        this.sharp = sharp;
    }

    public Double getAccScore() {
        return accScore;
    }

    public void setAccScore(Double accScore) {
        this.accScore = accScore;
    }

    public Double getEntrustMoney() {
        return entrustMoney;
    }

    public void setEntrustMoney(Double entrustMoney) {
        this.entrustMoney = entrustMoney;
    }

    public Double getAccMoney() {
        return accMoney;
    }

    public void setAccMoney(Double accMoney) {
        this.accMoney = accMoney;
    }
}
