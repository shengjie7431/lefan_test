package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.FinancialCostOrgDetails;

public interface FinancialCostOrgDetailsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinancialCostOrgDetails record);

    int insertSelective(FinancialCostOrgDetails record);

    FinancialCostOrgDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinancialCostOrgDetails record);

    int updateByPrimaryKey(FinancialCostOrgDetails record);

    int deleteByReApplyId(Long reApplyId);
}