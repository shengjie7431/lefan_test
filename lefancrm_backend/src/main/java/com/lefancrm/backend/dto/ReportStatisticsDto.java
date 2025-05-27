package com.lefancrm.backend.dto;

/**
 * Created by DELL on 2017/6/28.
 */
public class ReportStatisticsDto {

    private long id;
    private String orgName;
    private int caseGoodCount;
    private int promoteCount;
    private int paymentCount;
    private int agentCount;
    private int invaliCount;
    private int loanCount;
    private int loanTypeCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getCaseGoodCount() {
        return caseGoodCount;
    }

    public void setCaseGoodCount(int caseGoodCount) {
        this.caseGoodCount = caseGoodCount;
    }


    public int getPromoteCount() {
        return promoteCount;
    }

    public void setPromoteCount(int promoteCount) {
        this.promoteCount = promoteCount;
    }

    public int getPaymentCount() {
        return paymentCount;
    }

    public void setPaymentCount(int paymentCount) {
        this.paymentCount = paymentCount;
    }

    public int getAgentCount() {
        return agentCount;
    }

    public void setAgentCount(int agentCount) {
        this.agentCount = agentCount;
    }

    public int getInvaliCount() {
        return invaliCount;
    }

    public void setInvaliCount(int invaliCount) {
        this.invaliCount = invaliCount;
    }

    public int getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(int loanCount) {
        this.loanCount = loanCount;
    }

    public int getLoanTypeCount() {
        return loanTypeCount;
    }

    public void setLoanTypeCount(int loanTypeCount) {
        this.loanTypeCount = loanTypeCount;
    }
}
