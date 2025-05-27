package com.lefancrm.apicenter.dto;

public class SurveyPays {
    private Long orgId;
    private Double appMoney;
    private String remark;

    private Double surveyMoney;
    private Double wageMoney;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Double getAppMoney() {
        return appMoney;
    }

    public void setAppMoney(Double appMoney) {
        this.appMoney = appMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getSurveyMoney() {
        return surveyMoney;
    }

    public void setSurveyMoney(Double surveyMoney) {
        this.surveyMoney = surveyMoney;
    }

    public Double getWageMoney() {
        return wageMoney;
    }

    public void setWageMoney(Double wageMoney) {
        this.wageMoney = wageMoney;
    }
}
