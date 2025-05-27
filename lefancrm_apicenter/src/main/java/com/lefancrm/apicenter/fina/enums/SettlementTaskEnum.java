package com.lefancrm.apicenter.fina.enums;

/**
 * 结算单任务类型枚举
 */
public enum SettlementTaskEnum {
    CYJS(1,"出院结算",2L),
    XZGZ(2,"现场跟踪",4L),
    XCCS(3,"现场催收",5L);

    SettlementTaskEnum(int state, String stateName, Long curToDataId) {
        this.state = state;
        this.stateName = stateName;
        this.curToDataId = curToDataId;
    }

    private int state;
    private String stateName;
    private Long curToDataId;//当前枚举映射的数据库任务类型ID

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Long getCurToDataId() {
        return curToDataId;
    }

    public void setCurToDataId(Long curToDataId) {
        this.curToDataId = curToDataId;
    }

    public static String getStateNameByState(int state){
        SettlementTaskEnum[] values = SettlementTaskEnum.values();
        for (SettlementTaskEnum value : values) {
            if (value.getState() == state) {
                return value.stateName;
            }
        }
        return null;
    }

    public static Long getDataIdByState(int state){
        SettlementTaskEnum[] values = SettlementTaskEnum.values();
        for (SettlementTaskEnum value : values) {
            if (value.getState() == state) {
                return value.getCurToDataId();
            }
        }
        return null;
    }
}
