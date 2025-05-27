package com.lefancrm.backend.dto.feere;

import java.util.Date;

public class CanBeSuedCaseDTO {
    private Boolean LAY_CHECKED = true;//默认全选  layui关键字
    private Long surveyInfoId;
    private String surveyCaseNo;
    private String surveyPerson;
    private Long entrustOrgId;
    private String entrustOrgName;
    private Date entrustOprDate;//保司审核时间
    private Double money;//报销费用金额
    private Double entrustOkPrice;//委托方确认结算价格

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
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

    public Date getEntrustOprDate() {
        return entrustOprDate;
    }

    public void setEntrustOprDate(Date entrustOprDate) {
        this.entrustOprDate = entrustOprDate;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Boolean getLAY_CHECKED() {
        return LAY_CHECKED;
    }

    public void setLAY_CHECKED(Boolean LAY_CHECKED) {
        this.LAY_CHECKED = LAY_CHECKED;
    }

    public Double getEntrustOkPrice() {
        return entrustOkPrice;
    }

    public void setEntrustOkPrice(Double entrustOkPrice) {
        this.entrustOkPrice = entrustOkPrice;
    }
}
