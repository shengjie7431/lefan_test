package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyRiskCaseVisit;

import java.util.List;
import java.util.Map;

public interface SurveyRiskCaseVisitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyRiskCaseVisit record);

    int insertSelective(SurveyRiskCaseVisit record);

    SurveyRiskCaseVisit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyRiskCaseVisit record);

    int updateByPrimaryKey(SurveyRiskCaseVisit record);

    //数据
    List<SurveyRiskCaseVisit> list(Map map);
    int listSize(Map map);

    SurveyRiskCaseVisit selectOne(Map map);
}