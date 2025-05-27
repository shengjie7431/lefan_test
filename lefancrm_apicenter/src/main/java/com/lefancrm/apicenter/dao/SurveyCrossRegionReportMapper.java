package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.report.*;
import com.lefancrm.apicenter.model.SurveyCrossRegionReport;

import java.util.List;
import java.util.Map;

public interface SurveyCrossRegionReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyCrossRegionReport record);

    int insertSelective(SurveyCrossRegionReport record);

    SurveyCrossRegionReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyCrossRegionReport record);

    int updateByPrimaryKey(SurveyCrossRegionReport record);

    List<SurveyCrossRegionReport> selectAll(Map map);
    List<SurveyCrossRegionReport> selectAllNew(Map map);

    List<SurveyReportAreaDTO> selectAllArea(Map map);

    List<SurveyReportEntrustPriceDTO> selectEntrustPrice(Map map);
    List<SurveyReportEntrustPriceXDTO> selectEntrustXPrice(Map map);
    List<SurveyReportEntrustPriceYDTO> selectEntrustYPrice(Map map);


    SurveyReportLefanDTO selectAllBas(Map map);
    int selectAllBasByMomTime(Map map);
    int selectAllBasByTbTime(Map map);

    //调查机构 -- 案件地域分布
    List<SurveyReportAreaDTO> selectOrgArea(Map map);
    List<SurveyReportAreaDTO> selectOrgAreaCity(Map map);
    //调查员 -  案件地域分布
    List<SurveyReportAreaDTO> selectSurveyArea(Map map);


    List<SurveyReportEntrustTextDTO> selectEntrustAge(Map map);
    List<SurveyReportEntrustTextDTO> selectEntrustSex(Map map);
    List<SurveyReportEntrustTextDTO> selectEntrustSafeType(Map map);

}