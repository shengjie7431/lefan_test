package com.lefancrm.apicenter.model;

public class FinancialCostOrgDetails {
    private Long id;

    private Long financialReApplyId;

    private Long departmentId;

    private String departmentName;

    private Integer costTypeId;

    private String costTypeName;

    private Double costMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinancialReApplyId() {
        return financialReApplyId;
    }

    public void setFinancialReApplyId(Long financialReApplyId) {
        this.financialReApplyId = financialReApplyId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getCostTypeId() {
        return costTypeId;
    }

    public void setCostTypeId(Integer costTypeId) {
        this.costTypeId = costTypeId;
    }

    public String getCostTypeName() {
        return costTypeName;
    }

    public void setCostTypeName(String costTypeName) {
        this.costTypeName = costTypeName;
    }

    public Double getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(Double costMoney) {
        this.costMoney = costMoney;
    }
}