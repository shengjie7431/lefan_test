package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffTeam;

import java.util.List;
import java.util.Map;

public interface StaffTeamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffTeam record);

    int insertSelective(StaffTeam record);

    StaffTeam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffTeam record);

    int updateByPrimaryKey(StaffTeam record);


    //数据
    List<StaffTeam> list(Map map);
    int listSize(Map map);

    StaffTeam selectByOne(String name);
}