package com.lefancrm.apicenter.dto;

/**
 * Created by lixianfeng on 2018/6/28.
 */
public class AuthInfoReqDTO {
    private String reqSerial;//请求流水号

    private String merchantId;//商户ID

    private String customerId;//会员ID

    private String userIndexCardNo;//卡索引

    private String capitalName;//资方名称

    private String capitalIndexCardNo;//资方对公卡索引号

    private String loanAgreementNo;//贷款协议号

    private Double loanAmt;//贷款金额

    private String loanDesc;//代扣摘要

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserIndexCardNo() {
        return userIndexCardNo;
    }

    public void setUserIndexCardNo(String userIndexCardNo) {
        this.userIndexCardNo = userIndexCardNo;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    public String getCapitalIndexCardNo() {
        return capitalIndexCardNo;
    }

    public void setCapitalIndexCardNo(String capitalIndexCardNo) {
        this.capitalIndexCardNo = capitalIndexCardNo;
    }

    public String getLoanAgreementNo() {
        return loanAgreementNo;
    }

    public void setLoanAgreementNo(String loanAgreementNo) {
        this.loanAgreementNo = loanAgreementNo;
    }

    public Double getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(Double loanAmt) {
        this.loanAmt = loanAmt;
    }

    public String getLoanDesc() {
        return loanDesc;
    }

    public void setLoanDesc(String loanDesc) {
        this.loanDesc = loanDesc;
    }
}
