package com.lefancrm.apicenter.dto;

/**
 * 调查员报表
 * @author EDZ
 */
public class InvestigatorReportDto {
    /**
     * 在途调查案件数
     */
    private Integer investigatedNumber;

    /**
     * 在途超期案件数
     */
    private Integer overdueNumber;

    /**
     *平台复审通过的总积分
     */
    private Double score;

    /**
     * 职称等级
     */
    private String titleGrade;

    /**
     * 承接案件数
     */
    private Double acceptedNumber;

    /**
     * 承接案件数环比
     */
    private Double acceptedNumberChainRatio;

    /**
     * 平台复审通过案件数
     */
    private Double platformReviewNumber;

    /**
     *平台复审通过案件数环比
     */
    private Double platformReviewNumberChainRatio;

    /**
     * 保司终审通过件数
     */
    private Double insuranceCompanyNumber;

    /**
     * 保司终审通过件数环比
     */
    private Double insuranceCompanyNumberChainRatio;

    /**
     * 平台终审通过任务积分
     */
    private Double platformTaskPoints;

    /**
     * 平台终审通过任务积分环比
     */
    private Double platformTaskPointsChainRatio;

    /**
     * 保司终审通过任务积分
     */
    private Double missionPoints;

    /**
     * 保司终审通过任务积分环比
     */
    private Double missionPointsChainRatio;

    /**
     * 超期案件数
     */
    private Double overdueCasesNumber;

    /**
     * 超期率
     */
    private Double overdueRate;

    /**
     * 超期率环比
     */
    private Double overdueRateChainRatio;

    /**
     * 驳回案件数
     */
    private Double rejectedCasesNumber;

    /**
     * 驳回率
     */
    private Double rejectionRate;

    /**
     * 驳回率环比
     */
    private Double rejectionRateChainRatio;

    /**
     * 阳性案件数
     */
    private Double positiveCasesNumber;

    /**
     * 阳性率
     */
    private Double positiveRate;

    /**
     * 阳性率环比
     */
    private Double positiveRateChainRatio;

    /**
     *查得率
     */
    private Double searchRate;

    /**
     *查得率环比
     */
    private Double searchRateRatio;

    /**
     *件均时效
     */
    private Double averageAging;

    /**
     *件均时效环比
     */
    private Double averageAgingRatio;

    public Integer getInvestigatedNumber() {
        return investigatedNumber;
    }

    public void setInvestigatedNumber(Integer investigatedNumber) {
        this.investigatedNumber = investigatedNumber;
    }

    public Integer getOverdueNumber() {
        return overdueNumber;
    }

    public void setOverdueNumber(Integer overdueNumber) {
        this.overdueNumber = overdueNumber;
    }

    public String getTitleGrade() {
        return titleGrade;
    }

    public void setTitleGrade(String titleGrade) {
        this.titleGrade = titleGrade;
    }

    public Double getAcceptedNumber() {
        return acceptedNumber;
    }

    public void setAcceptedNumber(Double acceptedNumber) {
        this.acceptedNumber = acceptedNumber;
    }

    public Double getAcceptedNumberChainRatio() {
        return acceptedNumberChainRatio;
    }

    public void setAcceptedNumberChainRatio(Double acceptedNumberChainRatio) {
        this.acceptedNumberChainRatio = acceptedNumberChainRatio;
    }

    public Double getPlatformReviewNumber() {
        return platformReviewNumber;
    }

    public void setPlatformReviewNumber(Double platformReviewNumber) {
        this.platformReviewNumber = platformReviewNumber;
    }

    public Double getPlatformReviewNumberChainRatio() {
        return platformReviewNumberChainRatio;
    }

    public void setPlatformReviewNumberChainRatio(Double platformReviewNumberChainRatio) {
        this.platformReviewNumberChainRatio = platformReviewNumberChainRatio;
    }

    public Double getInsuranceCompanyNumber() {
        return insuranceCompanyNumber;
    }

    public void setInsuranceCompanyNumber(Double insuranceCompanyNumber) {
        this.insuranceCompanyNumber = insuranceCompanyNumber;
    }

    public Double getInsuranceCompanyNumberChainRatio() {
        return insuranceCompanyNumberChainRatio;
    }

    public void setInsuranceCompanyNumberChainRatio(Double insuranceCompanyNumberChainRatio) {
        this.insuranceCompanyNumberChainRatio = insuranceCompanyNumberChainRatio;
    }

    public Double getPlatformTaskPoints() {
        return platformTaskPoints;
    }

    public void setPlatformTaskPoints(Double platformTaskPoints) {
        this.platformTaskPoints = platformTaskPoints;
    }

    public Double getPlatformTaskPointsChainRatio() {
        return platformTaskPointsChainRatio;
    }

    public void setPlatformTaskPointsChainRatio(Double platformTaskPointsChainRatio) {
        this.platformTaskPointsChainRatio = platformTaskPointsChainRatio;
    }

    public Double getMissionPoints() {
        return missionPoints;
    }

    public void setMissionPoints(Double missionPoints) {
        this.missionPoints = missionPoints;
    }

    public Double getMissionPointsChainRatio() {
        return missionPointsChainRatio;
    }

    public void setMissionPointsChainRatio(Double missionPointsChainRatio) {
        this.missionPointsChainRatio = missionPointsChainRatio;
    }

    public Double getOverdueCasesNumber() {
        return overdueCasesNumber;
    }

    public void setOverdueCasesNumber(Double overdueCasesNumber) {
        this.overdueCasesNumber = overdueCasesNumber;
    }

    public Double getOverdueRate() {
        return overdueRate;
    }

    public void setOverdueRate(Double overdueRate) {
        this.overdueRate = overdueRate;
    }

    public Double getOverdueRateChainRatio() {
        return overdueRateChainRatio;
    }

    public void setOverdueRateChainRatio(Double overdueRateChainRatio) {
        this.overdueRateChainRatio = overdueRateChainRatio;
    }

    public Double getRejectedCasesNumber() {
        return rejectedCasesNumber;
    }

    public void setRejectedCasesNumber(Double rejectedCasesNumber) {
        this.rejectedCasesNumber = rejectedCasesNumber;
    }

    public Double getRejectionRate() {
        return rejectionRate;
    }

    public void setRejectionRate(Double rejectionRate) {
        this.rejectionRate = rejectionRate;
    }

    public Double getRejectionRateChainRatio() {
        return rejectionRateChainRatio;
    }

    public void setRejectionRateChainRatio(Double rejectionRateChainRatio) {
        this.rejectionRateChainRatio = rejectionRateChainRatio;
    }

    public Double getPositiveCasesNumber() {
        return positiveCasesNumber;
    }

    public void setPositiveCasesNumber(Double positiveCasesNumber) {
        this.positiveCasesNumber = positiveCasesNumber;
    }

    public Double getPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(Double positiveRate) {
        this.positiveRate = positiveRate;
    }

    public Double getPositiveRateChainRatio() {
        return positiveRateChainRatio;
    }

    public void setPositiveRateChainRatio(Double positiveRateChainRatio) {
        this.positiveRateChainRatio = positiveRateChainRatio;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getSearchRate() {
        return searchRate;
    }

    public void setSearchRate(Double searchRate) {
        this.searchRate = searchRate;
    }

    public Double getSearchRateRatio() {
        return searchRateRatio;
    }

    public void setSearchRateRatio(Double searchRateRatio) {
        this.searchRateRatio = searchRateRatio;
    }

    public Double getAverageAging() {
        return averageAging;
    }

    public void setAverageAging(Double averageAging) {
        this.averageAging = averageAging;
    }

    public Double getAverageAgingRatio() {
        return averageAgingRatio;
    }

    public void setAverageAgingRatio(Double averageAgingRatio) {
        this.averageAgingRatio = averageAgingRatio;
    }
}
