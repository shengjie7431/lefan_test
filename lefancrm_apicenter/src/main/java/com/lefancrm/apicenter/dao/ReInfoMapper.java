package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.ReInfoDTO;
import com.lefancrm.apicenter.model.ReInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface ReInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReInfo record);

    int insertSelective(ReInfo record);

    ReInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReInfo record);

    int updateByPrimaryKey(ReInfo record);

    List<ReInfoDTO> selectReinfos(ApiRequest apiRequest);

    int selectReinfosSize(ApiRequest apiRequest);

    int selectByReName(String reName);

}