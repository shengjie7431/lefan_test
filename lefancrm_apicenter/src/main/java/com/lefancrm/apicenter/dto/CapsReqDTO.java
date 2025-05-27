package com.lefancrm.apicenter.dto;

import java.io.Serializable;

public class CapsReqDTO implements Serializable {

    /**请求正文*/
    private String content;

    /**请求正文散列*/
    private String token;

    /**散列算法*/
    private String hashFunc;

    /**服务码*/
    private String serviceCode;

    /**系统ID*/
    private String systemId;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHashFunc() {
        return hashFunc;
    }

    public void setHashFunc(String hashFunc) {
        this.hashFunc = hashFunc;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

}
