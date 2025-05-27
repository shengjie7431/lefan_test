package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyChannelCostNew;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyChannelCostNewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyChannelCostNew record);

    int insertSelective(SurveyChannelCostNew record);

    SurveyChannelCostNew selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyChannelCostNew record);

    int updateByPrimaryKey(SurveyChannelCostNew record);

    SurveyChannelCostNew selectChannelCostNewByDirectionId(Long surveyDirectionId);

    SurveyChannelCostNew selectChannelCostNewUp(Map map);

    int generate(SurveyChannelCostNew upRecord);

    List<SurveyChannelCostNew> list(ApiRequest apiRequest);

    int listSize(ApiRequest apiRequest);

    List<SurveyChannelCostNew> selectGeneratePayInfo(Map paramMap);

    int updateProPayByIds(String ids);

    List<SurveyChannelCostNew> selectDeleteChannel(Map paramMap);
}