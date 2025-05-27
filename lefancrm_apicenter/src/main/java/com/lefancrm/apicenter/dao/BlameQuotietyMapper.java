package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BlameQuotiety;

import java.util.List;

public interface BlameQuotietyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlameQuotiety record);

    int insertSelective(BlameQuotiety record);

    BlameQuotiety selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlameQuotiety record);

    int updateByPrimaryKey(BlameQuotiety record);

    List<BlameQuotiety> selectBlameQuotiety();
}