package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyProgress;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyProgressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyProgress record);

    int insertSelective(SurveyProgress record);

    SurveyProgress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyProgress record);

    int updateByPrimaryKey(SurveyProgress record);

    List<SurveyProgress> list(Map map);

    int listSize(Map map);

    List<SurveyProgress> selectBySurveyInfoIds(@Param("surveyInfoIds") List<Long> surveyInfoIds);
}