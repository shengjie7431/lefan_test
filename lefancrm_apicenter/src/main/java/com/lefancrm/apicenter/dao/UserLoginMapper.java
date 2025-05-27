package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.UserLogin;

import java.util.Map;

public interface UserLoginMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserLogin record);

    int insertSelective(UserLogin record);

    UserLogin selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserLogin record);

    int updateByPrimaryKey(UserLogin record);

	UserLogin selectByPhone(String userTelphone);

	UserLogin selectByWechatId(String wechatId);

	UserLogin selectByWeiboId(String weiboId);

    UserLogin selectByPhoneAndPassword(Map<String, Object> map);
}