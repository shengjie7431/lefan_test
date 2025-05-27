package com.lefancrm.apicenter.dto;

/**
 * Created by DELL on 2017/12/20.
 */
public class CaseSumDataDto {

    private Integer caseType;

    private Integer caseCount;

    private Integer serviceFee;


    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public Integer getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(Integer caseCount) {
        this.caseCount = caseCount;
    }

    public Integer getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Integer serviceFee) {
        this.serviceFee = serviceFee;
    }
}
