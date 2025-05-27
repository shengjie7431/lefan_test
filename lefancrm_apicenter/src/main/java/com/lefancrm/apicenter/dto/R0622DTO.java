package com.lefancrm.apicenter.dto;

import java.io.Serializable;

public class R0622DTO implements Serializable {

    /**发起渠道*/
    private String channel;

    /**来源系统名*/
    private String system;

    /**对应商户号*/
    private String merchantNo;

    /**商户会员编号*/
    private String partnerId;

    private String customerId;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
