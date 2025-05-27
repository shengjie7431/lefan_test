package com.lefancrm.backend.dto;

import java.util.Date;

public class ProductSpecDto {
    private Long id;

    private Long productId;

    private String specName;

    private String specImg;

    private Double specPrice;

    private Double specCost;

    private Integer specStock;

    private Integer specSales;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecImg() {
        return specImg;
    }

    public void setSpecImg(String specImg) {
        this.specImg = specImg;
    }

    public Double getSpecPrice() {
        return specPrice;
    }

    public void setSpecPrice(Double specPrice) {
        this.specPrice = specPrice;
    }

    public Double getSpecCost() {
        return specCost;
    }

    public void setSpecCost(Double specCost) {
        this.specCost = specCost;
    }

    public Integer getSpecStock() {
        return specStock;
    }

    public void setSpecStock(Integer specStock) {
        this.specStock = specStock;
    }

    public Integer getSpecSales() {
        return specSales;
    }

    public void setSpecSales(Integer specSales) {
        this.specSales = specSales;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}