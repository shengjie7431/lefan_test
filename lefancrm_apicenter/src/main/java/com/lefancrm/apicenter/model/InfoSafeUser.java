package com.lefancrm.apicenter.model;

public class InfoSafeUser {
    private Long id;

    private Long safeCompanyId;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSafeCompanyId() {
        return safeCompanyId;
    }

    public void setSafeCompanyId(Long safeCompanyId) {
        this.safeCompanyId = safeCompanyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}