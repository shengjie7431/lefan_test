package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyChannelModel;
import com.lefancrm.apicenter.model.SurveyScoreModel;

import java.util.List;
import java.util.Map;

public interface SurveyScoreModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyScoreModel record);

    int insertSelective(SurveyScoreModel record);

    SurveyScoreModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyScoreModel record);

    int updateByPrimaryKey(SurveyScoreModel record);

    //数据
    List<SurveyChannelModel> list(Map map);

    int listSize(Map map);
}