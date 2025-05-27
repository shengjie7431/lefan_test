package com.lefancrm.apicenter.enums;

import java.util.HashMap;
import java.util.Map;

public enum IdentityTypeEnum {

    I("I", "身份证"),
    L("L", "临时身份证"),
    A2("A2", "军人身份证"),
    A5("A5","异常身份证"),
    A6("A6","暂住证"),
    A7("A7","居住证"),
    C("C","护照"),
    M("M","军官证"),
    A18("A18","军人证"),
    H("H","户口本"),
    O("O","其他");


    private String code;

    private String desc;

    private IdentityTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;

    }

    public static Map<String, String> convertAll2Map() {
        Map<String, String> identityTypeMap = new HashMap<String, String>();

        for (IdentityTypeEnum identityTypeEnum : values()) {

            identityTypeMap.put(identityTypeEnum.getCode(), identityTypeEnum.getDesc());

        }
        return identityTypeMap;
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
