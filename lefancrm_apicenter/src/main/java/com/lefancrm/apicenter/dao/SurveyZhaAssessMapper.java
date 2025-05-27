package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyZhaAssess;

import java.util.List;
import java.util.Map;

public interface SurveyZhaAssessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyZhaAssess record);

    int insertSelective(SurveyZhaAssess record);

    SurveyZhaAssess selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyZhaAssess record);

    int updateByPrimaryKey(SurveyZhaAssess record);

    List<SurveyZhaAssess> list(Map<String,Object> map);

    int listSize(Map map);
}