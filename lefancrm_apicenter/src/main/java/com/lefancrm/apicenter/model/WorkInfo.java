package com.lefancrm.apicenter.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DELL on 2017/8/2.
 */
public class WorkInfo implements Serializable{
    private Integer isTrafficAccident;

    private String loanNo;   //贷款编号

    private Double loanMoney;

    private Integer loanPurpose;

    private String accidentProvinceCode;

    private String accidentProvinceName;

    private String accidentCityCode;

    private String accidentCityName;

    private String accidentDistrictCode;

    private String accidentDistrictName;

    private Integer agentType;

    private String claimIndemnityDesc;

    private String reson;

    private String agentNo;

    private Integer returnState;

    private Date accidentTime;

    private Long insuranceCompanyId;

    private String insuranceCompany;

    private String insured;

    private String carNo;

    private String userPhone;

    private String accidentProvince;

    private String accidentCity;

    private String accidentDistrict;

    private String accidentAddress;

    private Integer accidentType;

    private Long accidentProvinceId;

    private Long accidentCityId;

    private Long accidentDistrictId;

    private String promoterName;

    private String realName;

    private String phone;

    private Integer occupation;

    private String province;

    private String city;

    private String district;

    private String promotedQrcode;

    private String reason;

    private Integer promotedType;

    private String companyName;

    private String legalPerson;

    private String legalPersonTel;

    private String companyTel;

    private String companyPromotedQrcode;

    private Long parentId;

    private Integer depth;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    private Long provinceId;

    private Long cityId;

    private Long districtId;

    private Long salesmanId;

    private String salesmanName;

    private String userImg;

    private Byte isOpen;

    private Long companyId;
    private Long id;

    private Double money;

    private Long userId;

    private String userName;

    private Integer state;

    private Integer tradeType;

    private String tradeDesc;

    private Date createTime;

    private String createBy;

    private Integer accountsState;

    private Long accountsId;

    private String widraCode;

    private String openId;

    private String unlineImg;

    private String userTel;

    private String wechatId;
    private Long orgId;

    private String orgName;

    public Integer getIsTrafficAccident() {
        return isTrafficAccident;
    }

    public void setIsTrafficAccident(Integer isTrafficAccident) {
        this.isTrafficAccident = isTrafficAccident;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    public Integer getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(Integer loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public String getAccidentProvinceCode() {
        return accidentProvinceCode;
    }

    public void setAccidentProvinceCode(String accidentProvinceCode) {
        this.accidentProvinceCode = accidentProvinceCode;
    }

    public String getAccidentProvinceName() {
        return accidentProvinceName;
    }

    public void setAccidentProvinceName(String accidentProvinceName) {
        this.accidentProvinceName = accidentProvinceName;
    }

    public String getAccidentCityCode() {
        return accidentCityCode;
    }

    public void setAccidentCityCode(String accidentCityCode) {
        this.accidentCityCode = accidentCityCode;
    }

    public String getAccidentCityName() {
        return accidentCityName;
    }

    public void setAccidentCityName(String accidentCityName) {
        this.accidentCityName = accidentCityName;
    }

    public String getAccidentDistrictCode() {
        return accidentDistrictCode;
    }

    public void setAccidentDistrictCode(String accidentDistrictCode) {
        this.accidentDistrictCode = accidentDistrictCode;
    }

    public String getAccidentDistrictName() {
        return accidentDistrictName;
    }

    public void setAccidentDistrictName(String accidentDistrictName) {
        this.accidentDistrictName = accidentDistrictName;
    }

    public Integer getAgentType() {
        return agentType;
    }

    public void setAgentType(Integer agentType) {
        this.agentType = agentType;
    }

    public String getClaimIndemnityDesc() {
        return claimIndemnityDesc;
    }

    public void setClaimIndemnityDesc(String claimIndemnityDesc) {
        this.claimIndemnityDesc = claimIndemnityDesc;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

    public Integer getReturnState() {
        return returnState;
    }

    public void setReturnState(Integer returnState) {
        this.returnState = returnState;
    }

    public Date getAccidentTime() {
        return accidentTime;
    }

    public void setAccidentTime(Date accidentTime) {
        this.accidentTime = accidentTime;
    }

    public Long getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public void setInsuranceCompanyId(Long insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsured() {
        return insured;
    }

    public void setInsured(String insured) {
        this.insured = insured;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getAccidentProvince() {
        return accidentProvince;
    }

    public void setAccidentProvince(String accidentProvince) {
        this.accidentProvince = accidentProvince;
    }

    public String getAccidentCity() {
        return accidentCity;
    }

    public void setAccidentCity(String accidentCity) {
        this.accidentCity = accidentCity;
    }

    public String getAccidentDistrict() {
        return accidentDistrict;
    }

    public void setAccidentDistrict(String accidentDistrict) {
        this.accidentDistrict = accidentDistrict;
    }

    public String getAccidentAddress() {
        return accidentAddress;
    }

    public void setAccidentAddress(String accidentAddress) {
        this.accidentAddress = accidentAddress;
    }

    public Integer getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(Integer accidentType) {
        this.accidentType = accidentType;
    }

    public Long getAccidentProvinceId() {
        return accidentProvinceId;
    }

    public void setAccidentProvinceId(Long accidentProvinceId) {
        this.accidentProvinceId = accidentProvinceId;
    }

    public Long getAccidentCityId() {
        return accidentCityId;
    }

    public void setAccidentCityId(Long accidentCityId) {
        this.accidentCityId = accidentCityId;
    }

    public Long getAccidentDistrictId() {
        return accidentDistrictId;
    }

    public void setAccidentDistrictId(Long accidentDistrictId) {
        this.accidentDistrictId = accidentDistrictId;
    }

    public String getPromoterName() {
        return promoterName;
    }

    public void setPromoterName(String promoterName) {
        this.promoterName = promoterName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getOccupation() {
        return occupation;
    }

    public void setOccupation(Integer occupation) {
        this.occupation = occupation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPromotedQrcode() {
        return promotedQrcode;
    }

    public void setPromotedQrcode(String promotedQrcode) {
        this.promotedQrcode = promotedQrcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public Integer getPromotedType() {
        return promotedType;
    }

    public void setPromotedType(Integer promotedType) {
        this.promotedType = promotedType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getLegalPersonTel() {
        return legalPersonTel;
    }

    public void setLegalPersonTel(String legalPersonTel) {
        this.legalPersonTel = legalPersonTel;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyPromotedQrcode() {
        return companyPromotedQrcode;
    }

    public void setCompanyPromotedQrcode(String companyPromotedQrcode) {
        this.companyPromotedQrcode = companyPromotedQrcode;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Byte getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Byte isOpen) {
        this.isOpen = isOpen;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeDesc() {
        return tradeDesc;
    }

    public void setTradeDesc(String tradeDesc) {
        this.tradeDesc = tradeDesc;
    }

    public Integer getAccountsState() {
        return accountsState;
    }

    public void setAccountsState(Integer accountsState) {
        this.accountsState = accountsState;
    }

    public Long getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(Long accountsId) {
        this.accountsId = accountsId;
    }

    public String getWidraCode() {
        return widraCode;
    }

    public void setWidraCode(String widraCode) {
        this.widraCode = widraCode;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnlineImg() {
        return unlineImg;
    }

    public void setUnlineImg(String unlineImg) {
        this.unlineImg = unlineImg;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
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
}
