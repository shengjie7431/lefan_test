package com.lefancrm.backend.dto;

public class PositionPromotionDto {
    private Long id;

    private Long positionId;

    private String positionName;

    private Integer promotionSignNum;

    private Integer promotionMonthNum;

    private Double promotionMoney;

    private Integer maintainSignNum;

    private Double maintainMoney;

    private Integer maintainMonthNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getPromotionSignNum() {
        return promotionSignNum;
    }

    public void setPromotionSignNum(Integer promotionSignNum) {
        this.promotionSignNum = promotionSignNum;
    }

    public Integer getPromotionMonthNum() {
        return promotionMonthNum;
    }

    public void setPromotionMonthNum(Integer promotionMonthNum) {
        this.promotionMonthNum = promotionMonthNum;
    }

    public Double getPromotionMoney() {
        return promotionMoney;
    }

    public void setPromotionMoney(Double promotionMoney) {
        this.promotionMoney = promotionMoney;
    }

    public Integer getMaintainSignNum() {
        return maintainSignNum;
    }

    public void setMaintainSignNum(Integer maintainSignNum) {
        this.maintainSignNum = maintainSignNum;
    }

    public Double getMaintainMoney() {
        return maintainMoney;
    }

    public void setMaintainMoney(Double maintainMoney) {
        this.maintainMoney = maintainMoney;
    }

    public Integer getMaintainMonthNum() {
        return maintainMonthNum;
    }

    public void setMaintainMonthNum(Integer maintainMonthNum) {
        this.maintainMonthNum = maintainMonthNum;
    }
}