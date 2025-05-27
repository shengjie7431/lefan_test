package com.lefancrm.apicenter.dto;

public class SurveyDirectionMoneyDTO {
    private int directionNum;
    private Double entrustMoney;//委托方价格
    private Double surveyMoney;//调查方价格
    private Double accMoney; //核算价格

    public Double getEntrustMoney() {
        return entrustMoney;
    }

    public void setEntrustMoney(Double entrustMoney) {
        this.entrustMoney = entrustMoney;
    }

    public Double getSurveyMoney() {
        return surveyMoney;
    }

    public void setSurveyMoney(Double surveyMoney) {
        this.surveyMoney = surveyMoney;
    }

    public Double getAccMoney() {
        return accMoney;
    }

    public void setAccMoney(Double accMoney) {
        this.accMoney = accMoney;
    }

    public int getDirectionNum() {
        return directionNum;
    }

    public void setDirectionNum(int directionNum) {
        this.directionNum = directionNum;
    }
}
