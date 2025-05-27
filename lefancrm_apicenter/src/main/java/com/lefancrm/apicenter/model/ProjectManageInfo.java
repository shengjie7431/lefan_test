package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

/**
 * 项目管理
 * @author EDZ
 */
public class ProjectManageInfo {

    /**
     * 主键标识ID
     */
    private Integer id;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 行业所属名称
     */
    private String industryAttributes;

    /**
     * 行业所属ID
     */
    private Integer industryAttributesId;

    /**
     * 司内业务属性
     */
    private String businessAttributes;

    /**
     * 司内业务属性ID
     */
    private Integer businessAttributesId;

    /**
     * 项目经理ID
     */
    private Integer managerId;

    /**
     * 项目经理名称
     */
    private String managerName;

    /**
     * 项目状态（1：已接洽，2：已投标（谈判），3：已签约，4：已移交）
     */
    private Integer managerState;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 删除状态（0：否，1：是）
     */
    private Integer deleteFlag;

    /**
     * 价格模板ID
     */
    private Integer priceModelId;

    private Long entrustCaseNum;//委托按件数
    private Long finishCaseNum;//完成按件数
    private Double imgMoney;//已开票金额
    private Double imgAccountMoney;//开票已到账金额
    private Double imgNoAccountMoney;//开票未到账金额
    private Long entrustOrgId;//委托方机构id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getIndustryAttributes() {
        return industryAttributes;
    }

    public void setIndustryAttributes(String industryAttributes) {
        this.industryAttributes = industryAttributes;
    }

    public Integer getIndustryAttributesId() {
        return industryAttributesId;
    }

    public void setIndustryAttributesId(Integer industryAttributesId) {
        this.industryAttributesId = industryAttributesId;
    }

    public String getBusinessAttributes() {
        return businessAttributes;
    }

    public void setBusinessAttributes(String businessAttributes) {
        this.businessAttributes = businessAttributes;
    }

    public Integer getBusinessAttributesId() {
        return businessAttributesId;
    }

    public void setBusinessAttributesId(Integer businessAttributesId) {
        this.businessAttributesId = businessAttributesId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getManagerState() {
        return managerState;
    }

    public void setManagerState(Integer managerState) {
        this.managerState = managerState;
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

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getPriceModelId() {
        return priceModelId;
    }

    public void setPriceModelId(Integer priceModelId) {
        this.priceModelId = priceModelId;
    }

    public Long getEntrustCaseNum() {
        return entrustCaseNum;
    }

    public void setEntrustCaseNum(Long entrustCaseNum) {
        this.entrustCaseNum = entrustCaseNum;
    }

    public Long getFinishCaseNum() {
        return finishCaseNum;
    }

    public void setFinishCaseNum(Long finishCaseNum) {
        this.finishCaseNum = finishCaseNum;
    }

    public Double getImgMoney() {
        return imgMoney;
    }

    public void setImgMoney(Double imgMoney) {
        this.imgMoney = imgMoney;
    }

    public Double getImgAccountMoney() {
        return imgAccountMoney;
    }

    public void setImgAccountMoney(Double imgAccountMoney) {
        this.imgAccountMoney = imgAccountMoney;
    }

    public Double getImgNoAccountMoney() {
        return imgNoAccountMoney;
    }

    public void setImgNoAccountMoney(Double imgNoAccountMoney) {
        this.imgNoAccountMoney = imgNoAccountMoney;
    }

    public Long getEntrustOrgId() {
        return entrustOrgId;
    }

    public void setEntrustOrgId(Long entrustOrgId) {
        this.entrustOrgId = entrustOrgId;
    }
}
