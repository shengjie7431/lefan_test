package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyLfcoinRule;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyLfcoinRuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyLfcoinRule record);

    int insertSelective(SurveyLfcoinRule record);

    SurveyLfcoinRule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyLfcoinRule record);

    int updateByPrimaryKey(SurveyLfcoinRule record);

    //数据
    List<SurveyLfcoinRule> list(Map map);
    int listSize(Map map);

    //
    SurveyLfcoinRule selectByCode(String code);
}