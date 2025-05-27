package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyReInfo;


public class SurveyReInfoDto extends SurveyReInfo {

    private Integer caseCount;     //案件总数
    private Integer reSurveyInvCount;//调查员总数
    private Integer waitSubmitInvoice;//待提交发票总数
    private Integer waitOrgCheck;//待机构审核
    private Integer waitFanceCheck;//待财务审核
    private Integer paying;//付款中
    private Integer waitOkAccount;//待确认到账
    private Integer reOk;//报销完成
    private Double reTotalMoney;//报销总金额


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