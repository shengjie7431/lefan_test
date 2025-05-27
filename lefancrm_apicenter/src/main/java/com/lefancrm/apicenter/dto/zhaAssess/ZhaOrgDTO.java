package com.lefancrm.apicenter.dto.zhaAssess;

public class ZhaOrgDTO {
    private Long zhaAssessId;
    private Long surveyOrgId;
    private String surveyOrgName;
    private Integer caseNum;//案件数量
    private Double money1;//减损金额
    private Double money2;//委托方确认金额
    private Double money3;//投产比
    private Integer sunCaseNum;//阳性案件数量
    private Double sunRate;//阳性率
    private Double eff;//平均时效
    private Double checkSunMoney;//阳性率考核
    private Double surveySubmitMoney;//调查放确认金额


    /**
     * 赔付金额
     */
    private Double pfAmt;

    /**
     * 减损奖励金
     */
    private Double jsAmt;


    /**
     * 减损率
     */
    private Double jsl;

    /**
     * 高额3W案件突破率
     */
    private Double ge3wRate;
    /**
     * 高额5W案件突破率
     */
    private Double ge5wRate;

    /**
     * 时效奖励
     */
    private Double effMoney;

    public Long getZhaAssessId() {
        return zhaAssessId;
    }

    public void setZhaAssessId(Long zhaAssessId) {
        this.zhaAssessId = zhaAssessId;
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

    public Integer getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(Integer caseNum) {
        this.caseNum = caseNum;
    }

    public Double getMoney1() {
        return money1;
    }

    public void setMoney1(Double money1) {
        this.money1 = money1;
    }

    public Double getMoney2() {
        return money2;
    }

    public void setMoney2(Double money2) {
        this.money2 = money2;
    }

    public Double getMoney3() {
        return money3;
    }

    public void setMoney3(Double money3) {
        this.money3 = money3;
    }

    public Integer getSunCaseNum() {
        return sunCaseNum;
    }

    public void setSunCaseNum(Integer sunCaseNum) {
        this.sunCaseNum = sunCaseNum;
    }

    public Double getSunRate() {
        return sunRate;
    }

    public void setSunRate(Double sunRate) {
        this.sunRate = sunRate;
    }

    public Double getEff() {
        return eff;
    }

    public void setEff(Double eff) {
        this.eff = eff;
    }

    public Double getCheckSunMoney() {
        return checkSunMoney;
    }

    public void setCheckSunMoney(Double checkSunMoney) {
        this.checkSunMoney = checkSunMoney;
    }

    public Double getSurveySubmitMoney() {
        return surveySubmitMoney;
    }

    public void setSurveySubmitMoney(Double surveySubmitMoney) {
        this.surveySubmitMoney = surveySubmitMoney;
    }

    public Double getPfAmt() {
        return pfAmt;
    }

    public void setPfAmt(Double pfAmt) {
        this.pfAmt = pfAmt;
    }

    public Double getJsAmt() {
        return jsAmt;
    }

    public void setJsAmt(Double jsAmt) {
        this.jsAmt = jsAmt;
    }

    public Double getJsl() {
        return jsl;
    }

    public void setJsl(Double jsl) {
        this.jsl = jsl;
    }

    public Double getGe3wRate() {
        return ge3wRate;
    }

    public void setGe3wRate(Double ge3wRate) {
        this.ge3wRate = ge3wRate;
    }

    public Double getGe5wRate() {
        return ge5wRate;
    }

    public void setGe5wRate(Double ge5wRate) {
        this.ge5wRate = ge5wRate;
    }

    public Double getEffMoney() {
        return effMoney;
    }

    public void setEffMoney(Double effMoney) {
        this.effMoney = effMoney;
    }
}
