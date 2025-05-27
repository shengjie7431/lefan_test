package com.lefancrm.backend.dto;

public class BillingApplyEnumItemDto {
    private Long id;

    private Long billingCorporationEnumId;

    private Long corporationId;

    private String corporationName;

    private Long billingEnumId;

    private String billingEnumName;

    private Long billingItemId;

    private String billingItemName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getBillingItemId() {
        return billingItemId;
    }

    public void setBillingItemId(Long billingItemId) {
        this.billingItemId = billingItemId;
    }

    public String getBillingItemName() {
        return billingItemName;
    }

    public void setBillingItemName(String billingItemName) {
        this.billingItemName = billingItemName;
    }

    public Long getBillingCorporationEnumId() {
        return billingCorporationEnumId;
    }

    public void setBillingCorporationEnumId(Long billingCorporationEnumId) {
        this.billingCorporationEnumId = billingCorporationEnumId;
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
}