package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyPayInfoDetail;

import java.util.List;
import java.util.Map;

public interface SurveyPayInfoDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyPayInfoDetail record);

    int insertSelective(SurveyPayInfoDetail record);

    SurveyPayInfoDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyPayInfoDetail record);

    int updateByPrimaryKey(SurveyPayInfoDetail record);

    List<SurveyPayInfoDetail> list(Map map);
    int listSize(Map map);

    int insertItems(Map<String,Object> paramMap);

    int updateChannelItems(String ids);
}