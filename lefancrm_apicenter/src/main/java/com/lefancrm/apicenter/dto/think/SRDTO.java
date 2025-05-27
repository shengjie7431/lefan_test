package com.lefancrm.apicenter.dto.think;

public class SRDTO {
    private Long orgId;
    private String orgName;
    private Long proId;
    private String proName;
    private Double billMoney;
    private Double billMoneyTax;
    private Double accMoney;
    private Double accMoneyTax;

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

    public Double getAccMoney() {
        return accMoney;
    }

    public void setAccMoney(Double accMoney) {
        this.accMoney = accMoney;
    }

    public Double getBillMoneyTax() {
        return billMoneyTax;
    }

    public void setBillMoneyTax(Double billMoneyTax) {
        this.billMoneyTax = billMoneyTax;
    }

    public Double getAccMoneyTax() {
        return accMoneyTax;
    }

    public void setAccMoneyTax(Double accMoneyTax) {
        this.accMoneyTax = accMoneyTax;
    }
}
