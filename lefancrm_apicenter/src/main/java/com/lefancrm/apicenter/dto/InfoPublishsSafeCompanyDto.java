package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.InfoPublishsSafeCompany;

public class InfoPublishsSafeCompanyDto extends InfoPublishsSafeCompany{
    private String safeCompanyName;
    private String safeName;
    private String safeTel;

    public String getSafeCompanyName() {
        return safeCompanyName;
    }

    public void setSafeCompanyName(String safeCompanyName) {
        this.safeCompanyName = safeCompanyName;
    }

    public String getSafeName() {
        return safeName;
    }

    public void setSafeName(String safeName) {
        this.safeName = safeName;
    }

    public String getSafeTel() {
        return safeTel;
    }

    public void setSafeTel(String safeTel) {
        this.safeTel = safeTel;
    }
}