package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffOrgan;

import java.util.List;
import java.util.Map;

public interface StaffOrganMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffOrgan record);

    int insertSelective(StaffOrgan record);

    StaffOrgan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffOrgan record);

    int updateByPrimaryKey(StaffOrgan record);

    //数据
    List<StaffOrgan> list(Map map);
    int listSize(Map map);

    StaffOrgan selectByOne(String name);

    List<StaffOrgan> listHq();
}