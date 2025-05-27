package com.lefancrm.apicenter.model;

public class SurveyTaskDirectionResult {
    private Long id;

    private Long taskInfoContentId;

    private String taskInfoContentName;

    private Long directionResultTypeId;

    private String directionResultTypeName;

    private String directionResultTypeCode;

    private Double score;

    private Double scoreRate;

    private Double pointScore;

    private Long taskId;

    private String taskName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDirectionResultTypeCode() {
        return directionResultTypeCode;
    }

    public void setDirectionResultTypeCode(String directionResultTypeCode) {
        this.directionResultTypeCode = directionResultTypeCode;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getScoreRate() {
        return scoreRate;
    }

    public void setScoreRate(Double scoreRate) {
        this.scoreRate = scoreRate;
    }

    public Double getPointScore() {
        return pointScore;
    }

    public void setPointScore(Double pointScore) {
        this.pointScore = pointScore;
    }

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
}