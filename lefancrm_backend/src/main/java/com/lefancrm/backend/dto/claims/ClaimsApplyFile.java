package com.lefancrm.backend.dto.claims;

public class ClaimsApplyFile {
    private Long id;

    private Long claimsApplyId;

    private Long commonFileId;

    private Long enumId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClaimsApplyId() {
        return claimsApplyId;
    }

    public void setClaimsApplyId(Long claimsApplyId) {
        this.claimsApplyId = claimsApplyId;
    }

    public Long getCommonFileId() {
        return commonFileId;
    }

    public void setCommonFileId(Long commonFileId) {
        this.commonFileId = commonFileId;
    }

    public Long getEnumId() {
        return enumId;
    }

    public void setEnumId(Long enumId) {
        this.enumId = enumId;
    }
}