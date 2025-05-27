package com.lefancrm.apicenter.model;

import java.util.Date;

public class PinganAuthInfo {
    private Long id;

    private String reqSerial;

    private String merchantId;

    private String customerId;

    private String userIndexCardNo;

    private String capitalName;

    private String capitalIndexCardNo;

    private String capitalIndexCard;

    private String loanAgreementNo;

    private Double loanAmt;

    private String loanDesc;

    private Date createTime;

    private Integer isSuccess;

    private String remark;

    private String repayLoanAgreementNo;

    private String signResult;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCapitalIndexCard() {
        return capitalIndexCard;
    }

    public void setCapitalIndexCard(String capitalIndexCard) {
        this.capitalIndexCard = capitalIndexCard;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRepayLoanAgreementNo() {
        return repayLoanAgreementNo;
    }

    public void setRepayLoanAgreementNo(String repayLoanAgreementNo) {
        this.repayLoanAgreementNo = repayLoanAgreementNo;
    }

    public String getSignResult() {
        return signResult;
    }

    public void setSignResult(String signResult) {
        this.signResult = signResult;
    }
}