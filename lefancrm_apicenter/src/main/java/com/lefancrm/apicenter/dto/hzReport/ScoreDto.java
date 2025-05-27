package com.lefancrm.apicenter.dto.hzReport;

import com.lefancrm.apicenter.model.SurveyInvestigatorCaseType;
import com.lefancrm.apicenter.model.SurveyRiskCaseInfo;
import com.lefancrm.apicenter.model.SurveyTaskType;

import java.util.Date;
import java.util.List;

/**
 * Created by zhuxia on 2020/4/14.
 * 互助积分
 */
public class ScoreDto {

    private String surveyOrgName;//调查机构

    private Long surveyOrgId;//调查机构

    private Integer orgLevel;//调查机构 级别

    private String entrustOrgName;//委托机构

    private String surveyUserName;//调查员

    private Long surveyUserId;//调查员

    private Integer caseState;//案件类型：1、单点；2、单点+单点；3、全案；4、全案+单点；5、全案+全案

    private String caseStateName;

    private Integer orgCaseState;//机构案件类型：1、单点；2、全案

    private String orgCaseStateName;

    private Integer surveyState;//案件状态 ：24、平台复审通过；28、保司终审通过

    private String surveyStateName;

    private Integer caseNum;//案件数量

    private Double surveyScore;//调查总积分

    private Double sunScore;//阳性总积分

    private Double scoreSum;//总积分

    private List<SurveyRiskCaseInfo> surveyRiskCaseInfoList;//案件信息

    private String entrustOrgIds;//互助平台

    //案件信息
    private String surveyNo;
    private String surveyPerson;
    private Long surveyInfoId;

    //调查员案件信息
    private Long investigatorCaseId;
    private Date userAssignDate;
    private Date userCreportDate;
    private Date userEndTime;
    private Integer userDays;
    private Double userBaseScore;//最终分值
    private Double userSunScore;
    private Double userScore;
    private Double overdueAgingRate; //超期考核时效
    private Double assessScore; //考核前总积分
    private Double assessSunScore; //考核前总阳性分
    private List<SurveyInvestigatorCaseType> tasks;
    private Double sunMoneyBs; //保司阳性奖励
    private String staffOpinion;//单条案件意见
    private Integer staffOpinionState;
    //机构案件
    private Long assignCaseId;
    private Date orgAssignDate;
    private Date orgCreportDate;
    private Date orgEndTime;
    private Integer orgDays;
    private Double orgBaseScore;
    private Double orgSunScore;
    private Double orgScore;
    private String orgTaskRemark;
    private List<SurveyTaskType> surveyTaskTypes;

    private List<ScoreDto> children;

    private Integer orgType;//机构类型：1、A类 2、B类
    private String orgTypeName;//机构类型名称：1、A类 2、B类

    private Integer channelCaseNum; //渠道案件数量（所有方向都为渠道调查的案件数量）

    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
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

    public Integer getCaseState() {
        return caseState;
    }

    public void setCaseState(Integer caseState) {
        this.caseState = caseState;
    }

    public Integer getOrgCaseState() {
        return orgCaseState;
    }

    public void setOrgCaseState(Integer orgCaseState) {
        this.orgCaseState = orgCaseState;
    }

    public Integer getSurveyState() {
        return surveyState;
    }

    public void setSurveyState(Integer surveyState) {
        this.surveyState = surveyState;
    }

    public Integer getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(Integer caseNum) {
        this.caseNum = caseNum;
    }

    public Double getSurveyScore() {
        return surveyScore;
    }

    public void setSurveyScore(Double surveyScore) {
        this.surveyScore = surveyScore;
    }

    public Double getSunScore() {
        return sunScore;
    }

    public void setSunScore(Double sunScore) {
        this.sunScore = sunScore;
    }

    public Double getScoreSum() {
        return scoreSum;
    }

    public void setScoreSum(Double scoreSum) {
        this.scoreSum = scoreSum;
    }

    public List<SurveyRiskCaseInfo> getSurveyRiskCaseInfoList() {
        return surveyRiskCaseInfoList;
    }

    public void setSurveyRiskCaseInfoList(List<SurveyRiskCaseInfo> surveyRiskCaseInfoList) {
        this.surveyRiskCaseInfoList = surveyRiskCaseInfoList;
    }

    public Long getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Long surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
    }

    public Long getSurveyUserId() {
        return surveyUserId;
    }

    public void setSurveyUserId(Long surveyUserId) {
        this.surveyUserId = surveyUserId;
    }

    public String getCaseStateName() {
        return caseStateName;
    }

    public void setCaseStateName(String caseStateName) {
        this.caseStateName = caseStateName;
    }

    public String getOrgCaseStateName() {
        return orgCaseStateName;
    }

    public void setOrgCaseStateName(String orgCaseStateName) {
        this.orgCaseStateName = orgCaseStateName;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Long getInvestigatorCaseId() {
        return investigatorCaseId;
    }

    public void setInvestigatorCaseId(Long investigatorCaseId) {
        this.investigatorCaseId = investigatorCaseId;
    }

    public Date getUserAssignDate() {
        return userAssignDate;
    }

    public void setUserAssignDate(Date userAssignDate) {
        this.userAssignDate = userAssignDate;
    }

    public Date getUserCreportDate() {
        return userCreportDate;
    }

    public void setUserCreportDate(Date userCreportDate) {
        this.userCreportDate = userCreportDate;
    }

    public Date getUserEndTime() {
        return userEndTime;
    }

    public void setUserEndTime(Date userEndTime) {
        this.userEndTime = userEndTime;
    }

    public Integer getUserDays() {
        return userDays;
    }

    public void setUserDays(Integer userDays) {
        this.userDays = userDays;
    }

    public Double getUserBaseScore() {
        return userBaseScore;
    }

    public void setUserBaseScore(Double userBaseScore) {
        this.userBaseScore = userBaseScore;
    }

    public Double getUserSunScore() {
        return userSunScore;
    }

    public void setUserSunScore(Double userSunScore) {
        this.userSunScore = userSunScore;
    }

    public Double getUserScore() {
        return userScore;
    }

    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }

    public List<SurveyInvestigatorCaseType> getTasks() {
        return tasks;
    }

    public void setTasks(List<SurveyInvestigatorCaseType> tasks) {
        this.tasks = tasks;
    }

    public Long getAssignCaseId() {
        return assignCaseId;
    }

    public void setAssignCaseId(Long assignCaseId) {
        this.assignCaseId = assignCaseId;
    }

    public Date getOrgAssignDate() {
        return orgAssignDate;
    }

    public void setOrgAssignDate(Date orgAssignDate) {
        this.orgAssignDate = orgAssignDate;
    }

    public Date getOrgCreportDate() {
        return orgCreportDate;
    }

    public void setOrgCreportDate(Date orgCreportDate) {
        this.orgCreportDate = orgCreportDate;
    }

    public Date getOrgEndTime() {
        return orgEndTime;
    }

    public void setOrgEndTime(Date orgEndTime) {
        this.orgEndTime = orgEndTime;
    }

    public Integer getOrgDays() {
        return orgDays;
    }

    public void setOrgDays(Integer orgDays) {
        this.orgDays = orgDays;
    }

    public Double getOrgBaseScore() {
        return orgBaseScore;
    }

    public void setOrgBaseScore(Double orgBaseScore) {
        this.orgBaseScore = orgBaseScore;
    }

    public Double getOrgSunScore() {
        return orgSunScore;
    }

    public void setOrgSunScore(Double orgSunScore) {
        this.orgSunScore = orgSunScore;
    }

    public Double getOrgScore() {
        return orgScore;
    }

    public void setOrgScore(Double orgScore) {
        this.orgScore = orgScore;
    }

    public List<SurveyTaskType> getSurveyTaskTypes() {
        return surveyTaskTypes;
    }

    public void setSurveyTaskTypes(List<SurveyTaskType> surveyTaskTypes) {
        this.surveyTaskTypes = surveyTaskTypes;
    }

    public String getSurveyStateName() {
        return surveyStateName;
    }

    public void setSurveyStateName(String surveyStateName) {
        this.surveyStateName = surveyStateName;
    }

    public String getOrgTaskRemark() {
        return orgTaskRemark;
    }

    public void setOrgTaskRemark(String orgTaskRemark) {
        this.orgTaskRemark = orgTaskRemark;
    }

    public List<ScoreDto> getChildren() {
        return children;
    }

    public void setChildren(List<ScoreDto> children) {
        this.children = children;
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

    public Double getOverdueAgingRate() {
        return overdueAgingRate;
    }

    public void setOverdueAgingRate(Double overdueAgingRate) {
        this.overdueAgingRate = overdueAgingRate;
    }

    public Double getAssessSunScore() {
        return assessSunScore;
    }

    public void setAssessSunScore(Double assessSunScore) {
        this.assessSunScore = assessSunScore;
    }

    public Double getAssessScore() {
        return assessScore;
    }

    public void setAssessScore(Double assessScore) {
        this.assessScore = assessScore;
    }

    public Double getSunMoneyBs() {
        return sunMoneyBs;
    }

    public void setSunMoneyBs(Double sunMoneyBs) {
        this.sunMoneyBs = sunMoneyBs;
    }

    public String getStaffOpinion() {
        return staffOpinion;
    }

    public void setStaffOpinion(String staffOpinion) {
        this.staffOpinion = staffOpinion;
    }

    public Integer getStaffOpinionState() {
        return staffOpinionState;
    }

    public void setStaffOpinionState(Integer staffOpinionState) {
        this.staffOpinionState = staffOpinionState;
    }

    public Integer getChannelCaseNum() {
        return channelCaseNum;
    }

    public void setChannelCaseNum(Integer channelCaseNum) {
        this.channelCaseNum = channelCaseNum;
    }
}
