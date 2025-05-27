package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseRiskControl;

public interface CaseRiskControlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseRiskControl record);

    int insertSelective(CaseRiskControl record);

    CaseRiskControl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseRiskControl record);

    int updateByPrimaryKey(CaseRiskControl record);

    CaseRiskControl selectByCaseId(Long caseId);
}