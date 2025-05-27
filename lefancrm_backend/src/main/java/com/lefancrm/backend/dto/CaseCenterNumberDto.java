package com.lefancrm.backend.dto;

/**
 * Created by lixianfeng on 2018/11/5.
 */
public class CaseCenterNumberDto {
    private Integer caseNumber;//案件总数
    private Integer caseLoan;//贷款案件数
    private Integer caseAgent;//代理案件数

    private Integer oprNumber;//洽谈阶段数量
    private Integer assNumber;//评估阶段数量
    private Integer claimNumber;//索赔阶段数量
    private Integer legalNumber;//诉讼阶段数量
    private Integer closeNumber;//结案

    //评估工作台
    private Integer pgdjs;
    private Integer pgdcs;
    private Integer pgdyg;
    private Integer pgdtjbg;
    private Integer pgshz;
    private Integer pgdtjdk;
    private Integer pgdtb;
    private Integer pgdcwzf;
    private Integer pgdqr;
    private Integer pgqrfwf;
    //索赔工作台
    private Integer spdjs;
    private Integer spclsjz;
    private Integer spdjd;
    private Integer spdzzyabg;
    private Integer spshz;
    private Integer sptjz;
    private Integer spdtjja;
    private Integer spdhk;
    private Integer spdja;
    //诉讼工作台
    private Integer ssdjs;
    private Integer ssclsjz;
    private Integer ssdzzyabg;
    private Integer ssshz;
    private Integer ssdla;
    private Integer ssdtjjabg;
    private Integer ssdhk;
    private Integer ssdja;

    public Integer getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(Integer caseNumber) {
        this.caseNumber = caseNumber;
    }

    public Integer getCaseLoan() {
        return caseLoan;
    }

    public void setCaseLoan(Integer caseLoan) {
        this.caseLoan = caseLoan;
    }

    public Integer getCaseAgent() {
        return caseAgent;
    }

    public void setCaseAgent(Integer caseAgent) {
        this.caseAgent = caseAgent;
    }

    public Integer getOprNumber() {
        return oprNumber;
    }

    public void setOprNumber(Integer oprNumber) {
        this.oprNumber = oprNumber;
    }

    public Integer getAssNumber() {
        return assNumber;
    }

    public void setAssNumber(Integer assNumber) {
        this.assNumber = assNumber;
    }

    public Integer getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(Integer claimNumber) {
        this.claimNumber = claimNumber;
    }

    public Integer getLegalNumber() {
        return legalNumber;
    }

    public void setLegalNumber(Integer legalNumber) {
        this.legalNumber = legalNumber;
    }

    public Integer getCloseNumber() {
        return closeNumber;
    }

    public void setCloseNumber(Integer closeNumber) {
        this.closeNumber = closeNumber;
    }

    public Integer getPgdjs() {
        return pgdjs;
    }

    public void setPgdjs(Integer pgdjs) {
        this.pgdjs = pgdjs;
    }

    public Integer getPgdcs() {
        return pgdcs;
    }

    public void setPgdcs(Integer pgdcs) {
        this.pgdcs = pgdcs;
    }

    public Integer getPgdyg() {
        return pgdyg;
    }

    public void setPgdyg(Integer pgdyg) {
        this.pgdyg = pgdyg;
    }

    public Integer getPgdtjbg() {
        return pgdtjbg;
    }

    public void setPgdtjbg(Integer pgdtjbg) {
        this.pgdtjbg = pgdtjbg;
    }

    public Integer getPgshz() {
        return pgshz;
    }

    public void setPgshz(Integer pgshz) {
        this.pgshz = pgshz;
    }

    public Integer getPgdtjdk() {
        return pgdtjdk;
    }

    public void setPgdtjdk(Integer pgdtjdk) {
        this.pgdtjdk = pgdtjdk;
    }

    public Integer getPgdtb() {
        return pgdtb;
    }

    public void setPgdtb(Integer pgdtb) {
        this.pgdtb = pgdtb;
    }

    public Integer getPgdcwzf() {
        return pgdcwzf;
    }

    public void setPgdcwzf(Integer pgdcwzf) {
        this.pgdcwzf = pgdcwzf;
    }

    public Integer getPgdqr() {
        return pgdqr;
    }

    public void setPgdqr(Integer pgdqr) {
        this.pgdqr = pgdqr;
    }

    public Integer getPgqrfwf() {
        return pgqrfwf;
    }

    public void setPgqrfwf(Integer pgqrfwf) {
        this.pgqrfwf = pgqrfwf;
    }

    public Integer getSpdjs() {
        return spdjs;
    }

    public void setSpdjs(Integer spdjs) {
        this.spdjs = spdjs;
    }

    public Integer getSpclsjz() {
        return spclsjz;
    }

    public void setSpclsjz(Integer spclsjz) {
        this.spclsjz = spclsjz;
    }

    public Integer getSpdjd() {
        return spdjd;
    }

    public void setSpdjd(Integer spdjd) {
        this.spdjd = spdjd;
    }

    public Integer getSpshz() {
        return spshz;
    }

    public void setSpshz(Integer spshz) {
        this.spshz = spshz;
    }

    public Integer getSptjz() {
        return sptjz;
    }

    public void setSptjz(Integer sptjz) {
        this.sptjz = sptjz;
    }

    public Integer getSpdtjja() {
        return spdtjja;
    }

    public void setSpdtjja(Integer spdtjja) {
        this.spdtjja = spdtjja;
    }

    public Integer getSpdhk() {
        return spdhk;
    }

    public void setSpdhk(Integer spdhk) {
        this.spdhk = spdhk;
    }

    public Integer getSpdja() {
        return spdja;
    }

    public void setSpdja(Integer spdja) {
        this.spdja = spdja;
    }

    public Integer getSsdjs() {
        return ssdjs;
    }

    public void setSsdjs(Integer ssdjs) {
        this.ssdjs = ssdjs;
    }

    public Integer getSsclsjz() {
        return ssclsjz;
    }

    public void setSsclsjz(Integer ssclsjz) {
        this.ssclsjz = ssclsjz;
    }

    public Integer getSsdzzyabg() {
        return ssdzzyabg;
    }

    public void setSsdzzyabg(Integer ssdzzyabg) {
        this.ssdzzyabg = ssdzzyabg;
    }

    public Integer getSsshz() {
        return ssshz;
    }

    public void setSsshz(Integer ssshz) {
        this.ssshz = ssshz;
    }

    public Integer getSsdla() {
        return ssdla;
    }

    public void setSsdla(Integer ssdla) {
        this.ssdla = ssdla;
    }

    public Integer getSsdtjjabg() {
        return ssdtjjabg;
    }

    public void setSsdtjjabg(Integer ssdtjjabg) {
        this.ssdtjjabg = ssdtjjabg;
    }

    public Integer getSsdhk() {
        return ssdhk;
    }

    public void setSsdhk(Integer ssdhk) {
        this.ssdhk = ssdhk;
    }

    public Integer getSsdja() {
        return ssdja;
    }

    public void setSsdja(Integer ssdja) {
        this.ssdja = ssdja;
    }

    public Integer getSpdzzyabg() {
        return spdzzyabg;
    }

    public void setSpdzzyabg(Integer spdzzyabg) {
        this.spdzzyabg = spdzzyabg;
    }
}
