package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffBusinessUnit;

import java.util.List;
import java.util.Map;

public interface StaffBusinessUnitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffBusinessUnit record);

    int insertSelective(StaffBusinessUnit record);

    StaffBusinessUnit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffBusinessUnit record);

    int updateByPrimaryKey(StaffBusinessUnit record);

    //数据
    List<StaffBusinessUnit> list(Map map);
    int listSize(Map map);

    StaffBusinessUnit selectByOne(String name);
}