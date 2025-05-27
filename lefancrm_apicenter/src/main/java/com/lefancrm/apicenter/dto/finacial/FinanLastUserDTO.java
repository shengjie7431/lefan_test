package com.lefancrm.apicenter.dto.finacial;

import com.lefancrm.apicenter.model.UserInfo;

import java.util.List;

public class FinanLastUserDTO {
    private int state;
    private List<Long> users;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }

    public FinanLastUserDTO(int state, List<Long> users) {
        this.state = state;
        this.users = users;
    }

    public FinanLastUserDTO() {
    }
}
