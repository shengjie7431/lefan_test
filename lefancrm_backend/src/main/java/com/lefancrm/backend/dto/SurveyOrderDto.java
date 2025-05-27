package com.lefancrm.backend.dto;

import java.util.Date;

public class SurveyOrderDto {
    private Long id;

    private Long proId;

    private String proCode;

    private String proImage;

    private String proReamrk;

    private Double proPrice;

    private Long lefanCoin;

    private Double realBuyPrice;

    private Long realBuyCoin;

    private Date orderTime;

    private Integer orderState;

    private String orderCode;

    private Long allowUserId;

    private String allowUserName;

    private String allowUserTel;

    private String allowUserAddress;

    private Integer isPay;

    private Date payTime;

    private String logisticsCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }

    public String getProReamrk() {
        return proReamrk;
    }

    public void setProReamrk(String proReamrk) {
        this.proReamrk = proReamrk;
    }

    public Double getProPrice() {
        return proPrice;
    }

    public void setProPrice(Double proPrice) {
        this.proPrice = proPrice;
    }

    public Long getLefanCoin() {
        return lefanCoin;
    }

    public void setLefanCoin(Long lefanCoin) {
        this.lefanCoin = lefanCoin;
    }

    public Double getRealBuyPrice() {
        return realBuyPrice;
    }

    public void setRealBuyPrice(Double realBuyPrice) {
        this.realBuyPrice = realBuyPrice;
    }

    public Long getRealBuyCoin() {
        return realBuyCoin;
    }

    public void setRealBuyCoin(Long realBuyCoin) {
        this.realBuyCoin = realBuyCoin;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Long getAllowUserId() {
        return allowUserId;
    }

    public void setAllowUserId(Long allowUserId) {
        this.allowUserId = allowUserId;
    }

    public String getAllowUserName() {
        return allowUserName;
    }

    public void setAllowUserName(String allowUserName) {
        this.allowUserName = allowUserName;
    }

    public String getAllowUserTel() {
        return allowUserTel;
    }

    public void setAllowUserTel(String allowUserTel) {
        this.allowUserTel = allowUserTel;
    }

    public String getAllowUserAddress() {
        return allowUserAddress;
    }

    public void setAllowUserAddress(String allowUserAddress) {
        this.allowUserAddress = allowUserAddress;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }
}