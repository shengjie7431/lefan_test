package com.lefancrm.backend.dto;

import java.util.Date;

/**
 * Created by ting on 2017/11/9.
 */
public class BaokuAccidentDto {
    private Long id;

    private String productCode;

    private String customerOrderId;

    private String policyHolder;

    private String policyHolderId;

    private String policyHolderIdType;

    private String insuredName;

    private String insuredNameId;

    private String insuredNameIdType;

    private Date insuredNameBirthday;

    private Date startDate;

    private Integer insuredCount;

    private String mobilePhone;

    private String remark;

    private Integer occupationCategory;

    private Date createTime;

    private String invoiceNumber;

    private String insuranceCertificateUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public String getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(String policyHolder) {
        this.policyHolder = policyHolder;
    }

    public String getPolicyHolderId() {
        return policyHolderId;
    }

    public void setPolicyHolderId(String policyHolderId) {
        this.policyHolderId = policyHolderId;
    }

    public String getPolicyHolderIdType() {
        return policyHolderIdType;
    }

    public void setPolicyHolderIdType(String policyHolderIdType) {
        this.policyHolderIdType = policyHolderIdType;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getInsuredNameId() {
        return insuredNameId;
    }

    public void setInsuredNameId(String insuredNameId) {
        this.insuredNameId = insuredNameId;
    }

    public String getInsuredNameIdType() {
        return insuredNameIdType;
    }

    public void setInsuredNameIdType(String insuredNameIdType) {
        this.insuredNameIdType = insuredNameIdType;
    }

    public Date getInsuredNameBirthday() {
        return insuredNameBirthday;
    }

    public void setInsuredNameBirthday(Date insuredNameBirthday) {
        this.insuredNameBirthday = insuredNameBirthday;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getInsuredCount() {
        return insuredCount;
    }

    public void setInsuredCount(Integer insuredCount) {
        this.insuredCount = insuredCount;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOccupationCategory() {
        return occupationCategory;
    }

    public void setOccupationCategory(Integer occupationCategory) {
        this.occupationCategory = occupationCategory;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInsuranceCertificateUrl() {
        return insuranceCertificateUrl;
    }

    public void setInsuranceCertificateUrl(String insuranceCertificateUrl) {
        this.insuranceCertificateUrl = insuranceCertificateUrl;
    }
}

