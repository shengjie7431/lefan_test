package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffDepartment;

import java.util.List;
import java.util.Map;

public interface StaffDepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffDepartment record);

    int insertSelective(StaffDepartment record);

    StaffDepartment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffDepartment record);

    int updateByPrimaryKey(StaffDepartment record);

    //数据
    List<StaffDepartment> list(Map map);
    int listSize(Map map);

    StaffDepartment selectByOne(String name);
}