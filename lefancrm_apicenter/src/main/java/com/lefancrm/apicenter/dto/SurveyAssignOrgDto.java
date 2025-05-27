package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.dao.SurveyConsignorMapper;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.model.CommonFile;
import com.lefancrm.apicenter.util.GetWorkDay;
import org.springframework.beans.factory.annotation.Autowired;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SurveyAssignOrgDto extends SurveyAssignOrg{
    private CommonFile commonFile;
    private SurveyRiskCase surveyRiskCase;
    private SurveyRiskCaseInfo surveyRiskCaseInfo;
    private Boolean isOverTime;//截止日期是否超时
    private Boolean isShow = true; //数据是否显示（模糊查询）
    private List<SurveyInvestigatorCaseDto> cases;
    private List<SurveyCaseDirectionDto> directions;

    private String efficiencyState;//案件时效具体状态：(场景时效跟踪菜单)
    private String efficiencyStateColor;//案件时效具体状态颜色：(场景时效跟踪菜单)
    private Boolean isCurOrg;//是否是当前机构下的案件  true是 false否
    private SurveyRiskCaseInfoDto surveyRiskCaseInfoDto;

    private String  investigatorCaseStr;//调查员信息拼接str (场景：分派调查员list)
    private List<InvestigatorCaseInfo> investigatorCaseInfos;//调查员信息拼接str (场景：分派调查员list)
    private String surveyNo;

    private List<CommonFile> commonFiles; //申请延期时：证据附件

    private Boolean review;

    private Boolean rateEdit;

    //调查回访数据
    private Date visitTime;
    private String visitPerson;
    private Integer isAbnormal;
    private Integer visitState;
    private String surveyUserNames;

    private List<SurveyAssignOrgType> assignOrgTypes;//机构案件任务类型
    private List<SurveyAssignOrgExtension> extensionList;//所有的延期记录

    private Integer efficiencyAttr;

    private String lastFollowContent;

    private Boolean oprOver;//审核是否超时

    private String oprOverTimeStr;//审核时效

    private List<Map<String, Object>> orgPreList;

    private Integer oveDay;//超期天数

    private Integer assDay;//考核时效survey/case/info

    private String surveyPerson;

    private String surveyCaseNo;

    private String oupdateBy;
    private Date oupdateTime;

    //方向总数
    private Integer totalDirection;

    //委托方价格
    private Double clientPrice;

    //延期审核记录id
    private Long assignOrgExtensionId;
    private SurveyAssignOrgExtension extension;//单条的延期记录
    private Date endTime;//案件截止时间（主案件）

    private Integer residueDays;//剩余天数

    private String entrustOrgName;

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

    public String getLastFollowContent() {
        return lastFollowContent;
    }

    public void setLastFollowContent(String lastFollowContent) {
        this.lastFollowContent = lastFollowContent;
    }

    public SurveyRiskCase getSurveyRiskCase() {
        return surveyRiskCase;
    }

    public void setSurveyRiskCase(SurveyRiskCase surveyRiskCase) {
        this.surveyRiskCase = surveyRiskCase;
    }

    public SurveyRiskCaseInfo getSurveyRiskCaseInfo() {
        return surveyRiskCaseInfo;
    }

    public void setSurveyRiskCaseInfo(SurveyRiskCaseInfo surveyRiskCaseInfo) {
        this.surveyRiskCaseInfo = surveyRiskCaseInfo;
    }

    public CommonFile getCommonFile() {
        return commonFile;
    }

    public void setCommonFile(CommonFile commonFile) {
        this.commonFile = commonFile;
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

    public Boolean getIsCurOrg() {
        return isCurOrg;
    }

    public void setIsCurOrg(Boolean isCurOrg) {
        this.isCurOrg = isCurOrg;
    }

    public SurveyRiskCaseInfoDto getSurveyRiskCaseInfoDto() {
        return surveyRiskCaseInfoDto;
    }

    public void setSurveyRiskCaseInfoDto(SurveyRiskCaseInfoDto surveyRiskCaseInfoDto) {
        this.surveyRiskCaseInfoDto = surveyRiskCaseInfoDto;
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

    public Integer getEfficiencyAttr() {
        return efficiencyAttr;
    }

    public void setEfficiencyAttr(Integer efficiencyAttr) {
        this.efficiencyAttr = efficiencyAttr;
    }

    public List<SurveyAssignOrgType> getAssignOrgTypes() {
        return assignOrgTypes;
    }

    public void setAssignOrgTypes(List<SurveyAssignOrgType> assignOrgTypes) {
        this.assignOrgTypes = assignOrgTypes;
    }

    public List<SurveyAssignOrgExtension> getExtensionList() {
        return extensionList;
    }

    public void setExtensionList(List<SurveyAssignOrgExtension> extensionList) {
        this.extensionList = extensionList;
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

    public String getSurveyPerson() {
        return surveyPerson;
    }

    public void setSurveyPerson(String surveyPerson) {
        this.surveyPerson = surveyPerson;
    }

    public String getSurveyCaseNo() {
        return surveyCaseNo;
    }

    public void setSurveyCaseNo(String surveyCaseNo) {
        this.surveyCaseNo = surveyCaseNo;
    }

    public Boolean getCurOrg() {
        return isCurOrg;
    }

    public void setCurOrg(Boolean curOrg) {
        isCurOrg = curOrg;
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

    public Long getAssignOrgExtensionId() {
        return assignOrgExtensionId;
    }

    public void setAssignOrgExtensionId(Long assignOrgExtensionId) {
        this.assignOrgExtensionId = assignOrgExtensionId;
    }

    public SurveyAssignOrgExtension getExtension() {
        return extension;
    }

    public void setExtension(SurveyAssignOrgExtension extension) {
        this.extension = extension;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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