package com.lefancrm.backend.dto;

import java.util.List;

/**
 * Created by DELL on 2017/12/29.
 */
public class WorkBenchDto {

    private List<CaseSumDataDto> caseSumDatas;

    private CaseSumReportDto caseSumReport = null;

    private Integer saleGold;

    private Integer saleAmount;

    private List<ActivityDayReportDto> activityDayReports;

    private  ActivityDayReportDto activityDayReport;

    private List<OrgInfoDto> orgInfos;

    public List<CaseSumDataDto> getCaseSumDatas() {
        return caseSumDatas;
    }

    public void setCaseSumDatas(List<CaseSumDataDto> caseSumDatas) {
        this.caseSumDatas = caseSumDatas;
    }

    public CaseSumReportDto getCaseSumReport() {
        return caseSumReport;
    }

    public void setCaseSumReport(CaseSumReportDto caseSumReport) {
        this.caseSumReport = caseSumReport;
    }

    public Integer getSaleGold() {
        return saleGold;
    }

    public void setSaleGold(Integer saleGold) {
        this.saleGold = saleGold;
    }

    public Integer getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Integer saleAmount) {
        this.saleAmount = saleAmount;
    }

    public List<ActivityDayReportDto> getActivityDayReports() {
        return activityDayReports;
    }

    public void setActivityDayReports(List<ActivityDayReportDto> activityDayReports) {
        this.activityDayReports = activityDayReports;
    }

    public ActivityDayReportDto getActivityDayReport() {
        return activityDayReport;
    }

    public void setActivityDayReport(ActivityDayReportDto activityDayReport) {
        this.activityDayReport = activityDayReport;
    }

    public List<OrgInfoDto> getOrgInfos() {
        return orgInfos;
    }

    public void setOrgInfos(List<OrgInfoDto> orgInfos) {
        this.orgInfos = orgInfos;
    }
}
