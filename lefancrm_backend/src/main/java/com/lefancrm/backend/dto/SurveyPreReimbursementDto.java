package com.lefancrm.backend.dto;

import java.util.Date;
import java.util.List;

public class SurveyPreReimbursementDto {
    private Long id;

    private Long surveyUserId;

    private String surveyUserName;

    private Double fileMoney;

    private Double trafficMoney;

    private Double totalMoney;

    private Integer state;

    private Date payDate;

    private Double payMoney;

    private Double nightMoney;

    private Double mileageMoney;

    private String orgName;

    private String returnText;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    List<InvestigatorPreDetailsDto> investigatorPreDetailsDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyUserId() {
        return surveyUserId;
    }

    public void setSurveyUserId(Long surveyUserId) {
        this.surveyUserId = surveyUserId;
    }

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
    }

    public Double getFileMoney() {
        return fileMoney;
    }

    public void setFileMoney(Double fileMoney) {
        this.fileMoney = fileMoney;
    }

    public Double getTrafficMoney() {
        return trafficMoney;
    }

    public void setTrafficMoney(Double trafficMoney) {
        this.trafficMoney = trafficMoney;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Double getNightMoney() {
        return nightMoney;
    }

    public void setNightMoney(Double nightMoney) {
        this.nightMoney = nightMoney;
    }

    public Double getMileageMoney() {
        return mileageMoney;
    }

    public void setMileageMoney(Double mileageMoney) {
        this.mileageMoney = mileageMoney;
    }

    public List<InvestigatorPreDetailsDto> getInvestigatorPreDetailsDtos() {
        return investigatorPreDetailsDtos;
    }

    public void setInvestigatorPreDetailsDtos(List<InvestigatorPreDetailsDto> investigatorPreDetailsDtos) {
        this.investigatorPreDetailsDtos = investigatorPreDetailsDtos;
    }

    public String getReturnText() {
        return returnText;
    }

    public void setReturnText(String returnText) {
        this.returnText = returnText;
    }
}