package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyModelInfo;

import java.util.List;
import java.util.Map;

public interface SurveyModelInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyModelInfo record);

    int insertSelective(SurveyModelInfo record);

    SurveyModelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyModelInfo record);

    int updateByPrimaryKey(SurveyModelInfo record);

    //数据
    List<SurveyModelInfo> list(Map map);
    int listSize(Map map);
}