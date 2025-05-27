package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyBusinessType;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyBusinessTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyBusinessType record);

    int insertSelective(SurveyBusinessType record);

    SurveyBusinessType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyBusinessType record);

    int updateByPrimaryKey(SurveyBusinessType record);

    //数据
    List<SurveyBusinessType> list(Map map);
    int listSize(Map map);
}