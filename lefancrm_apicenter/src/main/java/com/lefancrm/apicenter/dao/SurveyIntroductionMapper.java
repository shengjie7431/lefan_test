package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyIntroduction;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyIntroductionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyIntroduction record);

    int insertSelective(SurveyIntroduction record);

    SurveyIntroduction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyIntroduction record);

    int updateByPrimaryKey(SurveyIntroduction record);

    //数据
    List<SurveyIntroduction> list(Map map);
    int listSize(Map map);
}