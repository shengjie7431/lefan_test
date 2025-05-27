package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseMediationClaimLegal;

public interface CaseMediationClaimLegalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseMediationClaimLegal record);

    int insertSelective(CaseMediationClaimLegal record);

    CaseMediationClaimLegal selectByPrimaryKey(Long caseId);

    int updateByPrimaryKeySelective(CaseMediationClaimLegal record);

    int updateByPrimaryKey(CaseMediationClaimLegal record);

    CaseMediationClaimLegal queryByCaseId(Long caseId);

}