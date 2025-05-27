package com.lefancrm.apicenter.dto.claims;

import com.lefancrm.apicenter.model.ClaimsApplyFile;

import java.util.List;

public class ClaimsApplyEnumFilesDTO {
    private Long enumId;
    private String enumName;
    private List<ClaimsApplyFile> claimsApplyFiles;

    public Long getEnumId() {
        return enumId;
    }

    public void setEnumId(Long enumId) {
        this.enumId = enumId;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public List<ClaimsApplyFile> getClaimsApplyFiles() {
        return claimsApplyFiles;
    }

    public void setClaimsApplyFiles(List<ClaimsApplyFile> claimsApplyFiles) {
        this.claimsApplyFiles = claimsApplyFiles;
    }
}
