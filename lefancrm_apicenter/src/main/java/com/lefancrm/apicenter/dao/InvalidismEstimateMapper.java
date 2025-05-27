package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.InvalidismEstimate;
import com.lefancrm.apicenter.model.WorkInfo;

import java.util.List;
import java.util.Map;

public interface InvalidismEstimateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvalidismEstimate record);

    int insertSelective(InvalidismEstimate record);

    InvalidismEstimate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvalidismEstimate record);

    int updateByPrimaryKey(InvalidismEstimate record);

    List<InvalidismEstimate> queryInvalidismEstimateList(Long userId);
    List<InvalidismEstimate> selectInvalidismEstimateList(Map<String, Object> paramMap);

    int selectInvalidismEstimateListCount(Map<String, Object> paramMap);

    InvalidismEstimate selectInvalidismByParam(Map<String, Object> paramMap);

    List<WorkInfo> selectInvalidismEstimateByState(Map<String, Object> paramMap);

    int selectInvalWorkCount(Map<String, Object> paramMap);

    InvalidismEstimate selectInvalidismEstimateByInfo(Map<String, Object> paramMap);

}