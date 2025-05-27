package com.lefancrm.apicenter.model;

import java.util.Date;

public class CaseSignGuidance {
    private Long id;

    private String caseNo;

    private Long caseId;

    private Integer compensatePlan;

    private Integer customerType;

    private Integer signProduct;

    private String signGuidance;

    private Date createTime;

    private Long guidancePersonId;

    private String guidancePersonName;

    private String guidanceReject;

    private Integer guidanceState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Integer getCompensatePlan() {
        return compensatePlan;
    }

    public void setCompensatePlan(Integer compensatePlan) {
        this.compensatePlan = compensatePlan;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Integer getSignProduct() {
        return signProduct;
    }

    public void setSignProduct(Integer signProduct) {
        this.signProduct = signProduct;
    }

    public String getSignGuidance() {
        return signGuidance;
    }

    public void setSignGuidance(String signGuidance) {
        this.signGuidance = signGuidance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getGuidancePersonId() {
        return guidancePersonId;
    }

    public void setGuidancePersonId(Long guidancePersonId) {
        this.guidancePersonId = guidancePersonId;
    }

    public String getGuidancePersonName() {
        return guidancePersonName;
    }

    public void setGuidancePersonName(String guidancePersonName) {
        this.guidancePersonName = guidancePersonName;
    }

    public String getGuidanceReject() {
        return guidanceReject;
    }

    public void setGuidanceReject(String guidanceReject) {
        this.guidanceReject = guidanceReject;
    }

    public Integer getGuidanceState() {
        return guidanceState;
    }

    public void setGuidanceState(Integer guidanceState) {
        this.guidanceState = guidanceState;
    }
}