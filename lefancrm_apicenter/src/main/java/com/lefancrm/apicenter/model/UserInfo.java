package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

public class UserInfo {
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

    private Integer userAccount;

    private Long userProvinceId;

    private String userProvince;

    private Long userCityId;

    private String userCity;

    private Long userDistrictId;

    private String userDistrict;

    private List<Long> busUserRoleIds;

    private List<FrontRoleMenu> userRoleList;

    private List<BusUserRole> busUserRoles;

    private Integer isTester;

    public List<BusUserRole> getBusUserRoles() {
        return busUserRoles;
    }

    public void setBusUserRoles(List<BusUserRole> busUserRoles) {
        this.busUserRoles = busUserRoles;
    }

    public List<FrontRoleMenu> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<FrontRoleMenu> userRoleList) {
        this.userRoleList = userRoleList;
    }

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

    public Integer getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Integer userAccount) {
        this.userAccount = userAccount;
    }

    public Long getUserProvinceId() {
        return userProvinceId;
    }

    public void setUserProvinceId(Long userProvinceId) {
        this.userProvinceId = userProvinceId;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public Long getUserCityId() {
        return userCityId;
    }

    public void setUserCityId(Long userCityId) {
        this.userCityId = userCityId;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public Long getUserDistrictId() {
        return userDistrictId;
    }

    public void setUserDistrictId(Long userDistrictId) {
        this.userDistrictId = userDistrictId;
    }

    public String getUserDistrict() {
        return userDistrict;
    }

    public void setUserDistrict(String userDistrict) {
        this.userDistrict = userDistrict;
    }

    public List<Long> getBusUserRoleIds() {
        return busUserRoleIds;
    }

    public void setBusUserRoleIds(List<Long> busUserRoleIds) {
        this.busUserRoleIds = busUserRoleIds;
    }

    public Integer getIsTester() {
        return isTester;
    }

    public void setIsTester(Integer isTester) {
        this.isTester = isTester;
    }
}