package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyScoreModelArea;

import java.util.List;
import java.util.Map;

public interface SurveyScoreModelAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyScoreModelArea record);

    int insertSelective(SurveyScoreModelArea record);

    SurveyScoreModelArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyScoreModelArea record);

    int updateByPrimaryKey(SurveyScoreModelArea record);

    //数据
    List<SurveyScoreModelArea> list(Map map);

    int listSize(Map map);
}