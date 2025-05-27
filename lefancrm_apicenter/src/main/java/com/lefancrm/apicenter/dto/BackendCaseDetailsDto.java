package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.model.LoanApplication;

import java.util.List;

/**
 * Created by Admin on 2017-04-27.
 */
public class BackendCaseDetailsDto<T> {

    private LoanApplication loanApplication;   //贷款申请详情
    private AgentApply agentApply;     //代理申请详情
    private List<T> details;                   //案件跟踪详情

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    public AgentApply getAgentApply() {
        return agentApply;
    }

    public void setAgentApply(AgentApply agentApply) {
        this.agentApply = agentApply;
    }

    public List<T> getDetails() {
        return details;
    }

    public void setDetails(List<T> details) {
        this.details = details;
    }
}
