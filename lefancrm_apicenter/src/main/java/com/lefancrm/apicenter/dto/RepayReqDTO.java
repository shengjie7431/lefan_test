package com.lefancrm.apicenter.dto;

import java.math.BigDecimal;

/**
 * Created by lixianfeng on 2018/6/29.
 */
public class RepayReqDTO {
    private String reqSerial;
    private String merchantId;
    private String repaymentAgreementNo;
    private String repaymentType;
    private BigDecimal repaymentAmt;

    public String getReqSerial() {
        return reqSerial;
    }

    public void setReqSerial(String reqSerial) {
        this.reqSerial = reqSerial;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getRepaymentAgreementNo() {
        return repaymentAgreementNo;
    }

    public void setRepaymentAgreementNo(String repaymentAgreementNo) {
        this.repaymentAgreementNo = repaymentAgreementNo;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public BigDecimal getRepaymentAmt() {
        return repaymentAmt;
    }

    public void setRepaymentAmt(BigDecimal repaymentAmt) {
        this.repaymentAmt = repaymentAmt;
    }
}
