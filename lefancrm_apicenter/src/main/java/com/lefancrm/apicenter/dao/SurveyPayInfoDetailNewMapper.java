package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyPayInfoDetailNew;

import java.util.List;
import java.util.Map;

public interface SurveyPayInfoDetailNewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyPayInfoDetailNew record);

    int insertSelective(SurveyPayInfoDetailNew record);

    SurveyPayInfoDetailNew selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyPayInfoDetailNew record);

    int updateByPrimaryKey(SurveyPayInfoDetailNew record);

    int insertItems(Map<String,Object> paramMap);

    int updateChannelItems(String ids);

    int updateSettlementItems(Map<String,Object> paramMap);

    List<Long> getSettlementItems(Map<String,Object> paramMap);
}