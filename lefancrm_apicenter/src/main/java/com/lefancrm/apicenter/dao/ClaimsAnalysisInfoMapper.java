package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.ClaimsAnalysisInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface ClaimsAnalysisInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClaimsAnalysisInfo record);

    int insertSelective(ClaimsAnalysisInfo record);

    ClaimsAnalysisInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClaimsAnalysisInfo record);

    int updateByPrimaryKey(ClaimsAnalysisInfo record);

    List<ClaimsAnalysisInfo> list(ApiRequest apiRequest);

    int listSize(ApiRequest apiRequest);
}