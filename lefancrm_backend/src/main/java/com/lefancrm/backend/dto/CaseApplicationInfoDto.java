package com.lefancrm.backend.dto;

import java.util.Date;

public class CaseApplicationInfoDto {
    private Long id;

    private Integer type; //案件类型  1：代理申请，2：贷款申请

    private String caseNo; //案件编号

    private Long userId;//用户id

    private String userName;//用户姓名

    private String userPhone;//用户手机号码

    private Date createTime;//创建时间

    private Long accidentProvinceId;//事故发生省Id

    private String accidentProvince;//事故发生省

    private Long accidentCityId;//事故发生市Id

    private String accidentCity;//事故发生市

    private Long accidentDistrictId;//事故发生区Id

    private String accidentDistrict;//事故发生区

    private String accidentAddress;//具体地址

    private Integer state;//状态

    private String promoterName;//推广人

    private String promoterPhone;//推广人手机号

    private Integer isFined;

    private Integer isTestcase;//是否测试案件

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
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

    public String getPromoterName() {
        return promoterName;
    }

    public void setPromoterName(String promoterName) {
        this.promoterName = promoterName;
    }

    public String getPromoterPhone() {
        return promoterPhone;
    }

    public void setPromoterPhone(String promoterPhone) {
        this.promoterPhone = promoterPhone;
    }

    public Integer getIsFined() {
        return isFined;
    }

    public void setIsFined(Integer isFined) {
        this.isFined = isFined;
    }

    public Integer getIsTestcase() {
        return isTestcase;
    }

    public void setIsTestcase(Integer isTestcase) {
        this.isTestcase = isTestcase;
    }
}