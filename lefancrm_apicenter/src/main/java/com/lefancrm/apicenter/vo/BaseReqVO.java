package com.lefancrm.apicenter.vo;

import java.io.Serializable;

public class BaseReqVO implements Serializable {

    /**请求地址*/
    private String urlValue;

    /**服务码*/
    private String serviceCode = "R0568";

    /**系统ID*/
    private String systemId = "HEMODATA";

    /**散列算法*/
    private String hashFunc = "SHA-1";

    /**散列盐值*/
    private String salt = "abcd";

    /**请求正文散列*/
    private String token;

    /**加密算法*/
    private String encrypt = "AES";

    /**密钥*/
    private String key = "DF94CBDCA294DC5DEF1368E64313FD3B98FE5EBCAB7F23AE";

    /**请求正文*/
    private String content;

    private String privateKey;

    public String getUrlValue() {
        return urlValue;
    }

    public void setUrlValue(String urlValue) {
        this.urlValue = urlValue;
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

    public String getHashFunc() {
        return hashFunc;
    }

    public void setHashFunc(String hashFunc) {
        this.hashFunc = hashFunc;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
