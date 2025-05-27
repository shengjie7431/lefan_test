package com.lefancrm.apicenter.fina.model;

public class FinaSurveyPriceModelOrg {
    private Long id;

    private Long priceModelId;

    private String priceModelName;

    private Long orgId;

    private String orgName;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPriceModelId() {
        return priceModelId;
    }

    public void setPriceModelId(Long priceModelId) {
        this.priceModelId = priceModelId;
    }

    public String getPriceModelName() {
        return priceModelName;
    }

    public void setPriceModelName(String priceModelName) {
        this.priceModelName = priceModelName;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}