package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseMediationClaimReport;

import java.util.List;
import java.util.Map;

public interface CaseMediationClaimReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseMediationClaimReport record);

    int insertSelective(CaseMediationClaimReport record);

    CaseMediationClaimReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseMediationClaimReport caseMediationClaimReport);

    int updateByPrimaryKey(CaseMediationClaimReport record);

    List<CaseMediationClaimReport> selectByCaseId(Long caseId);

    CaseMediationClaimReport selectByCaseIdOrProjectName(Map map);
}