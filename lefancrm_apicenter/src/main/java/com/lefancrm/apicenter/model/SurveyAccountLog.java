package com.lefancrm.apicenter.model;

import java.util.Date;


/**
 * 申请结算价格调整表
 * @author EDZ
 */
public class SurveyAccountLog {
    /**
     * 主键标识ID
     */
    private Long id;

    /**
     * 案件字表ID
     */
    private Long surveyInfoId;

    /**
     * 1:代表乐凡申请结算价格
     */
    private Long codeType;

    /**
     * 旧的基本价格
     */
    private Double oldBasicPrice;

    /**
     * 新的基本价格
     */
    private Double newBasicPrice;

    /**
     * 旧的减损价格
     */
    private Double oldDePrice;

    /**
     * 新额减损价格
     */
    private Double newDePrice;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 备注（修改原因）
     */
    private String upDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyInfoId() {
        return surveyInfoId;
    }

    public void setSurveyInfoId(Long surveyInfoId) {
        this.surveyInfoId = surveyInfoId;
    }

    public Long getCodeType() {
        return codeType;
    }

    public void setCodeType(Long codeType) {
        this.codeType = codeType;
    }

    public Double getOldBasicPrice() {
        return oldBasicPrice;
    }

    public void setOldBasicPrice(Double oldBasicPrice) {
        this.oldBasicPrice = oldBasicPrice;
    }

    public Double getNewBasicPrice() {
        return newBasicPrice;
    }

    public void setNewBasicPrice(Double newBasicPrice) {
        this.newBasicPrice = newBasicPrice;
    }

    public Double getOldDePrice() {
        return oldDePrice;
    }

    public void setOldDePrice(Double oldDePrice) {
        this.oldDePrice = oldDePrice;
    }

    public Double getNewDePrice() {
        return newDePrice;
    }

    public void setNewDePrice(Double newDePrice) {
        this.newDePrice = newDePrice;
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

    public String getUpDesc() {
        return upDesc;
    }

    public void setUpDesc(String upDesc) {
        this.upDesc = upDesc;
    }
}