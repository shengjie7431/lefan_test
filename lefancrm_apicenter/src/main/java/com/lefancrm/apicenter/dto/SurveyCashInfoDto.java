package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.*;

import java.util.List;

public class SurveyCashInfoDto {

    private SurveyFranchisee surveyFranchisee; //加盟机构

    private SurveyBankCard surveyBankCard; //银行卡号

    private List<SurveyCashInfoDetail> surveyCashInfoDetails;//提现明细

    private SurveyCashInfo surveyCashInfo; //提现记录

    private List<SurveyCashInfoRecord> surveyCashInfoRecords;//提现数据表

    public List<SurveyCashInfoDetail> getSurveyCashInfoDetails() {
        return surveyCashInfoDetails;
    }

    public void setSurveyCashInfoDetails(List<SurveyCashInfoDetail> surveyCashInfoDetails) {
        this.surveyCashInfoDetails = surveyCashInfoDetails;
    }

    public SurveyBankCard getSurveyBankCard() {
        return surveyBankCard;
    }

    public void setSurveyBankCard(SurveyBankCard surveyBankCard) {
        this.surveyBankCard = surveyBankCard;
    }

    public SurveyFranchisee getSurveyFranchisee() {
        return surveyFranchisee;
    }

    public void setSurveyFranchisee(SurveyFranchisee surveyFranchisee) {
        this.surveyFranchisee = surveyFranchisee;
    }

    public SurveyCashInfo getSurveyCashInfo() {
        return surveyCashInfo;
    }

    public void setSurveyCashInfo(SurveyCashInfo surveyCashInfo) {
        this.surveyCashInfo = surveyCashInfo;
    }

    public List<SurveyCashInfoRecord> getSurveyCashInfoRecords() {
        return surveyCashInfoRecords;
    }

    public void setSurveyCashInfoRecords(List<SurveyCashInfoRecord> surveyCashInfoRecords) {
        this.surveyCashInfoRecords = surveyCashInfoRecords;
    }
}