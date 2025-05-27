package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

public class BillingApplyImgs {
    private Long id;

    private Long billId;

    private String billingCode;

    private String billingImgs;

    private Long createById;

    private String createBy;

    private Date createTime;

    private Integer state;

    private Double billingMoney;

    private Date stateUpdateTime;

    private Long stateUpdateById;

    private String stateUpdateBy;

    private Integer type;
    private Integer payState;

    private List<BillingApplyAccounts> billingApplyAccountsList;

    private Double okAccountMoney;//已到账金额
    private Double noAccountMoney;//未到账金额

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getBillingCode() {
        return billingCode;
    }

    public void setBillingCode(String billingCode) {
        this.billingCode = billingCode;
    }

    public String getBillingImgs() {
        return billingImgs;
    }

    public void setBillingImgs(String billingImgs) {
        this.billingImgs = billingImgs;
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Double getBillingMoney() {
        return billingMoney;
    }

    public void setBillingMoney(Double billingMoney) {
        this.billingMoney = billingMoney;
    }

    public Date getStateUpdateTime() {
        return stateUpdateTime;
    }

    public void setStateUpdateTime(Date stateUpdateTime) {
        this.stateUpdateTime = stateUpdateTime;
    }

    public Long getStateUpdateById() {
        return stateUpdateById;
    }

    public void setStateUpdateById(Long stateUpdateById) {
        this.stateUpdateById = stateUpdateById;
    }

    public String getStateUpdateBy() {
        return stateUpdateBy;
    }

    public void setStateUpdateBy(String stateUpdateBy) {
        this.stateUpdateBy = stateUpdateBy;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public List<BillingApplyAccounts> getBillingApplyAccountsList() {
        return billingApplyAccountsList;
    }

    public void setBillingApplyAccountsList(List<BillingApplyAccounts> billingApplyAccountsList) {
        this.billingApplyAccountsList = billingApplyAccountsList;
    }

    public Double getOkAccountMoney() {
        return okAccountMoney;
    }

    public void setOkAccountMoney(Double okAccountMoney) {
        this.okAccountMoney = okAccountMoney;
    }

    public Double getNoAccountMoney() {
        return noAccountMoney;
    }

    public void setNoAccountMoney(Double noAccountMoney) {
        this.noAccountMoney = noAccountMoney;
    }
}