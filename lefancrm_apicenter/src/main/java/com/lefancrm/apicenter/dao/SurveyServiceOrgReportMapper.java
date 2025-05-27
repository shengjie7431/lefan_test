package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyServiceOrgReport;

import java.util.List;
import java.util.Map;

public interface SurveyServiceOrgReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyServiceOrgReport record);

    int insertSelective(SurveyServiceOrgReport record);

    SurveyServiceOrgReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyServiceOrgReport record);

    int updateByPrimaryKey(SurveyServiceOrgReport record);

    List<SurveyServiceOrgReport> selectAll(Map map);
    List<SurveyServiceOrgReport> selectAllNew(Map map);
    List<SurveyServiceOrgReport> selectAllNewSun(Map map);
}