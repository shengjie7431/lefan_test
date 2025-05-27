package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.dto.hzReport.AssessmentIndexDto;
import com.lefancrm.apicenter.dto.hzReport.IncomeAndCostDto;
import com.lefancrm.apicenter.dto.hzReport.ProgressTrackDto;
import com.lefancrm.apicenter.dto.hzReport.ScoreDto;
import com.lefancrm.apicenter.model.SurveyAssignOrg;
import com.lefancrm.apicenter.model.SurveyAssignOrgBack;
import com.lefancrm.apicenter.model.SurveyInvestigatorCase;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.ibatis.annotations.Param;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SurveyInvestigatorCaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyInvestigatorCase record);

    int insertSelective(SurveyInvestigatorCase record);

    SurveyInvestigatorCaseDto selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyInvestigatorCase record);

    int updateByPrimaryKey(SurveyInvestigatorCase record);

    List<SurveyInvestigatorCaseDto> list(ApiRequest apiRequest);

    List<SurveyInvestigatorCaseDto> selectByList(Map map);

    int listSize(ApiRequest apiRequest);

    SurveyInvestigatorCase getSurveyInvestigatorCaseByOne(Map map);

    SurveyInvestigatorCase selectLastCommitInfo(Long surveyInfoId);

    /**
     * 任务详情  显示 需要显示已拒绝且已删除的案件（拒绝案件的同时 案件的删除状态也变为2）
     * @param surveyInfoId
     * @return
     */
    List<SurveyInvestigatorCase> getSurveyInvestigatorCasesBySurveyInfoIdTasks(Long surveyInfoId);

    /**
     * 任务详情  计算  已拒绝 和 已删除的案件 不需要计算
     * @param surveyInfoId
     * @return
     */
    List<SurveyInvestigatorCase> getSurveyInvestigatorCasesBySurveyInfoId(Long surveyInfoId);

    List<SurveyInvestigatorCase> getSurveyInvestigatorCasesBySurveyAssorgCaseId(Long surveyAssorgCaseId);

    List<SurveyInvestigatorCase> getSurveyInvestigatorCasesBySurveyInfoIdAndOrgId(Map map);

    /**
     * 查询调查员是否是 第一次完成案件
     * @param surveyUserId
     * @return
     */
    int getFirstCloseSurveyInvestigatorCase(Long surveyUserId);

    /**
     * 查询时效案件，发送短信提醒
     * @param
     * @return
     */
    List<SurveyInvestigatorCaseDto> selectCaseNoCommit(Map<String,Object> map);

    List<SurveyMoneyDto> selectSurveyMoney(ApiRequest map);

    List<SurveyMoneyDtoDetail> selectSurveyMoneyDetail(ApiRequest map);

    int selectSurveyMoneyDetailSize(ApiRequest map);

    int appsInsert(Map map);
    int appsUpdate(Map map);
    int payUpdate(Map map);

    //委托金额明细
    List<SurveyMoneyDtoDetail> selectEntruetMoneyDetail(ApiRequest map);
    List<SurveyMoneyDtoDetail> selectSurveyMoneyDetailExport(ApiRequest map);
    int selectEntruetMoneyDetailSize(ApiRequest map);

    /**
     * 案源分值
     * @param map
     * @return
     */
    Double selectScore(Map map);

    /**
     * 案源分值明细
     * @param map
     * @return
     */
    List<SurveySourceDto> selectScoreDetail(Map map);


    List<SurveyInvestigatorCase> getSurveyInvestigatorCasesBySurveyInfoIdAndOrgIdNoUserId(Map map);
    List<SurveyInvestigatorCase> selectSurveyInvestigatorCasesByNoId(Map map);

    //调查员主动退回的案件
    List<SurveyInvestigatorCase> getSurveyInvestigatorCasesBySurveyRemark(Map map);

    List<SurveyInvestigatorCaseDto> selectSurveyInvestigatorCasesByInfo(Map map);

    List<SurveyInvestigatorCaseDto> getSuns(Map map);

    List<SurveyInvestigatorCaseDto> getReviewSuns(Map map);


    int scores(Map map);

    //互助 积分数据列表
    List<ScoreDto> getScoreListByInvestigator(Map map);//条件查询的调查员积分
    List<ScoreDto> getScoreListBySurveyOrg(Map map);//机构积分
    List<ScoreDto> getScoreListBySurveyOrgArea(Map map);//片区机构积分
    List<ScoreDto> getInvestigatorBySurveyOrg(Map map);//机构下的调查员积分

    List<SurveyInvestigatorCaseDto> selectAllBySurveyId(Long SurveyId);

    //根据id查询案件所有方向的所有报销费用和
    Double selectAllDirectionReimTotalMoneyById(Long id);


    List<ScoreDto> getScoreCaseListByInvestigator(ApiRequest apiRequest);
    int getScoreCaseListSizeByInvestigator(ApiRequest apiRequest);
    List<ScoreDto> getScoreCaseListByOrg(ApiRequest apiRequest);
    int getScoreCaseListSizeByOrg(ApiRequest apiRequest);

    //考核报表
    List<AssessmentIndexDto> selectAssessMentIndex(Map map);
    List<AssessmentIndexDto> selectStateNameByStatue(Map map);

    List<AssessmentIndexDto> selectAssessMentList(ApiRequest apiRequest);
    int selectAssessMentListSize(ApiRequest apiRequest);



    //收入与成本报表
    List<IncomeAndCostDto> selectIncomeAndCostList(Map map);

    /**
     * 调查员报表
     * @param map
     * @return
     */
    public InvestigatorReportDto selectInvestigatorReport(Map map);

    //互助 进度跟踪报表
    List<ProgressTrackDto> getProgressTrackListByInvestigator(Map map);//调查员进度跟踪
    List<ProgressTrackDto> getProgressTrackBySurveyOrg(Map map);//机构进度跟踪
    List<ProgressTrackDto> getProgressTrackBySurveyOrgArea(Map map);//片区机构进度跟踪

    /**
     * 调查员报表详情
     * @param map
     * @return
     */
    public List<InvestigatorDetailsDto> selectInvestigatorDetails(Map map);

    /**
     * 调查员报表详情总数
     * @param map
     * @return
     */
    public Integer selectInvestigatorDetailsCount(Map map);

    //进度报表案件查询
    List<ProgressTrackDto> getProgressTrackCaseListByInvestigator(ApiRequest apiRequest);
    int getProgressTrackCaseListSizeByInvestigator(ApiRequest apiRequest);
    List<ProgressTrackDto> getProgressTrackCaseListByOrg(ApiRequest apiRequest);
    int getProgressTrackCaseSizeListByOrg(ApiRequest apiRequest);

    /**
     * 查询所有人分值
     * @param map
     * @return
     */
    List<SurveyInvestigatorCaseDto> selectAllScore(Map map);

    /**
     * 带教奖励中的任务详情
     * @param map
     * @return
     */
    List<SurveyInvestigatorCaseDto> selectTaskDetails(Map map);

    /**
     * 查询相同案件的同事
     * @param map
     * @return
     */
    List<SurveyInvestigatorCaseDto> selectColleague(Map map);


    List<SurveyInvestigatorCase> initRate(Map map);

    /**
     * 根据条件查询
     * @param map
     * @return
     */
    SurveyInvestigatorCase selectOne(Map map);

    List<SurveyInvestigatorCase> selectInformation(Map map);

    /**
     * 阳性奖励列表
     * @param map
     * @return
     */
    List<SurveyInvestigatorPositiveRewardDto> selectPositiveReward(Map map);

    /**
     * 阳性奖励列表详情
     * @param map
     * @return
     */
    List<SurveyInvestigatorPositiveRewardDto> selectPositiveRewardDetails(Map map);

    List<SurveyInvestigatorCaseDto> selectInvInfoByOrgCaseId(Long orgCaseId);

    List<SurveyInvestigatorCaseDto> selectOrgCaseRemindList(HashMap<String, Object> paramMap);

    SurveyInvestigatorCase selectBySurveyInfoIdAndUserId(@Param("surveyInfoId") Long surveyInfoId, @Param("userId") Long userId);

    SurveyInvestigatorCase selectMaxReportDateByOrgCaseId(Long id);

    SurveyInvestigatorCase selectAllCreportByOrgCaseId(Long surveyAssorgCaseId);


    List<SurveyInvestigatorCaseDto> listByIds(@Param("surveyAssorgCaseIds") List<Long> surveyInfoIds);
}