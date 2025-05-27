package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyInvestigatorCaseType;

import java.util.Date;
import java.util.List;

/**
 * 调查员表报详情实体类
 * @author EDZ
 */
public class InvestigatorDetailsDto {

    /**
     * 调查员案件表Id
     */
    private String aId;

    /**
     * 案件id
     */
    private String id;

    /**
     * 案件编号
     */
    private String surveyNo;

    /**
     * 被调查人
     */
    private String surveyPerson;

    /**
     * 互助平台
     */
    private String name;

    /**
     * 调查员
     */
    private String surveyUserName;

    /**
     * 任务状态
     */
    private String surveyState;

    /**
     * 分配任务类型
     */
    private List<SurveyInvestigatorCaseType> taskTypeList;
    private String assignmentType;

    /**
     * 机构案件类型
     */
    private String servicesId;

    /**
     * 分派调查员日期
     */
    private Date assignDate;

    /**
     * 提交审核日期
     */
    private Date reportDate;

    /**
     * 调查员截止日期
     */
    private Date surveyEndTime;

    /**
     * 调查员时效
     */
    private String agingDay;

    /**
     * 超期时效
     */
    private double agingOver;

    /**
     * 实际时效
     */
    private Double agingReal;

    /**
     * 是否复审驳回
     */
    private String returnState;

    /**
     * 是否标记阳性
     */
    private String sunTime;

    public List<SurveyInvestigatorCaseType> getTaskTypeList() {
        return taskTypeList;
    }

    public void setTaskTypeList(List<SurveyInvestigatorCaseType> taskTypeList) {
        this.taskTypeList = taskTypeList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
    }

    public String getSurveyState() {
        return surveyState;
    }

    public void setSurveyState(String surveyState) {
        this.surveyState = surveyState;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public String getServicesId() {
        return servicesId;
    }

    public void setServicesId(String servicesId) {
        this.servicesId = servicesId;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Date getSurveyEndTime() {
        return surveyEndTime;
    }

    public void setSurveyEndTime(Date surveyEndTime) {
        this.surveyEndTime = surveyEndTime;
    }

    public String getAgingDay() {
        return agingDay;
    }

    public void setAgingDay(String agingDay) {
        this.agingDay = agingDay;
    }

    public String getReturnState() {
        return returnState;
    }

    public void setReturnState(String returnState) {
        this.returnState = returnState;
    }

    public String getSunTime() {
        return sunTime;
    }

    public void setSunTime(String sunTime) {
        this.sunTime = sunTime;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public double getAgingOver() {
        return agingOver;
    }

    public void setAgingOver(double agingOver) {
        this.agingOver = agingOver;
    }

    public Double getAgingReal() {
        return agingReal;
    }

    public void setAgingReal(Double agingReal) {
        this.agingReal = agingReal;
    }
}
