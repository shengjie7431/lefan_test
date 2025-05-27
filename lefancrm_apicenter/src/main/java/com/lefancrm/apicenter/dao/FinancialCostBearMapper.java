package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.FinancialCostBear;

import java.util.List;
import java.util.Map;

public interface FinancialCostBearMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinancialCostBear record);

    int insertSelective(FinancialCostBear record);

    FinancialCostBear selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinancialCostBear record);

    int updateByPrimaryKey(FinancialCostBear record);

    List<FinancialCostBear> list(Map map);
    int listSize(Map map);

    FinancialCostBear selectOne(Map map);

    int updateState(Long financialReApplyId);
}