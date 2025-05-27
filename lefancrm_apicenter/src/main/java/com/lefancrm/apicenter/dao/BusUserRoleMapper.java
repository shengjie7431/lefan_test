package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.BusUserRole;

import java.util.List;
import java.util.Map;

public interface BusUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusUserRole record);

    int insertSelective(BusUserRole record);

    BusUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusUserRole record);

    int updateByPrimaryKey(BusUserRole record);

    List<BusUserRole> orgUserRoleList(Long userId);

    int deleteByUserId(Long userId);

    BusUserRole selectBusRoleInfo(Map<String, Object> paramMap);

    int deleteByParam(Map<String, Object> paramMap);

    List<BusUserRole> selectBusInfo(Map<String, Object> paramMap);

    Boolean selectUserHaveMenu(Map<String, Object> paramMap);

    List<BusUserRole> selectUserByRoleIds(String roleIds);
}