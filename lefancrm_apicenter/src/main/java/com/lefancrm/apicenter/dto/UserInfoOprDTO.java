package com.lefancrm.apicenter.dto;

public class UserInfoOprDTO {
    private Long userId;
    private String userName;

    //select框 待审核案件数以及超期数
    private Integer oprNum;
    private Integer oprOverNum;
    private Double overRate;
    private Double basScore;
    private Double sunScore;
    private Double totalScore;
    private int sunCaseNum;
    private Double sunRate;

    //当期保司通过案件数
    private Integer tnocaticitcp;
    //当期保司通过率
    private Double tnocaticitcpRate;
    //滚动保司通过案件数
    private Integer nocpbrpd;
    //滚动保司通过率
    private Double nocpbrpdRate;

    //当期保司通过率总数
    private Double tnocaticitcpNumRate;
    //滚动保司通过率总数
    private Double nocpbrpdNumRate;

    /**
     * 总时效
     */
    private Double totalPrescription;

    /**
     * 时效
     */
    private Double prescription;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getOprNum() {
        return oprNum;
    }

    public void setOprNum(Integer oprNum) {
        this.oprNum = oprNum;
    }

    public Integer getOprOverNum() {
        return oprOverNum;
    }

    public void setOprOverNum(Integer oprOverNum) {
        this.oprOverNum = oprOverNum;
    }

    public Double getOverRate() {
        return overRate;
    }

    public void setOverRate(Double overRate) {
        this.overRate = overRate;
    }

    public Double getBasScore() {
        return basScore;
    }

    public void setBasScore(Double basScore) {
        this.basScore = basScore;
    }

    public Double getSunScore() {
        return sunScore;
    }

    public void setSunScore(Double sunScore) {
        this.sunScore = sunScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public int getSunCaseNum() {
        return sunCaseNum;
    }

    public void setSunCaseNum(int sunCaseNum) {
        this.sunCaseNum = sunCaseNum;
    }

    public Double getSunRate() {
        return sunRate;
    }

    public void setSunRate(Double sunRate) {
        this.sunRate = sunRate;
    }

    public Integer getTnocaticitcp() {
        return tnocaticitcp;
    }

    public void setTnocaticitcp(Integer tnocaticitcp) {
        this.tnocaticitcp = tnocaticitcp;
    }

    public Double getTnocaticitcpRate() {
        return tnocaticitcpRate;
    }

    public void setTnocaticitcpRate(Double tnocaticitcpRate) {
        this.tnocaticitcpRate = tnocaticitcpRate;
    }

    public Integer getNocpbrpd() {
        return nocpbrpd;
    }

    public void setNocpbrpd(Integer nocpbrpd) {
        this.nocpbrpd = nocpbrpd;
    }

    public Double getNocpbrpdRate() {
        return nocpbrpdRate;
    }

    public void setNocpbrpdRate(Double nocpbrpdRate) {
        this.nocpbrpdRate = nocpbrpdRate;
    }

    public Double getTnocaticitcpNumRate() {
        return tnocaticitcpNumRate;
    }

    public void setTnocaticitcpNumRate(Double tnocaticitcpNumRate) {
        this.tnocaticitcpNumRate = tnocaticitcpNumRate;
    }

    public Double getNocpbrpdNumRate() {
        return nocpbrpdNumRate;
    }

    public void setNocpbrpdNumRate(Double nocpbrpdNumRate) {
        this.nocpbrpdNumRate = nocpbrpdNumRate;
    }

    public Double getTotalPrescription() {
        return totalPrescription;
    }

    public void setTotalPrescription(Double totalPrescription) {
        this.totalPrescription = totalPrescription;
    }

    public Double getPrescription() {
        return prescription;
    }

    public void setPrescription(Double prescription) {
        this.prescription = prescription;
    }
}
