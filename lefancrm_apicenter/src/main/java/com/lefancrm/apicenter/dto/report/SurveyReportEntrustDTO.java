package com.lefancrm.apicenter.dto.report;

import com.lefancrm.apicenter.model.SurveyConsignor;
import com.lefancrm.apicenter.model.SurveyConsignorReport;

public class SurveyReportEntrustDTO {
    private SurveyConsignor surveyConsignor;
    private Double basBillNotAcc;
    private Double basTotalBillMoney;//总开票金额
    private Double basTotalAccMoney;//总到账金额
    private int basEntrustNotApv;
    private int basLefanNotAss;
    private int basOrgSurveying;
    private int basLefanApv;
    private int basEntrustApv;
    private int basNotClose;
    private SurveyConsignorReport surveyConsignorReport;

    public SurveyConsignor getSurveyConsignor() {
        return surveyConsignor;
    }

    public void setSurveyConsignor(SurveyConsignor surveyConsignor) {
        this.surveyConsignor = surveyConsignor;
    }

    public Double getBasBillNotAcc() {
        return basBillNotAcc;
    }

    public void setBasBillNotAcc(Double basBillNotAcc) {
        this.basBillNotAcc = basBillNotAcc;
    }

    public int getBasEntrustNotApv() {
        return basEntrustNotApv;
    }

    public void setBasEntrustNotApv(int basEntrustNotApv) {
        this.basEntrustNotApv = basEntrustNotApv;
    }

    public int getBasLefanNotAss() {
        return basLefanNotAss;
    }

    public void setBasLefanNotAss(int basLefanNotAss) {
        this.basLefanNotAss = basLefanNotAss;
    }

    public int getBasOrgSurveying() {
        return basOrgSurveying;
    }

    public void setBasOrgSurveying(int basOrgSurveying) {
        this.basOrgSurveying = basOrgSurveying;
    }

    public int getBasLefanApv() {
        return basLefanApv;
    }

    public void setBasLefanApv(int basLefanApv) {
        this.basLefanApv = basLefanApv;
    }

    public int getBasEntrustApv() {
        return basEntrustApv;
    }

    public void setBasEntrustApv(int basEntrustApv) {
        this.basEntrustApv = basEntrustApv;
    }

    public int getBasNotClose() {
        return basNotClose;
    }

    public void setBasNotClose(int basNotClose) {
        this.basNotClose = basNotClose;
    }

    public SurveyConsignorReport getSurveyConsignorReport() {
        return surveyConsignorReport;
    }

    public void setSurveyConsignorReport(SurveyConsignorReport surveyConsignorReport) {
        this.surveyConsignorReport = surveyConsignorReport;
    }

    public Double getBasTotalBillMoney() {
        return basTotalBillMoney;
    }

    public void setBasTotalBillMoney(Double basTotalBillMoney) {
        this.basTotalBillMoney = basTotalBillMoney;
    }

    public Double getBasTotalAccMoney() {
        return basTotalAccMoney;
    }

    public void setBasTotalAccMoney(Double basTotalAccMoney) {
        this.basTotalAccMoney = basTotalAccMoney;
    }
}
