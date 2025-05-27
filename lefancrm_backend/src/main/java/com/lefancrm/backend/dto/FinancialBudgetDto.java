package com.lefancrm.backend.dto;

import java.util.Date;

public class FinancialBudgetDto {
    private Long id;

    private String year;

    private Double yearMoney;

    private Double useYearMoney;//已占用
    private Double surplusYearMoney;//剩余

    private Double january;
    private Double januaryUes;

    private Double february;
    private Double februaryUes;

    private Double march;
    private Double marchUes;

    private Double april;
    private Double aprilUes;

    private Double may;
    private Double mayUes;

    private Double june;
    private Double juneUes;

    private Double july;
    private Double julyUes;

    private Double august;
    private Double augustUes;

    private Double september;
    private Double septemberUes;

    private Double october;
    private Double octoberUes;

    private Double november;
    private Double novemberUes;

    private Double december;
    private Double decemberUes;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getYearMoney() {
        return yearMoney;
    }

    public void setYearMoney(Double yearMoney) {
        this.yearMoney = yearMoney;
    }

    public Double getJanuary() {
        return january;
    }

    public void setJanuary(Double january) {
        this.january = january;
    }

    public Double getFebruary() {
        return february;
    }

    public void setFebruary(Double february) {
        this.february = february;
    }

    public Double getMarch() {
        return march;
    }

    public void setMarch(Double march) {
        this.march = march;
    }

    public Double getApril() {
        return april;
    }

    public void setApril(Double april) {
        this.april = april;
    }

    public Double getMay() {
        return may;
    }

    public void setMay(Double may) {
        this.may = may;
    }

    public Double getJune() {
        return june;
    }

    public void setJune(Double june) {
        this.june = june;
    }

    public Double getJuly() {
        return july;
    }

    public void setJuly(Double july) {
        this.july = july;
    }

    public Double getAugust() {
        return august;
    }

    public void setAugust(Double august) {
        this.august = august;
    }

    public Double getSeptember() {
        return september;
    }

    public void setSeptember(Double september) {
        this.september = september;
    }

    public Double getOctober() {
        return october;
    }

    public void setOctober(Double october) {
        this.october = october;
    }

    public Double getNovember() {
        return november;
    }

    public void setNovember(Double november) {
        this.november = november;
    }

    public Double getDecember() {
        return december;
    }

    public void setDecember(Double december) {
        this.december = december;
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

    public Double getUseYearMoney() {
        return useYearMoney;
    }

    public void setUseYearMoney(Double useYearMoney) {
        this.useYearMoney = useYearMoney;
    }

    public Double getSurplusYearMoney() {
        return surplusYearMoney;
    }

    public void setSurplusYearMoney(Double surplusYearMoney) {
        this.surplusYearMoney = surplusYearMoney;
    }

    public Double getJanuaryUes() {
        return januaryUes;
    }

    public void setJanuaryUes(Double januaryUes) {
        this.januaryUes = januaryUes;
    }

    public Double getFebruaryUes() {
        return februaryUes;
    }

    public void setFebruaryUes(Double februaryUes) {
        this.februaryUes = februaryUes;
    }

    public Double getMarchUes() {
        return marchUes;
    }

    public void setMarchUes(Double marchUes) {
        this.marchUes = marchUes;
    }

    public Double getAprilUes() {
        return aprilUes;
    }

    public void setAprilUes(Double aprilUes) {
        this.aprilUes = aprilUes;
    }

    public Double getMayUes() {
        return mayUes;
    }

    public void setMayUes(Double mayUes) {
        this.mayUes = mayUes;
    }

    public Double getJuneUes() {
        return juneUes;
    }

    public void setJuneUes(Double juneUes) {
        this.juneUes = juneUes;
    }

    public Double getJulyUes() {
        return julyUes;
    }

    public void setJulyUes(Double julyUes) {
        this.julyUes = julyUes;
    }

    public Double getAugustUes() {
        return augustUes;
    }

    public void setAugustUes(Double augustUes) {
        this.augustUes = augustUes;
    }

    public Double getSeptemberUes() {
        return septemberUes;
    }

    public void setSeptemberUes(Double septemberUes) {
        this.septemberUes = septemberUes;
    }

    public Double getOctoberUes() {
        return octoberUes;
    }

    public void setOctoberUes(Double octoberUes) {
        this.octoberUes = octoberUes;
    }

    public Double getNovemberUes() {
        return novemberUes;
    }

    public void setNovemberUes(Double novemberUes) {
        this.novemberUes = novemberUes;
    }

    public Double getDecemberUes() {
        return decemberUes;
    }

    public void setDecemberUes(Double decemberUes) {
        this.decemberUes = decemberUes;
    }
}