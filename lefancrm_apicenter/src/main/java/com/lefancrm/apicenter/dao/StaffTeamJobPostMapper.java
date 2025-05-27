package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffTeamJobPost;

import java.util.List;
import java.util.Map;

public interface StaffTeamJobPostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffTeamJobPost record);

    int insertSelective(StaffTeamJobPost record);

    StaffTeamJobPost selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffTeamJobPost record);

    int updateByPrimaryKey(StaffTeamJobPost record);

    //数据
    List<StaffTeamJobPost> list(Map map);
    int listSize(Map map);

    int deleteByInfo(Map map);
}