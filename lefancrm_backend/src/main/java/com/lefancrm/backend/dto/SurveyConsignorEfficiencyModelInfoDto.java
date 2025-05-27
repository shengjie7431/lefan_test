package com.lefancrm.backend.dto;

public class SurveyConsignorEfficiencyModelInfoDto {
    private Long id;

    private Long efficiencyModelId;

    private String efficiencyModelName;

    private Long serviceId;

    private String serviceName;

    private Integer cityType;

    private Integer days;

    private Long subServiceId;
    private String subServiceName;

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

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getCityType() {
        return cityType;
    }

    public void setCityType(Integer cityType) {
        this.cityType = cityType;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Long getSubServiceId() {
        return subServiceId;
    }

    public void setSubServiceId(Long subServiceId) {
        this.subServiceId = subServiceId;
    }

    public String getSubServiceName() {
        return subServiceName;
    }

    public void setSubServiceName(String subServiceName) {
        this.subServiceName = subServiceName;
    }
}