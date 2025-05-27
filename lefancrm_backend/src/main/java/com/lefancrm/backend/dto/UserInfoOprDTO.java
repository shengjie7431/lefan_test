package com.lefancrm.backend.dto;

public class UserInfoOprDTO {
    private Long userId;
    private String userName;

    private Integer oprNum;
    private Integer oprOverNum;

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
}
