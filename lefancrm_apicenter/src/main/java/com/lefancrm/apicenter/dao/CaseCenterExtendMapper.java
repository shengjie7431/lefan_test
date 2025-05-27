package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseCenterExtend;

public interface CaseCenterExtendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseCenterExtend record);

    int insertSelective(CaseCenterExtend record);

    CaseCenterExtend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseCenterExtend record);

    int updateByPrimaryKey(CaseCenterExtend record);
}