package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

public class SurveyConsignor {
    private Long id;

    private String name;

    private String remark;

    private String company;

    private Long areaTypeId;

    private String areaName;

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

    private String code;

    private List<SurveyConsignorDepartment> surveyConsignorDepartment;

    private Integer isCredit;

    private String priceRemark;

    private String appUser;

    private String apvUser;

    private Integer businessAttr;

    private Double medicalMoney;//多份病史的价格

    private Integer orgAttr;//公司属性

    private Integer efficiencyAttr;//时效设置（1：工作日；2、自然日）

    private Double mainProvinceMoney;//主省价格

    private Double viceProvinceMoney;//副省价格

    private Integer modelType;//模板数据 1、乐凡 2、正言  3、中德  4、中宏 5、互助

    private Integer orgState;

    private Integer autoFillState;//是否粘贴，自动填充信息(创建案件时)

    private Double attrMaxSize;

    private Integer surveyNum;

    private Integer checkNum;

    private Integer advanceParty;

    private Integer serviceType;

    private String receiver;

    private String receiverTel;

    private String receiverAddress;

    private Integer sharp;

    private Long marketUserId;
    private String marketUserName;
    private Double marketRate;
    private Long recommendUserId;
    private String recommendUserName;
    private Double recommendRate;


    private Long billCompanyId;

    private String billCompanyName;

    private String modelName;

    private String ruleName;

    private List<BillingApplyCompany> companys;//开票对象

    private Integer pdfType;

    public Long getMarketUserId() {
        return marketUserId;
    }

    public void setMarketUserId(Long marketUserId) {
        this.marketUserId = marketUserId;
    }

    public String getMarketUserName() {
        return marketUserName;
    }

    public void setMarketUserName(String marketUserName) {
        this.marketUserName = marketUserName;
    }

    public Double getMarketRate() {
        return marketRate;
    }

    public void setMarketRate(Double marketRate) {
        this.marketRate = marketRate;
    }

    public Long getRecommendUserId() {
        return recommendUserId;
    }

    public void setRecommendUserId(Long recommendUserId) {
        this.recommendUserId = recommendUserId;
    }

    public String getRecommendUserName() {
        return recommendUserName;
    }

    public void setRecommendUserName(String recommendUserName) {
        this.recommendUserName = recommendUserName;
    }

    public Double getRecommendRate() {
        return recommendRate;
    }

    public void setRecommendRate(Double recommendRate) {
        this.recommendRate = recommendRate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getAreaTypeId() {
        return areaTypeId;
    }

    public void setAreaTypeId(Long areaTypeId) {
        this.areaTypeId = areaTypeId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SurveyConsignorDepartment> getSurveyConsignorDepartment() {
        return surveyConsignorDepartment;
    }

    public void setSurveyConsignorDepartment(List<SurveyConsignorDepartment> surveyConsignorDepartment) {
        this.surveyConsignorDepartment = surveyConsignorDepartment;
    }

    public Integer getIsCredit() {
        return isCredit;
    }

    public void setIsCredit(Integer isCredit) {
        this.isCredit = isCredit;
    }

    public String getPriceRemark() {
        return priceRemark;
    }

    public void setPriceRemark(String priceRemark) {
        this.priceRemark = priceRemark;
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

    public Integer getBusinessAttr() {
        return businessAttr;
    }

    public void setBusinessAttr(Integer businessAttr) {
        this.businessAttr = businessAttr;
    }

    public Double getMedicalMoney() {
        return medicalMoney;
    }

    public void setMedicalMoney(Double medicalMoney) {
        this.medicalMoney = medicalMoney;
    }

    public Integer getOrgAttr() {
        return orgAttr;
    }

    public void setOrgAttr(Integer orgAttr) {
        this.orgAttr = orgAttr;
    }

    public Integer getEfficiencyAttr() {
        return efficiencyAttr;
    }

    public void setEfficiencyAttr(Integer efficiencyAttr) {
        this.efficiencyAttr = efficiencyAttr;
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

    public Integer getModelType() {
        return modelType;
    }

    public void setModelType(Integer modelType) {
        this.modelType = modelType;
    }

    public Integer getOrgState() {
        return orgState;
    }

    public void setOrgState(Integer orgState) {
        this.orgState = orgState;
    }

    public Integer getAutoFillState() {
        return autoFillState;
    }

    public void setAutoFillState(Integer autoFillState) {
        this.autoFillState = autoFillState;
    }

    public Double getAttrMaxSize() {
        return attrMaxSize;
    }

    public void setAttrMaxSize(Double attrMaxSize) {
        this.attrMaxSize = attrMaxSize;
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

    public Integer getAdvanceParty() {
        return advanceParty;
    }

    public void setAdvanceParty(Integer advanceParty) {
        this.advanceParty = advanceParty;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Integer getSharp() {
        return sharp;
    }

    public void setSharp(Integer sharp) {
        this.sharp = sharp;
    }

    public Long getBillCompanyId() {
        return billCompanyId;
    }

    public void setBillCompanyId(Long billCompanyId) {
        this.billCompanyId = billCompanyId;
    }

    public String getBillCompanyName() {
        return billCompanyName;
    }

    public void setBillCompanyName(String billCompanyName) {
        this.billCompanyName = billCompanyName;
    }

    public List<BillingApplyCompany> getCompanys() {
        return companys;
    }

    public void setCompanys(List<BillingApplyCompany> companys) {
        this.companys = companys;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getPdfType() {
        return pdfType;
    }

    public void setPdfType(Integer pdfType) {
        this.pdfType = pdfType;
    }
}