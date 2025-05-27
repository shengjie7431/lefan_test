package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyLfcoinDetail;

public interface SurveyLfcoinDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyLfcoinDetail record);

    int insertSelective(SurveyLfcoinDetail record);

    SurveyLfcoinDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyLfcoinDetail record);

    int updateByPrimaryKey(SurveyLfcoinDetail record);
}