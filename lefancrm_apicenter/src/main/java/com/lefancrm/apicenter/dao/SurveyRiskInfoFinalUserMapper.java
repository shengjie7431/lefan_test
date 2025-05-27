package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyRiskInfoFinalUser;

import java.util.Map;

public interface SurveyRiskInfoFinalUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyRiskInfoFinalUser record);

    int insertSelective(SurveyRiskInfoFinalUser record);

    SurveyRiskInfoFinalUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyRiskInfoFinalUser record);

    int updateByPrimaryKey(SurveyRiskInfoFinalUser record);

    SurveyRiskInfoFinalUser selectByInfo(Map map);
}