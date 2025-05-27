package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyClockCase;
import com.lefancrm.apicenter.model.SurveyClockReInfo;

import java.util.Date;
import java.util.List;

/**
 * @author EDZ
 */
public class SurveyUserClockDto {
    private Long id;

    private Long surveyUserId;

    private String surveyUserName;

    private Double clockLbsX;

    private Double clockLbsY;

    private String address;

    private String addressName;

    /**
     * 打卡时间
     */
    private Date clockTime;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Long reId;

    private Long reInfoId;

    private List<SurveyClockCase> clockCaseList;

    /**
     * 报销费用详情
     */
    private SurveyClockReInfo surveyClockReInfo;

    private SurveyClockCase surveyClockCase;

    /**
     * 打卡次数
     */
    private Integer recordCount;

    /**
     * 案件次数
     */
    private Integer caseCount;

    /**
     * 被调查人
     */
    private String surveyPerson;

    /**
     * 报销费用
     */
    private Double reTotalMoney;

    /**
     * 第几次打卡
     */
    private String punchInRecord;

    /**
     * 地点打卡备注
     */
    private String addressDesc;

    private List<SurveyCostApplyDto> surveyCostApplyDto;

    private Boolean red;
    private Boolean edit;

    private Integer sameDaySort;//当日打卡编号

    private String province;

    private String city;

    private String district;

    private Double beforeDistance;

    private Double homeDistance;

    private Integer lastToday;


    private Long clockCaseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyUserId() {
        return surveyUserId;
    }

    public void setSurveyUserId(Long surveyUserId) {
        this.surveyUserId = surveyUserId;
    }

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
    }

    public Double getClockLbsX() {
        return clockLbsX;
    }

    public void setClockLbsX(Double clockLbsX) {
        this.clockLbsX = clockLbsX;
    }

    public Double getClockLbsY() {
        return clockLbsY;
    }

    public void setClockLbsY(Double clockLbsY) {
        this.clockLbsY = clockLbsY;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Date getClockTime() {
        return clockTime;
    }

    public void setClockTime(Date clockTime) {
        this.clockTime = clockTime;
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

    public Long getReId() {
        return reId;
    }

    public void setReId(Long reId) {
        this.reId = reId;
    }

    public Long getReInfoId() {
        return reInfoId;
    }

    public void setReInfoId(Long reInfoId) {
        this.reInfoId = reInfoId;
    }

    public List<SurveyClockCase> getClockCaseList() {
        return clockCaseList;
    }

    public void setClockCaseList(List<SurveyClockCase> clockCaseList) {
        this.clockCaseList = clockCaseList;
    }

    public SurveyClockReInfo getSurveyClockReInfo() {
        return surveyClockReInfo;
    }

    public void setSurveyClockReInfo(SurveyClockReInfo surveyClockReInfo) {
        this.surveyClockReInfo = surveyClockReInfo;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(Integer caseCount) {
        this.caseCount = caseCount;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public Double getReTotalMoney() {
        return reTotalMoney;
    }

    public void setReTotalMoney(Double reTotalMoney) {
        this.reTotalMoney = reTotalMoney;
    }

    public String getPunchInRecord() {
        return punchInRecord;
    }

    public void setPunchInRecord(String punchInRecord) {
        this.punchInRecord = punchInRecord;
    }

    public SurveyClockCase getSurveyClockCase() {
        return surveyClockCase;
    }

    public void setSurveyClockCase(SurveyClockCase surveyClockCase) {
        this.surveyClockCase = surveyClockCase;
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
    }

    public List<SurveyCostApplyDto> getSurveyCostApplyDto() {
        return surveyCostApplyDto;
    }

    public void setSurveyCostApplyDto(List<SurveyCostApplyDto> surveyCostApplyDto) {
        this.surveyCostApplyDto = surveyCostApplyDto;
    }

    public Boolean getRed() {
        return red;
    }

    public void setRed(Boolean red) {
        this.red = red;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public Integer getSameDaySort() {
        return sameDaySort;
    }

    public void setSameDaySort(Integer sameDaySort) {
        this.sameDaySort = sameDaySort;
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

    public Double getBeforeDistance() {
        return beforeDistance;
    }

    public void setBeforeDistance(Double beforeDistance) {
        this.beforeDistance = beforeDistance;
    }

    public Double getHomeDistance() {
        return homeDistance;
    }

    public void setHomeDistance(Double homeDistance) {
        this.homeDistance = homeDistance;
    }

    public Integer getLastToday() {
        return lastToday;
    }

    public void setLastToday(Integer lastToday) {
        this.lastToday = lastToday;
    }

    public Long getClockCaseId() {
        return clockCaseId;
    }

    public void setClockCaseId(Long clockCaseId) {
        this.clockCaseId = clockCaseId;
    }
}