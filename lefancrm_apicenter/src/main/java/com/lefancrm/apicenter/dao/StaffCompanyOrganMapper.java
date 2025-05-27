package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffCompanyOrgan;

import java.util.List;
import java.util.Map;

public interface StaffCompanyOrganMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffCompanyOrgan record);

    int insertSelective(StaffCompanyOrgan record);

    StaffCompanyOrgan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffCompanyOrgan record);

    int updateByPrimaryKey(StaffCompanyOrgan record);

    //数据
    List<StaffCompanyOrgan> list(Map map);
    int listSize(Map map);

    int deleteByInfo(Map map);
}