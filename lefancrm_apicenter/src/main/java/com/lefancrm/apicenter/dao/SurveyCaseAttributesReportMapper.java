package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.report.SurveyReportEntrustSourceDTO;
import com.lefancrm.apicenter.model.SurveyCaseAttributesReport;

import java.util.List;
import java.util.Map;

public interface SurveyCaseAttributesReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyCaseAttributesReport record);

    int insertSelective(SurveyCaseAttributesReport record);

    SurveyCaseAttributesReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyCaseAttributesReport record);

    int updateByPrimaryKey(SurveyCaseAttributesReport record);

    List<SurveyCaseAttributesReport> selectAll(Map map);

    List<SurveyCaseAttributesReport> selectAllNew(Map map);

    List<SurveyReportEntrustSourceDTO> selectAllSourceNew(Map map);
}