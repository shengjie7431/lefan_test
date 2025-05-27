package com.lefancrm.apicenter.vo;

import java.io.Serializable;

public class R0568RespVO implements Serializable {

    /**结果码*/
    private String respCode;

    /**备注*/
    private String memo;

    /**平安付会员号*/
    private String customerId;

    /**平安付外部会员号*/
    private String uId;

    /**arc-token*/
    private String token;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
