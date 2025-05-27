package com.lefancrm.apicenter.fina.model;

import com.lefancrm.apicenter.model.UserInfo;

import java.util.Date;
import java.util.List;

public class FinaSettlementInfo {
    private Long id;

    private String settlementNo;

    private Double realMoney;

    private Double medicalMoney;

    private Double medicalInsuranceMoney;

    private Double customerPaymentMoney;

    private Double refundMoney;

    private Double customerReceivesMoney;

    private Double lefanReceivesMoney;

    private Date lefanReceivesTime;

    private String lefanReceivesUserName;

    private Double lefanPaymentMoney;

    private Double deductibleMoney;

    private Integer refundChannel;

    private Integer leaveHospitalFile;

    private Integer refundState;

    private Integer refundMode;

    private String settlementInfoFile;

    private Double servicesMoney;

    private Double submitServicesMoney;

    private Long checkManId;

    private String checkManName;

    private Integer settlementState;

    private Integer repaymentState;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    private Date finshSettlementTime;

    private Date finshClaimsTime;

    private Date finshRepaymentTime;

    private Date finshPassedTime;

    private Date finshInvoceTime;

    private Date finshAccountTime;

    private Date finshCloseTime;

    private Long financeUserId;

    private String financeUserName;

    private Double repaymentMoney;

    private Double noRepaymentMoney;

    private Integer isBad;

    private Integer urgeState;

    private Long finaInfoParentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSettlementNo() {
        return settlementNo;
    }

    public void setSettlementNo(String settlementNo) {
        this.settlementNo = settlementNo;
    }

    public Double getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Double realMoney) {
        this.realMoney = realMoney;
    }

    public Double getMedicalMoney() {
        return medicalMoney;
    }

    public void setMedicalMoney(Double medicalMoney) {
        this.medicalMoney = medicalMoney;
    }

    public Double getMedicalInsuranceMoney() {
        return medicalInsuranceMoney;
    }

    public void setMedicalInsuranceMoney(Double medicalInsuranceMoney) {
        this.medicalInsuranceMoney = medicalInsuranceMoney;
    }

    public Double getCustomerPaymentMoney() {
        return customerPaymentMoney;
    }

    public void setCustomerPaymentMoney(Double customerPaymentMoney) {
        this.customerPaymentMoney = customerPaymentMoney;
    }

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Double getCustomerReceivesMoney() {
        return customerReceivesMoney;
    }

    public void setCustomerReceivesMoney(Double customerReceivesMoney) {
        this.customerReceivesMoney = customerReceivesMoney;
    }

    public Double getLefanReceivesMoney() {
        return lefanReceivesMoney;
    }

    public void setLefanReceivesMoney(Double lefanReceivesMoney) {
        this.lefanReceivesMoney = lefanReceivesMoney;
    }

    public Double getLefanPaymentMoney() {
        return lefanPaymentMoney;
    }

    public void setLefanPaymentMoney(Double lefanPaymentMoney) {
        this.lefanPaymentMoney = lefanPaymentMoney;
    }

    public Double getDeductibleMoney() {
        return deductibleMoney;
    }

    public void setDeductibleMoney(Double deductibleMoney) {
        this.deductibleMoney = deductibleMoney;
    }

    public Integer getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(Integer refundChannel) {
        this.refundChannel = refundChannel;
    }

    public Integer getLeaveHospitalFile() {
        return leaveHospitalFile;
    }

    public void setLeaveHospitalFile(Integer leaveHospitalFile) {
        this.leaveHospitalFile = leaveHospitalFile;
    }

    public Integer getRefundState() {
        return refundState;
    }

    public void setRefundState(Integer refundState) {
        this.refundState = refundState;
    }

    public String getSettlementInfoFile() {
        return settlementInfoFile;
    }

    public void setSettlementInfoFile(String settlementInfoFile) {
        this.settlementInfoFile = settlementInfoFile;
    }

    public Double getServicesMoney() {
        return servicesMoney;
    }

    public void setServicesMoney(Double servicesMoney) {
        this.servicesMoney = servicesMoney;
    }

    public Double getSubmitServicesMoney() {
        return submitServicesMoney;
    }

    public void setSubmitServicesMoney(Double submitServicesMoney) {
        this.submitServicesMoney = submitServicesMoney;
    }

    public Long getCheckManId() {
        return checkManId;
    }

    public void setCheckManId(Long checkManId) {
        this.checkManId = checkManId;
    }

    public String getCheckManName() {
        return checkManName;
    }

    public void setCheckManName(String checkManName) {
        this.checkManName = checkManName;
    }

    public Integer getSettlementState() {
        return settlementState;
    }

    public void setSettlementState(Integer settlementState) {
        this.settlementState = settlementState;
    }

    public Integer getRepaymentState() {
        return repaymentState;
    }

    public void setRepaymentState(Integer repaymentState) {
        this.repaymentState = repaymentState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getFinshSettlementTime() {
        return finshSettlementTime;
    }

    public void setFinshSettlementTime(Date finshSettlementTime) {
        this.finshSettlementTime = finshSettlementTime;
    }

    public Date getFinshClaimsTime() {
        return finshClaimsTime;
    }

    public void setFinshClaimsTime(Date finshClaimsTime) {
        this.finshClaimsTime = finshClaimsTime;
    }

    public Date getFinshRepaymentTime() {
        return finshRepaymentTime;
    }

    public void setFinshRepaymentTime(Date finshRepaymentTime) {
        this.finshRepaymentTime = finshRepaymentTime;
    }

    public Date getFinshPassedTime() {
        return finshPassedTime;
    }

    public void setFinshPassedTime(Date finshPassedTime) {
        this.finshPassedTime = finshPassedTime;
    }

    public Date getFinshInvoceTime() {
        return finshInvoceTime;
    }

    public void setFinshInvoceTime(Date finshInvoceTime) {
        this.finshInvoceTime = finshInvoceTime;
    }

    public Date getFinshAccountTime() {
        return finshAccountTime;
    }

    public void setFinshAccountTime(Date finshAccountTime) {
        this.finshAccountTime = finshAccountTime;
    }

    public Date getFinshCloseTime() {
        return finshCloseTime;
    }

    public void setFinshCloseTime(Date finshCloseTime) {
        this.finshCloseTime = finshCloseTime;
    }

    public Long getFinanceUserId() {
        return financeUserId;
    }

    public void setFinanceUserId(Long financeUserId) {
        this.financeUserId = financeUserId;
    }

    public String getFinanceUserName() {
        return financeUserName;
    }

    public void setFinanceUserName(String financeUserName) {
        this.financeUserName = financeUserName;
    }

    public Double getRepaymentMoney() {
        return repaymentMoney;
    }

    public void setRepaymentMoney(Double repaymentMoney) {
        this.repaymentMoney = repaymentMoney;
    }

    public Double getNoRepaymentMoney() {
        return noRepaymentMoney;
    }

    public void setNoRepaymentMoney(Double noRepaymentMoney) {
        this.noRepaymentMoney = noRepaymentMoney;
    }

    public Integer getIsBad() {
        return isBad;
    }

    public void setIsBad(Integer isBad) {
        this.isBad = isBad;
    }

    public Integer getUrgeState() {
        return urgeState;
    }

    public void setUrgeState(Integer urgeState) {
        this.urgeState = urgeState;
    }

    public Date getLefanReceivesTime() {
        return lefanReceivesTime;
    }

    public void setLefanReceivesTime(Date lefanReceivesTime) {
        this.lefanReceivesTime = lefanReceivesTime;
    }

    public String getLefanReceivesUserName() {
        return lefanReceivesUserName;
    }

    public void setLefanReceivesUserName(String lefanReceivesUserName) {
        this.lefanReceivesUserName = lefanReceivesUserName;
    }


    public Integer getRefundMode() {
        return refundMode;
    }

    public void setRefundMode(Integer refundMode) {
        this.refundMode = refundMode;
    }

    public Long getFinaInfoParentId() {
        return finaInfoParentId;
    }

    public void setFinaInfoParentId(Long finaInfoParentId) {
        this.finaInfoParentId = finaInfoParentId;
    }

    private Long entrustOrgId;

    private String entrustOrgName;

    private String insuredName;

    private String insuredTel;

    private String insurancePolicyNo;

    private Long hospitalId;

    private String hospitalName;

    private String department;

    private Date inHospitalTime;

    private String finaUserName;

    private String finaUserTel;

    private String settlementStateStr;

    private Double notRepayMoney;
    private UserInfo curUser;
    private FinaApplicantInfo finaApplicantInfo;
    private FinaApplicantMoney finaApplicantMoney;
    private FinaApplicant finaApplicant;
    private List<FinaSettlementOrg> orgInfos;
    private List<FinaSettlementInvestigator> surveyUserInfos;
    private List<FinaSettlementApplicant> settlementApplicants;
    private String settlementApplicantsJSON;
    private List<FinaFileSettlement> fileSettlements;
    private List<FinaSettlementTrack> settlementTracks;
    private List<FinaRepaymentInfo> repaymentInfos;
    private List<FinaUrgeInfo> urgeInfos;
    private Boolean showRepayAppBtn;

    public String getSettlementStateStr() {
        return settlementStateStr;
    }

    public void setSettlementStateStr(String settlementStateStr) {
        this.settlementStateStr = settlementStateStr;
    }

    public FinaApplicantInfo getFinaApplicantInfo() {
        return finaApplicantInfo;
    }

    public void setFinaApplicantInfo(FinaApplicantInfo finaApplicantInfo) {
        this.finaApplicantInfo = finaApplicantInfo;
    }

    public FinaApplicantMoney getFinaApplicantMoney() {
        return finaApplicantMoney;
    }

    public void setFinaApplicantMoney(FinaApplicantMoney finaApplicantMoney) {
        this.finaApplicantMoney = finaApplicantMoney;
    }

    public FinaApplicant getFinaApplicant() {
        return finaApplicant;
    }

    public void setFinaApplicant(FinaApplicant finaApplicant) {
        this.finaApplicant = finaApplicant;
    }

    public List<FinaSettlementOrg> getOrgInfos() {
        return orgInfos;
    }

    public void setOrgInfos(List<FinaSettlementOrg> orgInfos) {
        this.orgInfos = orgInfos;
    }

    public List<FinaSettlementInvestigator> getSurveyUserInfos() {
        return surveyUserInfos;
    }

    public void setSurveyUserInfos(List<FinaSettlementInvestigator> surveyUserInfos) {
        this.surveyUserInfos = surveyUserInfos;
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

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getInsuredTel() {
        return insuredTel;
    }

    public void setInsuredTel(String insuredTel) {
        this.insuredTel = insuredTel;
    }

    public String getInsurancePolicyNo() {
        return insurancePolicyNo;
    }

    public void setInsurancePolicyNo(String insurancePolicyNo) {
        this.insurancePolicyNo = insurancePolicyNo;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getInHospitalTime() {
        return inHospitalTime;
    }

    public void setInHospitalTime(Date inHospitalTime) {
        this.inHospitalTime = inHospitalTime;
    }

    public String getFinaUserName() {
        return finaUserName;
    }

    public void setFinaUserName(String finaUserName) {
        this.finaUserName = finaUserName;
    }

    public String getFinaUserTel() {
        return finaUserTel;
    }

    public void setFinaUserTel(String finaUserTel) {
        this.finaUserTel = finaUserTel;
    }

    public Double getNotRepayMoney() {
        return notRepayMoney;
    }

    public void setNotRepayMoney(Double notRepayMoney) {
        this.notRepayMoney = notRepayMoney;
    }

    public List<FinaSettlementApplicant> getSettlementApplicants() {
        return settlementApplicants;
    }

    public void setSettlementApplicants(List<FinaSettlementApplicant> settlementApplicants) {
        this.settlementApplicants = settlementApplicants;
    }

    public List<FinaFileSettlement> getFileSettlements() {
        return fileSettlements;
    }

    public void setFileSettlements(List<FinaFileSettlement> fileSettlements) {
        this.fileSettlements = fileSettlements;
    }

    public List<FinaSettlementTrack> getSettlementTracks() {
        return settlementTracks;
    }

    public void setSettlementTracks(List<FinaSettlementTrack> settlementTracks) {
        this.settlementTracks = settlementTracks;
    }

    public List<FinaRepaymentInfo> getRepaymentInfos() {
        return repaymentInfos;
    }

    public void setRepaymentInfos(List<FinaRepaymentInfo> repaymentInfos) {
        this.repaymentInfos = repaymentInfos;
    }

    public List<FinaUrgeInfo> getUrgeInfos() {
        return urgeInfos;
    }

    public void setUrgeInfos(List<FinaUrgeInfo> urgeInfos) {
        this.urgeInfos = urgeInfos;
    }

    public UserInfo getCurUser() {
        return curUser;
    }

    public void setCurUser(UserInfo curUser) {
        this.curUser = curUser;
    }

    public Boolean getShowRepayAppBtn() {
        return showRepayAppBtn;
    }

    public void setShowRepayAppBtn(Boolean showRepayAppBtn) {
        this.showRepayAppBtn = showRepayAppBtn;
    }

    public String getSettlementApplicantsJSON() {
        return settlementApplicantsJSON;
    }

    public void setSettlementApplicantsJSON(String settlementApplicantsJSON) {
        this.settlementApplicantsJSON = settlementApplicantsJSON;
    }
}