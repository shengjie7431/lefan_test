package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.FrontRoleMenu;

import java.util.List;
import java.util.Map;

public interface FrontRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FrontRoleMenu record);

    int insertSelective(FrontRoleMenu record);

    FrontRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FrontRoleMenu record);

    int updateByPrimaryKey(FrontRoleMenu record);

    List<FrontRoleMenu> selectList(Map<String, Object> paramMap);
}