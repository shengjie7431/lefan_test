package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.PaymentEstimateInquiry;

public class PaymentEstimateInquiryDto extends PaymentEstimateInquiry{
    /**
     * 输入的金额  扣费金额/开票金额 不同阶段意义不一样
     */
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    private Double assetsAmount;//本金
    private Double interstAmount;//利息
    private Double laveServiceAmount;//剩余服务费
    private Integer cycleDays;//周期

    private Double okAssessAmount;//最终评估金额 2018年11月14日10:39:19 新增

    private Integer caseType;

    private Boolean isWorkInjury = false;//是否是工商事故案件

    public Double getAssetsAmount() {
        return assetsAmount;
    }

    public void setAssetsAmount(Double assetsAmount) {
        this.assetsAmount = assetsAmount;
    }

    public Double getInterstAmount() {
        return interstAmount;
    }

    public void setInterstAmount(Double interstAmount) {
        this.interstAmount = interstAmount;
    }

    public Double getLaveServiceAmount() {
        return laveServiceAmount;
    }

    public void setLaveServiceAmount(Double laveServiceAmount) {
        this.laveServiceAmount = laveServiceAmount;
    }

    public Integer getCycleDays() {
        return cycleDays;
    }

    public void setCycleDays(Integer cycleDays) {
        this.cycleDays = cycleDays;
    }

    public Double getOkAssessAmount() {
        return okAssessAmount;
    }

    public void setOkAssessAmount(Double okAssessAmount) {
        this.okAssessAmount = okAssessAmount;
    }

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public Boolean getIsWorkInjury() {
        return isWorkInjury;
    }

    public void setIsWorkInjury(Boolean isWorkInjury) {
        this.isWorkInjury = isWorkInjury;
    }
}