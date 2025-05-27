package com.lefancrm.apicenter.dto.report;

import com.lefancrm.apicenter.model.SurveyCaseDirection;

import java.util.Date;
import java.util.List;

public class SurveyAccEntrustDTO {
    private Long id;
    private String surveyNo;
    private String surveyCno;
    private String surveyPerson;
    private Double billMoney;//开票金额
    private int sun;//是否阳性
    private Date entrustTime;//委托时间
    private Date surveyEndTime;//风控审核通过时间
    private Date entrustEndTime;//保司终审通过时间
    private int days;//调查时效
    private List<SurveyCaseDirection> directions;
    private String surveyCaseNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getSurveyCno() {
        return surveyCno;
    }

    public void setSurveyCno(String surveyCno) {
        this.surveyCno = surveyCno;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public Double getBillMoney() {
        return billMoney;
    }

    public void setBillMoney(Double billMoney) {
        this.billMoney = billMoney;
    }

    public int getSun() {
        return sun;
    }

    public void setSun(int sun) {
        this.sun = sun;
    }

    public Date getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(Date entrustTime) {
        this.entrustTime = entrustTime;
    }

    public Date getSurveyEndTime() {
        return surveyEndTime;
    }

    public void setSurveyEndTime(Date surveyEndTime) {
        this.surveyEndTime = surveyEndTime;
    }

    public Date getEntrustEndTime() {
        return entrustEndTime;
    }

    public void setEntrustEndTime(Date entrustEndTime) {
        this.entrustEndTime = entrustEndTime;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public List<SurveyCaseDirection> getDirections() {
        return directions;
    }

    public void setDirections(List<SurveyCaseDirection> directions) {
        this.directions = directions;
    }

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }
}
