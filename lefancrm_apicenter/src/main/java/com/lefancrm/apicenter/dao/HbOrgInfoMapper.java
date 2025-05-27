package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.HbOrgInfo;

import java.util.List;
import java.util.Map;

public interface HbOrgInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HbOrgInfo record);

    int insertSelective(HbOrgInfo record);

    HbOrgInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HbOrgInfo record);

    int updateByPrimaryKey(HbOrgInfo record);

    List<HbOrgInfo> list(Map  map);
    List<HbOrgInfo> selectList(Map  map);
    int selectListSize(Map  map);
}