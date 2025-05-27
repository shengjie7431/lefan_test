package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyServiceType;

import java.util.List;
import java.util.Map;

public interface FinaSurveyServiceTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyServiceType record);

    int insertSelective(FinaSurveyServiceType record);

    FinaSurveyServiceType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSurveyServiceType record);

    int updateByPrimaryKey(FinaSurveyServiceType record);

    //数据
    List<FinaSurveyServiceType> list(Map map);
    int listSize(Map map);
}