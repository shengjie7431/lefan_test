package com.lefancrm.apicenter.dto.report;

public class SurveyClockDTO {
    private Long surveyOrgId;
    private String surveyOrgName;
    private Long surveyUserId;
    private String surveyUserName;
    private Integer clockNum;
    private Integer directionNum;
    private Double tempMoney;
    private String firstClockTime;
    private Long firstClockTimestamp;
    private Double totalMoney;
    private Integer caseNum;
    private Double avgCaseMoney;

    private Integer totalNum;//每次打卡的案子累计总数
    private Double avgEveryDayClockNum;//平均每日打卡次数
    private Double avgCaseClockNum;//平均每案打卡次数
    private Integer lackClockNum;//缺卡天数
    private Long everyDayFirstClockAllSec;//每天首次打卡时间相加得到所有的秒数
    private Integer clockDay;  //打卡天数
    private Double money1;
    private Double money2;
    private Double money3;
    private Double money4;
    private Double money5;
    private Double money6;
    private Double money7;
    private Double money8;
    private Double money9;

    private Double distance;//公里数
    private Double tempDistance;//公里数除以方向数

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

    public Integer getClockNum() {
        return clockNum;
    }

    public void setClockNum(Integer clockNum) {
        this.clockNum = clockNum;
    }

    public String getFirstClockTime() {
        return firstClockTime;
    }

    public void setFirstClockTime(String firstClockTime) {
        this.firstClockTime = firstClockTime;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(Integer caseNum) {
        this.caseNum = caseNum;
    }

    public Double getAvgCaseMoney() {
        return avgCaseMoney;
    }

    public void setAvgCaseMoney(Double avgCaseMoney) {
        this.avgCaseMoney = avgCaseMoney;
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

    public Double getMoney4() {
        return money4;
    }

    public void setMoney4(Double money4) {
        this.money4 = money4;
    }

    public Double getMoney5() {
        return money5;
    }

    public void setMoney5(Double money5) {
        this.money5 = money5;
    }

    public Double getMoney6() {
        return money6;
    }

    public void setMoney6(Double money6) {
        this.money6 = money6;
    }

    public Double getMoney7() {
        return money7;
    }

    public void setMoney7(Double money7) {
        this.money7 = money7;
    }

    public Double getMoney8() {
        return money8;
    }

    public void setMoney8(Double money8) {
        this.money8 = money8;
    }

    public Double getMoney9() {
        return money9;
    }

    public void setMoney9(Double money9) {
        this.money9 = money9;
    }

    public Long getFirstClockTimestamp() {
        return firstClockTimestamp;
    }

    public void setFirstClockTimestamp(Long firstClockTimestamp) {
        this.firstClockTimestamp = firstClockTimestamp;
    }

    public Double getAvgEveryDayClockNum() {
        return avgEveryDayClockNum;
    }

    public void setAvgEveryDayClockNum(Double avgEveryDayClockNum) {
        this.avgEveryDayClockNum = avgEveryDayClockNum;
    }

    public Double getAvgCaseClockNum() {
        return avgCaseClockNum;
    }

    public void setAvgCaseClockNum(Double avgCaseClockNum) {
        this.avgCaseClockNum = avgCaseClockNum;
    }

    public Integer getLackClockNum() {
        return lackClockNum;
    }

    public void setLackClockNum(Integer lackClockNum) {
        this.lackClockNum = lackClockNum;
    }

    public Long getEveryDayFirstClockAllSec() {
        return everyDayFirstClockAllSec;
    }

    public void setEveryDayFirstClockAllSec(Long everyDayFirstClockAllSec) {
        this.everyDayFirstClockAllSec = everyDayFirstClockAllSec;
    }

    public Integer getClockDay() {
        return clockDay;
    }

    public void setClockDay(Integer clockDay) {
        this.clockDay = clockDay;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getDirectionNum() {
        return directionNum;
    }

    public void setDirectionNum(Integer directionNum) {
        this.directionNum = directionNum;
    }

    public Double getTempMoney() {
        return tempMoney;
    }

    public void setTempMoney(Double tempMoney) {
        this.tempMoney = tempMoney;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getTempDistance() {
        return tempDistance;
    }

    public void setTempDistance(Double tempDistance) {
        this.tempDistance = tempDistance;
    }
}
