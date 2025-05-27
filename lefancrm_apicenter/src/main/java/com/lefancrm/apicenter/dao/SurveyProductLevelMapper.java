package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyProductLevel;

import java.util.List;
import java.util.Map;

public interface SurveyProductLevelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyProductLevel record);

    int insertSelective(SurveyProductLevel record);

    SurveyProductLevel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyProductLevel record);

    int updateByPrimaryKey(SurveyProductLevel record);

    List<SurveyProductLevel> selectInfo(Map<String,Object> map);

    //根据productId删除
    int deleteByProductId(Long id);
}