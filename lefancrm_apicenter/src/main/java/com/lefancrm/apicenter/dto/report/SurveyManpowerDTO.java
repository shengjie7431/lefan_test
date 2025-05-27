package com.lefancrm.apicenter.dto.report;

public class SurveyManpowerDTO {
    private Long surveyOrgId;
    private String surveyOrgName;
    private int orgType;//是否存在片区机构 0否(B类)  1是(A类)
    private Integer orgLevel;
    //在编
    private int userNum1;//人数
    private int upUserNum1;//环比人数
    private Double num1Rate;//比例
    private Double userScore1;//积分
    private Double upUserScore1;//环比积分
    private Double score1Rate;//比例
    private Double userCaseNum1;
    private Double upUserCaseNum1;
    private Double userCaseNum1Rate;

    //调查学员
    private int userNum2;
    private int upUserNum2;
    private Double num2Rate;
    private Double userScore2;
    private Double upUserScore2;
    private Double score2Rate;
    private Double userCaseNum2;
    private Double upUserCaseNum2;
    private Double userCaseNum2Rate;
    //调查新人
    private int userNum3;
    private int upUserNum3;
    private Double num3Rate;
    private Double userScore3;
    private Double upUserScore3;
    private Double score3Rate;
    private Double userCaseNum3;
    private Double upUserCaseNum3;
    private Double userCaseNum3Rate;
    //有效调查员
    private int userNum4;
    private int upUserNum4;
    private Double num4Rate;
    private Double userScore4;
    private Double upUserScore4;
    private Double score4Rate;
    private Double userCaseNum4;
    private Double upUserCaseNum4;
    private Double userCaseNum4Rate;

    //合格调查员
    private int userNum5;
    private int upUserNum5;
    private Double num5Rate;
    private Double userScore5;
    private Double upUserScore5;
    private Double score5Rate;
    private Double userCaseNum5;
    private Double upUserCaseNum5;
    private Double userCaseNum5Rate;


    //活动调查员
    private int userNum6;
    private int upUserNum6;
    private Double num6Rate;
    private Double userScore6;
    private Double upUserScore6;
    private Double score6Rate;


    //沉默调查员
    private int userNum7;
    private int upUserNum7;
    private Double num7Rate;
    private Double userScore7;
    private Double upUserScore7;
    private Double score7Rate;

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

    public int getUserNum1() {
        return userNum1;
    }

    public void setUserNum1(int userNum1) {
        this.userNum1 = userNum1;
    }

    public int getUpUserNum1() {
        return upUserNum1;
    }

    public void setUpUserNum1(int upUserNum1) {
        this.upUserNum1 = upUserNum1;
    }

    public Double getNum1Rate() {
        return num1Rate;
    }

    public void setNum1Rate(Double num1Rate) {
        this.num1Rate = num1Rate;
    }

    public Double getUserScore1() {
        return userScore1;
    }

    public void setUserScore1(Double userScore1) {
        this.userScore1 = userScore1;
    }

    public Double getUpUserScore1() {
        return upUserScore1;
    }

    public void setUpUserScore1(Double upUserScore1) {
        this.upUserScore1 = upUserScore1;
    }

    public Double getScore1Rate() {
        return score1Rate;
    }

    public void setScore1Rate(Double score1Rate) {
        this.score1Rate = score1Rate;
    }

    public int getUserNum2() {
        return userNum2;
    }

    public void setUserNum2(int userNum2) {
        this.userNum2 = userNum2;
    }

    public int getUpUserNum2() {
        return upUserNum2;
    }

    public void setUpUserNum2(int upUserNum2) {
        this.upUserNum2 = upUserNum2;
    }

    public Double getNum2Rate() {
        return num2Rate;
    }

    public void setNum2Rate(Double num2Rate) {
        this.num2Rate = num2Rate;
    }

    public Double getUserScore2() {
        return userScore2;
    }

    public void setUserScore2(Double userScore2) {
        this.userScore2 = userScore2;
    }

    public Double getUpUserScore2() {
        return upUserScore2;
    }

    public void setUpUserScore2(Double upUserScore2) {
        this.upUserScore2 = upUserScore2;
    }

    public Double getScore2Rate() {
        return score2Rate;
    }

    public void setScore2Rate(Double score2Rate) {
        this.score2Rate = score2Rate;
    }

    public int getUserNum3() {
        return userNum3;
    }

    public void setUserNum3(int userNum3) {
        this.userNum3 = userNum3;
    }

    public int getUpUserNum3() {
        return upUserNum3;
    }

    public void setUpUserNum3(int upUserNum3) {
        this.upUserNum3 = upUserNum3;
    }

    public Double getNum3Rate() {
        return num3Rate;
    }

    public void setNum3Rate(Double num3Rate) {
        this.num3Rate = num3Rate;
    }

    public Double getUserScore3() {
        return userScore3;
    }

    public void setUserScore3(Double userScore3) {
        this.userScore3 = userScore3;
    }

    public Double getUpUserScore3() {
        return upUserScore3;
    }

    public void setUpUserScore3(Double upUserScore3) {
        this.upUserScore3 = upUserScore3;
    }

    public Double getScore3Rate() {
        return score3Rate;
    }

    public void setScore3Rate(Double score3Rate) {
        this.score3Rate = score3Rate;
    }

    public int getUserNum4() {
        return userNum4;
    }

    public void setUserNum4(int userNum4) {
        this.userNum4 = userNum4;
    }

    public int getUpUserNum4() {
        return upUserNum4;
    }

    public void setUpUserNum4(int upUserNum4) {
        this.upUserNum4 = upUserNum4;
    }

    public Double getNum4Rate() {
        return num4Rate;
    }

    public void setNum4Rate(Double num4Rate) {
        this.num4Rate = num4Rate;
    }

    public Double getUserScore4() {
        return userScore4;
    }

    public void setUserScore4(Double userScore4) {
        this.userScore4 = userScore4;
    }

    public Double getUpUserScore4() {
        return upUserScore4;
    }

    public void setUpUserScore4(Double upUserScore4) {
        this.upUserScore4 = upUserScore4;
    }

    public Double getScore4Rate() {
        return score4Rate;
    }

    public void setScore4Rate(Double score4Rate) {
        this.score4Rate = score4Rate;
    }

    public int getUserNum5() {
        return userNum5;
    }

    public void setUserNum5(int userNum5) {
        this.userNum5 = userNum5;
    }

    public int getUpUserNum5() {
        return upUserNum5;
    }

    public void setUpUserNum5(int upUserNum5) {
        this.upUserNum5 = upUserNum5;
    }

    public Double getNum5Rate() {
        return num5Rate;
    }

    public void setNum5Rate(Double num5Rate) {
        this.num5Rate = num5Rate;
    }

    public Double getUserScore5() {
        return userScore5;
    }

    public void setUserScore5(Double userScore5) {
        this.userScore5 = userScore5;
    }

    public Double getUpUserScore5() {
        return upUserScore5;
    }

    public void setUpUserScore5(Double upUserScore5) {
        this.upUserScore5 = upUserScore5;
    }

    public Double getScore5Rate() {
        return score5Rate;
    }

    public void setScore5Rate(Double score5Rate) {
        this.score5Rate = score5Rate;
    }

    public int getOrgType() {
        return orgType;
    }

    public void setOrgType(int orgType) {
        this.orgType = orgType;
    }

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public int getUserNum6() {
        return userNum6;
    }

    public void setUserNum6(int userNum6) {
        this.userNum6 = userNum6;
    }

    public int getUpUserNum6() {
        return upUserNum6;
    }

    public void setUpUserNum6(int upUserNum6) {
        this.upUserNum6 = upUserNum6;
    }

    public Double getNum6Rate() {
        return num6Rate;
    }

    public void setNum6Rate(Double num6Rate) {
        this.num6Rate = num6Rate;
    }

    public Double getUserScore6() {
        return userScore6;
    }

    public void setUserScore6(Double userScore6) {
        this.userScore6 = userScore6;
    }

    public Double getUpUserScore6() {
        return upUserScore6;
    }

    public void setUpUserScore6(Double upUserScore6) {
        this.upUserScore6 = upUserScore6;
    }

    public Double getScore6Rate() {
        return score6Rate;
    }

    public void setScore6Rate(Double score6Rate) {
        this.score6Rate = score6Rate;
    }

    public int getUserNum7() {
        return userNum7;
    }

    public void setUserNum7(int userNum7) {
        this.userNum7 = userNum7;
    }

    public int getUpUserNum7() {
        return upUserNum7;
    }

    public void setUpUserNum7(int upUserNum7) {
        this.upUserNum7 = upUserNum7;
    }

    public Double getNum7Rate() {
        return num7Rate;
    }

    public void setNum7Rate(Double num7Rate) {
        this.num7Rate = num7Rate;
    }

    public Double getUserScore7() {
        return userScore7;
    }

    public void setUserScore7(Double userScore7) {
        this.userScore7 = userScore7;
    }

    public Double getUpUserScore7() {
        return upUserScore7;
    }

    public void setUpUserScore7(Double upUserScore7) {
        this.upUserScore7 = upUserScore7;
    }

    public Double getScore7Rate() {
        return score7Rate;
    }

    public void setScore7Rate(Double score7Rate) {
        this.score7Rate = score7Rate;
    }

    public Double getUserCaseNum1() {
        return userCaseNum1;
    }

    public void setUserCaseNum1(Double userCaseNum1) {
        this.userCaseNum1 = userCaseNum1;
    }

    public Double getUpUserCaseNum1() {
        return upUserCaseNum1;
    }

    public void setUpUserCaseNum1(Double upUserCaseNum1) {
        this.upUserCaseNum1 = upUserCaseNum1;
    }

    public Double getUserCaseNum1Rate() {
        return userCaseNum1Rate;
    }

    public void setUserCaseNum1Rate(Double userCaseNum1Rate) {
        this.userCaseNum1Rate = userCaseNum1Rate;
    }

    public Double getUserCaseNum2() {
        return userCaseNum2;
    }

    public void setUserCaseNum2(Double userCaseNum2) {
        this.userCaseNum2 = userCaseNum2;
    }

    public Double getUpUserCaseNum2() {
        return upUserCaseNum2;
    }

    public void setUpUserCaseNum2(Double upUserCaseNum2) {
        this.upUserCaseNum2 = upUserCaseNum2;
    }

    public Double getUserCaseNum2Rate() {
        return userCaseNum2Rate;
    }

    public void setUserCaseNum2Rate(Double userCaseNum2Rate) {
        this.userCaseNum2Rate = userCaseNum2Rate;
    }

    public Double getUserCaseNum3() {
        return userCaseNum3;
    }

    public void setUserCaseNum3(Double userCaseNum3) {
        this.userCaseNum3 = userCaseNum3;
    }

    public Double getUpUserCaseNum3() {
        return upUserCaseNum3;
    }

    public void setUpUserCaseNum3(Double upUserCaseNum3) {
        this.upUserCaseNum3 = upUserCaseNum3;
    }

    public Double getUserCaseNum3Rate() {
        return userCaseNum3Rate;
    }

    public void setUserCaseNum3Rate(Double userCaseNum3Rate) {
        this.userCaseNum3Rate = userCaseNum3Rate;
    }

    public Double getUserCaseNum4() {
        return userCaseNum4;
    }

    public void setUserCaseNum4(Double userCaseNum4) {
        this.userCaseNum4 = userCaseNum4;
    }

    public Double getUpUserCaseNum4() {
        return upUserCaseNum4;
    }

    public void setUpUserCaseNum4(Double upUserCaseNum4) {
        this.upUserCaseNum4 = upUserCaseNum4;
    }

    public Double getUserCaseNum4Rate() {
        return userCaseNum4Rate;
    }

    public void setUserCaseNum4Rate(Double userCaseNum4Rate) {
        this.userCaseNum4Rate = userCaseNum4Rate;
    }

    public Double getUserCaseNum5() {
        return userCaseNum5;
    }

    public void setUserCaseNum5(Double userCaseNum5) {
        this.userCaseNum5 = userCaseNum5;
    }

    public Double getUpUserCaseNum5() {
        return upUserCaseNum5;
    }

    public void setUpUserCaseNum5(Double upUserCaseNum5) {
        this.upUserCaseNum5 = upUserCaseNum5;
    }

    public Double getUserCaseNum5Rate() {
        return userCaseNum5Rate;
    }

    public void setUserCaseNum5Rate(Double userCaseNum5Rate) {
        this.userCaseNum5Rate = userCaseNum5Rate;
    }
}
