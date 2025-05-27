package com.lefancrm.apicenter.model;

import java.util.List;

public class ThinkDataDetail {
    private Long id;

    private Long thinkDataId;

    private Integer dataType;

    private Long orgId;

    private String orgName;

    private Integer state;

    private Double depAccMony;

    private Double lefanInMony;

    private Double lefanOutMony;

    private Double lefanTaxMony;

    private List<ThinkDataOrgProduct> orgProducts;

    private List<FinancialFile> files;

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

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Double getDepAccMony() {
        return depAccMony;
    }

    public void setDepAccMony(Double depAccMony) {
        this.depAccMony = depAccMony;
    }

    public Double getLefanInMony() {
        return lefanInMony;
    }

    public void setLefanInMony(Double lefanInMony) {
        this.lefanInMony = lefanInMony;
    }

    public Double getLefanOutMony() {
        return lefanOutMony;
    }

    public void setLefanOutMony(Double lefanOutMony) {
        this.lefanOutMony = lefanOutMony;
    }

    public Double getLefanTaxMony() {
        return lefanTaxMony;
    }

    public void setLefanTaxMony(Double lefanTaxMony) {
        this.lefanTaxMony = lefanTaxMony;
    }

    public List<ThinkDataOrgProduct> getOrgProducts() {
        return orgProducts;
    }

    public void setOrgProducts(List<ThinkDataOrgProduct> orgProducts) {
        this.orgProducts = orgProducts;
    }

    public List<FinancialFile> getFiles() {
        return files;
    }

    public void setFiles(List<FinancialFile> files) {
        this.files = files;
    }
}