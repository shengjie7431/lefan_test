package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyAssignOrgBack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurveyAssignOrgBackMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyAssignOrgBack record);

    int insertSelective(SurveyAssignOrgBack record);

    SurveyAssignOrgBack selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyAssignOrgBack record);

    int updateByPrimaryKey(SurveyAssignOrgBack record);

    List<SurveyAssignOrgBack> listByIds(@Param("surveyInfoIds") List<Long> surveyInfoIds);
}