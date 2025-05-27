package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyDirectionResultType;

import java.util.List;
import java.util.Map;

public interface SurveyDirectionResultTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyDirectionResultType record);

    int insertSelective(SurveyDirectionResultType record);

    SurveyDirectionResultType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyDirectionResultType record);

    int updateByPrimaryKey(SurveyDirectionResultType record);

    //数据
    List<SurveyDirectionResultType> list(Map map);
    int listSize(Map map);

    SurveyDirectionResultType selectByInfo(Map map);
}