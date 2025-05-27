package com.lefancrm.apicenter.fina.enums;

public enum  SettlementFilesEnum {
    SETTLEMENT_ZYFP(1L,"住院发票"),
    SETTLEMENT_FYQD(2L,"费用清单"),
    SETTLEMENT_CYXJ(3L,"出院小结"),
    SETTLEMENT_QTCL(4L,"其他病例材料");

    private Long enumId;
    private String enumName;

    public Long getEnumId() {
        return enumId;
    }

    public void setEnumId(Long enumId) {
        this.enumId = enumId;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    SettlementFilesEnum(Long enumId, String enumName) {
        this.enumId = enumId;
        this.enumName = enumName;
    }
}
