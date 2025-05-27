package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyRiskCase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurveyRiskCaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyRiskCase record);

    int insertSelective(SurveyRiskCase record);

    SurveyRiskCase selectByPrimaryKey(Long id);
    List<SurveyRiskCase> selectByIdNumber(String idNumber);

    int updateByPrimaryKeySelective(SurveyRiskCase record);

    int updateByPrimaryKey(SurveyRiskCase record);

    List<SurveyRiskCase> list(@Param("surveyIds") List<Long> surveyIds);

    List<String> top3insures(Long entrustOrgId);
}