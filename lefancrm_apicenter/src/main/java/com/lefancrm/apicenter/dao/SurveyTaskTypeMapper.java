package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyTaskType;

import java.util.List;
import java.util.Map;

public interface SurveyTaskTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyTaskType record);

    int insertSelective(SurveyTaskType record);

    SurveyTaskType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyTaskType record);

    int updateByPrimaryKey(SurveyTaskType record);

    int getSurveyTaskTypeBySurveyInfoIdCount(Long surveyInfoId);

    List<SurveyTaskType> getSurveyTaskTypeBySurveyInfoId(Long surveyInfoId);

    //查询 已经分派给调查员的 任务类型
    List<SurveyTaskType> selectInfoForAssign(Map map);

}