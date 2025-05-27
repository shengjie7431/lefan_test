package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyServiceConsignorReport;

import java.util.List;
import java.util.Map;

public interface SurveyServiceConsignorReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyServiceConsignorReport record);

    int insertSelective(SurveyServiceConsignorReport record);

    SurveyServiceConsignorReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyServiceConsignorReport record);

    int updateByPrimaryKey(SurveyServiceConsignorReport record);

    List<SurveyServiceConsignorReport> selectAll(Map map);

    List<SurveyServiceConsignorReport> selectAllNew(Map map);
    List<SurveyServiceConsignorReport> selectAllNewSun(Map map);
}