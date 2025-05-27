package com.lefancrm.apicenter.model;

import java.util.Date;

public class PinganRepayInfo {
    private Long id;

    private String reqSerial;

    private String merchantId;

    private String repaymentAgreementNo;

    private String repaymentType;

    private Double repaymentAmt;

    private String token;

    private Date createTime;

    private String createBy;

    private String success;

    private String remark;

    private Long caseId;

    private String caseNo;

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

    public Double getRepaymentAmt() {
        return repaymentAmt;
    }

    public void setRepaymentAmt(Double repaymentAmt) {
        this.repaymentAmt = repaymentAmt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }
}