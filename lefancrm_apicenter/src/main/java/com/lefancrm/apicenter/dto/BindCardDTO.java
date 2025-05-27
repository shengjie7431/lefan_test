package com.lefancrm.apicenter.dto;

import java.io.Serializable;

public class BindCardDTO implements Serializable {
    private String bindCardUrlValue;
    private String merchantNo;
    private String version;
    private String returnUrl;
    private String cancelUrl;
    private String requestMsg;
    private String requestNo;
    private String extfield;

    private boolean isSendPost = true;//是否发送绑卡请求请求 默认发送
    //以下三个字段只有在 绑卡 不发送请求的时候使用
    private Long caseId;
    private String customerId;
    private String userIndexCardNo;

    public String getBindCardUrlValue() {
        return bindCardUrlValue;
    }

    public void setBindCardUrlValue(String bindCardUrlValue) {
        this.bindCardUrlValue = bindCardUrlValue;
    }

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

    public String getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg;
    }

    public boolean isSendPost() {
        return isSendPost;
    }

    public void setSendPost(boolean isSendPost) {
        this.isSendPost = isSendPost;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserIndexCardNo() {
        return userIndexCardNo;
    }

    public void setUserIndexCardNo(String userIndexCardNo) {
        this.userIndexCardNo = userIndexCardNo;
    }
}
