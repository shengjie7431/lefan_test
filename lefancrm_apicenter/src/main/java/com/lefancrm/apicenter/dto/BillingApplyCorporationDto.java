package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.BillingApplyCorporation;

/**
 * Created by lixianfeng on 2019/4/23.
 */
public class BillingApplyCorporationDto extends BillingApplyCorporation {
    private Boolean selected;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
