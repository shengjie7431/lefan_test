package com.lefancrm.apicenter.dto;

import java.util.Date;

public class UserSessionDto {

	private String userToken;
	// private String userType;
	// private String username;
	// private String nickname;
	private String nickname;
	private String icon;
	private String phone;
	private String pushToken;
    private String bsRoleIds;
    private Long userId;

    private String userName;

    private String nickName;

    private String userTel;

    private String email;

    private Integer userState;


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

    public Integer getIsPromoter() {
        return isPromoter;
    }

    public void setIsPromoter(Integer isPromoter) {
        this.isPromoter = isPromoter;
    }

    private  Integer  isPromoter;
	// private String registerFlag;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	// public String getRegisterFlag() {
	// return registerFlag;
	// }
	//
	// public void setRegisterFlag(String registerFlag) {
	// this.registerFlag = registerFlag;
	// }

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	//
	// public String getUserType() {
	// return userType;
	// }
	//
	// public void setUserType(String userType) {
	// this.userType = userType;
	// }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	// public String getUsername() {
	// return username;
	// }
	//
	// public void setUsername(String username) {
	// this.username = username;
	// }
	//
	// public String getNickname() {
	// return nickname;
	// }
	//
	// public void setNickname(String nickname) {
	// this.nickname = nickname;
	// }

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    // public String getDriverStatus() {
	// return driverStatus;
	// }
	//
	// public void setDriverStatus(String driverStatus) {
	// this.driverStatus = driverStatus;
	// }

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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

}
