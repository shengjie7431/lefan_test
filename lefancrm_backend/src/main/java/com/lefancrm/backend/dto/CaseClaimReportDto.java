package com.lefancrm.backend.dto;

import java.util.Date;

public class CaseClaimReportDto {
    private Long id;

    private Integer orgId;

    private String orgName;

    private Date date;

    private Integer totalNum;

    private Integer unclosedAgentNum;

    private Integer unclosedLoanNum;

    private Integer closedAgentNum;

    private Integer closedLoanNum;

    private Integer mediateClosedNum;

    private Integer litigationClosedNum;

    private Integer mediateClosedDayNum;

    private Integer litigationClosedDayNum;

    private Integer mediateClosedMonthNum;

    private Integer litigationClosedMonthNum;

    private Double returnServiceDayMoney;

    private Double returnPrincipalDayMoney;

    private Double returnInterestDayMoney;

    private Double returnServiceMonthMoney;

    private Double returnPrincipalMonthMoney;

    private Double returnInterestMonthMoney;

    private Integer releaseAgentNum;

    private Integer releaseLoanNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
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

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getUnclosedAgentNum() {
        return unclosedAgentNum;
    }

    public void setUnclosedAgentNum(Integer unclosedAgentNum) {
        this.unclosedAgentNum = unclosedAgentNum;
    }

    public Integer getUnclosedLoanNum() {
        return unclosedLoanNum;
    }

    public void setUnclosedLoanNum(Integer unclosedLoanNum) {
        this.unclosedLoanNum = unclosedLoanNum;
    }

    public Integer getClosedAgentNum() {
        return closedAgentNum;
    }

    public void setClosedAgentNum(Integer closedAgentNum) {
        this.closedAgentNum = closedAgentNum;
    }

    public Integer getClosedLoanNum() {
        return closedLoanNum;
    }

    public void setClosedLoanNum(Integer closedLoanNum) {
        this.closedLoanNum = closedLoanNum;
    }

    public Integer getMediateClosedNum() {
        return mediateClosedNum;
    }

    public void setMediateClosedNum(Integer mediateClosedNum) {
        this.mediateClosedNum = mediateClosedNum;
    }

    public Integer getLitigationClosedNum() {
        return litigationClosedNum;
    }

    public void setLitigationClosedNum(Integer litigationClosedNum) {
        this.litigationClosedNum = litigationClosedNum;
    }

    public Integer getMediateClosedDayNum() {
        return mediateClosedDayNum;
    }

    public void setMediateClosedDayNum(Integer mediateClosedDayNum) {
        this.mediateClosedDayNum = mediateClosedDayNum;
    }

    public Integer getLitigationClosedDayNum() {
        return litigationClosedDayNum;
    }

    public void setLitigationClosedDayNum(Integer litigationClosedDayNum) {
        this.litigationClosedDayNum = litigationClosedDayNum;
    }

    public Integer getMediateClosedMonthNum() {
        return mediateClosedMonthNum;
    }

    public void setMediateClosedMonthNum(Integer mediateClosedMonthNum) {
        this.mediateClosedMonthNum = mediateClosedMonthNum;
    }

    public Integer getLitigationClosedMonthNum() {
        return litigationClosedMonthNum;
    }

    public void setLitigationClosedMonthNum(Integer litigationClosedMonthNum) {
        this.litigationClosedMonthNum = litigationClosedMonthNum;
    }

    public Double getReturnServiceDayMoney() {
        return returnServiceDayMoney;
    }

    public void setReturnServiceDayMoney(Double returnServiceDayMoney) {
        this.returnServiceDayMoney = returnServiceDayMoney;
    }

    public Double getReturnPrincipalDayMoney() {
        return returnPrincipalDayMoney;
    }

    public void setReturnPrincipalDayMoney(Double returnPrincipalDayMoney) {
        this.returnPrincipalDayMoney = returnPrincipalDayMoney;
    }

    public Double getReturnInterestDayMoney() {
        return returnInterestDayMoney;
    }

    public void setReturnInterestDayMoney(Double returnInterestDayMoney) {
        this.returnInterestDayMoney = returnInterestDayMoney;
    }

    public Double getReturnServiceMonthMoney() {
        return returnServiceMonthMoney;
    }

    public void setReturnServiceMonthMoney(Double returnServiceMonthMoney) {
        this.returnServiceMonthMoney = returnServiceMonthMoney;
    }

    public Double getReturnPrincipalMonthMoney() {
        return returnPrincipalMonthMoney;
    }

    public void setReturnPrincipalMonthMoney(Double returnPrincipalMonthMoney) {
        this.returnPrincipalMonthMoney = returnPrincipalMonthMoney;
    }

    public Double getReturnInterestMonthMoney() {
        return returnInterestMonthMoney;
    }

    public void setReturnInterestMonthMoney(Double returnInterestMonthMoney) {
        this.returnInterestMonthMoney = returnInterestMonthMoney;
    }

    public Integer getReleaseAgentNum() {
        return releaseAgentNum;
    }

    public void setReleaseAgentNum(Integer releaseAgentNum) {
        this.releaseAgentNum = releaseAgentNum;
    }

    public Integer getReleaseLoanNum() {
        return releaseLoanNum;
    }

    public void setReleaseLoanNum(Integer releaseLoanNum) {
        this.releaseLoanNum = releaseLoanNum;
    }
}