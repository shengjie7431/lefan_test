package com.lefancrm.backend.dto;

import java.util.Date;

public class ArrivalInfoDto {
    private Long id;

    private Long caseId;

    private String caseNo;

    private String caseTitle;

    private Double arrivalMoney;

    private Date arrivalTime;

    private Long userCommissionId;

    private String userCommissionName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public Double getArrivalMoney() {
        return arrivalMoney;
    }

    public void setArrivalMoney(Double arrivalMoney) {
        this.arrivalMoney = arrivalMoney;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getUserCommissionId() {
        return userCommissionId;
    }

    public void setUserCommissionId(Long userCommissionId) {
        this.userCommissionId = userCommissionId;
    }

    public String getUserCommissionName() {
        return userCommissionName;
    }

    public void setUserCommissionName(String userCommissionName) {
        this.userCommissionName = userCommissionName;
    }
}