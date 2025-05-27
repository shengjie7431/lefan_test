package com.lefancrm.backend.dto;

import java.util.Date;
import java.util.List;

public class SurveyFranchiseeDto {
    private Long id;

    private String name;

    private String code;

    private Integer level;

    private Long parentId;

    private String parentName;

    private String areaName;

    private Long areaTypeId;

    private Integer areaType;

    private Integer type;

    private Long createBy;

    private String createByName;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private String province;

    private String city;

    private String district;

    private Integer provinceId;

    private Integer cityId;

    private Integer districtId;

    private Integer isSpecial;

    private Double balanceAmount;

    private Double accumulatedAmount;

    private String appUser;

    private String apvUser;

    private Double medicalMoney;//多份病史的价格

    private Integer payStateOk;

    private Integer aloneBill;

    private String acceptUser;

    private String acceptUserIdcard;

    private String acceptUserIdcardUrl;

    private List acceptUserIdcardUrlList;

    private String bankCard;

    private String bankName;

    private String bankUrl;

    private List bankUrlList;

    private Double mainProvinceMoney;//主省价格

    private Double viceProvinceMoney;//副省价格

    /**
     * '业务属性（1：互助，2：保险，3：互助+保险）'
     */
    private Integer busType;

    private Integer insuranceType;//保司调查方类别

    /**
     * 机构状态
     */
    private Integer orgState;

    /**
     * 关联人事管理-机构/部门id
     */
    private Integer departmentId;

    /**
     * 关联人事管理-机构/部门名称
     */
    private String departmentName;

    /**
     *深度案件价格
     */
    private Double deepCasesPrice;

    private Integer surveyNum;

    private Integer checkNum;

    private Integer serviceType;

    private Double maxMedicalMoney;

    public Integer getBusType() {
        return busType;
    }

    public void setBusType(Integer busType) {
        this.busType = busType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getAreaTypeId() {
        return areaTypeId;
    }

    public void setAreaTypeId(Long areaTypeId) {
        this.areaTypeId = areaTypeId;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Integer isSpecial) {
        this.isSpecial = isSpecial;
    }

    public Double getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Double getAccumulatedAmount() {
        return accumulatedAmount;
    }

    public void setAccumulatedAmount(Double accumulatedAmount) {
        this.accumulatedAmount = accumulatedAmount;
    }

    public String getAppUser() {
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    public String getApvUser() {
        return apvUser;
    }

    public void setApvUser(String apvUser) {
        this.apvUser = apvUser;
    }

    public Double getMedicalMoney() {
        return medicalMoney;
    }

    public void setMedicalMoney(Double medicalMoney) {
        this.medicalMoney = medicalMoney;
    }

    public Integer getPayStateOk() {
        return payStateOk;
    }

    public void setPayStateOk(Integer payStateOk) {
        this.payStateOk = payStateOk;
    }

    public Integer getAloneBill() {
        return aloneBill;
    }

    public void setAloneBill(Integer aloneBill) {
        this.aloneBill = aloneBill;
    }

    public String getAcceptUser() {
        return acceptUser;
    }

    public void setAcceptUser(String acceptUser) {
        this.acceptUser = acceptUser;
    }

    public String getAcceptUserIdcard() {
        return acceptUserIdcard;
    }

    public void setAcceptUserIdcard(String acceptUserIdcard) {
        this.acceptUserIdcard = acceptUserIdcard;
    }

    public String getAcceptUserIdcardUrl() {
        return acceptUserIdcardUrl;
    }

    public void setAcceptUserIdcardUrl(String acceptUserIdcardUrl) {
        this.acceptUserIdcardUrl = acceptUserIdcardUrl;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankUrl() {
        return bankUrl;
    }

    public void setBankUrl(String bankUrl) {
        this.bankUrl = bankUrl;
    }

    public Double getMainProvinceMoney() {
        return mainProvinceMoney;
    }

    public void setMainProvinceMoney(Double mainProvinceMoney) {
        this.mainProvinceMoney = mainProvinceMoney;
    }

    public Double getViceProvinceMoney() {
        return viceProvinceMoney;
    }

    public void setViceProvinceMoney(Double viceProvinceMoney) {
        this.viceProvinceMoney = viceProvinceMoney;
    }

    public List getAcceptUserIdcardUrlList() {
        return acceptUserIdcardUrlList;
    }

    public void setAcceptUserIdcardUrlList(List acceptUserIdcardUrlList) {
        this.acceptUserIdcardUrlList = acceptUserIdcardUrlList;
    }

    public List getBankUrlList() {
        return bankUrlList;
    }

    public void setBankUrlList(List bankUrlList) {
        this.bankUrlList = bankUrlList;
    }

    public Integer getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(Integer insuranceType) {
        this.insuranceType = insuranceType;
    }

    public Integer getOrgState() {
        return orgState;
    }

    public void setOrgState(Integer orgState) {
        this.orgState = orgState;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Double getDeepCasesPrice() {
        return deepCasesPrice;
    }

    public void setDeepCasesPrice(Double deepCasesPrice) {
        this.deepCasesPrice = deepCasesPrice;
    }

    public Integer getSurveyNum() {
        return surveyNum;
    }

    public void setSurveyNum(Integer surveyNum) {
        this.surveyNum = surveyNum;
    }

    public Integer getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(Integer checkNum) {
        this.checkNum = checkNum;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Double getMaxMedicalMoney() {
        return maxMedicalMoney;
    }

    public void setMaxMedicalMoney(Double maxMedicalMoney) {
        this.maxMedicalMoney = maxMedicalMoney;
    }
}