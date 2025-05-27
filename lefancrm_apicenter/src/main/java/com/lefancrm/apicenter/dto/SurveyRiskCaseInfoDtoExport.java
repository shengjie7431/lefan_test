package com.lefancrm.apicenter.dto;

/**
 * 案件分派按条件导出
 * @author EDZ
 */
public class SurveyRiskCaseInfoDtoExport {
    /**
     * 案件编号
     */
    private String surveyCaseNo;

    /**
     * 调查编号
     */
    private String surveyNo;

    /**
     * 被调查人
     */
    private String surveyPerson;

    /**
     * 联系方式
     */
    private String surveryPersonTel;

    /**
     * 理赔申请金额
     */
    private String claimsMoney;

    /**
     * 领域
     */
    private String surveyBusName;

    /**
     * 案件阶段
     */
    private Integer surveyPhase;

    /**
     * 分派机构状态
     */
    private Integer orgAssign;

    /**
     * 分派调查员状态
     */
    private Integer assignState;

    /**
     * 保险公司
     */
    private String entrustOrgName;

    /**
     * 委托时间
     */
    private String entrustTime;

    /**
     * 创建时间
     */
    private String createTime;

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
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

    public String getSurveryPersonTel() {
        return surveryPersonTel;
    }

    public void setSurveryPersonTel(String surveryPersonTel) {
        this.surveryPersonTel = surveryPersonTel;
    }

    public String getClaimsMoney() {
        return claimsMoney;
    }

    public void setClaimsMoney(String claimsMoney) {
        this.claimsMoney = claimsMoney;
    }

    public Integer getSurveyPhase() {
        return surveyPhase;
    }

    public void setSurveyPhase(Integer surveyPhase) {
        this.surveyPhase = surveyPhase;
    }

    public Integer getOrgAssign() {
        return orgAssign;
    }

    public void setOrgAssign(Integer orgAssign) {
        this.orgAssign = orgAssign;
    }

    public Integer getAssignState() {
        return assignState;
    }

    public void setAssignState(Integer assignState) {
        this.assignState = assignState;
    }

    public String getEntrustOrgName() {
        return entrustOrgName;
    }

    public void setEntrustOrgName(String entrustOrgName) {
        this.entrustOrgName = entrustOrgName;
    }

    public String getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(String entrustTime) {
        this.entrustTime = entrustTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSurveyBusName() {
        return surveyBusName;
    }

    public void setSurveyBusName(String surveyBusName) {
        this.surveyBusName = surveyBusName;
    }
}
