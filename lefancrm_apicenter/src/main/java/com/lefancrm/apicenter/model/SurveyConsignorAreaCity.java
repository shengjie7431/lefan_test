package com.lefancrm.apicenter.model;

public class SurveyConsignorAreaCity {
    private Long id;

    private Long orgId;

    private String orgName;

    private Long areaId;

    private String areaName;

    private String areaLongname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaLongname() {
        return areaLongname;
    }

    public void setAreaLongname(String areaLongname) {
        this.areaLongname = areaLongname;
    }
}