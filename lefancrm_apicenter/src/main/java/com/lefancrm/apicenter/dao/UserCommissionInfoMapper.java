package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.UserCommissionInfo;

import java.util.Map;

import java.util.List;
import java.util.Map;

public interface UserCommissionInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCommissionInfo record);

    int insertSelective(UserCommissionInfo record);

    UserCommissionInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCommissionInfo record);

    int updateByPrimaryKey(UserCommissionInfo record);


    UserCommissionInfo queryByUserIdAndTime(Map<String, Object> paramMap);

    //获取数据总值，包含条件查询后的结果
    int selectCountUserCommissionInfo(Map<String, Object> paramMap);

    //列表list查询，包含条件查询
    List<UserCommissionInfo> selectUserCommissionInfoList(Map<String, Object> paramMap);
}