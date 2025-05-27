package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.FinancialReProgres;

import java.util.List;
import java.util.Map;

public interface FinancialReProgresMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinancialReProgres record);

    int insertSelective(FinancialReProgres record);

    FinancialReProgres selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinancialReProgres record);

    int updateByPrimaryKey(FinancialReProgres record);

    List<FinancialReProgres> list(Map map);
    int listSize(Map map);
}