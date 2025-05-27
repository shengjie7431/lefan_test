package com.lefancrm.apicenter.dto.hzReport;

/**
 * Created by zhuxia on 2020/5/11.
 * 查得率报表
 */
public class CaseDirectionDto {

    private String surveyOrgName;//调查机构
    private Long surveyOrgId;//调查机构
    private Integer orgLevel;//调查机构 级别

    private String entrustOrgIds;//互助平台
    private String entrustOrgName;//委托机构

    private String surveyUserName;//调查员
    private Long surveyUserId;//调查员

    private Integer surveyState;//案件状态 ：24、平台复审通过；28、保司终审通过
    private String surveyStateName;

    private Integer orgType;//机构类型：1、A类 2、B类
    private String orgTypeName;//机构类型名称：1、A类 2、B类

    private Integer fileNum;        //需查得方向总数 （是否获得屏拍或者纸质材料）
    private Integer haveFileNum;    //查得总数
    private Double haveFileRate;     //查得率

    private Integer soundNum;      //需录音方向总数 （是否获得录音）
    private Integer haveSoundNum;    //查得录音方向总数
    private Double haveSoundRate;      //录音率


    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
    }

    public Long getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Long surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
    }

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getEntrustOrgIds() {
        return entrustOrgIds;
    }

    public void setEntrustOrgIds(String entrustOrgIds) {
        this.entrustOrgIds = entrustOrgIds;
    }

    public String getEntrustOrgName() {
        return entrustOrgName;
    }

    public void setEntrustOrgName(String entrustOrgName) {
        this.entrustOrgName = entrustOrgName;
    }

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
    }

    public Long getSurveyUserId() {
        return surveyUserId;
    }

    public void setSurveyUserId(Long surveyUserId) {
        this.surveyUserId = surveyUserId;
    }

    public Integer getSurveyState() {
        return surveyState;
    }

    public void setSurveyState(Integer surveyState) {
        this.surveyState = surveyState;
    }

    public String getSurveyStateName() {
        return surveyStateName;
    }

    public void setSurveyStateName(String surveyStateName) {
        this.surveyStateName = surveyStateName;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public String getOrgTypeName() {
        return orgTypeName;
    }

    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }

    public Integer getHaveFileNum() {
        return haveFileNum;
    }

    public void setHaveFileNum(Integer haveFileNum) {
        this.haveFileNum = haveFileNum;
    }

    public Double getHaveFileRate() {
        return haveFileRate;
    }

    public void setHaveFileRate(Double haveFileRate) {
        this.haveFileRate = haveFileRate;
    }

    public Integer getSoundNum() {
        return soundNum;
    }

    public void setSoundNum(Integer soundNum) {
        this.soundNum = soundNum;
    }

    public Integer getHaveSoundNum() {
        return haveSoundNum;
    }

    public void setHaveSoundNum(Integer haveSoundNum) {
        this.haveSoundNum = haveSoundNum;
    }

    public Double getHaveSoundRate() {
        return haveSoundRate;
    }

    public void setHaveSoundRate(Double haveSoundRate) {
        this.haveSoundRate = haveSoundRate;
    }
}
