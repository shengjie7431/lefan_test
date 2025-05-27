package com.lefancrm.backend.dto;

import java.util.List;

public class CasePersonalDayReportListDto {

    private List<CasePersonalDayReportDto> newList;

    private List<CasePersonalDayReportDto> orgList;

    private List<CasePersonalDayReportDto> allList;

    public List<CasePersonalDayReportDto> getNewList() {
        return newList;
    }

    public void setNewList(List<CasePersonalDayReportDto> newList) {
        this.newList = newList;
    }

    public List<CasePersonalDayReportDto> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<CasePersonalDayReportDto> orgList) {
        this.orgList = orgList;
    }

    public List<CasePersonalDayReportDto> getAllList() {
        return allList;
    }

    public void setAllList(List<CasePersonalDayReportDto> allList) {
        this.allList = allList;
    }
}