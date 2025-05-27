package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyChannelModelInfo;

import java.util.List;
import java.util.Map;

public interface SurveyChannelModelInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyChannelModelInfo record);

    int insertSelective(SurveyChannelModelInfo record);

    SurveyChannelModelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyChannelModelInfo record);

    int updateByPrimaryKey(SurveyChannelModelInfo record);

    int deleteByParam(Map paramMap);

    int insertRecords(Map paramMap);

    //数据
    List<SurveyChannelModelInfo> list(Map map);

    int listSize(Map map);

    SurveyChannelModelInfo selectByParam(Map map);

    int copy(Map<String,Object> paramMap);
}