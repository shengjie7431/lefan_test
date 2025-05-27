package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseFollowInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CaseFollowInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseFollowInfo record);

    int insertSelective(CaseFollowInfo record);

    CaseFollowInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseFollowInfo record);

    int updateByPrimaryKey(CaseFollowInfo record);

    List<CaseFollowInfo> selectCaseFollowInfo(HashMap<String, Object> map);

    List<CaseFollowInfo> queryCaseFollowByCaseId(Map<String, Object> map);

    List<CaseFollowInfo> queryCaseFollowByCaseNo(Map<String, Object> map);
}
