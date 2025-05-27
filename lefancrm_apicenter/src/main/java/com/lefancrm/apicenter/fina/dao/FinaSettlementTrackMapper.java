package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSettlementTrack;

import java.util.List;
import java.util.Map;

public interface FinaSettlementTrackMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSettlementTrack record);

    int insertSelective(FinaSettlementTrack record);

    FinaSettlementTrack selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSettlementTrack record);

    int updateByPrimaryKey(FinaSettlementTrack record);

    List<FinaSettlementTrack> list(Map<String,Object> paramMap);
}