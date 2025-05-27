package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.FinancialCostDetails;

import java.util.List;
import java.util.Map;

public interface FinancialCostDetailsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinancialCostDetails record);

    int insertSelective(FinancialCostDetails record);

    FinancialCostDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinancialCostDetails record);

    int updateByPrimaryKey(FinancialCostDetails record);

    List<FinancialCostDetails> list(Map map);
    int listSize(Map map);
}