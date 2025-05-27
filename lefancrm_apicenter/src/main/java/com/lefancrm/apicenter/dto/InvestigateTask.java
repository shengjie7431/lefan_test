package com.lefancrm.apicenter.dto;

import java.util.List;

public class InvestigateTask {
    private String investigatorAccount;
    private String investigatorName;
    private String isMainInvestigator;
    private String thirdPartyAccount;
    private String taskStatus;
    private String investigatorSubmitTime;//提交时间
    private String assignTaskTime;//分配时间

    private List<InvestigateSubTask> subTaskList;
    private String isInvalid;

    public String getInvestigatorAccount() {
        return investigatorAccount;
    }

    public void setInvestigatorAccount(String investigatorAccount) {
        this.investigatorAccount = investigatorAccount;
    }

    public String getInvestigatorName() {
        return investigatorName;
    }

    public void setInvestigatorName(String investigatorName) {
        this.investigatorName = investigatorName;
    }

    public String getIsMainInvestigator() {
        return isMainInvestigator;
    }

    public void setIsMainInvestigator(String isMainInvestigator) {
        this.isMainInvestigator = isMainInvestigator;
    }

    public String getThirdPartyAccount() {
        return thirdPartyAccount;
    }

    public void setThirdPartyAccount(String thirdPartyAccount) {
        this.thirdPartyAccount = thirdPartyAccount;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getInvestigatorSubmitTime() {
        return investigatorSubmitTime;
    }

    public void setInvestigatorSubmitTime(String investigatorSubmitTime) {
        this.investigatorSubmitTime = investigatorSubmitTime;
    }

    public String getAssignTaskTime() {
        return assignTaskTime;
    }

    public void setAssignTaskTime(String assignTaskTime) {
        this.assignTaskTime = assignTaskTime;
    }

    public List<InvestigateSubTask> getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(List<InvestigateSubTask> subTaskList) {
        this.subTaskList = subTaskList;
    }

    public String getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(String isInvalid) {
        this.isInvalid = isInvalid;
    }
}
