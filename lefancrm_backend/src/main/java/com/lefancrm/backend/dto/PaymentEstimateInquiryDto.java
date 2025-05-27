package com.lefancrm.backend.dto;

public class PaymentEstimateInquiryDto {
    private Long id;

    private Double payableFee;

    private Double freePrePayFee;

    private Double medicalFee;

    private Double serviceRate;

    private Double agentServiceFee;

    private Double reductionServiceFee;

    private Long paymentEstimateId;

    private Long userId;

    private Double advanceFee;

    private Double serviceFee;

    private Double servicePayRate;

    private Double finalFee;

    private Integer isUpdate;

    private Double discountLoanFee;

    private Double applyDiscountLoanFee;

    private Double shouldDeFee;

    private Double realDeFee;

    private Integer isNeedLoan;

    private Double stillNeedFee;

    private Double loanRate;

    private Double loanFee;

    private Double insuranceFee;

    private Double interestRate;

    private Double totalLoanFee;

    private Double totalDeFee;

    private Double amount;

    private Double assetsAmount;//本金
    private Double interstAmount;//利息
    private Double laveServiceAmount;//剩余服务费
    private Integer cycleDays;//周期

    private Double okAssessAmount;//最终评估金额 2018年11月14日10:39:19 新增

    private Integer chargeType;
    private Integer caseType;

    private Integer advcanceType;

    private Boolean isWorkInjury;

    private Integer state;
    private String inquiryDesc;
    private Integer calculationType;
    private Integer rateBaseType;
    private Double invalidismIndemnifyFee;
    private Double spiritComfortFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPayableFee() {
        return payableFee;
    }

    public void setPayableFee(Double payableFee) {
        this.payableFee = payableFee;
    }

    public Double getFreePrePayFee() {
        return freePrePayFee;
    }

    public void setFreePrePayFee(Double freePrePayFee) {
        this.freePrePayFee = freePrePayFee;
    }

    public Double getMedicalFee() {
        return medicalFee;
    }

    public void setMedicalFee(Double medicalFee) {
        this.medicalFee = medicalFee;
    }

    public Double getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(Double serviceRate) {
        this.serviceRate = serviceRate;
    }

    public Double getAgentServiceFee() {
        return agentServiceFee;
    }

    public void setAgentServiceFee(Double agentServiceFee) {
        this.agentServiceFee = agentServiceFee;
    }

    public Double getReductionServiceFee() {
        return reductionServiceFee;
    }

    public void setReductionServiceFee(Double reductionServiceFee) {
        this.reductionServiceFee = reductionServiceFee;
    }

    public Long getPaymentEstimateId() {
        return paymentEstimateId;
    }

    public void setPaymentEstimateId(Long paymentEstimateId) {
        this.paymentEstimateId = paymentEstimateId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAdvanceFee() {
        return advanceFee;
    }

    public void setAdvanceFee(Double advanceFee) {
        this.advanceFee = advanceFee;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getServicePayRate() {
        return servicePayRate;
    }

    public void setServicePayRate(Double servicePayRate) {
        this.servicePayRate = servicePayRate;
    }

    public Double getFinalFee() {
        return finalFee;
    }

    public void setFinalFee(Double finalFee) {
        this.finalFee = finalFee;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Double getDiscountLoanFee() {
        return discountLoanFee;
    }

    public void setDiscountLoanFee(Double discountLoanFee) {
        this.discountLoanFee = discountLoanFee;
    }

    public Double getApplyDiscountLoanFee() {
        return applyDiscountLoanFee;
    }

    public void setApplyDiscountLoanFee(Double applyDiscountLoanFee) {
        this.applyDiscountLoanFee = applyDiscountLoanFee;
    }

    public Double getShouldDeFee() {
        return shouldDeFee;
    }

    public void setShouldDeFee(Double shouldDeFee) {
        this.shouldDeFee = shouldDeFee;
    }

    public Double getRealDeFee() {
        return realDeFee;
    }

    public void setRealDeFee(Double realDeFee) {
        this.realDeFee = realDeFee;
    }

    public Integer getIsNeedLoan() {
        return isNeedLoan;
    }

    public void setIsNeedLoan(Integer isNeedLoan) {
        this.isNeedLoan = isNeedLoan;
    }

    public Double getStillNeedFee() {
        return stillNeedFee;
    }

    public void setStillNeedFee(Double stillNeedFee) {
        this.stillNeedFee = stillNeedFee;
    }

    public Double getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(Double loanRate) {
        this.loanRate = loanRate;
    }

    public Double getLoanFee() {
        return loanFee;
    }

    public void setLoanFee(Double loanFee) {
        this.loanFee = loanFee;
    }

    public Double getInsuranceFee() {
        return insuranceFee;
    }

    public void setInsuranceFee(Double insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getTotalLoanFee() {
        return totalLoanFee;
    }

    public void setTotalLoanFee(Double totalLoanFee) {
        this.totalLoanFee = totalLoanFee;
    }

    public Double getTotalDeFee() {
        return totalDeFee;
    }

    public void setTotalDeFee(Double totalDeFee) {
        this.totalDeFee = totalDeFee;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

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

    public Integer getAdvcanceType() {
        return advcanceType;
    }

    public void setAdvcanceType(Integer advcanceType) {
        this.advcanceType = advcanceType;
    }

    public Boolean getIsWorkInjury() {
        return isWorkInjury;
    }

    public void setIsWorkInjury(Boolean isWorkInjury) {
        this.isWorkInjury = isWorkInjury;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getInquiryDesc() {
        return inquiryDesc;
    }

    public void setInquiryDesc(String inquiryDesc) {
        this.inquiryDesc = inquiryDesc;
    }

    public Integer getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(Integer calculationType) {
        this.calculationType = calculationType;
    }

    public Integer getRateBaseType() {
        return rateBaseType;
    }

    public void setRateBaseType(Integer rateBaseType) {
        this.rateBaseType = rateBaseType;
    }

    public Double getInvalidismIndemnifyFee() {
        return invalidismIndemnifyFee;
    }

    public void setInvalidismIndemnifyFee(Double invalidismIndemnifyFee) {
        this.invalidismIndemnifyFee = invalidismIndemnifyFee;
    }

    public Double getSpiritComfortFee() {
        return spiritComfortFee;
    }

    public void setSpiritComfortFee(Double spiritComfortFee) {
        this.spiritComfortFee = spiritComfortFee;
    }
}