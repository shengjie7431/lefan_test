package com.lefancrm.apicenter.model;

public class StaffPaySlipManager {
    private Long id;

    private Long staffPaySlipId;

    private Integer managerType;

    private Long organManagerUserId;

    private Long organManagerStaffId;

    private String organManagerName;

    private Long superiorManagerUserId;

    private Long superiorManagerStaffId;

    private String superiorManagerName;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStaffPaySlipId() {
        return staffPaySlipId;
    }

    public void setStaffPaySlipId(Long staffPaySlipId) {
        this.staffPaySlipId = staffPaySlipId;
    }

    public Integer getManagerType() {
        return managerType;
    }

    public void setManagerType(Integer managerType) {
        this.managerType = managerType;
    }

    public Long getOrganManagerUserId() {
        return organManagerUserId;
    }

    public void setOrganManagerUserId(Long organManagerUserId) {
        this.organManagerUserId = organManagerUserId;
    }

    public Long getOrganManagerStaffId() {
        return organManagerStaffId;
    }

    public void setOrganManagerStaffId(Long organManagerStaffId) {
        this.organManagerStaffId = organManagerStaffId;
    }

    public String getOrganManagerName() {
        return organManagerName;
    }

    public void setOrganManagerName(String organManagerName) {
        this.organManagerName = organManagerName;
    }

    public Long getSuperiorManagerUserId() {
        return superiorManagerUserId;
    }

    public void setSuperiorManagerUserId(Long superiorManagerUserId) {
        this.superiorManagerUserId = superiorManagerUserId;
    }

    public Long getSuperiorManagerStaffId() {
        return superiorManagerStaffId;
    }

    public void setSuperiorManagerStaffId(Long superiorManagerStaffId) {
        this.superiorManagerStaffId = superiorManagerStaffId;
    }

    public String getSuperiorManagerName() {
        return superiorManagerName;
    }

    public void setSuperiorManagerName(String superiorManagerName) {
        this.superiorManagerName = superiorManagerName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}