package com.lefancrm.apicenter.dto.hzReport;

import com.lefancrm.apicenter.model.SurveyAssignOrgType;
import com.lefancrm.apicenter.model.SurveyInvestigatorCaseType;
import com.lefancrm.apicenter.model.SurveyRiskCaseInfo;
import com.lefancrm.apicenter.model.SurveyTaskType;

import java.util.Date;
import java.util.List;

/**
 * Created by zhuxia on 2020/5/11.
 * 进度跟踪报表
 */
public class ProgressTrackDto {

    private String surveyOrgName;//调查机构
    private Long surveyOrgId;//调查机构
    private Integer orgLevel;//调查机构 级别

    private String entrustOrgIds;//互助平台
    private String entrustOrgName;//委托机构

    private String surveyUserName;//调查员
    private Long surveyUserId;//调查员

    private Integer caseState;//案件类型：1、单点；2、单点+单点；3、全案；4、全案+单点；5、全案+全案
    private String caseStateName;

    private Integer orgCaseState;//机构案件类型：1、单点；2、全案
    private String orgCaseStateName;

    private Integer surveyState;//案件状态 ：24、平台复审通过；28、保司终审通过
    private String surveyStateName;

    private List<SurveyRiskCaseInfo> surveyRiskCaseInfoList;//案件信息
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
    private Integer userSurveyState;//案件状态
    private String userSurveyStateName;//
    private Integer userReturnState; //是否初审驳回: 0、否，1、是
    private Integer userIsSun;//是否标记阳性: 0、否，1、是
    private List<SurveyInvestigatorCaseType> investigatorCaseTypes;

    //机构案件
    private Long assignCaseId;
    private Date orgAssignDate;
    private Date orgCreportDate;
    private Date orgEndTime;
    private Integer orgDays;
    private Integer orgSurveyState;
    private String orgSurveyStateName;//案件状态
    private Integer orgReturnState; //是否复审驳回: 0、否，1、是
    private Integer orgIsSun;//是否标记阳性: 0、否，1、是
    private String orgTaskRemark;//调查需求
    private Long reviewUserId;
    private Date reviewTime;
    private List<SurveyAssignOrgType> surveyAssignOrgTypes;

    private List<ProgressTrackDto> children;//子级数据
    private Integer orgType;//机构类型：1、A类 2、B类
    private String orgTypeName;//机构类型名称：1、A类 2、B类

    private Integer entrustNum;     //委托量
    private Integer upEntrustNum;   //环比委托量
    private Double entrustRate;     //环比

    private Integer reviewNum;      //复审量
    private Integer upReviewNum;    //环比复审量
    private Double reviewRate;      //环比

    private Integer ztNum;      //在途案件数
    private Integer jjcqNum;      //即将超期案件数
    private Integer cqdhfNum;      //超期待回复
    private Integer yzcqNum;      //严重超期数
    private Integer yxNum;      //阳性案件数


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

    public String getSurveyStateName() {
        return surveyStateName;
    }

    public void setSurveyStateName(String surveyStateName) {
        this.surveyStateName = surveyStateName;
    }

    public List<ProgressTrackDto> getChildren() {
        return children;
    }

    public void setChildren(List<ProgressTrackDto> children) {
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

    public List<SurveyInvestigatorCaseType> getInvestigatorCaseTypes() {
        return investigatorCaseTypes;
    }

    public void setInvestigatorCaseTypes(List<SurveyInvestigatorCaseType> investigatorCaseTypes) {
        this.investigatorCaseTypes = investigatorCaseTypes;
    }

    public List<SurveyAssignOrgType> getSurveyAssignOrgTypes() {
        return surveyAssignOrgTypes;
    }

    public void setSurveyAssignOrgTypes(List<SurveyAssignOrgType> surveyAssignOrgTypes) {
        this.surveyAssignOrgTypes = surveyAssignOrgTypes;
    }

    public Integer getEntrustNum() {
        return entrustNum;
    }

    public void setEntrustNum(Integer entrustNum) {
        this.entrustNum = entrustNum;
    }

    public Integer getUpEntrustNum() {
        return upEntrustNum;
    }

    public void setUpEntrustNum(Integer upEntrustNum) {
        this.upEntrustNum = upEntrustNum;
    }

    public Double getEntrustRate() {
        return entrustRate;
    }

    public void setEntrustRate(Double entrustRate) {
        this.entrustRate = entrustRate;
    }

    public Integer getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(Integer reviewNum) {
        this.reviewNum = reviewNum;
    }

    public Integer getUpReviewNum() {
        return upReviewNum;
    }

    public void setUpReviewNum(Integer upReviewNum) {
        this.upReviewNum = upReviewNum;
    }

    public Double getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(Double reviewRate) {
        this.reviewRate = reviewRate;
    }

    public Integer getZtNum() {
        return ztNum;
    }

    public void setZtNum(Integer ztNum) {
        this.ztNum = ztNum;
    }

    public Integer getJjcqNum() {
        return jjcqNum;
    }

    public void setJjcqNum(Integer jjcqNum) {
        this.jjcqNum = jjcqNum;
    }

    public Integer getCqdhfNum() {
        return cqdhfNum;
    }

    public void setCqdhfNum(Integer cqdhfNum) {
        this.cqdhfNum = cqdhfNum;
    }

    public Integer getYzcqNum() {
        return yzcqNum;
    }

    public void setYzcqNum(Integer yzcqNum) {
        this.yzcqNum = yzcqNum;
    }

    public Integer getYxNum() {
        return yxNum;
    }

    public void setYxNum(Integer yxNum) {
        this.yxNum = yxNum;
    }

    public Integer getOrgSurveyState() {
        return orgSurveyState;
    }

    public void setOrgSurveyState(Integer orgSurveyState) {
        this.orgSurveyState = orgSurveyState;
    }

    public String getOrgSurveyStateName() {
        return orgSurveyStateName;
    }

    public void setOrgSurveyStateName(String orgSurveyStateName) {
        this.orgSurveyStateName = orgSurveyStateName;
    }

    public Integer getUserSurveyState() {
        return userSurveyState;
    }

    public void setUserSurveyState(Integer userSurveyState) {
        this.userSurveyState = userSurveyState;
    }

    public String getUserSurveyStateName() {
        return userSurveyStateName;
    }

    public void setUserSurveyStateName(String userSurveyStateName) {
        this.userSurveyStateName = userSurveyStateName;
    }

    public Integer getUserReturnState() {
        return userReturnState;
    }

    public void setUserReturnState(Integer userReturnState) {
        this.userReturnState = userReturnState;
    }

    public Integer getUserIsSun() {
        return userIsSun;
    }

    public void setUserIsSun(Integer userIsSun) {
        this.userIsSun = userIsSun;
    }

    public Integer getOrgReturnState() {
        return orgReturnState;
    }

    public void setOrgReturnState(Integer orgReturnState) {
        this.orgReturnState = orgReturnState;
    }

    public Integer getOrgIsSun() {
        return orgIsSun;
    }

    public void setOrgIsSun(Integer orgIsSun) {
        this.orgIsSun = orgIsSun;
    }

    public String getOrgTaskRemark() {
        return orgTaskRemark;
    }

    public void setOrgTaskRemark(String orgTaskRemark) {
        this.orgTaskRemark = orgTaskRemark;
    }

    public Long getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(Long reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }
}
