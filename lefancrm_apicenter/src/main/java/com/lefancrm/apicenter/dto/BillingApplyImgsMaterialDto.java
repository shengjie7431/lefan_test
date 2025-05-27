package com.lefancrm.apicenter.dto;

import java.util.Date;

public class BillingApplyImgsMaterialDto {

    private Long caseId;

    private String caseNo;

    private String caseTitle;

    private String billingCode;//发票号码

    private String billingImgs;//发票路径

    private Date imgsCreateTime;//发票创建时间

    private String imgsCreateBy;//发票创建人

    private Integer imgsState;//发票状态

    private String materialImgs;//材料路径

    private Date materialCreateTime;//材料创建时间

    private String materialCreateBy;//材料创建人

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getBillingCode() {
        return billingCode;
    }

    public void setBillingCode(String billingCode) {
        this.billingCode = billingCode;
    }

    public String getBillingImgs() {
        return billingImgs;
    }

    public void setBillingImgs(String billingImgs) {
        this.billingImgs = billingImgs;
    }

    public Date getImgsCreateTime() {
        return imgsCreateTime;
    }

    public void setImgsCreateTime(Date imgsCreateTime) {
        this.imgsCreateTime = imgsCreateTime;
    }

    public String getImgsCreateBy() {
        return imgsCreateBy;
    }

    public void setImgsCreateBy(String imgsCreateBy) {
        this.imgsCreateBy = imgsCreateBy;
    }

    public Integer getImgsState() {
        return imgsState;
    }

    public void setImgsState(Integer imgsState) {
        this.imgsState = imgsState;
    }

    public String getMaterialImgs() {
        return materialImgs;
    }

    public void setMaterialImgs(String materialImgs) {
        this.materialImgs = materialImgs;
    }

    public Date getMaterialCreateTime() {
        return materialCreateTime;
    }

    public void setMaterialCreateTime(Date materialCreateTime) {
        this.materialCreateTime = materialCreateTime;
    }

    public String getMaterialCreateBy() {
        return materialCreateBy;
    }

    public void setMaterialCreateBy(String materialCreateBy) {
        this.materialCreateBy = materialCreateBy;
    }
}