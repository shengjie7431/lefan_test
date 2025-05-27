package com.lefancrm.apicenter.dto.hzReport;

import java.util.Date;
import java.util.List;

/**
 * IncomeAndCostDto 收入与成本
 */
public class IncomeAndCostDto {
    private Long parentId;
    private String surveyOrgName;//调查机构
    private Long surveyOrgId;//调查机构id
    private Double platformApplicationIncome;//平台申请收入
    private Double huanbiplatformApplicationIncome;//环比平台收入
    private Double platformApplicationIncomeRate;//平台申请收入环比

    private Date insuranceCompanyConfirmDate;//保司审核通过时间
    private Double insuranceCompanyConfirmsRevenue;//保司确认收入
    private Double huanbiinsuranceCompanyConfirmsRevenue;//保司确认收入

    private Double insuranceCompanyConfirmsRevenueRate;//保司确认收入环比
    private Double cityTransportationSubsidies;//市内交通补贴
    private Double medicalHistoryMoney;//病史费
    private Double troubleshootingMoney;//排查费用
    private Double opcTroubleshootingMoney;//门诊排查费用
    private Double printingMoney;//体检报告打印费
    private Double crossCityTransportationFees;//跨地市交通费
    private Double otherFee;//其他费用
    private Double accommodatioMoney;//住宿费
    private Integer orgType;//机构类型：1、A类 2、B类
    private String orgTypeName;//机构类型名称：1、A类 2、B类
    private Double totalMoney;
    private Integer type;
    private String groupId;
    private Double surveyCompanyConfirmsRevenue;
    private Double surveyCompanyConfirmsRevenueRate;

    public Double getSurveyCompanyConfirmsRevenue() {
        return surveyCompanyConfirmsRevenue;
    }

    public void setSurveyCompanyConfirmsRevenue(Double surveyCompanyConfirmsRevenue) {
        this.surveyCompanyConfirmsRevenue = surveyCompanyConfirmsRevenue;
    }

    public Double getSurveyCompanyConfirmsRevenueRate() {
        return surveyCompanyConfirmsRevenueRate;
    }

    public void setSurveyCompanyConfirmsRevenueRate(Double surveyCompanyConfirmsRevenueRate) {
        this.surveyCompanyConfirmsRevenueRate = surveyCompanyConfirmsRevenueRate;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Double getHuanbiplatformApplicationIncome() {
        return huanbiplatformApplicationIncome;
    }

    public void setHuanbiplatformApplicationIncome(Double huanbiplatformApplicationIncome) {
        this.huanbiplatformApplicationIncome = huanbiplatformApplicationIncome;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    private List<IncomeAndCostDto> children;
    private String idStr;


    public String getOrgTypeName() {
        return orgTypeName;
    }

    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public void setAccommodatioMoney(Double accommodatioMoney) {
        this.accommodatioMoney = accommodatioMoney;
    }

    public Double getAccommodatioMoney() {
        return accommodatioMoney;
    }

    public void setChildren(List<IncomeAndCostDto> children) {
        this.children = children;
    }

    public List<IncomeAndCostDto> getChildren() {
        return children;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
    }

    public Long getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Long surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
    }

    public Double getPlatformApplicationIncome() {
        return platformApplicationIncome;
    }

    public void setPlatformApplicationIncome(Double platformApplicationIncome) {
        this.platformApplicationIncome = platformApplicationIncome;
    }

    public Double getPlatformApplicationIncomeRate() {
        return platformApplicationIncomeRate;
    }

    public void setPlatformApplicationIncomeRate(Double platformApplicationIncomeRate) {
        this.platformApplicationIncomeRate = platformApplicationIncomeRate;
    }

    public Double getInsuranceCompanyConfirmsRevenue() {
        return insuranceCompanyConfirmsRevenue;
    }

    public void setInsuranceCompanyConfirmsRevenue(Double insuranceCompanyConfirmsRevenue) {
        this.insuranceCompanyConfirmsRevenue = insuranceCompanyConfirmsRevenue;
    }

    public Double getInsuranceCompanyConfirmsRevenueRate() {
        return insuranceCompanyConfirmsRevenueRate;
    }

    public void setInsuranceCompanyConfirmsRevenueRate(Double insuranceCompanyConfirmsRevenueRate) {
        this.insuranceCompanyConfirmsRevenueRate = insuranceCompanyConfirmsRevenueRate;
    }

    public Double getCityTransportationSubsidies() {
        return cityTransportationSubsidies;
    }

    public void setCityTransportationSubsidies(Double cityTransportationSubsidies) {
        this.cityTransportationSubsidies = cityTransportationSubsidies;
    }

    public Double getMedicalHistoryMoney() {
        return medicalHistoryMoney;
    }

    public void setMedicalHistoryMoney(Double medicalHistoryMoney) {
        this.medicalHistoryMoney = medicalHistoryMoney;
    }

    public Double getTroubleshootingMoney() {
        return troubleshootingMoney;
    }

    public void setTroubleshootingMoney(Double troubleshootingMoney) {
        this.troubleshootingMoney = troubleshootingMoney;
    }

    public Double getPrintingMoney() {
        return printingMoney;
    }

    public void setPrintingMoney(Double printingMoney) {
        this.printingMoney = printingMoney;
    }

    public Double getCrossCityTransportationFees() {
        return crossCityTransportationFees;
    }

    public void setCrossCityTransportationFees(Double crossCityTransportationFees) {
        this.crossCityTransportationFees = crossCityTransportationFees;
    }

    public Double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(Double otherFee) {
        this.otherFee = otherFee;
    }

    public Double getHuanbiinsuranceCompanyConfirmsRevenue() {
        return huanbiinsuranceCompanyConfirmsRevenue;
    }

    public void setHuanbiinsuranceCompanyConfirmsRevenue(Double huanbiinsuranceCompanyConfirmsRevenue) {
        this.huanbiinsuranceCompanyConfirmsRevenue = huanbiinsuranceCompanyConfirmsRevenue;
    }

    public Date getInsuranceCompanyConfirmDate() {
        return insuranceCompanyConfirmDate;
    }

    public void setInsuranceCompanyConfirmDate(Date insuranceCompanyConfirmDate) {
        this.insuranceCompanyConfirmDate = insuranceCompanyConfirmDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getOpcTroubleshootingMoney() {
        return opcTroubleshootingMoney;
    }

    public void setOpcTroubleshootingMoney(Double opcTroubleshootingMoney) {
        this.opcTroubleshootingMoney = opcTroubleshootingMoney;
    }
}
