package com.lefancrm.backend.dto;

import java.util.Date;

public class CaseEntrustInputDto {
    private Long id;

    private String injuredPerson;

    private String injuredTel;

    private Long accidentProvinceId;

    private Long accidentCityId;

    private Long accidentDistrictId;

    private String accidentProvince;

    private String accidentCity;

    private String accidentDistrict;

    private String accidentAddress;

    private Integer agentType;

    private String agentDesc;

    private Date accidentTime;

    private Date createTime;

    private String createBy;

    private Integer deleteFlag;

    private Integer checkState;

    private String reson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInjuredPerson() {
        return injuredPerson;
    }

    public void setInjuredPerson(String injuredPerson) {
        this.injuredPerson = injuredPerson;
    }

    public String getInjuredTel() {
        return injuredTel;
    }

    public void setInjuredTel(String injuredTel) {
        this.injuredTel = injuredTel;
    }

    public Long getAccidentProvinceId() {
        return accidentProvinceId;
    }

    public void setAccidentProvinceId(Long accidentProvinceId) {
        this.accidentProvinceId = accidentProvinceId;
    }

    public Long getAccidentCityId() {
        return accidentCityId;
    }

    public void setAccidentCityId(Long accidentCityId) {
        this.accidentCityId = accidentCityId;
    }

    public Long getAccidentDistrictId() {
        return accidentDistrictId;
    }

    public void setAccidentDistrictId(Long accidentDistrictId) {
        this.accidentDistrictId = accidentDistrictId;
    }

    public String getAccidentProvince() {
        return accidentProvince;
    }

    public void setAccidentProvince(String accidentProvince) {
        this.accidentProvince = accidentProvince;
    }

    public String getAccidentCity() {
        return accidentCity;
    }

    public void setAccidentCity(String accidentCity) {
        this.accidentCity = accidentCity;
    }

    public String getAccidentDistrict() {
        return accidentDistrict;
    }

    public void setAccidentDistrict(String accidentDistrict) {
        this.accidentDistrict = accidentDistrict;
    }

    public String getAccidentAddress() {
        return accidentAddress;
    }

    public void setAccidentAddress(String accidentAddress) {
        this.accidentAddress = accidentAddress;
    }

    public Integer getAgentType() {
        return agentType;
    }

    public void setAgentType(Integer agentType) {
        this.agentType = agentType;
    }

    public String getAgentDesc() {
        return agentDesc;
    }

    public void setAgentDesc(String agentDesc) {
        this.agentDesc = agentDesc;
    }

    public Date getAccidentTime() {
        return accidentTime;
    }

    public void setAccidentTime(Date accidentTime) {
        this.accidentTime = accidentTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getCheckState() {
        return checkState;
    }

    public void setCheckState(Integer checkState) {
        this.checkState = checkState;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }
}