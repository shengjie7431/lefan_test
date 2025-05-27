package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.BusinessRole;

/**
 * Created by lixianfeng on 2019/4/24.
 */
public class BusinessRoleDto extends BusinessRole{
    private Boolean selected;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
