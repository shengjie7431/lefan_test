package com.lefancrm.apicenter.dto.finacial;

/**
 * 每刻报销状态枚举
 */
public enum FinancialReApplyStateEnumDto {
    APPLY_STATE_DTJ(1,"待提交"),
    APPLY_STATE_DSHJGJL(2,"待机构经理审核"),
    APPLY_STATE_DSHFGZ(3,"待分管总审核"),
    APPLY_STATE_DSHCWZY(4,"待财务专员审核"),
    APPLY_STATE_DSHCWJL(5,"待财务经理审核"),
    APPLY_STATE_DSHZJL(6,"待总经理审核"),
    APPLY_STATE_DSHDSZ(66,"待董事长审核"),
    APPLY_STATE_DFK(7,"待付款"),
    APPLY_STATE_YFK(8,"已付款",false),
    APPLY_STATE_THJGJL(9,"机构经理审核退回"),
    APPLY_STATE_THFGZ(10,"分管总审核退回"),
    APPLY_STATE_THCWZY(11,"财务专员审核退回"),
    APPLY_STATE_THCWJL(12,"财务经理审核退回"),
    APPLY_STATE_THZJL(13,"总经理审核退回"),
    APPLY_STATE_DJKHX(14,"待借款核销"),
    APPLY_STATE_BFJKHX(15,"部分借款核销"),
    APPLY_STATE_YJKHX(16,"已借款核销",false),
    APPLY_STATE_YCX(17,"已撤销",false),
    APPLY_STATE_DSHCWZJ(55,"待财务总监审核");



    private int state;
    private String stateName;
    private Boolean checked = true;//前端默认是否选中

    FinancialReApplyStateEnumDto(int state, String stateName, Boolean checked){
        this.state = state;
        this.stateName = stateName;
        this.checked =checked;
    }

    FinancialReApplyStateEnumDto(int state, String stateName){
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
        FinancialReApplyStateEnumDto[] values = FinancialReApplyStateEnumDto.values();
        for (FinancialReApplyStateEnumDto value : values) {
            if (value.getState() == state) {
                return value.stateName;
            }
        }
        return null;
    }

    public static  FinancialReApplyStateEnumDto get(int state){
        FinancialReApplyStateEnumDto[] values = FinancialReApplyStateEnumDto.values();
        for (FinancialReApplyStateEnumDto value : values) {
            if (value.getState() == state) {
                return value;
            }
        }
        return null;
    }
}
