package com.lefancrm.backend.dto.staff;

import java.util.Date;

public class StaffPerformancePersonnelDto {
    private Long id;

    private Long staffPerformanceId;

    private Long staffPersonnelId;

    private String jobNo;

    private Double assessKpi;

    private Double integral;

    private Double integralPay;

    private Integer lateEarlyNum;

    private Double lateEarlyMoney;

    private Integer absenteeismNum;

    private Double absenteeismMoney;

    private Double leaveNum;

    private Double leaveMoney;

    private Double sickLeaveTime;

    private Double sickLeaveMoney;

    private Double otherPay;

    private Double rate;

    private String remarks;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    private String realName;
    private Double assesPerfPay;
    private Double realAssessKpi;

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

    private String company;

    private String organ;

    private String department;

    private String jobPost;

    private String userTel;

    private Date entryTime;

    private Double realWorkingDays;

    private Double workingDays;

    private Double fixedPerfPay;

    private Double travelAllowancePay;

    private Double managePerfPay;

    private Double realPay;

    private Double scoreHz;

    private Double sunScoreHz;

    private Double scoreBs;

    private Long socialSecurityCompanyId;

    private String socialSecurityCompany;

    private Double sunMoneyBs; //保司阳性奖励

    private Double managePerfPaySize;

    private Integer manageCaseNum;

    private Double welfarePay;

    private String welfareRemark;

    private Double quitCost;
    private String workTime;

    private Double managePerfPaySizeHz;

    private Integer manageCaseNumHz;

    private Double examineScore;

    private Double examinePay;

    private Double otherCutPay;

    private String otherCutRemarks;

    private Double examineRate;

    private Double caseSubMoney;//个案减损奖励

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
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

    public Double getLeaveNum() {
        return leaveNum;
    }

    public void setLeaveNum(Double leaveNum) {
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Double getAssesPerfPay() {
        return assesPerfPay;
    }

    public void setAssesPerfPay(Double assesPerfPay) {
        this.assesPerfPay = assesPerfPay;
    }

    public Double getRealAssessKpi() {
        return realAssessKpi;
    }

    public void setRealAssessKpi(Double realAssessKpi) {
        this.realAssessKpi = realAssessKpi;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobPost() {
        return jobPost;
    }

    public void setJobPost(String jobPost) {
        this.jobPost = jobPost;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
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

    public Double getFixedPerfPay() {
        return fixedPerfPay;
    }

    public void setFixedPerfPay(Double fixedPerfPay) {
        this.fixedPerfPay = fixedPerfPay;
    }

    public Double getTravelAllowancePay() {
        return travelAllowancePay;
    }

    public void setTravelAllowancePay(Double travelAllowancePay) {
        this.travelAllowancePay = travelAllowancePay;
    }

    public Double getManagePerfPay() {
        return managePerfPay;
    }

    public void setManagePerfPay(Double managePerfPay) {
        this.managePerfPay = managePerfPay;
    }

    public Double getRealPay() {
        return realPay;
    }

    public void setRealPay(Double realPay) {
        this.realPay = realPay;
    }

    public Double getScoreHz() {
        return scoreHz;
    }

    public void setScoreHz(Double scoreHz) {
        this.scoreHz = scoreHz;
    }

    public Double getSunScoreHz() {
        return sunScoreHz;
    }

    public void setSunScoreHz(Double sunScoreHz) {
        this.sunScoreHz = sunScoreHz;
    }

    public Double getScoreBs() {
        return scoreBs;
    }

    public void setScoreBs(Double scoreBs) {
        this.scoreBs = scoreBs;
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

    public Double getQuitCost() {
        return quitCost;
    }

    public void setQuitCost(Double quitCost) {
        this.quitCost = quitCost;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
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

    public Double getExamineRate() {
        return examineRate;
    }

    public void setExamineRate(Double examineRate) {
        this.examineRate = examineRate;
    }

    public Double getCaseSubMoney() {
        return caseSubMoney;
    }

    public void setCaseSubMoney(Double caseSubMoney) {
        this.caseSubMoney = caseSubMoney;
    }
}