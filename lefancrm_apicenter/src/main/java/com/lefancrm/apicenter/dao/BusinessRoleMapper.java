package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.BusinessRoleDto;
import com.lefancrm.apicenter.model.BusinessRole;

import java.util.List;
import java.util.Map;

public interface BusinessRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessRole record);

    int insertSelective(BusinessRole record);

    BusinessRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessRole record);

    int updateByPrimaryKey(BusinessRole record);

    List<BusinessRole> selectAll(Map<String, Object> paramMap);
    int selectAllCount(Map<String, Object> paramMap);

    String selectUserRoles(Long userId);

    String selectInvestigatorRoles(Map map);

    List<BusinessRole> selectInvestigatorRoleList(Map<String, Object> paramMap);

    //修改功能--删除狄大人角色
    int deleteByUserIdAndInvest(Map map);

    List<BusinessRoleDto> selectDtoByLeftId(Map map);
}