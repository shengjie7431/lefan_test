package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyLfcoinExplain;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyLfcoinExplainMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyLfcoinExplain record);

    int insertSelective(SurveyLfcoinExplain record);

    SurveyLfcoinExplain selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyLfcoinExplain record);

    int updateByPrimaryKey(SurveyLfcoinExplain record);

    //数据
    List<SurveyLfcoinExplain> list(Map map);
    int listSize(Map map);
}