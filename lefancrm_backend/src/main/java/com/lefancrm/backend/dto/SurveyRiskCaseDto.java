package com.lefancrm.backend.dto;

import java.util.Date;

public class SurveyRiskCaseDto {
    private Long id;

    private String surveyNo;

    private String surveyPerson;

    private String surveryPersonTel;

    private Integer sex;

    private String policyNo;

    private String claimsNo;

    private Double claimsMoney;

    private Integer idType;

    private String idNumber;

    private Double surveyTotalMoney;

    private Double entrustTotalMoney;

    private Long entrustUserId;

    private String entrustUserName;

    private Long entrustOrgId;

    private String entrustOrgName;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private String surveyCaseNo;

    private Integer age;

    private Integer insureType;

    private Date entrustTime;

    private Date insureTime;

    private Date dangerTime;

    private String dangerAddress;

    private Date insureTakeTime;

    private Long modelId;//模板id

    private Long departmentId;

    private String departmentName;

    private Integer transferType;

    private String transferTypeName;

    private Long topSurveyId;//第一级：“发起二调”的顶级 调查id

    private Integer nextTransferType;//下一个“发起二调”的类别

    private String nextTransferName; //下一个“发起二调”的类别

    private Boolean isShowTransfer = true; //是否展示“二调按钮”

    private String insureName; //保险种类名称

    private String hzContactName;

    private String hzContactTel;

    private Integer hzProduct;

    private Date hzWaitEndTime;

    private String hzLiveAddress;

    private String hzConfirmDisease;

    private String hzFollowInfo;

    private Integer investigationArea;

    private Boolean repetition;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    public Double getClaimsMoney() {
        return claimsMoney;
    }

    public void setClaimsMoney(Double claimsMoney) {
        this.claimsMoney = claimsMoney;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Double getSurveyTotalMoney() {
        return surveyTotalMoney;
    }

    public void setSurveyTotalMoney(Double surveyTotalMoney) {
        this.surveyTotalMoney = surveyTotalMoney;
    }

    public Double getEntrustTotalMoney() {
        return entrustTotalMoney;
    }

    public void setEntrustTotalMoney(Double entrustTotalMoney) {
        this.entrustTotalMoney = entrustTotalMoney;
    }

    public Long getEntrustUserId() {
        return entrustUserId;
    }

    public void setEntrustUserId(Long entrustUserId) {
        this.entrustUserId = entrustUserId;
    }

    public String getEntrustUserName() {
        return entrustUserName;
    }

    public void setEntrustUserName(String entrustUserName) {
        this.entrustUserName = entrustUserName;
    }

    public Long getEntrustOrgId() {
        return entrustOrgId;
    }

    public void setEntrustOrgId(Long entrustOrgId) {
        this.entrustOrgId = entrustOrgId;
    }

    public String getEntrustOrgName() {
        return entrustOrgName;
    }

    public void setEntrustOrgName(String entrustOrgName) {
        this.entrustOrgName = entrustOrgName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
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

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getInsureType() {
        return insureType;
    }

    public void setInsureType(Integer insureType) {
        this.insureType = insureType;
    }

    public Date getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(Date entrustTime) {
        this.entrustTime = entrustTime;
    }

    public Date getInsureTime() {
        return insureTime;
    }

    public void setInsureTime(Date insureTime) {
        this.insureTime = insureTime;
    }

    public Date getDangerTime() {
        return dangerTime;
    }

    public void setDangerTime(Date dangerTime) {
        this.dangerTime = dangerTime;
    }

    public String getDangerAddress() {
        return dangerAddress;
    }

    public void setDangerAddress(String dangerAddress) {
        this.dangerAddress = dangerAddress;
    }

    public Date getInsureTakeTime() {
        return insureTakeTime;
    }

    public void setInsureTakeTime(Date insureTakeTime) {
        this.insureTakeTime = insureTakeTime;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
    }

    public String getTransferTypeName() {
        return transferTypeName;
    }

    public void setTransferTypeName(String transferTypeName) {
        this.transferTypeName = transferTypeName;
    }

    public Long getTopSurveyId() {
        return topSurveyId;
    }

    public void setTopSurveyId(Long topSurveyId) {
        this.topSurveyId = topSurveyId;
    }

    public Integer getNextTransferType() {
        return nextTransferType;
    }

    public void setNextTransferType(Integer nextTransferType) {
        this.nextTransferType = nextTransferType;
    }

    public String getNextTransferName() {
        return nextTransferName;
    }

    public void setNextTransferName(String nextTransferName) {
        this.nextTransferName = nextTransferName;
    }

    public Boolean getIsShowTransfer() {
        return isShowTransfer;
    }

    public void setIsShowTransfer(Boolean isShowTransfer) {
        this.isShowTransfer = isShowTransfer;
    }

    public String getInsureName() {
        return insureName;
    }

    public void setInsureName(String insureName) {
        this.insureName = insureName;
    }

    public String getHzContactName() {
        return hzContactName;
    }

    public void setHzContactName(String hzContactName) {
        this.hzContactName = hzContactName;
    }

    public String getHzContactTel() {
        return hzContactTel;
    }

    public void setHzContactTel(String hzContactTel) {
        this.hzContactTel = hzContactTel;
    }

    public Integer getHzProduct() {
        return hzProduct;
    }

    public void setHzProduct(Integer hzProduct) {
        this.hzProduct = hzProduct;
    }

    public Date getHzWaitEndTime() {
        return hzWaitEndTime;
    }

    public void setHzWaitEndTime(Date hzWaitEndTime) {
        this.hzWaitEndTime = hzWaitEndTime;
    }

    public String getHzLiveAddress() {
        return hzLiveAddress;
    }

    public void setHzLiveAddress(String hzLiveAddress) {
        this.hzLiveAddress = hzLiveAddress;
    }

    public String getHzConfirmDisease() {
        return hzConfirmDisease;
    }

    public void setHzConfirmDisease(String hzConfirmDisease) {
        this.hzConfirmDisease = hzConfirmDisease;
    }

    public String getHzFollowInfo() {
        return hzFollowInfo;
    }

    public void setHzFollowInfo(String hzFollowInfo) {
        this.hzFollowInfo = hzFollowInfo;
    }

    public Integer getInvestigationArea() {
        return investigationArea;
    }

    public void setInvestigationArea(Integer investigationArea) {
        this.investigationArea = investigationArea;
    }

    public Boolean getRepetition() {
        return repetition;
    }

    public void setRepetition(Boolean repetition) {
        this.repetition = repetition;
    }

    public String getCooperativeCompany() {
        return cooperativeCompany;
    }

    public void setCooperativeCompany(String cooperativeCompany) {
        this.cooperativeCompany = cooperativeCompany;
    }

    public Integer getDelegationMode() {
        return delegationMode;
    }

    public void setDelegationMode(Integer delegationMode) {
        this.delegationMode = delegationMode;
    }



      //    主险代码
    private String mainInsurance;

    public String getMainInsurance() {
        return mainInsurance;
    }
    public void setMainInsurance(String mainInsurance) {
        this.mainInsurance = mainInsurance;
    }
}