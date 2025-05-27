package com.lefancrm.apicenter.dto.report;

public class SurveyReportLefanDTO {
    //基础信息
    private int basEntrustOrgs;
    private int basSurveyOrgs;
    private int basSurveyUsers;
    private Double basBillNotAcc;
    private Double basTotalBillMoney;//总开票金额
    private Double basTotalAccMoney;//总到账金额

    private int basEntrustNotApv;
    private int basLefanNotAss;
    private int basOrgSurveying;
    private int basLefanApv;
    private int basEntrustApv;
    private int basNotClose;

    //关键指标
    private int tarNewEntrust;
    private int  tarMomNewEntrust;
    private Double tarNewEntrustRate;
    private int tarTbNewEntrust;
    private Double tarTbNewEntrustRate;

    private int tarNewEntrustOK;
    private Double tarEntrustMoney;
    private Double tarBillMoney;
    private Double tarAccMoney;
    private Double tarAvgMoney;
    private int tarDirectionNum;//方向数量
    private Double tarAvgDirectionMoney;//方向件均
    private Double tarSunRate;
    private Double tarAraeRate;
    private int tarEffic;
    private Double tarEfficRate;

    public int getBasEntrustOrgs() {
        return basEntrustOrgs;
    }

    public void setBasEntrustOrgs(int basEntrustOrgs) {
        this.basEntrustOrgs = basEntrustOrgs;
    }

    public int getBasSurveyOrgs() {
        return basSurveyOrgs;
    }

    public void setBasSurveyOrgs(int basSurveyOrgs) {
        this.basSurveyOrgs = basSurveyOrgs;
    }

    public int getBasSurveyUsers() {
        return basSurveyUsers;
    }

    public void setBasSurveyUsers(int basSurveyUsers) {
        this.basSurveyUsers = basSurveyUsers;
    }

    public Double getBasBillNotAcc() {
        return basBillNotAcc;
    }

    public void setBasBillNotAcc(Double basBillNotAcc) {
        this.basBillNotAcc = basBillNotAcc;
    }

    public int getBasEntrustNotApv() {
        return basEntrustNotApv;
    }

    public void setBasEntrustNotApv(int basEntrustNotApv) {
        this.basEntrustNotApv = basEntrustNotApv;
    }

    public int getBasLefanNotAss() {
        return basLefanNotAss;
    }

    public void setBasLefanNotAss(int basLefanNotAss) {
        this.basLefanNotAss = basLefanNotAss;
    }

    public int getBasOrgSurveying() {
        return basOrgSurveying;
    }

    public void setBasOrgSurveying(int basOrgSurveying) {
        this.basOrgSurveying = basOrgSurveying;
    }

    public int getBasLefanApv() {
        return basLefanApv;
    }

    public void setBasLefanApv(int basLefanApv) {
        this.basLefanApv = basLefanApv;
    }

    public int getBasEntrustApv() {
        return basEntrustApv;
    }

    public void setBasEntrustApv(int basEntrustApv) {
        this.basEntrustApv = basEntrustApv;
    }

    public int getBasNotClose() {
        return basNotClose;
    }

    public void setBasNotClose(int basNotClose) {
        this.basNotClose = basNotClose;
    }

    public int getTarNewEntrust() {
        return tarNewEntrust;
    }

    public void setTarNewEntrust(int tarNewEntrust) {
        this.tarNewEntrust = tarNewEntrust;
    }

    public int getTarNewEntrustOK() {
        return tarNewEntrustOK;
    }

    public void setTarNewEntrustOK(int tarNewEntrustOK) {
        this.tarNewEntrustOK = tarNewEntrustOK;
    }

    public Double getTarEntrustMoney() {
        return tarEntrustMoney;
    }

    public void setTarEntrustMoney(Double tarEntrustMoney) {
        this.tarEntrustMoney = tarEntrustMoney;
    }

    public Double getTarBillMoney() {
        return tarBillMoney;
    }

    public void setTarBillMoney(Double tarBillMoney) {
        this.tarBillMoney = tarBillMoney;
    }

    public Double getTarAccMoney() {
        return tarAccMoney;
    }

    public void setTarAccMoney(Double tarAccMoney) {
        this.tarAccMoney = tarAccMoney;
    }

    public Double getTarAvgMoney() {
        return tarAvgMoney;
    }

    public void setTarAvgMoney(Double tarAvgMoney) {
        this.tarAvgMoney = tarAvgMoney;
    }

    public Double getTarSunRate() {
        return tarSunRate;
    }

    public void setTarSunRate(Double tarSunRate) {
        this.tarSunRate = tarSunRate;
    }

    public Double getTarAraeRate() {
        return tarAraeRate;
    }

    public void setTarAraeRate(Double tarAraeRate) {
        this.tarAraeRate = tarAraeRate;
    }

    public int getTarEffic() {
        return tarEffic;
    }

    public void setTarEffic(int tarEffic) {
        this.tarEffic = tarEffic;
    }

    public Double getTarEfficRate() {
        return tarEfficRate;
    }

    public void setTarEfficRate(Double tarEfficRate) {
        this.tarEfficRate = tarEfficRate;
    }

    public Double getBasTotalBillMoney() {
        return basTotalBillMoney;
    }

    public void setBasTotalBillMoney(Double basTotalBillMoney) {
        this.basTotalBillMoney = basTotalBillMoney;
    }

    public Double getBasTotalAccMoney() {
        return basTotalAccMoney;
    }

    public void setBasTotalAccMoney(Double basTotalAccMoney) {
        this.basTotalAccMoney = basTotalAccMoney;
    }

    public Double getTarAvgDirectionMoney() {
        return tarAvgDirectionMoney;
    }

    public void setTarAvgDirectionMoney(Double tarAvgDirectionMoney) {
        this.tarAvgDirectionMoney = tarAvgDirectionMoney;
    }

    public int getTarDirectionNum() {
        return tarDirectionNum;
    }

    public void setTarDirectionNum(int tarDirectionNum) {
        this.tarDirectionNum = tarDirectionNum;
    }

    public int getTarMomNewEntrust() {
        return tarMomNewEntrust;
    }

    public void setTarMomNewEntrust(int tarMomNewEntrust) {
        this.tarMomNewEntrust = tarMomNewEntrust;
    }

    public Double getTarNewEntrustRate() {
        return tarNewEntrustRate;
    }

    public void setTarNewEntrustRate(Double tarNewEntrustRate) {
        this.tarNewEntrustRate = tarNewEntrustRate;
    }

    public int getTarTbNewEntrust() {
        return tarTbNewEntrust;
    }

    public void setTarTbNewEntrust(int tarTbNewEntrust) {
        this.tarTbNewEntrust = tarTbNewEntrust;
    }

    public Double getTarTbNewEntrustRate() {
        return tarTbNewEntrustRate;
    }

    public void setTarTbNewEntrustRate(Double tarTbNewEntrustRate) {
        this.tarTbNewEntrustRate = tarTbNewEntrustRate;
    }
}
