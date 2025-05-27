package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaFileSettlement;

import java.util.List;
import java.util.Map;

public interface FinaFileSettlementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaFileSettlement record);

    int insertSelective(FinaFileSettlement record);

    FinaFileSettlement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaFileSettlement record);

    int updateByPrimaryKey(FinaFileSettlement record);

    List<FinaFileSettlement> list(Map<String,Object> paramMap);
}