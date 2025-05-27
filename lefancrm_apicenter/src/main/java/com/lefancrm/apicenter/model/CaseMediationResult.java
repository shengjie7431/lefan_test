package com.lefancrm.apicenter.model;

import java.util.List;

/**
 * Created by DELL on 2017/5/19.
 */
public class CaseMediationResult {

    private String mainDispute;

    private String mediateDesc;

    private List<CaseMediationReport> caseMediationReports;

    public String getMainDispute() {
        return mainDispute;
    }

    public void setMainDispute(String mainDispute) {
        this.mainDispute = mainDispute;
    }

    public String getMediateDesc() {
        return mediateDesc;
    }

    public void setMediateDesc(String mediateDesc) {
        this.mediateDesc = mediateDesc;
    }

    public List<CaseMediationReport> getCaseMediationReports() {
        return caseMediationReports;
    }

    public void setCaseMediationReports(List<CaseMediationReport> caseMediationReports) {
        this.caseMediationReports = caseMediationReports;
    }
}
