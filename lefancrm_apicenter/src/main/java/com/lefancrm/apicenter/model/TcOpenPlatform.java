package com.lefancrm.apicenter.model;

import java.util.Date;
import java.util.List;

public class TcOpenPlatform {
    private Integer id;

    private String authkey;

    private String secert;

    private String grantAuthor;

    private Date grantTime;

    private Integer grandState;

    private String grandImg;

    private String userType;

    private List<TcOpenPlatformApi> tcOpenPlatformApiList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthkey() {
        return authkey;
    }

    public void setAuthkey(String authkey) {
        this.authkey = authkey;
    }

    public String getSecert() {
        return secert;
    }

    public void setSecert(String secert) {
        this.secert = secert;
    }

    public String getGrantAuthor() {
        return grantAuthor;
    }

    public void setGrantAuthor(String grantAuthor) {
        this.grantAuthor = grantAuthor;
    }

    public Date getGrantTime() {
        return grantTime;
    }

    public void setGrantTime(Date grantTime) {
        this.grantTime = grantTime;
    }

    public Integer getGrandState() {
        return grandState;
    }

    public void setGrandState(Integer grandState) {
        this.grandState = grandState;
    }

    public String getGrandImg() {
        return grandImg;
    }

    public void setGrandImg(String grandImg) {
        this.grandImg = grandImg;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<TcOpenPlatformApi> getTcOpenPlatformApiList() {
        return tcOpenPlatformApiList;
    }

    public void setTcOpenPlatformApiList(List<TcOpenPlatformApi> tcOpenPlatformApiList) {
        this.tcOpenPlatformApiList = tcOpenPlatformApiList;
    }
}