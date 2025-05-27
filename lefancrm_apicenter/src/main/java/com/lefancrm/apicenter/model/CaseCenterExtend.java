package com.lefancrm.apicenter.model;

import java.util.Date;

public class CaseCenterExtend {
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

    private Date negotiateDate;//测试报价审核通过时间
    private Date assessDate;//评估时间
    private Date claimantDate;//索赔时间
    private Date legalDate;//诉讼时间
    private Date closedDate;//结案时间
    private Date releaseDate;//解约时间

    private String userPromotedPhone;//推广人联系方式

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

    public Date getNegotiateDate() {
        return negotiateDate;
    }

    public void setNegotiateDate(Date negotiateDate) {
        this.negotiateDate = negotiateDate;
    }

    public Date getAssessDate() {
        return assessDate;
    }

    public void setAssessDate(Date assessDate) {
        this.assessDate = assessDate;
    }

    public Date getClaimantDate() {
        return claimantDate;
    }

    public void setClaimantDate(Date claimantDate) {
        this.claimantDate = claimantDate;
    }

    public Date getLegalDate() {
        return legalDate;
    }

    public void setLegalDate(Date legalDate) {
        this.legalDate = legalDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getUserPromotedPhone() {
        return userPromotedPhone;
    }

    public void setUserPromotedPhone(String userPromotedPhone) {
        this.userPromotedPhone = userPromotedPhone;
    }
}