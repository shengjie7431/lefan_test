package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.UserPoLevel;

public class UserPoLevelDto extends UserPoLevel{

    private String positionName;
    private String positionLevelName;

    public String getPositionLevelName() {
        return positionLevelName;
    }

    public void setPositionLevelName(String positionLevelName) {
        this.positionLevelName = positionLevelName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}