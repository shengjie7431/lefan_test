package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyCashInfoDetail;

import java.util.List;
import java.util.Map;

public interface SurveyCashInfoDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyCashInfoDetail record);

    int insertSelective(SurveyCashInfoDetail record);

    SurveyCashInfoDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyCashInfoDetail record);

    int updateByPrimaryKey(SurveyCashInfoDetail record);

    List<SurveyCashInfoDetail> list(Map map);
    int listSize(Map map);
}