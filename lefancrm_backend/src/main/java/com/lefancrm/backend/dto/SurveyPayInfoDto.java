package com.lefancrm.backend.dto;

import com.lefancrm.backend.dto.feere.SurveyInvestigatorReInfoDto;
import com.lefancrm.backend.dto.fina.FinaHospitalAccount;
import com.lefancrm.backend.dto.fina.FinaHospitalInfo;
import com.lefancrm.backend.dto.financial.FinancialCostBearDto;
import com.lefancrm.backend.dto.financial.FinancialCostDetailsDto;
import com.lefancrm.backend.dto.financial.FinancialReApplyDto;

import java.util.Date;
import java.util.List;

public class SurveyPayInfoDto {
    private Long id;

    private String payNo;

    private Long orgId;

    private String orgName;

    private Integer sourceSupportType;

    private Double appPayMoney;

    private Double realPayMoney;

    private Date appStartDate;

    private Date appEndDate;

    private Integer appType;

    private String remark;

    private Integer payState;

    private Date payTime;

    private Long payUserId;

    private String payUserName;

    private String payImgUrl;

    private Date payStateOkTime;

    private Long createUserId;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    private int surveyCaseNum;

    private String opinion;

    private SurveyFranchiseeDto surveyFranchisee;

    private Integer payType;

    private Long paySurveyUserId;

    private String paySurveyUserName;

    private Long payKeyId;

    private SurveyInvestigatorDto surveyInvestigatorDto;

    private List<String> images;

    private String reCreateBy;

    private Long userId;

    private String realName;

    private Long staffPersonnelId;

    private Long socialSecurityCompanyId;

    private String socialSecurityCompany;

    private Long organId;

    private String organ;

    private Long departmentId;

    private String department;

    private Long teamId;

    private String team;

    private Double payRate;

    private FinaHospitalAccount finaHospitalAccount;

    private Double payTax;

    private Double payTaxRate;

    private Long jobPostId;

    private String jobPost;

    private FinancialReApplyDto financialReApply;// 每刻报销申请表

    private Double realIncomeMoney;//实际所得金额

    private SurveyInvestigatorReInfoDto reInfo;
    private List<FinancialCostDetailsDto> costDetails;
    private List<FinancialCostBearDto> costBears;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getSourceSupportType() {
        return sourceSupportType;
    }

    public void setSourceSupportType(Integer sourceSupportType) {
        this.sourceSupportType = sourceSupportType;
    }

    public Double getAppPayMoney() {
        return appPayMoney;
    }

    public void setAppPayMoney(Double appPayMoney) {
        this.appPayMoney = appPayMoney;
    }

    public Double getRealPayMoney() {
        return realPayMoney;
    }

    public void setRealPayMoney(Double realPayMoney) {
        this.realPayMoney = realPayMoney;
    }

    public Date getAppStartDate() {
        return appStartDate;
    }

    public void setAppStartDate(Date appStartDate) {
        this.appStartDate = appStartDate;
    }

    public Date getAppEndDate() {
        return appEndDate;
    }

    public void setAppEndDate(Date appEndDate) {
        this.appEndDate = appEndDate;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Long getPayUserId() {
        return payUserId;
    }

    public void setPayUserId(Long payUserId) {
        this.payUserId = payUserId;
    }

    public String getPayUserName() {
        return payUserName;
    }

    public void setPayUserName(String payUserName) {
        this.payUserName = payUserName;
    }

    public Date getPayStateOkTime() {
        return payStateOkTime;
    }

    public void setPayStateOkTime(Date payStateOkTime) {
        this.payStateOkTime = payStateOkTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
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

    public SurveyFranchiseeDto getSurveyFranchisee() {
        return surveyFranchisee;
    }

    public void setSurveyFranchisee(SurveyFranchiseeDto surveyFranchisee) {
        this.surveyFranchisee = surveyFranchisee;
    }

    public int getSurveyCaseNum() {
        return surveyCaseNum;
    }

    public void setSurveyCaseNum(int surveyCaseNum) {
        this.surveyCaseNum = surveyCaseNum;
    }

    public String getPayImgUrl() {
        return payImgUrl;
    }

    public void setPayImgUrl(String payImgUrl) {
        this.payImgUrl = payImgUrl;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getPaySurveyUserId() {
        return paySurveyUserId;
    }

    public void setPaySurveyUserId(Long paySurveyUserId) {
        this.paySurveyUserId = paySurveyUserId;
    }

    public String getPaySurveyUserName() {
        return paySurveyUserName;
    }

    public void setPaySurveyUserName(String paySurveyUserName) {
        this.paySurveyUserName = paySurveyUserName;
    }

    public Long getPayKeyId() {
        return payKeyId;
    }

    public void setPayKeyId(Long payKeyId) {
        this.payKeyId = payKeyId;
    }

    public SurveyInvestigatorDto getSurveyInvestigatorDto() {
        return surveyInvestigatorDto;
    }

    public void setSurveyInvestigatorDto(SurveyInvestigatorDto surveyInvestigatorDto) {
        this.surveyInvestigatorDto = surveyInvestigatorDto;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getReCreateBy() {
        return reCreateBy;
    }

    public void setReCreateBy(String reCreateBy) {
        this.reCreateBy = reCreateBy;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getStaffPersonnelId() {
        return staffPersonnelId;
    }

    public void setStaffPersonnelId(Long staffPersonnelId) {
        this.staffPersonnelId = staffPersonnelId;
    }

    public Long getSocialSecurityCompanyId() {
        return socialSecurityCompanyId;
    }

    public void setSocialSecurityCompanyId(Long socialSecurityCompanyId) {
        this.socialSecurityCompanyId = socialSecurityCompanyId;
    }

    public String getSocialSecurityCompany() {
        return socialSecurityCompany;
    }

    public void setSocialSecurityCompany(String socialSecurityCompany) {
        this.socialSecurityCompany = socialSecurityCompany;
    }

    public Long getOrganId() {
        return organId;
    }

    public void setOrganId(Long organId) {
        this.organId = organId;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Double getPayRate() {
        return payRate;
    }

    public void setPayRate(Double payRate) {
        this.payRate = payRate;
    }

    public FinaHospitalAccount getFinaHospitalAccount() {
        return finaHospitalAccount;
    }

    public void setFinaHospitalAccount(FinaHospitalAccount finaHospitalAccount) {
        this.finaHospitalAccount = finaHospitalAccount;
    }

    public Double getPayTax() {
        return payTax;
    }

    public void setPayTax(Double payTax) {
        this.payTax = payTax;
    }

    public Double getPayTaxRate() {
        return payTaxRate;
    }

    public void setPayTaxRate(Double payTaxRate) {
        this.payTaxRate = payTaxRate;
    }

    public Long getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Long jobPostId) {
        this.jobPostId = jobPostId;
    }

    public String getJobPost() {
        return jobPost;
    }

    public void setJobPost(String jobPost) {
        this.jobPost = jobPost;
    }

    public FinancialReApplyDto getFinancialReApply() {
        return financialReApply;
    }

    public void setFinancialReApply(FinancialReApplyDto financialReApply) {
        this.financialReApply = financialReApply;
    }

    public Double getRealIncomeMoney() {
        return realIncomeMoney;
    }

    public void setRealIncomeMoney(Double realIncomeMoney) {
        this.realIncomeMoney = realIncomeMoney;
    }

    public SurveyInvestigatorReInfoDto getReInfo() {
        return reInfo;
    }

    public void setReInfo(SurveyInvestigatorReInfoDto reInfo) {
        this.reInfo = reInfo;
    }

    public List<FinancialCostDetailsDto> getCostDetails() {
        return costDetails;
    }

    public void setCostDetails(List<FinancialCostDetailsDto> costDetails) {
        this.costDetails = costDetails;
    }

    public List<FinancialCostBearDto> getCostBears() {
        return costBears;
    }

    public void setCostBears(List<FinancialCostBearDto> costBears) {
        this.costBears = costBears;
    }
}