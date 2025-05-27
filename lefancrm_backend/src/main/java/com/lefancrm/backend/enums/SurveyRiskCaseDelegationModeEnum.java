package com.lefancrm.backend.enums;

/**
 * @Description: 委托方式
 * @Author: zhang.h
 * @Date: 2024/8/22 14:12
 * @Version: 1.0.0
 */


public enum SurveyRiskCaseDelegationModeEnum {
    DELEGATION_MODE_1(1, "系统委托"),
    DELEGATION_MODE_2(2, "邮件提调");


    public Integer type;
    public String desc;

    SurveyRiskCaseDelegationModeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static SurveyRiskCaseDelegationModeEnum getDelegationModeEnum(Integer type) {
        for (SurveyRiskCaseDelegationModeEnum delegationModeEnum : values()) {
            if (delegationModeEnum.getType().equals(type)) {
                return delegationModeEnum;
            }
        }
        return null;
    }
}
