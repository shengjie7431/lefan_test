package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseEntrustInput;

import java.util.List;
import java.util.Map;

public interface CaseEntrustInputMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseEntrustInput record);

    int insertSelective(CaseEntrustInput record);

    CaseEntrustInput selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseEntrustInput record);

    int updateByPrimaryKey(CaseEntrustInput record);

    List<CaseEntrustInput> list(Map map);
    int listSize(Map map);
}