package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SurveyInvestigatorCase {
    private Long id;

    private Long surveyId;

    private Long surveyInfoId;

    private Double surveyTaskMoney;

    private Double entrustTaskMoney;

    private Double entrustReLosses;

    private Double surveryReLosses;

    private String entrustReLossesRemark;

    private String surveryReLoossesRemark;

    private Long surveyUserId;

    private String surveyUserName;

    private Integer surveyUserType;

    private String surveyRemark;

    private Integer surveyState;

    private String surveyStateName;

    private Date assignDate;

    private Date acceptDate;

    private String surveyInfo;

    private String surveyItem;

    private Long entrustOrgId;

    private String entrustOrgName;

    private Long creportId;

    private String creportName;

    private Integer creportState;

    private Date creportDate;

    private Integer surveryUserOrgType;

    private Integer isSun;

    private Integer scoreState;

    private Integer scoreLevel;

    private Integer userCashState;

    private String searchCondition;

    private Integer deleteFlag;

    private Date surveyEndTime;

    private String scoreRemark;

    private String surveyTaskRemark;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Integer isDirectionSuccess;

    private Integer sunState;

    private String sunRemark ;

    private Integer returnState;

    private Integer surveyPay;

    private String surveySummary;
    private Integer reviewOff;

    private Double score; //分值

    private Date sunTime;

    private Integer sunType;

    private Long sunUserId;

    private String sunUserName;

    private String specialRemark;

    private Long surveyAssorgCaseId;

    private Double scoreSun;

    private Double bsScore;

    private Double otherScore;

    private Integer agingDay;

    private Double overdueAgingRate;

    private Double assessScore;

    private Double assessSunScore;

    private Double assessBsScore;

    private Double assessOtherScore;

    List<Map<String,Object>> urgPreList;

    private Integer oveDay;//超期天数

    private Integer assDay;//考核时效survey/case/info

    private Double agingCheck;
    private Double agingReal;
    private Double agingOver;
    /**
     * 阳性奖励
     */
    private Double sunMoney;

    /**
     * 调查员类型
     */
    private Integer investigatorType;

    private Integer newCase;

    private Boolean showKey = false;

    //扩展表
    private SurveyInvestigatorCaseSub surveyInvestigatorCaseSub;

    public Double getAgingCheck() {
        return agingCheck;
    }

    public void setAgingCheck(Double agingCheck) {
        this.agingCheck = agingCheck;
    }

    public Double getAgingReal() {
        return agingReal;
    }

    public void setAgingReal(Double agingReal) {
        this.agingReal = agingReal;
    }

    public Double getAgingOver() {
        return agingOver;
    }

    public void setAgingOver(Double agingOver) {
        this.agingOver = agingOver;
    }

    public Integer getOveDay() {
        return oveDay;
    }

    public void setOveDay(Integer oveDay) {
        this.oveDay = oveDay;
    }

    public Integer getAssDay() {
        return assDay;
    }

    public void setAssDay(Integer assDay) {
        this.assDay = assDay;
    }

    public Integer getScoreState() {
        return scoreState;
    }

    public void setScoreState(Integer scoreState) {
        this.scoreState = scoreState;
    }

    public Integer getScoreLevel() {
        return scoreLevel;
    }

    public void setScoreLevel(Integer scoreLevel) {
        this.scoreLevel = scoreLevel;
    }

    public Integer getUserCashState() {
        return userCashState;
    }

    public void setUserCashState(Integer userCashState) {
        this.userCashState = userCashState;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getSurveyTaskMoney() {
        return surveyTaskMoney;
    }

    public void setSurveyTaskMoney(Double surveyTaskMoney) {
        this.surveyTaskMoney = surveyTaskMoney;
    }

    public Double getEntrustTaskMoney() {
        return entrustTaskMoney;
    }

    public void setEntrustTaskMoney(Double entrustTaskMoney) {
        this.entrustTaskMoney = entrustTaskMoney;
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

    public Integer getSurveyUserType() {
        return surveyUserType;
    }

    public void setSurveyUserType(Integer surveyUserType) {
        this.surveyUserType = surveyUserType;
    }

    public String getSurveyRemark() {
        return surveyRemark;
    }

    public void setSurveyRemark(String surveyRemark) {
        this.surveyRemark = surveyRemark;
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

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getSurveyInfo() {
        return surveyInfo;
    }

    public void setSurveyInfo(String surveyInfo) {
        this.surveyInfo = surveyInfo;
    }

    public String getSurveyItem() {
        return surveyItem;
    }

    public void setSurveyItem(String surveyItem) {
        this.surveyItem = surveyItem;
    }

    public Long getEntrustOrgId() {
        return entrustOrgId;
    }

    public void setEntrustOrgId(Long entrustOrgId) {
        this.entrustOrgId = entrustOrgId;
    }

    public String getEntrustOrgName() {
        return entrustOrgName;
    }

    public void setEntrustOrgName(String entrustOrgName) {
        this.entrustOrgName = entrustOrgName;
    }

    public Long getCreportId() {
        return creportId;
    }

    public void setCreportId(Long creportId) {
        this.creportId = creportId;
    }

    public String getCreportName() {
        return creportName;
    }

    public void setCreportName(String creportName) {
        this.creportName = creportName;
    }

    public Integer getCreportState() {
        return creportState;
    }

    public void setCreportState(Integer creportState) {
        this.creportState = creportState;
    }

    public Date getCreportDate() {
        return creportDate;
    }

    public void setCreportDate(Date creportDate) {
        this.creportDate = creportDate;
    }

    public Integer getSurveryUserOrgType() {
        return surveryUserOrgType;
    }

    public void setSurveryUserOrgType(Integer surveryUserOrgType) {
        this.surveryUserOrgType = surveryUserOrgType;
    }

    public Integer getIsSun() {
        return isSun;
    }

    public void setIsSun(Integer isSun) {
        this.isSun = isSun;
    }

    public Double getEntrustReLosses() {
        return entrustReLosses;
    }

    public void setEntrustReLosses(Double entrustReLosses) {
        this.entrustReLosses = entrustReLosses;
    }

    public Double getSurveryReLosses() {
        return surveryReLosses;
    }

    public void setSurveryReLosses(Double surveryReLosses) {
        this.surveryReLosses = surveryReLosses;
    }

    public String getEntrustReLossesRemark() {
        return entrustReLossesRemark;
    }

    public void setEntrustReLossesRemark(String entrustReLossesRemark) {
        this.entrustReLossesRemark = entrustReLossesRemark;
    }

    public String getSurveryReLoossesRemark() {
        return surveryReLoossesRemark;
    }

    public void setSurveryReLoossesRemark(String surveryReLoossesRemark) {
        this.surveryReLoossesRemark = surveryReLoossesRemark;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getSurveyEndTime() {
        return surveyEndTime;
    }

    public void setSurveyEndTime(Date surveyEndTime) {
        this.surveyEndTime = surveyEndTime;
    }

    public String getScoreRemark() {
        return scoreRemark;
    }

    public void setScoreRemark(String scoreRemark) {
        this.scoreRemark = scoreRemark;
    }

    public String getSurveyTaskRemark() {
        return surveyTaskRemark;
    }

    public void setSurveyTaskRemark(String surveyTaskRemark) {
        this.surveyTaskRemark = surveyTaskRemark;
    }

    public Integer getIsDirectionSuccess() {
        return isDirectionSuccess;
    }

    public void setIsDirectionSuccess(Integer isDirectionSuccess) {
        this.isDirectionSuccess = isDirectionSuccess;
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

    public Integer getSunState() {
        return sunState;
    }

    public void setSunState(Integer sunState) {
        this.sunState = sunState;
    }

    public String getSunRemark() {
        return sunRemark;
    }

    public void setSunRemark(String sunRemark) {
        this.sunRemark = sunRemark;
    }

    public Integer getReturnState() {
        return returnState;
    }

    public void setReturnState(Integer returnState) {
        this.returnState = returnState;
    }

    public Integer getSurveyPay() {
        return surveyPay;
    }

    public void setSurveyPay(Integer surveyPay) {
        this.surveyPay = surveyPay;
    }

    public String getSurveySummary() {
        return surveySummary;
    }

    public void setSurveySummary(String surveySummary) {
        this.surveySummary = surveySummary;
    }

    public Integer getReviewOff() {
        return reviewOff;
    }

    public void setReviewOff(Integer reviewOff) {
        this.reviewOff = reviewOff;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getSunTime() {
        return sunTime;
    }

    public void setSunTime(Date sunTime) {
        this.sunTime = sunTime;
    }

    public Integer getSunType() {
        return sunType;
    }

    public void setSunType(Integer sunType) {
        this.sunType = sunType;
    }

    public Long getSunUserId() {
        return sunUserId;
    }

    public void setSunUserId(Long sunUserId) {
        this.sunUserId = sunUserId;
    }

    public String getSunUserName() {
        return sunUserName;
    }

    public void setSunUserName(String sunUserName) {
        this.sunUserName = sunUserName;
    }

    public String getSpecialRemark() {
        return specialRemark;
    }

    public void setSpecialRemark(String specialRemark) {
        this.specialRemark = specialRemark;
    }

    public Long getSurveyAssorgCaseId() {
        return surveyAssorgCaseId;
    }

    public void setSurveyAssorgCaseId(Long surveyAssorgCaseId) {
        this.surveyAssorgCaseId = surveyAssorgCaseId;
    }

    public Double getScoreSun() {
        return scoreSun;
    }

    public void setScoreSun(Double scoreSun) {
        this.scoreSun = scoreSun;
    }

    public Double getBsScore() {
        return bsScore;
    }

    public void setBsScore(Double bsScore) {
        this.bsScore = bsScore;
    }

    public Double getOtherScore() {
        return otherScore;
    }

    public void setOtherScore(Double otherScore) {
        this.otherScore = otherScore;
    }

    public Integer getAgingDay() {
        return agingDay;
    }

    public void setAgingDay(Integer agingDay) {
        this.agingDay = agingDay;
    }

    public Double getOverdueAgingRate() {
        return overdueAgingRate;
    }

    public void setOverdueAgingRate(Double overdueAgingRate) {
        this.overdueAgingRate = overdueAgingRate;
    }

    public Double getAssessScore() {
        return assessScore;
    }

    public void setAssessScore(Double assessScore) {
        this.assessScore = assessScore;
    }

    public Double getAssessSunScore() {
        return assessSunScore;
    }

    public void setAssessSunScore(Double assessSunScore) {
        this.assessSunScore = assessSunScore;
    }

    public Double getAssessBsScore() {
        return assessBsScore;
    }

    public void setAssessBsScore(Double assessBsScore) {
        this.assessBsScore = assessBsScore;
    }

    public Double getAssessOtherScore() {
        return assessOtherScore;
    }

    public void setAssessOtherScore(Double assessOtherScore) {
        this.assessOtherScore = assessOtherScore;
    }

    public List<Map<String, Object>> getUrgPreList() {
        return urgPreList;
    }

    public void setUrgPreList(List<Map<String, Object>> urgPreList) {
        this.urgPreList = urgPreList;
    }

    public Double getSunMoney() {
        return sunMoney;
    }

    public void setSunMoney(Double sunMoney) {
        this.sunMoney = sunMoney;
    }

    public Integer getInvestigatorType() {
        return investigatorType;
    }

    public void setInvestigatorType(Integer investigatorType) {
        this.investigatorType = investigatorType;
    }

    public SurveyInvestigatorCaseSub getSurveyInvestigatorCaseSub() {
        return surveyInvestigatorCaseSub;
    }

    public void setSurveyInvestigatorCaseSub(SurveyInvestigatorCaseSub surveyInvestigatorCaseSub) {
        this.surveyInvestigatorCaseSub = surveyInvestigatorCaseSub;
    }

    public Integer getNewCase() {
        return newCase;
    }

    public void setNewCase(Integer newCase) {
        this.newCase = newCase;
    }

    public Boolean getShowKey() {
        return showKey;
    }

    public void setShowKey(Boolean showKey) {
        this.showKey = showKey;
    }
}