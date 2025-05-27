package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BusApplyCorporationRole;

import java.util.List;
import java.util.Map;

public interface BusApplyCorporationRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusApplyCorporationRole record);

    int insertSelective(BusApplyCorporationRole record);

    BusApplyCorporationRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusApplyCorporationRole record);

    int updateByPrimaryKey(BusApplyCorporationRole record);

    List<BusApplyCorporationRole> list(Map map);
    int listSize(Map map);

    List<BusApplyCorporationRole> listByUserId(Long userId);
    int deleteByRoleId(Long roleId);
    int deleteByLeftId(Long leftId);

}