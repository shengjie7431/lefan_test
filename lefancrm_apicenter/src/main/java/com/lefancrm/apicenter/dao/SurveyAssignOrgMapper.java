package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.AssignOrgDTO;
import com.lefancrm.apicenter.dto.SurveyAssignOrgDto;
import com.lefancrm.apicenter.dto.avg.SurveyHuzhuDTO;
import com.lefancrm.apicenter.dto.hzReport.ScoreDto;
import com.lefancrm.apicenter.dto.report.SurveyOrgDTO;
import com.lefancrm.apicenter.dto.report.SurveyOrgDetailDTO;
import com.lefancrm.apicenter.dto.report.SurveyReportCaseDTO;
import com.lefancrm.apicenter.model.SurveyAssignOrg;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyAssignOrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyAssignOrg record);

    int insertSelective(SurveyAssignOrg record);

    SurveyAssignOrgDto selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyAssignOrg record);

    int updateByPrimaryKey(SurveyAssignOrg record);

    List<SurveyAssignOrgDto> list(Map map);

    int listSize(Map map);

    SurveyAssignOrgDto selectByOne(Map map);

    SurveyAssignOrgDto selectPrimaryOrg(Long surveyInfoId);

    int selectIsHavePrimaryOrg(Long surveyInfoId);

    int updateOrgPrimayTypeBySurveyInfoId(Long surveyInfoId);

    int updateListOrgId(Map map);


    List<SurveyOrgDTO> getSurveyOrgData(ApiRequest apiRequest);
    int getSurveyOrgDataSize(ApiRequest apiRequest);

    List<SurveyOrgDetailDTO> getSurveyOrgDataDetail(ApiRequest apiRequest);
    int getSurveyOrgDataDetailSize(ApiRequest apiRequest);
    SurveyHuzhuDTO getSurveyOrgDataAvg(ApiRequest apiRequest);

    List<SurveyAssignOrg> selectListBySurveyInfoIdAndNotOrgId(Map map);
    SurveyAssignOrg selectPirmayBySurveyInfoId(Long surveyInfoId);

    List<SurveyAssignOrg> selectSurveyAssignOrgByNoOrgId(Map map);

    /**
     * 获取机构主动退回的案件
     * @param map
     * @return
     */
    List<SurveyAssignOrg> selectSurveyAssignOrgByOrgOpinion(Map map);

    /**
     * 案件分派list
     * @param map
     * @return
     */
    List<SurveyAssignOrgDto> selectAssignOrgList(Map map);
    int selectAssignOrgListSize(Map map);

    /**
     * 调查回访list
     * @param map
     * @return
     */
    List<SurveyAssignOrgDto> selectAssignVisitList(Map map);
    int selectAssignVisitListSize(Map map);

    /**
     * 平台复审（互助）list
     * @param map
     * @return
     */
    List<SurveyAssignOrgDto> selectHelpReviewList(Map map);
    int selectHelpReviewListSize(Map map);

    /**
     * 根据Map查询机构
     * @param map
     * @return
     */
    List<SurveyAssignOrgDto> selectByMap(Map map);


    List<SurveyAssignOrg> initRate(Map map);

    List<AssignOrgDTO> selectRoic(Map map);


    /**
     * 延期审核list
     * @param map
     * @return
     */
    List<SurveyAssignOrgDto> selectExtensionTimeList(Map map);
    int selectExtensionTimeListSize(Map map);

    Integer updateInscompanyMoney(Map map);


    List<SurveyAssignOrgDto> selectChannels(Map<String,Object> paramMap);

    //查询该机构（省级），时间段内做的案件
    List<ScoreDto> selectOrgCaseList(Map map);
    int selectOrgCaseListSize(Map map);

    SurveyAssignOrgDto selectMaxOrgEndTimeBySurveyInfoId(Long surveyInfoId);

    List<SurveyAssignOrgDto> selectOrgCaseRemindList(Map paramMap);

    List<SurveyAssignOrg> selectByCases(List<SurveyReportCaseDTO> list);

    SurveyAssignOrg selectByInfoIdAndOrgId(@Param("surveyInfoId") Long surveyInfoId, @Param("orgId") Long orgId);

    List<SurveyAssignOrg> selectListByInfoId(Long surveyInfoId);
}