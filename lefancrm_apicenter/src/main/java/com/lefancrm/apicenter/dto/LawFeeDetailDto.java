package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.LawFeeDetail;

public class LawFeeDetailDto extends LawFeeDetail {

    private Long entrustUserId;//委托人id

    private String entrustUserName;//委托人姓名

    private String entrustUserTel;//委托人电话

    private String refoundRemark; // 退费说明

    public Long getEntrustUserId() {
        return entrustUserId;
    }

    public void setEntrustUserId(Long entrustUserId) {
        this.entrustUserId = entrustUserId;
    }

    public String getEntrustUserName() {
        return entrustUserName;
    }

    public void setEntrustUserName(String entrustUserName) {
        this.entrustUserName = entrustUserName;
    }

    public String getRefoundRemark() {
        return refoundRemark;
    }

    public void setRefoundRemark(String refoundRemark) {
        this.refoundRemark = refoundRemark;
    }

    public String getEntrustUserTel() {
        return entrustUserTel;
    }

    public void setEntrustUserTel(String entrustUserTel) {
        this.entrustUserTel = entrustUserTel;
    }
}