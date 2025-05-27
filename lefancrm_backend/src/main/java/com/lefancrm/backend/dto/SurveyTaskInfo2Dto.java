package com.lefancrm.backend.dto;

import java.util.Date;

public class SurveyTaskInfo2Dto {
    private Long taskId;

    private String taskName;

    private Long taskInfoContentId;

    private String taskInfoContentName;

    private Long directionResultTypeId;

    private String directionResultTypeName;

    private String infoName;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getTaskInfoContentId() {
        return taskInfoContentId;
    }

    public void setTaskInfoContentId(Long taskInfoContentId) {
        this.taskInfoContentId = taskInfoContentId;
    }

    public String getTaskInfoContentName() {
        return taskInfoContentName;
    }

    public void setTaskInfoContentName(String taskInfoContentName) {
        this.taskInfoContentName = taskInfoContentName;
    }

    public Long getDirectionResultTypeId() {
        return directionResultTypeId;
    }

    public void setDirectionResultTypeId(Long directionResultTypeId) {
        this.directionResultTypeId = directionResultTypeId;
    }

    public String getDirectionResultTypeName() {
        return directionResultTypeName;
    }

    public void setDirectionResultTypeName(String directionResultTypeName) {
        this.directionResultTypeName = directionResultTypeName;
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }
}