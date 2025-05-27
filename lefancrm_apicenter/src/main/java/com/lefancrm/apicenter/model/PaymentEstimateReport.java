package com.lefancrm.apicenter.model;

public class PaymentEstimateReport {
    private Long id;

    private Long paymentEstimateId;

    private String paymentProject;

    private Double medicalFee;

    private Double compulsoryInsuranceFee;

    private Double commercialInsuranceFee;

    private Double causeTroubleFee;

    private Double checkMedicalFee;

    private String checkCompulsoryInsuranceFee;

    private String checkCommercialInsuranceFee;

    private String checkCauseTroubleFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaymentEstimateId() {
        return paymentEstimateId;
    }

    public void setPaymentEstimateId(Long paymentEstimateId) {
        this.paymentEstimateId = paymentEstimateId;
    }

    public String getPaymentProject() {
        return paymentProject;
    }

    public void setPaymentProject(String paymentProject) {
        this.paymentProject = paymentProject;
    }

    public Double getMedicalFee() {
        return medicalFee;
    }

    public void setMedicalFee(Double medicalFee) {
        this.medicalFee = medicalFee;
    }

    public Double getCompulsoryInsuranceFee() {
        return compulsoryInsuranceFee;
    }

    public void setCompulsoryInsuranceFee(Double compulsoryInsuranceFee) {
        this.compulsoryInsuranceFee = compulsoryInsuranceFee;
    }

    public Double getCommercialInsuranceFee() {
        return commercialInsuranceFee;
    }

    public void setCommercialInsuranceFee(Double commercialInsuranceFee) {
        this.commercialInsuranceFee = commercialInsuranceFee;
    }

    public Double getCauseTroubleFee() {
        return causeTroubleFee;
    }

    public void setCauseTroubleFee(Double causeTroubleFee) {
        this.causeTroubleFee = causeTroubleFee;
    }

    public Double getCheckMedicalFee() {
        return checkMedicalFee;
    }

    public void setCheckMedicalFee(Double checkMedicalFee) {
        this.checkMedicalFee = checkMedicalFee;
    }

    public String getCheckCompulsoryInsuranceFee() {
        return checkCompulsoryInsuranceFee;
    }

    public void setCheckCompulsoryInsuranceFee(String checkCompulsoryInsuranceFee) {
        this.checkCompulsoryInsuranceFee = checkCompulsoryInsuranceFee;
    }

    public String getCheckCommercialInsuranceFee() {
        return checkCommercialInsuranceFee;
    }

    public void setCheckCommercialInsuranceFee(String checkCommercialInsuranceFee) {
        this.checkCommercialInsuranceFee = checkCommercialInsuranceFee;
    }

    public String getCheckCauseTroubleFee() {
        return checkCauseTroubleFee;
    }

    public void setCheckCauseTroubleFee(String checkCauseTroubleFee) {
        this.checkCauseTroubleFee = checkCauseTroubleFee;
    }
}