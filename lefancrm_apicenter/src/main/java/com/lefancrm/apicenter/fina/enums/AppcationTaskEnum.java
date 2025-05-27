package com.lefancrm.apicenter.fina.enums;

/**
 * 垫付案件任务类型枚举
 */
public enum AppcationTaskEnum {
    DFCLSJ(1,"垫付材料收集",1L),
    XZGZ(2,"现场跟踪",4L),
    QRDZ(3,"确认到账",3L);

    AppcationTaskEnum(int state, String stateName, Long curToDataId) {
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
        AppcationTaskEnum[] values = AppcationTaskEnum.values();
        for (AppcationTaskEnum value : values) {
            if (value.getState() == state) {
                return value.stateName;
            }
        }
        return null;
    }

    public static Long getDataIdByState(int state){
        AppcationTaskEnum[] values = AppcationTaskEnum.values();
        for (AppcationTaskEnum value : values) {
            if (value.getState() == state) {
                return value.getCurToDataId();
            }
        }
        return null;
    }
}
