package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyFollowDto;
import com.lefancrm.apicenter.model.SurveyFollow;

import java.util.List;
import java.util.Map;

public interface SurveyFollowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyFollow record);

    int insertSelective(SurveyFollow record);

    SurveyFollowDto selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyFollow record);

    int updateByPrimaryKey(SurveyFollow record);

    List<SurveyFollowDto> getSurveyFollowsBySurveyInfoId(Long surveyInfoId);
}