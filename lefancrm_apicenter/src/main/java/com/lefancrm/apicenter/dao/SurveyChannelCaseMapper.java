package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyChannelCase;

import java.util.List;

public interface SurveyChannelCaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyChannelCase record);

    int insertSelective(SurveyChannelCase record);

    SurveyChannelCase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyChannelCase record);

    int updateByPrimaryKey(SurveyChannelCase record);

    int deleteBySurveyChannelId(Long surveyChannelId);

    List<SurveyChannelCase> selectBySurveyChannelId(Long surveyChannelId);

    List<SurveyChannelCase> selectBySurveyChannelIdGroupBySurveyInfoId(Long surveyChannelId);

    int selectChannelNotOpr(Long surveyInfoId);

}