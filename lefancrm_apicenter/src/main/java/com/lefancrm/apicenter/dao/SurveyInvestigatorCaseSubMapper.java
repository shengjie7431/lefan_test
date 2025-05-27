package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyInvestigatorCaseSub;

public interface SurveyInvestigatorCaseSubMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyInvestigatorCaseSub record);

    int insertSelective(SurveyInvestigatorCaseSub record);

    SurveyInvestigatorCaseSub selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyInvestigatorCaseSub record);

    int updateByPrimaryKey(SurveyInvestigatorCaseSub record);

    SurveyInvestigatorCaseSub selectByInvestigatorCaseId(Long surveyInvestigatorCaseId);

    //清空所有“意见（绩效管理中意见）”
    int deleteAllStaffOpinion();
}