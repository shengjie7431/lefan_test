package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaApplicant;

public interface FinaApplicantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaApplicant record);

    int insertSelective(FinaApplicant record);

    FinaApplicant selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaApplicant record);

    int updateByPrimaryKey(FinaApplicant record);
}