package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyLevelExplain;

import java.util.List;
import java.util.Map;

public interface SurveyLevelExplainMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyLevelExplain record);

    int insertSelective(SurveyLevelExplain record);

    SurveyLevelExplain selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyLevelExplain record);

    int updateByPrimaryKey(SurveyLevelExplain record);

    //数据
    List<SurveyLevelExplain> list(Map map);
    int listSize(Map map);
}