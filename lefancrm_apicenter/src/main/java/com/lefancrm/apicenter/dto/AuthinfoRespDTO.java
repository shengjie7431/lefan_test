package com.lefancrm.apicenter.dto;

/**
 * Created by lixianfeng on 2018/6/28.
 */
public class AuthinfoRespDTO {
    private String respCode;
    private String respMsg;
    private String accessToken;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
