package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.FrontMenu;

import java.util.List;
import java.util.Map;

public interface FrontMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FrontMenu record);

    int insertSelective(FrontMenu record);

    FrontMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FrontMenu record);

    int updateByPrimaryKey(FrontMenu record);

    List<FrontMenu> selectTreeList(Map<String, Object> params);
}