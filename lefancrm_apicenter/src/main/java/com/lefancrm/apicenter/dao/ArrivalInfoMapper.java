package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.ArrivalInfoDto;
import com.lefancrm.apicenter.model.ArrivalInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface ArrivalInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArrivalInfo record);

    int insertSelective(ArrivalInfo record);

    ArrivalInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArrivalInfo record);

    int updateByPrimaryKey(ArrivalInfo record);

    List<ArrivalInfoDto> selectList(ApiRequest request);

    int selectListSize(ApiRequest request);

    List<ArrivalInfo> selectByInfo(Map<String, Object> map);
}