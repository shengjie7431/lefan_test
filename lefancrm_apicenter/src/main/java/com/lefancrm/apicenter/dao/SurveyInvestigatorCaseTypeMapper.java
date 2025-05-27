package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyInvestigatorCaseType;

import java.util.List;
import java.util.Map;

public interface SurveyInvestigatorCaseTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyInvestigatorCaseType record);

    int insertSelective(SurveyInvestigatorCaseType record);

    SurveyInvestigatorCaseType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyInvestigatorCaseType record);

    int updateByPrimaryKey(SurveyInvestigatorCaseType record);

    int getSurveyInvestigatorCaseTypeBySurveyInfoIdCount(Long surveyInfoId);

    SurveyInvestigatorCaseType getSurveyInvestigatorCaseTypeByOne(Map map);

    List<SurveyInvestigatorCaseType> getSurveyInvestigatorCaseTypesByCaseId(Long surveyUserCaseId);

    List<SurveyInvestigatorCaseType> list(Map map);
}