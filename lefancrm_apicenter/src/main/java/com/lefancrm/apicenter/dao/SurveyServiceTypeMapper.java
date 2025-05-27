package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyServiceType;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyServiceTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyServiceType record);

    int insertSelective(SurveyServiceType record);

    SurveyServiceType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyServiceType record);

    int updateByPrimaryKey(SurveyServiceType record);

    //数据
    List<SurveyServiceType> list(Map map);
    int listSize(Map map);
}