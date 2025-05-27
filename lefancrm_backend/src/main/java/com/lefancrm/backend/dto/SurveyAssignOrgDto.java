package com.lefancrm.backend.dto;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SurveyAssignOrgDto {
    private Long id;

    private Long surveyOrgId;

    private String surveyOrgName;

    private Long surveyId;

    private Long surveyInfoId;

    private Long reportId;

    private String reportName;

    private Integer reportState;

    private Date reportDate;

    private Integer orgSurveyState;

    private String orgSurveyStateName;

    private Long surveyInvestigatorCaseId;

    private Date orgEndTime;

    private String orgTaskRemark;

    private String orgOpinion;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private Integer orgPrimaryType;


    private CommonFile commonFile;
    private SurveyRiskCaseDto surveyRiskCase;
    private SurveyRiskCaseInfoDto surveyRiskCaseInfo;
    private Boolean isOverTime;//截止日期是否超时

    private Boolean isShow = true; //数据是否显示（模糊查询）

    private String orgSummary;

    private Integer reviewOff;

    private List<SurveyInvestigatorCaseDto> cases;
    private List<SurveyCaseDirectionDto> directions;

    private String efficiencyState;//案件时效具体状态：(场景时效跟踪菜单)
    private String efficiencyStateColor;//案件时效具体状态颜色：(场景时效跟踪菜单)

    private Long servicesId;

    private String servicesName;

    private Integer payType;

    private String surveryReLoossesRemark;

    private Double surveyMoney;

    private Double surveryReLosses;

    private Double surveyMoneySubmit;

    private Double surveryReLossesSubmit;

    private String surveryReportRemark;

    private Integer surveyPay;

    private Boolean isCurOrg;//是否是当前机构下的案件  true是 false否

    private Integer surveyReturn; //调查员主动退回

    private Integer extensionState;

    private Date extensionTime;

    private String extensionReason;

    private String extensionBackReason;

    private String extensionFiles;

    private SurveyRiskCaseInfoDto surveyRiskCaseInfoDto;

    private String surveyNo;

    private String  investigatorCaseStr;//调查员信息拼接str (场景：分派调查员list)
    private List<InvestigatorCaseInfo> investigatorCaseInfos;//调查员信息拼接str (场景：分派调查员list)

    private List<CommonFile> commonFiles; //申请延期时：证据附件

    private Boolean review;

    private Boolean rateEdit;

    private Long reviewUserId;

    private String reviewUserName;

    private Date reviewTime;

    private Double inscompanyMoney;

    private Double inscompanyDeMoney;

    //回访数据
    private Date visitTime;
    private String visitPerson;
    private Integer isAbnormal;
    private Integer visitState;
    private String surveyUserNames;

    private List<SurveyAssignOrgTypeDto> assignOrgTypes;//机构案件任务类型
    private List<SurveyAssignOrgExtensionDto> extensionList;//所有的延期记录
    private Integer oveDay;//超期天数

    private Integer assDay;//考核时效
    /**
     * 旧的委托方价格
     */
    private Double oldInscompanyMoney;
    /**
     * 旧的委托方减损价格
     */
    private Double oldInscompanyDeMoney;
    /**
     * 价格修改备注
     */
    private String inscompanyMoneyDesc;

    /**
     *确认委托方价格
     * @return
     */
    private Double inscompanySubmitMoney;

    /**
     *旧的确认委托方价格
     * @return
     */
    private Double oldInscompanySubmitMoney;

    /**
     *确认委托方价格修改备注
     * @return
     */
    private Double inscompanySubmitMoneyDesc;

    private Double overdueAgingRate;

    private Double assessOrgMoney;

    private Double assessOrgLossesMoney;

    private Integer agingDay;

    private Date oldOrgEndTime;

    private Date utterEndTime;

    private String lastFollowContent;

    private Boolean oprOver;//审核是否超时

    private String oprOverTimeStr;//审核时效

    private List<Map<String, Object>> orgPreList;


    private Double agingCheck;
    private Double agingReal;
    private Double agingOver;
    private String oupdateBy;
    private Double accMoney;

    private Date oupdateTime;

    //方向总数
    private Integer totalDirection;

    //委托方价格
    private Double clientPrice;

    private Date endTime;//案件截止时间（主案件）

    //延期审核记录id
    private Long assignOrgExtensionId;
    private SurveyAssignOrgExtensionDto extension;//单条的延期记录

    private Integer residueDays;//剩余天数

    private String entrustOrgName;

    private String surveyCaseNo;

    private String surveyPerson;

    private Integer newCase;

    private Integer markError;

    private String markErrorRemark;

    private Boolean urgent;


    private String hxsj;//回销时间
    private String ajzt;//案件状态
    private String yqjl;//延期记录
    private String thjl;//退回记录
    private String ajgt;//案件沟通
    public String getOupdateBy() {
        return oupdateBy;
    }

    public void setOupdateBy(String oupdateBy) {
        this.oupdateBy = oupdateBy;
    }

    public Date getOupdateTime() {
        return oupdateTime;
    }

    public void setOupdateTime(Date oupdateTime) {
        this.oupdateTime = oupdateTime;
    }
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

    public String getLastFollowContent() {
        return lastFollowContent;
    }

    public void setLastFollowContent(String lastFollowContent) {
        this.lastFollowContent = lastFollowContent;
    }

    public Boolean getCurOrg() {
        return isCurOrg;
    }

    public void setCurOrg(Boolean curOrg) {
        isCurOrg = curOrg;
    }

    public Double getInscompanySubmitMoney() {
        return inscompanySubmitMoney;
    }

    public void setInscompanySubmitMoney(Double inscompanySubmitMoney) {
        this.inscompanySubmitMoney = inscompanySubmitMoney;
    }

    public Double getOldInscompanySubmitMoney() {
        return oldInscompanySubmitMoney;
    }

    public void setOldInscompanySubmitMoney(Double oldInscompanySubmitMoney) {
        this.oldInscompanySubmitMoney = oldInscompanySubmitMoney;
    }

    public Double getInscompanySubmitMoneyDesc() {
        return inscompanySubmitMoneyDesc;
    }

    public void setInscompanySubmitMoneyDesc(Double inscompanySubmitMoneyDesc) {
        this.inscompanySubmitMoneyDesc = inscompanySubmitMoneyDesc;
    }

    public Double getOldInscompanyMoney() {
        return oldInscompanyMoney;
    }

    public void setOldInscompanyMoney(Double oldInscompanyMoney) {
        this.oldInscompanyMoney = oldInscompanyMoney;
    }

    public Double getOldInscompanyDeMoney() {
        return oldInscompanyDeMoney;
    }

    public void setOldInscompanyDeMoney(Double oldInscompanyDeMoney) {
        this.oldInscompanyDeMoney = oldInscompanyDeMoney;
    }

    public String getInscompanyMoneyDesc() {
        return inscompanyMoneyDesc;
    }

    public void setInscompanyMoneyDesc(String inscompanyMoneyDesc) {
        this.inscompanyMoneyDesc = inscompanyMoneyDesc;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public SurveyRiskCaseDto getSurveyRiskCase() {
        return surveyRiskCase;
    }

    public void setSurveyRiskCase(SurveyRiskCaseDto surveyRiskCase) {
        this.surveyRiskCase = surveyRiskCase;
    }

    public SurveyRiskCaseInfoDto getSurveyRiskCaseInfo() {
        return surveyRiskCaseInfo;
    }

    public void setSurveyRiskCaseInfo(SurveyRiskCaseInfoDto surveyRiskCaseInfo) {
        this.surveyRiskCaseInfo = surveyRiskCaseInfo;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Integer getReportState() {
        return reportState;
    }

    public void setReportState(Integer reportState) {
        this.reportState = reportState;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
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

    public Long getSurveyInvestigatorCaseId() {
        return surveyInvestigatorCaseId;
    }

    public void setSurveyInvestigatorCaseId(Long surveyInvestigatorCaseId) {
        this.surveyInvestigatorCaseId = surveyInvestigatorCaseId;
    }

    public CommonFile getCommonFile() {
        return commonFile;
    }

    public void setCommonFile(CommonFile commonFile) {
        this.commonFile = commonFile;
    }

    public Date getOrgEndTime() {
        return orgEndTime;
    }

    public void setOrgEndTime(Date orgEndTime) {
        this.orgEndTime = orgEndTime;
    }

    public String getOrgTaskRemark() {
        return orgTaskRemark;
    }

    public void setOrgTaskRemark(String orgTaskRemark) {
        this.orgTaskRemark = orgTaskRemark;
    }

    public String getOrgOpinion() {
        return orgOpinion;
    }

    public void setOrgOpinion(String orgOpinion) {
        this.orgOpinion = orgOpinion;
    }

    public Boolean getIsOverTime() {
        return isOverTime;
    }

    public void setIsOverTime(Boolean isOverTime) {
        this.isOverTime = isOverTime;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public Integer getOrgPrimaryType() {
        return orgPrimaryType;
    }

    public void setOrgPrimaryType(Integer orgPrimaryType) {
        this.orgPrimaryType = orgPrimaryType;
    }

    public String getOrgSummary() {
        return orgSummary;
    }

    public void setOrgSummary(String orgSummary) {
        this.orgSummary = orgSummary;
    }

    public Boolean getOverTime() {
        return isOverTime;
    }

    public void setOverTime(Boolean overTime) {
        isOverTime = overTime;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }

    public List<SurveyInvestigatorCaseDto> getCases() {
        return cases;
    }

    public void setCases(List<SurveyInvestigatorCaseDto> cases) {
        this.cases = cases;
    }

    public List<SurveyCaseDirectionDto> getDirections() {
        return directions;
    }

    public void setDirections(List<SurveyCaseDirectionDto> directions) {
        this.directions = directions;
    }

    public String getEfficiencyState() {
        return efficiencyState;
    }

    public void setEfficiencyState(String efficiencyState) {
        this.efficiencyState = efficiencyState;
    }

    public String getEfficiencyStateColor() {
        return efficiencyStateColor;
    }

    public void setEfficiencyStateColor(String efficiencyStateColor) {
        this.efficiencyStateColor = efficiencyStateColor;
    }

    public String getSurveryReLoossesRemark() {
        return surveryReLoossesRemark;
    }

    public void setSurveryReLoossesRemark(String surveryReLoossesRemark) {
        this.surveryReLoossesRemark = surveryReLoossesRemark;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public Long getServicesId() {
        return servicesId;
    }

    public void setServicesId(Long servicesId) {
        this.servicesId = servicesId;
    }

    public Double getSurveyMoney() {
        return surveyMoney;
    }

    public void setSurveyMoney(Double surveyMoney) {
        this.surveyMoney = surveyMoney;
    }

    public Double getSurveryReLosses() {
        return surveryReLosses;
    }

    public void setSurveryReLosses(Double surveryReLosses) {
        this.surveryReLosses = surveryReLosses;
    }

    public Double getSurveyMoneySubmit() {
        return surveyMoneySubmit;
    }

    public void setSurveyMoneySubmit(Double surveyMoneySubmit) {
        this.surveyMoneySubmit = surveyMoneySubmit;
    }

    public Double getSurveryReLossesSubmit() {
        return surveryReLossesSubmit;
    }

    public void setSurveryReLossesSubmit(Double surveryReLossesSubmit) {
        this.surveryReLossesSubmit = surveryReLossesSubmit;
    }

    public String getSurveryReportRemark() {
        return surveryReportRemark;
    }

    public void setSurveryReportRemark(String surveryReportRemark) {
        this.surveryReportRemark = surveryReportRemark;
    }

    public Integer getSurveyPay() {
        return surveyPay;
    }

    public void setSurveyPay(Integer surveyPay) {
        this.surveyPay = surveyPay;
    }

    public Boolean getIsCurOrg() {
        return isCurOrg;
    }

    public void setIsCurOrg(Boolean isCurOrg) {
        this.isCurOrg = isCurOrg;
    }

    public Integer getSurveyReturn() {
        return surveyReturn;
    }

    public void setSurveyReturn(Integer surveyReturn) {
        this.surveyReturn = surveyReturn;
    }

    public SurveyRiskCaseInfoDto getSurveyRiskCaseInfoDto() {
        return surveyRiskCaseInfoDto;
    }

    public void setSurveyRiskCaseInfoDto(SurveyRiskCaseInfoDto surveyRiskCaseInfoDto) {
        this.surveyRiskCaseInfoDto = surveyRiskCaseInfoDto;
    }

    public Integer getExtensionState() {
        return extensionState;
    }

    public void setExtensionState(Integer extensionState) {
        this.extensionState = extensionState;
    }

    public Date getExtensionTime() {
        return extensionTime;
    }

    public void setExtensionTime(Date extensionTime) {
        this.extensionTime = extensionTime;
    }

    public String getExtensionReason() {
        return extensionReason;
    }

    public void setExtensionReason(String extensionReason) {
        this.extensionReason = extensionReason;
    }

    public String getExtensionBackReason() {
        return extensionBackReason;
    }

    public void setExtensionBackReason(String extensionBackReason) {
        this.extensionBackReason = extensionBackReason;
    }

    public String getExtensionFiles() {
        return extensionFiles;
    }

    public void setExtensionFiles(String extensionFiles) {
        this.extensionFiles = extensionFiles;
    }

    public String getInvestigatorCaseStr() {
        return investigatorCaseStr;
    }

    public void setInvestigatorCaseStr(String investigatorCaseStr) {
        this.investigatorCaseStr = investigatorCaseStr;
    }

    public class InvestigatorCaseInfo{
        private String surveyUserName;
        private Date assignDate;
        private Date creportDate;
        private Date surveyEndTime;
        private String efficiencyState;//案件时效具体状态
        private String efficiencyStateColor;//案件时效具体状态颜色
        private String surveyStateName;//任务状态
        private Boolean showKey = false;

        public String getSurveyStateName() {
            return surveyStateName;
        }

        public void setSurveyStateName(String surveyStateName) {
            this.surveyStateName = surveyStateName;
        }

        public String getSurveyUserName() {
            return surveyUserName;
        }

        public void setSurveyUserName(String surveyUserName) {
            this.surveyUserName = surveyUserName;
        }

        public Date getAssignDate() {
            return assignDate;
        }

        public void setAssignDate(Date assignDate) {
            this.assignDate = assignDate;
        }

        public Date getCreportDate() {
            return creportDate;
        }

        public void setCreportDate(Date creportDate) {
            this.creportDate = creportDate;
        }

        public Date getSurveyEndTime() {
            return surveyEndTime;
        }

        public void setSurveyEndTime(Date surveyEndTime) {
            this.surveyEndTime = surveyEndTime;
        }

        public String getEfficiencyState() {
            return efficiencyState;
        }

        public void setEfficiencyState(String efficiencyState) {
            this.efficiencyState = efficiencyState;
        }

        public String getEfficiencyStateColor() {
            return efficiencyStateColor;
        }

        public void setEfficiencyStateColor(String efficiencyStateColor) {
            this.efficiencyStateColor = efficiencyStateColor;
        }

        public Boolean getShowKey() {
            return showKey;
        }

        public void setShowKey(Boolean showKey) {
            this.showKey = showKey;
        }
    }

    public List<InvestigatorCaseInfo> getInvestigatorCaseInfos() {
        return investigatorCaseInfos;
    }

    public void setInvestigatorCaseInfos(List<InvestigatorCaseInfo> investigatorCaseInfos) {
        this.investigatorCaseInfos = investigatorCaseInfos;
    }

    public Integer getReviewOff() {
        return reviewOff;
    }

    public void setReviewOff(Integer reviewOff) {
        this.reviewOff = reviewOff;
    }

    public String getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(String surveyNo) {
        this.surveyNo = surveyNo;
    }

    public List<CommonFile> getCommonFiles() {
        return commonFiles;
    }

    public void setCommonFiles(List<CommonFile> commonFiles) {
        this.commonFiles = commonFiles;
    }

    public Long getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(Long reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public String getReviewUserName() {
        return reviewUserName;
    }

    public void setReviewUserName(String reviewUserName) {
        this.reviewUserName = reviewUserName;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Boolean getReview() {
        return review;
    }

    public void setReview(Boolean review) {
        this.review = review;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitPerson() {
        return visitPerson;
    }

    public void setVisitPerson(String visitPerson) {
        this.visitPerson = visitPerson;
    }

    public Integer getIsAbnormal() {
        return isAbnormal;
    }

    public void setIsAbnormal(Integer isAbnormal) {
        this.isAbnormal = isAbnormal;
    }

    public Integer getVisitState() {
        return visitState;
    }

    public void setVisitState(Integer visitState) {
        this.visitState = visitState;
    }

    public String getSurveyUserNames() {
        return surveyUserNames;
    }

    public void setSurveyUserNames(String surveyUserNames) {
        this.surveyUserNames = surveyUserNames;
    }

    public Double getInscompanyMoney() {
        return inscompanyMoney;
    }

    public void setInscompanyMoney(Double inscompanyMoney) {
        this.inscompanyMoney = inscompanyMoney;
    }

    public Double getInscompanyDeMoney() {
        return inscompanyDeMoney;
    }

    public void setInscompanyDeMoney(Double inscompanyDeMoney) {
        this.inscompanyDeMoney = inscompanyDeMoney;
    }

    public Date getOldOrgEndTime() {
        return oldOrgEndTime;
    }

    public void setOldOrgEndTime(Date oldOrgEndTime) {
        this.oldOrgEndTime = oldOrgEndTime;
    }

    public List<SurveyAssignOrgTypeDto> getAssignOrgTypes() {
        return assignOrgTypes;
    }

    public void setAssignOrgTypes(List<SurveyAssignOrgTypeDto> assignOrgTypes) {
        this.assignOrgTypes = assignOrgTypes;
    }

    public List<SurveyAssignOrgExtensionDto> getExtensionList() {
        return extensionList;
    }

    public void setExtensionList(List<SurveyAssignOrgExtensionDto> extensionList) {
        this.extensionList = extensionList;
    }

    public Date getUtterEndTime() {
        return utterEndTime;
    }

    public void setUtterEndTime(Date utterEndTime) {
        this.utterEndTime = utterEndTime;
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

    public Double getAssessOrgMoney() {
        return assessOrgMoney;
    }

    public void setAssessOrgMoney(Double assessOrgMoney) {
        this.assessOrgMoney = assessOrgMoney;
    }

    public Double getAssessOrgLossesMoney() {
        return assessOrgLossesMoney;
    }

    public void setAssessOrgLossesMoney(Double assessOrgLossesMoney) {
        this.assessOrgLossesMoney = assessOrgLossesMoney;
    }

    public Boolean getRateEdit() {
        return rateEdit;
    }

    public void setRateEdit(Boolean rateEdit) {
        this.rateEdit = rateEdit;
    }

    public Boolean getOprOver() {
        return oprOver;
    }

    public void setOprOver(Boolean oprOver) {
        this.oprOver = oprOver;
    }

    public String getOprOverTimeStr() {
        return oprOverTimeStr;
    }

    public void setOprOverTimeStr(String oprOverTimeStr) {
        this.oprOverTimeStr = oprOverTimeStr;
    }

    public List<Map<String, Object>> getOrgPreList() {
        return orgPreList;
    }

    public void setOrgPreList(List<Map<String, Object>> orgPreList) {
        this.orgPreList = orgPreList;
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

    public Integer getTotalDirection() {
        return totalDirection;
    }

    public void setTotalDirection(Integer totalDirection) {
        this.totalDirection = totalDirection;
    }

    public Double getClientPrice() {
        return clientPrice;
    }

    public void setClientPrice(Double clientPrice) {
        this.clientPrice = clientPrice;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getAssignOrgExtensionId() {
        return assignOrgExtensionId;
    }

    public void setAssignOrgExtensionId(Long assignOrgExtensionId) {
        this.assignOrgExtensionId = assignOrgExtensionId;
    }

    public SurveyAssignOrgExtensionDto getExtension() {
        return extension;
    }

    public void setExtension(SurveyAssignOrgExtensionDto extension) {
        this.extension = extension;
    }

    public Integer getResidueDays() {
        return residueDays;
    }

    public void setResidueDays(Integer residueDays) {
        this.residueDays = residueDays;
    }

    public String getEntrustOrgName() {
        return entrustOrgName;
    }

    public void setEntrustOrgName(String entrustOrgName) {
        this.entrustOrgName = entrustOrgName;
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

    public Double getAccMoney() {
        return accMoney;
    }

    public void setAccMoney(Double accMoney) {
        this.accMoney = accMoney;
    }

    public Integer getNewCase() {
        return newCase;
    }

    public void setNewCase(Integer newCase) {
        this.newCase = newCase;
    }

    public Integer getMarkError() {
        return markError;
    }

    public void setMarkError(Integer markError) {
        this.markError = markError;
    }

    public String getMarkErrorRemark() {
        return markErrorRemark;
    }

    public void setMarkErrorRemark(String markErrorRemark) {
        this.markErrorRemark = markErrorRemark;
    }

    public Boolean getUrgent() {
        return urgent;
    }

    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
    }

    public String getHxsj() {
        return hxsj;
    }

    public void setHxsj(String hxsj) {
        this.hxsj = hxsj;
    }

    public String getAjzt() {
        return ajzt;
    }

    public void setAjzt(String ajzt) {
        this.ajzt = ajzt;
    }

    public String getYqjl() {
        return yqjl;
    }

    public void setYqjl(String yqjl) {
        this.yqjl = yqjl;
    }

    public String getThjl() {
        return thjl;
    }

    public void setThjl(String thjl) {
        this.thjl = thjl;
    }

    public String getAjgt() {
        return ajgt;
    }

    public void setAjgt(String ajgt) {
        this.ajgt = ajgt;
    }
}