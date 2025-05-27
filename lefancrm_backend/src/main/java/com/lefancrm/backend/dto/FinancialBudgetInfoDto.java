package com.lefancrm.backend.dto;

import com.lefancrm.backend.annotation.Excel;

import java.util.Date;

public class FinancialBudgetInfoDto {
    @Excel(name = "编号", excelIndex = 0)
    private Long id;

    private Long budgetId;
    @Excel(name = "年份", excelIndex = 1)
    private String year;

    private Double yearMoney;
    @Excel(name = "预算归属公司", excelIndex = 2)
    private String ascriptionCompany;

    private Long ascriptionCompanyId;

    private String ascriptionDepartment;

    private Long ascriptionDepartmentId;
    @Excel(name = "机构", excelIndex = 3)
    private String ascriptionOrgan;

    private Long ascriptionOrganId;

    private Long costTypeId;
    @Excel(name = "费用类型", excelIndex = 4)
    private String costTypeName;
    @Excel(name = "一月", excelIndex = 5)
    private Double january;
    @Excel(name = "二月", excelIndex = 6)
    private Double february;
    @Excel(name = "三月", excelIndex = 7)
    private Double march;
    @Excel(name = "四月", excelIndex = 8)
    private Double april;
    @Excel(name = "五月", excelIndex = 9)
    private Double may;
    @Excel(name = "六月", excelIndex = 10)
    private Double june;
    @Excel(name = "七月", excelIndex = 11)
    private Double july;
    @Excel(name = "八月", excelIndex = 12)
    private Double august;
    @Excel(name = "九月", excelIndex = 13)
    private Double september;
    @Excel(name = "十月", excelIndex = 14)
    private Double october;
    @Excel(name = "十一月", excelIndex = 15)
    private Double november;
    @Excel(name = "十二月", excelIndex = 16)
    private Double december;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Double januaryUes;

    private Double februaryUes;

    private Double marchUes;

    private Double aprilUes;

    private Double mayUes;

    private Double juneUes;

    private Double julyUes;

    private Double augustUes;

    private Double septemberUes;

    private Double octoberUes;

    private Double novemberUes;

    private Double decemberUes;

    private Double useYearMoney;//已占用
    private Double surplusYearMoney;//剩余

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
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

    public String getAscriptionCompany() {
        return ascriptionCompany;
    }

    public void setAscriptionCompany(String ascriptionCompany) {
        this.ascriptionCompany = ascriptionCompany;
    }

    public Long getAscriptionCompanyId() {
        return ascriptionCompanyId;
    }

    public void setAscriptionCompanyId(Long ascriptionCompanyId) {
        this.ascriptionCompanyId = ascriptionCompanyId;
    }

    public String getAscriptionDepartment() {
        return ascriptionDepartment;
    }

    public void setAscriptionDepartment(String ascriptionDepartment) {
        this.ascriptionDepartment = ascriptionDepartment;
    }

    public Long getAscriptionDepartmentId() {
        return ascriptionDepartmentId;
    }

    public void setAscriptionDepartmentId(Long ascriptionDepartmentId) {
        this.ascriptionDepartmentId = ascriptionDepartmentId;
    }

    public String getAscriptionOrgan() {
        return ascriptionOrgan;
    }

    public void setAscriptionOrgan(String ascriptionOrgan) {
        this.ascriptionOrgan = ascriptionOrgan;
    }

    public Long getAscriptionOrganId() {
        return ascriptionOrganId;
    }

    public void setAscriptionOrganId(Long ascriptionOrganId) {
        this.ascriptionOrganId = ascriptionOrganId;
    }

    public Long getCostTypeId() {
        return costTypeId;
    }

    public void setCostTypeId(Long costTypeId) {
        this.costTypeId = costTypeId;
    }

    public String getCostTypeName() {
        return costTypeName;
    }

    public void setCostTypeName(String costTypeName) {
        this.costTypeName = costTypeName;
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
}