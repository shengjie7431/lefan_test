package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffPostAppellation;

import java.util.List;
import java.util.Map;

public interface StaffPostAppellationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffPostAppellation record);

    int insertSelective(StaffPostAppellation record);

    StaffPostAppellation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffPostAppellation record);

    int updateByPrimaryKey(StaffPostAppellation record);

    List<StaffPostAppellation> list(Map map);
    int listSize(Map map);

    List<StaffPostAppellation> listAll(Map map);

    StaffPostAppellation selectByOne(String appellationName);

}