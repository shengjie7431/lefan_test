package com.lefancrm.apicenter.vo;

import java.io.Serializable;

public class BindCardVO implements Serializable {

    private String bindCardUrlValue;

    private String merchantNo;

    private String version;

    private String returnUrl;

    private String cancelUrl;

    private String requestMsg;

    private String requestNo;

    private String extfield;

    private String cipherText;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getExtfield() {
        return extfield;
    }

    public void setExtfield(String extfield) {
        this.extfield = extfield;
    }

    public String getBindCardUrlValue() {
        return bindCardUrlValue;
    }

    public void setBindCardUrlValue(String bindCardUrlValue) {
        this.bindCardUrlValue = bindCardUrlValue;
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }
}
