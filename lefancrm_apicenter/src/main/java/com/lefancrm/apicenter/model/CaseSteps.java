package com.lefancrm.apicenter.model;

import java.util.Date;

public class CaseSteps {
    private Long id;

    private Long caseCenterId;

    private String stepCode;

    private String stepName;

    private Date startTime;

    private Date endTime;

    private String createBy;

    private String updateBy;

    private Integer deleteFlag;

    private Integer curState;

    private Integer curStateStep;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCaseCenterId() {
        return caseCenterId;
    }

    public void setCaseCenterId(Long caseCenterId) {
        this.caseCenterId = caseCenterId;
    }

    public String getStepCode() {
        return stepCode;
    }

    public void setStepCode(String stepCode) {
        this.stepCode = stepCode;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public Integer getCurState() {
        return curState;
    }

    public void setCurState(Integer curState) {
        this.curState = curState;
    }

    public Integer getCurStateStep() {
        return curStateStep;
    }

    public void setCurStateStep(Integer curStateStep) {
        this.curStateStep = curStateStep;
    }
}