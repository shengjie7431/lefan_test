package com.lefancrm.apicenter.model;

import java.util.Date;

/**
 * @author EDZ
 */
public class SurveyUserClock {

    /**
     * id
     */
    private Long id;

    /**
     * 人员id
     */
    private Long surveyUserId;

    /**
     * 调查员姓名
     */
    private String surveyUserName;

    /**
     * 打卡地址坐标X
     */
    private Double clockLbsX;

    /**
     * 打卡地址坐标Y
     */
    private Double clockLbsY;

    /**
     * 打卡详细地址
     */
    private String address;

    private String addressName;

    /**
     * 打卡时间
     */
    private Date clockTime;

    /**
     * 打卡员工所属机构ID
     */
    private Long surveyOrgId;

    /**
     * 打卡员工所属机构名称
     */
    private String surveyOrgName;

    private Long reId;

    private Long reInfoId;

    /**
     * 地点打卡备注
     */
    private String addressDesc;


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

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
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