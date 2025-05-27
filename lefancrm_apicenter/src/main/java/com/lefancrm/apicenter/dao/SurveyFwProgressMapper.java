package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyFwProgress;

import java.util.List;

public interface SurveyFwProgressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyFwProgress record);

    int insertSelective(SurveyFwProgress record);

    SurveyFwProgress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyFwProgress record);

    int updateByPrimaryKey(SurveyFwProgress record);

    List<SurveyFwProgress> selectByFwId(Long fwId);
}