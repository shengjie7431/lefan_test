package com.lefancrm.apicenter.model;

public class LevelPromotion {
    private Long id;

    private Long levelId;

    private String levelCode;

    private Integer promotionSignNum;

    private Double promotionMoney;

    private Integer promotionMonthNum;

    private Integer maintainSignNum;

    private Double maintainMoney;

    private Integer maintainMonthNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public Integer getPromotionSignNum() {
        return promotionSignNum;
    }

    public void setPromotionSignNum(Integer promotionSignNum) {
        this.promotionSignNum = promotionSignNum;
    }

    public Double getPromotionMoney() {
        return promotionMoney;
    }

    public void setPromotionMoney(Double promotionMoney) {
        this.promotionMoney = promotionMoney;
    }

    public Integer getPromotionMonthNum() {
        return promotionMonthNum;
    }

    public void setPromotionMonthNum(Integer promotionMonthNum) {
        this.promotionMonthNum = promotionMonthNum;
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