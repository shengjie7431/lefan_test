package com.lefancrm.apicenter.model;

public class OrgAccount {


    private Long orgId;

    private Long userId;

    private Double userRecharge;

    private Double userUsable;

    public Double getUserForegift() {
        return userForegift;
    }

    public void setUserForegift(Double userForegift) {
        this.userForegift = userForegift;
    }

    private Double userForegift;

    private Double foregiftMoney;


    public Double getForegiftMoney() {
        return foregiftMoney;
    }

    public void setForegiftMoney(Double foregiftMoney) {
        this.foregiftMoney = foregiftMoney;
    }


    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getUserRecharge() {
        return userRecharge;
    }

    public void setUserRecharge(Double userRecharge) {
        this.userRecharge = userRecharge;
    }

    public Double getUserUsable() {
        return userUsable;
    }

    public void setUserUsable(Double userUsable) {
        this.userUsable = userUsable;
    }
}