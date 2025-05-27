package com.lefancrm.backend.dto;

import java.io.Serializable;

/**
 * Created by ting on 2017/12/28.
 */
public class OrgActivityCountReportDto implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long orgId;
    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    private Double saleAmount;
    private Integer visitNum;
    private Integer targetNum;
    private Integer intentionNum;
    private Integer signNum;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Integer getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(Integer visitNum) {
        this.visitNum = visitNum;
    }

    public Integer getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(Integer targetNum) {
        this.targetNum = targetNum;
    }

    public Integer getIntentionNum() {
        return intentionNum;
    }

    public void setIntentionNum(Integer intentionNum) {
        this.intentionNum = intentionNum;
    }

    public Integer getSignNum() {
        return signNum;
    }

    public void setSignNum(Integer signNum) {
        this.signNum = signNum;
    }
}
