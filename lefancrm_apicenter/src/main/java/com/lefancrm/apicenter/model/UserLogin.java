package com.lefancrm.apicenter.model;

import java.util.Date;

public class UserLogin {
    private Long userId;

    private String password;

    private String userTelphone;

    private String wechatId;

    private String weiboId;

    private Date createTime;

    private Date modifyTime;

    private String lfpcOpenid;

    private String gxlpOpenid;

    private String lfpcUnionid;

    private String gxlpUnionid;

    private String ddrOpenid;

    private String ddrUnionid;

    private String lfpc2Openid;

    private String lfpc2Unionid;



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserTelphone() {
        return userTelphone;
    }

    public void setUserTelphone(String userTelphone) {
        this.userTelphone = userTelphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getLfpcOpenid() {
        return lfpcOpenid;
    }

    public void setLfpcOpenid(String lfpcOpenid) {
        this.lfpcOpenid = lfpcOpenid;
    }

    public String getGxlpOpenid() {
        return gxlpOpenid;
    }

    public void setGxlpOpenid(String gxlpOpenid) {
        this.gxlpOpenid = gxlpOpenid;
    }

    public String getLfpcUnionid() {
        return lfpcUnionid;
    }

    public void setLfpcUnionid(String lfpcUnionid) {
        this.lfpcUnionid = lfpcUnionid;
    }

    public String getGxlpUnionid() {
        return gxlpUnionid;
    }

    public void setGxlpUnionid(String gxlpUnionid) {
        this.gxlpUnionid = gxlpUnionid;
    }

    public String getDdrOpenid() {
        return ddrOpenid;
    }

    public void setDdrOpenid(String ddrOpenid) {
        this.ddrOpenid = ddrOpenid;
    }

    public String getDdrUnionid() {
        return ddrUnionid;
    }

    public void setDdrUnionid(String ddrUnionid) {
        this.ddrUnionid = ddrUnionid;
    }

    public String getLfpc2Openid() {
        return lfpc2Openid;
    }

    public void setLfpc2Openid(String lfpc2Openid) {
        this.lfpc2Openid = lfpc2Openid;
    }

    public String getLfpc2Unionid() {
        return lfpc2Unionid;
    }

    public void setLfpc2Unionid(String lfpc2Unionid) {
        this.lfpc2Unionid = lfpc2Unionid;
    }
}