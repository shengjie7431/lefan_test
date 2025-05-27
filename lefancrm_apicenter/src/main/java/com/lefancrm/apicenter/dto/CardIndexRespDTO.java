package com.lefancrm.apicenter.dto;

import java.io.Serializable;

public class CardIndexRespDTO implements Serializable {

    private String cardSeqId;

    private String status;

    private String desc;

    public String getCardSeqId() {
        return cardSeqId;
    }

    public void setCardSeqId(String cardSeqId) {
        this.cardSeqId = cardSeqId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
