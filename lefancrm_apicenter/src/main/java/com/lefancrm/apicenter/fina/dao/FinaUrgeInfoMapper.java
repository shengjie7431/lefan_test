package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaUrgeInfo;

import java.util.List;
import java.util.Map;

public interface FinaUrgeInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaUrgeInfo record);

    int insertSelective(FinaUrgeInfo record);

    FinaUrgeInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaUrgeInfo record);

    int updateByPrimaryKey(FinaUrgeInfo record);

    List<FinaUrgeInfo> list(Map<String,Object> paramMap);
}