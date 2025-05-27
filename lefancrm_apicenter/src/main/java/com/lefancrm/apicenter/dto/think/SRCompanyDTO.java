package com.lefancrm.apicenter.dto.think;

public class SRCompanyDTO {
    private Long commpanyId;
    private String commpanyName;
    private Long proId;
    private String proName;
    private Double billMoney;
    private Double billMoneyTax;
    private Double accMoney;
    private Double accMoneyTax;

    public Long getCommpanyId() {
        return commpanyId;
    }

    public void setCommpanyId(Long commpanyId) {
        this.commpanyId = commpanyId;
    }

    public String getCommpanyName() {
        return commpanyName;
    }

    public void setCommpanyName(String commpanyName) {
        this.commpanyName = commpanyName;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Double getBillMoney() {
        return billMoney;
    }

    public void setBillMoney(Double billMoney) {
        this.billMoney = billMoney;
    }

    public Double getBillMoneyTax() {
        return billMoneyTax;
    }

    public void setBillMoneyTax(Double billMoneyTax) {
        this.billMoneyTax = billMoneyTax;
    }

    public Double getAccMoney() {
        return accMoney;
    }

    public void setAccMoney(Double accMoney) {
        this.accMoney = accMoney;
    }

    public Double getAccMoneyTax() {
        return accMoneyTax;
    }

    public void setAccMoneyTax(Double accMoneyTax) {
        this.accMoneyTax = accMoneyTax;
    }

}
