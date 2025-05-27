package com.lefancrm.backend.dto.claims;

import com.lefancrm.backend.annotation.Excel;

import java.util.Date;

public class ClaimsArrangedInfo {
    private Long id;

    @Excel(name = "联系人",excelIndex = 0)
    private String linkName;

    @Excel(name = "联系电话",excelIndex = 1)
    private String linkTel;

    @Excel(name = "事故地点",excelIndex = 2)
    private String accidentAddress;

    private Double accidentXLbs;

    private Double accidentYLbs;

    @Excel(name = "创建时间",excelIndex = 3, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    private String createBy;

    @Excel(name = "处理状态",excelIndex = 4,enumValue = "0=未处理,1=已处理")
    private Integer handleState;

    @Excel(name = "处理人",excelIndex = 5)
    private String handleUser;

    @Excel(name = "处理时间",excelIndex = 6, dateFormat = "yyyy-MM-dd")
    private Date handleTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getAccidentAddress() {
        return accidentAddress;
    }

    public void setAccidentAddress(String accidentAddress) {
        this.accidentAddress = accidentAddress;
    }

    public Double getAccidentXLbs() {
        return accidentXLbs;
    }

    public void setAccidentXLbs(Double accidentXLbs) {
        this.accidentXLbs = accidentXLbs;
    }

    public Double getAccidentYLbs() {
        return accidentYLbs;
    }

    public void setAccidentYLbs(Double accidentYLbs) {
        this.accidentYLbs = accidentYLbs;
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
}