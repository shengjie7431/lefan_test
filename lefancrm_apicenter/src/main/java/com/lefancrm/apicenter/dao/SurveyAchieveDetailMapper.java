package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyAchieveDetail;

public interface SurveyAchieveDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyAchieveDetail record);

    int insertSelective(SurveyAchieveDetail record);

    SurveyAchieveDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyAchieveDetail record);

    int updateByPrimaryKey(SurveyAchieveDetail record);
}