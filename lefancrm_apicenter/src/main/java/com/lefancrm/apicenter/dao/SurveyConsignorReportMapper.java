package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.report.SurveyReportEntrustDTO;
import com.lefancrm.apicenter.dto.report.SurveyTrendDTO;
import com.lefancrm.apicenter.model.SurveyConsignorReport;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SurveyConsignorReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignorReport record);

    int insertSelective(SurveyConsignorReport record);

    SurveyConsignorReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignorReport record);

    int updateByPrimaryKey(SurveyConsignorReport record);

    List<SurveyConsignorReport> selectAll(Map map);

    List<SurveyConsignorReport> selectAllNew(Map map);

    List<SurveyConsignorReport> selectAllTrend(Map map);

    List<SurveyConsignorReport> selectAllTrendNew1(Map map);

    List<SurveyConsignorReport> selectAllTrendNew1Year(Map map);

    List<SurveyConsignorReport> selectAllTrendNew2(Map map);

    List<SurveyConsignorReport> selectAllTrendNew2Year(Map map);

    SurveyReportEntrustDTO selectEntrustBas(Map map);

    List<SurveyTrendDTO> selectTrendYear(Map map);
    List<SurveyTrendDTO> selectTrendOrgYear(Map map);

    /**
     * 关键指标
     *
     * @param map
     * @return  总共返回三条数据
     *  1 当前机构数据
     *  2 总机构数据  页面显示 平均值。  总数据/机构数量
     *  3 各机构最大值数据
     */
    List<SurveyConsignorReport> selectEntrustKeyTar(Map map);
    int selectEntrustKeyTarEntrusts(Map map);// 机构数量

    Date selectMinReportDate(Map map);
    Date selectMaxReportDate(Map map);

}