package com.lefancrm.apicenter.model;

import java.util.Date;

public class CaseClosedReport {
    private Long id;

    private Long caseId;

    private String caseNo;

    private String clienteleName;

    private Long clienteleId;

    private Long orgId;

    private String orgName;

    private Integer entrustType;

    private Date entrustTime;

    private Integer closedType;

    private Double compensateRate;

    private Long claimantId;

    private String claimantName;

    private String claimantPre;

    private String agentFirm;

    private String agentLawyer;

    private Double compensateMoney;

    private Double compulsoryMoney;

    private Double commercialMoney;

    private Double driverMoney;

    private Double injuredMoney;

    private Double fixedMoney;

    private Integer isFixed;

    private Double proMoney;

    private Integer isPro;

    private String chargeDesc;

    private Double includedTotalMoney;

    private Double shouldTotalMoney;

    private Double actualTotalMoney;

    private String isDeductedLoan;

    private Double deductedLoanMoney;

    private Double loanMoney;

    private Integer isPre;

    private Double preMoney;

    private Integer loanType;

    private Integer loanCycle;

    private Date backTime;

    private Double insuranceMoney;

    private Double interestMoney;

    private Double lawyerMoney;

    private Double appraisalMoney;

    private Double litigateMoney;

    private Double preserveMoney;

    private String otherDesc;

    private String moneyDesc;

    private String financeSign;

    private String riskSign;

    private Date financeSignTime;

    private Date riskSignTime;

    private Date claimBeginTime;

    private String legalUser;

    private Long legalUserId;

    private Date legalBeginTime;

    private Double agentLawyerMoney;

    private Double loanChannelRate;

    private Double loanChannelMoney;

    private Double surplusTotalMoney;

    private Double claimMoney;

    private Double otherMoney;

    private Double closedReportDeMoney;

    private Integer chargeType;

    private Double fixedChargeMoney;

    private Double proportionChargeMoney;

    private Double proportionChargeRate;

    private Double blendFixedChargeMoney;

    private Double blendProportionChargeMoney;

    private Double blendProportionChargeRate;

    //便于保存开户行等信息
    private String lawyerMoneyAccName;
    private String lawyerMoneyBankName;
    private String lawyerMoneyCardNo;
    private String appraisalMoneyAccName;
    private String appraisalMoneyBankName;
    private String appraisalMoneyCardNo;
    private String litigateMoneyAccName;
    private String litigateMoneyBankName;
    private String litigateMoneyCardNo;
    private String claimMoneyAccName;
    private String claimMoneyBankName;
    private String claimMoneyCardNo;
    private String otherMoneyAccName;
    private String otherMoneyBankName;
    private String otherMoneyCardNo;

    //2018年6月22日14:27:48  增加  支付伤者 支付驾驶员 支付保险公司金额
    private Double payWoundedMoney;
    private Double payDriverMoney;
    private Double paySafeMoney;
    private Integer stageType;//结案阶段 1一审  2二审

    private String lawyerMoneyRemarks;

    private String appraisalMoneyRemarks;

    private String litigateMoneyRemarks;

    private String claimMoneyRemarks;

    private String otherMoneyRemarks;

    private Integer stepCode;//用于回调时，代表需要展示的页码
    private Integer noNext;//用于回调时，判断页面是关闭、还是下一页

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

    public String getClienteleName() {
        return clienteleName;
    }

    public void setClienteleName(String clienteleName) {
        this.clienteleName = clienteleName;
    }

    public Long getClienteleId() {
        return clienteleId;
    }

    public void setClienteleId(Long clienteleId) {
        this.clienteleId = clienteleId;
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

    public Integer getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(Integer entrustType) {
        this.entrustType = entrustType;
    }

    public Date getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(Date entrustTime) {
        this.entrustTime = entrustTime;
    }

    public Integer getClosedType() {
        return closedType;
    }

    public void setClosedType(Integer closedType) {
        this.closedType = closedType;
    }

    public Double getCompensateRate() {
        return compensateRate;
    }

    public void setCompensateRate(Double compensateRate) {
        this.compensateRate = compensateRate;
    }

    public Long getClaimantId() {
        return claimantId;
    }

    public void setClaimantId(Long claimantId) {
        this.claimantId = claimantId;
    }

    public String getClaimantName() {
        return claimantName;
    }

    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    public String getClaimantPre() {
        return claimantPre;
    }

    public void setClaimantPre(String claimantPre) {
        this.claimantPre = claimantPre;
    }

    public String getAgentFirm() {
        return agentFirm;
    }

    public void setAgentFirm(String agentFirm) {
        this.agentFirm = agentFirm;
    }

    public String getAgentLawyer() {
        return agentLawyer;
    }

    public void setAgentLawyer(String agentLawyer) {
        this.agentLawyer = agentLawyer;
    }

    public Double getCompensateMoney() {
        return compensateMoney;
    }

    public void setCompensateMoney(Double compensateMoney) {
        this.compensateMoney = compensateMoney;
    }

    public Double getCompulsoryMoney() {
        return compulsoryMoney;
    }

    public void setCompulsoryMoney(Double compulsoryMoney) {
        this.compulsoryMoney = compulsoryMoney;
    }

    public Double getCommercialMoney() {
        return commercialMoney;
    }

    public void setCommercialMoney(Double commercialMoney) {
        this.commercialMoney = commercialMoney;
    }

    public Double getDriverMoney() {
        return driverMoney;
    }

    public void setDriverMoney(Double driverMoney) {
        this.driverMoney = driverMoney;
    }

    public Double getInjuredMoney() {
        return injuredMoney;
    }

    public void setInjuredMoney(Double injuredMoney) {
        this.injuredMoney = injuredMoney;
    }

    public Double getFixedMoney() {
        return fixedMoney;
    }

    public void setFixedMoney(Double fixedMoney) {
        this.fixedMoney = fixedMoney;
    }

    public Integer getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(Integer isFixed) {
        this.isFixed = isFixed;
    }

    public Double getProMoney() {
        return proMoney;
    }

    public void setProMoney(Double proMoney) {
        this.proMoney = proMoney;
    }

    public Integer getIsPro() {
        return isPro;
    }

    public void setIsPro(Integer isPro) {
        this.isPro = isPro;
    }

    public String getChargeDesc() {
        return chargeDesc;
    }

    public void setChargeDesc(String chargeDesc) {
        this.chargeDesc = chargeDesc;
    }

    public Double getIncludedTotalMoney() {
        return includedTotalMoney;
    }

    public void setIncludedTotalMoney(Double includedTotalMoney) {
        this.includedTotalMoney = includedTotalMoney;
    }

    public Double getShouldTotalMoney() {
        return shouldTotalMoney;
    }

    public void setShouldTotalMoney(Double shouldTotalMoney) {
        this.shouldTotalMoney = shouldTotalMoney;
    }

    public Double getActualTotalMoney() {
        return actualTotalMoney;
    }

    public void setActualTotalMoney(Double actualTotalMoney) {
        this.actualTotalMoney = actualTotalMoney;
    }

    public String getIsDeductedLoan() {
        return isDeductedLoan;
    }

    public void setIsDeductedLoan(String isDeductedLoan) {
        this.isDeductedLoan = isDeductedLoan;
    }

    public Double getDeductedLoanMoney() {
        return deductedLoanMoney;
    }

    public void setDeductedLoanMoney(Double deductedLoanMoney) {
        this.deductedLoanMoney = deductedLoanMoney;
    }

    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    public Integer getIsPre() {
        return isPre;
    }

    public void setIsPre(Integer isPre) {
        this.isPre = isPre;
    }

    public Double getPreMoney() {
        return preMoney;
    }

    public void setPreMoney(Double preMoney) {
        this.preMoney = preMoney;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public Integer getLoanCycle() {
        return loanCycle;
    }

    public void setLoanCycle(Integer loanCycle) {
        this.loanCycle = loanCycle;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public Double getInsuranceMoney() {
        return insuranceMoney;
    }

    public void setInsuranceMoney(Double insuranceMoney) {
        this.insuranceMoney = insuranceMoney;
    }

    public Double getInterestMoney() {
        return interestMoney;
    }

    public void setInterestMoney(Double interestMoney) {
        this.interestMoney = interestMoney;
    }

    public Double getLawyerMoney() {
        return lawyerMoney;
    }

    public void setLawyerMoney(Double lawyerMoney) {
        this.lawyerMoney = lawyerMoney;
    }

    public Double getAppraisalMoney() {
        return appraisalMoney;
    }

    public void setAppraisalMoney(Double appraisalMoney) {
        this.appraisalMoney = appraisalMoney;
    }

    public Double getLitigateMoney() {
        return litigateMoney;
    }

    public void setLitigateMoney(Double litigateMoney) {
        this.litigateMoney = litigateMoney;
    }

    public Double getPreserveMoney() {
        return preserveMoney;
    }

    public void setPreserveMoney(Double preserveMoney) {
        this.preserveMoney = preserveMoney;
    }

    public String getOtherDesc() {
        return otherDesc;
    }

    public void setOtherDesc(String otherDesc) {
        this.otherDesc = otherDesc;
    }

    public String getMoneyDesc() {
        return moneyDesc;
    }

    public void setMoneyDesc(String moneyDesc) {
        this.moneyDesc = moneyDesc;
    }

    public String getFinanceSign() {
        return financeSign;
    }

    public void setFinanceSign(String financeSign) {
        this.financeSign = financeSign;
    }

    public String getRiskSign() {
        return riskSign;
    }

    public void setRiskSign(String riskSign) {
        this.riskSign = riskSign;
    }

    public Date getFinanceSignTime() {
        return financeSignTime;
    }

    public void setFinanceSignTime(Date financeSignTime) {
        this.financeSignTime = financeSignTime;
    }

    public Date getRiskSignTime() {
        return riskSignTime;
    }

    public void setRiskSignTime(Date riskSignTime) {
        this.riskSignTime = riskSignTime;
    }

    public Date getClaimBeginTime() {
        return claimBeginTime;
    }

    public void setClaimBeginTime(Date claimBeginTime) {
        this.claimBeginTime = claimBeginTime;
    }

    public String getLegalUser() {
        return legalUser;
    }

    public void setLegalUser(String legalUser) {
        this.legalUser = legalUser;
    }

    public Long getLegalUserId() {
        return legalUserId;
    }

    public void setLegalUserId(Long legalUserId) {
        this.legalUserId = legalUserId;
    }

    public Date getLegalBeginTime() {
        return legalBeginTime;
    }

    public void setLegalBeginTime(Date legalBeginTime) {
        this.legalBeginTime = legalBeginTime;
    }

    public Double getAgentLawyerMoney() {
        return agentLawyerMoney;
    }

    public void setAgentLawyerMoney(Double agentLawyerMoney) {
        this.agentLawyerMoney = agentLawyerMoney;
    }

    public Double getLoanChannelRate() {
        return loanChannelRate;
    }

    public void setLoanChannelRate(Double loanChannelRate) {
        this.loanChannelRate = loanChannelRate;
    }

    public Double getLoanChannelMoney() {
        return loanChannelMoney;
    }

    public void setLoanChannelMoney(Double loanChannelMoney) {
        this.loanChannelMoney = loanChannelMoney;
    }

    public Double getSurplusTotalMoney() {
        return surplusTotalMoney;
    }

    public void setSurplusTotalMoney(Double surplusTotalMoney) {
        this.surplusTotalMoney = surplusTotalMoney;
    }

    public Double getClaimMoney() {
        return claimMoney;
    }

    public void setClaimMoney(Double claimMoney) {
        this.claimMoney = claimMoney;
    }

    public Double getOtherMoney() {
        return otherMoney;
    }

    public void setOtherMoney(Double otherMoney) {
        this.otherMoney = otherMoney;
    }

    public Double getClosedReportDeMoney() {
        return closedReportDeMoney;
    }

    public void setClosedReportDeMoney(Double closedReportDeMoney) {
        this.closedReportDeMoney = closedReportDeMoney;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public Double getFixedChargeMoney() {
        return fixedChargeMoney;
    }

    public void setFixedChargeMoney(Double fixedChargeMoney) {
        this.fixedChargeMoney = fixedChargeMoney;
    }

    public Double getProportionChargeMoney() {
        return proportionChargeMoney;
    }

    public void setProportionChargeMoney(Double proportionChargeMoney) {
        this.proportionChargeMoney = proportionChargeMoney;
    }

    public Double getProportionChargeRate() {
        return proportionChargeRate;
    }

    public void setProportionChargeRate(Double proportionChargeRate) {
        this.proportionChargeRate = proportionChargeRate;
    }

    public Double getBlendFixedChargeMoney() {
        return blendFixedChargeMoney;
    }

    public void setBlendFixedChargeMoney(Double blendFixedChargeMoney) {
        this.blendFixedChargeMoney = blendFixedChargeMoney;
    }

    public Double getBlendProportionChargeMoney() {
        return blendProportionChargeMoney;
    }

    public void setBlendProportionChargeMoney(Double blendProportionChargeMoney) {
        this.blendProportionChargeMoney = blendProportionChargeMoney;
    }

    public Double getBlendProportionChargeRate() {
        return blendProportionChargeRate;
    }

    public void setBlendProportionChargeRate(Double blendProportionChargeRate) {
        this.blendProportionChargeRate = blendProportionChargeRate;
    }

    public String getLawyerMoneyAccName() {
        return lawyerMoneyAccName;
    }

    public void setLawyerMoneyAccName(String lawyerMoneyAccName) {
        this.lawyerMoneyAccName = lawyerMoneyAccName;
    }

    public String getLawyerMoneyBankName() {
        return lawyerMoneyBankName;
    }

    public void setLawyerMoneyBankName(String lawyerMoneyBankName) {
        this.lawyerMoneyBankName = lawyerMoneyBankName;
    }

    public String getLawyerMoneyCardNo() {
        return lawyerMoneyCardNo;
    }

    public void setLawyerMoneyCardNo(String lawyerMoneyCardNo) {
        this.lawyerMoneyCardNo = lawyerMoneyCardNo;
    }

    public String getAppraisalMoneyAccName() {
        return appraisalMoneyAccName;
    }

    public void setAppraisalMoneyAccName(String appraisalMoneyAccName) {
        this.appraisalMoneyAccName = appraisalMoneyAccName;
    }

    public String getAppraisalMoneyBankName() {
        return appraisalMoneyBankName;
    }

    public void setAppraisalMoneyBankName(String appraisalMoneyBankName) {
        this.appraisalMoneyBankName = appraisalMoneyBankName;
    }

    public String getAppraisalMoneyCardNo() {
        return appraisalMoneyCardNo;
    }

    public void setAppraisalMoneyCardNo(String appraisalMoneyCardNo) {
        this.appraisalMoneyCardNo = appraisalMoneyCardNo;
    }

    public String getLitigateMoneyAccName() {
        return litigateMoneyAccName;
    }

    public void setLitigateMoneyAccName(String litigateMoneyAccName) {
        this.litigateMoneyAccName = litigateMoneyAccName;
    }

    public String getLitigateMoneyBankName() {
        return litigateMoneyBankName;
    }

    public void setLitigateMoneyBankName(String litigateMoneyBankName) {
        this.litigateMoneyBankName = litigateMoneyBankName;
    }

    public String getLitigateMoneyCardNo() {
        return litigateMoneyCardNo;
    }

    public void setLitigateMoneyCardNo(String litigateMoneyCardNo) {
        this.litigateMoneyCardNo = litigateMoneyCardNo;
    }

    public String getClaimMoneyAccName() {
        return claimMoneyAccName;
    }

    public void setClaimMoneyAccName(String claimMoneyAccName) {
        this.claimMoneyAccName = claimMoneyAccName;
    }

    public String getClaimMoneyBankName() {
        return claimMoneyBankName;
    }

    public void setClaimMoneyBankName(String claimMoneyBankName) {
        this.claimMoneyBankName = claimMoneyBankName;
    }

    public String getClaimMoneyCardNo() {
        return claimMoneyCardNo;
    }

    public void setClaimMoneyCardNo(String claimMoneyCardNo) {
        this.claimMoneyCardNo = claimMoneyCardNo;
    }

    public String getOtherMoneyAccName() {
        return otherMoneyAccName;
    }

    public void setOtherMoneyAccName(String otherMoneyAccName) {
        this.otherMoneyAccName = otherMoneyAccName;
    }

    public String getOtherMoneyBankName() {
        return otherMoneyBankName;
    }

    public void setOtherMoneyBankName(String otherMoneyBankName) {
        this.otherMoneyBankName = otherMoneyBankName;
    }

    public String getOtherMoneyCardNo() {
        return otherMoneyCardNo;
    }

    public void setOtherMoneyCardNo(String otherMoneyCardNo) {
        this.otherMoneyCardNo = otherMoneyCardNo;
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

    public Integer getStageType() {
        return stageType;
    }

    public void setStageType(Integer stageType) {
        this.stageType = stageType;
    }

    public String getLawyerMoneyRemarks() {
        return lawyerMoneyRemarks;
    }

    public void setLawyerMoneyRemarks(String lawyerMoneyRemarks) {
        this.lawyerMoneyRemarks = lawyerMoneyRemarks;
    }

    public String getAppraisalMoneyRemarks() {
        return appraisalMoneyRemarks;
    }

    public void setAppraisalMoneyRemarks(String appraisalMoneyRemarks) {
        this.appraisalMoneyRemarks = appraisalMoneyRemarks;
    }

    public String getLitigateMoneyRemarks() {
        return litigateMoneyRemarks;
    }

    public void setLitigateMoneyRemarks(String litigateMoneyRemarks) {
        this.litigateMoneyRemarks = litigateMoneyRemarks;
    }

    public String getClaimMoneyRemarks() {
        return claimMoneyRemarks;
    }

    public void setClaimMoneyRemarks(String claimMoneyRemarks) {
        this.claimMoneyRemarks = claimMoneyRemarks;
    }

    public String getOtherMoneyRemarks() {
        return otherMoneyRemarks;
    }

    public void setOtherMoneyRemarks(String otherMoneyRemarks) {
        this.otherMoneyRemarks = otherMoneyRemarks;
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