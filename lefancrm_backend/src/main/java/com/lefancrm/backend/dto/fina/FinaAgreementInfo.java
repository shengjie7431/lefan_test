package com.lefancrm.backend.dto.fina;

import java.util.Date;

public class FinaAgreementInfo {
    private Long id;

    private Long finaId;

    private Long finaInfoId;

    private String agreementName;

    private Integer signState;

    private String agreementPath;

    private String signAgreementPath;

    private String signAgreementPathLocal;

    private Date signTime;

    private String signUserName;

    private String signUserIdcard;

    private Long surveyUserId;

    private String surveyUserName;

    private Integer fileType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinaId() {
        return finaId;
    }

    public void setFinaId(Long finaId) {
        this.finaId = finaId;
    }

    public Long getFinaInfoId() {
        return finaInfoId;
    }

    public void setFinaInfoId(Long finaInfoId) {
        this.finaInfoId = finaInfoId;
    }

    public String getAgreementName() {
        return agreementName;
    }

    public void setAgreementName(String agreementName) {
        this.agreementName = agreementName;
    }

    public Integer getSignState() {
        return signState;
    }

    public void setSignState(Integer signState) {
        this.signState = signState;
    }

    public String getAgreementPath() {
        return agreementPath;
    }

    public void setAgreementPath(String agreementPath) {
        this.agreementPath = agreementPath;
    }

    public String getSignAgreementPath() {
        return signAgreementPath;
    }

    public void setSignAgreementPath(String signAgreementPath) {
        this.signAgreementPath = signAgreementPath;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getSignUserName() {
        return signUserName;
    }

    public void setSignUserName(String signUserName) {
        this.signUserName = signUserName;
    }

    public String getSignUserIdcard() {
        return signUserIdcard;
    }

    public void setSignUserIdcard(String signUserIdcard) {
        this.signUserIdcard = signUserIdcard;
    }

    public Long getSurveyUserId() {
        return surveyUserId;
    }

    public void setSurveyUserId(Long surveyUserId) {
        this.surveyUserId = surveyUserId;
    }

    public String getSurveyUserName() {
        return surveyUserName;
    }

    public void setSurveyUserName(String surveyUserName) {
        this.surveyUserName = surveyUserName;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getSignAgreementPathLocal() {
        return signAgreementPathLocal;
    }

    public void setSignAgreementPathLocal(String signAgreementPathLocal) {
        this.signAgreementPathLocal = signAgreementPathLocal;
    }
}