package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.HoboProCase;
import com.lefancrm.apicenter.model.HoboProInfo;

import java.util.List;
import java.util.Map;

public interface HoboProInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HoboProInfo record);

    int insertSelective(HoboProInfo record);

    HoboProInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HoboProInfo record);

    int updateByPrimaryKey(HoboProInfo record);

    List<HoboProInfo> list(Map<String,Object> map);

    int listSize(Map map);
}