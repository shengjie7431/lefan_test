package com.lefancrm.apicenter.dto;

import com.lefancrm.apicenter.model.ActivityDayReport;
import com.lefancrm.apicenter.model.OrgInfo;

import java.util.List;

/**
 * Created by DELL on 2017/12/29.
 */
public class WorkBenchDto {

    private List<CaseSumDataDto> caseSumDatas;

    private CaseSumReportDto caseSumReport = null;

    private Integer saleGold;

    private Integer saleAmount;

    private List<ActivityDayReport> activityDayReports;

    private  ActivityDayReport activityDayReport;

    private List<OrgInfo> orgInfos;

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

    public List<ActivityDayReport> getActivityDayReports() {
        return activityDayReports;
    }

    public void setActivityDayReports(List<ActivityDayReport> activityDayReports) {
        this.activityDayReports = activityDayReports;
    }

    public ActivityDayReport getActivityDayReport() {
        return activityDayReport;
    }

    public void setActivityDayReport(ActivityDayReport activityDayReport) {
        this.activityDayReport = activityDayReport;
    }

    public List<OrgInfo> getOrgInfos() {
        return orgInfos;
    }

    public void setOrgInfos(List<OrgInfo> orgInfos) {
        this.orgInfos = orgInfos;
    }
}
