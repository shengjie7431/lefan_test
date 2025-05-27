package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyBankCard;

public interface SurveyBankCardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyBankCard record);

    int insertSelective(SurveyBankCard record);

    SurveyBankCard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyBankCard record);

    int updateByPrimaryKey(SurveyBankCard record);

    SurveyBankCard selectByUserId(Long userId);
}