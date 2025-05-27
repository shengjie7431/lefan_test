package com.lefancrm.apicenter.dto;

import java.util.Date;

/**
 * Created by lixianfeng on 2019/5/20.
 */
public class CaseStepsDTO {
    private String stepName;
    private Date showTime;
    private Boolean isBule;
    public CaseStepsDTO(){

    }

    public CaseStepsDTO(String stepName, Date showTime, Boolean isBule) {
        this.stepName = stepName;
        this.showTime = showTime;
        this.isBule = isBule;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    public Boolean getIsBule() {
        return isBule;
    }

    public void setIsBule(Boolean isBule) {
        this.isBule = isBule;
    }
}
