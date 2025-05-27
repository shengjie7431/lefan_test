package com.lefancrm.apicenter.dto.finacial;

public enum FinancialFileTableEnumDto {
    FINACIAL_RE_APPLE_ATTR("FINACIAL_RE_APPLE_ATTR","每刻报销申请单凭证"),
    FINACIAL_COST_DETAILS_ATTR("FINACIAL_COST_DETAILS_ATTR","费用明细凭证"),
    FINACIAL_RE_LOAN_ATTR("FINACIAL_RE_LOAN_ATTR","借款单凭证"),
    FINACIAL_RE_BACK_ATTR("FINACIAL_RE_BACK_ATTR","还款凭证"),

    THINK_DATA_ORG_ATTR("THINK_DATA_ORG_ATTR","经营分析报表数据录入机构凭证"),
    SURVEY_ENTRUST_PACT("SURVEY_ENTRUST_PACT","委托方合同协议");


    FinancialFileTableEnumDto(String code, String name){
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

    public static FinancialFileTableEnumDto getFileTableEnumByCode(String code){
        FinancialFileTableEnumDto[] values = FinancialFileTableEnumDto.values();
        for (FinancialFileTableEnumDto value : values) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
