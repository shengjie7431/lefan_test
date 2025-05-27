package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaConfirmAccount;

import java.util.Map;

public interface FinaConfirmAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaConfirmAccount record);

    int insertSelective(FinaConfirmAccount record);

    FinaConfirmAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaConfirmAccount record);

    int updateByPrimaryKey(FinaConfirmAccount record);

    FinaConfirmAccount selectOne(Map map);
}