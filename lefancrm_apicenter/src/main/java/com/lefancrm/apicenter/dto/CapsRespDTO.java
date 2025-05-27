package com.lefancrm.apicenter.dto;

import java.io.Serializable;

public class CapsRespDTO implements Serializable {

    /**返回正文AES*/
    private String content;

    /**返回正文散列*/
    private String token;

    /**散列算法*/
    private String hashFunc;

    /**CAPS应答码*/
    private String code;

    /**CAPS应答描述*/
    private String memo;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
