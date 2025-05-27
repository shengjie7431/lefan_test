package com.lefancrm.apicenter.model;

import java.io.Serializable;

public class QueryBankCardByCustomerId4DomainBean implements Serializable {

    private Long id;

    private String bankType;

    private String bankShort;

    private String bankName;

    private String indexCardId;

    private String bankNoAfter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankShort() {
        return bankShort;
    }

    public void setBankShort(String bankShort) {
        this.bankShort = bankShort;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIndexCardId() {
        return indexCardId;
    }

    public void setIndexCardId(String indexCardId) {
        this.indexCardId = indexCardId;
    }

    public String getBankNoAfter() {
        return bankNoAfter;
    }

    public void setBankNoAfter(String bankNoAfter) {
        this.bankNoAfter = bankNoAfter;
    }
}
