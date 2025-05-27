package com.lefancrm.backend.dto;

import java.util.Date;

public class LawCaseInfoDto {
    private Long id;

    private String caseNo;

    private String pubCaseNo;

    private Long orgId;

    private String orgName;

    private Long assessId;

    private String assessName;

    private String assessTel;

    private Date acceptTime;

    private Long appUserId;

    private String appUserName;

    private String appUserTel;

    private Long entrustUserId;

    private String entrustUserName;

    private String entrustUserTel;

    private Integer caseType;

    private Date entrustTime;

    private Double targetAmount;

    private String targetProvince;

    private String targetCity;

    private String targetDistrict;

    private Integer targetProvinceId;

    private Integer targetCityId;

    private Integer targetDistrictId;

    private String targetAddress;

    private String linkName;

    private String linkTel;

    private String assessRemark;

    private Double assessFee;

    private Double checkAssessFee;

    private Double finalAssessAmount;

    private Double finalAssessFee;

    private Date assessRecTime;

    private Double travelFee;

    private String entrustDesc;

    private String remark;

    private Integer oneCheckOpinion;

    private String oneCheckOpinionRemark;

    private Integer twoCheckOpinion;

    private String twoCheckOpinionRemark;

    private Integer lssuerCheckOpinion;

    private String lssuerCheckOpinionRemark;

    private Integer flowState;

    private String flowStateName;

    private Integer refundType;

    private Integer stageState;

    private Integer retreatState;

    private Date retreatTime;

    private Long createBy;

    private String createByName;

    private Date createTime;

    private Date updateTime;

    private Long updateBy;

    private Integer isCursupplement;

    private Integer isDelete;

    private Integer isUploadAssessPlan;

    private Integer isUploadReqmoneyLetter;

    private Integer isSendReport;

    private Integer isArrAssFee;

    private Integer isExpress;

    private Date sendReportTime;

    private Date expressTime;

    private Integer isDraft;

    private Integer isArrRefund;

    private Date arrRefundTime;

    private String refoundRemark;

    private Integer isSue;

    private Integer isBill;

    private String toName;

    private String toTel;

    private String toOrg;

    private String toAddress;

    private Integer isOkInfo;

    private Boolean isAssess = false;//是否评估师
    private Boolean isComplex  = false;//是否综合内勤
    private Boolean isAssessSuper  = false;//是否评估主管
    private Boolean isAssessManager  = false;//是否评估经理

    private String collectionRemarks;

    private Integer payType;

    private Integer isOkInfo2;

    private Double refundFee;

    private Integer isReport;

    private String toProvince;

    private String toCity;

    private String toDistrict;

    private Integer toProvinceId;

    private Integer toCityId;

    private Integer toDistrictId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getPubCaseNo() {
        return pubCaseNo;
    }

    public void setPubCaseNo(String pubCaseNo) {
        this.pubCaseNo = pubCaseNo;
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

    public Long getAssessId() {
        return assessId;
    }

    public void setAssessId(Long assessId) {
        this.assessId = assessId;
    }

    public String getAssessName() {
        return assessName;
    }

    public void setAssessName(String assessName) {
        this.assessName = assessName;
    }

    public String getAssessTel() {
        return assessTel;
    }

    public void setAssessTel(String assessTel) {
        this.assessTel = assessTel;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public String getAppUserName() {
        return appUserName;
    }

    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName;
    }

    public String getAppUserTel() {
        return appUserTel;
    }

    public void setAppUserTel(String appUserTel) {
        this.appUserTel = appUserTel;
    }

    public Long getEntrustUserId() {
        return entrustUserId;
    }

    public void setEntrustUserId(Long entrustUserId) {
        this.entrustUserId = entrustUserId;
    }

    public String getEntrustUserName() {
        return entrustUserName;
    }

    public void setEntrustUserName(String entrustUserName) {
        this.entrustUserName = entrustUserName;
    }

    public String getEntrustUserTel() {
        return entrustUserTel;
    }

    public void setEntrustUserTel(String entrustUserTel) {
        this.entrustUserTel = entrustUserTel;
    }

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public Date getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(Date entrustTime) {
        this.entrustTime = entrustTime;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public String getTargetProvince() {
        return targetProvince;
    }

    public void setTargetProvince(String targetProvince) {
        this.targetProvince = targetProvince;
    }

    public String getTargetCity() {
        return targetCity;
    }

    public void setTargetCity(String targetCity) {
        this.targetCity = targetCity;
    }

    public String getTargetDistrict() {
        return targetDistrict;
    }

    public void setTargetDistrict(String targetDistrict) {
        this.targetDistrict = targetDistrict;
    }

    public Integer getTargetProvinceId() {
        return targetProvinceId;
    }

    public void setTargetProvinceId(Integer targetProvinceId) {
        this.targetProvinceId = targetProvinceId;
    }

    public Integer getTargetCityId() {
        return targetCityId;
    }

    public void setTargetCityId(Integer targetCityId) {
        this.targetCityId = targetCityId;
    }

    public Integer getTargetDistrictId() {
        return targetDistrictId;
    }

    public void setTargetDistrictId(Integer targetDistrictId) {
        this.targetDistrictId = targetDistrictId;
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getAssessRemark() {
        return assessRemark;
    }

    public void setAssessRemark(String assessRemark) {
        this.assessRemark = assessRemark;
    }

    public Double getAssessFee() {
        return assessFee;
    }

    public void setAssessFee(Double assessFee) {
        this.assessFee = assessFee;
    }

    public Double getCheckAssessFee() {
        return checkAssessFee;
    }

    public void setCheckAssessFee(Double checkAssessFee) {
        this.checkAssessFee = checkAssessFee;
    }

    public Double getFinalAssessAmount() {
        return finalAssessAmount;
    }

    public void setFinalAssessAmount(Double finalAssessAmount) {
        this.finalAssessAmount = finalAssessAmount;
    }

    public Double getFinalAssessFee() {
        return finalAssessFee;
    }

    public void setFinalAssessFee(Double finalAssessFee) {
        this.finalAssessFee = finalAssessFee;
    }

    public Date getAssessRecTime() {
        return assessRecTime;
    }

    public void setAssessRecTime(Date assessRecTime) {
        this.assessRecTime = assessRecTime;
    }

    public Double getTravelFee() {
        return travelFee;
    }

    public void setTravelFee(Double travelFee) {
        this.travelFee = travelFee;
    }

    public String getEntrustDesc() {
        return entrustDesc;
    }

    public void setEntrustDesc(String entrustDesc) {
        this.entrustDesc = entrustDesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOneCheckOpinion() {
        return oneCheckOpinion;
    }

    public void setOneCheckOpinion(Integer oneCheckOpinion) {
        this.oneCheckOpinion = oneCheckOpinion;
    }

    public String getOneCheckOpinionRemark() {
        return oneCheckOpinionRemark;
    }

    public void setOneCheckOpinionRemark(String oneCheckOpinionRemark) {
        this.oneCheckOpinionRemark = oneCheckOpinionRemark;
    }

    public Integer getTwoCheckOpinion() {
        return twoCheckOpinion;
    }

    public void setTwoCheckOpinion(Integer twoCheckOpinion) {
        this.twoCheckOpinion = twoCheckOpinion;
    }

    public String getTwoCheckOpinionRemark() {
        return twoCheckOpinionRemark;
    }

    public void setTwoCheckOpinionRemark(String twoCheckOpinionRemark) {
        this.twoCheckOpinionRemark = twoCheckOpinionRemark;
    }

    public Integer getLssuerCheckOpinion() {
        return lssuerCheckOpinion;
    }

    public void setLssuerCheckOpinion(Integer lssuerCheckOpinion) {
        this.lssuerCheckOpinion = lssuerCheckOpinion;
    }

    public String getLssuerCheckOpinionRemark() {
        return lssuerCheckOpinionRemark;
    }

    public void setLssuerCheckOpinionRemark(String lssuerCheckOpinionRemark) {
        this.lssuerCheckOpinionRemark = lssuerCheckOpinionRemark;
    }

    public Integer getFlowState() {
        return flowState;
    }

    public void setFlowState(Integer flowState) {
        this.flowState = flowState;
    }

    public String getFlowStateName() {
        return flowStateName;
    }

    public void setFlowStateName(String flowStateName) {
        this.flowStateName = flowStateName;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public Integer getStageState() {
        return stageState;
    }

    public void setStageState(Integer stageState) {
        this.stageState = stageState;
    }

    public Integer getRetreatState() {
        return retreatState;
    }

    public void setRetreatState(Integer retreatState) {
        this.retreatState = retreatState;
    }

    public Date getRetreatTime() {
        return retreatTime;
    }

    public void setRetreatTime(Date retreatTime) {
        this.retreatTime = retreatTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getIsCursupplement() {
        return isCursupplement;
    }

    public void setIsCursupplement(Integer isCursupplement) {
        this.isCursupplement = isCursupplement;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsUploadAssessPlan() {
        return isUploadAssessPlan;
    }

    public void setIsUploadAssessPlan(Integer isUploadAssessPlan) {
        this.isUploadAssessPlan = isUploadAssessPlan;
    }

    public Integer getIsUploadReqmoneyLetter() {
        return isUploadReqmoneyLetter;
    }

    public void setIsUploadReqmoneyLetter(Integer isUploadReqmoneyLetter) {
        this.isUploadReqmoneyLetter = isUploadReqmoneyLetter;
    }

    public Integer getIsSendReport() {
        return isSendReport;
    }

    public void setIsSendReport(Integer isSendReport) {
        this.isSendReport = isSendReport;
    }

    public Integer getIsArrAssFee() {
        return isArrAssFee;
    }

    public void setIsArrAssFee(Integer isArrAssFee) {
        this.isArrAssFee = isArrAssFee;
    }

    public Integer getIsExpress() {
        return isExpress;
    }

    public void setIsExpress(Integer isExpress) {
        this.isExpress = isExpress;
    }

    public Date getSendReportTime() {
        return sendReportTime;
    }

    public void setSendReportTime(Date sendReportTime) {
        this.sendReportTime = sendReportTime;
    }

    public Date getExpressTime() {
        return expressTime;
    }

    public void setExpressTime(Date expressTime) {
        this.expressTime = expressTime;
    }

    public Integer getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(Integer isDraft) {
        this.isDraft = isDraft;
    }

    public Boolean getIsAssess() {
        return isAssess;
    }

    public void setIsAssess(Boolean isAssess) {
        this.isAssess = isAssess;
    }

    public Boolean getIsComplex() {
        return isComplex;
    }

    public void setIsComplex(Boolean isComplex) {
        this.isComplex = isComplex;
    }

    public Boolean getIsAssessSuper() {
        return isAssessSuper;
    }

    public void setIsAssessSuper(Boolean isAssessSuper) {
        this.isAssessSuper = isAssessSuper;
    }

    public Boolean getIsAssessManager() {
        return isAssessManager;
    }

    public void setIsAssessManager(Boolean isAssessManager) {
        this.isAssessManager = isAssessManager;
    }

    public Integer getIsArrRefund() {
        return isArrRefund;
    }

    public void setIsArrRefund(Integer isArrRefund) {
        this.isArrRefund = isArrRefund;
    }

    public Date getArrRefundTime() {
        return arrRefundTime;
    }

    public void setArrRefundTime(Date arrRefundTime) {
        this.arrRefundTime = arrRefundTime;
    }

    public String getRefoundRemark() {
        return refoundRemark;
    }

    public void setRefoundRemark(String refoundRemark) {
        this.refoundRemark = refoundRemark;
    }

    public Integer getIsSue() {
        return isSue;
    }

    public void setIsSue(Integer isSue) {
        this.isSue = isSue;
    }

    public Integer getIsBill() {
        return isBill;
    }

    public void setIsBill(Integer isBill) {
        this.isBill = isBill;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToTel() {
        return toTel;
    }

    public void setToTel(String toTel) {
        this.toTel = toTel;
    }

    public String getToOrg() {
        return toOrg;
    }

    public void setToOrg(String toOrg) {
        this.toOrg = toOrg;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public Integer getIsOkInfo() {
        return isOkInfo;
    }

    public void setIsOkInfo(Integer isOkInfo) {
        this.isOkInfo = isOkInfo;
    }

    public String getCollectionRemarks() {
        return collectionRemarks;
    }

    public void setCollectionRemarks(String collectionRemarks) {
        this.collectionRemarks = collectionRemarks;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getIsOkInfo2() {
        return isOkInfo2;
    }

    public void setIsOkInfo2(Integer isOkInfo2) {
        this.isOkInfo2 = isOkInfo2;
    }

    public Double getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Double refundFee) {
        this.refundFee = refundFee;
    }

    public Integer getIsReport() {
        return isReport;
    }

    public void setIsReport(Integer isReport) {
        this.isReport = isReport;
    }

    public String getToProvince() {
        return toProvince;
    }

    public void setToProvince(String toProvince) {
        this.toProvince = toProvince;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getToDistrict() {
        return toDistrict;
    }

    public void setToDistrict(String toDistrict) {
        this.toDistrict = toDistrict;
    }

    public Integer getToProvinceId() {
        return toProvinceId;
    }

    public void setToProvinceId(Integer toProvinceId) {
        this.toProvinceId = toProvinceId;
    }

    public Integer getToCityId() {
        return toCityId;
    }

    public void setToCityId(Integer toCityId) {
        this.toCityId = toCityId;
    }

    public Integer getToDistrictId() {
        return toDistrictId;
    }

    public void setToDistrictId(Integer toDistrictId) {
        this.toDistrictId = toDistrictId;
    }
}