package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.*;
import com.lefancrm.apicenter.dto.report.SurveyAccEntrustDTO;
import com.lefancrm.apicenter.dto.report.SurveyReportCaseDTO;
import com.lefancrm.apicenter.model.SurveyRiskCase;
import com.lefancrm.apicenter.model.SurveyRiskCaseInfo;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyRiskCaseInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyRiskCaseInfo record);

    int insertSelective(SurveyRiskCaseInfo record);

    SurveyRiskCaseInfoDto selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyRiskCaseInfo record);

    int updateByPrimaryKey(SurveyRiskCaseInfo record);

    int updatePrice1IsCalc(@Param("ids") List<String> ids);
    int updatePrice2IsCalc(@Param("ids") List<String> ids);

    SurveyRiskCaseInfoDto getSurveyRiskCaseInfoBySurveyCno(String surveyCno);

    List<SurveyRiskCaseInfoDto> list(ApiRequest apiRequest);

    List<SurveyRiskCaseInfoDto> xhbBatchList(ApiRequest apiRequest);

    int xhbBatchListOperator(ApiRequest apiRequest);

    List<SurveyRiskCaseInfoDto> listMark(ApiRequest apiRequest);

    int listSize(ApiRequest apiRequest);

    //导出list
    List<SurveyRiskCaseInfoExportDto> exportList(ApiRequest apiRequest);
    int exportListSize(ApiRequest apiRequest);

    //对账清单
    List<SurveyRiskCaseInfoExportDto> surveyReportScoreList(ApiRequest apiRequest);
    int surveyReportScoreListSize(ApiRequest apiRequest);

    //分值导出
    List<SurveyRiskCaseInfoExportDto> orgReportScoreList(ApiRequest apiRequest);
    int orgReportScoreListSize(ApiRequest apiRequest);

    List<SurveyAccEntrustDTO> selectAccEntrustList(ApiRequest apiRequest);
    int selectAccEntrustListSize(ApiRequest apiRequest);

    //批量开票的所有金额
    double selectBillingMoney(@Param("ids") List<String> ids);

    //导出list
    List<SurveyRiskCaseInfoExportDto> exportHuZhuList(ApiRequest apiRequest);

    int selectOrgNewSendCount(Map map);
    int selectUserNewSendCount(Map map);


    /**
     * 可下发案件清单
     * @param apiRequest
     * @return
     */
    List<CanBeSuedCaseDTO> canBeSuedList(ApiRequest apiRequest);

    /**
     * 查询单个数据
     * @param findMap
     * @return
     */
    SurveyRiskCaseInfoDto selectByOne(Map findMap);

    int updateReimState(@Param("ids") List<String> ids);


    int updateIsPayEntrustFee(@Param("ids") List<String> ids);

    /**
     * 案件分派按条件导出
     * @param map
     * @return
     */
    List<SurveyRiskCaseInfoDtoExport> exportByCondition(Map map);

    /**
     * 狄大人报表 案件列表SQL
     * @param map
     * @return
     */
    List<SurveyReportCaseDTO> selectReportCaseItem(Map map);

    Integer selectCount(Map map);

    List<SurveyRiskCaseInfo> selectByMap(Map map);

    int updatePerformanceState(Map map);

    List<SurveyRiskCaseInfoDto> selectBsCaseList(String ids);

    List<SurveyCaseXHB> selectCaseListByClaimsNos(String claimsNos);

    int updateBsCaseList(Map<String,Object> paramMap);

    SurveyRiskCaseInfo selectByHandleId(Long handleId);

    int updateEntrustMoney(@Param("successData") List<SurveyCaseXHB> successData);

    List<SurveyRiskCaseInfoDto> listByIds(@Param("surveyInfoIds") List<Long> surveyInfoIds);
    
    void updatePerformanceStatePerson(Map map);

}