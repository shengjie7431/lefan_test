package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyServiceAdvantage;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyServiceAdvantageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyServiceAdvantage record);

    int insertSelective(SurveyServiceAdvantage record);

    SurveyServiceAdvantage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyServiceAdvantage record);

    int updateByPrimaryKey(SurveyServiceAdvantage record);

    //数据
    List<SurveyServiceAdvantage> list(Map map);
    int listSize(Map map);
}