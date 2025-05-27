package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseClaimGuidance;

import java.util.Map;

public interface CaseClaimGuidanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseClaimGuidance record);

    int insertSelective(CaseClaimGuidance record);

    CaseClaimGuidance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseClaimGuidance record);

    int updateByPrimaryKey(CaseClaimGuidance record);

    CaseClaimGuidance selectByCaseCenterId(Long caseCenterId);
}