package com.lefancrm.backend.dto.claims;

import com.lefancrm.backend.annotation.Excel;

import java.util.Date;

public class ClaimsApplyInfo {
    private Long id;
    @Excel(name = "被保险人姓名",excelIndex = 0)
    private String insuredName;
    @Excel(name = "手机号码",excelIndex = 1)
    private String linkTel;
    @Excel(name = "保险公司",excelIndex = 2)
    private String insuranceCompany;
    @Excel(name = "险种",excelIndex = 3,enumValue = "1=健康险,2=意外险")
    private Integer insuranceType;
    @Excel(name = "处理状态",excelIndex = 4,enumValue = "0=未处理,1=已处理")
    private Integer handleState;
    @Excel(name = "处理人",excelIndex = 5)
    private String handleUser;
    @Excel(name = "处理时间",excelIndex = 6, dateFormat = "yyyy-MM-dd")
    private Date handleTime;
    @Excel(name = "创建时间",excelIndex = 7, dateFormat = "yyyy-MM-dd")
    private Date createTime;
    @Excel(name = "创建人",excelIndex = 8)
    private String createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public Integer getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(Integer insuranceType) {
        this.insuranceType = insuranceType;
    }

    public Integer getHandleState() {
        return handleState;
    }

    public void setHandleState(Integer handleState) {
        this.handleState = handleState;
    }

    public String getHandleUser() {
        return handleUser;
    }

    public void setHandleUser(String handleUser) {
        this.handleUser = handleUser;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
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
}