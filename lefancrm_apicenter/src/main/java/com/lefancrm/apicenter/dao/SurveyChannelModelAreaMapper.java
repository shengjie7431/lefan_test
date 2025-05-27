package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyChannelModelArea;

import java.util.List;
import java.util.Map;

public interface SurveyChannelModelAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyChannelModelArea record);

    int insertSelective(SurveyChannelModelArea record);

    SurveyChannelModelArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyChannelModelArea record);

    int updateByPrimaryKey(SurveyChannelModelArea record);

    //数据
    List<SurveyChannelModelArea> list(Map map);

    int listSize(Map map);

    int copy(Map<String,Object> paramMap);
}