package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.HbUserInfo;

import java.util.List;
import java.util.Map;

public interface HbUserInfoMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(HbUserInfo record);

    int insertSelective(HbUserInfo record);

    HbUserInfo selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(HbUserInfo record);

    int updateByPrimaryKey(HbUserInfo record);

    List<HbUserInfo> selectList(Map map);
    int selectListSize(Map  map);
}