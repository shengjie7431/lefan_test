package com.lefancrm.apicenter.fina.model;

import com.lefancrm.apicenter.fina.dao.FinaApplicantOrgMapper;

import java.util.Date;
import java.util.List;

public class FinaApplicantInfo {
    private Long id;

    private String caseApplicantNo;

    private Long finaId;

    private Long entrustOrgId;

    private String entrustOrgName;

    private String insuredName;

    private String insuredTel;

    private String insuredIdcard;

    private Integer relationship;

    private Double applyAdvanceMoney;

    private String insurancePolicyNo;

    private Date firstInsureTime;

    private Double insureMoney;

    private String insureType;

    private Date insureStartTime;

    private Date insureEndTime;

    private Double deductibleMoney;

    private Integer outInsureType;

    private String outInsureReason;

    private Long hospitalId;

    private String hospitalName;

    private String department;

    private Date inHospitalTime;

    private Integer businessType;

    private String firstContactDesc;

    private Integer applicantState;
    private String applicantStateStr;//垫付状态

    private Date createTime;

    private String createBy;

    private Date finshCollectTime;

    private Date firstPassedTime;

    private Date finshSurveyTime;

    private Date finshReviewTime;

    private Date finshInsuranceTime;

    private Date finshLoanTime;

    private Date loanAccountTime;

    private Date finshStatementTime;

    private Date finshApplyclaimsTime;

    private Date finshRepaymentTime;

    private Date finalInsurancePassedTime;

    private Date caseCloseTime;

    private Date revisitTime;

    private String firstReturnReason;

    private Date firstReturnTime;

    private String refuseApplicant;

    private Integer deleteFlag;

    private Date updateTime;

    private String updateBy;

    private Integer finaNum;

    private Long finaParentId;

    private Integer caseType; //案件类型：1、正常案件，2、风险案件，3、坏账案件

    private Long surveyInfoId;//调查案件子表ID（转乐凡调查）

    //申请人信息
    private FinaApplicant finaApplicant;
    private Long finaUserId;
    private String finaUserName;
    private String finaUserIdcard;
    private String finaUserTel;

    private List<FinaApplicantOrg> finaApplicantOrgList; //机构案件信息
    private List<FinaApplicantInvestigator> finaApplicantInvestigatorList; //调查员案件信息
    private List<FinaApplicantTrack> finaApplicantTrackList;//跟踪信息
    private FinaDiagnosisTreatment finaDiagnosisTreatment;//主要诊断
    private List<FinaDiagnosisTreatment> finaDiagnosisTreatmentList;//其他诊断
    private FinaHospitalAccount finaHospitalAccount;//医院账号
    private FinaHospitalInfo finaHospitalInfo;
    private FinaConfirmAccount finaConfirmAccount;
    private Integer applicantFileNum; //立案附件数量

    //垫付费用信息表
    private FinaApplicantMoney finaApplicantMoney;
    private Double estimateMoney;
    private Double proposalMoney;
    private Double actualMoney;
    private Integer riskLevel;
    private Date realLoanTime;
    private Integer confirmType;
    private Long checkManId;
    private String checkManName;
    private Long doubleCheckManId;
    private String doubleCheckManName;
    private Date checkTime;
    private Date doubleCheckTime;

    //机构信息（仅为垫付材料收集）
    private FinaApplicantOrg finaApplicantOrg;
    private Boolean assignOrg = false; //是否已分派机构
    private Long applicantOrgId;
    private Long surveyOrgId;
    private String surveyOrgName;
    private Integer finaOrgState;
    private Date orgAssignTime;
    private Date orgSubmitTime;
    private Date orgEndTime;
    private Double orgAging;

    //垫付员信息（仅为垫付材料收集）
    private FinaApplicantInvestigator finaApplicantInvestigator;
    private Boolean assignUser = false; //是否已分派调查员
    private Long applicantInvestigatorId;
    private Long surveyUserId;
    private String surveyUserName;
    private Integer userState;
    private Date userAssignTime;
    private Date userSubmitTime;
    private Date userEndTime;
    private Double userAging;

    private Integer insuredAge;

    private FinaSettlementInfo finaSettlementInfo;

    private Boolean haveAccount = false;//是否已确认到账
    private Integer progressNum;//建议垫付金额的进度数量

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseApplicantNo() {
        return caseApplicantNo;
    }

    public void setCaseApplicantNo(String caseApplicantNo) {
        this.caseApplicantNo = caseApplicantNo;
    }

    public Long getFinaId() {
        return finaId;
    }

    public void setFinaId(Long finaId) {
        this.finaId = finaId;
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

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getInsuredTel() {
        return insuredTel;
    }

    public void setInsuredTel(String insuredTel) {
        this.insuredTel = insuredTel;
    }

    public String getInsuredIdcard() {
        return insuredIdcard;
    }

    public void setInsuredIdcard(String insuredIdcard) {
        this.insuredIdcard = insuredIdcard;
    }

    public Integer getRelationship() {
        return relationship;
    }

    public void setRelationship(Integer relationship) {
        this.relationship = relationship;
    }

    public Double getApplyAdvanceMoney() {
        return applyAdvanceMoney;
    }

    public void setApplyAdvanceMoney(Double applyAdvanceMoney) {
        this.applyAdvanceMoney = applyAdvanceMoney;
    }

    public String getInsurancePolicyNo() {
        return insurancePolicyNo;
    }

    public void setInsurancePolicyNo(String insurancePolicyNo) {
        this.insurancePolicyNo = insurancePolicyNo;
    }

    public Date getFirstInsureTime() {
        return firstInsureTime;
    }

    public void setFirstInsureTime(Date firstInsureTime) {
        this.firstInsureTime = firstInsureTime;
    }

    public Double getInsureMoney() {
        return insureMoney;
    }

    public void setInsureMoney(Double insureMoney) {
        this.insureMoney = insureMoney;
    }

    public String getInsureType() {
        return insureType;
    }

    public void setInsureType(String insureType) {
        this.insureType = insureType;
    }

    public Date getInsureStartTime() {
        return insureStartTime;
    }

    public void setInsureStartTime(Date insureStartTime) {
        this.insureStartTime = insureStartTime;
    }

    public Date getInsureEndTime() {
        return insureEndTime;
    }

    public void setInsureEndTime(Date insureEndTime) {
        this.insureEndTime = insureEndTime;
    }

    public Double getDeductibleMoney() {
        return deductibleMoney;
    }

    public void setDeductibleMoney(Double deductibleMoney) {
        this.deductibleMoney = deductibleMoney;
    }

    public String getOutInsureReason() {
        return outInsureReason;
    }

    public void setOutInsureReason(String outInsureReason) {
        this.outInsureReason = outInsureReason;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getInHospitalTime() {
        return inHospitalTime;
    }

    public void setInHospitalTime(Date inHospitalTime) {
        this.inHospitalTime = inHospitalTime;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getFirstContactDesc() {
        return firstContactDesc;
    }

    public void setFirstContactDesc(String firstContactDesc) {
        this.firstContactDesc = firstContactDesc;
    }

    public Integer getApplicantState() {
        return applicantState;
    }

    public void setApplicantState(Integer applicantState) {
        this.applicantState = applicantState;
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

    public Date getFinshCollectTime() {
        return finshCollectTime;
    }

    public void setFinshCollectTime(Date finshCollectTime) {
        this.finshCollectTime = finshCollectTime;
    }

    public Date getFirstPassedTime() {
        return firstPassedTime;
    }

    public void setFirstPassedTime(Date firstPassedTime) {
        this.firstPassedTime = firstPassedTime;
    }

    public Date getFinshSurveyTime() {
        return finshSurveyTime;
    }

    public void setFinshSurveyTime(Date finshSurveyTime) {
        this.finshSurveyTime = finshSurveyTime;
    }

    public Date getFinshReviewTime() {
        return finshReviewTime;
    }

    public void setFinshReviewTime(Date finshReviewTime) {
        this.finshReviewTime = finshReviewTime;
    }

    public Date getFinshInsuranceTime() {
        return finshInsuranceTime;
    }

    public void setFinshInsuranceTime(Date finshInsuranceTime) {
        this.finshInsuranceTime = finshInsuranceTime;
    }

    public Date getFinshLoanTime() {
        return finshLoanTime;
    }

    public void setFinshLoanTime(Date finshLoanTime) {
        this.finshLoanTime = finshLoanTime;
    }

    public Date getLoanAccountTime() {
        return loanAccountTime;
    }

    public void setLoanAccountTime(Date loanAccountTime) {
        this.loanAccountTime = loanAccountTime;
    }

    public Date getFinshStatementTime() {
        return finshStatementTime;
    }

    public void setFinshStatementTime(Date finshStatementTime) {
        this.finshStatementTime = finshStatementTime;
    }

    public Date getFinshApplyclaimsTime() {
        return finshApplyclaimsTime;
    }

    public void setFinshApplyclaimsTime(Date finshApplyclaimsTime) {
        this.finshApplyclaimsTime = finshApplyclaimsTime;
    }

    public Date getFinshRepaymentTime() {
        return finshRepaymentTime;
    }

    public void setFinshRepaymentTime(Date finshRepaymentTime) {
        this.finshRepaymentTime = finshRepaymentTime;
    }

    public Date getFinalInsurancePassedTime() {
        return finalInsurancePassedTime;
    }

    public void setFinalInsurancePassedTime(Date finalInsurancePassedTime) {
        this.finalInsurancePassedTime = finalInsurancePassedTime;
    }

    public Date getCaseCloseTime() {
        return caseCloseTime;
    }

    public void setCaseCloseTime(Date caseCloseTime) {
        this.caseCloseTime = caseCloseTime;
    }

    public Date getRevisitTime() {
        return revisitTime;
    }

    public void setRevisitTime(Date revisitTime) {
        this.revisitTime = revisitTime;
    }

    public String getFirstReturnReason() {
        return firstReturnReason;
    }

    public void setFirstReturnReason(String firstReturnReason) {
        this.firstReturnReason = firstReturnReason;
    }

    public Date getFirstReturnTime() {
        return firstReturnTime;
    }

    public void setFirstReturnTime(Date firstReturnTime) {
        this.firstReturnTime = firstReturnTime;
    }

    public String getRefuseApplicant() {
        return refuseApplicant;
    }

    public void setRefuseApplicant(String refuseApplicant) {
        this.refuseApplicant = refuseApplicant;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public Integer getFinaNum() {
        return finaNum;
    }

    public void setFinaNum(Integer finaNum) {
        this.finaNum = finaNum;
    }

    public Long getFinaParentId() {
        return finaParentId;
    }

    public void setFinaParentId(Long finaParentId) {
        this.finaParentId = finaParentId;
    }

    public Long getFinaUserId() {
        return finaUserId;
    }

    public void setFinaUserId(Long finaUserId) {
        this.finaUserId = finaUserId;
    }

    public String getFinaUserName() {
        return finaUserName;
    }

    public void setFinaUserName(String finaUserName) {
        this.finaUserName = finaUserName;
    }

    public String getFinaUserIdcard() {
        return finaUserIdcard;
    }

    public void setFinaUserIdcard(String finaUserIdcard) {
        this.finaUserIdcard = finaUserIdcard;
    }

    public String getFinaUserTel() {
        return finaUserTel;
    }

    public void setFinaUserTel(String finaUserTel) {
        this.finaUserTel = finaUserTel;
    }

    public List<FinaApplicantOrg> getFinaApplicantOrgList() {
        return finaApplicantOrgList;
    }

    public void setFinaApplicantOrgList(List<FinaApplicantOrg> finaApplicantOrgList) {
        this.finaApplicantOrgList = finaApplicantOrgList;
    }

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public FinaApplicant getFinaApplicant() {
        FinaApplicant finaApplicant = new FinaApplicant();
        finaApplicant.setFinaUserId(this.finaUserId);
        finaApplicant.setFinaUserName(this.finaUserName);
        finaApplicant.setFinaUserIdcard(this.finaUserIdcard);
        finaApplicant.setFinaUserTel(this.finaUserTel);
        setFinaApplicant(finaApplicant);
        return finaApplicant;
    }

    public void setFinaApplicant(FinaApplicant finaApplicant) {
        this.finaApplicant = finaApplicant;
    }

    public FinaApplicantMoney getFinaApplicantMoney() {
        FinaApplicantMoney finaApplicantMoney =  new FinaApplicantMoney();
        finaApplicantMoney.setEstimateMoney(this.estimateMoney);
        finaApplicantMoney.setProposalMoney(this.proposalMoney);
        finaApplicantMoney.setActualMoney(this.actualMoney);
        finaApplicantMoney.setRiskLevel(this.riskLevel);
        finaApplicantMoney.setRealLoanTime(this.realLoanTime);
        finaApplicantMoney.setConfirmType(this.confirmType);
        finaApplicantMoney.setCheckManId(this.checkManId);
        finaApplicantMoney.setCheckManName(this.checkManName);
        finaApplicantMoney.setDoubleCheckManId(this.doubleCheckManId);
        finaApplicantMoney.setDoubleCheckManName(this.doubleCheckManName);
        finaApplicantMoney.setCheckTime(this.checkTime);
        finaApplicantMoney.setDoubleCheckTime(this.doubleCheckTime);
        setFinaApplicantMoney(finaApplicantMoney);
        return finaApplicantMoney;
    }

    public void setFinaApplicantMoney(FinaApplicantMoney finaApplicantMoney) {
        this.finaApplicantMoney = finaApplicantMoney;
    }

    public Double getEstimateMoney() {
        return estimateMoney;
    }

    public void setEstimateMoney(Double estimateMoney) {
        this.estimateMoney = estimateMoney;
    }

    public Double getProposalMoney() {
        return proposalMoney;
    }

    public void setProposalMoney(Double proposalMoney) {
        this.proposalMoney = proposalMoney;
    }

    public Double getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(Double actualMoney) {
        this.actualMoney = actualMoney;
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Date getRealLoanTime() {
        return realLoanTime;
    }

    public void setRealLoanTime(Date realLoanTime) {
        this.realLoanTime = realLoanTime;
    }

    public Integer getConfirmType() {
        return confirmType;
    }

    public void setConfirmType(Integer confirmType) {
        this.confirmType = confirmType;
    }

    public Long getCheckManId() {
        return checkManId;
    }

    public void setCheckManId(Long checkManId) {
        this.checkManId = checkManId;
    }

    public String getCheckManName() {
        return checkManName;
    }

    public void setCheckManName(String checkManName) {
        this.checkManName = checkManName;
    }

    public Long getDoubleCheckManId() {
        return doubleCheckManId;
    }

    public void setDoubleCheckManId(Long doubleCheckManId) {
        this.doubleCheckManId = doubleCheckManId;
    }

    public String getDoubleCheckManName() {
        return doubleCheckManName;
    }

    public void setDoubleCheckManName(String doubleCheckManName) {
        this.doubleCheckManName = doubleCheckManName;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getDoubleCheckTime() {
        return doubleCheckTime;
    }

    public void setDoubleCheckTime(Date doubleCheckTime) {
        this.doubleCheckTime = doubleCheckTime;
    }

    public List<FinaApplicantInvestigator> getFinaApplicantInvestigatorList() {
        return finaApplicantInvestigatorList;
    }

    public void setFinaApplicantInvestigatorList(List<FinaApplicantInvestigator> finaApplicantInvestigatorList) {
        this.finaApplicantInvestigatorList = finaApplicantInvestigatorList;
    }

    public String getApplicantStateStr() {
        return applicantStateStr;
    }

    public void setApplicantStateStr(String applicantStateStr) {
        this.applicantStateStr = applicantStateStr;
    }

    public List<FinaApplicantTrack> getFinaApplicantTrackList() {
        return finaApplicantTrackList;
    }

    public void setFinaApplicantTrackList(List<FinaApplicantTrack> finaApplicantTrackList) {
        this.finaApplicantTrackList = finaApplicantTrackList;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public FinaDiagnosisTreatment getFinaDiagnosisTreatment() {
        return finaDiagnosisTreatment;
    }

    public void setFinaDiagnosisTreatment(FinaDiagnosisTreatment finaDiagnosisTreatment) {
        this.finaDiagnosisTreatment = finaDiagnosisTreatment;
    }

    public List<FinaDiagnosisTreatment> getFinaDiagnosisTreatmentList() {
        return finaDiagnosisTreatmentList;
    }

    public void setFinaDiagnosisTreatmentList(List<FinaDiagnosisTreatment> finaDiagnosisTreatmentList) {
        this.finaDiagnosisTreatmentList = finaDiagnosisTreatmentList;
    }

    public FinaHospitalAccount getFinaHospitalAccount() {
        return finaHospitalAccount;
    }

    public void setFinaHospitalAccount(FinaHospitalAccount finaHospitalAccount) {
        this.finaHospitalAccount = finaHospitalAccount;
    }

    public Integer getOutInsureType() {
        return outInsureType;
    }

    public void setOutInsureType(Integer outInsureType) {
        this.outInsureType = outInsureType;
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

    public Integer getFinaOrgState() {
        return finaOrgState;
    }

    public void setFinaOrgState(Integer finaOrgState) {
        this.finaOrgState = finaOrgState;
    }

    public Date getOrgAssignTime() {
        return orgAssignTime;
    }

    public void setOrgAssignTime(Date orgAssignTime) {
        this.orgAssignTime = orgAssignTime;
    }

    public Date getOrgSubmitTime() {
        return orgSubmitTime;
    }

    public void setOrgSubmitTime(Date orgSubmitTime) {
        this.orgSubmitTime = orgSubmitTime;
    }

    public Date getOrgEndTime() {
        return orgEndTime;
    }

    public void setOrgEndTime(Date orgEndTime) {
        this.orgEndTime = orgEndTime;
    }

    public Double getOrgAging() {
        return orgAging;
    }

    public void setOrgAging(Double orgAging) {
        this.orgAging = orgAging;
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

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public Date getUserAssignTime() {
        return userAssignTime;
    }

    public void setUserAssignTime(Date userAssignTime) {
        this.userAssignTime = userAssignTime;
    }

    public Date getUserSubmitTime() {
        return userSubmitTime;
    }

    public void setUserSubmitTime(Date userSubmitTime) {
        this.userSubmitTime = userSubmitTime;
    }

    public Date getUserEndTime() {
        return userEndTime;
    }

    public void setUserEndTime(Date userEndTime) {
        this.userEndTime = userEndTime;
    }

    public Double getUserAging() {
        return userAging;
    }

    public void setUserAging(Double userAging) {
        this.userAging = userAging;
    }

    public Long getApplicantOrgId() {
        return applicantOrgId;
    }

    public void setApplicantOrgId(Long applicantOrgId) {
        this.applicantOrgId = applicantOrgId;
    }

    public Long getApplicantInvestigatorId() {
        return applicantInvestigatorId;
    }

    public void setApplicantInvestigatorId(Long applicantInvestigatorId) {
        this.applicantInvestigatorId = applicantInvestigatorId;
    }

    public FinaApplicantInvestigator getFinaApplicantInvestigator() {
        return finaApplicantInvestigator;
    }

    public void setFinaApplicantInvestigator(FinaApplicantInvestigator finaApplicantInvestigator) {
        this.finaApplicantInvestigator = finaApplicantInvestigator;
    }

    public FinaApplicantOrg getFinaApplicantOrg() {
        return finaApplicantOrg;
    }

    public void setFinaApplicantOrg(FinaApplicantOrg finaApplicantOrg) {
        this.finaApplicantOrg = finaApplicantOrg;
    }

    public Integer getInsuredAge() {
        return insuredAge;
    }

    public void setInsuredAge(Integer insuredAge) {
        this.insuredAge = insuredAge;
    }

    public FinaHospitalInfo getFinaHospitalInfo() {
        return finaHospitalInfo;
    }

    public void setFinaHospitalInfo(FinaHospitalInfo finaHospitalInfo) {
        this.finaHospitalInfo = finaHospitalInfo;
    }

    public Boolean getAssignOrg() {
        return assignOrg;
    }

    public void setAssignOrg(Boolean assignOrg) {
        this.assignOrg = assignOrg;
    }

    public Boolean getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(Boolean assignUser) {
        this.assignUser = assignUser;
    }

    public FinaSettlementInfo getFinaSettlementInfo() {
        return finaSettlementInfo;
    }

    public void setFinaSettlementInfo(FinaSettlementInfo finaSettlementInfo) {
        this.finaSettlementInfo = finaSettlementInfo;
    }

    public Integer getApplicantFileNum() {
        return applicantFileNum;
    }

    public void setApplicantFileNum(Integer applicantFileNum) {
        this.applicantFileNum = applicantFileNum;
    }

    public Boolean getHaveAccount() {
        return haveAccount;
    }

    public void setHaveAccount(Boolean haveAccount) {
        this.haveAccount = haveAccount;
    }

    public Integer getProgressNum() {
        return progressNum;
    }

    public void setProgressNum(Integer progressNum) {
        this.progressNum = progressNum;
    }

    public FinaConfirmAccount getFinaConfirmAccount() {
        return finaConfirmAccount;
    }

    public void setFinaConfirmAccount(FinaConfirmAccount finaConfirmAccount) {
        this.finaConfirmAccount = finaConfirmAccount;
    }
}