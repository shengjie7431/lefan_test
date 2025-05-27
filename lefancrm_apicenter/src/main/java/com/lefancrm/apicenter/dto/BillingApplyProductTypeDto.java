package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.BillingApplyProductType;

/**
 * Created by lixianfeng on 2019/4/23.
 */
public class BillingApplyProductTypeDto extends BillingApplyProductType {
    private Boolean selected;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
