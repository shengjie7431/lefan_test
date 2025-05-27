package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.CommonEnum;

/**
 * Created by lixianfeng on 2019/4/23.
 */
public class CommonEnumDto extends CommonEnum {
    private Boolean selected;

    private Double accMoney;//到账金额
    private Double imgMoney;//开票金额
    private Double accSum;//到账总金额
    private Double imgSum;//开票总金额
    private Double receMoney;//应收账款金额
    private String dateTime;//时间

    private Double unmatchMoney;//待分配回款

    private Double monthImgMoneyHBRate;//本月开票金额环比
    private Double monthImgMoneyTBRate;//本月开票金额同比

    private Double monthAccMoneyHBRate;//本月到账金额环比
    private Double monthAccMoneyTBRate;//本月到账金额同比

    private Double yearImgMoneyYear;//本年开票金额
    private Double yearImgMoneyTBRate;//本年开票金额同比

    private Double yearAccMoneyYear;//本年到账金额
    private Double yearAccMoneyTBRate;//本年到账金额同比

    private Double hisReceMoneyYear;//历史应收金额 ：上年的应收账款余额 + 开票总额（本年一月至本年被选中的月份） - 到账总额 （本年一月至本年被选中的月份）
    private Double hisReceMoneyTBRate;//历史应收金额同比


    private Double aa;
    private Double bb;
    private Double cc;
    private Double dd;
    private Double ee;
    private Double ff;
    private Double gg;
    private Double hh;
    private Double ii;
    private Double jj;
    private Double kk;
    private Double ll;
    private Double mm;
    private Double nn;

    public Double getAccMoney() {
        return accMoney;
    }

    public void setAccMoney(Double accMoney) {
        this.accMoney = accMoney;
    }

    public Double getImgMoney() {
        return imgMoney;
    }

    public void setImgMoney(Double imgMoney) {
        this.imgMoney = imgMoney;
    }

    public Double getAccSum() {
        return accSum;
    }

    public void setAccSum(Double accSum) {
        this.accSum = accSum;
    }

    public Double getImgSum() {
        return imgSum;
    }

    public void setImgSum(Double imgSum) {
        this.imgSum = imgSum;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Double getReceMoney() {
        return receMoney;
    }

    public void setReceMoney(Double receMoney) {
        this.receMoney = receMoney;
    }

    public Double getUnmatchMoney() {
        return unmatchMoney;
    }

    public void setUnmatchMoney(Double unmatchMoney) {
        this.unmatchMoney = unmatchMoney;
    }

    public Double getMonthImgMoneyHBRate() {
        return monthImgMoneyHBRate;
    }

    public void setMonthImgMoneyHBRate(Double monthImgMoneyHBRate) {
        this.monthImgMoneyHBRate = monthImgMoneyHBRate;
    }

    public Double getMonthImgMoneyTBRate() {
        return monthImgMoneyTBRate;
    }

    public void setMonthImgMoneyTBRate(Double monthImgMoneyTBRate) {
        this.monthImgMoneyTBRate = monthImgMoneyTBRate;
    }

    public Double getMonthAccMoneyHBRate() {
        return monthAccMoneyHBRate;
    }

    public void setMonthAccMoneyHBRate(Double monthAccMoneyHBRate) {
        this.monthAccMoneyHBRate = monthAccMoneyHBRate;
    }

    public Double getMonthAccMoneyTBRate() {
        return monthAccMoneyTBRate;
    }

    public void setMonthAccMoneyTBRate(Double monthAccMoneyTBRate) {
        this.monthAccMoneyTBRate = monthAccMoneyTBRate;
    }

    public Double getYearImgMoneyYear() {
        return yearImgMoneyYear;
    }

    public void setYearImgMoneyYear(Double yearImgMoneyYear) {
        this.yearImgMoneyYear = yearImgMoneyYear;
    }

    public Double getYearImgMoneyTBRate() {
        return yearImgMoneyTBRate;
    }

    public void setYearImgMoneyTBRate(Double yearImgMoneyTBRate) {
        this.yearImgMoneyTBRate = yearImgMoneyTBRate;
    }

    public Double getYearAccMoneyYear() {
        return yearAccMoneyYear;
    }

    public void setYearAccMoneyYear(Double yearAccMoneyYear) {
        this.yearAccMoneyYear = yearAccMoneyYear;
    }

    public Double getYearAccMoneyTBRate() {
        return yearAccMoneyTBRate;
    }

    public void setYearAccMoneyTBRate(Double yearAccMoneyTBRate) {
        this.yearAccMoneyTBRate = yearAccMoneyTBRate;
    }

    public Double getHisReceMoneyYear() {
        return hisReceMoneyYear;
    }

    public void setHisReceMoneyYear(Double hisReceMoneyYear) {
        this.hisReceMoneyYear = hisReceMoneyYear;
    }

    public Double getHisReceMoneyTBRate() {
        return hisReceMoneyTBRate;
    }

    public void setHisReceMoneyTBRate(Double hisReceMoneyTBRate) {
        this.hisReceMoneyTBRate = hisReceMoneyTBRate;
    }

    public Double getAa() {
        return aa;
    }

    public void setAa(Double aa) {
        this.aa = aa;
    }

    public Double getBb() {
        return bb;
    }

    public void setBb(Double bb) {
        this.bb = bb;
    }

    public Double getCc() {
        return cc;
    }

    public void setCc(Double cc) {
        this.cc = cc;
    }

    public Double getDd() {
        return dd;
    }

    public void setDd(Double dd) {
        this.dd = dd;
    }

    public Double getEe() {
        return ee;
    }

    public void setEe(Double ee) {
        this.ee = ee;
    }

    public Double getFf() {
        return ff;
    }

    public void setFf(Double ff) {
        this.ff = ff;
    }

    public Double getGg() {
        return gg;
    }

    public void setGg(Double gg) {
        this.gg = gg;
    }

    public Double getHh() {
        return hh;
    }

    public void setHh(Double hh) {
        this.hh = hh;
    }

    public Double getIi() {
        return ii;
    }

    public void setIi(Double ii) {
        this.ii = ii;
    }

    public Double getJj() {
        return jj;
    }

    public void setJj(Double jj) {
        this.jj = jj;
    }

    public Double getKk() {
        return kk;
    }

    public void setKk(Double kk) {
        this.kk = kk;
    }

    public Double getLl() {
        return ll;
    }

    public void setLl(Double ll) {
        this.ll = ll;
    }

    public Double getMm() {
        return mm;
    }

    public void setMm(Double mm) {
        this.mm = mm;
    }

    public Double getNn() {
        return nn;
    }

    public void setNn(Double nn) {
        this.nn = nn;
    }
}
