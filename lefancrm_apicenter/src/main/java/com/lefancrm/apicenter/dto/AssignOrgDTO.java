package com.lefancrm.apicenter.dto;

public class AssignOrgDTO {

    private Long id;

    private String name;

    /**
     * 新增委派
     */
    private Integer newlyAdded;

    /**
     * 调查费
     */
    private Double surveyMoney;

    /**
     * 委托方价格
     */
    private Double entrustMoney;

    /**
     * 新增保司终审通过
     */
    private Integer entrustEeportEndDate;

    /**
     * 调查时效
     */
    private Double agingReal;

    /**
     * 案件总数
     */
    private Integer total;

    /**
     * 阳性案件数
     */
    private Double positiveNum;

    /**
     * 阳性率
     */
    private Double positiveRate;

    /**
     * 退回案件数
     */
    private Double returnNum;

    /**
     * 退回率
     */
    private Double returnRate;

    /**
     * 绝对超期数量
     */
    private Double absolutelyNum;

    /**
     * 绝对超期率
     */
    private Double absolutelyRate;

    /**
     * 相对超期数量
     */
    private Double relativeNum;

    /**
     * 相对超期率
     */
    private Double relativeRate;

    /**
     * 分值
     */
    private Double score;

    private Integer directionNum;

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

    public Integer getNewlyAdded() {
        return newlyAdded;
    }

    public void setNewlyAdded(Integer newlyAdded) {
        this.newlyAdded = newlyAdded;
    }

    public Double getSurveyMoney() {
        return surveyMoney;
    }

    public void setSurveyMoney(Double surveyMoney) {
        this.surveyMoney = surveyMoney;
    }

    public Double getEntrustMoney() {
        return entrustMoney;
    }

    public void setEntrustMoney(Double entrustMoney) {
        this.entrustMoney = entrustMoney;
    }

    public Integer getEntrustEeportEndDate() {
        return entrustEeportEndDate;
    }

    public void setEntrustEeportEndDate(Integer entrustEeportEndDate) {
        this.entrustEeportEndDate = entrustEeportEndDate;
    }

    public Double getAgingReal() {
        return agingReal;
    }

    public void setAgingReal(Double agingReal) {
        this.agingReal = agingReal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getPositiveNum() {
        return positiveNum;
    }

    public void setPositiveNum(Double positiveNum) {
        this.positiveNum = positiveNum;
    }

    public Double getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(Double returnNum) {
        this.returnNum = returnNum;
    }

    public Double getAbsolutelyNum() {
        return absolutelyNum;
    }

    public void setAbsolutelyNum(Double absolutelyNum) {
        this.absolutelyNum = absolutelyNum;
    }

    public Double getAbsolutelyRate() {
        return absolutelyRate;
    }

    public void setAbsolutelyRate(Double absolutelyRate) {
        this.absolutelyRate = absolutelyRate;
    }

    public Double getRelativeNum() {
        return relativeNum;
    }

    public void setRelativeNum(Double relativeNum) {
        this.relativeNum = relativeNum;
    }

    public Double getRelativeRate() {
        return relativeRate;
    }

    public void setRelativeRate(Double relativeRate) {
        this.relativeRate = relativeRate;
    }

    public Double getPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(Double positiveRate) {
        this.positiveRate = positiveRate;
    }

    public Double getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(Double returnRate) {
        this.returnRate = returnRate;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getDirectionNum() {
        return directionNum;
    }

    public void setDirectionNum(Integer directionNum) {
        this.directionNum = directionNum;
    }
}
