package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseClosedObjReport;

import java.util.List;
import java.util.Map;

public interface CaseClosedObjReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseClosedObjReport record);

    int insertSelective(CaseClosedObjReport record);

    CaseClosedObjReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseClosedObjReport record);

    int updateByPrimaryKey(CaseClosedObjReport record);

    List<CaseClosedObjReport> queryByCaseId(Long caseId);

    CaseClosedObjReport selectByCaseIdOrProjectName(Map map);
}