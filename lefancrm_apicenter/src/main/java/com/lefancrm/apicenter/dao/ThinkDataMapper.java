package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.ThinkData;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface ThinkDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ThinkData record);

    int insertSelective(ThinkData record);

    ThinkData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThinkData record);

    int updateByPrimaryKey(ThinkData record);

    List<ThinkData> list(ApiRequest apiRequest);
    int listSize(ApiRequest apiRequest);
}