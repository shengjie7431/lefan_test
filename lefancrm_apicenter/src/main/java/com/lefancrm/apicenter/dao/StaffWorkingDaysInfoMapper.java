package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffWorkingDaysInfo;

import java.util.List;
import java.util.Map;

public interface StaffWorkingDaysInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffWorkingDaysInfo record);

    int insertSelective(StaffWorkingDaysInfo record);

    StaffWorkingDaysInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffWorkingDaysInfo record);

    int updateByPrimaryKey(StaffWorkingDaysInfo record);

    //数据
    List<StaffWorkingDaysInfo> list(Map map);
    int listSize(Map map);

    //通过日期时间查询
    StaffWorkingDaysInfo selectByWorkTime(String code);
}