package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyServiceUserReport;

import java.util.List;
import java.util.Map;

public interface SurveyServiceUserReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyServiceUserReport record);

    int insertSelective(SurveyServiceUserReport record);

    SurveyServiceUserReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyServiceUserReport record);

    int updateByPrimaryKey(SurveyServiceUserReport record);

    List<SurveyServiceUserReport> selectAll(Map map);
    List<SurveyServiceUserReport> selectAllNew(Map map);
    List<SurveyServiceUserReport> selectAllNewSun(Map map);
}