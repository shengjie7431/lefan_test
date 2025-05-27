package com.lefancrm.backend.dto;

import java.util.List;

/**
 * Created by DELL on 2017/5/19.
 */
public class CaseMediationResultDto {

    private String mainDispute;

    private String mediateDesc;

    private List<CaseMediationReportDto> caseMediationReports;

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

    public List<CaseMediationReportDto> getCaseMediationReports() {
        return caseMediationReports;
    }

    public void setCaseMediationReports(List<CaseMediationReportDto> caseMediationReports) {
        this.caseMediationReports = caseMediationReports;
    }
}
