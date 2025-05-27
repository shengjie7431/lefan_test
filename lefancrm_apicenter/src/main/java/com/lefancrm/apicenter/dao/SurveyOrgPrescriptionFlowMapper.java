package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.SurveyOrgPrescriptionFlow;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyOrgPrescriptionFlowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyOrgPrescriptionFlow record);

    int insertSelective(SurveyOrgPrescriptionFlow record);

    SurveyOrgPrescriptionFlow selectByPrimaryKey(Long id);

    SurveyOrgPrescriptionFlow selectByAssOrgIdLimitOne(Long assOrgId);

    int updateByPrimaryKeySelective(SurveyOrgPrescriptionFlow record);

    int updateByPrimaryKey(SurveyOrgPrescriptionFlow record);

    /**
     *
     * @param surveyInfoId
     * @param surveyAssignOrgId
     * @return
     */
    List<SurveyOrgPrescriptionFlow> selectByInfoIdAndSurOrgId(@Param("surveyInfoId") Long surveyInfoId, @Param("surveyAssignOrgId") Long surveyAssignOrgId);


    /**
     *
     * @param map
     * @return
     */
    List<SurveyOrgPrescriptionFlow> selectByInfoIdAndSurOrgIdPrescription(Map map);

    /**
     *  计算机构时效天数
     * @param surveyInfoId
     * @param surveyAssignOrgId
     * @return
     */
    double selectOrgAgingDayByInfoIdAndSurOrgId(@Param("surveyInfoId") Long surveyInfoId, @Param("surveyAssignOrgId") Long surveyAssignOrgId);

    int deleteByAssignOrgId(Long id);
}