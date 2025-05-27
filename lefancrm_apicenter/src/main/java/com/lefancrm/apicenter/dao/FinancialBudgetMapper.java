package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.FinancialBudget;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface FinancialBudgetMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinancialBudget record);

    int insertSelective(FinancialBudget record);

    FinancialBudget selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinancialBudget record);

    int updateByPrimaryKey(FinancialBudget record);

    Integer budgetListSize(ApiRequest apiReq);

    List<FinancialBudget> budgetList(ApiRequest apiReq);

    Map<String,Double> selectEveryUseMoney(Map paraMap);

    Map<String,Double> selectEveryCostTypeUseMoney(Map paraMap);
}