package com.lefancrm.apicenter.fina.dao;


import com.lefancrm.apicenter.fina.model.FinaSettlementInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FinaSettlementInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSettlementInfo record);

    int insertSelective(FinaSettlementInfo record);

    FinaSettlementInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSettlementInfo record);

    int updateByPrimaryKey(FinaSettlementInfo record);

    List<FinaSettlementInfo> list(Map<String,Object> paramMap);

    int listSize(Map map);

    Double selectServiceMoney(@Param("ids") List<String> ids);

    int updateDataByIds(Map<String,Object> paramMap);
}