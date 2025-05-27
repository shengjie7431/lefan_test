package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffPersonnelInfoLog;

import java.util.List;
import java.util.Map;

public interface StaffPersonnelInfoLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffPersonnelInfoLog record);

    int insertSelective(StaffPersonnelInfoLog record);

    StaffPersonnelInfoLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffPersonnelInfoLog record);

    int updateByPrimaryKey(StaffPersonnelInfoLog record);

    //数据
    List<StaffPersonnelInfoLog> list(Map map);
    int listSize(Map map);
}