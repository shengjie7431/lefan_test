package com.lefancrm.apicenter.fina.model;

import java.util.Date;

public class FinaSignSupplement {
    private Long id;

    private Long finaId;

    private Long finaInfoId;

    private String finaMail;

    private String attendingDoctor;

    private Double estimateMoney;

    private Integer type;

    private Integer invoiceNum;

    private Double invoiceMoney;

    private Date idcardEndDate;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinaId() {
        return finaId;
    }

    public void setFinaId(Long finaId) {
        this.finaId = finaId;
    }

    public Long getFinaInfoId() {
        return finaInfoId;
    }

    public void setFinaInfoId(Long finaInfoId) {
        this.finaInfoId = finaInfoId;
    }

    public String getFinaMail() {
        return finaMail;
    }

    public void setFinaMail(String finaMail) {
        this.finaMail = finaMail;
    }

    public String getAttendingDoctor() {
        return attendingDoctor;
    }

    public void setAttendingDoctor(String attendingDoctor) {
        this.attendingDoctor = attendingDoctor;
    }

    public Double getEstimateMoney() {
        return estimateMoney;
    }

    public void setEstimateMoney(Double estimateMoney) {
        this.estimateMoney = estimateMoney;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(Integer invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public Double getInvoiceMoney() {
        return invoiceMoney;
    }

    public void setInvoiceMoney(Double invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }

    public Date getIdcardEndDate() {
        return idcardEndDate;
    }

    public void setIdcardEndDate(Date idcardEndDate) {
        this.idcardEndDate = idcardEndDate;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}