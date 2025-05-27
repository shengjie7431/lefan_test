package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyHelpInfo;

import java.util.Map;

public interface SurveyHelpInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyHelpInfo record);

    int insertSelective(SurveyHelpInfo record);

    SurveyHelpInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyHelpInfo record);

    int updateByPrimaryKey(SurveyHelpInfo record);

    SurveyHelpInfo seletSurveyHelpInfoBySurveyInfoIdAndOrgId(Map map);
}