package com.lefancrm.backend.dto;

import java.util.Date;

public class CaseCenterExtendDto {
    private Long id;

    private Long customerFlowState;

    private Long assessFlowState;

    private Long claimFlowState;

    private Long legalFlowState;

    private Integer defineState;

    private Date defineDate;

    private Double defineAmount;

    private Integer repay;

    private String repayImg;

    private Long operatorFlowState;

    private Integer appraiseType;

    private Integer mediateType;

    private Integer closeCaseType;

    private Integer oldListState;

    private String oldListStateName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerFlowState() {
        return customerFlowState;
    }

    public void setCustomerFlowState(Long customerFlowState) {
        this.customerFlowState = customerFlowState;
    }

    public Long getAssessFlowState() {
        return assessFlowState;
    }

    public void setAssessFlowState(Long assessFlowState) {
        this.assessFlowState = assessFlowState;
    }

    public Long getClaimFlowState() {
        return claimFlowState;
    }

    public void setClaimFlowState(Long claimFlowState) {
        this.claimFlowState = claimFlowState;
    }

    public Long getLegalFlowState() {
        return legalFlowState;
    }

    public void setLegalFlowState(Long legalFlowState) {
        this.legalFlowState = legalFlowState;
    }

    public Integer getDefineState() {
        return defineState;
    }

    public void setDefineState(Integer defineState) {
        this.defineState = defineState;
    }

    public Date getDefineDate() {
        return defineDate;
    }

    public void setDefineDate(Date defineDate) {
        this.defineDate = defineDate;
    }

    public Double getDefineAmount() {
        return defineAmount;
    }

    public void setDefineAmount(Double defineAmount) {
        this.defineAmount = defineAmount;
    }

    public Integer getRepay() {
        return repay;
    }

    public void setRepay(Integer repay) {
        this.repay = repay;
    }

    public String getRepayImg() {
        return repayImg;
    }

    public void setRepayImg(String repayImg) {
        this.repayImg = repayImg;
    }

    public Long getOperatorFlowState() {
        return operatorFlowState;
    }

    public void setOperatorFlowState(Long operatorFlowState) {
        this.operatorFlowState = operatorFlowState;
    }

    public Integer getAppraiseType() {
        return appraiseType;
    }

    public void setAppraiseType(Integer appraiseType) {
        this.appraiseType = appraiseType;
    }

    public Integer getMediateType() {
        return mediateType;
    }

    public void setMediateType(Integer mediateType) {
        this.mediateType = mediateType;
    }

    public Integer getCloseCaseType() {
        return closeCaseType;
    }

    public void setCloseCaseType(Integer closeCaseType) {
        this.closeCaseType = closeCaseType;
    }

    public Integer getOldListState() {
        return oldListState;
    }

    public void setOldListState(Integer oldListState) {
        this.oldListState = oldListState;
    }

    public String getOldListStateName() {
        return oldListStateName;
    }

    public void setOldListStateName(String oldListStateName) {
        this.oldListStateName = oldListStateName;
    }
}