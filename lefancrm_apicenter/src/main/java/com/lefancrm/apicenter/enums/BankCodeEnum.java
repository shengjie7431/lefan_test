package com.lefancrm.apicenter.enums;

import java.util.HashMap;
import java.util.Map;

public enum BankCodeEnum {

    ABC("ABC", "农业银行"),
    BCCB("BCCB", "北京银行"),
    BOC("BOC", "中国银行"),
    BOCOM("BOCOM", "交通银行"),
    CCB("CCB", "建设银行"),
    CEB("CEB", "光大银行"),
    CIB("CIB", "兴业银行"),
    CMB("CMB", "招商银行"),
    CMBC("CMBC", "民生银行"),
    CNCB("CNCB", "中信银行"),
    HBB("HBB", "湖北银行"),
    HSB("HSB", "恒生银行"),
    HXB("HXB", "华夏银行"),
    ICBC("ICBC", "工商银行"),
    PAB("PAB", "平安银行"),
    PSBC("PSBC", "中国邮政储蓄");

    private String code;

    private String desc;

    private BankCodeEnum(String code, String desc) {
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

    public static Map<String, String> convertAll2Map() {
        Map<String, String> bankCodeMap = new HashMap<String, String>();

        for (BankCodeEnum bankCodeEnum : values()) {
            bankCodeMap.put(bankCodeEnum.getCode(), bankCodeEnum.getDesc());

        }

        return bankCodeMap;
    }

}
