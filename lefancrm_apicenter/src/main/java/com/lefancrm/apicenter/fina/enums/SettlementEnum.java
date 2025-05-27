package com.lefancrm.apicenter.fina.enums;

/**
 * 结算单状态枚举
 */
public enum SettlementEnum {
    SETTLEMENT_CYJSZ(1,"出院结算中"),
    SETTLEMENT_DSQLP(2,"待申请理赔"),
    SETTLEMENT_DHK(3,"待还款"),
    SETTLEMENT_DBSSH(4,"待保司审核"),
    SETTLEMENT_FWFDKP(5,"服务费待开票"),
    SETTLEMENT_FWFDQRDZ(6,"服务费待确认到账"),
    SETTLEMENT_ZCJA(7,"正常结案",false),
    SETTLEMENT_CSZZJA(8,"催收终止(结案)",false);

    private int state;
    private String stateName;
    private Boolean checked = true;//前端默认是否选中

    SettlementEnum(int state,String stateName){
        this.state = state;
        this.stateName = stateName;
    }

    SettlementEnum(int state,String stateName,Boolean checked){
        this.state = state;
        this.stateName = stateName;
        this.checked = checked;
    }

    public int getState() {
        return state;
    }

    public String getStateName() {
        return stateName;
    }

    public Boolean getChecked() {
        return checked;
    }

    public static String getStateNameByState(int state){
        SettlementEnum[] values = SettlementEnum.values();
        for (SettlementEnum value : values) {
            if (value.getState() == state) {
                return value.stateName;
            }
        }
        return null;
    }
}
