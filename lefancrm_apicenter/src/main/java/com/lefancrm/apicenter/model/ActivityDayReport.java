package com.lefancrm.apicenter.model;

import java.util.Date;

public class ActivityDayReport {
    private Long id;

    private Long userId;

    private String userName;

    private Long orgId;

    private String orgName;

    private Double saleAmount;

    private Double saleGoal;

    private Integer completionRate;

    private Integer visitNum;

    private Integer targetNum;

    private Integer intentionNum;

    private Integer signNum;

    private Integer closedNum;

    private Date salesDate;

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

    public Double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Double getSaleGoal() {
        return saleGoal;
    }

    public void setSaleGoal(Double saleGoal) {
        this.saleGoal = saleGoal;
    }

    public Integer getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(Integer completionRate) {
        this.completionRate = completionRate;
    }

    public Integer getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(Integer visitNum) {
        this.visitNum = visitNum;
    }

    public Integer getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(Integer targetNum) {
        this.targetNum = targetNum;
    }

    public Integer getIntentionNum() {
        return intentionNum;
    }

    public void setIntentionNum(Integer intentionNum) {
        this.intentionNum = intentionNum;
    }

    public Integer getSignNum() {
        return signNum;
    }

    public void setSignNum(Integer signNum) {
        this.signNum = signNum;
    }

    public Integer getClosedNum() {
        return closedNum;
    }

    public void setClosedNum(Integer closedNum) {
        this.closedNum = closedNum;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }
}