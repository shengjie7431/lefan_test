package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseAssessmentObjReport;

import java.util.List;
import java.util.Map;

public interface CaseAssessmentObjReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseAssessmentObjReport record);

    int insertSelective(CaseAssessmentObjReport record);

    CaseAssessmentObjReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseAssessmentObjReport record);

    int updateByPrimaryKey(CaseAssessmentObjReport record);

    List<CaseAssessmentObjReport> selectByCaseId(Long caseId);

    CaseAssessmentObjReport selectByCaseIdOrProjectName(Map map);
}