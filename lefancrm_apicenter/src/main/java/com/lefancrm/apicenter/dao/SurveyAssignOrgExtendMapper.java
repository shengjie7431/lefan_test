package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.SurveyAssignOrgExtend;

public interface SurveyAssignOrgExtendMapper {
    int deleteByPrimaryKey(Long surveyAssignOrgId);

    int insert(SurveyAssignOrgExtend record);

    int insertSelective(SurveyAssignOrgExtend record);

    SurveyAssignOrgExtend selectByPrimaryKey(Long surveyAssignOrgId);

    int updateByPrimaryKeySelective(SurveyAssignOrgExtend record);

    int updateByPrimaryKey(SurveyAssignOrgExtend record);
}