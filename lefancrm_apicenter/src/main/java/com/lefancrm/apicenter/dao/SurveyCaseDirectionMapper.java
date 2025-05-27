package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.dto.hzReport.CaseDirectionDto;
import com.lefancrm.apicenter.model.SurveyAreaCategoriesAreaCity;
import com.lefancrm.apicenter.model.SurveyCaseDirection;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyCaseDirectionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyCaseDirection record);

    int insertSelective(SurveyCaseDirection record);

    SurveyCaseDirection selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyCaseDirection record);

    int updateByPrimaryKey(SurveyCaseDirection record);

    List<SurveyCaseDirection> list(Map map);

    int listSize(Map map);

    //分值清单
    List<SurveyCaseDirectionScoreDto> listByScore(Map map);
    int listSizeByScore(Map map);
    //人员的调查方向详情
    List<SurveyCaseDirectionScoreDto> directionListByUser(Map map);

    /***
     * 委托方报表-对账清单 方向列表
     * @param map
     * @return
     */
    List<SurveyCaseDirection> selectAccEntrustListDirections(Map map);

    /***
     * 机构报表-分值清单 某调查机构下的方向列表
     * @param map
     * @return
     */
    List<SurveyCaseDirection> selectAllBySurveyOrgId(Map map);

    SurveyCaseDirection selectDirectionOneByName(Map map);

    List<SurveyCaseDirection> selectValidateDirectionName(Map map);

    SurveyDirectionMoneyDTO selectDirectionMoney(Map map);

    Double selectMaxPriceForShenDu(Map map);

    List<SurveyCaseDirection> afterScores(Map map);

    SurveyCaseDirection selectCaseDirectionBySurveyId(Long surveyId);

    /**
     * 互助案件区域分布报表查询接口
     * @param map
     * @return
     */
    List<SurveyCaseDirectionDto> selectReportList(Map map);

    /**
     * 根据所选择的省市级查询数据
     * @param map
     * @return
     */
    List<SurveyCaseDirectionDto> selectPmlList(Map map);

    /**
     * 互助案件区域分布报表详情查询接口
     * @param map
     * @return
     */
    List<SurveyRegionalDistributionCasesDto> detailsList(Map map);


    /**
     * 互助案件区域分布报表详情查询接口总数
     * @param map
     * @return
     */
    int detailsListSize(Map map);

    /**
     * 根据机构ID查询 改案件属于那个区域
     * @param surveyAssorgCaseId
     * @return 0市区 1郊区 2省会 3地级市 4县级市
     */
    int selectAreaTypeBySurveyAssOrgId(Long surveyAssorgCaseId);

    //互助 查得率报表
    List<CaseDirectionDto> getCaseDirectionByInvestigator(Map map);//调查员查得率报表
    List<CaseDirectionDto> getCaseDirectionBySurveyOrg(Map map);//机构查得率报表

    List<CaseDirectionDto> getCaseDirectionBySurveyArea(Map map);//机构查得率报表

    List<CaseDirectionDto> getInvestigatorBySurveyOrg(Map map);//通过机构查询调查员的查得率报表

    /**
     * 同事的调查方向
     * @return
     */
    List<SurveyCaseDirectionDto> selectDirectionColleagues(Map map);

    /**
     * 根据条件查询方向总数
     * @param map
     * @return
     */
    Integer selectTotalDirection(Map map);

    List<SurveyCaseDirection> selectDirection(Map map);

    /**
     * 查询某机构的所有方向。包含片区机构.  且 未提交过渠道费用的方向
     * @return
     */
    List<SurveyCaseDirection> selectDirections(Map<String,Object> paramMap);

    List<SurveyCaseDirection> selectAllBySurveyUserId (Map<String,Object> paramMap);

    List<SurveyCaseDirection> selectAllDirectionByRiskInfoId(Long riskInfoId);

    List<SurveyCaseDirection> selectByCases(List<SurveyRiskCaseInfoExportDto> list); //根据案件 查询所属方向

    Double selectScoreAllDirectionByInvCaseId(Long id);

    Double selectSurveyMoneyByAssorgId(Long assorgId);

    /**
     *  获取调查方深度案件价格
     * @param paramMap  机构案件ID 、互助或保司
     * @return
     */
    Double selectSurveyShenduMoneyByMap(Map<String,Object> paramMap);


    List<SurveyCaseDirection> selectClockDirections(Map<String,Object> paramMap);
}