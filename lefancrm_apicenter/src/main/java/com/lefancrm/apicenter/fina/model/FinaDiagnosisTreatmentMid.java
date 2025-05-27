package com.lefancrm.apicenter.fina.model;

import java.util.Date;

public class FinaDiagnosisTreatmentMid {
    private Long id;

    private Long diagnosisId;

    private Long treatmentId;

    private Double diseaseTypeScore;

    private Double tripleAChs;

    private Double tripleASpe;

    private Double tripleOther;

    private Double doubleAChs;

    private Double doubleOther;

    private Double firstHosptal;

    private Date createTime;

    private String createBy;

    private Integer deleteFlag;

    private Date updateTime;

    private String updateBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Long diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public Long getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
    }

    public Double getDiseaseTypeScore() {
        return diseaseTypeScore;
    }

    public void setDiseaseTypeScore(Double diseaseTypeScore) {
        this.diseaseTypeScore = diseaseTypeScore;
    }

    public Double getTripleAChs() {
        return tripleAChs;
    }

    public void setTripleAChs(Double tripleAChs) {
        this.tripleAChs = tripleAChs;
    }

    public Double getTripleASpe() {
        return tripleASpe;
    }

    public void setTripleASpe(Double tripleASpe) {
        this.tripleASpe = tripleASpe;
    }

    public Double getTripleOther() {
        return tripleOther;
    }

    public void setTripleOther(Double tripleOther) {
        this.tripleOther = tripleOther;
    }

    public Double getDoubleAChs() {
        return doubleAChs;
    }

    public void setDoubleAChs(Double doubleAChs) {
        this.doubleAChs = doubleAChs;
    }

    public Double getDoubleOther() {
        return doubleOther;
    }

    public void setDoubleOther(Double doubleOther) {
        this.doubleOther = doubleOther;
    }

    public Double getFirstHosptal() {
        return firstHosptal;
    }

    public void setFirstHosptal(Double firstHosptal) {
        this.firstHosptal = firstHosptal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}