package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.UserAccountDetail;

import java.util.List;
import java.util.Map;

public interface UserAccountDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAccountDetail record);

    int insertSelective(UserAccountDetail record);

    UserAccountDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAccountDetail record);

    int updateByPrimaryKey(UserAccountDetail record);

    List<UserAccountDetail> selectUserAccountDetailByParam(Map<String, Object> paramMap);

    Double selectUserAccountMoneyByParam(Map<String, Object> paramMap);

    UserAccountDetail selectUserAccountDetailByBusinessId(Long id);
}