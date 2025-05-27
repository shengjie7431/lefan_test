package com.lefancrm.backend.dto;

/**
 * Created by lixianfeng on 2019/6/19.
 */
public class SurveySourceScoreDetailDto {
    private Long userId;
    private String userName;
    private Double score;

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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
