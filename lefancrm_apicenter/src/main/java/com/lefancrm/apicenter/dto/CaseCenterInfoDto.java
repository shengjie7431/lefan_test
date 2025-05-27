package com.lefancrm.apicenter.dto;

import cn.jpush.api.utils.StringUtils;
import com.lefancrm.apicenter.model.CaseCenterExtend;
import com.lefancrm.apicenter.model.CaseCenterInfo;
import com.lefancrm.apicenter.model.CaseCenterInfoExtend2;
import com.lefancrm.apicenter.model.CaseKaitingInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by lixianfeng on 2018/3/23.
 */
public class CaseCenterInfoDto extends CaseCenterInfo{
    private String address;//地址
    private String caseTypeName;//案件类型名称
    private String gradationStateName;//阶段状态名称

    private Long assessmentReportId;//公估报告ID
    private Long riskReportId;//评估报告ID
    private Long mediationReportId;//索赔报告ID
    private Long closeReportId;//结案报告ID
    private Long legalReportId;//诉讼报告ID

    private Long operatorFlowState;//业务员跟踪状态
    private Long customerFlowState;//客服跟踪状态
    private Long assessFlowState;//评估跟踪状态
    private Long claimFlowState;//索赔跟踪状态
    private Long legalFlowState;//诉讼跟踪状态
    private String operatorFlowName;//业务员跟踪状态名称
    private String customerFlowName;//客服跟踪状态名称
    private String assessFlowName;//评估跟踪状态名称
    private String claimFlowName;//索赔跟踪状态名称

    private String legalFlowName;//诉讼跟踪状态名称
    private String defineStateName;//服务费确认状态
    private Integer defineState;
    private Date defineDate;

    private Double defineAmount;

    private Boolean isShowRed;
    private Integer repay;//还款状态

    private String repayImg;//还款图片

    private Integer appraiseType;

    private Integer mediateType;

    private Integer closeCaseType;

    private Integer overTimeType; //超时案件的 类型

    private String salesmanPhone;//推广人手机号

    private CaseCenterInfoExtend2 extend2;

    private List<CaseStepsDTO> caseStepsDTOs;

    private Date claimantDate;//索赔时间--caseCenterExtend表中的
    private Date legalDate;//诉讼时间---caseCenterExtend表中的

    private List<CaseKaitingInfo> caseKaitingInfos;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCaseTypeName() {
        return caseTypeName;
    }

    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName;
    }

    public String getGradationStateName() {
        return gradationStateName;
    }

    public void setGradationStateName(String gradationStateName) {
        this.gradationStateName = gradationStateName;
    }

    public Long getAssessmentReportId() {
        return assessmentReportId;
    }

    public void setAssessmentReportId(Long assessmentReportId) {
        this.assessmentReportId = assessmentReportId;
    }

    public Long getRiskReportId() {
        return riskReportId;
    }

    public void setRiskReportId(Long riskReportId) {
        this.riskReportId = riskReportId;
    }

    public Long getMediationReportId() {
        return mediationReportId;
    }

    public void setMediationReportId(Long mediationReportId) {
        this.mediationReportId = mediationReportId;
    }

    public Long getCloseReportId() {
        return closeReportId;
    }

    public void setCloseReportId(Long closeReportId) {
        this.closeReportId = closeReportId;
    }

    public Long getLegalReportId() {
        return legalReportId;
    }

    public void setLegalReportId(Long legalReportId) {
        this.legalReportId = legalReportId;
    }

    public Long getCustomerFlowState() {
        return customerFlowState;
    }

    public void setCustomerFlowState(Long customerFlowState) {
        this.customerFlowState = customerFlowState;
    }

    public Long getAssessFlowState() {
        return assessFlowState;
    }

    public void setAssessFlowState(Long assessFlowState) {
        this.assessFlowState = assessFlowState;
    }

    public Long getClaimFlowState() {
        return claimFlowState;
    }

    public void setClaimFlowState(Long claimFlowState) {
        this.claimFlowState = claimFlowState;
    }

    public Long getLegalFlowState() {
        return legalFlowState;
    }

    public void setLegalFlowState(Long legalFlowState) {
        this.legalFlowState = legalFlowState;
    }

    public String getCustomerFlowName() {
        return customerFlowName;
    }

    public void setCustomerFlowName(String customerFlowName) {
        this.customerFlowName = customerFlowName;
    }

    public String getAssessFlowName() {
        return assessFlowName;
    }

    public void setAssessFlowName(String assessFlowName) {
        this.assessFlowName = assessFlowName;
    }

    public String getClaimFlowName() {
        return claimFlowName;
    }

    public void setClaimFlowName(String claimFlowName) {
        this.claimFlowName = claimFlowName;
    }

    public String getLegalFlowName() {
        return legalFlowName;
    }

    public void setLegalFlowName(String legalFlowName) {
        this.legalFlowName = legalFlowName;
    }

    public String getDefineStateName() {
        return defineStateName;
    }

    public void setDefineStateName(String defineStateName) {
        this.defineStateName = defineStateName;
    }

    public Integer getDefineState() {
        return defineState;
    }

    public void setDefineState(Integer defineState) {
        this.defineState = defineState;
    }

    public Date getDefineDate() {
        return defineDate;
    }

    public void setDefineDate(Date defineDate) {
        this.defineDate = defineDate;
    }

    public Double getDefineAmount() {
        return defineAmount;
    }

    public void setDefineAmount(Double defineAmount) {
        this.defineAmount = defineAmount;
    }

    public Boolean getIsShowRed() {
        return isShowRed;
    }

    public void setIsShowRed(Boolean isShowRed) {
        this.isShowRed = isShowRed;
    }

    public Integer getRepay() {
        return repay;
    }

    public void setRepay(Integer repay) {
        this.repay = repay;
    }

    public String getRepayImg() {
        return repayImg;
    }

    public void setRepayImg(String repayImg) {
        this.repayImg = repayImg;
    }

    public Long getOperatorFlowState() {
        return operatorFlowState;
    }

    public void setOperatorFlowState(Long operatorFlowState) {
        this.operatorFlowState = operatorFlowState;
    }

    public Integer getAppraiseType() {
        return appraiseType;
    }

    public void setAppraiseType(Integer appraiseType) {
        this.appraiseType = appraiseType;
    }

    public Integer getMediateType() {
        return mediateType;
    }

    public void setMediateType(Integer mediateType) {
        this.mediateType = mediateType;
    }

    public Integer getCloseCaseType() {
        return closeCaseType;
    }

    public void setCloseCaseType(Integer closeCaseType) {
        this.closeCaseType = closeCaseType;
    }

    public String getOperatorFlowName() {
        return operatorFlowName;
    }

    public void setOperatorFlowName(String operatorFlowName) {
        this.operatorFlowName = operatorFlowName;
    }

    public Integer getOverTimeType() {
        return overTimeType;
    }

    public void setOverTimeType(Integer overTimeType) {
        this.overTimeType = overTimeType;
    }

    public String getSalesmanPhone() {
        return salesmanPhone;
    }

    public void setSalesmanPhone(String salesmanPhone) {
        this.salesmanPhone = salesmanPhone;
    }

    public CaseCenterInfoExtend2 getExtend2() {
        return extend2;
    }

    public void setExtend2(CaseCenterInfoExtend2 extend2) {
        this.extend2 = extend2;
    }

    public List<CaseStepsDTO> getCaseStepsDTOs() {
        return caseStepsDTOs;
    }

    public void setCaseStepsDTOs(List<CaseStepsDTO> caseStepsDTOs) {
        this.caseStepsDTOs = caseStepsDTOs;
    }

    public Date getClaimantDate() {
        return claimantDate;
    }

    public void setClaimantDate(Date claimantDate) {
        this.claimantDate = claimantDate;
    }

    public Date getLegalDate() {
        return legalDate;
    }

    public void setLegalDate(Date legalDate) {
        this.legalDate = legalDate;
    }

    public List<CaseKaitingInfo> getCaseKaitingInfos() {
        return caseKaitingInfos;
    }

    public void setCaseKaitingInfos(List<CaseKaitingInfo> caseKaitingInfos) {
        this.caseKaitingInfos = caseKaitingInfos;
    }
}
