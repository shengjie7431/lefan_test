package com.lefancrm.backend.dto;

import java.util.List;

/**
 * Created by lixianfeng on 2019/7/15.
 */
public class TemplateDataZD {
    private String trusteeOrgName;
    private String entrustDate;
    private String endDate;
    private String entrustUserName;
    private String surveyUserName;
    private String idNumber;
    private String numberTypeStr;
    private String claimsNo;
    private String safeDate;
    private String surveyUserDate;
    private String surveyItem;
    private List<SurveyCaseDirectionDto> directions;
    private String directionInfo;
    private String directionResult;
    private String fileMidInfo;
    private String userSignDate;
    private String surveyManagerName;
    private String managerSignDate;
    private String totalMoney;

    public String getTrusteeOrgName() {
        return trusteeOrgName;
    }

    public void setTrusteeOrgName(String trusteeOrgName) {
        this.trusteeOrgName = trusteeOrgName;
    }

    public String getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(String entrustDate) {
        this.entrustDate = entrustDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getClaimsNo() {
        return claimsNo;
    }

    public void setClaimsNo(String claimsNo) {
        this.claimsNo = claimsNo;
    }

    public String getSafeDate() {
        return safeDate;
    }

    public void setSafeDate(String safeDate) {
        this.safeDate = safeDate;
    }

    public String getSurveyUserDate() {
        return surveyUserDate;
    }

    public void setSurveyUserDate(String surveyUserDate) {
        this.surveyUserDate = surveyUserDate;
    }

    public String getSurveyItem() {
        return surveyItem;
    }

    public void setSurveyItem(String surveyItem) {
        this.surveyItem = surveyItem;
    }

    public List<SurveyCaseDirectionDto> getDirections() {
        return directions;
    }

    public void setDirections(List<SurveyCaseDirectionDto> directions) {
        this.directions = directions;
    }

    public String getDirectionInfo() {
        return directionInfo;
    }

    public void setDirectionInfo(String directionInfo) {
        this.directionInfo = directionInfo;
    }

    public String getDirectionResult() {
        return directionResult;
    }

    public void setDirectionResult(String directionResult) {
        this.directionResult = directionResult;
    }

    public String getFileMidInfo() {
        return fileMidInfo;
    }

    public void setFileMidInfo(String fileMidInfo) {
        this.fileMidInfo = fileMidInfo;
    }

    public String getUserSignDate() {
        return userSignDate;
    }

    public void setUserSignDate(String userSignDate) {
        this.userSignDate = userSignDate;
    }

    public String getSurveyManagerName() {
        return surveyManagerName;
    }

    public void setSurveyManagerName(String surveyManagerName) {
        this.surveyManagerName = surveyManagerName;
    }

    public String getManagerSignDate() {
        return managerSignDate;
    }

    public void setManagerSignDate(String managerSignDate) {
        this.managerSignDate = managerSignDate;
    }

    public String getEntrustUserName() {
        return entrustUserName;
    }

    public void setEntrustUserName(String entrustUserName) {
        this.entrustUserName = entrustUserName;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getNumberTypeStr() {
        return numberTypeStr;
    }

    public void setNumberTypeStr(String numberTypeStr) {
        this.numberTypeStr = numberTypeStr;
    }
}
