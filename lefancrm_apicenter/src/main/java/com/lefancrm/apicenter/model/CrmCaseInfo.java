package com.lefancrm.apicenter.model;

import java.util.Date;

public class CrmCaseInfo {
    private Long id;

    private Long customerId;

    private Date firstVisitTime;

    private Integer caseSource;

    private Integer isIntention;

    private Integer caseType;

    private Integer caseProgress;

    private Double claimFee;

    private Double loanFee;

    private Double serviceFee;

    private Date nextTime;

    private Date createTime;

    private Date updateTime;

    private Double dataRate;

    private CrmCustomerFollows crmCustomerFollows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getFirstVisitTime() {
        return firstVisitTime;
    }

    public void setFirstVisitTime(Date firstVisitTime) {
        this.firstVisitTime = firstVisitTime;
    }

    public Integer getCaseSource() {
        return caseSource;
    }

    public void setCaseSource(Integer caseSource) {
        this.caseSource = caseSource;
    }

    public Integer getIsIntention() {
        return isIntention;
    }

    public void setIsIntention(Integer isIntention) {
        this.isIntention = isIntention;
    }

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public Integer getCaseProgress() {
        return caseProgress;
    }

    public void setCaseProgress(Integer caseProgress) {
        this.caseProgress = caseProgress;
    }

    public Double getClaimFee() {
        return claimFee;
    }

    public void setClaimFee(Double claimFee) {
        this.claimFee = claimFee;
    }

    public Double getLoanFee() {
        return loanFee;
    }

    public void setLoanFee(Double loanFee) {
        this.loanFee = loanFee;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Double getDataRate() {
        return dataRate;
    }

    public void setDataRate(Double dataRate) {
        this.dataRate = dataRate;
    }

    public CrmCustomerFollows getCrmCustomerFollows() {
        return crmCustomerFollows;
    }

    public void setCrmCustomerFollows(CrmCustomerFollows crmCustomerFollows) {
        this.crmCustomerFollows = crmCustomerFollows;
    }
}