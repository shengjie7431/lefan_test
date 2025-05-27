package com.lefancrm.apicenter.fina.model;

public class FinaMedicalInsurance {
    private Long id;

    private Long finaId;

    private Long finaInfoId;

    private Integer medicalInsuranceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinaId() {
        return finaId;
    }

    public void setFinaId(Long finaId) {
        this.finaId = finaId;
    }

    public Long getFinaInfoId() {
        return finaInfoId;
    }

    public void setFinaInfoId(Long finaInfoId) {
        this.finaInfoId = finaInfoId;
    }

    public Integer getMedicalInsuranceType() {
        return medicalInsuranceType;
    }

    public void setMedicalInsuranceType(Integer medicalInsuranceType) {
        this.medicalInsuranceType = medicalInsuranceType;
    }
}