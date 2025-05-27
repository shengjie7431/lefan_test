package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseMediationClaim;

public interface CaseMediationClaimMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseMediationClaim record);

    int insertSelective(CaseMediationClaim record);

    CaseMediationClaim selectByPrimaryKey(Long caseId);

    int updateByPrimaryKeySelective(CaseMediationClaim record);

    int updateByPrimaryKey(CaseMediationClaim record);

    CaseMediationClaim queryByCaseId(Long caseId);

}