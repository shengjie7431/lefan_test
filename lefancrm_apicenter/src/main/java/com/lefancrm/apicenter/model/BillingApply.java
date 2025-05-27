package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

public class BillingApply {
    private Long id;

    private Long caseId;

    private String caseNo;

    private String caseTitle;

    private Double servcieMoney;

    private Double channelMoney;

    private Double insuranceMoney;

    private Double deductionMoney;

    private Double billingMoney;

    private Integer billingState;

    private Date billingTime;

    private String img;

    private String billingCode;

    private Integer billingEnum;

    private Integer billingItem;

    private Integer billingType;

    private Long orgId;

    private String orgName;

    private String recipientsName;

    private String recipientsPhone;

    private String province;

    private String city;

    private String district;

    private Integer provinceId;

    private Integer cityId;

    private Integer districtId;

    private String address;

    private String insuredName;

    private String carNo;

    private String woundedName;

    private String insurerCompanyName;

    private String policyNo;

    private String reportNo;

    private String remark;

    private Date createTime;

    private Long createById;

    private String createBy;

    private Long billingById;

    private String billingBy;

    private Date updateTime;

    private Long updateById;

    private String updateBy;

    private String operReason;//审核原因

    private Integer isPreSign;//预签约：1、预签约案件

    private Integer businessType;//业务类别

    private String meritName;//绩效所属人员

    private Double confirmAccountMoney;//公估确认到账金额

    private Integer confirmAccountState;//公估确认到账状态

    private Date confirmAccountTime;//公估确认到账时间

    private String rejectReason;//驳回申请原因

    private Long companyId; // 开票对象id

    private String companyName;//开票对象name

    private Long parentId; // 合并开票的父级id

    private Integer isMerge;//是否是合并数据：1、是

    private Boolean isRed = false;//是否显示红色

    private Long surveyDepartmentId;

    private String surveyDepartmentName;

    private Long surveyConsignorId;

    private String surveyConsignorName;

    private Integer productType;

    private Integer billingSource;

    private Long surveyBillSubjectId; //开票主体

    private String surveyBillSubjectName;

    private Long surveyOrgId;//调查机构

    private String surveyOrgName;//调查机构

    private Double okBillMoney;//已开票金额
    private Double noBillMoney;//未开票金额
    private Double okAccountMoney;//已到账金额
    private Double noAccountMoney;//未到账金额

    private List<BillingApplyAccounts> billingApplyAccountsList;
    private Long meritUserId;

    private Long staffOrgId;

    private String staffOrgName;

    private Double taxRate;

    public Long getStaffOrgId() {
        return staffOrgId;
    }

    public void setStaffOrgId(Long staffOrgId) {
        this.staffOrgId = staffOrgId;
    }

    public String getStaffOrgName() {
        return staffOrgName;
    }

    public void setStaffOrgName(String staffOrgName) {
        this.staffOrgName = staffOrgName;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public Double getServcieMoney() {
        return servcieMoney;
    }

    public void setServcieMoney(Double servcieMoney) {
        this.servcieMoney = servcieMoney;
    }

    public Double getChannelMoney() {
        return channelMoney;
    }

    public void setChannelMoney(Double channelMoney) {
        this.channelMoney = channelMoney;
    }

    public Double getInsuranceMoney() {
        return insuranceMoney;
    }

    public void setInsuranceMoney(Double insuranceMoney) {
        this.insuranceMoney = insuranceMoney;
    }

    public Double getDeductionMoney() {
        return deductionMoney;
    }

    public void setDeductionMoney(Double deductionMoney) {
        this.deductionMoney = deductionMoney;
    }

    public Double getBillingMoney() {
        return billingMoney;
    }

    public void setBillingMoney(Double billingMoney) {
        this.billingMoney = billingMoney;
    }

    public Integer getBillingState() {
        return billingState;
    }

    public void setBillingState(Integer billingState) {
        this.billingState = billingState;
    }

    public Date getBillingTime() {
        return billingTime;
    }

    public void setBillingTime(Date billingTime) {
        this.billingTime = billingTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBillingCode() {
        return billingCode;
    }

    public void setBillingCode(String billingCode) {
        this.billingCode = billingCode;
    }

    public Integer getBillingEnum() {
        return billingEnum;
    }

    public void setBillingEnum(Integer billingEnum) {
        this.billingEnum = billingEnum;
    }

    public Integer getBillingItem() {
        return billingItem;
    }

    public void setBillingItem(Integer billingItem) {
        this.billingItem = billingItem;
    }

    public Integer getBillingType() {
        return billingType;
    }

    public void setBillingType(Integer billingType) {
        this.billingType = billingType;
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

    public String getRecipientsName() {
        return recipientsName;
    }

    public void setRecipientsName(String recipientsName) {
        this.recipientsName = recipientsName;
    }

    public String getRecipientsPhone() {
        return recipientsPhone;
    }

    public void setRecipientsPhone(String recipientsPhone) {
        this.recipientsPhone = recipientsPhone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getWoundedName() {
        return woundedName;
    }

    public void setWoundedName(String woundedName) {
        this.woundedName = woundedName;
    }

    public String getInsurerCompanyName() {
        return insurerCompanyName;
    }

    public void setInsurerCompanyName(String insurerCompanyName) {
        this.insurerCompanyName = insurerCompanyName;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Long getBillingById() {
        return billingById;
    }

    public void setBillingById(Long billingById) {
        this.billingById = billingById;
    }

    public String getBillingBy() {
        return billingBy;
    }

    public void setBillingBy(String billingBy) {
        this.billingBy = billingBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(Long updateById) {
        this.updateById = updateById;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getOperReason() {
        return operReason;
    }

    public void setOperReason(String operReason) {
        this.operReason = operReason;
    }

    public Integer getIsPreSign() {
        return isPreSign;
    }

    public void setIsPreSign(Integer isPreSign) {
        this.isPreSign = isPreSign;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getMeritName() {
        return meritName;
    }

    public void setMeritName(String meritName) {
        this.meritName = meritName;
    }

    public Double getConfirmAccountMoney() {
        return confirmAccountMoney;
    }

    public void setConfirmAccountMoney(Double confirmAccountMoney) {
        this.confirmAccountMoney = confirmAccountMoney;
    }

    public Integer getConfirmAccountState() {
        return confirmAccountState;
    }

    public void setConfirmAccountState(Integer confirmAccountState) {
        this.confirmAccountState = confirmAccountState;
    }

    public Date getConfirmAccountTime() {
        return confirmAccountTime;
    }

    public void setConfirmAccountTime(Date confirmAccountTime) {
        this.confirmAccountTime = confirmAccountTime;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getIsMerge() {
        return isMerge;
    }

    public void setIsMerge(Integer isMerge) {
        this.isMerge = isMerge;
    }

    public Boolean getIsRed() {
        return isRed;
    }

    public void setIsRed(Boolean isRed) {
        this.isRed = isRed;
    }

    public Long getSurveyDepartmentId() {
        return surveyDepartmentId;
    }

    public void setSurveyDepartmentId(Long surveyDepartmentId) {
        this.surveyDepartmentId = surveyDepartmentId;
    }

    public String getSurveyDepartmentName() {
        return surveyDepartmentName;
    }

    public void setSurveyDepartmentName(String surveyDepartmentName) {
        this.surveyDepartmentName = surveyDepartmentName;
    }

    public Long getSurveyConsignorId() {
        return surveyConsignorId;
    }

    public void setSurveyConsignorId(Long surveyConsignorId) {
        this.surveyConsignorId = surveyConsignorId;
    }

    public String getSurveyConsignorName() {
        return surveyConsignorName;
    }

    public void setSurveyConsignorName(String surveyConsignorName) {
        this.surveyConsignorName = surveyConsignorName;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getBillingSource() {
        return billingSource;
    }

    public void setBillingSource(Integer billingSource) {
        this.billingSource = billingSource;
    }

    public Long getSurveyBillSubjectId() {
        return surveyBillSubjectId;
    }

    public void setSurveyBillSubjectId(Long surveyBillSubjectId) {
        this.surveyBillSubjectId = surveyBillSubjectId;
    }

    public String getSurveyBillSubjectName() {
        return surveyBillSubjectName;
    }

    public void setSurveyBillSubjectName(String surveyBillSubjectName) {
        this.surveyBillSubjectName = surveyBillSubjectName;
    }

    public Boolean getRed() {
        return isRed;
    }

    public void setRed(Boolean red) {
        isRed = red;
    }

    public Long getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Long surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
    }

    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
    }

    public Double getOkBillMoney() {
        return okBillMoney;
    }

    public void setOkBillMoney(Double okBillMoney) {
        this.okBillMoney = okBillMoney;
    }

    public Double getNoBillMoney() {
        return noBillMoney;
    }

    public void setNoBillMoney(Double noBillMoney) {
        this.noBillMoney = noBillMoney;
    }

    public Double getOkAccountMoney() {
        return okAccountMoney;
    }

    public void setOkAccountMoney(Double okAccountMoney) {
        this.okAccountMoney = okAccountMoney;
    }

    public Double getNoAccountMoney() {
        return noAccountMoney;
    }

    public void setNoAccountMoney(Double noAccountMoney) {
        this.noAccountMoney = noAccountMoney;
    }

    public List<BillingApplyAccounts> getBillingApplyAccountsList() {
        return billingApplyAccountsList;
    }

    public void setBillingApplyAccountsList(List<BillingApplyAccounts> billingApplyAccountsList) {
        this.billingApplyAccountsList = billingApplyAccountsList;
    }

    public Long getMeritUserId() {
        return meritUserId;
    }

    public void setMeritUserId(Long meritUserId) {
        this.meritUserId = meritUserId;
    }
}