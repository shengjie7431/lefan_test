package com.lefancrm.apicenter.dto;

public class OrgAccountDto {


    private Long orgId;

    private Long userId;

    private Double userRecharge;

    private Double userUsable;

    private String orgName;

    private String orgTel;

    private String linkName;

    private String linkTel;


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgTel() {
        return orgTel;
    }

    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }




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