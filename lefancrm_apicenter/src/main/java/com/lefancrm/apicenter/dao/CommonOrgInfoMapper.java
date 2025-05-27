package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.CommonOrgInfo;

import java.util.List;
import java.util.Map;

public interface CommonOrgInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommonOrgInfo record);

    int insertSelective(CommonOrgInfo record);

    CommonOrgInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommonOrgInfo record);

    int updateByPrimaryKey(CommonOrgInfo record);

    List<CommonOrgInfo> queryCOrgListByParam(Map<String, Object> paramMap);

    int queryCOrgListByParamCount(Map<String, Object> paramMap);

}