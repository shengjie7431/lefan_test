package com.lefancrm.apicenter.model;

import java.util.Date;

public class SuningWithholdApply{
    private Long id;

    private Long caseId;

    private String caseNo;

    private String caseTitle;

    private Double withholdMoney;

    private Date withholdTime;

    private Integer withholdState;

    private Integer withholdType;

    private Long appUserId;//申请人ID
    private String code;//错误码
    private String remark;//备注
    private Integer accountState;//到账状态
    private Integer applyType;//代扣申请类型

    private String orderCode;

    private String tradeCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public Double getWithholdMoney() {
        return withholdMoney;
    }

    public void setWithholdMoney(Double withholdMoney) {
        this.withholdMoney = withholdMoney;
    }

    public Date getWithholdTime() {
        return withholdTime;
    }

    public void setWithholdTime(Date withholdTime) {
        this.withholdTime = withholdTime;
    }

    public Integer getWithholdState() {
        return withholdState;
    }

    public void setWithholdState(Integer withholdState) {
        this.withholdState = withholdState;
    }

    public Integer getWithholdType() {
        return withholdType;
    }

    public void setWithholdType(Integer withholdType) {
        this.withholdType = withholdType;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAccountState() {
        return accountState;
    }

    public void setAccountState(Integer accountState) {
        this.accountState = accountState;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }
}