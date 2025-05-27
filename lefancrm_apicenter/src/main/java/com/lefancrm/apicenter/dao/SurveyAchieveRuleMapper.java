package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyAchieveRule;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyAchieveRuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyAchieveRule record);

    int insertSelective(SurveyAchieveRule record);

    SurveyAchieveRule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyAchieveRule record);

    int updateByPrimaryKey(SurveyAchieveRule record);

    //数据
    List<SurveyAchieveRule> list(Map map);
    int listSize(Map map);

    //通过code查询
    SurveyAchieveRule selectByCode(String code);
}