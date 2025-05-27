package com.lefancrm.backend.dto;

import java.util.Date;
import java.util.List;

public class ThinkData {
    private Long id;

    private Date thinkTime;

    private Integer deleteFlag;

    private String userAndStateStr;

    private List<ManagerUser> managerUsers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getThinkTime() {
        return thinkTime;
    }

    public void setThinkTime(Date thinkTime) {
        this.thinkTime = thinkTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getUserAndStateStr() {
        return userAndStateStr;
    }

    public void setUserAndStateStr(String userAndStateStr) {
        this.userAndStateStr = userAndStateStr;
    }

    public List<ManagerUser> getManagerUsers() {
        return managerUsers;
    }

    public void setManagerUsers(List<ManagerUser> managerUsers) {
        this.managerUsers = managerUsers;
    }

    public class ManagerUser{
        private String userName;
        private String state;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}