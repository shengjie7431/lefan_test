package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSettlementOrg;

import java.util.List;
import java.util.Map;

public interface FinaSettlementOrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSettlementOrg record);

    int insertSelective(FinaSettlementOrg record);

    FinaSettlementOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSettlementOrg record);

    int updateByPrimaryKey(FinaSettlementOrg record);

    List<FinaSettlementOrg> list(Map<String,Object> paramMap);
}