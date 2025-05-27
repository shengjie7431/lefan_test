package com.lefancrm.apicenter.dto;

import java.math.BigDecimal;

/**
 * Created by lixianfeng on 2018/6/29.
 */
public class RepayRespDTO {
    private String respCode;
    private String respMsg;
    public String getRespCode() {
        return respCode;
    }

    private String reqNo;

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }
}
