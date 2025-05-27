package com.lefancrm.backend.dto.fina;

public class FinaSurveyConsignorEfficiencyModelOrgDto {
    private Long id;

    private Long efficiencyModelId;

    private String efficiencyModelName;

    private Long orgId;

    private String orgName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEfficiencyModelId() {
        return efficiencyModelId;
    }

    public void setEfficiencyModelId(Long efficiencyModelId) {
        this.efficiencyModelId = efficiencyModelId;
    }

    public String getEfficiencyModelName() {
        return efficiencyModelName;
    }

    public void setEfficiencyModelName(String efficiencyModelName) {
        this.efficiencyModelName = efficiencyModelName;
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
}