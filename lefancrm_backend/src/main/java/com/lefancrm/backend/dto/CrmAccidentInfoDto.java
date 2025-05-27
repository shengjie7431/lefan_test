package com.lefancrm.backend.dto;

import java.util.Date;

public class CrmAccidentInfoDto {
    private Long id;

    private Long customerId;

    private Date accidentDate;

    private String province;

    private Integer provinceId;

    private String city;

    private Integer cityId;

    private String district;

    private Integer districtId;

    private String accidentAddress;

    private Integer accidentCognizance;

    private String policeTeam;

    private String policeMan;

    private String policeTel;

    private String insCompulsory;

    private String insCommercial;

    private Double threeQuota;

    private Integer isDeductibles;

    private String driverName;

    private String driverTel;

    private Integer isMulti;

    private Integer isRelief;

    private String otherDesc;

    private Double dataRate;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(Date accidentDate) {
        this.accidentDate = accidentDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getAccidentAddress() {
        return accidentAddress;
    }

    public void setAccidentAddress(String accidentAddress) {
        this.accidentAddress = accidentAddress;
    }

    public Integer getAccidentCognizance() {
        return accidentCognizance;
    }

    public void setAccidentCognizance(Integer accidentCognizance) {
        this.accidentCognizance = accidentCognizance;
    }

    public String getPoliceTeam() {
        return policeTeam;
    }

    public void setPoliceTeam(String policeTeam) {
        this.policeTeam = policeTeam;
    }

    public String getPoliceMan() {
        return policeMan;
    }

    public void setPoliceMan(String policeMan) {
        this.policeMan = policeMan;
    }

    public String getPoliceTel() {
        return policeTel;
    }

    public void setPoliceTel(String policeTel) {
        this.policeTel = policeTel;
    }

    public String getInsCompulsory() {
        return insCompulsory;
    }

    public void setInsCompulsory(String insCompulsory) {
        this.insCompulsory = insCompulsory;
    }

    public String getInsCommercial() {
        return insCommercial;
    }

    public void setInsCommercial(String insCommercial) {
        this.insCommercial = insCommercial;
    }

    public Double getThreeQuota() {
        return threeQuota;
    }

    public void setThreeQuota(Double threeQuota) {
        this.threeQuota = threeQuota;
    }

    public Integer getIsDeductibles() {
        return isDeductibles;
    }

    public void setIsDeductibles(Integer isDeductibles) {
        this.isDeductibles = isDeductibles;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverTel() {
        return driverTel;
    }

    public void setDriverTel(String driverTel) {
        this.driverTel = driverTel;
    }

    public Integer getIsMulti() {
        return isMulti;
    }

    public void setIsMulti(Integer isMulti) {
        this.isMulti = isMulti;
    }

    public Integer getIsRelief() {
        return isRelief;
    }

    public void setIsRelief(Integer isRelief) {
        this.isRelief = isRelief;
    }

    public String getOtherDesc() {
        return otherDesc;
    }

    public void setOtherDesc(String otherDesc) {
        this.otherDesc = otherDesc;
    }

    public Double getDataRate() {
        return dataRate;
    }

    public void setDataRate(Double dataRate) {
        this.dataRate = dataRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}