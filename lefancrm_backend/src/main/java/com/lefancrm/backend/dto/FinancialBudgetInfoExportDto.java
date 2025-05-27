package com.lefancrm.backend.dto;

import com.lefancrm.backend.annotation.Excel;


public class FinancialBudgetInfoExportDto {

    @Excel(name = "年份", excelIndex = 0)
    private String year;
    @Excel(name = "年度预算总额", excelIndex = 1)
    private Double yearMoney;
    @Excel(name = "预算归属公司", excelIndex = 2)
    private String ascriptionCompany;
    @Excel(name = "机构", excelIndex = 3)
    private String ascriptionOrgan;
    @Excel(name = "费用类型", excelIndex = 4)
    private String costTypeName;
    @Excel(name = "已占用", excelIndex = 5)
    private Double useYearMoney;//已占用
    @Excel(name = "剩余预算", excelIndex = 6)
    private Double surplusYearMoney;//剩余
    @Excel(name = "一月", excelIndex = 7)
    private String january;
    @Excel(name = "二月", excelIndex = 8)
    private String february;
    @Excel(name = "三月", excelIndex = 9)
    private String march;
    @Excel(name = "四月", excelIndex = 10)
    private String april;
    @Excel(name = "五月", excelIndex = 11)
    private String may;
    @Excel(name = "六月", excelIndex = 12)
    private String june;
    @Excel(name = "七月", excelIndex = 13)
    private String july;
    @Excel(name = "八月", excelIndex = 14)
    private String august;
    @Excel(name = "九月", excelIndex = 15)
    private String september;
    @Excel(name = "十月", excelIndex = 16)
    private String october;
    @Excel(name = "十一月", excelIndex = 17)
    private String november;
    @Excel(name = "十二月", excelIndex = 18)
    private String december;

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

    public String getAscriptionCompany() {
        return ascriptionCompany;
    }

    public void setAscriptionCompany(String ascriptionCompany) {
        this.ascriptionCompany = ascriptionCompany;
    }

    public String getAscriptionOrgan() {
        return ascriptionOrgan;
    }

    public void setAscriptionOrgan(String ascriptionOrgan) {
        this.ascriptionOrgan = ascriptionOrgan;
    }

    public String getCostTypeName() {
        return costTypeName;
    }

    public void setCostTypeName(String costTypeName) {
        this.costTypeName = costTypeName;
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

    public String getJanuary() {
        return january;
    }

    public void setJanuary(String january) {
        this.january = january;
    }

    public String getFebruary() {
        return february;
    }

    public void setFebruary(String february) {
        this.february = february;
    }

    public String getMarch() {
        return march;
    }

    public void setMarch(String march) {
        this.march = march;
    }

    public String getApril() {
        return april;
    }

    public void setApril(String april) {
        this.april = april;
    }

    public String getMay() {
        return may;
    }

    public void setMay(String may) {
        this.may = may;
    }

    public String getJune() {
        return june;
    }

    public void setJune(String june) {
        this.june = june;
    }

    public String getJuly() {
        return july;
    }

    public void setJuly(String july) {
        this.july = july;
    }

    public String getAugust() {
        return august;
    }

    public void setAugust(String august) {
        this.august = august;
    }

    public String getSeptember() {
        return september;
    }

    public void setSeptember(String september) {
        this.september = september;
    }

    public String getOctober() {
        return october;
    }

    public void setOctober(String october) {
        this.october = october;
    }

    public String getNovember() {
        return november;
    }

    public void setNovember(String november) {
        this.november = november;
    }

    public String getDecember() {
        return december;
    }

    public void setDecember(String december) {
        this.december = december;
    }
}