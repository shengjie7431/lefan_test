package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyTaskInfoContent;

import java.util.List;
import java.util.Map;

public interface SurveyTaskInfoContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyTaskInfoContent record);

    int insertSelective(SurveyTaskInfoContent record);

    SurveyTaskInfoContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyTaskInfoContent record);

    int updateByPrimaryKey(SurveyTaskInfoContent record);

    //数据
    List<SurveyTaskInfoContent> list(Map map);
    int listSize(Map map);
}