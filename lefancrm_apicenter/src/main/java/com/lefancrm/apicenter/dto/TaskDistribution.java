package com.lefancrm.apicenter.dto;

/**
 * 任务分布报表
 * @author EDZ
 */
public class TaskDistribution {

    private Long id;

    /**
     * 任务分类
     */
    private String taskClassification;

    /**
     * 任务数
     */
    private Integer taskNumber;

    /**
     * 占比
     */
    private Double proportion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskClassification() {
        return taskClassification;
    }

    public void setTaskClassification(String taskClassification) {
        this.taskClassification = taskClassification;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }
}
