package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

public class StaffOrgan {
    private Long id;

    private String name;

    private Long companyId;

    private String companyName;

    private Integer type;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Integer deleteFlag;

    private String organManagerName;

    private Long organManagerUserId;

    private Long organManagerStaffId;

    private Long superiorManagerUserId;

    private Long superiorManagerStaffId;

    private String superiorManagerName;

    private String code;//code码

    private Integer organAttribute;

    private Long organProduct;//xml 没有改。
    private String organProductName;//xml 没有改。

    private Integer organType;
    private Integer isCanModify;
    private Integer state;

    private Integer performance;

    private Integer accOutEqual;

    private Double warnMoney;

    private List<StaffOrganProduct> staffOrganProductList;

    public List<StaffOrganProduct> getStaffOrganProductList() {
        return staffOrganProductList;
    }

    public void setStaffOrganProductList(List<StaffOrganProduct> staffOrganProductList) {
        this.staffOrganProductList = staffOrganProductList;
    }
    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public Integer getPerformance() {
        return performance;
    }

    public Integer getState() {
        return state;
    }



    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOrganType() {
        return organType;
    }

    public void setOrganType(Integer organType) {
        this.organType = organType;
    }

    public Integer getIsCanModify() {
        return isCanModify;
    }

    public void setIsCanModify(Integer isCanModify) {
        this.isCanModify = isCanModify;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getOrganManagerName() {
        return organManagerName;
    }

    public void setOrganManagerName(String organManagerName) {
        this.organManagerName = organManagerName;
    }

    public Long getOrganManagerUserId() {
        return organManagerUserId;
    }

    public void setOrganManagerUserId(Long organManagerUserId) {
        this.organManagerUserId = organManagerUserId;
    }

    public Long getOrganManagerStaffId() {
        return organManagerStaffId;
    }

    public void setOrganManagerStaffId(Long organManagerStaffId) {
        this.organManagerStaffId = organManagerStaffId;
    }

    public Long getSuperiorManagerUserId() {
        return superiorManagerUserId;
    }

    public void setSuperiorManagerUserId(Long superiorManagerUserId) {
        this.superiorManagerUserId = superiorManagerUserId;
    }

    public Long getSuperiorManagerStaffId() {
        return superiorManagerStaffId;
    }

    public void setSuperiorManagerStaffId(Long superiorManagerStaffId) {
        this.superiorManagerStaffId = superiorManagerStaffId;
    }

    public String getSuperiorManagerName() {
        return superiorManagerName;
    }

    public void setSuperiorManagerName(String superiorManagerName) {
        this.superiorManagerName = superiorManagerName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getOrganAttribute() {
        return organAttribute;
    }

    public void setOrganAttribute(Integer organAttribute) {
        this.organAttribute = organAttribute;
    }

    public Long getOrganProduct() {
        return organProduct;
    }

    public void setOrganProduct(Long organProduct) {
        this.organProduct = organProduct;
    }

    public String getOrganProductName() {
        return organProductName;
    }

    public void setOrganProductName(String organProductName) {
        this.organProductName = organProductName;
    }

    public Integer getAccOutEqual() {
        return accOutEqual;
    }

    public void setAccOutEqual(Integer accOutEqual) {
        this.accOutEqual = accOutEqual;
    }

    public Double getWarnMoney() {
        return warnMoney;
    }

    public void setWarnMoney(Double warnMoney) {
        this.warnMoney = warnMoney;
    }
}