package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseAssessmentReport;

public interface CaseAssessmentReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseAssessmentReport record);

    int insertSelective(CaseAssessmentReport record);

    CaseAssessmentReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseAssessmentReport record);

    int updateByPrimaryKey(CaseAssessmentReport record);

    CaseAssessmentReport selectByCaseId(Long caseId);
}