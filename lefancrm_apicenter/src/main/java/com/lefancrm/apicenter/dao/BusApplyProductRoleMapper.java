package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BusApplyProductRole;

import java.util.List;
import java.util.Map;

public interface BusApplyProductRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusApplyProductRole record);

    int insertSelective(BusApplyProductRole record);

    BusApplyProductRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusApplyProductRole record);

    int updateByPrimaryKey(BusApplyProductRole record);

    List<BusApplyProductRole> list(Map map);
    int listSize(Map map);

    List<BusApplyProductRole> listByUserId(Long userId);

    int deleteByRoleId(Long roleId);
    int deleteByLeftId(Long leftId);

}