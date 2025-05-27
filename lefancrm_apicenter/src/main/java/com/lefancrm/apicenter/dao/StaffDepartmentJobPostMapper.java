package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffDepartmentJobPost;

import java.util.List;
import java.util.Map;

public interface StaffDepartmentJobPostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffDepartmentJobPost record);

    int insertSelective(StaffDepartmentJobPost record);

    StaffDepartmentJobPost selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffDepartmentJobPost record);

    int updateByPrimaryKey(StaffDepartmentJobPost record);

    //数据
    List<StaffDepartmentJobPost> list(Map map);
    int listSize(Map map);

    int deleteByInfo(Map map);
}