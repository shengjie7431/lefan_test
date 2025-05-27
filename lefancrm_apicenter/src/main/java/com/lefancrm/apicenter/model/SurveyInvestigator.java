package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author EDZ
 */
public class SurveyInvestigator {
    private Long id;

    private String nickName;

    private String realName;

    private Long userId;

    private String idcard;

    private String province;

    private String city;

    private String district;

    private Integer provinceId;

    private Integer cityId;

    private Integer districtId;

    private String address;

    private String includeArea;

    private String includeBus;

    private String tel;

    private Integer authType;

    private Integer accState;

    private String remark;

    private Long lefanCurrency;

    private Long achPoint;

    private Double amount;

    private Long titleId;

    private String titleName;

    private Long orgId;

    private String orgName;

    private Long createBy;

    private String createByName;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private Integer type;

    private String includeAreaName;

    private String includeBusName;

    private String includeTask;

    private String includeTaskName;

    private List<BusinessRole> businessRole;
    /**
     * 调查中案件数量
     */
    private Integer dcyCaseNum;
    /**
     * 个人签名url
     */
    private String signUrl;
    /**
     * 银行卡号
     */
    private String bankNo;
    /**
     * 银行名称（含开户行）
     */
    private String bankName;
    /**
     * 第一责任区
     */
    private String resArea;
    /**
     * 第一责任区ID
     */
    private String resAreaId;
    /**
     * 其他覆盖区域
     */
    private String otherOverlayArea;
    /**
     * 其他覆盖区域ID
     */
    private String otherOverlayAreaId;

    /**
     * 业务属性（1：互助，2：保险，3：互助+保险）
     */
    private Integer busType;

    /**
     * 离职时间
     */
    private Date quitTime;

    /**
     * 带教老师ID
     */
    private Integer teacherUserId;

    /**
     * 带教老师姓名
     */
    private String teacherUserName;

    /**
     * 带教老师机构ID
     */
    private Integer teacherOrgId;

    /**
     * 带教老师机构名称
     */
    private String teacherOrgName;

    /**
     * 带教老师电话
     */
    private String teacherUserTel;

    /**
     * 是否标记达标（0：否，1：是）
     */
    private Integer isNewPeople;

    /**
     * 是否结算奖励（0：否，1：是）
     */
    private Integer isSettlement;

    /**
     * 实际达标日期
     */
    private Date realQualifiedTime;

    /**
     * 达标截止日期
     */
    private Date qualifiedEnfdTime;

    /**
     * 时效天数
     */
    private Integer effecDays;

    /**
     * 带教奖励
     */
    private Double teacherReward;

    /**
     * 带教实际奖励
     */
    private Double teacherRealReward;

    /**
     * 备注
     */
    private String rewardDesc;

    /**
     * 操作人ID
     */
    private Integer operateUserId;

    /**
     * 操作人
     */
    private String operateUserName;

    /**
     * 积分
     */
    private Double score;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     *带教合计金额
     */
    private Double totalAmount;

    /**
     *老总意见
     */
    private Double basicScore;

    /**
     *基础积分
     */
    private Double basicIntegral;

    /**
     * 片区ID
     */
    private Integer surveyAreaId;

    /**
     * 片区名称
     */
    private String surveyAreaName;

    private Integer channelType;//是否渠道调查员

    private Boolean LAY_CHECKED=false;

    private Integer haveNwAccount;

    private String registerNo;

    public Boolean getLAY_CHECKED() {
        return LAY_CHECKED;
    }

    public void setLAY_CHECKED(Boolean LAY_CHECKED) {
        this.LAY_CHECKED = LAY_CHECKED;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getRewardDesc() {
        return rewardDesc;
    }

    public void setRewardDesc(String rewardDesc) {
        this.rewardDesc = rewardDesc;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getTeacherUserId() {
        return teacherUserId;
    }

    public void setTeacherUserId(Integer teacherUserId) {
        this.teacherUserId = teacherUserId;
    }

    public String getTeacherUserName() {
        return teacherUserName;
    }

    public void setTeacherUserName(String teacherUserName) {
        this.teacherUserName = teacherUserName;
    }

    public Integer getTeacherOrgId() {
        return teacherOrgId;
    }

    public void setTeacherOrgId(Integer teacherOrgId) {
        this.teacherOrgId = teacherOrgId;
    }

    public String getTeacherOrgName() {
        return teacherOrgName;
    }

    public void setTeacherOrgName(String teacherOrgName) {
        this.teacherOrgName = teacherOrgName;
    }

    public String getTeacherUserTel() {
        return teacherUserTel;
    }

    public void setTeacherUserTel(String teacherUserTel) {
        this.teacherUserTel = teacherUserTel;
    }

    public Integer getIsNewPeople() {
        return isNewPeople;
    }

    public void setIsNewPeople(Integer isNewPeople) {
        this.isNewPeople = isNewPeople;
    }

    public Integer getIsSettlement() {
        return isSettlement;
    }

    public void setIsSettlement(Integer isSettlement) {
        this.isSettlement = isSettlement;
    }

    public Date getRealQualifiedTime() {
        return realQualifiedTime;
    }

    public void setRealQualifiedTime(Date realQualifiedTime) {
        this.realQualifiedTime = realQualifiedTime;
    }

    public Date getQualifiedEnfdTime() {
        return qualifiedEnfdTime;
    }

    public void setQualifiedEnfdTime(Date qualifiedEnfdTime) {
        this.qualifiedEnfdTime = qualifiedEnfdTime;
    }

    public Integer getEffecDays() {
        return effecDays;
    }

    public void setEffecDays(Integer effecDays) {
        this.effecDays = effecDays;
    }

    public Double getTeacherReward() {
        return teacherReward;
    }

    public void setTeacherReward(Double teacherReward) {
        this.teacherReward = teacherReward;
    }

    public Double getTeacherRealReward() {
        return teacherRealReward;
    }

    public void setTeacherRealReward(Double teacherRealReward) {
        this.teacherRealReward = teacherRealReward;
    }

    public Integer getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Integer operateUserId) {
        this.operateUserId = operateUserId;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }

    public Integer getBusType() {
        return busType;
    }

    public void setBusType(Integer busType) {
        this.busType = busType;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getResArea() {
        return resArea;
    }

    public void setResArea(String resArea) {
        this.resArea = resArea;
    }

    public String getResAreaId() {
        return resAreaId;
    }

    public void setResAreaId(String resAreaId) {
        this.resAreaId = resAreaId;
    }

    public String getOtherOverlayArea() {
        return otherOverlayArea;
    }

    public void setOtherOverlayArea(String otherOverlayArea) {
        this.otherOverlayArea = otherOverlayArea;
    }

    public String getOtherOverlayAreaId() {
        return otherOverlayAreaId;
    }

    public void setOtherOverlayAreaId(String otherOverlayAreaId) {
        this.otherOverlayAreaId = otherOverlayAreaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIncludeArea() {
        return includeArea;
    }

    public void setIncludeArea(String includeArea) {
        this.includeArea = includeArea;
    }

    public String getIncludeBus() {
        return includeBus;
    }

    public void setIncludeBus(String includeBus) {
        this.includeBus = includeBus;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public Integer getAccState() {
        return accState;
    }

    public void setAccState(Integer accState) {
        this.accState = accState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getLefanCurrency() {
        return lefanCurrency;
    }

    public void setLefanCurrency(Long lefanCurrency) {
        this.lefanCurrency = lefanCurrency;
    }

    public Long getAchPoint() {
        return achPoint;
    }

    public void setAchPoint(Long achPoint) {
        this.achPoint = achPoint;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
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

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIncludeAreaName() {
        return includeAreaName;
    }

    public void setIncludeAreaName(String includeAreaName) {
        this.includeAreaName = includeAreaName;
    }

    public String getIncludeBusName() {
        return includeBusName;
    }

    public void setIncludeBusName(String includeBusName) {
        this.includeBusName = includeBusName;
    }

    public List<BusinessRole> getBusinessRole() {
        return businessRole;
    }

    public void setBusinessRole(List<BusinessRole> businessRole) {
        this.businessRole = businessRole;
    }

    public String getIncludeTask() {
        return includeTask;
    }

    public void setIncludeTask(String includeTask) {
        this.includeTask = includeTask;
    }

    public String getIncludeTaskName() {
        return includeTaskName;
    }

    public void setIncludeTaskName(String includeTaskName) {
        this.includeTaskName = includeTaskName;
    }

    public Integer getDcyCaseNum() {
        return dcyCaseNum;
    }

    public void setDcyCaseNum(Integer dcyCaseNum) {
        this.dcyCaseNum = dcyCaseNum;
    }

    public String getSignUrl() {
        return signUrl;
    }

    public void setSignUrl(String signUrl) {
        this.signUrl = signUrl;
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }

    public Double getBasicScore() {
        return basicScore;
    }

    public void setBasicScore(Double basicScore) {
        this.basicScore = basicScore;
    }

    public Double getBasicIntegral() {
        return basicIntegral;
    }

    public void setBasicIntegral(Double basicIntegral) {
        this.basicIntegral = basicIntegral;
    }

    public Integer getSurveyAreaId() {
        return surveyAreaId;
    }

    public void setSurveyAreaId(Integer surveyAreaId) {
        this.surveyAreaId = surveyAreaId;
    }

    public String getSurveyAreaName() {
        return surveyAreaName;
    }

    public void setSurveyAreaName(String surveyAreaName) {
        this.surveyAreaName = surveyAreaName;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public Integer getHaveNwAccount() {
        return haveNwAccount;
    }

    public void setHaveNwAccount(Integer haveNwAccount) {
        this.haveNwAccount = haveNwAccount;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }
}