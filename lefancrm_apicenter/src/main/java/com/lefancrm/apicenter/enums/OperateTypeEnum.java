package com.lefancrm.apicenter.enums;

public enum OperateTypeEnum {

    SIGN("01", "签约");

    private String code;

    private String desc;

    private OperateTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
