package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyQa;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyQaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyQa record);

    int insertSelective(SurveyQa record);

    SurveyQa selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyQa record);

    int updateByPrimaryKey(SurveyQa record);

    //数据
    List<SurveyQa> list(Map map);
    int listSize(Map map);
}