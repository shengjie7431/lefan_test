package com.lefancrm.apicenter.model;

import java.util.Date;

public class AgentApply {
    private Long id;

    private Long userId;

    private String userName;

    private String userPhone;

    private String accidentProvince;

    private String accidentCity;

    private String accidentDistrict;

    private Integer accidentProvinceId;

    private Integer accidentCityId;

    private Integer accidentDistrictId;

    private String accidentAddress;

    private Integer agentType;

    private String claimIndemnityDesc;

    private Integer state;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    private String reson;

    private String agentNo;

    private Integer isRead;

    private Date accidentTime;

    private Integer isFined;

    private String userPromotedName;//推广人姓名(来自表user_promoted)

    private String userPromotedPhone;//推广人电话(来自表user_promoted)

    private Integer isTestcase;//是否测试案件

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getAccidentProvinceId() {
        return accidentProvinceId;
    }

    public void setAccidentProvinceId(Integer accidentProvinceId) {
        this.accidentProvinceId = accidentProvinceId;
    }

    public Integer getAccidentCityId() {
        return accidentCityId;
    }

    public void setAccidentCityId(Integer accidentCityId) {
        this.accidentCityId = accidentCityId;
    }

    public Integer getAccidentDistrictId() {
        return accidentDistrictId;
    }

    public void setAccidentDistrictId(Integer accidentDistrictId) {
        this.accidentDistrictId = accidentDistrictId;
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

    public String getClaimIndemnityDesc() {
        return claimIndemnityDesc;
    }

    public void setClaimIndemnityDesc(String claimIndemnityDesc) {
        this.claimIndemnityDesc = claimIndemnityDesc;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Date getAccidentTime() {
        return accidentTime;
    }

    public void setAccidentTime(Date accidentTime) {
        this.accidentTime = accidentTime;
    }

    public Integer getIsFined() {
        return isFined;
    }

    public void setIsFined(Integer isFined) {
        this.isFined = isFined;
    }

    public String getUserPromotedName() {
        return userPromotedName;
    }

    public void setUserPromotedName(String userPromotedName) {
        this.userPromotedName = userPromotedName;
    }

    public String getUserPromotedPhone() {
        return userPromotedPhone;
    }

    public void setUserPromotedPhone(String userPromotedPhone) {
        this.userPromotedPhone = userPromotedPhone;
    }

    public Integer getIsTestcase() {
        return isTestcase;
    }

    public void setIsTestcase(Integer isTestcase) {
        this.isTestcase = isTestcase;
    }
}