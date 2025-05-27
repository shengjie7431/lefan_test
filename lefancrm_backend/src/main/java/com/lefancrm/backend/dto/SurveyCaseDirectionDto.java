package com.lefancrm.backend.dto;

import java.util.Date;
import java.util.List;

public class SurveyCaseDirectionDto {
    private Long id;

    private String directionName;

    private String directionText;

    private Long surveyInvestigatorCaseId;

    private Long taskId;

    private String taskName;

    private Long surveyId;

    private Long surveyInfoId;

    private Double surveyMoney;

    private Double entrustMoney;

    private Long entrustTaskId;

    private String createBy;

    private String updateBy;

    private Date createTime;

    private Date updateTime;

    private Integer deleteFlag;

    private String province;

    private String city;

    private String district;

    private Integer provinceId;

    private Integer cityId;

    private Integer districtId;

    private Integer areaType;

    private Integer areaId;

    private String areaName;

    private Integer regionType;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Integer surveyPriceSource;

    private Integer entrustPriceSource;

    private String newName;

    private Long newId;

    private Double score;

    private String surveyMoneyRemark;

    private String entrustMoneyRemark;

    private Double oldSurveyMoney;

    private Double oldEntrustMoney;

    private Integer medicalNumber;//病历数量

    private Long directionResultTypeId;//方向结果类型
    private String directionResultTypeName;
    private String directionResultTypeCode;

    private SurveyInvestigatorCaseDto surveyInvestigatorCase;
    private SurveyRiskCaseInfoDto surveyRiskCaseInfo;

    private List<SurveyCaseDirectionFileDto> surveyCaseDirectionFiles;//方向附件清单
    private Integer surveyCaseDirectionFilesSize;
    private int directionFilesSize;

    private String directionInfo;

    private String realDirectionName;//用于删除服务器端的方向附件（此数据与服务器端方向名称比较）

    private String itemDateStr;
    private String itemCityStr;
    private String itemContext;
    private String cityStr;
    private String addressStr;
    private String money1;
    private String money2;
    private String margeStr;
    private String taskColor;//任务类型的颜色
    private String newColor;//任务子类的颜色
    private String surveyUserName;
    private String surveyReason;

    private Integer reviewOff;//是否审核


    //互助对应属性
    private Date huzhuDate;
    private String attr1Obj;
    private String attr1ObjName;

    private String attr2Type;
    private String attr2His;

    private String attr3Obj;

    private String attr4Name1;
    private String attr4Remark1;
    private String attr4Name2;
    private String attr4Remark2;

    private Long surveyAssorgCaseId;

    private int sun;
    private String sunRemark;

    private String huzhuColsRemark;
    private String huzhuSunStr;

    private Boolean review;

    private int haveReimbursement;
    private Boolean showExpenseReimbursementValue;//是否显示费用报销数值
    private String expenseReimbursementValue;//费用报销数值
    private List<SurveyReimbursementFileDto> surveyReimbursementFileDtoList;
    private Integer haveFile;
    private Integer haveSound;

    private Double hisScore;

    private Double channelFeeCur;

    private Double channelFeeSent;

    /**
     * 总数
     */
    private Integer count;

    /**
     * 地区名称Id
     */
    private String regionNameId;

    /**
     * 地区名称
     */
    private String regionName;

    /**
     * 占比
     */
    private Integer proportion;

    private Integer invalidState;//是否无效方向（0：有效；1、无效）

    private Integer channelType;

    private SurveyChannelCostNew surveyChannelCostNew;

    private String surveyCno;

    private Double accMoney;
    private Double accScore;
    private Double scoreRate;

    private Long clockId;

    private Long addressX;

    private Long addressY;

    private Integer orgPoint;

    private Integer evaluate;

    private Integer materRaw;

    private List<SurveyTaskInfoContentDto> taskInfoContents;

    private List<SurveyTaskDirectionResultDto> resultTypes;


    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getShowExpenseReimbursementValue() {
        return showExpenseReimbursementValue;
    }

    public void setShowExpenseReimbursementValue(Boolean showExpenseReimbursementValue) {
        this.showExpenseReimbursementValue = showExpenseReimbursementValue;
    }

    public String getExpenseReimbursementValue() {
        return expenseReimbursementValue;
    }

    public void setExpenseReimbursementValue(String expenseReimbursementValue) {
        this.expenseReimbursementValue = expenseReimbursementValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getDirectionText() {
        return directionText;
    }

    public void setDirectionText(String directionText) {
        this.directionText = directionText;
    }

    public Long getSurveyInvestigatorCaseId() {
        return surveyInvestigatorCaseId;
    }

    public void setSurveyInvestigatorCaseId(Long surveyInvestigatorCaseId) {
        this.surveyInvestigatorCaseId = surveyInvestigatorCaseId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Double getSurveyMoney() {
        return surveyMoney;
    }

    public void setSurveyMoney(Double surveyMoney) {
        this.surveyMoney = surveyMoney;
    }

    public Double getEntrustMoney() {
        return entrustMoney;
    }

    public void setEntrustMoney(Double entrustMoney) {
        this.entrustMoney = entrustMoney;
    }

    public Long getEntrustTaskId() {
        return entrustTaskId;
    }

    public void setEntrustTaskId(Long entrustTaskId) {
        this.entrustTaskId = entrustTaskId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public SurveyInvestigatorCaseDto getSurveyInvestigatorCase() {
        return surveyInvestigatorCase;
    }

    public void setSurveyInvestigatorCase(SurveyInvestigatorCaseDto surveyInvestigatorCase) {
        this.surveyInvestigatorCase = surveyInvestigatorCase;
    }

    public Integer getRegionType() {
        return regionType;
    }

    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }

    public Long getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Long surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
    }

    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
    }

    public Integer getSurveyPriceSource() {
        return surveyPriceSource;
    }

    public void setSurveyPriceSource(Integer surveyPriceSource) {
        this.surveyPriceSource = surveyPriceSource;
    }

    public Integer getEntrustPriceSource() {
        return entrustPriceSource;
    }

    public void setEntrustPriceSource(Integer entrustPriceSource) {
        this.entrustPriceSource = entrustPriceSource;
    }

    public List<SurveyCaseDirectionFileDto> getSurveyCaseDirectionFiles() {
        return surveyCaseDirectionFiles;
    }

    public void setSurveyCaseDirectionFiles(List<SurveyCaseDirectionFileDto> surveyCaseDirectionFiles) {
        this.surveyCaseDirectionFiles = surveyCaseDirectionFiles;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public Long getNewId() {
        return newId;
    }

    public void setNewId(Long newId) {
        this.newId = newId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getSurveyMoneyRemark() {
        return surveyMoneyRemark;
    }

    public void setSurveyMoneyRemark(String surveyMoneyRemark) {
        this.surveyMoneyRemark = surveyMoneyRemark;
    }

    public String getEntrustMoneyRemark() {
        return entrustMoneyRemark;
    }

    public void setEntrustMoneyRemark(String entrustMoneyRemark) {
        this.entrustMoneyRemark = entrustMoneyRemark;
    }

    public Double getOldSurveyMoney() {
        return oldSurveyMoney;
    }

    public void setOldSurveyMoney(Double oldSurveyMoney) {
        this.oldSurveyMoney = oldSurveyMoney;
    }

    public Double getOldEntrustMoney() {
        return oldEntrustMoney;
    }

    public void setOldEntrustMoney(Double oldEntrustMoney) {
        this.oldEntrustMoney = oldEntrustMoney;
    }

    public String getDirectionInfo() {
        return directionInfo;
    }

    public void setDirectionInfo(String directionInfo) {
        this.directionInfo = directionInfo;
    }

    public String getItemDateStr() {
        return itemDateStr;
    }

    public void setItemDateStr(String itemDateStr) {
        this.itemDateStr = itemDateStr;
    }

    public String getItemCityStr() {
        return itemCityStr;
    }

    public void setItemCityStr(String itemCityStr) {
        this.itemCityStr = itemCityStr;
    }

    public String getItemContext() {
        return itemContext;
    }

    public void setItemContext(String itemContext) {
        this.itemContext = itemContext;
    }

    public String getCityStr() {
        return cityStr;
    }

    public void setCityStr(String cityStr) {
        this.cityStr = cityStr;
    }

    public String getAddressStr() {
        return addressStr;
    }

    public void setAddressStr(String addressStr) {
        this.addressStr = addressStr;
    }

    public String getMoney1() {
        return money1;
    }

    public void setMoney1(String money1) {
        this.money1 = money1;
    }

    public String getMoney2() {
        return money2;
    }

    public void setMoney2(String money2) {
        this.money2 = money2;
    }

    public String getMargeStr() {
        return margeStr;
    }

    public void setMargeStr(String margeStr) {
        this.margeStr = margeStr;
    }

    public String getTaskColor() {
        return taskColor;
    }

    public void setTaskColor(String taskColor) {
        this.taskColor = taskColor;
    }

    public String getNewColor() {
        return newColor;
    }

    public void setNewColor(String newColor) {
        this.newColor = newColor;
    }

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
    }

    public Integer getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(Integer medicalNumber) {
        this.medicalNumber = medicalNumber;
    }

    public Long getDirectionResultTypeId() {
        return directionResultTypeId;
    }

    public void setDirectionResultTypeId(Long directionResultTypeId) {
        this.directionResultTypeId = directionResultTypeId;
    }

    public String getDirectionResultTypeName() {
        return directionResultTypeName;
    }

    public void setDirectionResultTypeName(String directionResultTypeName) {
        this.directionResultTypeName = directionResultTypeName;
    }

    public String getDirectionResultTypeCode() {
        return directionResultTypeCode;
    }

    public void setDirectionResultTypeCode(String directionResultTypeCode) {
        this.directionResultTypeCode = directionResultTypeCode;
    }

    public String getSurveyReason() {
        return surveyReason;
    }

    public void setSurveyReason(String surveyReason) {
        this.surveyReason = surveyReason;
    }

    public int getDirectionFilesSize() {
        return directionFilesSize;
    }

    public void setDirectionFilesSize(int directionFilesSize) {
        this.directionFilesSize = directionFilesSize;
    }

    public Integer getReviewOff() {
        return reviewOff;
    }

    public void setReviewOff(Integer reviewOff) {
        this.reviewOff = reviewOff;
    }

    public Date getHuzhuDate() {
        return huzhuDate;
    }

    public void setHuzhuDate(Date huzhuDate) {
        this.huzhuDate = huzhuDate;
    }

    public String getAttr1Obj() {
        return attr1Obj;
    }

    public void setAttr1Obj(String attr1Obj) {
        this.attr1Obj = attr1Obj;
    }

    public String getAttr1ObjName() {
        return attr1ObjName;
    }

    public void setAttr1ObjName(String attr1ObjName) {
        this.attr1ObjName = attr1ObjName;
    }

    public String getAttr2Type() {
        return attr2Type;
    }

    public void setAttr2Type(String attr2Type) {
        this.attr2Type = attr2Type;
    }

    public String getAttr2His() {
        return attr2His;
    }

    public void setAttr2His(String attr2His) {
        this.attr2His = attr2His;
    }

    public String getAttr3Obj() {
        return attr3Obj;
    }

    public void setAttr3Obj(String attr3Obj) {
        this.attr3Obj = attr3Obj;
    }

    public String getAttr4Name1() {
        return attr4Name1;
    }

    public void setAttr4Name1(String attr4Name1) {
        this.attr4Name1 = attr4Name1;
    }

    public String getAttr4Remark1() {
        return attr4Remark1;
    }

    public void setAttr4Remark1(String attr4Remark1) {
        this.attr4Remark1 = attr4Remark1;
    }

    public String getAttr4Name2() {
        return attr4Name2;
    }

    public void setAttr4Name2(String attr4Name2) {
        this.attr4Name2 = attr4Name2;
    }

    public String getAttr4Remark2() {
        return attr4Remark2;
    }

    public void setAttr4Remark2(String attr4Remark2) {
        this.attr4Remark2 = attr4Remark2;
    }

    public int getSun() {
        return sun;
    }

    public void setSun(int sun) {
        this.sun = sun;
    }

    public String getHuzhuColsRemark() {
        return huzhuColsRemark;
    }

    public void setHuzhuColsRemark(String huzhuColsRemark) {
        this.huzhuColsRemark = huzhuColsRemark;
    }

    public String getHuzhuSunStr() {
        return huzhuSunStr;
    }

    public void setHuzhuSunStr(String huzhuSunStr) {
        this.huzhuSunStr = huzhuSunStr;
    }

    public String getSunRemark() {
        return sunRemark;
    }

    public void setSunRemark(String sunRemark) {
        this.sunRemark = sunRemark;
    }

    public SurveyRiskCaseInfoDto getSurveyRiskCaseInfo() {
        return surveyRiskCaseInfo;
    }

    public void setSurveyRiskCaseInfo(SurveyRiskCaseInfoDto surveyRiskCaseInfo) {
        this.surveyRiskCaseInfo = surveyRiskCaseInfo;
    }

    public Boolean getReview() {
        return review;
    }

    public void setReview(Boolean review) {
        this.review = review;
    }

    public int getHaveReimbursement() {
        return haveReimbursement;
    }

    public void setHaveReimbursement(int haveReimbursement) {
        this.haveReimbursement = haveReimbursement;
    }

    public List<SurveyReimbursementFileDto> getSurveyReimbursementFileDtoList() {
        return surveyReimbursementFileDtoList;
    }

    public void setSurveyReimbursementFileDtoList(List<SurveyReimbursementFileDto> surveyReimbursementFileDtoList) {
        this.surveyReimbursementFileDtoList = surveyReimbursementFileDtoList;
    }

    public Long getSurveyAssorgCaseId() {
        return surveyAssorgCaseId;
    }

    public void setSurveyAssorgCaseId(Long surveyAssorgCaseId) {
        this.surveyAssorgCaseId = surveyAssorgCaseId;
    }

    public Integer getHaveFile() {
        return haveFile;
    }

    public void setHaveFile(Integer haveFile) {
        this.haveFile = haveFile;
    }

    public Integer getHaveSound() {
        return haveSound;
    }

    public void setHaveSound(Integer haveSound) {
        this.haveSound = haveSound;
    }

    public Integer getSurveyCaseDirectionFilesSize() {
        return surveyCaseDirectionFilesSize;
    }

    public void setSurveyCaseDirectionFilesSize(Integer surveyCaseDirectionFilesSize) {
        this.surveyCaseDirectionFilesSize = surveyCaseDirectionFilesSize;
    }

    public String getRegionNameId() {
        return regionNameId;
    }

    public void setRegionNameId(String regionNameId) {
        this.regionNameId = regionNameId;
    }

    public Double getHisScore() {
        return hisScore;
    }

    public void setHisScore(Double hisScore) {
        this.hisScore = hisScore;
    }

    public Double getChannelFeeCur() {
        return channelFeeCur;
    }

    public void setChannelFeeCur(Double channelFeeCur) {
        this.channelFeeCur = channelFeeCur;
    }

    public Double getChannelFeeSent() {
        return channelFeeSent;
    }

    public void setChannelFeeSent(Double channelFeeSent) {
        this.channelFeeSent = channelFeeSent;
    }

    public Integer getInvalidState() {
        return invalidState;
    }

    public void setInvalidState(Integer invalidState) {
        this.invalidState = invalidState;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public SurveyChannelCostNew getSurveyChannelCostNew() {
        return surveyChannelCostNew;
    }

    public void setSurveyChannelCostNew(SurveyChannelCostNew surveyChannelCostNew) {
        this.surveyChannelCostNew = surveyChannelCostNew;
    }

    public String getRealDirectionName() {
        return realDirectionName;
    }

    public void setRealDirectionName(String realDirectionName) {
        this.realDirectionName = realDirectionName;
    }

    public String getSurveyCno() {
        return surveyCno;
    }

    public void setSurveyCno(String surveyCno) {
        this.surveyCno = surveyCno;
    }

    public Double getAccMoney() {
        return accMoney;
    }

    public void setAccMoney(Double accMoney) {
        this.accMoney = accMoney;
    }

    public Double getAccScore() {
        return accScore;
    }

    public void setAccScore(Double accScore) {
        this.accScore = accScore;
    }

    public Double getScoreRate() {
        return scoreRate;
    }

    public void setScoreRate(Double scoreRate) {
        this.scoreRate = scoreRate;
    }

    public Long getClockId() {
        return clockId;
    }

    public void setClockId(Long clockId) {
        this.clockId = clockId;
    }

    public Long getAddressX() {
        return addressX;
    }

    public void setAddressX(Long addressX) {
        this.addressX = addressX;
    }

    public Long getAddressY() {
        return addressY;
    }

    public void setAddressY(Long addressY) {
        this.addressY = addressY;
    }

    public Integer getOrgPoint() {
        return orgPoint;
    }

    public void setOrgPoint(Integer orgPoint) {
        this.orgPoint = orgPoint;
    }


    public List<SurveyTaskInfoContentDto> getTaskInfoContents() {
        return taskInfoContents;
    }

    public void setTaskInfoContents(List<SurveyTaskInfoContentDto> taskInfoContents) {
        this.taskInfoContents = taskInfoContents;
    }

    public List<SurveyTaskDirectionResultDto> getResultTypes() {
        return resultTypes;
    }

    public void setResultTypes(List<SurveyTaskDirectionResultDto> resultTypes) {
        this.resultTypes = resultTypes;
    }

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }

    public Integer getMaterRaw() {
        return materRaw;
    }

    public void setMaterRaw(Integer materRaw) {
        this.materRaw = materRaw;
    }
}