package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffCompany;

import java.util.List;
import java.util.Map;

public interface StaffCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffCompany record);

    int insertSelective(StaffCompany record);

    StaffCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffCompany record);

    int updateByPrimaryKey(StaffCompany record);

    //数据
    List<StaffCompany> list(Map map);
    int listSize(Map map);

    StaffCompany selectByOne(String name);
}