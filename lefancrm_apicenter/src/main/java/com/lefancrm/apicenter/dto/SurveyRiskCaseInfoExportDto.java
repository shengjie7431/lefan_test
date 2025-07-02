package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyCaseDirection;

import java.util.Date;
import java.util.List;

/**
 * Created by wangwei  on 2019-03-21.
 */
public class SurveyRiskCaseInfoExportDto {

    //对象SurveyRiskCaseInfo
    private Long caseInfoId;
    private Double billingMoney;
    private Double confirmAccountMoney;
    private String surveyBusName;
    private String surveyStateName;
    private Date createTime;
    private Date entrustReportStartDate;
    private Date entrustReportEndDate;
    private Integer isSun;
    private Date closeEndDate;
    private String servicesName;
    private Double entrustOkPrice1;

    //对象SurveyRiskCase
    private String surveyNo;
    private String surveyPerson;
    private String surveryPersonTel;
    private Double claimsMoney;
    private String entrustUserName;
    private String entrustOrgName;
    private Long entrustOrgId;
    private String policyNo;
    private String claimsNo;
    private Date entrustTime;
    private String departmentName;
    private Long departmentId;
    private String surveyCaseNo;
    private Long servicesId;
    /**
     * 合作公司
     */
    private String cooperativeCompany;

    /**
     * 分公司
     */
    private String subsidiaryCompany;

    /**
     * 健康险公司
     */
    private String healthInsuranceCompany;

    /**
     * 任务号
     */
    private String taskNumber;

    /**
     * 委托方式 1:系统委托 2:邮件提调
     * 枚举类：SurveyRiskCaseDelegationModeEnum
     */
    private Integer delegationMode;

    private Integer transferType;
    private String transferTypeName;


    //对象SurveyCaseDirection
    private List<SurveyCaseDirection> surveyCaseDirections;
    private String directionName;
    private Double entrustMoney;
    private String taskName;
    private String newName;
    private Double score;

    //对象SurveyInvestigatorCase
    private List<SurveyInvestigatorCaseDto> surveyInvestigatorCases;
    private Date assignDate;    //分派时间
    private Long surveyUserId;  //调查员
    private String surveyUserName;
    private Long surveyOrgId;  //调查机构
    private String surveyOrgName;
    private Integer returnState;//是否退回

    private Integer efficiency;//调查时效
    private Double scoreSum;//总分值
    private Double entrustMoneySum;//总金额

    private Date endTime;//截止时间
    private Boolean isOverTime = false;//是否超时效
    private Long investigatorCaseId;  //调查员案件Id
    private List<SurveyAssignOrgDto> surveyAssignOrgs;

    private Integer consignorOrgAttr = 1;//委托机构：公司属性（1：保险公司；2、互助机构）
    private Integer consignorEfficiencyAttr = 1;//委托机构：时效设置（1：工作日；2、自然日）
    private Integer consignorModelId = 1;//委托机构：模板id()

    private String directionNameStrs; //互助案件调查方向拼接 （场景：分表导出）
    private String entrustMoneyCount; //互助案件总收费金额 （场景：分表导出）

    private String remarkAll;//延期审核记录的 所有备注
    private Integer areaType;//区域类别
    private Integer agingDay;//案件考核时效
    private Integer overTimeDay;//超期天数

    private Double surveyMoneySubmit;// 机构案件：确认调查费
    private Double surveryReLossesSubmit;//机构案件：确认减损价格

    private Long surveyAssignCaseId;
    private String provinceStr;//省份集合

    private String surveyItem;

    private String idCard;


    private String hzContactName;

    private String investigationArea;

    private String hzContactTel;

    private String insureName;

    private String mainInsurance;

    public String getTransferTypeName() {
        return transferTypeName;
    }

    public void setTransferTypeName(String transferTypeName) {
        this.transferTypeName = transferTypeName;
    }

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
    }

    public Integer getDelegationMode() {
        return delegationMode;
    }

    public void setDelegationMode(Integer delegationMode) {
        this.delegationMode = delegationMode;
    }

    public String getCooperativeCompany() {
        return cooperativeCompany;
    }

    public void setCooperativeCompany(String cooperativeCompany) {
        this.cooperativeCompany = cooperativeCompany;
    }

    public String getSubsidiaryCompany() {
        return subsidiaryCompany;
    }

    public void setSubsidiaryCompany(String subsidiaryCompany) {
        this.subsidiaryCompany = subsidiaryCompany;
    }

    public String getHealthInsuranceCompany() {
        return healthInsuranceCompany;
    }

    public void setHealthInsuranceCompany(String healthInsuranceCompany) {
        this.healthInsuranceCompany = healthInsuranceCompany;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Long getCaseInfoId() {
        return caseInfoId;
    }

    public void setCaseInfoId(Long caseInfoId) {
        this.caseInfoId = caseInfoId;
    }

    public Double getBillingMoney() {
        return billingMoney;
    }

    public void setBillingMoney(Double billingMoney) {
        this.billingMoney = billingMoney;
    }

    public Double getConfirmAccountMoney() {
        return confirmAccountMoney;
    }

    public void setConfirmAccountMoney(Double confirmAccountMoney) {
        this.confirmAccountMoney = confirmAccountMoney;
    }

    public String getSurveyBusName() {
        return surveyBusName;
    }

    public void setSurveyBusName(String surveyBusName) {
        this.surveyBusName = surveyBusName;
    }

    public String getSurveyStateName() {
        return surveyStateName;
    }

    public void setSurveyStateName(String surveyStateName) {
        this.surveyStateName = surveyStateName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEntrustReportStartDate() {
        return entrustReportStartDate;
    }

    public void setEntrustReportStartDate(Date entrustReportStartDate) {
        this.entrustReportStartDate = entrustReportStartDate;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public String getSurveryPersonTel() {
        return surveryPersonTel;
    }

    public void setSurveryPersonTel(String surveryPersonTel) {
        this.surveryPersonTel = surveryPersonTel;
    }

    public Double getClaimsMoney() {
        return claimsMoney;
    }

    public void setClaimsMoney(Double claimsMoney) {
        this.claimsMoney = claimsMoney;
    }

    public String getEntrustUserName() {
        return entrustUserName;
    }

    public void setEntrustUserName(String entrustUserName) {
        this.entrustUserName = entrustUserName;
    }

    public String getEntrustOrgName() {
        return entrustOrgName;
    }

    public void setEntrustOrgName(String entrustOrgName) {
        this.entrustOrgName = entrustOrgName;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getClaimsNo() {
        return claimsNo;
    }

    public void setClaimsNo(String claimsNo) {
        this.claimsNo = claimsNo;
    }

    public Integer getIsSun() {
        return isSun;
    }

    public void setIsSun(Integer isSun) {
        this.isSun = isSun;
    }

    public Long getEntrustOrgId() {
        return entrustOrgId;
    }

    public void setEntrustOrgId(Long entrustOrgId) {
        this.entrustOrgId = entrustOrgId;
    }

    public Date getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(Date entrustTime) {
        this.entrustTime = entrustTime;
    }

    public List<SurveyCaseDirection> getSurveyCaseDirections() {
        return surveyCaseDirections;
    }

    public void setSurveyCaseDirections(List<SurveyCaseDirection> surveyCaseDirections) {
        this.surveyCaseDirections = surveyCaseDirections;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public Double getEntrustMoney() {
        return entrustMoney;
    }

    public void setEntrustMoney(Double entrustMoney) {
        this.entrustMoney = entrustMoney;
    }

    public Date getCloseEndDate() {
        return closeEndDate;
    }

    public void setCloseEndDate(Date closeEndDate) {
        this.closeEndDate = closeEndDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Long getSurveyUserId() {
        return surveyUserId;
    }

    public void setSurveyUserId(Long surveyUserId) {
        this.surveyUserId = surveyUserId;
    }

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
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

    public Integer getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Integer efficiency) {
        this.efficiency = efficiency;
    }

    public List<SurveyInvestigatorCaseDto> getSurveyInvestigatorCases() {
        return surveyInvestigatorCases;
    }

    public void setSurveyInvestigatorCases(List<SurveyInvestigatorCaseDto> surveyInvestigatorCases) {
        this.surveyInvestigatorCases = surveyInvestigatorCases;
    }

    public Double getScoreSum() {
        return scoreSum;
    }

    public void setScoreSum(Double scoreSum) {
        this.scoreSum = scoreSum;
    }

    public Double getEntrustMoneySum() {
        return entrustMoneySum;
    }

    public void setEntrustMoneySum(Double entrustMoneySum) {
        this.entrustMoneySum = entrustMoneySum;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getIsOverTime() {
        return isOverTime;
    }

    public void setIsOverTime(Boolean isOverTime) {
        this.isOverTime = isOverTime;
    }

    public Integer getReturnState() {
        return returnState;
    }

    public void setReturnState(Integer returnState) {
        this.returnState = returnState;
    }

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }

    public Long getInvestigatorCaseId() {
        return investigatorCaseId;
    }

    public void setInvestigatorCaseId(Long investigatorCaseId) {
        this.investigatorCaseId = investigatorCaseId;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public List<SurveyAssignOrgDto> getSurveyAssignOrgs() {
        return surveyAssignOrgs;
    }

    public void setSurveyAssignOrgs(List<SurveyAssignOrgDto> surveyAssignOrgs) {
        this.surveyAssignOrgs = surveyAssignOrgs;
    }

    public Integer getConsignorOrgAttr() {
        return consignorOrgAttr;
    }

    public void setConsignorOrgAttr(Integer consignorOrgAttr) {
        this.consignorOrgAttr = consignorOrgAttr;
    }

    public Integer getConsignorEfficiencyAttr() {
        return consignorEfficiencyAttr;
    }

    public void setConsignorEfficiencyAttr(Integer consignorEfficiencyAttr) {
        this.consignorEfficiencyAttr = consignorEfficiencyAttr;
    }

    public String getDirectionNameStrs() {
        return directionNameStrs;
    }

    public void setDirectionNameStrs(String directionNameStrs) {
        this.directionNameStrs = directionNameStrs;
    }

    public String getEntrustMoneyCount() {
        return entrustMoneyCount;
    }

    public void setEntrustMoneyCount(String entrustMoneyCount) {
        this.entrustMoneyCount = entrustMoneyCount;
    }

    public Date getEntrustReportEndDate() {
        return entrustReportEndDate;
    }

    public void setEntrustReportEndDate(Date entrustReportEndDate) {
        this.entrustReportEndDate = entrustReportEndDate;
    }

    public Double getEntrustOkPrice1() {
        return entrustOkPrice1;
    }

    public void setEntrustOkPrice1(Double entrustOkPrice1) {
        this.entrustOkPrice1 = entrustOkPrice1;
    }

    public String getRemarkAll() {
        return remarkAll;
    }

    public void setRemarkAll(String remarkAll) {
        this.remarkAll = remarkAll;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public Integer getAgingDay() {
        return agingDay;
    }

    public void setAgingDay(Integer agingDay) {
        this.agingDay = agingDay;
    }

    public Integer getOverTimeDay() {
        return overTimeDay;
    }

    public void setOverTimeDay(Integer overTimeDay) {
        this.overTimeDay = overTimeDay;
    }

    public Long getServicesId() {
        return servicesId;
    }

    public void setServicesId(Long servicesId) {
        this.servicesId = servicesId;
    }

    public Double getSurveyMoneySubmit() {
        return surveyMoneySubmit;
    }

    public void setSurveyMoneySubmit(Double surveyMoneySubmit) {
        this.surveyMoneySubmit = surveyMoneySubmit;
    }

    public Double getSurveryReLossesSubmit() {
        return surveryReLossesSubmit;
    }

    public void setSurveryReLossesSubmit(Double surveryReLossesSubmit) {
        this.surveryReLossesSubmit = surveryReLossesSubmit;
    }

    public Long getSurveyAssignCaseId() {
        return surveyAssignCaseId;
    }

    public void setSurveyAssignCaseId(Long surveyAssignCaseId) {
        this.surveyAssignCaseId = surveyAssignCaseId;
    }

    public Integer getConsignorModelId() {
        return consignorModelId;
    }

    public void setConsignorModelId(Integer consignorModelId) {
        this.consignorModelId = consignorModelId;
    }

    public String getProvinceStr() {
        return provinceStr;
    }

    public void setProvinceStr(String provinceStr) {
        this.provinceStr = provinceStr;
    }

    public String getSurveyItem() {
        return surveyItem;
    }

    public void setSurveyItem(String surveyItem) {
        this.surveyItem = surveyItem;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getHzContactName() {
        return hzContactName;
    }

    public void setHzContactName(String hzContactName) {
        this.hzContactName = hzContactName;
    }

    public String getInvestigationArea() {
        return investigationArea;
    }

    public void setInvestigationArea(String investigationArea) {
        this.investigationArea = investigationArea;
    }

    public String getHzContactTel() {
        return hzContactTel;
    }

    public void setHzContactTel(String hzContactTel) {
        this.hzContactTel = hzContactTel;
    }

    public String getInsureName() {
        return insureName;
    }

    public void setInsureName(String insureName) {
        this.insureName = insureName;
    }

    public String getMainInsurance() {
        return mainInsurance;
    }

    public void setMainInsurance(String mainInsurance) {
        this.mainInsurance = mainInsurance;
    }
}
