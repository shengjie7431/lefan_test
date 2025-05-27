package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.OperatorFollowInfo;

import java.util.List;
import java.util.Map;

public interface OperatorFollowInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OperatorFollowInfo record);

    int insertSelective(OperatorFollowInfo record);

    OperatorFollowInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OperatorFollowInfo record);

    int updateByPrimaryKey(OperatorFollowInfo record);

    List<OperatorFollowInfo> selectOperatorFollowInfo(Map<String, Object> map);

    List<OperatorFollowInfo> selectOperatorFollowInfoByTypeAndCaseId(Map<String, Object> map);
}