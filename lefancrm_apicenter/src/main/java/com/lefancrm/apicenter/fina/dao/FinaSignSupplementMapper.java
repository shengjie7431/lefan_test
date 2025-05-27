package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSignSupplement;

public interface FinaSignSupplementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSignSupplement record);

    int insertSelective(FinaSignSupplement record);

    FinaSignSupplement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSignSupplement record);

    int updateByPrimaryKey(FinaSignSupplement record);
}