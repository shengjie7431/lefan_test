package com.lefancrm.apicenter.model;

public class ThinkDataOrgProduct {
    private Long id;

    private Long thinkDataId;

    private Long thinkDataDetailId;

    private Long productId;

    private String productName;

    private Double claimSunAddmony;

    private Double claimSunSubmoney;

    private Double busAccMony;

    private Long orgId;

    private Integer dataType;//1.业务主营;2业务管理;3后援管理;4.乐凡集团

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getThinkDataId() {
        return thinkDataId;
    }

    public void setThinkDataId(Long thinkDataId) {
        this.thinkDataId = thinkDataId;
    }

    public Long getThinkDataDetailId() {
        return thinkDataDetailId;
    }

    public void setThinkDataDetailId(Long thinkDataDetailId) {
        this.thinkDataDetailId = thinkDataDetailId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getClaimSunAddmony() {
        return claimSunAddmony;
    }

    public void setClaimSunAddmony(Double claimSunAddmony) {
        this.claimSunAddmony = claimSunAddmony;
    }

    public Double getClaimSunSubmoney() {
        return claimSunSubmoney;
    }

    public void setClaimSunSubmoney(Double claimSunSubmoney) {
        this.claimSunSubmoney = claimSunSubmoney;
    }

    public Double getBusAccMony() {
        return busAccMony;
    }

    public void setBusAccMony(Double busAccMony) {
        this.busAccMony = busAccMony;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }
}