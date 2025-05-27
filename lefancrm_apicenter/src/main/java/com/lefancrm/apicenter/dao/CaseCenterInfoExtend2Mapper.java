package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseCenterInfoExtend2;

public interface CaseCenterInfoExtend2Mapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseCenterInfoExtend2 record);

    int insertSelective(CaseCenterInfoExtend2 record);

    CaseCenterInfoExtend2 selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseCenterInfoExtend2 record);

    int updateByPrimaryKey(CaseCenterInfoExtend2 record);
}