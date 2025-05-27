package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyCashInfoRecord;

import java.util.List;
import java.util.Map;

public interface SurveyCashInfoRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyCashInfoRecord record);

    int insertSelective(SurveyCashInfoRecord record);

    SurveyCashInfoRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyCashInfoRecord record);

    int updateByPrimaryKey(SurveyCashInfoRecord record);

    List<SurveyCashInfoRecord> list(Map map);
    int listSize(Map map);
}