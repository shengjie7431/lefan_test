package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

public class CaseMediationClaimLegal {
    private Long id;

    private Long userId;

    private String userName;

    private Long caseId;

    private Long claimerId;

    private String claimerName;

    private String cardNumber;

    private Long insuranceCompanyId;

    private String insuranceCompany;

    private Long insOfficerId;

    private String insOfficerTel;

    private String insOfficerName;

    private Date mediateDate;

    private Double loanMoney;

    private Date claimTime;

    private Date outInsuranceTime;

    private Double liabilityRatio;

    private Date arrivalTime;

    private Double cptMoney;

    private Double cpsMoney;

    private Double cocMoney;

    private Double finalLoanMoney;

    private Double repaymentMoney;

    private Double serviceMoney;

    private String mediateDesc;

    private String mediateFailDesc;

    private String partyName;

    private String partyTel;

    private String appStandType;

    private String accidentDutyType;

    private String determineType;

    private String invalidismGrade;

    private String hurtTrafficType;

    private String partyTrafficeType;

    private String payInsuranceType;

    private Double tradeAmount;

    private String someCarType;

    private String carDesc;

    private String disclaimerType;

    private String disclaimerDesc;

    private String caseDesc;

    private Date caseCommitTime;

    private String planReviewDesc;

    private String planReviewPerson;

    private Date planReviewTime;

    private String insurerReviewDesc;

    private String insurerReviewPerson;

    private Date insurerReviewTime;

    //2018年6月22日14:27:48  增加  支付伤者 支付驾驶员 支付保险公司金额
    private Double payWoundedMoney;
    private Double payDriverMoney;
    private Double paySafeMoney;

    private List<CaseMediationClaimReport> caseMediationClaimReports;

    private Integer stepCode;//用于回调时，代表需要展示的页码
    private Integer noNext;//用于回调时，判断页面是关闭、还是下一页

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getClaimerId() {
        return claimerId;
    }

    public void setClaimerId(Long claimerId) {
        this.claimerId = claimerId;
    }

    public String getClaimerName() {
        return claimerName;
    }

    public void setClaimerName(String claimerName) {
        this.claimerName = claimerName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public void setInsuranceCompanyId(Long insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public Long getInsOfficerId() {
        return insOfficerId;
    }

    public void setInsOfficerId(Long insOfficerId) {
        this.insOfficerId = insOfficerId;
    }

    public String getInsOfficerTel() {
        return insOfficerTel;
    }

    public void setInsOfficerTel(String insOfficerTel) {
        this.insOfficerTel = insOfficerTel;
    }

    public String getInsOfficerName() {
        return insOfficerName;
    }

    public void setInsOfficerName(String insOfficerName) {
        this.insOfficerName = insOfficerName;
    }

    public Date getMediateDate() {
        return mediateDate;
    }

    public void setMediateDate(Date mediateDate) {
        this.mediateDate = mediateDate;
    }

    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    public Date getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(Date claimTime) {
        this.claimTime = claimTime;
    }

    public Date getOutInsuranceTime() {
        return outInsuranceTime;
    }

    public void setOutInsuranceTime(Date outInsuranceTime) {
        this.outInsuranceTime = outInsuranceTime;
    }

    public Double getLiabilityRatio() {
        return liabilityRatio;
    }

    public void setLiabilityRatio(Double liabilityRatio) {
        this.liabilityRatio = liabilityRatio;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Double getCptMoney() {
        return cptMoney;
    }

    public void setCptMoney(Double cptMoney) {
        this.cptMoney = cptMoney;
    }

    public Double getCpsMoney() {
        return cpsMoney;
    }

    public void setCpsMoney(Double cpsMoney) {
        this.cpsMoney = cpsMoney;
    }

    public Double getCocMoney() {
        return cocMoney;
    }

    public void setCocMoney(Double cocMoney) {
        this.cocMoney = cocMoney;
    }

    public Double getFinalLoanMoney() {
        return finalLoanMoney;
    }

    public void setFinalLoanMoney(Double finalLoanMoney) {
        this.finalLoanMoney = finalLoanMoney;
    }

    public Double getRepaymentMoney() {
        return repaymentMoney;
    }

    public void setRepaymentMoney(Double repaymentMoney) {
        this.repaymentMoney = repaymentMoney;
    }

    public Double getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(Double serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

    public String getMediateDesc() {
        return mediateDesc;
    }

    public void setMediateDesc(String mediateDesc) {
        this.mediateDesc = mediateDesc;
    }

    public String getMediateFailDesc() {
        return mediateFailDesc;
    }

    public void setMediateFailDesc(String mediateFailDesc) {
        this.mediateFailDesc = mediateFailDesc;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyTel() {
        return partyTel;
    }

    public void setPartyTel(String partyTel) {
        this.partyTel = partyTel;
    }

    public String getAppStandType() {
        return appStandType;
    }

    public void setAppStandType(String appStandType) {
        this.appStandType = appStandType;
    }

    public String getAccidentDutyType() {
        return accidentDutyType;
    }

    public void setAccidentDutyType(String accidentDutyType) {
        this.accidentDutyType = accidentDutyType;
    }

    public String getDetermineType() {
        return determineType;
    }

    public void setDetermineType(String determineType) {
        this.determineType = determineType;
    }

    public String getInvalidismGrade() {
        return invalidismGrade;
    }

    public void setInvalidismGrade(String invalidismGrade) {
        this.invalidismGrade = invalidismGrade;
    }

    public String getHurtTrafficType() {
        return hurtTrafficType;
    }

    public void setHurtTrafficType(String hurtTrafficType) {
        this.hurtTrafficType = hurtTrafficType;
    }

    public String getPartyTrafficeType() {
        return partyTrafficeType;
    }

    public void setPartyTrafficeType(String partyTrafficeType) {
        this.partyTrafficeType = partyTrafficeType;
    }

    public String getPayInsuranceType() {
        return payInsuranceType;
    }

    public void setPayInsuranceType(String payInsuranceType) {
        this.payInsuranceType = payInsuranceType;
    }

    public Double getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(Double tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getSomeCarType() {
        return someCarType;
    }

    public void setSomeCarType(String someCarType) {
        this.someCarType = someCarType;
    }

    public String getCarDesc() {
        return carDesc;
    }

    public void setCarDesc(String carDesc) {
        this.carDesc = carDesc;
    }

    public String getDisclaimerType() {
        return disclaimerType;
    }

    public void setDisclaimerType(String disclaimerType) {
        this.disclaimerType = disclaimerType;
    }

    public String getDisclaimerDesc() {
        return disclaimerDesc;
    }

    public void setDisclaimerDesc(String disclaimerDesc) {
        this.disclaimerDesc = disclaimerDesc;
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    public Date getCaseCommitTime() {
        return caseCommitTime;
    }

    public void setCaseCommitTime(Date caseCommitTime) {
        this.caseCommitTime = caseCommitTime;
    }

    public String getPlanReviewDesc() {
        return planReviewDesc;
    }

    public void setPlanReviewDesc(String planReviewDesc) {
        this.planReviewDesc = planReviewDesc;
    }

    public String getPlanReviewPerson() {
        return planReviewPerson;
    }

    public void setPlanReviewPerson(String planReviewPerson) {
        this.planReviewPerson = planReviewPerson;
    }

    public Date getPlanReviewTime() {
        return planReviewTime;
    }

    public void setPlanReviewTime(Date planReviewTime) {
        this.planReviewTime = planReviewTime;
    }

    public String getInsurerReviewDesc() {
        return insurerReviewDesc;
    }

    public void setInsurerReviewDesc(String insurerReviewDesc) {
        this.insurerReviewDesc = insurerReviewDesc;
    }

    public String getInsurerReviewPerson() {
        return insurerReviewPerson;
    }

    public void setInsurerReviewPerson(String insurerReviewPerson) {
        this.insurerReviewPerson = insurerReviewPerson;
    }

    public Date getInsurerReviewTime() {
        return insurerReviewTime;
    }

    public void setInsurerReviewTime(Date insurerReviewTime) {
        this.insurerReviewTime = insurerReviewTime;
    }

    public List<CaseMediationClaimReport> getCaseMediationClaimReports() {
        return caseMediationClaimReports;
    }

    public void setCaseMediationClaimReports(List<CaseMediationClaimReport> caseMediationClaimReports) {
        this.caseMediationClaimReports = caseMediationClaimReports;
    }

    public Double getPayWoundedMoney() {
        return payWoundedMoney;
    }

    public void setPayWoundedMoney(Double payWoundedMoney) {
        this.payWoundedMoney = payWoundedMoney;
    }

    public Double getPayDriverMoney() {
        return payDriverMoney;
    }

    public void setPayDriverMoney(Double payDriverMoney) {
        this.payDriverMoney = payDriverMoney;
    }

    public Double getPaySafeMoney() {
        return paySafeMoney;
    }

    public void setPaySafeMoney(Double paySafeMoney) {
        this.paySafeMoney = paySafeMoney;
    }

    public Integer getStepCode() {
        return stepCode;
    }

    public void setStepCode(Integer stepCode) {
        this.stepCode = stepCode;
    }

    public Integer getNoNext() {
        return noNext;
    }

    public void setNoNext(Integer noNext) {
        this.noNext = noNext;
    }
}