package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BusApplyOrgRole;

import java.util.List;
import java.util.Map;

public interface BusApplyOrgRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusApplyOrgRole record);

    int insertSelective(BusApplyOrgRole record);

    BusApplyOrgRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusApplyOrgRole record);

    int updateByPrimaryKey(BusApplyOrgRole record);

    List<BusApplyOrgRole> list(Map map);

    int listSize(Map map);

    List<BusApplyOrgRole> listByUserId(Long userId);
    int deleteByRoleId(Long roleId);
    int deleteByLeftId(Long leftId);
}