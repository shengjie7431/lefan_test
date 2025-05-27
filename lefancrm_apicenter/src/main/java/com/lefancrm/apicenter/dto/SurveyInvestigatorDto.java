package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.SurveyInvestigator;

/**
 *
 * @author EDZ
 */
public class SurveyInvestigatorDto extends SurveyInvestigator {

    /**
     * 调查中案件数量
     */
    private Integer opinionPoll;

    /**
     * 超期案件数量
     */
    private Integer overdue;

    public Integer getOpinionPoll() {
        return opinionPoll;
    }

    public void setOpinionPoll(Integer opinionPoll) {
        this.opinionPoll = opinionPoll;
    }

    public Integer getOverdue() {
        return overdue;
    }

    public void setOverdue(Integer overdue) {
        this.overdue = overdue;
    }
}