package com.lefancrm.apicenter.dto;

import java.util.Date;

public class CaseEstimateInfoDto {
    private Long id;

    private Long estimateId;// 案件ID

    private Integer type; //案件类型  1：伤残预估，2：赔付测算

    private Long userId;//用户id

    private String userName;//用户姓名

    private String userPhone;//用户手机号码

    private Date createTime;//创建时间

    private String createBy;//创建人

    private Long accidentProvinceId;//事故发生省Id

    private String accidentProvince;//事故发生省

    private Long accidentCityId;//事故发生市Id

    private String accidentCity;//事故发生市

    private Long accidentDistrictId;//事故发生区Id

    private String accidentDistrict;//事故发生区

    private String accidentAddress;//具体地址

    private Long turnStatus; //是否转办0:未转办;1:已转办

    private String turnStatusName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(Long estimateId) {
        this.estimateId = estimateId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    public Long getAccidentProvinceId() {
        return accidentProvinceId;
    }

    public void setAccidentProvinceId(Long accidentProvinceId) {
        this.accidentProvinceId = accidentProvinceId;
    }

    public String getAccidentProvince() {
        return accidentProvince;
    }

    public void setAccidentProvince(String accidentProvince) {
        this.accidentProvince = accidentProvince;
    }

    public Long getAccidentCityId() {
        return accidentCityId;
    }

    public void setAccidentCityId(Long accidentCityId) {
        this.accidentCityId = accidentCityId;
    }

    public String getAccidentCity() {
        return accidentCity;
    }

    public void setAccidentCity(String accidentCity) {
        this.accidentCity = accidentCity;
    }

    public Long getAccidentDistrictId() {
        return accidentDistrictId;
    }

    public void setAccidentDistrictId(Long accidentDistrictId) {
        this.accidentDistrictId = accidentDistrictId;
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

    public Long getTurnStatus() {
        return turnStatus;
    }

    public void setTurnStatus(Long turnStatus) {
        this.turnStatus = turnStatus;
    }

    public String getTurnStatusName() {
        return turnStatusName;
    }

    public void setTurnStatusName(String turnStatusName) {
        this.turnStatusName = turnStatusName;
    }
}