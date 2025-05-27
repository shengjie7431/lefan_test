package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyRiskCaseGuide;

public interface SurveyRiskCaseGuideMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyRiskCaseGuide record);

    int insertSelective(SurveyRiskCaseGuide record);

    SurveyRiskCaseGuide selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyRiskCaseGuide record);

    int updateByPrimaryKey(SurveyRiskCaseGuide record);

    SurveyRiskCaseGuide selectBySurveyInfoId(Long surveyInfoId);
}