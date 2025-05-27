package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyCashInfo {
    private Long id;

    private String cashInfoName;

    private String cashInfoCode;

    private Long franchiseeId;

    private String franchiseeName;

    private Long applyUserId;

    private String applyUserName;

    private String applyUserTel;

    private Date applyTime;

    private Long investigatorId;

    private String investigatorName;

    private Double caseAmount;

    private Double feeRatio;

    private Double feeAmount;

    private Double realAmount;

    private Integer cashState;

    private String reason;

    private Integer confirmAccountState;

    private String paySource;

    private Integer tradeType;

    private String unlineImg;

    private String tradeDesc;

    private Long operateUserId;

    private String operateUserName;

    private Date operateTime;

    private Long surveyBankCardId;

    private Date createTime;

    private Integer deleteFlag;

    private Boolean orgMan;//机构复核
    private Boolean lfYuying;//狄大人运营

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCashInfoName() {
        return cashInfoName;
    }

    public void setCashInfoName(String cashInfoName) {
        this.cashInfoName = cashInfoName;
    }

    public String getCashInfoCode() {
        return cashInfoCode;
    }

    public void setCashInfoCode(String cashInfoCode) {
        this.cashInfoCode = cashInfoCode;
    }

    public Long getFranchiseeId() {
        return franchiseeId;
    }

    public void setFranchiseeId(Long franchiseeId) {
        this.franchiseeId = franchiseeId;
    }

    public String getFranchiseeName() {
        return franchiseeName;
    }

    public void setFranchiseeName(String franchiseeName) {
        this.franchiseeName = franchiseeName;
    }

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getApplyUserTel() {
        return applyUserTel;
    }

    public void setApplyUserTel(String applyUserTel) {
        this.applyUserTel = applyUserTel;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Long getInvestigatorId() {
        return investigatorId;
    }

    public void setInvestigatorId(Long investigatorId) {
        this.investigatorId = investigatorId;
    }

    public String getInvestigatorName() {
        return investigatorName;
    }

    public void setInvestigatorName(String investigatorName) {
        this.investigatorName = investigatorName;
    }

    public Double getCaseAmount() {
        return caseAmount;
    }

    public void setCaseAmount(Double caseAmount) {
        this.caseAmount = caseAmount;
    }

    public Double getFeeRatio() {
        return feeRatio;
    }

    public void setFeeRatio(Double feeRatio) {
        this.feeRatio = feeRatio;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Double getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(Double realAmount) {
        this.realAmount = realAmount;
    }

    public Integer getCashState() {
        return cashState;
    }

    public void setCashState(Integer cashState) {
        this.cashState = cashState;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getConfirmAccountState() {
        return confirmAccountState;
    }

    public void setConfirmAccountState(Integer confirmAccountState) {
        this.confirmAccountState = confirmAccountState;
    }

    public String getPaySource() {
        return paySource;
    }

    public void setPaySource(String paySource) {
        this.paySource = paySource;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getUnlineImg() {
        return unlineImg;
    }

    public void setUnlineImg(String unlineImg) {
        this.unlineImg = unlineImg;
    }

    public String getTradeDesc() {
        return tradeDesc;
    }

    public void setTradeDesc(String tradeDesc) {
        this.tradeDesc = tradeDesc;
    }

    public Long getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Long operateUserId) {
        this.operateUserId = operateUserId;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Long getSurveyBankCardId() {
        return surveyBankCardId;
    }

    public void setSurveyBankCardId(Long surveyBankCardId) {
        this.surveyBankCardId = surveyBankCardId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Boolean getOrgMan() {
        return orgMan;
    }

    public void setOrgMan(Boolean orgMan) {
        this.orgMan = orgMan;
    }

    public Boolean getLfYuying() {
        return lfYuying;
    }

    public void setLfYuying(Boolean lfYuying) {
        this.lfYuying = lfYuying;
    }
}