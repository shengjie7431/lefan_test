package com.lefancrm.backend.dto;

import java.util.Date;

public class InvalidismEstimateReportDto {
    private Long id;

    private String userName;

    private String accidentAddress;

    private String injuryDiagnose;

    private Integer isOperation;

    private String reportBasis;

    private String invalidismGrade;

    private String reportDesc;

    private Long estimateId;

    private Date applyTime;

    private Date createTime;

    private String invalidismGradeStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccidentAddress() {
        return accidentAddress;
    }

    public void setAccidentAddress(String accidentAddress) {
        this.accidentAddress = accidentAddress;
    }

    public String getInjuryDiagnose() {
        return injuryDiagnose;
    }

    public void setInjuryDiagnose(String injuryDiagnose) {
        this.injuryDiagnose = injuryDiagnose;
    }

    public Integer getIsOperation() {
        return isOperation;
    }

    public void setIsOperation(Integer isOperation) {
        this.isOperation = isOperation;
    }

    public String getReportBasis() {
        return reportBasis;
    }

    public void setReportBasis(String reportBasis) {
        this.reportBasis = reportBasis;
    }

    public String getInvalidismGrade() {
        return invalidismGrade;
    }

    public void setInvalidismGrade(String invalidismGrade) {
        this.invalidismGrade = invalidismGrade;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public Long getEstimateId() {
        return estimateId;
    }

    public void setEstimateId(Long estimateId) {
        this.estimateId = estimateId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInvalidismGradeStr() {
        return invalidismGradeStr;
    }

    public void setInvalidismGradeStr(String invalidismGradeStr) {
        this.invalidismGradeStr = invalidismGradeStr;
    }
}