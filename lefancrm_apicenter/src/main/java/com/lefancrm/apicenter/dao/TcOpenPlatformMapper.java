package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.TcOpenPlatform;

public interface TcOpenPlatformMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TcOpenPlatform record);

    int insertSelective(TcOpenPlatform record);

    TcOpenPlatform selectByPrimaryKey(Integer id);

    TcOpenPlatform selectApiListByParam(String apiKey);

    int updateByPrimaryKeySelective(TcOpenPlatform record);

    int updateByPrimaryKey(TcOpenPlatform record);
}