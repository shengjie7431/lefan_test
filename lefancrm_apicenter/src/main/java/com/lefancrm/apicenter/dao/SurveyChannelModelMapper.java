package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyChannelModel;

import java.util.List;
import java.util.Map;

public interface SurveyChannelModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyChannelModel record);

    int insertSelective(SurveyChannelModel record);

    SurveyChannelModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyChannelModel record);

    int updateByPrimaryKey(SurveyChannelModel record);


    //数据
    List<SurveyChannelModel> list(Map map);

    int listSize(Map map);
}