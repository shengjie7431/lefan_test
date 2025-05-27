package com.lefancrm.backend.dto.staff;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

public class StaffPersonnelInfoDto {
    private Long id;

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

    private Integer deleteFlag;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    /**
     * 养老保险基数
     */
    private Double pensionBase;

    /**
     * 养老保险公司比例
     */
    private Double pensionCompanyRate;

    /**
     * 养老保险个人比例
     */
    private Double pensionPersonalRate;

    /**
     * 医疗保险基数
     */
    private Double medicalBase;

    /**
     * 医疗保险公司比例
     */
    private Double medicalCompanyRate;

    /**
     * 医疗保险个人比例
     */
    private Double medicalPersonalRate;

    /**
     * 失业保险基数
     */
    private Double upmBase;

    /**
     * 失业保险公司比例
     */
    private Double upmCompanyRate;

    /**
     * 失业保险个人比例
     */
    private Double upmPersonalRate;

    /**
     * 工伤保险基数
     */
    private Double isaBase;

    /**
     * 工伤保险公司比例
     */
    private Double isaCompanyRate;

    /**
     * 生育保险基数
     */
    private Double birthBase;

    /**
     * 生育保险公司比例
     */
    private Double birthCompanyRate;

    /**
     * 公积金公司比例
     */
    private Double fundPayCompanyRate;

    /**
     * 公积金个人比例
     */
    private Double fundPayPersonalRate;

    private Long teamId;

    private String team;

    private Long socialSecurityCompanyId;

    private String socialSecurityCompany;

    private Double managePerfPaySize;

    private Double quitCost;

    private Double managePerfPaySizeHz;

    private Long surveyLevelId;

    private String surveyLevelName;

    private Date regularTime;

    private String extTel;

    private String officePlace;

    private String remark;

    private String trialTime;

    private String jobLevel;

    private String education;

    private String graduationSchool;

    private Date graduationTime;

    private String major;

    private String bankNo;

    private String bankName;

    private String contractCompany;

    private String contractType;

    private Date firstContractBeginTime;

    private Date firstContractEndTime;

    private Date nowContractBeginTime;

    private Date nowContractEndTime;

    private String contractTerm;

    private String renewNum;

    private String emergencyContactName;

    private String emergencyContactRelation;

    private String emergencyContactTel;

    private String familyName;

    private String familyRelation;

    private String familySex;

    private Date familyBirthday;

    private String familyTel;

    private String familyIdcardName;

    @XmlElement(name = "成本归属公司ID")
    private Long budgetCompanyId;

    @XmlElement(name = "成本归属公司名称")
    private String budgetCompanyName;

    @XmlElement(name = "岗位职级ID")
    private Long postRankId;

    @XmlElement(name = "岗位职级名称")
    private String postRankName;

    @XmlElement(name = "职务称谓ID")
    private Long postAppellationId;

    private Integer isCanModify;//是否可以更改机构

    private Double homeLbsX;

    private Double homeLbsY;

    private String homeAddress;

    private String homeAddressName;

    public Long getBudgetCompanyId() {
        return budgetCompanyId;
    }

    public void setBudgetCompanyId(Long budgetCompanyId) {
        this.budgetCompanyId = budgetCompanyId;
    }

    public String getBudgetCompanyName() {
        return budgetCompanyName;
    }

    public void setBudgetCompanyName(String budgetCompanyName) {
        this.budgetCompanyName = budgetCompanyName;
    }

    public Long getPostRankId() {
        return postRankId;
    }

    public void setPostRankId(Long postRankId) {
        this.postRankId = postRankId;
    }

    public String getPostRankName() {
        return postRankName;
    }

    public void setPostRankName(String postRankName) {
        this.postRankName = postRankName;
    }

    public Long getPostAppellationId() {
        return postAppellationId;
    }

    public void setPostAppellationId(Long postAppellationId) {
        this.postAppellationId = postAppellationId;
    }

    public String getPostAppellationName() {
        return postAppellationName;
    }

    public void setPostAppellationName(String postAppellationName) {
        this.postAppellationName = postAppellationName;
    }

    @XmlElement(name = "职务称谓名称")
    private String postAppellationName;


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

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public Double getPensionBase() {
        return pensionBase;
    }

    public void setPensionBase(Double pensionBase) {
        this.pensionBase = pensionBase;
    }

    public Double getPensionCompanyRate() {
        return pensionCompanyRate;
    }

    public void setPensionCompanyRate(Double pensionCompanyRate) {
        this.pensionCompanyRate = pensionCompanyRate;
    }

    public Double getPensionPersonalRate() {
        return pensionPersonalRate;
    }

    public void setPensionPersonalRate(Double pensionPersonalRate) {
        this.pensionPersonalRate = pensionPersonalRate;
    }

    public Double getMedicalBase() {
        return medicalBase;
    }

    public void setMedicalBase(Double medicalBase) {
        this.medicalBase = medicalBase;
    }

    public Double getMedicalCompanyRate() {
        return medicalCompanyRate;
    }

    public void setMedicalCompanyRate(Double medicalCompanyRate) {
        this.medicalCompanyRate = medicalCompanyRate;
    }

    public Double getMedicalPersonalRate() {
        return medicalPersonalRate;
    }

    public void setMedicalPersonalRate(Double medicalPersonalRate) {
        this.medicalPersonalRate = medicalPersonalRate;
    }

    public Double getUpmBase() {
        return upmBase;
    }

    public void setUpmBase(Double upmBase) {
        this.upmBase = upmBase;
    }

    public Double getUpmCompanyRate() {
        return upmCompanyRate;
    }

    public void setUpmCompanyRate(Double upmCompanyRate) {
        this.upmCompanyRate = upmCompanyRate;
    }

    public Double getUpmPersonalRate() {
        return upmPersonalRate;
    }

    public void setUpmPersonalRate(Double upmPersonalRate) {
        this.upmPersonalRate = upmPersonalRate;
    }

    public Double getIsaBase() {
        return isaBase;
    }

    public void setIsaBase(Double isaBase) {
        this.isaBase = isaBase;
    }

    public Double getIsaCompanyRate() {
        return isaCompanyRate;
    }

    public void setIsaCompanyRate(Double isaCompanyRate) {
        this.isaCompanyRate = isaCompanyRate;
    }

    public Double getBirthBase() {
        return birthBase;
    }

    public void setBirthBase(Double birthBase) {
        this.birthBase = birthBase;
    }

    public Double getBirthCompanyRate() {
        return birthCompanyRate;
    }

    public void setBirthCompanyRate(Double birthCompanyRate) {
        this.birthCompanyRate = birthCompanyRate;
    }

    public Double getFundPayCompanyRate() {
        return fundPayCompanyRate;
    }

    public void setFundPayCompanyRate(Double fundPayCompanyRate) {
        this.fundPayCompanyRate = fundPayCompanyRate;
    }

    public Double getFundPayPersonalRate() {
        return fundPayPersonalRate;
    }

    public void setFundPayPersonalRate(Double fundPayPersonalRate) {
        this.fundPayPersonalRate = fundPayPersonalRate;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Long getSocialSecurityCompanyId() {
        return socialSecurityCompanyId;
    }

    public void setSocialSecurityCompanyId(Long socialSecurityCompanyId) {
        this.socialSecurityCompanyId = socialSecurityCompanyId;
    }

    public String getSocialSecurityCompany() {
        return socialSecurityCompany;
    }

    public void setSocialSecurityCompany(String socialSecurityCompany) {
        this.socialSecurityCompany = socialSecurityCompany;
    }

    public Double getManagePerfPaySize() {
        return managePerfPaySize;
    }

    public void setManagePerfPaySize(Double managePerfPaySize) {
        this.managePerfPaySize = managePerfPaySize;
    }

    public Double getQuitCost() {
        return quitCost;
    }

    public void setQuitCost(Double quitCost) {
        this.quitCost = quitCost;
    }

    public Double getManagePerfPaySizeHz() {
        return managePerfPaySizeHz;
    }

    public void setManagePerfPaySizeHz(Double managePerfPaySizeHz) {
        this.managePerfPaySizeHz = managePerfPaySizeHz;
    }

    public Long getSurveyLevelId() {
        return surveyLevelId;
    }

    public void setSurveyLevelId(Long surveyLevelId) {
        this.surveyLevelId = surveyLevelId;
    }

    public String getSurveyLevelName() {
        return surveyLevelName;
    }

    public void setSurveyLevelName(String surveyLevelName) {
        this.surveyLevelName = surveyLevelName;
    }

    public Date getRegularTime() {
        return regularTime;
    }

    public void setRegularTime(Date regularTime) {
        this.regularTime = regularTime;
    }

    public String getExtTel() {
        return extTel;
    }

    public void setExtTel(String extTel) {
        this.extTel = extTel;
    }

    public String getOfficePlace() {
        return officePlace;
    }

    public void setOfficePlace(String officePlace) {
        this.officePlace = officePlace;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTrialTime() {
        return trialTime;
    }

    public void setTrialTime(String trialTime) {
        this.trialTime = trialTime;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduationSchool() {
        return graduationSchool;
    }

    public void setGraduationSchool(String graduationSchool) {
        this.graduationSchool = graduationSchool;
    }

    public Date getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Date graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getContractCompany() {
        return contractCompany;
    }

    public void setContractCompany(String contractCompany) {
        this.contractCompany = contractCompany;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Date getFirstContractBeginTime() {
        return firstContractBeginTime;
    }

    public void setFirstContractBeginTime(Date firstContractBeginTime) {
        this.firstContractBeginTime = firstContractBeginTime;
    }

    public Date getFirstContractEndTime() {
        return firstContractEndTime;
    }

    public void setFirstContractEndTime(Date firstContractEndTime) {
        this.firstContractEndTime = firstContractEndTime;
    }

    public Date getNowContractBeginTime() {
        return nowContractBeginTime;
    }

    public void setNowContractBeginTime(Date nowContractBeginTime) {
        this.nowContractBeginTime = nowContractBeginTime;
    }

    public Date getNowContractEndTime() {
        return nowContractEndTime;
    }

    public void setNowContractEndTime(Date nowContractEndTime) {
        this.nowContractEndTime = nowContractEndTime;
    }

    public String getContractTerm() {
        return contractTerm;
    }

    public void setContractTerm(String contractTerm) {
        this.contractTerm = contractTerm;
    }

    public String getRenewNum() {
        return renewNum;
    }

    public void setRenewNum(String renewNum) {
        this.renewNum = renewNum;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactRelation() {
        return emergencyContactRelation;
    }

    public void setEmergencyContactRelation(String emergencyContactRelation) {
        this.emergencyContactRelation = emergencyContactRelation;
    }

    public String getEmergencyContactTel() {
        return emergencyContactTel;
    }

    public void setEmergencyContactTel(String emergencyContactTel) {
        this.emergencyContactTel = emergencyContactTel;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyRelation() {
        return familyRelation;
    }

    public void setFamilyRelation(String familyRelation) {
        this.familyRelation = familyRelation;
    }

    public String getFamilySex() {
        return familySex;
    }

    public void setFamilySex(String familySex) {
        this.familySex = familySex;
    }

    public Date getFamilyBirthday() {
        return familyBirthday;
    }

    public void setFamilyBirthday(Date familyBirthday) {
        this.familyBirthday = familyBirthday;
    }

    public String getFamilyTel() {
        return familyTel;
    }

    public void setFamilyTel(String familyTel) {
        this.familyTel = familyTel;
    }

    public String getFamilyIdcardName() {
        return familyIdcardName;
    }

    public void setFamilyIdcardName(String familyIdcardName) {
        this.familyIdcardName = familyIdcardName;
    }


    public Integer getIsCanModify() {
        return isCanModify;
    }

    public void setIsCanModify(Integer isCanModify) {
        this.isCanModify = isCanModify;
    }

    public Double getHomeLbsX() {
        return homeLbsX;
    }

    public void setHomeLbsX(Double homeLbsX) {
        this.homeLbsX = homeLbsX;
    }

    public Double getHomeLbsY() {
        return homeLbsY;
    }

    public void setHomeLbsY(Double homeLbsY) {
        this.homeLbsY = homeLbsY;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHomeAddressName() {
        return homeAddressName;
    }

    public void setHomeAddressName(String homeAddressName) {
        this.homeAddressName = homeAddressName;
    }
}