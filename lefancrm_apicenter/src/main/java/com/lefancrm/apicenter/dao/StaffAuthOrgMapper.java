package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffAuthOrg;

import java.util.List;

public interface StaffAuthOrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffAuthOrg record);

    int insertSelective(StaffAuthOrg record);

    StaffAuthOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffAuthOrg record);

    int updateByPrimaryKey(StaffAuthOrg record);

    List<StaffAuthOrg> selectByUserId(Long userId);
}