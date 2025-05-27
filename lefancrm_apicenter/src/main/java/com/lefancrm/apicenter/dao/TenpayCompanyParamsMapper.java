package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.TenpayCompanyParams;

public interface TenpayCompanyParamsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TenpayCompanyParams record);

    int insertSelective(TenpayCompanyParams record);

    TenpayCompanyParams selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TenpayCompanyParams record);

    int updateByPrimaryKey(TenpayCompanyParams record);
}