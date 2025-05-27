package com.lefancrm.backend.dto;

import java.util.Date;

public class SharecaseMediateDescDto {
    private Long id;

    private Long caseId;

    private String mediateDesc;

    private String onePrice;

    private Double injuredFee;

    private Double companyFee;

    private Double accidentFee;

    private String insuranceDesc;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getMediateDesc() {
        return mediateDesc;
    }

    public void setMediateDesc(String mediateDesc) {
        this.mediateDesc = mediateDesc;
    }

    public String getOnePrice() {
        return onePrice;
    }

    public void setOnePrice(String onePrice) {
        this.onePrice = onePrice;
    }

    public Double getInjuredFee() {
        return injuredFee;
    }

    public void setInjuredFee(Double injuredFee) {
        this.injuredFee = injuredFee;
    }

    public Double getCompanyFee() {
        return companyFee;
    }

    public void setCompanyFee(Double companyFee) {
        this.companyFee = companyFee;
    }

    public Double getAccidentFee() {
        return accidentFee;
    }

    public void setAccidentFee(Double accidentFee) {
        this.accidentFee = accidentFee;
    }

    public String getInsuranceDesc() {
        return insuranceDesc;
    }

    public void setInsuranceDesc(String insuranceDesc) {
        this.insuranceDesc = insuranceDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}