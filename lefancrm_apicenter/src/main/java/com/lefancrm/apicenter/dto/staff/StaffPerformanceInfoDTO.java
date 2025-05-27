package com.lefancrm.apicenter.dto.staff;

import java.util.Date;

public class StaffPerformanceInfoDTO {
    private Long id;

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

    private String entryTimeStr;

    private Long itemId;

    private Long staffPerformanceId;

    private Long staffPersonnelId;

    private Double assessKpi;

    private Double integral;

    private Double integralPay;

    private Integer lateEarlyNum;

    private Double lateEarlyMoney;

    private Integer absenteeismNum;

    private Double absenteeismMoney;

    private Integer leaveNum;

    private Double leaveMoney;

    private Double sickLeaveTime;

    private Double sickLeaveMoney;

    private Double otherPay;

    private Double rate;

    private String remarks;

    private Double realPay;
    private Double bsScoreHz;
    private Double otherScoreHz;
    private Double sunScoreHz;
    private Double trafficSubsidy;

    private Double realWorkingDays;
    private Double workingDays;
    private Double realAssessKpi;

    private Double assessBsScore;//考核前京沪分值

    private Double assessOtherScore;//考核前其他分值

    private String organOpinion;

    private String superiorOpinion;

    private String bossOpinion;

    private Long organManagerUserId;

    private Long superiorManagerUserId;

    private Integer backState;

    private String backReason;

    private Integer showState;

    private Double basicIntegral;

    private Double fixedPerfPayBase;

    private Double travelAllowancePayBase;

    private Double managePerfPayBase;

    private Integer isNewPeople;

    private Double bsScoreBs;

    private Double otherScoreBs;

    private Double sunScoreBs;

    private Double monthBasicIntegral;

    private String team;

    private Long teamId;

    private Double scoreHz;//互助调查积分（京沪积分 + 非京沪积分）
    private Double scoreBs;//保司调查积分（京沪积分 + 非京沪积分）

    private Long investigatorId;//调查员id（用于判断非调查员的积分 为null）
    private Long investigatorOrgId;//调查员所在机构id

    private Long socialSecurityCompanyId;

    private String socialSecurityCompany;

    private Double sunMoneyBs; //保司阳性奖励

    private Double managePerfPaySize;

    private Integer manageCaseNum;

    private Double welfarePay;

    private String welfareRemark;

    private Double managePerfPaySizeHz;

    private Integer manageCaseNumHz;

    private Double examineScore;

    private Double examinePay;

    private Double otherCutPay;

    private String otherCutRemarks;

    private Double assesPerfBasePay;//考核绩效（员工管理中基础数据）

    private Double examineRate;//审核绩效基数

    private Double caseSubMoney;//个案减损奖励

    private Integer hzStaffOpinionState;
    private Integer bsStaffOpinionState;

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getStaffPerformanceId() {
        return staffPerformanceId;
    }

    public void setStaffPerformanceId(Long staffPerformanceId) {
        this.staffPerformanceId = staffPerformanceId;
    }

    public Long getStaffPersonnelId() {
        return staffPersonnelId;
    }

    public void setStaffPersonnelId(Long staffPersonnelId) {
        this.staffPersonnelId = staffPersonnelId;
    }

    public Double getAssessKpi() {
        return assessKpi;
    }

    public void setAssessKpi(Double assessKpi) {
        this.assessKpi = assessKpi;
    }

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    public Double getIntegralPay() {
        return integralPay;
    }

    public void setIntegralPay(Double integralPay) {
        this.integralPay = integralPay;
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

    public Double getOtherPay() {
        return otherPay;
    }

    public void setOtherPay(Double otherPay) {
        this.otherPay = otherPay;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEntryTimeStr() {
        return entryTimeStr;
    }

    public void setEntryTimeStr(String entryTimeStr) {
        this.entryTimeStr = entryTimeStr;
    }

    public Double getRealPay() {
        return realPay;
    }

    public void setRealPay(Double realPay) {
        this.realPay = realPay;
    }

    public Double getBsScoreHz() {
        return bsScoreHz;
    }

    public void setBsScoreHz(Double bsScoreHz) {
        this.bsScoreHz = bsScoreHz;
    }

    public Double getOtherScoreHz() {
        return otherScoreHz;
    }

    public void setOtherScoreHz(Double otherScoreHz) {
        this.otherScoreHz = otherScoreHz;
    }

    public Double getSunScoreHz() {
        return sunScoreHz;
    }

    public void setSunScoreHz(Double sunScoreHz) {
        this.sunScoreHz = sunScoreHz;
    }

    public Double getTrafficSubsidy() {
        return trafficSubsidy;
    }

    public void setTrafficSubsidy(Double trafficSubsidy) {
        this.trafficSubsidy = trafficSubsidy;
    }

    public Double getRealWorkingDays() {
        return realWorkingDays;
    }

    public void setRealWorkingDays(Double realWorkingDays) {
        this.realWorkingDays = realWorkingDays;
    }

    public Double getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Double workingDays) {
        this.workingDays = workingDays;
    }

    public Double getRealAssessKpi() {
        return realAssessKpi;
    }

    public void setRealAssessKpi(Double realAssessKpi) {
        this.realAssessKpi = realAssessKpi;
    }

    public Double getAssessBsScore() {
        return assessBsScore;
    }

    public void setAssessBsScore(Double assessBsScore) {
        this.assessBsScore = assessBsScore;
    }

    public Double getAssessOtherScore() {
        return assessOtherScore;
    }

    public void setAssessOtherScore(Double assessOtherScore) {
        this.assessOtherScore = assessOtherScore;
    }

    public String getOrganOpinion() {
        return organOpinion;
    }

    public void setOrganOpinion(String organOpinion) {
        this.organOpinion = organOpinion;
    }

    public String getSuperiorOpinion() {
        return superiorOpinion;
    }

    public void setSuperiorOpinion(String superiorOpinion) {
        this.superiorOpinion = superiorOpinion;
    }

    public String getBossOpinion() {
        return bossOpinion;
    }

    public void setBossOpinion(String bossOpinion) {
        this.bossOpinion = bossOpinion;
    }

    public Long getOrganManagerUserId() {
        return organManagerUserId;
    }

    public void setOrganManagerUserId(Long organManagerUserId) {
        this.organManagerUserId = organManagerUserId;
    }

    public Long getSuperiorManagerUserId() {
        return superiorManagerUserId;
    }

    public void setSuperiorManagerUserId(Long superiorManagerUserId) {
        this.superiorManagerUserId = superiorManagerUserId;
    }

    public Integer getBackState() {
        return backState;
    }

    public void setBackState(Integer backState) {
        this.backState = backState;
    }

    public String getBackReason() {
        return backReason;
    }

    public void setBackReason(String backReason) {
        this.backReason = backReason;
    }

    public Integer getShowState() {
        return showState;
    }

    public void setShowState(Integer showState) {
        this.showState = showState;
    }

    public Double getBasicIntegral() {
        return basicIntegral;
    }

    public void setBasicIntegral(Double basicIntegral) {
        this.basicIntegral = basicIntegral;
    }

    public Double getFixedPerfPayBase() {
        return fixedPerfPayBase;
    }

    public void setFixedPerfPayBase(Double fixedPerfPayBase) {
        this.fixedPerfPayBase = fixedPerfPayBase;
    }

    public Double getTravelAllowancePayBase() {
        return travelAllowancePayBase;
    }

    public void setTravelAllowancePayBase(Double travelAllowancePayBase) {
        this.travelAllowancePayBase = travelAllowancePayBase;
    }

    public Double getManagePerfPayBase() {
        return managePerfPayBase;
    }

    public void setManagePerfPayBase(Double managePerfPayBase) {
        this.managePerfPayBase = managePerfPayBase;
    }

    public Integer getIsNewPeople() {
        return isNewPeople;
    }

    public void setIsNewPeople(Integer isNewPeople) {
        this.isNewPeople = isNewPeople;
    }

    public Double getBsScoreBs() {
        return bsScoreBs;
    }

    public void setBsScoreBs(Double bsScoreBs) {
        this.bsScoreBs = bsScoreBs;
    }

    public Double getOtherScoreBs() {
        return otherScoreBs;
    }

    public void setOtherScoreBs(Double otherScoreBs) {
        this.otherScoreBs = otherScoreBs;
    }

    public Double getSunScoreBs() {
        return sunScoreBs;
    }

    public void setSunScoreBs(Double sunScoreBs) {
        this.sunScoreBs = sunScoreBs;
    }

    public Double getMonthBasicIntegral() {
        return monthBasicIntegral;
    }

    public void setMonthBasicIntegral(Double monthBasicIntegral) {
        this.monthBasicIntegral = monthBasicIntegral;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Double getScoreHz() {
        return scoreHz;
    }

    public void setScoreHz(Double scoreHz) {
        this.scoreHz = scoreHz;
    }

    public Double getScoreBs() {
        return scoreBs;
    }

    public void setScoreBs(Double scoreBs) {
        this.scoreBs = scoreBs;
    }

    public Long getInvestigatorId() {
        return investigatorId;
    }

    public void setInvestigatorId(Long investigatorId) {
        this.investigatorId = investigatorId;
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

    public Double getSunMoneyBs() {
        return sunMoneyBs;
    }

    public void setSunMoneyBs(Double sunMoneyBs) {
        this.sunMoneyBs = sunMoneyBs;
    }

    public Double getManagePerfPaySize() {
        return managePerfPaySize;
    }

    public void setManagePerfPaySize(Double managePerfPaySize) {
        this.managePerfPaySize = managePerfPaySize;
    }

    public Integer getManageCaseNum() {
        return manageCaseNum;
    }

    public void setManageCaseNum(Integer manageCaseNum) {
        this.manageCaseNum = manageCaseNum;
    }

    public Double getWelfarePay() {
        return welfarePay;
    }

    public void setWelfarePay(Double welfarePay) {
        this.welfarePay = welfarePay;
    }

    public String getWelfareRemark() {
        return welfareRemark;
    }

    public void setWelfareRemark(String welfareRemark) {
        this.welfareRemark = welfareRemark;
    }

    public Long getInvestigatorOrgId() {
        return investigatorOrgId;
    }

    public void setInvestigatorOrgId(Long investigatorOrgId) {
        this.investigatorOrgId = investigatorOrgId;
    }

    public Double getManagePerfPaySizeHz() {
        return managePerfPaySizeHz;
    }

    public void setManagePerfPaySizeHz(Double managePerfPaySizeHz) {
        this.managePerfPaySizeHz = managePerfPaySizeHz;
    }

    public Integer getManageCaseNumHz() {
        return manageCaseNumHz;
    }

    public void setManageCaseNumHz(Integer manageCaseNumHz) {
        this.manageCaseNumHz = manageCaseNumHz;
    }

    public Double getExamineScore() {
        return examineScore;
    }

    public void setExamineScore(Double examineScore) {
        this.examineScore = examineScore;
    }

    public Double getExaminePay() {
        return examinePay;
    }

    public void setExaminePay(Double examinePay) {
        this.examinePay = examinePay;
    }

    public Double getOtherCutPay() {
        return otherCutPay;
    }

    public void setOtherCutPay(Double otherCutPay) {
        this.otherCutPay = otherCutPay;
    }

    public String getOtherCutRemarks() {
        return otherCutRemarks;
    }

    public void setOtherCutRemarks(String otherCutRemarks) {
        this.otherCutRemarks = otherCutRemarks;
    }

    public Double getAssesPerfBasePay() {
        return assesPerfBasePay;
    }

    public void setAssesPerfBasePay(Double assesPerfBasePay) {
        this.assesPerfBasePay = assesPerfBasePay;
    }

    public Double getExamineRate() {
        return examineRate;
    }

    public void setExamineRate(Double examineRate) {
        this.examineRate = examineRate;
    }

    public Integer getHzStaffOpinionState() {
        return hzStaffOpinionState;
    }

    public void setHzStaffOpinionState(Integer hzStaffOpinionState) {
        this.hzStaffOpinionState = hzStaffOpinionState;
    }

    public Integer getBsStaffOpinionState() {
        return bsStaffOpinionState;
    }

    public void setBsStaffOpinionState(Integer bsStaffOpinionState) {
        this.bsStaffOpinionState = bsStaffOpinionState;
    }

    public Double getCaseSubMoney() {
        return caseSubMoney;
    }

    public void setCaseSubMoney(Double caseSubMoney) {
        this.caseSubMoney = caseSubMoney;
    }
}
