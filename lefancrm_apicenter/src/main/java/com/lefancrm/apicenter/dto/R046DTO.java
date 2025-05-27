package com.lefancrm.apicenter.dto;

import java.io.Serializable;

public class R046DTO implements Serializable {

    private String version;

    private String charset;

    private String signMethod;

    private String signature;

    private String transType;

    private String bizType;

    private String bankEnc;

    private String bankShort;

    private String customerIdNo;

    private String customerIdType;

    private String customerName;

    private String cardType;

    private String bankCvn;

    private String expiredYear;

    private String expiredMonth;

    private String telephone;


    private String merchantId;

    private String merchantSeqNo;

    private String businessScene;


    private String merReserved;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBankEnc() {
        return bankEnc;
    }

    public void setBankEnc(String bankEnc) {
        this.bankEnc = bankEnc;
    }

    public String getBankShort() {
        return bankShort;
    }

    public void setBankShort(String bankShort) {
        this.bankShort = bankShort;
    }

    public String getCustomerIdNo() {
        return customerIdNo;
    }

    public void setCustomerIdNo(String customerIdNo) {
        this.customerIdNo = customerIdNo;
    }

    public String getCustomerIdType() {
        return customerIdType;
    }

    public void setCustomerIdType(String customerIdType) {
        this.customerIdType = customerIdType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBankCvn() {
        return bankCvn;
    }

    public void setBankCvn(String bankCvn) {
        this.bankCvn = bankCvn;
    }

    public String getExpiredYear() {
        return expiredYear;
    }

    public void setExpiredYear(String expiredYear) {
        this.expiredYear = expiredYear;
    }

    public String getExpiredMonth() {
        return expiredMonth;
    }

    public void setExpiredMonth(String expiredMonth) {
        this.expiredMonth = expiredMonth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantSeqNo() {
        return merchantSeqNo;
    }

    public void setMerchantSeqNo(String merchantSeqNo) {
        this.merchantSeqNo = merchantSeqNo;
    }

    public String getBusinessScene() {
        return businessScene;
    }

    public void setBusinessScene(String businessScene) {
        this.businessScene = businessScene;
    }

    public String getMerReserved() {
        return merReserved;
    }

    public void setMerReserved(String merReserved) {
        this.merReserved = merReserved;
    }
}
