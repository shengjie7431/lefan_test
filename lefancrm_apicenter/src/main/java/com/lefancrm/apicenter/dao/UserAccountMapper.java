package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.UserAccount;

import java.util.Map;

public interface UserAccountMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKey(UserAccount record);

    int addMoneyUserAccountByUserId(Map<String, Object> paramMap);

    int lessMoneyUserAccountByUserId(Map<String, Object> paramMap);
}