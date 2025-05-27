package com.lefancrm.backend.dto.fina;

import java.util.Date;

public class FinaRepaymentInfo {
    private Long id;

    private Long settlementInfoId;

    private String repaymentNo;

    private Double repaymentMoney;

    private Integer repaymentSource;

    private Integer repaymentType;

    private String repaymentPath;

    private Date repaymentTime;

    private Long applyUserId;

    private String applyUserName;

    private Integer repaymentState;

    private Long financeUserId;

    private String financeUserName;

    private Date repaymentPassedTime;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    private String oprRemark;

    private Long urgeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSettlementInfoId() {
        return settlementInfoId;
    }

    public void setSettlementInfoId(Long settlementInfoId) {
        this.settlementInfoId = settlementInfoId;
    }

    public String getRepaymentNo() {
        return repaymentNo;
    }

    public void setRepaymentNo(String repaymentNo) {
        this.repaymentNo = repaymentNo;
    }

    public Double getRepaymentMoney() {
        return repaymentMoney;
    }

    public void setRepaymentMoney(Double repaymentMoney) {
        this.repaymentMoney = repaymentMoney;
    }

    public Integer getRepaymentSource() {
        return repaymentSource;
    }

    public void setRepaymentSource(Integer repaymentSource) {
        this.repaymentSource = repaymentSource;
    }

    public Integer getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }

    public String getRepaymentPath() {
        return repaymentPath;
    }

    public void setRepaymentPath(String repaymentPath) {
        this.repaymentPath = repaymentPath;
    }

    public Date getRepaymentTime() {
        return repaymentTime;
    }

    public void setRepaymentTime(Date repaymentTime) {
        this.repaymentTime = repaymentTime;
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

    public Integer getRepaymentState() {
        return repaymentState;
    }

    public void setRepaymentState(Integer repaymentState) {
        this.repaymentState = repaymentState;
    }

    public Long getFinanceUserId() {
        return financeUserId;
    }

    public void setFinanceUserId(Long financeUserId) {
        this.financeUserId = financeUserId;
    }

    public String getFinanceUserName() {
        return financeUserName;
    }

    public void setFinanceUserName(String financeUserName) {
        this.financeUserName = financeUserName;
    }

    public Date getRepaymentPassedTime() {
        return repaymentPassedTime;
    }

    public void setRepaymentPassedTime(Date repaymentPassedTime) {
        this.repaymentPassedTime = repaymentPassedTime;
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

    public String getOprRemark() {
        return oprRemark;
    }

    public void setOprRemark(String oprRemark) {
        this.oprRemark = oprRemark;
    }

    public Long getUrgeId() {
        return urgeId;
    }

    public void setUrgeId(Long urgeId) {
        this.urgeId = urgeId;
    }
}