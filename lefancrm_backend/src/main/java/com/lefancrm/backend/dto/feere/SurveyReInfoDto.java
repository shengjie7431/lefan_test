package com.lefancrm.backend.dto.feere;


import java.util.Date;


public class SurveyReInfoDto {
    private Long id;

    private String reName;

    private String reDate;

    private Integer reState;

    private Date downTime;

    private Date createTime;

    private String createBy;
    private Integer caseCount;     //案件总数
    private Integer reSurveyInvCount;//调查员总数
    private Integer waitSubmitInvoice;//待提交发票总数
    private Integer waitOrgCheck;//待机构审核
    private Integer waitFanceCheck;//待财务审核
    private Integer paying;//付款中
    private Integer waitOkAccount;//待确认到账
    private Integer reOk;//报销完成
    private Double reTotalMoney;//报销总金额

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReName() {
        return reName;
    }

    public void setReName(String reName) {
        this.reName = reName;
    }

    public String getReDate() {
        return reDate;
    }

    public void setReDate(String reDate) {
        this.reDate = reDate;
    }

    public Integer getReState() {
        return reState;
    }

    public void setReState(Integer reState) {
        this.reState = reState;
    }

    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
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

    public Integer getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(Integer caseCount) {
        this.caseCount = caseCount;
    }

    public Integer getReSurveyInvCount() {
        return reSurveyInvCount;
    }

    public void setReSurveyInvCount(Integer reSurveyInvCount) {
        this.reSurveyInvCount = reSurveyInvCount;
    }

    public Integer getWaitSubmitInvoice() {
        return waitSubmitInvoice;
    }

    public void setWaitSubmitInvoice(Integer waitSubmitInvoice) {
        this.waitSubmitInvoice = waitSubmitInvoice;
    }

    public Integer getWaitOrgCheck() {
        return waitOrgCheck;
    }

    public void setWaitOrgCheck(Integer waitOrgCheck) {
        this.waitOrgCheck = waitOrgCheck;
    }

    public Integer getWaitFanceCheck() {
        return waitFanceCheck;
    }

    public void setWaitFanceCheck(Integer waitFanceCheck) {
        this.waitFanceCheck = waitFanceCheck;
    }

    public Integer getPaying() {
        return paying;
    }

    public void setPaying(Integer paying) {
        this.paying = paying;
    }

    public Integer getWaitOkAccount() {
        return waitOkAccount;
    }

    public void setWaitOkAccount(Integer waitOkAccount) {
        this.waitOkAccount = waitOkAccount;
    }

    public Integer getReOk() {
        return reOk;
    }

    public void setReOk(Integer reOk) {
        this.reOk = reOk;
    }

    public Double getReTotalMoney() {
        return reTotalMoney;
    }

    public void setReTotalMoney(Double reTotalMoney) {
        this.reTotalMoney = reTotalMoney;
    }
}