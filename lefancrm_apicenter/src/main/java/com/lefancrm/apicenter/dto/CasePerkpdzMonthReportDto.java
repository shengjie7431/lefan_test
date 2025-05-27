package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.CasePerkpdzMonthReport;

import java.util.Date;

public class CasePerkpdzMonthReportDto extends CasePerkpdzMonthReport {

    private Double invoiceTitle;//开票金额合计

    private Double receivedTitle;//到账金额合计

    private Double invalidTitle; //作废金额合计

    private Double redrushTitle; //红冲金额合计

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