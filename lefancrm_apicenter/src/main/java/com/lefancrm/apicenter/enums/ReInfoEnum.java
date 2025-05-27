package com.lefancrm.apicenter.enums;

/**
 * 报销状态
 */
public enum ReInfoEnum {
    WAIT_SUBMIT_INVOICE(1,"待提交发票"),
    WAIT_ORG_CHECK(2,"待机构审核"),
    WAIT_FINANCE_CHECK(3,"待财务审核"),
    CHECK_PASS(4,"审核通过"),
    THE_PAYING(5,"付款中"),
//    WAIT_OK_ACCOUNT(6,"待确认到账"),
    RE_SUCCESS(7,"报销完成"),
    FINANCE_CHECK_REJECT(8,"财务审核驳回"),
    ORG_CHECK_REJECT(9,"机构审核驳回"),
    NO_RE_MONEY(7,"无费用报销");
    private final int state;
    private final String stateName;

    ReInfoEnum(int state,String stateName){
        this.state = state;
        this.stateName = stateName;
    }

    public int getState() {
        return state;
    }

    public String getStateName() {
        return stateName;
    }
}
