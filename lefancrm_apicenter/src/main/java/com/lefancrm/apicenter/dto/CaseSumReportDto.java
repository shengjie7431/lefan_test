package com.lefancrm.apicenter.dto;

/**
 * Created by DELL on 2017/12/29.
 */
public class CaseSumReportDto {

    private Integer serviceFee;
    private Integer  loanFee;
    private Integer caseCount;


    public Integer getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Integer serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Integer getLoanFee() {
        return loanFee;
    }

    public void setLoanFee(Integer loanFee) {
        this.loanFee = loanFee;
    }

    public Integer getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(Integer caseCount) {
        this.caseCount = caseCount;
    }
}
