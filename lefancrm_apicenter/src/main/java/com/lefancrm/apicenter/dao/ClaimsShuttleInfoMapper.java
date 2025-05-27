package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.ClaimsShuttleInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface ClaimsShuttleInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClaimsShuttleInfo record);

    int insertSelective(ClaimsShuttleInfo record);

    ClaimsShuttleInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClaimsShuttleInfo record);

    int updateByPrimaryKey(ClaimsShuttleInfo record);

    List<ClaimsShuttleInfo> list(ApiRequest apiRequest);

    int listSize(ApiRequest apiRequest);
}