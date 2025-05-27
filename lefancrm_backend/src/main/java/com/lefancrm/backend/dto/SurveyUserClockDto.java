package com.lefancrm.backend.dto;

import java.util.Date;
import java.util.List;

public class SurveyUserClockDto {
    private Long id;

    private Long surveyUserId;

    private String surveyUserName;

    private Double clockLbsX;

    private Double clockLbsY;

    private String address;

    private String addressName;

    private Date clockTime;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Long reId;

    private Long reInfoId;

    private List<SurveyClockCaseDto> clockCaseList;

    private SurveyClockReInfoDto surveyClockReInfo;

    private String province;

    private String city;

    private String district;

    private Double beforeDistance;

    private Double homeDistance;

    private Integer lastToday;

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

    public List<SurveyClockCaseDto> getClockCaseList() {
        return clockCaseList;
    }

    public void setClockCaseList(List<SurveyClockCaseDto> clockCaseList) {
        this.clockCaseList = clockCaseList;
    }

    public SurveyClockReInfoDto getSurveyClockReInfo() {
        return surveyClockReInfo;
    }

    public void setSurveyClockReInfo(SurveyClockReInfoDto surveyClockReInfo) {
        this.surveyClockReInfo = surveyClockReInfo;
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
}