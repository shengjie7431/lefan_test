package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.InvalidismEstimateReport;

public interface InvalidismEstimateReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvalidismEstimateReport record);

    int insertSelective(InvalidismEstimateReport record);

    InvalidismEstimateReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvalidismEstimateReport record);

    int updateByPrimaryKey(InvalidismEstimateReport record);

    InvalidismEstimateReport queryApplyByEstimateId(Long estimateId);
}