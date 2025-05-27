package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyPunish;

import java.util.List;
import java.util.Map;

public interface SurveyPunishMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyPunish record);

    int insertSelective(SurveyPunish record);

    SurveyPunish selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyPunish record);

    int updateByPrimaryKey(SurveyPunish record);

    List<SurveyPunish> list(Map map);

    int listSize(Map map);
}