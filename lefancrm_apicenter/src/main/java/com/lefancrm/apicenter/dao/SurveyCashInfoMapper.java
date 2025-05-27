package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyCashInfo;

import java.util.List;
import java.util.Map;

public interface SurveyCashInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyCashInfo record);

    int insertSelective(SurveyCashInfo record);

    SurveyCashInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyCashInfo record);

    int updateByPrimaryKey(SurveyCashInfo record);

    List<SurveyCashInfo> list(Map map);

    int listSize(Map map);

    List<SurveyCashInfo> selectByDate(Map<String,Object> map);
}