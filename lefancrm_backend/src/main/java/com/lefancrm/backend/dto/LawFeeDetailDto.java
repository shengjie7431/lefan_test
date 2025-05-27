package com.lefancrm.backend.dto;

import java.util.Date;

public class LawFeeDetailDto {

    private Long id;

    private Long caseId;

    private String caseNo;

    private Double money;

    private Date retreatTime;

    private Date arrTime;

    private Date createTime;

    private Integer type;

    private Long entrustUserId;//委托人id

    private String entrustUserName;//委托人姓名

    private String entrustUserTel;//委托人电话

    private String refoundRemark; // 退费说明

    private Integer state;

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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getRetreatTime() {
        return retreatTime;
    }

    public void setRetreatTime(Date retreatTime) {
        this.retreatTime = retreatTime;
    }

    public Date getArrTime() {
        return arrTime;
    }

    public void setArrTime(Date arrTime) {
        this.arrTime = arrTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getEntrustUserId() {
        return entrustUserId;
    }

    public void setEntrustUserId(Long entrustUserId) {
        this.entrustUserId = entrustUserId;
    }

    public String getEntrustUserName() {
        return entrustUserName;
    }

    public void setEntrustUserName(String entrustUserName) {
        this.entrustUserName = entrustUserName;
    }

    public String getRefoundRemark() {
        return refoundRemark;
    }

    public void setRefoundRemark(String refoundRemark) {
        this.refoundRemark = refoundRemark;
    }

    public String getEntrustUserTel() {
        return entrustUserTel;
    }

    public void setEntrustUserTel(String entrustUserTel) {
        this.entrustUserTel = entrustUserTel;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}