package com.lefancrm.apicenter.vo;

import java.io.Serializable;

public class CardIndexReqVO implements Serializable {

    private String cardIndexUrlValue;

    private String cardNo;

    private String cvv2;

    private String expDate;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCardIndexUrlValue() {
        return cardIndexUrlValue;
    }

    public void setCardIndexUrlValue(String cardIndexUrlValue) {
        this.cardIndexUrlValue = cardIndexUrlValue;
    }
}
