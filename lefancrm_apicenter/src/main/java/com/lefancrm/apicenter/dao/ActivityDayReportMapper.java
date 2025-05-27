package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CaseSumDataDto;
import com.lefancrm.apicenter.dto.CaseSumReportDto;
import com.lefancrm.apicenter.dto.OrgActivityCountReportDto;
import com.lefancrm.apicenter.model.ActivityDayReport;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface ActivityDayReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityDayReport record);

    int insertSelective(ActivityDayReport record);

    ActivityDayReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityDayReport record);

    int updateByPrimaryKey(ActivityDayReport record);

    /**
     * 查询机构或者小组的实际金额
     * @param paramMap
     * @return
     */
    int querySaleAmountGold(Map<String, Object> paramMap);

    /**
     * 查询机构或者小组的实际金额(周期)
     * @param paramMap
     * @return
     */
    int querySaleAmountGoldDate(Map<String, Object> paramMap);

    /**
     * 查询自己的实际金额
     * @return
     */
    int queryMeSaleAmountGold(Map<String, Object> paramMap);

    /**
     * 查询自己的实际金额(周期)
     * @return
     */
    int queryMeSaleAmountGoldDate(Map<String, Object> paramMap);

    /**
     * 查询月业绩排名
     * @param paramMap
     * @return
     */
    List<ActivityDayReport> queryAchievementRank(Map<String, Object> paramMap);

    int queryAchievementRankCount(Map<String, Object> paramMap);

    /**
     * 查询月业绩排名(周期，小组或者机构)
     * @param paramMap
     * @return
     */
    List<ActivityDayReport> queryAchievementRankDate(Map<String, Object> paramMap);

    /**
     * 查询月业绩排名(周期，自己)
     * @param paramMap
     * @return
     */
    List<ActivityDayReport> queryMeAchievementRankDate(Map<String, Object> paramMap);

    /**
     * 查询成交客户本日排名
     * @return
     */
    List<ActivityDayReport> querySignCaseDayRank(Map<String, Object> paramMap);
    int querySignCaseDayRankCount();

    /**
     * 查询成交客户本周排名
     * @return
     */
    List<ActivityDayReport> querySignCaseWeekRank(Map<String, Object> paramMap);
    int querySignCaseWeekRankCount();


    /**
     * 查询成交客户上周排名
     * @return
     */
    List<ActivityDayReport> querySignCaseLastWeekRank(Map<String, Object> paramMap);
    int querySignCaseLastWeekRankCount();

    /**
     * 查询成交客户本月排名
     * @return
     */
    List<ActivityDayReport> querySignCaseMonthRank(Map<String, Object> paramMap);
    int querySignCaseMonthRankCount();

    /**
     * 查询成交客户上月排名
     * @return
     */
    List<ActivityDayReport> querySignCaseLastMonthRank(Map<String, Object> paramMap);
    int querySignCaseLastMonthRankCount();


    /**
     * 查询拜访客户本日排名
     * @return
     */
    List<ActivityDayReport> queryVisitCustomerDayRank(Map<String, Object> paramMap);
    int queryVisitCustomerDayRankCount();

    /**
     * 查询拜访客户本周排名
     * @return
     */
    List<ActivityDayReport> queryVisitCustomerWeekRank(Map<String, Object> paramMap);
    int queryVisitCustomerWeekRankCount();


    /**
     * 查询拜访客户上周排名
     * @return
     */
    List<ActivityDayReport> queryVisitCustomerLastWeekRank(Map<String, Object> paramMap);
    int queryVisitCustomerLastWeekRankCount();

    /**
     * 查询拜访客户本月排名
     * @return
     */
    List<ActivityDayReport> queryVisitCustomerMonthRank(Map<String, Object> paramMap);
    int queryVisitCustomerMonthRankCount();

    /**
     * 查询拜访客户上月排名
     * @return
     */
    List<ActivityDayReport> queryVisitCustomerLastMonthRank(Map<String, Object> paramMap);
    int queryVisitCustomerLastMonthRankCount();


    /**
     * 查询机构或者小组销售漏斗
     * @param paramMap
     * @return
     */
    ActivityDayReport querySaleFunnel(Map<String, Object> paramMap);

    /**
     * 查询自己销售漏斗
     * @param paramMap
     * @return
     */
    ActivityDayReport queryMeSaleFunnel(Map<String, Object> paramMap);

    /**
     * 查询机构或者小组销售漏斗(周期)
     * @param paramMap
     * @return
     */
    ActivityDayReport querySaleFunnelDate(Map<String, Object> paramMap);

    /**
     * 查询自己销售漏斗(周期)
     * @param paramMap
     * @return
     */
    ActivityDayReport queryMeSaleFunnelDate(Map<String, Object> paramMap);

    /**
     * 查询机构或者小组案件汇总数据
     * @param paramMap
     * @return
     */
    List<CaseSumDataDto> queryCaseDataNum(Map<String, Object> paramMap);

    /**
     * 查询自己案件汇总数据
     * @param paramMap
     * @return
     */
    List<CaseSumDataDto> queryMeCaseDataNum(Map<String, Object> paramMap);

    /**
     * 查询当前机构所有的成员的销售活动日报
     * @param apiReq
     * @return
     */
    List<ActivityDayReport> queryReportListByOrgId(ApiRequest apiReq);

    /**
     * 查询当前机构销售活动日报总记
     * @param paramMap
     * @return
     */
    List<OrgActivityCountReportDto> queryCountReportByOrgId(Map<String, Object> paramMap);

    //根据条件查询机构月成交业绩
    List<ActivityDayReport> queryOrgMoneyListById(ApiRequest apiReq);
    //根据条件查询机构CC月成交业绩
    List<ActivityDayReport> queryOrgCCMoneyListById(ApiRequest apiReq);


    /**
     * 查看机构或者小组案件汇总
     * @param paramMap
     * @return
     */
    CaseSumReportDto queryCaseReportNum(Map<String, Object> paramMap);

    /**
     * 查看自己案件汇总
     * @param paramMap
     * @return
     */
    CaseSumReportDto queryMeCaseReportNum(Map<String, Object> paramMap);
    int queryOutNumberByUserId(Map<String, Object> paramMap);
}