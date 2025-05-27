package com.lefancrm.backend.dto;

import java.util.Date;
import java.util.List;

public class InfoPublishsForumDto {
    private Long id;

    private Long publishsId;

    private String forumRemark;

    private Date createTime;

    private Long createBy;

    private String createName;

    private Integer isDelete;

    private List<InfoPublishsForumFileDto> infoPublishsForumFiles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPublishsId() {
        return publishsId;
    }

    public void setPublishsId(Long publishsId) {
        this.publishsId = publishsId;
    }

    public String getForumRemark() {
        return forumRemark;
    }

    public void setForumRemark(String forumRemark) {
        this.forumRemark = forumRemark;
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

    public List<InfoPublishsForumFileDto> getInfoPublishsForumFiles() {
        return infoPublishsForumFiles;
    }

    public void setInfoPublishsForumFiles(List<InfoPublishsForumFileDto> infoPublishsForumFiles) {
        this.infoPublishsForumFiles = infoPublishsForumFiles;
    }
}