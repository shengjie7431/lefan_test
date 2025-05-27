package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.SurveyCheckPreFlow;

import java.util.List;

public interface SurveyCheckPreFlowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyCheckPreFlow record);

    int insertSelective(SurveyCheckPreFlow record);

    SurveyCheckPreFlow selectByPrimaryKey(Long id);

    SurveyCheckPreFlow selectBySurveyAssignOrgId(Long surveyAssignOrgId);

    List<SurveyCheckPreFlow> selectPreListBySurveyAssignOrgId(Long surveyAssignOrgId);

    int updateByPrimaryKeySelective(SurveyCheckPreFlow record);

    int updateByPrimaryKey(SurveyCheckPreFlow record);
}