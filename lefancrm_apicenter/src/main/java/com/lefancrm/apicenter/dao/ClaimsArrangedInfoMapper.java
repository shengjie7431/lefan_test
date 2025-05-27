package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.ClaimsArrangedInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface ClaimsArrangedInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClaimsArrangedInfo record);

    int insertSelective(ClaimsArrangedInfo record);

    ClaimsArrangedInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClaimsArrangedInfo record);

    int updateByPrimaryKey(ClaimsArrangedInfo record);

    List<ClaimsArrangedInfo> list(ApiRequest apiRequest);

    int listSize(ApiRequest apiRequest);
}