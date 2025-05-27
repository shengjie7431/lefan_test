package com.lefancrm.backend.dto;

public class BillingApplyCorporationEnumDto {
    private Long id;

    private Long corporationId;

    private String corporationName;

    private Long billingEnumId;

    private String billingEnumName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(Long corporationId) {
        this.corporationId = corporationId;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public Long getBillingEnumId() {
        return billingEnumId;
    }

    public void setBillingEnumId(Long billingEnumId) {
        this.billingEnumId = billingEnumId;
    }

    public String getBillingEnumName() {
        return billingEnumName;
    }

    public void setBillingEnumName(String billingEnumName) {
        this.billingEnumName = billingEnumName;
    }
}