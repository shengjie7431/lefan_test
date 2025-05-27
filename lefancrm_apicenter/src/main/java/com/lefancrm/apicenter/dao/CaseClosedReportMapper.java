package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseClosedReport;

public interface CaseClosedReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseClosedReport record);

    int insertSelective(CaseClosedReport record);

    CaseClosedReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseClosedReport record);

    int updateByPrimaryKey(CaseClosedReport record);

    CaseClosedReport selectByCaseId(Long caseId);}