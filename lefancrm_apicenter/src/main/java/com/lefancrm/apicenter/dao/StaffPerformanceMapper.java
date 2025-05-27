package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffPerformance;

import java.util.List;
import java.util.Map;

public interface StaffPerformanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffPerformance record);

    int insertSelective(StaffPerformance record);

    StaffPerformance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffPerformance record);

    int updateByPrimaryKey(StaffPerformance record);

    //数据
    List<StaffPerformance> list(Map map);
    int listSize(Map map);

    //通过日期时间查询
    StaffPerformance selectByWorkTime(String code);

    List<StaffPerformance> allList(Map map);
}