package com.lefancrm.backend.dto;

import java.util.Date;

/**
 *带教奖励清单
 * @author EDZ
 */
public class SurveyTeachRewardListDto {
    private Long id;

    private String listName;

    private Integer teacherNum;

    private Integer studentNum;

    private Double teacherReward;

    private Integer state;

    private Date createTime;

    private String craeteBy;

    private Date updateTime;

    private String updateBy;

    private String returnDesc;

    private Date approvalTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Integer getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(Integer teacherNum) {
        this.teacherNum = teacherNum;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public Double getTeacherReward() {
        return teacherReward;
    }

    public void setTeacherReward(Double teacherReward) {
        this.teacherReward = teacherReward;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCraeteBy() {
        return craeteBy;
    }

    public void setCraeteBy(String craeteBy) {
        this.craeteBy = craeteBy;
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

    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }
}
