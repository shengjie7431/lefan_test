package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyFwCase;

import java.util.List;
import java.util.Map;

public interface SurveyFwCaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyFwCase record);

    int insertSelective(SurveyFwCase record);

    SurveyFwCase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyFwCase record);

    int updateByPrimaryKey(SurveyFwCase record);

    List<SurveyFwCase> list(Map<String,Object> map);

    int listSize(Map map);
}