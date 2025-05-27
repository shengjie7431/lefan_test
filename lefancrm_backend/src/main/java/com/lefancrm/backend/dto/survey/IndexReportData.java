package com.lefancrm.backend.dto.survey;

import java.util.Date;

public class IndexReportData {
    //过滤条件
    private int  searchType;
    private Date startTime;
    private Date endTime;
    //基础信息
    private BassInfo bassInfo;
    private class BassInfo{
        private int entrustOrgs;
        private int surveyOrgs;

        public int getEntrustOrgs() {
            return entrustOrgs;
        }

        public void setEntrustOrgs(int entrustOrgs) {
            this.entrustOrgs = entrustOrgs;
        }

        public int getSurveyOrgs() {
            return surveyOrgs;
        }

        public void setSurveyOrgs(int surveyOrgs) {
            this.surveyOrgs = surveyOrgs;
        }
    }

    //关键指标

    //案件走势

    //新增案件来源

    //委托机构排名

    //调查机构排名

    //领域

    //业务类型

    //任务统计

    //跨区域

    //地区分布


    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BassInfo getBassInfo() {
        return bassInfo;
    }

    public void setBassInfo(BassInfo bassInfo) {
        this.bassInfo = bassInfo;
    }
}
