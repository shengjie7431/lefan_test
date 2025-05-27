package com.lefancrm.apicenter.dto;

import java.io.Serializable;

/**
 * Created by Jani on 2017/12/19.
 */
public class NoSignUserDto implements Serializable {
    private Long userId;

    private String userName;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String img;

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

}
