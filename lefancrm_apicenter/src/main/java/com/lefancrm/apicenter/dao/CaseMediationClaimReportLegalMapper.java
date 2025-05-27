package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseMediationClaimReportLegal;

import java.util.List;
import java.util.Map;

public interface CaseMediationClaimReportLegalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseMediationClaimReportLegal record);

    int insertSelective(CaseMediationClaimReportLegal record);

    CaseMediationClaimReportLegal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseMediationClaimReportLegal CaseMediationClaimReportLegal);

    int updateByPrimaryKey(CaseMediationClaimReportLegal record);

    List<CaseMediationClaimReportLegal> selectByCaseId(Long caseId);

    CaseMediationClaimReportLegal selectByCaseIdOrProjectName(Map map);
}