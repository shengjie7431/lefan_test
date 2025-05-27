package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffOrganDepartment;

import java.util.List;
import java.util.Map;

public interface StaffOrganDepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffOrganDepartment record);

    int insertSelective(StaffOrganDepartment record);

    StaffOrganDepartment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffOrganDepartment record);

    int updateByPrimaryKey(StaffOrganDepartment record);

    //数据
    List<StaffOrganDepartment> list(Map map);
    int listSize(Map map);

    int deleteByInfo(Map map);
}