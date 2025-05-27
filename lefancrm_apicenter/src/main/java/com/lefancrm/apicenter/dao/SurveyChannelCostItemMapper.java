package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyChannelCostItem;

import java.util.List;

public interface SurveyChannelCostItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyChannelCostItem record);

    int insertSelective(SurveyChannelCostItem record);

    SurveyChannelCostItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyChannelCostItem record);

    int updateByPrimaryKey(SurveyChannelCostItem record);

    int deleteBySurveyChannelId(Long surveyChannelId);

    List<SurveyChannelCostItem> selectBySurveyChannelId(Long surveyChannelId);
}