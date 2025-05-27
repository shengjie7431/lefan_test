package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffDepartmentTeam;

import java.util.List;
import java.util.Map;

public interface StaffDepartmentTeamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffDepartmentTeam record);

    int insertSelective(StaffDepartmentTeam record);

    StaffDepartmentTeam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffDepartmentTeam record);

    int updateByPrimaryKey(StaffDepartmentTeam record);

    //数据
    List<StaffDepartmentTeam> list(Map map);
    int listSize(Map map);

    int deleteByInfo(Map map);
}