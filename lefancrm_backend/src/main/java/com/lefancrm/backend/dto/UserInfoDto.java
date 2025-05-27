package com.lefancrm.backend.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jani on 2017/3/15.
 */
public class UserInfoDto implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long userId;

    private String userName;

    private String nickName;

    private String userTel;

    private String email;

    private Integer userState;

    private Integer isPromoter;

    private Integer deleteFlag;

    private Integer sex;

    private String userAddress;

    private Date createTime;

    private String createBy;

    private Date modifyTime;

    private String modifyBy;

    private String promotedQrcode;

    private String img;

    private Integer userType;

    private Long orgId;

    private String orgName;

    private Long bsRoleId;

    private Integer caseId;

    private Integer caseState;

    private Integer infoSafeUserId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getPromotedQrcode() {
        return promotedQrcode;
    }

    public void setPromotedQrcode(String promotedQrcode) {
        this.promotedQrcode = promotedQrcode;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public Long getBsRoleId() {
        return bsRoleId;
    }

    public void setBsRoleId(Integer Long) {
        this.bsRoleId = bsRoleId;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getCaseState() {
        return caseState;
    }

    public void setCaseState(Integer caseState) {
        this.caseState = caseState;
    }

    public Integer getInfoSafeUserId() {
        return infoSafeUserId;
    }

    public void setInfoSafeUserId(Integer infoSafeUserId) {
        this.infoSafeUserId = infoSafeUserId;
    }
}
