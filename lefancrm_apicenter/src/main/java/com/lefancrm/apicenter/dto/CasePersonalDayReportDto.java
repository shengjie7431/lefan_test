package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.util.DecimalUtil;

import java.util.Date;

public class CasePersonalDayReportDto {
    private Long id;

    private Long orgId;

    private String orgName;

    private Date date;

    private Long directorId;

    private String directorName;

    private Long salesmanId;

    private String salesmanName;

    private Integer isDisability;

    private Integer agentSignNum;

    private Double agentServiceMoney;

    private Double advanceAgentServiceMoney;

    private Double okAgentServiceMoney;

    private Integer loanSignNum;

    private Double loanServiceMoney;

    private Double loanChannelMoney;

    private Double advanceLoanServiceMoney;

    private Double okLoanServiceMoney;

    private Integer destroyCaseNum;

    private Integer newCaseNum;

    private Double newServiceMoney;

    private Integer totalSignNum;

    private Double totalServiceMoney;

    private Double totalOkServiceMoney;

    private Double totalAdServiceMoney;

    private Integer caseInputNum;

    private Integer caseTargetNum;

    private Integer casePotentialNum;

    private Integer caseIntentionNum;

    private Integer caseSignNum;

    private Double targetToPotRate;

    private Double potToInteRate;

    private Double inteToSignRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public Integer getIsDisability() {
        return isDisability;
    }

    public void setIsDisability(Integer isDisability) {
        this.isDisability = isDisability;
    }

    public Integer getAgentSignNum() {
        return agentSignNum;
    }

    public void setAgentSignNum(Integer agentSignNum) {
        this.agentSignNum = agentSignNum;
    }

    public Double getAgentServiceMoney() {
        return agentServiceMoney;
    }

    public void setAgentServiceMoney(Double agentServiceMoney) {
        this.agentServiceMoney = agentServiceMoney;
    }

    public Double getAdvanceAgentServiceMoney() {
        return advanceAgentServiceMoney;
    }

    public void setAdvanceAgentServiceMoney(Double advanceAgentServiceMoney) {
        this.advanceAgentServiceMoney = advanceAgentServiceMoney;
    }

    public Integer getLoanSignNum() {
        return loanSignNum;
    }

    public void setLoanSignNum(Integer loanSignNum) {
        this.loanSignNum = loanSignNum;
    }

    public Double getLoanServiceMoney() {
        return loanServiceMoney;
    }

    public void setLoanServiceMoney(Double loanServiceMoney) {
        this.loanServiceMoney = loanServiceMoney;
    }

    public Double getLoanChannelMoney() {
        return loanChannelMoney;
    }

    public void setLoanChannelMoney(Double loanChannelMoney) {
        this.loanChannelMoney = loanChannelMoney;
    }

    public Double getAdvanceLoanServiceMoney() {
        return advanceLoanServiceMoney;
    }

    public void setAdvanceLoanServiceMoney(Double advanceLoanServiceMoney) {
        this.advanceLoanServiceMoney = advanceLoanServiceMoney;
    }

    public Integer getDestroyCaseNum() {
        return destroyCaseNum;
    }

    public void setDestroyCaseNum(Integer destroyCaseNum) {
        this.destroyCaseNum = destroyCaseNum;
    }

    public Integer getNewCaseNum() {
        return newCaseNum;
    }

    public void setNewCaseNum(Integer newCaseNum) {
        this.newCaseNum = newCaseNum;
    }

    public Double getNewServiceMoney() {
        return newServiceMoney;
    }

    public void setNewServiceMoney(Double newServiceMoney) {
        this.newServiceMoney = newServiceMoney;
    }

    public Integer getTotalSignNum() {
        return totalSignNum;
    }

    public void setTotalSignNum(Integer totalSignNum) {
        this.totalSignNum = totalSignNum;
    }

    public Double getTotalServiceMoney() {
        return totalServiceMoney;
    }

    public void setTotalServiceMoney(Double totalServiceMoney) {
        this.totalServiceMoney = totalServiceMoney;
    }

    public Double getTotalOkServiceMoney() {
        return totalOkServiceMoney;
    }

    public void setTotalOkServiceMoney(Double totalOkServiceMoney) {
        this.totalOkServiceMoney = totalOkServiceMoney;
    }

    public Double getTotalAdServiceMoney() {
        return totalAdServiceMoney;
    }

    public void setTotalAdServiceMoney(Double totalAdServiceMoney) {
        this.totalAdServiceMoney = totalAdServiceMoney;
    }

    public Integer getCaseInputNum() {
        return caseInputNum;
    }

    public void setCaseInputNum(Integer caseInputNum) {
        this.caseInputNum = caseInputNum;
    }

    public Integer getCaseTargetNum() {
        return caseTargetNum;
    }

    public void setCaseTargetNum(Integer caseTargetNum) {
        this.caseTargetNum = caseTargetNum;
    }

    public Integer getCasePotentialNum() {
        return casePotentialNum;
    }

    public void setCasePotentialNum(Integer casePotentialNum) {
        this.casePotentialNum = casePotentialNum;
    }

    public Integer getCaseIntentionNum() {
        return caseIntentionNum;
    }

    public void setCaseIntentionNum(Integer caseIntentionNum) {
        this.caseIntentionNum = caseIntentionNum;
    }

    public Integer getCaseSignNum() {
        return caseSignNum;
    }

    public void setCaseSignNum(Integer caseSignNum) {
        this.caseSignNum = caseSignNum;
    }

    public Double getTargetToPotRate() {
        return targetToPotRate;
    }

    public void setTargetToPotRate(Double targetToPotRate) {
        this.targetToPotRate = DecimalUtil.twoDecimalTOFourFromFive(targetToPotRate == null ? 0 : targetToPotRate);
    }

    public Double getPotToInteRate() {
        return potToInteRate;
    }

    public void setPotToInteRate(Double potToInteRate) {
        this.potToInteRate = DecimalUtil.twoDecimalTOFourFromFive(potToInteRate == null ? 0 : potToInteRate);
    }

    public Double getInteToSignRate() {
        return inteToSignRate;
    }

    public void setInteToSignRate(Double inteToSignRate) {
        this.inteToSignRate = DecimalUtil.twoDecimalTOFourFromFive(inteToSignRate == null ? 0 : inteToSignRate);
    }

    public Double getOkAgentServiceMoney() {
        return okAgentServiceMoney;
    }

    public void setOkAgentServiceMoney(Double okAgentServiceMoney) {
        this.okAgentServiceMoney = okAgentServiceMoney;
    }

    public Double getOkLoanServiceMoney() {
        return okLoanServiceMoney;
    }

    public void setOkLoanServiceMoney(Double okLoanServiceMoney) {
        this.okLoanServiceMoney = okLoanServiceMoney;
    }
}