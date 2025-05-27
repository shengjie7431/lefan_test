package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyBusinessTaskType;

import java.util.List;
import java.util.Map;

public interface SurveyBusinessTaskTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyBusinessTaskType record);

    int insertSelective(SurveyBusinessTaskType record);

    SurveyBusinessTaskType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyBusinessTaskType record);

    int updateByPrimaryKey(SurveyBusinessTaskType record);

    //数据
    List<SurveyBusinessTaskType> list(Map map);
    int listSize(Map map);
}