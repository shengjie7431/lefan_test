package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.ClaimsApplyInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface ClaimsApplyInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClaimsApplyInfo record);

    int insertSelective(ClaimsApplyInfo record);

    ClaimsApplyInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClaimsApplyInfo record);

    int updateByPrimaryKey(ClaimsApplyInfo record);

    List<ClaimsApplyInfo> list(ApiRequest apiRequest);

    int listSize(ApiRequest apiRequest);
}