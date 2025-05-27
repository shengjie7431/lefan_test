package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

public class FinancialReApply {
    private Long id;

    private String reNo;

    private Integer reType;

    private String reReasons;

    private Double reMoney;

    private Double repaymentMoney;

    private Integer state;

    private Date applyTime;

    private Date payTime;

    private String companyTitle;

    private Long companyId;

    private Long applyUserId;

    private String applyUserName;

    private String payeeName;

    private String payeeNo;

    private Long bankId;

    private String bankName;

    private String branchBank;

    private String bankImage;

    private String applyDesc;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer deleteFlag;

    private Long departmentId; //对应staff_organ表的id

    private String departmentName;

    private Long departmentUserId;

    private String departmentUserName;

    private Long zhuanUserId;

    private String zhuanUserName;

    private Integer zhuanType;

    private Integer zhuanOprState;

    private Long curUserId;



    private Date ceoEndTime;//总经理审核通过时间

    private String backReason;//驳回原因

    private String stateStr;//状态名称

    private List<FinancialCostBear> financialCostBearList;//费用承担明细

    private List<FinancialCostDetails> financialCostDetails;//费用明细
    private Double costSumMoney;//费用明细总金额

    private String departmentNameStr;//承担部门集合

    private List<FinancialFile> financialFileList; //附件

    private String roleCode;//案件阶段
    private Boolean ceoRole;//总经理
    private Boolean organManagerRole;//机构经理
    private Boolean superiorManagerRole;//分管总
    private Boolean financeRole;//财务专员
    private Boolean financeMangeRole;//财务主管
    private Boolean lefanRole;//董事长

    private Boolean cwzjRole;//财务总监

    private Boolean checked = true;//多机构审核时，判断当前机构负责人是否已审核过

    private String organManagerUserNames;//机构经理
    private String superiorManagerUserNames;//分管总
    private String showOprUserNames;//显示的当前审核人

    private Integer isCanModify;//是否可以更改机构

    private Long payUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReNo() {
        return reNo;
    }

    public void setReNo(String reNo) {
        this.reNo = reNo;
    }

    public Integer getReType() {
        return reType;
    }

    public void setReType(Integer reType) {
        this.reType = reType;
    }

    public String getReReasons() {
        return reReasons;
    }

    public void setReReasons(String reReasons) {
        this.reReasons = reReasons;
    }

    public Double getReMoney() {
        return reMoney;
    }

    public void setReMoney(Double reMoney) {
        this.reMoney = reMoney;
    }

    public Double getRepaymentMoney() {
        return repaymentMoney;
    }

    public void setRepaymentMoney(Double repaymentMoney) {
        this.repaymentMoney = repaymentMoney;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeNo() {
        return payeeNo;
    }

    public void setPayeeNo(String payeeNo) {
        this.payeeNo = payeeNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchBank() {
        return branchBank;
    }

    public void setBranchBank(String branchBank) {
        this.branchBank = branchBank;
    }

    public String getBankImage() {
        return bankImage;
    }

    public void setBankImage(String bankImage) {
        this.bankImage = bankImage;
    }

    public String getApplyDesc() {
        return applyDesc;
    }

    public void setApplyDesc(String applyDesc) {
        this.applyDesc = applyDesc;
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

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    public List<FinancialCostBear> getFinancialCostBearList() {
        return financialCostBearList;
    }

    public void setFinancialCostBearList(List<FinancialCostBear> financialCostBearList) {
        this.financialCostBearList = financialCostBearList;
    }

    public List<FinancialCostDetails> getFinancialCostDetails() {
        return financialCostDetails;
    }

    public void setFinancialCostDetails(List<FinancialCostDetails> financialCostDetails) {
        this.financialCostDetails = financialCostDetails;
    }

    public String getDepartmentNameStr() {
        return departmentNameStr;
    }

    public void setDepartmentNameStr(String departmentNameStr) {
        this.departmentNameStr = departmentNameStr;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public List<FinancialFile> getFinancialFileList() {
        return financialFileList;
    }

    public void setFinancialFileList(List<FinancialFile> financialFileList) {
        this.financialFileList = financialFileList;
    }

    public Date getCeoEndTime() {
        return ceoEndTime;
    }

    public void setCeoEndTime(Date ceoEndTime) {
        this.ceoEndTime = ceoEndTime;
    }

    public String getBackReason() {
        return backReason;
    }

    public void setBackReason(String backReason) {
        this.backReason = backReason;
    }

    public Double getCostSumMoney() {
        return costSumMoney;
    }

    public void setCostSumMoney(Double costSumMoney) {
        this.costSumMoney = costSumMoney;
    }

    public Boolean getCeoRole() {
        return ceoRole;
    }

    public void setCeoRole(Boolean ceoRole) {
        this.ceoRole = ceoRole;
    }

    public Boolean getOrganManagerRole() {
        return organManagerRole;
    }

    public void setOrganManagerRole(Boolean organManagerRole) {
        this.organManagerRole = organManagerRole;
    }

    public Boolean getSuperiorManagerRole() {
        return superiorManagerRole;
    }

    public void setSuperiorManagerRole(Boolean superiorManagerRole) {
        this.superiorManagerRole = superiorManagerRole;
    }

    public Boolean getFinanceRole() {
        return financeRole;
    }

    public void setFinanceRole(Boolean financeRole) {
        this.financeRole = financeRole;
    }

    public Boolean getFinanceMangeRole() {
        return financeMangeRole;
    }

    public void setFinanceMangeRole(Boolean financeMangeRole) {
        this.financeMangeRole = financeMangeRole;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getDepartmentUserId() {
        return departmentUserId;
    }

    public void setDepartmentUserId(Long departmentUserId) {
        this.departmentUserId = departmentUserId;
    }

    public String getDepartmentUserName() {
        return departmentUserName;
    }

    public void setDepartmentUserName(String departmentUserName) {
        this.departmentUserName = departmentUserName;
    }

    public String getOrganManagerUserNames() {
        return organManagerUserNames;
    }

    public void setOrganManagerUserNames(String organManagerUserNames) {
        this.organManagerUserNames = organManagerUserNames;
    }

    public String getSuperiorManagerUserNames() {
        return superiorManagerUserNames;
    }

    public void setSuperiorManagerUserNames(String superiorManagerUserNames) {
        this.superiorManagerUserNames = superiorManagerUserNames;
    }

    public String getShowOprUserNames() {
        return showOprUserNames;
    }

    public void setShowOprUserNames(String showOprUserNames) {
        this.showOprUserNames = showOprUserNames;
    }

    public Boolean getLefanRole() {
        return lefanRole;
    }

    public void setLefanRole(Boolean lefanRole) {
        this.lefanRole = lefanRole;
    }

    public Long getZhuanUserId() {
        return zhuanUserId;
    }

    public void setZhuanUserId(Long zhuanUserId) {
        this.zhuanUserId = zhuanUserId;
    }

    public String getZhuanUserName() {
        return zhuanUserName;
    }

    public void setZhuanUserName(String zhuanUserName) {
        this.zhuanUserName = zhuanUserName;
    }

    public Integer getZhuanType() {
        return zhuanType;
    }

    public void setZhuanType(Integer zhuanType) {
        this.zhuanType = zhuanType;
    }

    public Integer getZhuanOprState() {
        return zhuanOprState;
    }

    public void setZhuanOprState(Integer zhuanOprState) {
        this.zhuanOprState = zhuanOprState;
    }

    public Long getCurUserId() {
        return curUserId;
    }

    public void setCurUserId(Long curUserId) {
        this.curUserId = curUserId;
    }

    public Integer getIsCanModify() {
        return isCanModify;
    }

    public void setIsCanModify(Integer isCanModify) {
        this.isCanModify = isCanModify;
    }

    public Long getPayUserId() {
        return payUserId;
    }

    public void setPayUserId(Long payUserId) {
        this.payUserId = payUserId;
    }

    public Boolean getCwzjRole() {
        return cwzjRole;
    }

    public void setCwzjRole(Boolean cwzjRole) {
        this.cwzjRole = cwzjRole;
    }
}