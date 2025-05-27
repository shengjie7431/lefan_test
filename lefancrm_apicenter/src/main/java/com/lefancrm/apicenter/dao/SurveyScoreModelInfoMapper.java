package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyScoreModelInfo;

import java.util.List;
import java.util.Map;

public interface SurveyScoreModelInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyScoreModelInfo record);

    int insertSelective(SurveyScoreModelInfo record);

    SurveyScoreModelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyScoreModelInfo record);

    int updateByPrimaryKey(SurveyScoreModelInfo record);

    int deleteByParam(Map paramMap);

    //数据
    List<SurveyScoreModelInfo> list(Map map);

    int listSize(Map map);

    SurveyScoreModelInfo selectByParam(Map map);
}