package com.lefancrm.apicenter.fina.dto;

public class SettlementUserDTO {
    private Long userId;
    private String userName;

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

    public SettlementUserDTO(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
