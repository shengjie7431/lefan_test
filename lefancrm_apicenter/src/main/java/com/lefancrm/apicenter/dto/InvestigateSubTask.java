package com.lefancrm.apicenter.dto;

import java.util.List;

public class InvestigateSubTask {
    private List<InitiateApproach> initiateApproachList;
    private String isInvalid;

    public List<InitiateApproach> getInitiateApproachList() {
        return initiateApproachList;
    }

    public void setInitiateApproachList(List<InitiateApproach> initiateApproachList) {
        this.initiateApproachList = initiateApproachList;
    }

    public String getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(String isInvalid) {
        this.isInvalid = isInvalid;
    }
}
