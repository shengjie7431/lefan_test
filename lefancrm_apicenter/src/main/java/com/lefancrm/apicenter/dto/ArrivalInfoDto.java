package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.ArrivalInfo;

/**
 * Created by lixianfeng on 2018/4/9.
 */
public class ArrivalInfoDto extends ArrivalInfo{
    private String userCommissionName;

    public String getUserCommissionName() {
        return userCommissionName;
    }

    public void setUserCommissionName(String userCommissionName) {
        this.userCommissionName = userCommissionName;
    }
}
