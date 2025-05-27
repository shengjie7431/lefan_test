package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyUserSign;

public interface SurveyUserSignMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyUserSign record);

    int insertSelective(SurveyUserSign record);

    SurveyUserSign selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyUserSign record);

    int updateByPrimaryKey(SurveyUserSign record);

    SurveyUserSign selectByUserId(Long userId);
}