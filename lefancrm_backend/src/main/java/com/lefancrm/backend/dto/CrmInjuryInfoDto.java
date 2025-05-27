package com.lefancrm.backend.dto;

import java.util.Date;

public class CrmInjuryInfoDto {
    private Long id;

    private Long customerId;

    private String injuryName;

    private Double usedMedicalFee;

    private Double oweMedicalFee;

    private Double neededMedicalFee;

    private Integer financingType;

    private String visHospital;

    private Integer isInhospital;

    private Integer isOperation;

    private String hospitalDepartments;

    private Integer bedNumber;

    private Integer hospitalNumber;

    private String doctor;

    private String doctorTel;

    private String nurse;

    private String nurseTel;

    private String otherDesc;

    private Double dataRate;

    private Date createDate;

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

    public String getInjuryName() {
        return injuryName;
    }

    public void setInjuryName(String injuryName) {
        this.injuryName = injuryName;
    }

    public Double getUsedMedicalFee() {
        return usedMedicalFee;
    }

    public void setUsedMedicalFee(Double usedMedicalFee) {
        this.usedMedicalFee = usedMedicalFee;
    }

    public Double getOweMedicalFee() {
        return oweMedicalFee;
    }

    public void setOweMedicalFee(Double oweMedicalFee) {
        this.oweMedicalFee = oweMedicalFee;
    }

    public Double getNeededMedicalFee() {
        return neededMedicalFee;
    }

    public void setNeededMedicalFee(Double neededMedicalFee) {
        this.neededMedicalFee = neededMedicalFee;
    }

    public Integer getFinancingType() {
        return financingType;
    }

    public void setFinancingType(Integer financingType) {
        this.financingType = financingType;
    }

    public String getVisHospital() {
        return visHospital;
    }

    public void setVisHospital(String visHospital) {
        this.visHospital = visHospital;
    }

    public Integer getIsInhospital() {
        return isInhospital;
    }

    public void setIsInhospital(Integer isInhospital) {
        this.isInhospital = isInhospital;
    }

    public Integer getIsOperation() {
        return isOperation;
    }

    public void setIsOperation(Integer isOperation) {
        this.isOperation = isOperation;
    }

    public String getHospitalDepartments() {
        return hospitalDepartments;
    }

    public void setHospitalDepartments(String hospitalDepartments) {
        this.hospitalDepartments = hospitalDepartments;
    }

    public Integer getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(Integer bedNumber) {
        this.bedNumber = bedNumber;
    }

    public Integer getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNumber(Integer hospitalNumber) {
        this.hospitalNumber = hospitalNumber;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDoctorTel() {
        return doctorTel;
    }

    public void setDoctorTel(String doctorTel) {
        this.doctorTel = doctorTel;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public String getNurseTel() {
        return nurseTel;
    }

    public void setNurseTel(String nurseTel) {
        this.nurseTel = nurseTel;
    }

    public String getOtherDesc() {
        return otherDesc;
    }

    public void setOtherDesc(String otherDesc) {
        this.otherDesc = otherDesc;
    }

    public Double getDataRate() {
        return dataRate;
    }

    public void setDataRate(Double dataRate) {
        this.dataRate = dataRate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}