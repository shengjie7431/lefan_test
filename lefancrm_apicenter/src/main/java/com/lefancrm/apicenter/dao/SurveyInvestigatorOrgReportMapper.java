package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.report.SurveyReportInvestigatorOrgDTO;
import com.lefancrm.apicenter.model.SurveyInvestigatorOrgReport;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SurveyInvestigatorOrgReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyInvestigatorOrgReport record);

    int insertSelective(SurveyInvestigatorOrgReport record);

    SurveyInvestigatorOrgReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyInvestigatorOrgReport record);

    int updateByPrimaryKey(SurveyInvestigatorOrgReport record);

    List<SurveyInvestigatorOrgReport> selectAll(Map map);
    List<SurveyInvestigatorOrgReport> selectAllNew(Map map);

    //调查报表：基础信息
    SurveyReportInvestigatorOrgDTO selectBas(Map map);

    SurveyInvestigatorOrgReport selectBySurveyOrgId(Map map);

    List<SurveyInvestigatorOrgReport> selectAllTrend(Map map);
    List<SurveyInvestigatorOrgReport> selectAllTrendNew(Map map);
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
    List<SurveyInvestigatorOrgReport> selectOrgKeyTar(Map map);
    int selectOrgKeyTarOrgs(Map map);// 机构数量

    //获取基础数据 -- 未结算费用
    double selectBasMoneyNotAcc(Long surveyOrgId);

    int selectReportTarOverData(Map map);

    double selectEntrustAgreementMoney(Map map);
}