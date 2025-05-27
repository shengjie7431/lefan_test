package com.lefancrm.apicenter.model;

import java.util.Date;

public class SurveyQa {
    private Long id;

    private String question;

    private String answer;

    private Date questionTime;

    private Date answerTime;

    private Long questionUserId;

    private String questionUserName;

    private Long answerUserId;

    private String answerUserName;

    private Integer questionState;

    private Integer questionType;

    private Integer questionLabel;

    private Integer isTop;

    private Integer deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Date questionTime) {
        this.questionTime = questionTime;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public Long getQuestionUserId() {
        return questionUserId;
    }

    public void setQuestionUserId(Long questionUserId) {
        this.questionUserId = questionUserId;
    }

    public String getQuestionUserName() {
        return questionUserName;
    }

    public void setQuestionUserName(String questionUserName) {
        this.questionUserName = questionUserName;
    }

    public Long getAnswerUserId() {
        return answerUserId;
    }

    public void setAnswerUserId(Long answerUserId) {
        this.answerUserId = answerUserId;
    }

    public String getAnswerUserName() {
        return answerUserName;
    }

    public void setAnswerUserName(String answerUserName) {
        this.answerUserName = answerUserName;
    }

    public Integer getQuestionState() {
        return questionState;
    }

    public void setQuestionState(Integer questionState) {
        this.questionState = questionState;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getQuestionLabel() {
        return questionLabel;
    }

    public void setQuestionLabel(Integer questionLabel) {
        this.questionLabel = questionLabel;
    }
}