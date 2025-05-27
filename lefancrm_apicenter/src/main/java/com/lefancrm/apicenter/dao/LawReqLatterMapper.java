package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.LawReqLatter;

public interface LawReqLatterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LawReqLatter record);

    int insertSelective(LawReqLatter record);

    LawReqLatter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LawReqLatter record);

    int updateByPrimaryKey(LawReqLatter record);
}