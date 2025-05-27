package com.lefancrm.backend.dto.staff;

import java.util.Date;

public class StaffPayInfoDTO {
    private String workTime;

    private Long userId;

    private String realName;

    private String userTel;

    private String jobNo;

    private String idCard;

    private Long businessUnitId;

    private String businessUnit;

    private Long companyId;

    private String company;

    private Long organId;

    private String organ;

    private Long departmentId;

    private String department;

    private Long jobPostId;

    private String jobPost;

    private Double basePay;

    private Double fixedPerfPay;

    private Double assesPerfPay;

    private Double managePerfPay;

    private Double travelAllowancePay;

    private Double socialSecurityPay;

    private Double fundPay;

    private String payAddress;

    private Integer staffState;

    private Integer relation;

    private Date quitTime;

    private Date entryTime;

    private Double rate;

    //员工数据，工资条明细数据
    private Long itemId;

    private Long staffPaySlipId;

    private Long staffPersonnelId;

    private Integer lateEarlyNum;

    private Double lateEarlyMoney;

    private Integer absenteeismNum;

    private Double absenteeismMoney;

    private Integer leaveNum;

    private Double leaveMoney;

    private Double sickLeaveTime;

    private Double sickLeaveMoney;

    private Double conpanyFundMoney;

    private Double personalFundMoney;

    private Double companyPensionBenefits;

    private Double personalPensionBenefits;

    private Double companyMedicalInsurance;

    private Double personalMedicalInsurance;

    private Double companyUnemploymentInsurance;

    private Double personalUnemploymentInsurance;

    private Double companyBirthInsurance;

    private Double companyInjuryInsurance;

    private Double overtimePay;

    private Double otherPay;

    private Double individualTax;

    private Double realWages;

    private String remarks;

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

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Long getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(Long businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getOrganId() {
        return organId;
    }

    public void setOrganId(Long organId) {
        this.organId = organId;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Long jobPostId) {
        this.jobPostId = jobPostId;
    }

    public String getJobPost() {
        return jobPost;
    }

    public void setJobPost(String jobPost) {
        this.jobPost = jobPost;
    }

    public Double getBasePay() {
        return basePay;
    }

    public void setBasePay(Double basePay) {
        this.basePay = basePay;
    }

    public Double getFixedPerfPay() {
        return fixedPerfPay;
    }

    public void setFixedPerfPay(Double fixedPerfPay) {
        this.fixedPerfPay = fixedPerfPay;
    }

    public Double getAssesPerfPay() {
        return assesPerfPay;
    }

    public void setAssesPerfPay(Double assesPerfPay) {
        this.assesPerfPay = assesPerfPay;
    }

    public Double getManagePerfPay() {
        return managePerfPay;
    }

    public void setManagePerfPay(Double managePerfPay) {
        this.managePerfPay = managePerfPay;
    }

    public Double getTravelAllowancePay() {
        return travelAllowancePay;
    }

    public void setTravelAllowancePay(Double travelAllowancePay) {
        this.travelAllowancePay = travelAllowancePay;
    }

    public Double getSocialSecurityPay() {
        return socialSecurityPay;
    }

    public void setSocialSecurityPay(Double socialSecurityPay) {
        this.socialSecurityPay = socialSecurityPay;
    }

    public Double getFundPay() {
        return fundPay;
    }

    public void setFundPay(Double fundPay) {
        this.fundPay = fundPay;
    }

    public String getPayAddress() {
        return payAddress;
    }

    public void setPayAddress(String payAddress) {
        this.payAddress = payAddress;
    }

    public Integer getStaffState() {
        return staffState;
    }

    public void setStaffState(Integer staffState) {
        this.staffState = staffState;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Double getRate() {
        return rate;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getStaffPaySlipId() {
        return staffPaySlipId;
    }

    public void setStaffPaySlipId(Long staffPaySlipId) {
        this.staffPaySlipId = staffPaySlipId;
    }

    public Long getStaffPersonnelId() {
        return staffPersonnelId;
    }

    public void setStaffPersonnelId(Long staffPersonnelId) {
        this.staffPersonnelId = staffPersonnelId;
    }

    public Integer getLateEarlyNum() {
        return lateEarlyNum;
    }

    public void setLateEarlyNum(Integer lateEarlyNum) {
        this.lateEarlyNum = lateEarlyNum;
    }

    public Double getLateEarlyMoney() {
        return lateEarlyMoney;
    }

    public void setLateEarlyMoney(Double lateEarlyMoney) {
        this.lateEarlyMoney = lateEarlyMoney;
    }

    public Integer getAbsenteeismNum() {
        return absenteeismNum;
    }

    public void setAbsenteeismNum(Integer absenteeismNum) {
        this.absenteeismNum = absenteeismNum;
    }

    public Double getAbsenteeismMoney() {
        return absenteeismMoney;
    }

    public void setAbsenteeismMoney(Double absenteeismMoney) {
        this.absenteeismMoney = absenteeismMoney;
    }

    public Integer getLeaveNum() {
        return leaveNum;
    }

    public void setLeaveNum(Integer leaveNum) {
        this.leaveNum = leaveNum;
    }

    public Double getLeaveMoney() {
        return leaveMoney;
    }

    public void setLeaveMoney(Double leaveMoney) {
        this.leaveMoney = leaveMoney;
    }

    public Double getSickLeaveTime() {
        return sickLeaveTime;
    }

    public void setSickLeaveTime(Double sickLeaveTime) {
        this.sickLeaveTime = sickLeaveTime;
    }

    public Double getSickLeaveMoney() {
        return sickLeaveMoney;
    }

    public void setSickLeaveMoney(Double sickLeaveMoney) {
        this.sickLeaveMoney = sickLeaveMoney;
    }

    public Double getConpanyFundMoney() {
        return conpanyFundMoney;
    }

    public void setConpanyFundMoney(Double conpanyFundMoney) {
        this.conpanyFundMoney = conpanyFundMoney;
    }

    public Double getPersonalFundMoney() {
        return personalFundMoney;
    }

    public void setPersonalFundMoney(Double personalFundMoney) {
        this.personalFundMoney = personalFundMoney;
    }

    public Double getCompanyPensionBenefits() {
        return companyPensionBenefits;
    }

    public void setCompanyPensionBenefits(Double companyPensionBenefits) {
        this.companyPensionBenefits = companyPensionBenefits;
    }

    public Double getPersonalPensionBenefits() {
        return personalPensionBenefits;
    }

    public void setPersonalPensionBenefits(Double personalPensionBenefits) {
        this.personalPensionBenefits = personalPensionBenefits;
    }

    public Double getCompanyMedicalInsurance() {
        return companyMedicalInsurance;
    }

    public void setCompanyMedicalInsurance(Double companyMedicalInsurance) {
        this.companyMedicalInsurance = companyMedicalInsurance;
    }

    public Double getPersonalMedicalInsurance() {
        return personalMedicalInsurance;
    }

    public void setPersonalMedicalInsurance(Double personalMedicalInsurance) {
        this.personalMedicalInsurance = personalMedicalInsurance;
    }

    public Double getCompanyUnemploymentInsurance() {
        return companyUnemploymentInsurance;
    }

    public void setCompanyUnemploymentInsurance(Double companyUnemploymentInsurance) {
        this.companyUnemploymentInsurance = companyUnemploymentInsurance;
    }

    public Double getPersonalUnemploymentInsurance() {
        return personalUnemploymentInsurance;
    }

    public void setPersonalUnemploymentInsurance(Double personalUnemploymentInsurance) {
        this.personalUnemploymentInsurance = personalUnemploymentInsurance;
    }

    public Double getCompanyBirthInsurance() {
        return companyBirthInsurance;
    }

    public void setCompanyBirthInsurance(Double companyBirthInsurance) {
        this.companyBirthInsurance = companyBirthInsurance;
    }

    public Double getCompanyInjuryInsurance() {
        return companyInjuryInsurance;
    }

    public void setCompanyInjuryInsurance(Double companyInjuryInsurance) {
        this.companyInjuryInsurance = companyInjuryInsurance;
    }

    public Double getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(Double overtimePay) {
        this.overtimePay = overtimePay;
    }

    public Double getOtherPay() {
        return otherPay;
    }

    public void setOtherPay(Double otherPay) {
        this.otherPay = otherPay;
    }

    public Double getIndividualTax() {
        return individualTax;
    }

    public void setIndividualTax(Double individualTax) {
        this.individualTax = individualTax;
    }

    public Double getRealWages() {
        return realWages;
    }

    public void setRealWages(Double realWages) {
        this.realWages = realWages;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
