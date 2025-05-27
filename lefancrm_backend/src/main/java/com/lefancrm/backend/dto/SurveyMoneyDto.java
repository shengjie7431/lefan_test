package com.lefancrm.backend.dto;

/**
 * Created by lixianfeng on 2019/4/26.
 */
public class SurveyMoneyDto {
    private Long orgId;     //机构
    private String orgName; //机构名词
    private Double surveyMoney;  //调查费
    private Double wageMoney; //工资成本
    private Double fitMoney; //利润
    private Double hisOweMoney; //历史欠费
    private Double appMoney; //实际付款
    private Double entrustMoney;  //委托方金额
    private int payStateOk;

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

    public Double getFitMoney() {
        return fitMoney;
    }

    public void setFitMoney(Double fitMoney) {
        this.fitMoney = fitMoney;
    }

    public Double getEntrustMoney() {
        return entrustMoney;
    }

    public void setEntrustMoney(Double entrustMoney) {
        this.entrustMoney = entrustMoney;
    }

    public int getPayStateOk() {
        return payStateOk;
    }

    public void setPayStateOk(int payStateOk) {
        this.payStateOk = payStateOk;
    }

    public Double getHisOweMoney() {
        return hisOweMoney;
    }

    public void setHisOweMoney(Double hisOweMoney) {
        this.hisOweMoney = hisOweMoney;
    }

    public Double getAppMoney() {
        return appMoney;
    }

    public void setAppMoney(Double appMoney) {
        this.appMoney = appMoney;
    }
}
