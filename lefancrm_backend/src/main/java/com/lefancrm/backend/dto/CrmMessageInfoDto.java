package com.lefancrm.backend.dto;

import java.util.Date;

/**
 * Created by liuting on 2018/1/2.
 */
public class CrmMessageInfoDto {
    private Long id;

    private String titile;

    private String content;

    private Date sendTime;

    private Integer msgType;
    private Integer isRead;
    private Integer deleteFlag;
    private Integer isSend;
    private String sendName;
    private String receiveName;

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitile() {
        return titile;
    }

    @Override
    public String toString() {
        return "CrmMessageInfoDto{" +
                "id=" + id +
                ", titile='" + titile + '\'' +
                ", content='" + content + '\'' +
                ", sendTime=" + sendTime +
                ", msgType=" + msgType +
                ", isRead=" + isRead +
                ", deleteFlag=" + deleteFlag +
                '}';
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }
}
