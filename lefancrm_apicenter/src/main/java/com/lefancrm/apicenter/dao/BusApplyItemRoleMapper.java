package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BusApplyItemRole;

import java.util.List;
import java.util.Map;

public interface BusApplyItemRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusApplyItemRole record);

    int insertSelective(BusApplyItemRole record);

    BusApplyItemRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusApplyItemRole record);

    int updateByPrimaryKey(BusApplyItemRole record);

    List<BusApplyItemRole> list(Map map);
    int listSize(Map map);

    List<BusApplyItemRole> listByUserId(Long userId);
    int deleteByRoleId(Long roleId);
    int deleteByLeftId(Long leftId);
}