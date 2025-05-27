package com.lefancrm.backend.dto;

import java.util.Date;

public class CasePerkpdzMonthReportDto {
    private Long id;

    private Long orgId;

    private String orgName;

    private Date date;

    private Double qctInvoiceMoney;

    private Double fxdcInvoiceMoney;

    private Double jjInvoiceMoney;

    private Double fyInvoiceMoney;

    private Double ccxInvoiceMoney;

    private Double ggpgInvoiceMoney;

    private Double otherInvoiceMoney;

    private Double qctReceivedMoney;

    private Double fxdcReceivedMoney;

    private Double jjReceivedMoney;

    private Double fyReceivedMoney;

    private Double ccxReceivedMoney;

    private Double ggpgReceivedMoney;

    private Double otherReceivedMoney;

    private Double pbInvoiceMoney;

    private Double pbReceivedMoney;

    private Double invoiceTitle;//开票金额合计

    private Double receivedTitle;//到账金额合计

    private Double qctInvalidMoney;

    private Double fxdcInvalidMoney;

    private Double jjInvalidMoney;

    private Double fyInvalidMoney;

    private Double ccxInvalidMoney;

    private Double ggpgInvalidMoney;

    private Double otherInvalidMoney;

    private Double pbInvalidMoney;

    private Double qctRedrushMoney;

    private Double fxdcRedrushMoney;

    private Double jjRedrushMoney;

    private Double fyRedrushMoney;

    private Double ccxRedrushMoney;

    private Double ggpgRedrushMoney;

    private Double otherRedrushMoney;

    private Double pbRedrushMoney;

    private Double invalidTitle; //作废金额合计

    private Double redrushTitle; //红冲金额合计

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getQctInvoiceMoney() {
        return qctInvoiceMoney;
    }

    public void setQctInvoiceMoney(Double qctInvoiceMoney) {
        this.qctInvoiceMoney = qctInvoiceMoney;
    }

    public Double getFxdcInvoiceMoney() {
        return fxdcInvoiceMoney;
    }

    public void setFxdcInvoiceMoney(Double fxdcInvoiceMoney) {
        this.fxdcInvoiceMoney = fxdcInvoiceMoney;
    }

    public Double getJjInvoiceMoney() {
        return jjInvoiceMoney;
    }

    public void setJjInvoiceMoney(Double jjInvoiceMoney) {
        this.jjInvoiceMoney = jjInvoiceMoney;
    }

    public Double getFyInvoiceMoney() {
        return fyInvoiceMoney;
    }

    public void setFyInvoiceMoney(Double fyInvoiceMoney) {
        this.fyInvoiceMoney = fyInvoiceMoney;
    }

    public Double getCcxInvoiceMoney() {
        return ccxInvoiceMoney;
    }

    public void setCcxInvoiceMoney(Double ccxInvoiceMoney) {
        this.ccxInvoiceMoney = ccxInvoiceMoney;
    }

    public Double getGgpgInvoiceMoney() {
        return ggpgInvoiceMoney;
    }

    public void setGgpgInvoiceMoney(Double ggpgInvoiceMoney) {
        this.ggpgInvoiceMoney = ggpgInvoiceMoney;
    }

    public Double getOtherInvoiceMoney() {
        return otherInvoiceMoney;
    }

    public void setOtherInvoiceMoney(Double otherInvoiceMoney) {
        this.otherInvoiceMoney = otherInvoiceMoney;
    }

    public Double getQctReceivedMoney() {
        return qctReceivedMoney;
    }

    public void setQctReceivedMoney(Double qctReceivedMoney) {
        this.qctReceivedMoney = qctReceivedMoney;
    }

    public Double getFxdcReceivedMoney() {
        return fxdcReceivedMoney;
    }

    public void setFxdcReceivedMoney(Double fxdcReceivedMoney) {
        this.fxdcReceivedMoney = fxdcReceivedMoney;
    }

    public Double getJjReceivedMoney() {
        return jjReceivedMoney;
    }

    public void setJjReceivedMoney(Double jjReceivedMoney) {
        this.jjReceivedMoney = jjReceivedMoney;
    }

    public Double getFyReceivedMoney() {
        return fyReceivedMoney;
    }

    public void setFyReceivedMoney(Double fyReceivedMoney) {
        this.fyReceivedMoney = fyReceivedMoney;
    }

    public Double getCcxReceivedMoney() {
        return ccxReceivedMoney;
    }

    public void setCcxReceivedMoney(Double ccxReceivedMoney) {
        this.ccxReceivedMoney = ccxReceivedMoney;
    }

    public Double getGgpgReceivedMoney() {
        return ggpgReceivedMoney;
    }

    public void setGgpgReceivedMoney(Double ggpgReceivedMoney) {
        this.ggpgReceivedMoney = ggpgReceivedMoney;
    }

    public Double getOtherReceivedMoney() {
        return otherReceivedMoney;
    }

    public void setOtherReceivedMoney(Double otherReceivedMoney) {
        this.otherReceivedMoney = otherReceivedMoney;
    }

    public Double getPbInvoiceMoney() {
        return pbInvoiceMoney;
    }

    public void setPbInvoiceMoney(Double pbInvoiceMoney) {
        this.pbInvoiceMoney = pbInvoiceMoney;
    }

    public Double getPbReceivedMoney() {
        return pbReceivedMoney;
    }

    public void setPbReceivedMoney(Double pbReceivedMoney) {
        this.pbReceivedMoney = pbReceivedMoney;
    }

    public Double getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(Double invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public Double getReceivedTitle() {
        return receivedTitle;
    }

    public void setReceivedTitle(Double receivedTitle) {
        this.receivedTitle = receivedTitle;
    }

    public Double getQctInvalidMoney() {
        return qctInvalidMoney;
    }

    public void setQctInvalidMoney(Double qctInvalidMoney) {
        this.qctInvalidMoney = qctInvalidMoney;
    }

    public Double getFxdcInvalidMoney() {
        return fxdcInvalidMoney;
    }

    public void setFxdcInvalidMoney(Double fxdcInvalidMoney) {
        this.fxdcInvalidMoney = fxdcInvalidMoney;
    }

    public Double getJjInvalidMoney() {
        return jjInvalidMoney;
    }

    public void setJjInvalidMoney(Double jjInvalidMoney) {
        this.jjInvalidMoney = jjInvalidMoney;
    }

    public Double getFyInvalidMoney() {
        return fyInvalidMoney;
    }

    public void setFyInvalidMoney(Double fyInvalidMoney) {
        this.fyInvalidMoney = fyInvalidMoney;
    }

    public Double getCcxInvalidMoney() {
        return ccxInvalidMoney;
    }

    public void setCcxInvalidMoney(Double ccxInvalidMoney) {
        this.ccxInvalidMoney = ccxInvalidMoney;
    }

    public Double getGgpgInvalidMoney() {
        return ggpgInvalidMoney;
    }

    public void setGgpgInvalidMoney(Double ggpgInvalidMoney) {
        this.ggpgInvalidMoney = ggpgInvalidMoney;
    }

    public Double getOtherInvalidMoney() {
        return otherInvalidMoney;
    }

    public void setOtherInvalidMoney(Double otherInvalidMoney) {
        this.otherInvalidMoney = otherInvalidMoney;
    }

    public Double getPbInvalidMoney() {
        return pbInvalidMoney;
    }

    public void setPbInvalidMoney(Double pbInvalidMoney) {
        this.pbInvalidMoney = pbInvalidMoney;
    }

    public Double getQctRedrushMoney() {
        return qctRedrushMoney;
    }

    public void setQctRedrushMoney(Double qctRedrushMoney) {
        this.qctRedrushMoney = qctRedrushMoney;
    }

    public Double getFxdcRedrushMoney() {
        return fxdcRedrushMoney;
    }

    public void setFxdcRedrushMoney(Double fxdcRedrushMoney) {
        this.fxdcRedrushMoney = fxdcRedrushMoney;
    }

    public Double getJjRedrushMoney() {
        return jjRedrushMoney;
    }

    public void setJjRedrushMoney(Double jjRedrushMoney) {
        this.jjRedrushMoney = jjRedrushMoney;
    }

    public Double getFyRedrushMoney() {
        return fyRedrushMoney;
    }

    public void setFyRedrushMoney(Double fyRedrushMoney) {
        this.fyRedrushMoney = fyRedrushMoney;
    }

    public Double getCcxRedrushMoney() {
        return ccxRedrushMoney;
    }

    public void setCcxRedrushMoney(Double ccxRedrushMoney) {
        this.ccxRedrushMoney = ccxRedrushMoney;
    }

    public Double getGgpgRedrushMoney() {
        return ggpgRedrushMoney;
    }

    public void setGgpgRedrushMoney(Double ggpgRedrushMoney) {
        this.ggpgRedrushMoney = ggpgRedrushMoney;
    }

    public Double getOtherRedrushMoney() {
        return otherRedrushMoney;
    }

    public void setOtherRedrushMoney(Double otherRedrushMoney) {
        this.otherRedrushMoney = otherRedrushMoney;
    }

    public Double getPbRedrushMoney() {
        return pbRedrushMoney;
    }

    public void setPbRedrushMoney(Double pbRedrushMoney) {
        this.pbRedrushMoney = pbRedrushMoney;
    }

    public Double getInvalidTitle() {
        return invalidTitle;
    }

    public void setInvalidTitle(Double invalidTitle) {
        this.invalidTitle = invalidTitle;
    }

    public Double getRedrushTitle() {
        return redrushTitle;
    }

    public void setRedrushTitle(Double redrushTitle) {
        this.redrushTitle = redrushTitle;
    }
}