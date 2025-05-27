package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BusApplyEnumRole;

import java.util.List;
import java.util.Map;

public interface BusApplyEnumRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusApplyEnumRole record);

    int insertSelective(BusApplyEnumRole record);

    BusApplyEnumRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusApplyEnumRole record);

    int updateByPrimaryKey(BusApplyEnumRole record);

    List<BusApplyEnumRole> list(Map map);
    int listSize(Map map);

    List<BusApplyEnumRole> listByUserId(Long userId);
    int deleteByRoleId(Long roleId);
    int deleteByLeftId(Long leftId);
}