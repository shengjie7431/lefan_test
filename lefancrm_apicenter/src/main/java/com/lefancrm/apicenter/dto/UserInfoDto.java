package com.lefancrm.apicenter.dto;

import java.io.Serializable;

/**
 * Created by Thinkpad on 2016/10/29.
 */
public class UserInfoDto implements Serializable{

    private String userIcon;

    private Long birthday;

    private Integer height;

    private Double weight;

    private String hobby;

    private String gymnasium;

    private Integer userIntegral;

    private Long userId;

    private String userName;

    private String nickName;

    private String userTel;

    private Integer userState;

    private Integer isPromoter;

    private Integer deleteFlag;

    private Integer sex;

    private String promotedQrcode;

    private Integer userType;

    private Long orgId;

    private String orgName;

    private String bsRoleIds;

    private Integer userAccount;

    private Integer infoSafeUserId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getGymnasium() {
        return gymnasium;
    }

    public void setGymnasium(String gymnasium) {
        this.gymnasium = gymnasium;
    }

    public Integer getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(Integer userIntegral) {
        this.userIntegral = userIntegral;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public Integer getIsPromoter() {
        return isPromoter;
    }

    public void setIsPromoter(Integer isPromoter) {
        this.isPromoter = isPromoter;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getPromotedQrcode() {
        return promotedQrcode;
    }

    public void setPromotedQrcode(String promotedQrcode) {
        this.promotedQrcode = promotedQrcode;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

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

    public String getBsRoleIds() {
        return bsRoleIds;
    }

    public void setBsRoleIds(String bsRoleIds) {
        this.bsRoleIds = bsRoleIds;
    }

    public Integer getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Integer userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getInfoSafeUserId() {
        return infoSafeUserId;
    }

    public void setInfoSafeUserId(Integer infoSafeUserId) {
        this.infoSafeUserId = infoSafeUserId;
    }
}
