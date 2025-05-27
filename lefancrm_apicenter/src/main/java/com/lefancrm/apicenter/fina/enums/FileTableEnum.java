package com.lefancrm.apicenter.fina.enums;

public enum  FileTableEnum {
    FINA_SETTLEMENT_TRACK_ATTR("FINA_SETTLEMENT_TRACK_ATTR","结算单跟踪附件"),
    FINA_REPAYMENT_INFO_ATTR("FINA_REPAYMENT_INFO_ATTR","还款凭证"),

    FINA_URGE_INFO_ATTR_TEL("FINA_URGE_INFO_ATTR_TEL","电话催收凭证"),
    FINA_URGE_INFO_ATTR_USER("FINA_URGE_INFO_ATTR_USER","上门催收凭证"),
    FINA_URGE_INFO_ATTR_LS_ONE("FINA_URGE_INFO_ATTR_LS_ONE","律师函材料"),
    FINA_URGE_INFO_ATTR_LS_TWO("FINA_URGE_INFO_ATTR_LS_TWO","律师函凭证"),
    FINA_URGE_INFO_ATTR_SS_ONE("FINA_URGE_INFO_ATTR_SS_ONE","诉讼材料"),
    FINA_URGE_INFO_ATTR_SS_TWO("FINA_URGE_INFO_ATTR_SS_TWO","诉讼凭证"),

    FINA_APPLICANT_TRACK_ATTR("FINA_APPLICANT_TRACK_ATTR","垫付案件跟踪附件"),
    FINA_CONFIRM_ACCOUNT_ATTR("FINA_CONFIRM_ACCOUNT_ATTR","垫付案件确认到账"),
    FINA_AGREEMENT_INFO_ATTR("FINA_AGREEMENT_INFO_ATTR","垫付案件理赔申请书");


    FileTableEnum(String code,String name){
        this.code = code;
        this.name = name;
    }
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static FileTableEnum getFileTableEnumByCode(String code){
        FileTableEnum[] values = FileTableEnum.values();
        for (FileTableEnum value : values) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
