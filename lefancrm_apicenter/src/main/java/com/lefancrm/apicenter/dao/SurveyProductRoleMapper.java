package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyProductRole;

import java.util.List;
import java.util.Map;

public interface SurveyProductRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyProductRole record);

    int insertSelective(SurveyProductRole record);

    SurveyProductRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyProductRole record);

    int updateByPrimaryKey(SurveyProductRole record);

    List<SurveyProductRole> selectInfo(Map<String,Object> map);

    //根据productId删除
    int deleteByProductId(Long id);
}