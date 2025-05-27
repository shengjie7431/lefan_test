package com.lefancrm.apicenter.vo;

public class R0568ReqVO extends BaseReqVO {

    /**发起渠道*/
    private String channel;

    /**来源系统名*/
    private String system;

    /**电脑IP*/
    private String pcIp;

    /**手机设备号*/
    private String deviceNum;

    /**手机设备信息*/
    private String deviceMessage;

    /**数据来源*/
    private String coOperCode;

    /**对应商户号*/
    private String merchantNo;

    /**商户会员编号*/
    private String partnerId;

    /**绑定手机号*/
    private String bindMobile;

    /**真实姓名*/
    private String realName;

    /**证件类型*/
    private String identityType;

    /**证件号码*/
    private String identityNumber;

    /**性别*/
    private String sex;

    /**生日*/
    private String birthDate;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getPcIp() {
        return pcIp;
    }

    public void setPcIp(String pcIp) {
        this.pcIp = pcIp;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getDeviceMessage() {
        return deviceMessage;
    }

    public void setDeviceMessage(String deviceMessage) {
        this.deviceMessage = deviceMessage;
    }

    public String getCoOperCode() {
        return coOperCode;
    }

    public void setCoOperCode(String coOperCode) {
        this.coOperCode = coOperCode;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getBindMobile() {
        return bindMobile;
    }

    public void setBindMobile(String bindMobile) {
        this.bindMobile = bindMobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
