package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyChannelCost;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyChannelCostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyChannelCost record);

//    int insertSelective(SurveyChannelCost record);

    SurveyChannelCost selectByPrimaryKey(Long id);

//    int updateByPrimaryKeySelective(SurveyChannelCost record);

    int updateByPrimaryKey(SurveyChannelCost record);

    int updateProPayByIds(String ids);

    SurveyChannelCost selectUpItem(Map<String,Object> paramMap);

    List<SurveyChannelCost> list(ApiRequest apiRequest);

    int listSize(ApiRequest apiRequest);

    List<SurveyChannelCost> selectGeneratePayInfo(Map<String,Object> paramMap);
}