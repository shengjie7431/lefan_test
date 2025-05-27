package com.lefancrm.backend.dto.feere;

import java.util.List;

public class InvestigatorReInfoDTO {
    private Long id;
    private Long reId;
    private String feeReName;
    private Long surveyOrgId;
    private String surveyOrgName;
    private Long surveyUserId;
    private String surveyUserName;
    private Double totalMoney;
    private int reState;
    private String reStateName;
    private String rejectDesc;
    private String lineStr;
    private List<CanBeSuedCaseDTO> items;
    private Double dataFee;//互助-基层员工案件资料调阅及复印费
    private Double hzAccommodationFee;//互助-基层员工跨省跨市城际间差旅费报销-住宿费
    private Double hzTransportationFee;//互助-基层员工跨省跨市城际间差旅费报销-交通费
    private Double mileageSubsidy;//里程补贴
    private Boolean showBtn;
    private String preStateName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReId() {
        return reId;
    }

    public void setReId(Long reId) {
        this.reId = reId;
    }

    public String getFeeReName() {
        return feeReName;
    }

    public void setFeeReName(String feeReName) {
        this.feeReName = feeReName;
    }

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

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getReState() {
        return reState;
    }

    public void setReState(int reState) {
        this.reState = reState;
    }

    public String getReStateName() {
        return reStateName;
    }

    public void setReStateName(String reStateName) {
        this.reStateName = reStateName;
    }

    public String getRejectDesc() {
        return rejectDesc;
    }

    public void setRejectDesc(String rejectDesc) {
        this.rejectDesc = rejectDesc;
    }

    public String getLineStr() {
        return lineStr;
    }

    public void setLineStr(String lineStr) {
        this.lineStr = lineStr;
    }

    public List<CanBeSuedCaseDTO> getItems() {
        return items;
    }

    public void setItems(List<CanBeSuedCaseDTO> items) {
        this.items = items;
    }

    public Boolean getShowBtn() {
        return showBtn;
    }

    public void setShowBtn(Boolean showBtn) {
        this.showBtn = showBtn;
    }

    public Double getDataFee() {
        return dataFee;
    }

    public void setDataFee(Double dataFee) {
        this.dataFee = dataFee;
    }

    public Double getHzAccommodationFee() {
        return hzAccommodationFee;
    }

    public void setHzAccommodationFee(Double hzAccommodationFee) {
        this.hzAccommodationFee = hzAccommodationFee;
    }

    public Double getHzTransportationFee() {
        return hzTransportationFee;
    }

    public void setHzTransportationFee(Double hzTransportationFee) {
        this.hzTransportationFee = hzTransportationFee;
    }

    public Double getMileageSubsidy() {
        return mileageSubsidy;
    }

    public void setMileageSubsidy(Double mileageSubsidy) {
        this.mileageSubsidy = mileageSubsidy;
    }

    public String getPreStateName() {
        return preStateName;
    }

    public void setPreStateName(String preStateName) {
        this.preStateName = preStateName;
    }
}
