package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.SurveyInvestigatorReInfo;
import com.lefancrm.apicenter.model.SurveyReInfo;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurveyInvestigatorReInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyInvestigatorReInfo record);

    int insertSelective(SurveyInvestigatorReInfo record);

    SurveyInvestigatorReInfo selectByPrimaryKey(Long id);

    List<SurveyInvestigatorReInfo> selectByParam(ApiRequest apiRequest);

    int selectByParamCount(ApiRequest apiRequest);

    int updateByPrimaryKeySelective(SurveyInvestigatorReInfo record);

    int updateByPrimaryKey(SurveyInvestigatorReInfo record);

    int insertBySelect(SurveyReInfo surveyReInfo);

    int selectReAllOkByReId(Long reId);

    List<SurveyInvestigatorReInfo> selectReStateBySurveyUserId(Long currentUserId);

    List<SurveyInvestigatorReInfo> selectByReName(String reName);

    List<SurveyInvestigatorReInfo> selectByReId(Long reId);
    List<SurveyInvestigatorReInfo> selectByReIds(String reIds);

    List<SurveyInvestigatorReInfo> selectCurrentReUserIdsByReId(@Param("id") Long id,@Param("preInfoDate") String preInfoDate);

    SurveyInvestigatorReInfo selectReInfoSurveyUserId(@Param("surveyUserId") Long surveyUserId, @Param("reInfoDate") String reInfoDate,@Param("surveyOrgId") Long surveyOrgId);

    SurveyInvestigatorReInfo selectMoneyBySelective(String clockIds);

    Double selectSurveyUserAllMoneyBySlective(@Param("surveyUserId") Long surveyUserId, @Param("clockDate") String clockDate,@Param("surveyOrgId") Long surveyOrgId);

    SurveyInvestigatorReInfo selectBySelective(Long clockId);
}