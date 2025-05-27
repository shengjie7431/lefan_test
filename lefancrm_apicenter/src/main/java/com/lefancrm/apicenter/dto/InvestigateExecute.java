package com.lefancrm.apicenter.dto;

import java.util.List;

public class InvestigateExecute {
    private String approachCode;
    private List<InvestigateExecuteDetail> executeDetailList;

    public String getApproachCode() {
        return approachCode;
    }

    public void setApproachCode(String approachCode) {
        this.approachCode = approachCode;
    }

    public List<InvestigateExecuteDetail> getExecuteDetailList() {
        return executeDetailList;
    }

    public void setExecuteDetailList(List<InvestigateExecuteDetail> executeDetailList) {
        this.executeDetailList = executeDetailList;
    }
}
