package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSettlementInvestigator;

import java.util.List;
import java.util.Map;

public interface FinaSettlementInvestigatorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSettlementInvestigator record);

    int insertSelective(FinaSettlementInvestigator record);

    FinaSettlementInvestigator selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSettlementInvestigator record);

    int updateByPrimaryKey(FinaSettlementInvestigator record);

    List<FinaSettlementInvestigator> list(Map<String,Object> paramMap);
}