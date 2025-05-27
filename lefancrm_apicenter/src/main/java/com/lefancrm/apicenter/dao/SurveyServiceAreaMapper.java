package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyServiceArea;

import java.util.List;
import java.util.Map;

public interface SurveyServiceAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyServiceArea record);

    int insertSelective(SurveyServiceArea record);

    SurveyServiceArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyServiceArea record);

    int updateByPrimaryKey(SurveyServiceArea record);

    //数据
    List<SurveyServiceArea> list(Map map);
    int listSize(Map map);
}