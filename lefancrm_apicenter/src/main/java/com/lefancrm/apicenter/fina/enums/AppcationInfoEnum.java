package com.lefancrm.apicenter.fina.enums;

/**
 * 垫付案件状态枚举
 */
public enum AppcationInfoEnum {
    APPLICATION_CLSJZ(1,"材料收集中"),
    APPLICATION_DFCSZ(2,"垫付初审中"),
    APPLICATION_DFDCZ(3,"垫付调查中"),
    APPLICATION_DFFSZ(4,"垫付复审中"),
    APPLICATION_DFBSZSZ(5,"垫付保司审核中"),
    APPLICATION_SHTTDFK(6,"审核通过待放款"),
    APPLICATION_DQRDZ(7,"待确认到账"),
    APPLICATION_YQRDZ(8,"已确认到账"),
    APPLICATION_CYJSZ(9,"出院结算中"),
    APPLICATION_DSQLP(10,"待申请理赔"),
    APPLICATION_DHK(11,"待还款"),
    APPLICATION_DBSZS(12,"待保司终审"),
    APPLICATION_FWFDKP(13,"服务费待开票"),
    APPLICATION_FWFDQRDZ(14,"服务费待确认到账"),
    APPLICATION_ZCJA(15,"正常结案"),
    APPLICATION_JJDFDHF(16,"拒绝垫付待回访"),
    APPLICATION_JJDFJA(17,"拒绝垫付结案"),
    APPLICATION_FQDFJA(18,"放弃垫付结案");


    private int state;
    private String stateName;
    private Boolean checked = true;//前端默认是否选中

    AppcationInfoEnum(int state, String stateName,Boolean checked){
        this.state = state;
        this.stateName = stateName;
        this.checked =checked;
    }

    AppcationInfoEnum(int state, String stateName){
        this.state = state;
        this.stateName = stateName;
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
        AppcationInfoEnum[] values = AppcationInfoEnum.values();
        for (AppcationInfoEnum value : values) {
            if (value.getState() == state) {
                return value.stateName;
            }
        }
        return null;
    }
}
