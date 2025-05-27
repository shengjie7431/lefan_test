package com.lefancrm.apicenter.model;

public class SurveyConsignerDepartment {
    private Long id;

    private Long consignorOrgId;

    private Long consignorDepartmentId;

    private Long consignerUserId;

    private String consignorDepartmentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsignorOrgId() {
        return consignorOrgId;
    }

    public void setConsignorOrgId(Long consignorOrgId) {
        this.consignorOrgId = consignorOrgId;
    }

    public Long getConsignorDepartmentId() {
        return consignorDepartmentId;
    }

    public void setConsignorDepartmentId(Long consignorDepartmentId) {
        this.consignorDepartmentId = consignorDepartmentId;
    }

    public Long getConsignerUserId() {
        return consignerUserId;
    }

    public void setConsignerUserId(Long consignerUserId) {
        this.consignerUserId = consignerUserId;
    }

    public String getConsignorDepartmentName() {
        return consignorDepartmentName;
    }

    public void setConsignorDepartmentName(String consignorDepartmentName) {
        this.consignorDepartmentName = consignorDepartmentName;
    }
}