package com.lefancrm.backend.dto.fina;

public class FinaSettlementApplicant {
    private Long id;

    private Long finaId;

    private Long finaInfoId;

    private Long settlementInfoId;

    private Integer finaNum;

    private String finaNumStr;

    private String caseApplicantNo;

    private String caseApplicantNoStr;

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

    public Long getSettlementInfoId() {
        return settlementInfoId;
    }

    public void setSettlementInfoId(Long settlementInfoId) {
        this.settlementInfoId = settlementInfoId;
    }

    public Integer getFinaNum() {
        return finaNum;
    }

    public void setFinaNum(Integer finaNum) {
        this.finaNum = finaNum;
    }

    public String getCaseApplicantNo() {
        return caseApplicantNo;
    }

    public void setCaseApplicantNo(String caseApplicantNo) {
        this.caseApplicantNo = caseApplicantNo;
    }

    public String getCaseApplicantNoStr() {
        return caseApplicantNoStr;
    }

    public void setCaseApplicantNoStr(String caseApplicantNoStr) {
        this.caseApplicantNoStr = caseApplicantNoStr;
    }

    public String getFinaNumStr() {
        return finaNumStr;
    }

    public void setFinaNumStr(String finaNumStr) {
        this.finaNumStr = finaNumStr;
    }
}