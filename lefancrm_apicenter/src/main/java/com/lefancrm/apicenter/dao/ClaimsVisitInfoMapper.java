package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.ClaimsVisitInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface ClaimsVisitInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClaimsVisitInfo record);

    int insertSelective(ClaimsVisitInfo record);

    ClaimsVisitInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClaimsVisitInfo record);

    int updateByPrimaryKey(ClaimsVisitInfo record);

    List<ClaimsVisitInfo> list(ApiRequest apiRequest);

    int listSize(ApiRequest apiRequest);
}