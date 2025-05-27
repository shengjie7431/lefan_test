package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.CasePersonalCwInfo;

public class CasePersonalCwInfoDto extends CasePersonalCwInfo {

    private Double costTotle;//成本&支出合计：诉讼费金额+佣金金额+维护费金额+利息支出+其他支出

    public Double getCostTotle() {
        return costTotle;
    }

    public void setCostTotle(Double costTotle) {
        this.costTotle = costTotle;
    }
}