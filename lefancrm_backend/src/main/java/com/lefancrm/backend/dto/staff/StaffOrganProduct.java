package com.lefancrm.backend.dto.staff;

public class StaffOrganProduct {
    private Long id;

    private Long organId;

    private String organName;

    private Long productEnumId;

    private String productEnumName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrganId() {
        return organId;
    }

    public void setOrganId(Long organId) {
        this.organId = organId;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public Long getProductEnumId() {
        return productEnumId;
    }

    public void setProductEnumId(Long productEnumId) {
        this.productEnumId = productEnumId;
    }

    public String getProductEnumName() {
        return productEnumName;
    }

    public void setProductEnumName(String productEnumName) {
        this.productEnumName = productEnumName;
    }
}