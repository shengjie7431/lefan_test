package com.lefancrm.backend.dto;

import java.util.Date;
import java.util.List;

public class InfoPublishsDto {
    private Long id;

    private String userName;

    private String userTel;

    private String userCardid;

    private String userOtherInfo;

    private String problemRemark;

    private Date createTime;

    private Long createBy;

    private String createName;

    private Integer isDelete;

    private Integer isPublish;

    private List<InfoPublishsForumDto> infoPublishsForumDtos;

    private String safeCompanys;
    private String safeCompanyNames;

    private Boolean isAssignRole;//是否有分配协助保司的权限

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserCardid() {
        return userCardid;
    }

    public void setUserCardid(String userCardid) {
        this.userCardid = userCardid;
    }

    public String getUserOtherInfo() {
        return userOtherInfo;
    }

    public void setUserOtherInfo(String userOtherInfo) {
        this.userOtherInfo = userOtherInfo;
    }

    public String getProblemRemark() {
        return problemRemark;
    }

    public void setProblemRemark(String problemRemark) {
        this.problemRemark = problemRemark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public List<InfoPublishsForumDto> getInfoPublishsForumDtos() {
        return infoPublishsForumDtos;
    }

    public void setInfoPublishsForumDtos(List<InfoPublishsForumDto> infoPublishsForumDtos) {
        this.infoPublishsForumDtos = infoPublishsForumDtos;
    }

    public String getSafeCompanys() {
        return safeCompanys;
    }

    public void setSafeCompanys(String safeCompanys) {
        this.safeCompanys = safeCompanys;
    }

    public String getSafeCompanyNames() {
        return safeCompanyNames;
    }

    public void setSafeCompanyNames(String safeCompanyNames) {
        this.safeCompanyNames = safeCompanyNames;
    }

    public Boolean getIsAssignRole() {
        return isAssignRole;
    }

    public void setIsAssignRole(Boolean isAssignRole) {
        this.isAssignRole = isAssignRole;
    }
}