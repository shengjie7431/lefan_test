package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaProgress;

import java.util.List;
import java.util.Map;

public interface FinaProgressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaProgress record);

    int insertSelective(FinaProgress record);

    FinaProgress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaProgress record);

    int updateByPrimaryKey(FinaProgress record);

    List<FinaProgress> list(Map map);
    int listSize(Map map);
}