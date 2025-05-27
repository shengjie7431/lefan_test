package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyLevel;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyLevelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyLevel record);

    int insertSelective(SurveyLevel record);

    SurveyLevel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyLevel record);

    int updateByPrimaryKey(SurveyLevel record);

    //数据
    List<SurveyLevel> list(Map map);
    int listSize(Map map);

    //通过code查询 单条数据
    SurveyLevel selectOne(Map map);

    SurveyLevel selectByPoint(Long point);
}