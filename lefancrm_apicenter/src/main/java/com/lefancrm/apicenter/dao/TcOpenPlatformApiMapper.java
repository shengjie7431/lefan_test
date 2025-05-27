package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.TcOpenPlatformApi;

import java.util.List;

public interface TcOpenPlatformApiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TcOpenPlatformApi record);

    int insertSelective(TcOpenPlatformApi record);

    TcOpenPlatformApi selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TcOpenPlatformApi record);

    int updateByPrimaryKey(TcOpenPlatformApi record);

    List<TcOpenPlatformApi> selectListByPlatformId (Long id);
}