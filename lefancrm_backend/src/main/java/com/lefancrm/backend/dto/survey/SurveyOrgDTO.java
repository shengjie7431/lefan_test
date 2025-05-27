package com.lefancrm.backend.dto.survey;

import com.lefancrm.backend.dto.avg.SurveyHuzhuDTO;

import java.util.List;

public class SurveyOrgDTO {
    private Long surveyOrgId;
    private String surveyOrgName;
    private Long entrustOrgId;
    private String entrustOrgName;
    private String beginDate;
    private String endDate;
    private int caseNum;
    private int longNum;
    private Double longRate;
    private int vetoNum;
    private int vetoCaseNum;//驳回案件数
    private Double vetoRate;

    private SurveyHuzhuDTO surveyHuzhuDTO;

    List<SurveyOrgDetailDTO> longCaseList;//超时集合
    List<SurveyOrgDetailDTO> vetoCaseList;//驳回集合

    public Long getSurveyOrgId() {
        return surveyOrgId;
    }

    public void setSurveyOrgId(Long surveyOrgId) {
        this.surveyOrgId = surveyOrgId;
    }

    public String getSurveyOrgName() {
        return surveyOrgName;
    }

    public void setSurveyOrgName(String surveyOrgName) {
        this.surveyOrgName = surveyOrgName;
    }

    public Long getEntrustOrgId() {
        return entrustOrgId;
    }

    public void setEntrustOrgId(Long entrustOrgId) {
        this.entrustOrgId = entrustOrgId;
    }

    public String getEntrustOrgName() {
        return entrustOrgName;
    }

    public void setEntrustOrgName(String entrustOrgName) {
        this.entrustOrgName = entrustOrgName;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(int caseNum) {
        this.caseNum = caseNum;
    }

    public int getLongNum() {
        return longNum;
    }

    public void setLongNum(int longNum) {
        this.longNum = longNum;
    }

    public Double getLongRate() {
        return longRate;
    }

    public void setLongRate(Double longRate) {
        this.longRate = longRate;
    }

    public int getVetoNum() {
        return vetoNum;
    }

    public void setVetoNum(int vetoNum) {
        this.vetoNum = vetoNum;
    }

    public Double getVetoRate() {
        return vetoRate;
    }

    public void setVetoRate(Double vetoRate) {
        this.vetoRate = vetoRate;
    }

    public List<SurveyOrgDetailDTO> getLongCaseList() {
        return longCaseList;
    }

    public void setLongCaseList(List<SurveyOrgDetailDTO> longCaseList) {
        this.longCaseList = longCaseList;
    }

    public List<SurveyOrgDetailDTO> getVetoCaseList() {
        return vetoCaseList;
    }

    public void setVetoCaseList(List<SurveyOrgDetailDTO> vetoCaseList) {
        this.vetoCaseList = vetoCaseList;
    }

    public int getVetoCaseNum() {
        return vetoCaseNum;
    }

    public void setVetoCaseNum(int vetoCaseNum) {
        this.vetoCaseNum = vetoCaseNum;
    }

    public SurveyHuzhuDTO getSurveyHuzhuDTO() {
        return surveyHuzhuDTO;
    }

    public void setSurveyHuzhuDTO(SurveyHuzhuDTO surveyHuzhuDTO) {
        this.surveyHuzhuDTO = surveyHuzhuDTO;
    }
}
