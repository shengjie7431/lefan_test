package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.FinancialCostType;

import java.util.List;
import java.util.Map;

public interface FinancialCostTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinancialCostType record);

    int insertSelective(FinancialCostType record);

    FinancialCostType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinancialCostType record);

    int updateByPrimaryKey(FinancialCostType record);

    List<FinancialCostType> list(Map map);
    int listSize(Map map);
}