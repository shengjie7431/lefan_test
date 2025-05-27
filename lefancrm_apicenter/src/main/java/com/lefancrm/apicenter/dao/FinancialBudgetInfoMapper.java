package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.FinancialBudgetInfo;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FinancialBudgetInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinancialBudgetInfo record);

    int insertSelective(FinancialBudgetInfo record);

    FinancialBudgetInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinancialBudgetInfo record);

    int updateByPrimaryKey(FinancialBudgetInfo record);

    List<FinancialBudgetInfo> selectByBudgetId(ApiRequest apiReq);

    Integer selectByBudgetIdSize(ApiRequest apiReq);

    void deleteByBudgetId(Long budgetId);

    List<FinancialBudgetInfo> selectData();

    List<FinancialBudgetInfo> selectNoInsertDataByBudgetId(Long budgetId);

    List<FinancialBudgetInfo> list(Map<String, Object> paramMap);

    void insertBatchData(@Param("list") List<FinancialBudgetInfo> financialBudgetInfoList, @Param("user") UserInfo userInfo);

    List<FinancialBudgetInfo> selectDataByGroup(ApiRequest apiReq);

    Integer selectDataByGroupCount(ApiRequest apiReq);
}