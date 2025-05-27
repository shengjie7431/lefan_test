package com.lefancrm.apicenter.dto.hzReport;


import java.util.Date;
import java.util.List;

public class AssessmentIndexDto {
    private Long id;
    private Long cId;
    private Long riskId;
    private Long parentId;
    private String surveyOrgName;//调查机构
    private String entrustOrgName; //互助平台
    private Long surveyOrgId;//调查机构id
    private String surveyUserName;//调查员
    private Long surveyUserId;//调查员id

    private Integer caseState;//案件类型：1、单点；2、单点+单点；3、全案；4、全案+单点；5、全案+全案
    private Integer orgCaseState;//机构案件类型：1、单点；2、全案

    private Date orgCreateTime;//委托时间
    private Date orgEndTime;//机构截至时间
    private Integer overdueNum;//超期件数
    private String overdueRate;//超期率
    private String overdueChain;//超期环比

    private Integer rejectedNum;//驳回件数 按件数
    private String rejectionRate; //驳回率
    private String dismissedQoQ;//驳回环比

    private Integer rejectedNumForRm;//驳回件数  按次数
    private String rejectionRateForRm; //驳回率
    private String dismissedQoQForRm;//驳回环比

    private Integer positivepNum;//阳性件数
    private String positiveRate;//阳性率
    private String positiveRatio;//阳性环比

    private Integer isSun;//是否阳性
    private Date reportDate;//机构初审通过时间
    private Date cReportTime;//提交机构审核时间

    private Date assignDate;//分派给调查员的时间
    private Date surveyEndTime;//调查员调查截止时间

    private Integer returnState;//机构驳回次数

    private String servicesName;//机构案件类型名称
    private String caseStateName;//案件类型名称

    private Integer surveyReturnState;//调查员驳回状态

    private Date reviewTime;//复审通过时间

    private String scoreSun;//阳性分

    private Integer orgType;//机构类型：1、A类 2、B类
    private String orgTypeName;//机构类型名称：1、A类 2、B类

    private List<AssessmentIndexDto> childrens;
    private int  orgAgingDay;
    private int  surveyAgingDay;

    private int orgAgingReal;
    private int orgAgingOver;
    private int surveyAgingReal;
    private int surveyAgingOver;

    //案件信息
    private String surveyInfoId;
    private Integer assignCaseId;
    private String surveyCaseNo;
    private String surveyPerson;
    private String surveyItem;
    private Date orgReportTime;
    private Integer orgSurveyState;
    private Date invAssignDate;
    private Date invCReportDate;
    private Integer invAgingDay;
    private Date sunTime;
    private String taskName;
    private String areaIds;
    private Long surveyAreaId;


    public int getOrgAgingReal() {
        return orgAgingReal;
    }

    public void setOrgAgingReal(int orgAgingReal) {
        this.orgAgingReal = orgAgingReal;
    }

    public int getOrgAgingOver() {
        return orgAgingOver;
    }

    public void setOrgAgingOver(int orgAgingOver) {
        this.orgAgingOver = orgAgingOver;
    }

    public int getSurveyAgingReal() {
        return surveyAgingReal;
    }

    public void setSurveyAgingReal(int surveyAgingReal) {
        this.surveyAgingReal = surveyAgingReal;
    }

    public int getSurveyAgingOver() {
        return surveyAgingOver;
    }

    public void setSurveyAgingOver(int surveyAgingOver) {
        this.surveyAgingOver = surveyAgingOver;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getAssignCaseId() {
        return assignCaseId;
    }

    public void setAssignCaseId(Integer assignCaseId) {
        this.assignCaseId = assignCaseId;
    }

    public String getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(String surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public int getOrgAgingDay() {
        return orgAgingDay;
    }

    public void setOrgAgingDay(int orgAgingDay) {
        this.orgAgingDay = orgAgingDay;
    }

    public int getSurveyAgingDay() {
        return surveyAgingDay;
    }

    public void setSurveyAgingDay(int surveyAgingDay) {
        this.surveyAgingDay = surveyAgingDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

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

    public Long getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Long surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
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

    public Date getOrgCreateTime() {
        return orgCreateTime;
    }

    public void setOrgCreateTime(Date orgCreateTime) {
        this.orgCreateTime = orgCreateTime;
    }

    public Date getOrgEndTime() {
        return orgEndTime;
    }

    public void setOrgEndTime(Date orgEndTime) {
        this.orgEndTime = orgEndTime;
    }

    public String getOverdueRate() {
        return overdueRate;
    }

    public void setOverdueRate(String overdueRate) {
        this.overdueRate = overdueRate;
    }

    public String getOverdueChain() {
        return overdueChain;
    }

    public void setOverdueChain(String overdueChain) {
        this.overdueChain = overdueChain;
    }

    public Integer getRejectedNum() {
        return rejectedNum;
    }

    public void setRejectedNum(Integer rejectedNum) {
        this.rejectedNum = rejectedNum;
    }

    public String getRejectionRate() {
        return rejectionRate;
    }

    public void setRejectionRate(String rejectionRate) {
        this.rejectionRate = rejectionRate;
    }

    public String getDismissedQoQ() {
        return dismissedQoQ;
    }

    public void setDismissedQoQ(String dismissedQoQ) {
        this.dismissedQoQ = dismissedQoQ;
    }

    public Integer getPositivepNum() {
        return positivepNum;
    }

    public void setPositivepNum(Integer positivepNum) {
        this.positivepNum = positivepNum;
    }

    public String getPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(String positiveRate) {
        this.positiveRate = positiveRate;
    }

    public String getPositiveRatio() {
        return positiveRatio;
    }

    public void setPositiveRatio(String positiveRatio) {
        this.positiveRatio = positiveRatio;
    }

    public Integer getIsSun() {
        return isSun;
    }

    public void setIsSun(Integer isSun) {
        this.isSun = isSun;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Date getSurveyEndTime() {
        return surveyEndTime;
    }

    public void setSurveyEndTime(Date surveyEndTime) {
        this.surveyEndTime = surveyEndTime;
    }

    public Integer getReturnState() {
        return returnState;
    }

    public void setReturnState(Integer returnState) {
        this.returnState = returnState;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public String getCaseStateName() {
        return caseStateName;
    }

    public void setCaseStateName(String caseStateName) {
        this.caseStateName = caseStateName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSurveyReturnState() {
        return surveyReturnState;
    }

    public void setSurveyReturnState(Integer surveyReturnState) {
        this.surveyReturnState = surveyReturnState;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getScoreSun() {
        return scoreSun;
    }

    public void setScoreSun(String scoreSun) {
        this.scoreSun = scoreSun;
    }

    public Integer getOverdueNum() {
        return overdueNum;
    }

    public void setOverdueNum(Integer overdueNum) {
        this.overdueNum = overdueNum;
    }

    public List<AssessmentIndexDto> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<AssessmentIndexDto> childrens) {
        this.childrens = childrens;
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

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public String getSurveyItem() {
        return surveyItem;
    }

    public void setSurveyItem(String surveyItem) {
        this.surveyItem = surveyItem;
    }

    public Date getOrgReportTime() {
        return orgReportTime;
    }

    public void setOrgReportTime(Date orgReportTime) {
        this.orgReportTime = orgReportTime;
    }

    public Integer getOrgSurveyState() {
        return orgSurveyState;
    }

    public void setOrgSurveyState(Integer orgSurveyState) {
        this.orgSurveyState = orgSurveyState;
    }

    public Date getInvAssignDate() {
        return invAssignDate;
    }

    public void setInvAssignDate(Date invAssignDate) {
        this.invAssignDate = invAssignDate;
    }

    public Date getInvCReportDate() {
        return invCReportDate;
    }

    public void setInvCReportDate(Date invCReportDate) {
        this.invCReportDate = invCReportDate;
    }


    public Date getSunTime() {
        return sunTime;
    }

    public void setSunTime(Date sunTime) {
        this.sunTime = sunTime;
    }

    public Integer getInvAgingDay() {
        return invAgingDay;
    }

    public void setInvAgingDay(Integer invAgingDay) {
        this.invAgingDay = invAgingDay;
    }

    public Long getRiskId() {
        return riskId;
    }

    public void setRiskId(Long riskId) {
        this.riskId = riskId;
    }

    public Date getcReportTime() {
        return cReportTime;
    }

    public void setcReportTime(Date cReportTime) {
        this.cReportTime = cReportTime;
    }

    public Integer getRejectedNumForRm() {
        return rejectedNumForRm;
    }

    public void setRejectedNumForRm(Integer rejectedNumForRm) {
        this.rejectedNumForRm = rejectedNumForRm;
    }

    public String getRejectionRateForRm() {
        return rejectionRateForRm;
    }

    public void setRejectionRateForRm(String rejectionRateForRm) {
        this.rejectionRateForRm = rejectionRateForRm;
    }

    public String getDismissedQoQForRm() {
        return dismissedQoQForRm;
    }

    public void setDismissedQoQForRm(String dismissedQoQForRm) {
        this.dismissedQoQForRm = dismissedQoQForRm;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
    }

    public Long getSurveyAreaId() {
        return surveyAreaId;
    }

    public void setSurveyAreaId(Long surveyAreaId) {
        this.surveyAreaId = surveyAreaId;
    }
}
