package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyRiskCaseInfoDto;
import com.lefancrm.apicenter.model.SurveyAssignOrgExtension;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyAssignOrgExtensionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyAssignOrgExtension record);

    int insertSelective(SurveyAssignOrgExtension record);

    SurveyAssignOrgExtension selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyAssignOrgExtension record);

    int updateByPrimaryKey(SurveyAssignOrgExtension record);

    List<SurveyAssignOrgExtension> list(Map map);



    List<SurveyAssignOrgExtension> listByIds(@Param("surveyInfoIds") List<Long> surveyInfoIds);
}