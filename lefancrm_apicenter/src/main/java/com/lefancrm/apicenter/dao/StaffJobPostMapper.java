package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffJobPost;

import java.util.List;
import java.util.Map;

public interface StaffJobPostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffJobPost record);

    int insertSelective(StaffJobPost record);

    StaffJobPost selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffJobPost record);

    int updateByPrimaryKey(StaffJobPost record);

    //数据
    List<StaffJobPost> list(Map map);
    int listSize(Map map);

    StaffJobPost selectByOne(String name);
}