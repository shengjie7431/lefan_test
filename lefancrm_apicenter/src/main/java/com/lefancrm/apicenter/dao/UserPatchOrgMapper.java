package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.UserPatchOrg;

import java.util.List;
import java.util.Map;

public interface UserPatchOrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserPatchOrg record);

    int insertSelective(UserPatchOrg record);

    UserPatchOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPatchOrg record);

    int updateByPrimaryKey(UserPatchOrg record);

    List<UserPatchOrg> selectInfoByUserId(Map<String, Object> paramMap);

    UserPatchOrg selectInfoByOrgIdAndUserId(Map<String, Object> paramMap);

    List<UserPatchOrg> selectParentInfoByUserId(Map<String, Object> paramMap);
}