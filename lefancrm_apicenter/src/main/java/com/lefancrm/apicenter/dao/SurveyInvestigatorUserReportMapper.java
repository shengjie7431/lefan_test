package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.report.SurveyReportInvestigatorUserDTO;
import com.lefancrm.apicenter.model.SurveyInvestigatorUserReport;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SurveyInvestigatorUserReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyInvestigatorUserReport record);

    int insertSelective(SurveyInvestigatorUserReport record);

    SurveyInvestigatorUserReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyInvestigatorUserReport record);

    int updateByPrimaryKey(SurveyInvestigatorUserReport record);

    List<SurveyInvestigatorUserReport> selectAll(Map map);
    List<SurveyInvestigatorUserReport> selectAllNew(Map map);

    //调查报表：基础信息
    SurveyReportInvestigatorUserDTO selectBas(Map map);

    //调查报表 - 关键数据
    SurveyInvestigatorUserReport selectBySurveyUserId(Map map);

    List<SurveyInvestigatorUserReport> selectAllTrend(Map map);
    List<SurveyInvestigatorUserReport> selectAllTrendNew(Map map);
    Date selectMinReportDate(Map map);
    Date selectMaxReportDate(Map map);

    /**
     * 关键指标
     *
     * @param map
     * @return  总共返回三条数据
     *  1 当前机构数据
     *  2 总机构数据  页面显示 平均值。  总数据/机构数量
     *  3 各机构最大值数据
     */
    List<SurveyInvestigatorUserReport> selectSurveyKeyTar(Map map);
    int selectSurveyKeyTarSurveys(Map map);// 机构数量
}