package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.SurveyUserPrescriptionFlow;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyUserPrescriptionFlowMapper {
    int deleteByPrimaryKey(Long id);

    int deleteBySurveyInvCaseId(Long caseId);

    int insert(SurveyUserPrescriptionFlow record);

    int insertSelective(SurveyUserPrescriptionFlow record);

    SurveyUserPrescriptionFlow selectByPrimaryKey(Long id);

    SurveyUserPrescriptionFlow selectByInvCaseIdLimitOne(Long invCaseId);

    int updateByPrimaryKeySelective(SurveyUserPrescriptionFlow record);

    int updateByPrimaryKey(SurveyUserPrescriptionFlow record);

    List<SurveyUserPrescriptionFlow> selectByInfoIdAndSurOrgIdInvId(@Param("surveyInfoId") Long id, @Param("surveyAssignOrgId") Long id1, @Param("surveyInvId") Long id2);

    /**
     * 计算调查员时效
     * @param surveyInfoId
     * @param surveyAssignOrgId
     * @param surveyInvId
     * @return
     */
    double selectInvAgingDayByInfoIdAndSurOrgIdInvId(@Param("surveyInfoId") Long surveyInfoId, @Param("surveyAssignOrgId") Long surveyAssignOrgId, @Param("surveyInvId") Long surveyInvId);


    List<SurveyUserPrescriptionFlow> selectPrescription(Map map);

    int deleteByInvCaseId(Long id);
}