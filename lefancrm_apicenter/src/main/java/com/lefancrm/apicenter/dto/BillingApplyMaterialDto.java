package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.BillingApplyMaterial;


public class BillingApplyMaterialDto extends BillingApplyMaterial {

    private String fileType;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}